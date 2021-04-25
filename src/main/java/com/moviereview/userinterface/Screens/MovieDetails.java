package com.moviereview.userinterface.Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.moviereview.userinterface.App;
import com.moviereview.userinterface.Movie;

public class MovieDetails {
    
    private JPanel movieViewPanel = new JPanel();
    private Movie movie;        //Currently selected movie

    public MovieDetails(Movie movie)
    {
        this.movie = movie;
        createWidgets();
    }

    public void createWidgets()
    {
        //Setup everything else
        JButton back = new JButton();
        back.setText("Back button");
        back.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.switchContentPanels(App.Views.Home);       
            }
        });

        //Setup movie specific content
        if(movie != null)
        {
            JLabel text = new JLabel();
            text.setText(movie.getTitle());
    
            movieViewPanel.add(text);
        }
        movieViewPanel.add(back);
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
