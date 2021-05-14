package com.moviereview.userinterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.moviereview.userinterface.App.Views;

public class FilterOptions{
    private String sTitle;
    private Genre genre;
    private int yearFrom;
    private int yearTo;
    private int scoreFrom;
    private int scoreTo;

    public FilterOptions()
    {
        resetOptions();
    }

    public void filterTitle(String text)
    {
        this.sTitle = text;
        filter();
    }

    public void filterGenre(Genre gen)
    {
        this.genre = gen;
        filter();
    }

    public void filterYear(int from, int to)
    {
        this.yearFrom = from;
        this.yearTo = to;
        filter();
    }

    public void filterScore(int from, int to)
    {
        this.scoreFrom = from;
        this.scoreTo = to;
        filter();
    }

    public void resetOptions()
    {
        //Could fetch these from sidebar instance?
        SideBar.getInstance();
        this.sTitle = "NULL";
        this.genre = SideBar.curGenre;
        this.yearFrom = SideBar.curYearFrom;
        this.yearTo = SideBar.curYearTo;
        this.scoreFrom = SideBar.curScoreFrom;
        this.scoreTo = SideBar.curScoreTo;
    }

    //This could be done much better
    public void filter()
    {
        System.out.println("Filtering...");
        List<Movie> newList = new ArrayList<Movie>();
        //Add movies the the new list if the title contains the substring
        for(Movie m: App.movieList)
        {
            if(this.sTitle == "NULL")
                newList.add(m);
            else if(m.getTitle().toLowerCase().contains(this.sTitle))
            {                
                newList.add(m);
            }
        }
        
        //Remove if the genre doesn't match
        Iterator<Movie> tmp = newList.iterator();
        while(tmp.hasNext())
        {
            Movie m = tmp.next();
            if(this.genre != Genre.All)
            {
                if(m.getGenre() != this.genre)
                    tmp.remove();
            }
        }
        
        tmp = newList.iterator();
        //Remove if the year is not in the range
        while(tmp.hasNext())
        {
            Movie m = tmp.next();
            if(this.yearFrom != 0 && this.yearTo != 0)
            {
                if(m.getYear() < this.yearFrom || m.getYear() > this.yearTo)
                    tmp.remove();
            }
        }

        tmp = newList.iterator();
        while(tmp.hasNext())
        {
            Movie m = tmp.next();
            if(this.scoreFrom != 0 && this.scoreTo != 0)
            {
                if(m.getScore() < this.scoreFrom || m.getScore() > this.scoreTo)
                    tmp.remove();
            }
        }

        App.curMovieList = newList;
        App.switchContentPanels(Views.Home);
    }
}
