package com.moviereview.userinterface.Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.moviereview.userinterface.App;
import com.moviereview.userinterface.Movie;

public class MovieDetails {
    
    //Set up panels
    private JPanel movieViewPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel poster = new JPanel();
    private JPanel leftBottomPanel = new JPanel();
    private Movie movie;        //Currently selected movie

    //UI gaps
    private final int hGap = 120;
    private final int vGap = 15;

    public MovieDetails(Movie movie)
    {
        this.movie = movie;
        createWidgets();
    }

    public void createWidgets()
    {
        //Set up buttons
        JButton back = new JButton();
        back.setText("Back");
        JButton favorite = new JButton();
        favorite.setText("Add to favorites");
        JButton review = new JButton();
        review.setText("Review this movie");
        JButton viewReviews = new JButton();
        viewReviews.setText("More reviews");

        //Set up listener
        back.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.switchContentPanels(App.Views.Home);       
            }
        });

        //Set up panel layouts
        movieViewPanel.setLayout(new BorderLayout(hGap, vGap));
        leftPanel.setLayout(new BorderLayout(hGap, vGap));
        rightPanel.setLayout(new BorderLayout(hGap, vGap));
        poster.setLayout(new BorderLayout(hGap, vGap));
        leftBottomPanel.setLayout(new BorderLayout(hGap, vGap));

        poster.setMinimumSize(new Dimension(150, 200));
        poster.setPreferredSize(new Dimension(150, 200));
        poster.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        leftPanel.add(back, BorderLayout.PAGE_START);

        //Setup movie specific content
        if(movie != null)
        {
            JLabel title = new JLabel();
            title.setText(movie.getTitle());
            JLabel year = new JLabel();
            year.setText("(" + Integer.toString(movie.getYear())+ ")");
            JLabel summary = new JLabel();
            summary.setText("Summary: " + movie.getDesc());
            JLabel avgRating = new JLabel();
            avgRating.setText("Average rating: X.X");
            JLabel reviews = new JLabel();
            reviews.setText("Reviews");
    
            poster.add(title, BorderLayout.PAGE_START);
            poster.add(year, BorderLayout.PAGE_END);
            rightPanel.add(summary, BorderLayout.PAGE_START);
            rightPanel.add(avgRating, BorderLayout.CENTER);
            rightPanel.add(reviews, BorderLayout.PAGE_END);
        }

        leftBottomPanel.add(favorite, BorderLayout.PAGE_START);
        leftBottomPanel.add(review, BorderLayout.PAGE_END);

        leftPanel.add(poster, BorderLayout.CENTER);
        leftPanel.add(leftBottomPanel, BorderLayout.PAGE_END);
        rightPanel.add(viewReviews, BorderLayout.PAGE_END);
        movieViewPanel.add(leftPanel, BorderLayout.LINE_START);
        movieViewPanel.add(rightPanel, BorderLayout.LINE_END);
    }
    
    public JPanel getContent()
    {
        return movieViewPanel;
    }

    public void setMovie(Movie newMovie)
    {
        this.movie = newMovie;
    }
}
