package com.moviereview.userinterface;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class SideBar {

    //Singleton variable of navbar
    private static SideBar sideBar = null;
    private static JPanel sideBarPanel = null;
    private static List<String> genreOptions = new ArrayList<String>(); 
    
    private SideBar()
    {
        //Initialize widgets
        genreOptions.add("Kaikki");
        genreOptions.add("Toiminta");
        genreOptions.add("Superhero");
        createWidgets();
    }

    public static SideBar getInstance()
    {
        if(sideBar == null)
            sideBar = new SideBar();

        return sideBar;
    }

    public void createWidgets()
    {
        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        sideBarPanel.setBorder(new LineBorder(Color.BLACK));
        sideBarPanel.setPreferredSize(new Dimension(220, 200));

        JPanel filterContainer = new JPanel();
        filterContainer.setPreferredSize(new Dimension(sideBarPanel.getWidth(), 200));
        
        JLabel sText = new JLabel("Suodattimet");
        sText.setVerticalAlignment(JLabel.CENTER);
        sText.setHorizontalAlignment(JLabel.CENTER);
        sText.setFont(new Font("SansSerif", Font.PLAIN, 24));
        
        JButton resetFilters = new JButton();
        resetFilters.setText("Nollaa valinnat");
        resetFilters.setFont(new Font("SansSerif", Font.PLAIN, 24));
        filterContainer.add(sText);
        filterContainer.add(resetFilters);

        //Genre
        JPanel genreContainer = new JPanel();
        genreContainer.setPreferredSize(new Dimension(sideBarPanel.getWidth(), 200));

        JLabel gText = new JLabel("Genre");
        gText.setAlignmentX(genreContainer.CENTER_ALIGNMENT);
        gText.setFont(new Font("SansSerif", Font.PLAIN, 24));

        JComboBox genreBox = new JComboBox(genreOptions.toArray());
        genreBox.setFont(new Font("SansSerif", Font.PLAIN, 24));
        
        genreContainer.add(gText);
        genreContainer.add(Box.createRigidArea(new Dimension(0, 42)));
        genreContainer.add(genreBox);

        //Year
        JPanel yearContainer = new JPanel();
        yearContainer.setPreferredSize(new Dimension(sideBarPanel.getWidth(), 200));

        JLabel yText = new JLabel("Vuosiluku");
        yText.setVerticalAlignment(JLabel.CENTER);
        yText.setHorizontalAlignment(JLabel.CENTER);
        yText.setFont(new Font("SansSerif", Font.PLAIN, 24));

        JPanel datesContainer = new JPanel();
        //datesContainer.setPreferredSize(new Dimension(yearContainer.getWidth(), 200));
        
        List<String> dates = new ArrayList<String>();
        Integer year;
        for(year = 1950; year < 2021; year++)
        {
            dates.add(year.toString());
        }
        JComboBox dateFromBox = new JComboBox(dates.toArray());
        JComboBox dateToBox = new JComboBox(dates.toArray());
        dateFromBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
        dateToBox.setFont(new Font("SansSerif", Font.PLAIN, 18));

        datesContainer.add(dateFromBox);
        datesContainer.add(dateToBox);
        yearContainer.add(yText);
        yearContainer.add(datesContainer);

        sideBarPanel.add(filterContainer);
        sideBarPanel.add(genreContainer);
        sideBarPanel.add(yearContainer);
        sideBarPanel.add(Box.createRigidArea(new Dimension(0, 200)));

    }

    public JPanel getContent()
    {
        return sideBarPanel;
    }
    
}
