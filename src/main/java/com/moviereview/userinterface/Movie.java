package com.moviereview.userinterface;

import com.moviereview.userinterface.Genre;

public class Movie {
    
    private String title;
    private String description;
    private int year;
    private Genre genre;
    private int score;

    public Movie(String title, String desc, int year, Genre genre, int score)
    {
        this.title = title;
        this.description = desc;
        this.year = year;
        this.genre = genre;
        this.score = score;
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

    public int getScore()
    {
        return this.score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
