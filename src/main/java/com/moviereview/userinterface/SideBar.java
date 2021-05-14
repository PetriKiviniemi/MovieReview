package com.moviereview.userinterface;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.moviereview.userinterface.App.Views;


public class SideBar {

    private static SideBar sideBar = null;
    private static JPanel sideBarPanel = null;
    private static List<Genre> genreOptions = new ArrayList<Genre>();
    //Widgets
    private static JComboBox genreBox;
    private static JComboBox dateFromBox;
    private static JComboBox dateToBox;
    private static JComboBox scoreFromBox;
    private static JComboBox scoreToBox;
    //Store the filter options for comparing if they have changed
    public static Genre curGenre;
    public static int curYearFrom;
    public static int curYearTo;
    public static int curScoreFrom;
    public static int curScoreTo;
    
    //Singleton, we only want one of these ever.
    private SideBar()
    {
        //Initialize widgets
        genreOptions.add(Genre.All);
        genreOptions.add(Genre.Comedy);
        genreOptions.add(Genre.Action);
        genreOptions.add(Genre.Superhero);
        curGenre = genreOptions.get(0);
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
        
        JLabel sText = new JLabel("Select filters");
        sText.setVerticalAlignment(JLabel.CENTER);
        sText.setHorizontalAlignment(JLabel.CENTER);
        sText.setFont(new Font("SansSerif", Font.PLAIN, 24));
        
        JButton resetFilters = new JButton();
        resetFilters.setText("Reset");
        resetFilters.setFont(new Font("SansSerif", Font.PLAIN, 24));

        //Instead of having static objects, these could be objects that receive events, such as "Reset filters"
        resetFilters.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                App.filterOp.resetOptions();
                if(genreBox != null)
                    genreBox.setSelectedItem(genreBox.getItemAt(0));
                if(dateFromBox != null)
                    dateFromBox.setSelectedItem(dateFromBox.getItemAt(0));
                if(dateToBox != null)
                    dateToBox.setSelectedItem(dateToBox.getItemAt(dateToBox.getItemCount()-1));
                if(scoreFromBox != null)
                    scoreFromBox.setSelectedItem(scoreFromBox.getItemAt(0));
                if(scoreToBox != null)
                    scoreToBox.setSelectedItem(scoreToBox.getItemAt(scoreToBox.getItemCount()-1));
            }
            
        });

        filterContainer.add(sText);
        filterContainer.add(resetFilters);

        //Genre
        JPanel genreContainer = new JPanel();
        genreContainer.setPreferredSize(new Dimension(sideBarPanel.getWidth(), 200));

        JLabel gText = new JLabel("Genre");
        gText.setAlignmentX(genreContainer.CENTER_ALIGNMENT);
        gText.setFont(new Font("SansSerif", Font.PLAIN, 24));

        genreBox = new JComboBox(genreOptions.toArray());
        genreBox.setFont(new Font("SansSerif", Font.PLAIN, 24));
        
        genreBox.addItemListener(new ItemListener()
        {

            @Override
            public void itemStateChanged(ItemEvent e) {
                
                if((Genre)e.getItem() != curGenre)
                {
                    App.filterOp.filterGenre((Genre)e.getItem());
                    curGenre = (Genre)e.getItem();
                }
            }
            
        });
        genreContainer.add(gText);
        genreContainer.add(Box.createRigidArea(new Dimension(0, 42)));
        genreContainer.add(genreBox);

        //Year
        JPanel yearContainer = new JPanel();
        yearContainer.setLayout(new BorderLayout());
        yearContainer.setPreferredSize(new Dimension(sideBarPanel.getWidth(), 200));

        JLabel yText = new JLabel("Year");
        yText.setVerticalAlignment(JLabel.CENTER);
        yText.setHorizontalAlignment(JLabel.CENTER);

        yText.setFont(new Font("SansSerif", Font.PLAIN, 24));

        JPanel datesContainer = new JPanel();
        
        List<String> dates = new ArrayList<String>();
        Integer year;
        for(year = 1950; year <= 2021; year++)
        {
            dates.add(year.toString());
        }
        dateFromBox = new JComboBox(dates.toArray());
        dateToBox = new JComboBox(dates.toArray());
        dateFromBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
        dateToBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
        dateFromBox.setSelectedItem(dateFromBox.getItemAt(0));
        dateToBox.setSelectedItem(dateToBox.getItemAt(dateToBox.getItemCount()-1));
        curYearFrom = Integer.parseInt(dateFromBox.getItemAt(0).toString());
        curYearTo = Integer.parseInt(dateToBox.getItemAt(dateToBox.getItemCount()-1).toString());
        
        dateFromBox.addItemListener(new ItemListener()
        {

            @Override
            public void itemStateChanged(ItemEvent e) {
                int newYear = Integer.parseInt(e.getItem().toString());
                if(newYear != curYearFrom)
                {
                    App.filterOp.filterYear(newYear, curYearTo);
                    curYearFrom = newYear;
                }
                
            }
            
        });

        dateToBox.addItemListener(new ItemListener()
        {

            @Override
            public void itemStateChanged(ItemEvent e) {
                int newYear = Integer.parseInt(e.getItem().toString());
                if(newYear != curYearTo)
                {
                    App.filterOp.filterYear(curYearFrom, newYear);
                    curYearTo = newYear;
                }
                
            }
            
        });

        datesContainer.add(dateFromBox);
        datesContainer.add(dateToBox);
        yearContainer.add(yText, BorderLayout.NORTH);
        yearContainer.add(datesContainer, BorderLayout.CENTER);

        //Filter for review scores
        JPanel reviewScoreContainer = new JPanel();
        reviewScoreContainer.setLayout(new BorderLayout());
        reviewScoreContainer.setPreferredSize(new Dimension(sideBarPanel.getWidth(), 200));

        JLabel scoreText = new JLabel("Score");;
        scoreText.setFont(new Font("SansSerif", Font.PLAIN, 24));
        scoreText.setVerticalAlignment(JLabel.CENTER);
        scoreText.setHorizontalAlignment(JLabel.CENTER);

        JPanel scoresContainer = new JPanel();

        List<String> scores = new ArrayList<String>();
        Integer score;
        for(score = 1; score <= 10; score++)
        {
            scores.add(score.toString());
        }
        scoreFromBox = new JComboBox(scores.toArray());
        scoreToBox = new JComboBox(scores.toArray());

        scoreFromBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
        scoreToBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
        
        scoreFromBox.setSelectedItem(scoreFromBox.getItemAt(0));
        scoreToBox.setSelectedItem(scoreToBox.getItemAt(scoreToBox.getItemCount()-1));
        
        curScoreFrom = Integer.parseInt(scoreFromBox.getItemAt(0).toString());
        curScoreTo = Integer.parseInt(scoreToBox.getItemAt(scoreToBox.getItemCount()-1).toString());
        
        scoreFromBox.addItemListener(new ItemListener()
        {

            @Override
            public void itemStateChanged(ItemEvent e) {
                int newScore = Integer.parseInt(e.getItem().toString());
                if(newScore != curScoreFrom)
                {
                    App.filterOp.filterScore(newScore, curScoreTo);
                    curScoreFrom = newScore;
                }
                
            }
            
        });
 
        scoreToBox.addItemListener(new ItemListener()
        {

            @Override
            public void itemStateChanged(ItemEvent e) {
                int newScore = Integer.parseInt(e.getItem().toString());
                if(newScore != curScoreTo)
                {
                    App.filterOp.filterScore(curScoreFrom, newScore);
                    curScoreTo = newScore;
                }
                
            }
            
        });

        scoresContainer.add(scoreFromBox);
        scoresContainer.add(scoreToBox);
        reviewScoreContainer.add(scoreText, BorderLayout.NORTH);
        reviewScoreContainer.add(scoresContainer, BorderLayout.CENTER);

        sideBarPanel.add(filterContainer);
        sideBarPanel.add(genreContainer);
        sideBarPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        sideBarPanel.add(yearContainer);
        sideBarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        sideBarPanel.add(reviewScoreContainer);
        sideBarPanel.add(Box.createRigidArea(new Dimension(0, 50)));

    }

    public JPanel getContent()
    {
        return sideBarPanel;
    }
    
}
