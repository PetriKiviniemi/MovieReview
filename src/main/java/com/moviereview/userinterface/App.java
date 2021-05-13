package com.moviereview.userinterface;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.moviereview.userinterface.NavBar;
import com.moviereview.userinterface.SideBar;
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

    //Homescreen
    public static HomeScreen home = new HomeScreen();

    public static void switchContentPanels(Views view)
    {
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

    public static void resetMovieList()
    {
        App.curMovieList = App.movieList;
    }

    //Main/Home window frame goes here, since it's static 
    public static void main( String[] args )
    {
        //Create main frame
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        NavBar navBar = NavBar.getInstance();
        SideBar sideBar = SideBar.getInstance();
        filterOp = new FilterOptions();
        
        //Initialize the different content views
                //Add some temporary movies
        movieList.add(new Movie("Spiderman", "Photographer starts selling his own pictures.", 2004, Genre.Superhero));
        movieList.add(new Movie("Batman", "Rich man dresses up in latex to beat up criminals.", 1995, Genre.Superhero));
        movieList.add(new Movie("The Office", "Fictional documentary about working at an office.", 2005, Genre.Comedy));
        resetMovieList();

        switchContentPanels(Views.Home);

        f.getContentPane().add(BorderLayout.NORTH, navBar.getContent());
        f.getContentPane().add(BorderLayout.WEST, sideBar.getContent());
        f.getContentPane().add(BorderLayout.CENTER, contentView);

                
        f.setMinimumSize(new Dimension(1000, 640));
        f.pack();
        f.setSize(1024, 720);
        f.setVisible(true);
    }
}
