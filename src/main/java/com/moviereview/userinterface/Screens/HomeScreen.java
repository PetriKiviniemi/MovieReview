package com.moviereview.userinterface.Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.event.MouseInputListener;

import com.moviereview.userinterface.App;
import com.moviereview.userinterface.Genre;
import com.moviereview.userinterface.Movie;
import com.moviereview.userinterface.NavBar;

public class HomeScreen {

    private JPanel homePanel = new JPanel();
    private String resultText = "Titles";

    public HomeScreen()
    {
        createWidgets();
    }

    private void createWidgets()
    {
        JPanel newHomePanel = new JPanel();
        newHomePanel.setLayout(new BoxLayout(newHomePanel, BoxLayout.Y_AXIS));
        //Create a panel for the movies. This could be grid or flow layout
        JPanel infoTextWrapper = new JPanel();
        JLabel infoText = new JLabel();
        //Fetch result text
        NavBar.getInstance();
        if(NavBar.hasSearched)
            resultText = "Results:" + App.curMovieList.size();
        else
            resultText = "Titles";
        infoText.setText(resultText);
        infoText.setFont(new Font("SansSerif", Font.PLAIN, 36));
        infoTextWrapper.add(infoText);

        JPanel moviePanel = new JPanel();
        
        int pos = 0;
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
        
        newHomePanel.add(infoTextWrapper);
        newHomePanel.add(moviePanel);

        //Replace the existing panel with the new one
        homePanel = newHomePanel;
    }

    public JPanel getContent()
    {
        createWidgets();
        return homePanel;
    }

}
