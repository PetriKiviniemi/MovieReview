package com.moviereview.userinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagConstraints;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingContainer;
import javax.swing.border.LineBorder;
import javax.swing.text.View;

import com.moviereview.userinterface.App.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class NavBar {
    
    //Singleton variable of navbar
    private static NavBar navbarInstance = null;
    private static JPanel navBarPanel = null;
    private static boolean loggedIn = false;        //Boolean for managing logged-in state
    public static boolean hasSearched = false;     //Boolean for checking if user has searched
    private static JTextField srchField;
    private static JButton homeBtn;

    private NavBar()
    {
        //Initialize widgets
        createWidgets();
    }

    //Should be done with events?
    public static void resetSearchText()
    {
        srchField.setText("");
        hasSearched = false;
    }

    public static NavBar getInstance()
    {
        if(navbarInstance == null)
            navbarInstance = new NavBar();

        return navbarInstance;
    }

    private void createWidgets()
    {
        //Setup nav bar container
        navBarPanel = new JPanel();
        navBarPanel.setLayout(new BoxLayout(navBarPanel, BoxLayout.X_AXIS));
        navBarPanel.setBorder(new LineBorder(Color.BLACK));
        
        //Home button
        JPanel hBtnWrapper = new JPanel();
        BufferedImage hIcon;
        
        homeBtn = new JButton();
        //homeBtn.setBorderPainted(false);
        homeBtn.setFocusPainted(false);
        homeBtn.setContentAreaFilled(false);
        try {
            final URL url = Thread.currentThread().getContextClassLoader().getResource("images/homeIcon.png");
            ImageIcon ico = new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
            homeBtn.setIcon(ico);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Couldn't read image");
        }

        homeBtn.addMouseListener(new MouseListener()
        {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
                try {
                    final URL url = Thread.currentThread().getContextClassLoader().getResource("images/clickedHomeIcon.png");
                    ImageIcon ico = new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
                    NavBar.homeBtn.setIcon(ico);
                } catch (NullPointerException er) {
                    System.out.println("Couldn't read image");
                }
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
                try
                {
                    final URL url = Thread.currentThread().getContextClassLoader().getResource("images/homeIcon.png");
                    ImageIcon ico = new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
                    NavBar.homeBtn.setIcon(ico);
                    resetSearchText();
                    App.resetMovieList();
                    App.filterOp.resetOptions();
                    App.switchContentPanels(Views.Home);
                }
                catch(NullPointerException er)
                {

                }
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
            
        });

        homeBtn.setMaximumSize(new Dimension(64, 64));
        homeBtn.setPreferredSize(new Dimension(64, 64));
        homeBtn.setBorder(new RoundedBorder(10));

        hBtnWrapper.add(homeBtn);
        hBtnWrapper.setBorder(BorderFactory.createEmptyBorder(15, 65, 25, 50)); 

        //Search container
        GridBagLayout searchLayout = new GridBagLayout();
        GridBagConstraints layoutC = new GridBagConstraints();
        
        JPanel searchContainer = new JPanel();
        searchContainer.setLayout(searchLayout);
        searchContainer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        searchContainer.setBorder(BorderFactory.createEmptyBorder(15, 20, 25, 0));       //Set component margin with empty border
        searchContainer.setMaximumSize(new Dimension(200, 200));
        
        JLabel sText = new JLabel("HAKU");
        srchField = new JTextField(20);
        srchField.setOpaque(false);

        JLabel srchWrapper = new JLabel( new ImageIcon());
        srchWrapper.setLayout(new BorderLayout());
        srchWrapper.add(srchField);
        //Add Magnifying glass icon to the search field
        try {
            final URL url = Thread.currentThread().getContextClassLoader().getResource("images/magnGlassIcon.png");
            ImageIcon ico = new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
            srchWrapper.setIcon(ico);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Couldn't read image");
        }

        JButton sBtn = new JButton();
        
        //Search styling
        sText.setFont(new Font("SansSerif", Font.PLAIN, 24));
        srchField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        srchField.setMinimumSize(new Dimension(200, 25));
        sBtn.setText("Hae");
        
        //Add contents to search container
        layoutC.weightx = 0;
        layoutC.fill = GridBagConstraints.HORIZONTAL;
        layoutC.gridx = 0;
        layoutC.gridy = 0;
        layoutC.ipady = 0;
        searchContainer.add(sText, layoutC);
        
        layoutC.weightx = 0.5;
        layoutC.fill = GridBagConstraints.HORIZONTAL;
        layoutC.gridx = 0;
        layoutC.gridy = 1;
        searchContainer.add(srchWrapper, layoutC);

        layoutC.weightx = 0;
        layoutC.fill = GridBagConstraints.HORIZONTAL;
        layoutC.gridx = 1;
        layoutC.gridy = 1;
        searchContainer.add(sBtn, layoutC);
        sBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = srchField.getText();
                if(text.isBlank() || text.isEmpty())
                    text = "NULL";
                hasSearched = true;
                App.filterOp.filterTitle(text);
            }
        });
        //Login button
        JPanel loginWrapper = new JPanel();
        loginWrapper.setBorder(BorderFactory.createEmptyBorder(30, 200, 25, 0));
        
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("SansSerif", Font.PLAIN, 24));
        loginBtn.setPreferredSize(new Dimension(200, 50));
        
        loginWrapper.add(loginBtn);
        
        navBarPanel.add(hBtnWrapper);
        navBarPanel.add(Box.createHorizontalGlue());
        navBarPanel.add(searchContainer);        
        navBarPanel.add(Box.createHorizontalGlue());
        navBarPanel.add(loginWrapper);
    }

    public JPanel getContent()
    {
        return navBarPanel;
    }

    public int getWidth()
    {
        return navBarPanel.getWidth();
    }

    public int getHeight()
    {
        return navBarPanel.getWidth();
    }

}
