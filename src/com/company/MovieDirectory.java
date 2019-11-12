package com.company;

public class MovieDirectory {
    private Movie [] movies;

    // Constructor
    MovieDirectory(){
        movies = new Movie[25];
    }

    // Methods
    public void addMovie(Movie movie, int index){
        this.movies[index] = movie;
    }

    public String getRandomMovie(int index){
        return this.movies[index].name;
    }
}
