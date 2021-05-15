package com.moviereview.userinterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import com.moviereview.userinterface.Screens.HomeScreen;
import com.moviereview.userinterface.Screens.MovieDetails;

public class App
{
    public static enum Views{
        Home, Details
    };
    public static List<Movie> movieList = new ArrayList<Movie>();     //List of all movies/series
    public static List<Movie> curMovieList = new ArrayList<Movie>();  //List of currently displayed series
    public static Movie movieToReview;      //Currently selected movie
    private static JPanel contentView = new JPanel();
    public static FilterOptions filterOp;
    public static JFrame f;     //Main JFrame
    private static Views curView;

    //Homescreen
    public static HomeScreen home = new HomeScreen();

    public static void switchContentPanels(Views view)
    {
        curView = view;
        contentView.removeAll();
        JPanel panel = new JPanel();
        switch(view)
        {
            case Home:
                panel = home.getContent();
                break;
            case Details:
                MovieDetails detailsScreen = new MovieDetails(App.movieToReview);
                panel = detailsScreen.getContent();
                break;
        }
        contentView.add(panel);

        contentView.repaint();
        contentView.revalidate();
    }

    public static Dimension getContentViewDimensions()
    {
        if(f != null && f.getWidth() != 0)
        {
            return new Dimension(f.getWidth() - SideBar.getInstance().getContent().getWidth(), f.getHeight() - NavBar.getInstance().getContent().getHeight());
        }
        else
            return new Dimension(750, 500);
    }

    public static void resetMovieList()
    {
        App.curMovieList = App.movieList;
    }

    //Main/Home window frame goes here, since it's static 
    public static void main( String[] args )
    {
        //Create main frame
        f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        NavBar navBar = NavBar.getInstance();
        SideBar sideBar = SideBar.getInstance();
        filterOp = new FilterOptions();
        
        //Add some temporary movies
        movieList.add(new Movie("Spiderman", "Man gets superpowers and starts selling pictures of himself.", 2004, Genre.Superhero, 7));
        movieList.add(new Movie("Batman", "Rich man dresses up in latex to beat up criminals.", 1995, Genre.Superhero, 8));
        movieList.add(new Movie("The Office", "Fictional documentary about working at an office.", 2005, Genre.Comedy, 10));
        resetMovieList();

        switchContentPanels(Views.Home);

        f.getContentPane().add(BorderLayout.NORTH, navBar.getContent());
        f.getContentPane().add(BorderLayout.WEST, sideBar.getContent());
        f.getContentPane().add(BorderLayout.CENTER, contentView);

    
        f.addComponentListener(new ComponentListener()
        {

            @Override
            public void componentResized(ComponentEvent e) {
                switchContentPanels(curView);
                //For resizing the content views
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                
            }

            @Override
            public void componentShown(ComponentEvent e) {
                
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                
            }
            
        });
        f.setMinimumSize(new Dimension(1000, 640));
        f.pack();
        f.setSize(1024, 720);
        f.setVisible(true);
    }
}
