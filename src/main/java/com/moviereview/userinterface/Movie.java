package com.moviereview.userinterface;

import com.moviereview.userinterface.Genre;

public class Movie {
    
    private String title;
    private String description;
    private int year;
    private Genre genre;

    public Movie(String title, String desc, int year, Genre genre)
    {
        this.title = title;
        this.description = desc;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getDesc()
    {
        return this.description;
    }

    public int getYear()
    {
        return this.year;
    }

    public Genre getGenre()
    {
        return this.genre;
    }
}
