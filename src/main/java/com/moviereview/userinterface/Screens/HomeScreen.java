package com.moviereview.userinterface.Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import com.moviereview.userinterface.App;
import com.moviereview.userinterface.Movie;
import com.moviereview.userinterface.NavBar;

public class HomeScreen {

    private JPanel homePanel = new JPanel();
    private String resultText = "Highest review scores:";

    public HomeScreen()
    {
        createWidgets();
    }

    private void createWidgets()
    {
        JPanel newHomePanel = new JPanel();
        newHomePanel.setLayout(new BorderLayout());
        //Create a panel for the movies. This could be grid or flow layout
        JPanel infoTextWrapper = new JPanel();
        JLabel infoText = new JLabel();
        infoTextWrapper.setLayout(new BorderLayout());
        //Fetch result text
        NavBar.getInstance();
        JLabel headerText = new JLabel("Titles");
        headerText.setFont(new Font("SansSerif", Font.PLAIN, 26));

        if(NavBar.hasSearched)
            resultText = "Results:" + App.curMovieList.size();
        else
            resultText = "Best reviewed:";
        
        
        infoText.setText(resultText);
        infoText.setFont(new Font("SansSerif", Font.PLAIN, 26));

        infoTextWrapper.add(headerText, BorderLayout.NORTH);
        infoTextWrapper.add(infoText, BorderLayout.WEST);

        //Make wrapper for movies container -> this way we can stick the moviesPanel to the left side of this container
        JPanel moviesContainer = new JPanel();
        moviesContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        moviesContainer.setMinimumSize(App.getContentViewDimensions());
        Dimension tmpDim = new Dimension((int)App.getContentViewDimensions().getWidth()-50, (int)App.getContentViewDimensions().getHeight()-50);        
        moviesContainer.setPreferredSize(tmpDim);

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
        moviesContainer.add(moviePanel);
        newHomePanel.add(infoTextWrapper, BorderLayout.NORTH);
        newHomePanel.add(moviesContainer, BorderLayout.WEST);

        //Replace the existing panel with the new one
        homePanel = newHomePanel;
    }

    public JPanel getContent()
    {
        createWidgets();
        return homePanel;
    }

}
