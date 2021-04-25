package com.moviereview.userinterface.Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import com.moviereview.userinterface.App;
import com.moviereview.userinterface.Genre;
import com.moviereview.userinterface.Movie;

public class HomeScreen {

    private JPanel homePanel = new JPanel();

    public HomeScreen()
    {
        createWidgets();
    }

    private void createWidgets()
    {
        //Create a panel for the movies. This could be grid or flow layout
        JPanel moviePanel = new JPanel();

        //Create containers for every movie
        for (Movie movie : App.curMovieList) {
            JPanel container = new JPanel();
            container.setMinimumSize(new Dimension(150, 200));
            container.setPreferredSize(new Dimension(150, 200));
            JLabel movieTitle = new JLabel();
            movieTitle.setText(movie.getTitle());
            container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            container.add(movieTitle);
            
            //Create const versions of the movie to pass them forwards to the content panels
            final Movie tmp = movie;
            
            container.addMouseListener(new MouseInputListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub  
                }
    
                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub
                    App.movieToReview = tmp;
                    App.switchContentPanels(App.Views.Details);
                }
    
                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
    
                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
    
                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
    
                @Override
                public void mouseDragged(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
    
                @Override
                public void mouseMoved(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
            });

            moviePanel.add(container);
        }

        //Add different headers for different sectors here aswell

        homePanel = moviePanel;        
    }

    public JPanel getContent()
    {
        createWidgets();
        return homePanel;
    }

}
