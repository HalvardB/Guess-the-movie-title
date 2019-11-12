package com.company;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        try {
            // Find how many movies there are
            File file = new File("movies.txt");
            Scanner scanner = new Scanner(file);
            Scanner scanner2 = new Scanner(System.in);

            // Create movie directory
            MovieDirectory directory = new MovieDirectory();
            int movieIndex = 0;

            while(scanner.hasNextLine()){
                String currentMovie = scanner.nextLine();
                Movie newMovie = new Movie();
                newMovie.name = currentMovie;
                directory.addMovie(newMovie, movieIndex);
                movieIndex++;
            }

            // Pick random movie
            int randomNumber = (int) (Math.random() * 24) + 1;
            String randomMovie = directory.getRandomMovie(randomNumber);
            int randomMovieLength = randomMovie.length();

            System.out.println("Solution: " + randomMovie);

            // Create a string with only ----- in it
            String hiddenString = "";
            char emptyChar = ' ';
            for(int i = 0; i<randomMovieLength; i++){
                if(randomMovie.charAt(i) == emptyChar){
                    hiddenString += " ";
                } else {
                    hiddenString += "-";
                }
            }
            System.out.println("Guess the movie title: " + hiddenString);

            int totalWrongGuesses = 0;
            StringBuilder newHiddenString = new StringBuilder(hiddenString);

            while(totalWrongGuesses < 10){
                // Ask the person to guess a character
                String stringGuess = scanner2.nextLine();

                // See if the character is in the movie name
                if(randomMovie.indexOf(stringGuess) > -1) {
                    int index = randomMovie.indexOf(stringGuess);
                    char currentChar = stringGuess.charAt(0);
                    newHiddenString.setCharAt(index, currentChar);

                    // In case there are more of the same character
                    if (randomMovie.substring(index + 1).indexOf(stringGuess) > -1) {
                        int index2 = randomMovie.substring(index + 1).indexOf(stringGuess);
                        newHiddenString.setCharAt((index + index2 + 1), currentChar);
                    }
                    System.out.println("Nicely, done! That was correct!");
                } else {
                    // if wrong guess
                    totalWrongGuesses++;
                    if(totalWrongGuesses == 10){
                        System.out.println("Sorry, you spent all your 10 guesses! The answer was '" + randomMovie +"'.");
                        break;
                    }
                    System.out.println("You have guessed " + totalWrongGuesses + " wrong letter(s), you have " + (10-totalWrongGuesses) + " wrong guesses left.");
                }

                // Check if finished
                if(newHiddenString.indexOf("-") == -1){
                    System.out.println("Congratulations, you did it!");
                    break;
                }

                System.out.println("Guess the movie title: " + newHiddenString);
            }

        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }
}
