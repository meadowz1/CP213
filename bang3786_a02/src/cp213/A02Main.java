package cp213;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sample testing for Assignment 2.
 *
 * @author David Brown
 * @version 2024-09-01
 */
public class A02Main {
    // Constants
    private static final String LINE = "-".repeat(40);

    /**
     * Main method.
     *
     * @param args unused
     */
    public static void main(final String[] args) {
	// Sets console I/O to UTF-8 (Required for Windows as of Java 18.)
	System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
	System.out.println("Assignment 2 Methods Tests");
	System.out.println();
	testMovie();
    }

    /**
     * Tests various Movie-related functionalities.
     */
    public static void testMovie() {
	testMovieCompareTo(); // Test compareTo method in Movie class

	// Print out list of genres
	System.out.println("List of genres:");
	System.out.println(Movie.genresMenu());
	System.out.println(LINE);

	// Create a new movie object and display its data
	System.out.println("New Movie Object:");
	Movie movie = new Movie("Dark City", 1998, "Alex Proyas", 7.8, 0);
	System.out.println(movie);
	System.out.println(LINE);

	// Display the getters for the movie object
	System.out.println("Getters for Dark City");
	System.out.println("Title: " + movie.getTitle());
	System.out.println("Year: " + movie.getYear());
	System.out.println("Genre (int): " + movie.getGenre());
	System.out.println("Genre (String): " + movie.genreToName());
	System.out.println("Director: " + movie.getDirector());
	System.out.println("Rating: " + movie.getRating());
	System.out.println(LINE);

	// Create a movie using user input from the keyboard
	System.out.println("Get Movie from keyboard:");
	final Scanner keyboard = new Scanner(System.in);
	movie = MovieUtilities.getMovie(keyboard);
	System.out.println();
	System.out.println(movie);
	System.out.println(LINE);

	// Create a movie from a formatted string
	System.out.println("readMovie from string");
	String line = "Dark City|1998|Alex Proyas|7.8|0";
	movie = MovieUtilities.readMovie(line);
	System.out.println(movie);
	System.out.println(LINE);

	// Read movies from a file
	System.out.println("Read Movies from file:");
	ArrayList<Movie> movies = new ArrayList<>();
	try {
	    final File file = new File("movies.txt"); // Ensure this file exists in your project
	    Scanner fileIn = new Scanner(file);
	    movies = MovieUtilities.readMovies(fileIn);
	    fileIn.close();
	} catch (FileNotFoundException e1) {
	    e1.printStackTrace();
	}

	// Print out all the movies read from the file
	for (final Movie m : movies) {
	    System.out.println(m);
	}
	System.out.println(LINE);

	// Count the number of movies for each genre
	int[] counts = MovieUtilities.genreCounts(movies);
	int n = Movie.GENRES.length;
	for (int i = 0; i < n; i++) {
	    System.out.println(Movie.GENRES[i] + ": " + counts[i]);
	}
	System.out.println(LINE);

	// Get movies by year
	System.out.print("Movie year: ");
	int year = keyboard.nextInt();
	keyboard.nextLine(); // Consume newline
	System.out.println();
	System.out.println(LINE);
	ArrayList<Movie> temp = MovieUtilities.getByYear(movies, year);
	for (Movie m : temp) {
	    System.out.println(m);
	}
	System.out.println(LINE);

	// Get movies by rating
	System.out.print("Movie rating: ");
	double rating = keyboard.nextDouble();
	keyboard.nextLine(); // Consume newline
	System.out.println();
	temp = MovieUtilities.getByRating(movies, rating);
	for (Movie m : temp) {
	    System.out.println(m);
	}
	System.out.println(LINE);

	// Get movies by genre
	System.out.println("Movie genre: ");
	int genre = MovieUtilities.readGenre(keyboard);
	System.out.println();
	temp = MovieUtilities.getByGenre(movies, genre);
	for (Movie m : temp) {
	    System.out.println(m);
	}
	System.out.println(LINE);

	// Test movie comparisons again
	System.out.println("Comparisons");
	testMovieCompareTo();
    }

    /**
     * Tests the compareTo method in the Movie class.
     */
    public static void testMovieCompareTo() {
	Movie movie = new Movie("Les Misérables", 1998, "Bille August", 7.4, 2);
	Movie prevMovie = new Movie("Les Misérables", 1978, "Glenn Jordan", 7.3, 2);
	Movie nextMovie = new Movie("Les Misérables", 2019, "Ladj Ly", 7.6, 2);
	Movie firstMovie = new Movie("Ad Astra", 2019, "James Gray", 6.5, 0);
	Movie lastMovie = new Movie("Z", 1969, "Costa Gravas", 8.2, 2);

	System.out.println("Compare Les Misérables to itself (expects 0): " + movie.compareTo(movie));
	System.out.println("Compare Les Misérables to Ad Astra (expects > 0): " + movie.compareTo(firstMovie));
	System.out.println("Compare Les Misérables to Z (expects < 0): " + movie.compareTo(lastMovie));
	System.out.println(
		"Compare Les Misérables (1998) to Les Misérables (1978)  (expects > 0): " + movie.compareTo(prevMovie));
	System.out.println(
		"Compare Les Misérables (1998) to Les Misérables (2019) (expects < 0): " + movie.compareTo(nextMovie));
    }

}
