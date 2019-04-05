import java.io.IOException;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.*;
import java.util.Scanner;

class Movie{
    public String name;
    public int rents;
    public int date;

    Movie(String name, int rents, int date){
        this.name = name;
        this.rents = rents;
        this.date = date;
    }

}

public class TP3_TarefaA {
    public static void main (String[] arguments) {
        Scanner sc = new Scanner(System.in);
        String movieQt = sc.nextLine();
        ArrayList<Movie> dataBase = new ArrayList<Movie>();
        ArrayList<Movie> oldToNew = new ArrayList<Movie>();
        ArrayList<Movie> popular = new ArrayList<Movie>();
        ArrayList<Movie> alphabetical = new ArrayList<Movie>();

        dataBase = createDataBase(Integer.parseInt(movieQt));

    }

    public static ArrayList<Movie>  createDataBase (int movieQt){ //utility function to read from stdin
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Scanner sc = new Scanner(System.in);

        for(int i=0; i < movieQt; i++){
            String movieInfo = sc.nextLine();
            //System.out.println(movieInfo);
            String[] parts = movieInfo.split(" ");
            int movieDate = Integer.parseInt(parts[0]);
            //System.out.println(movieDate);
            int movieRent = Integer.parseInt(parts[1]);
            //System.out.println(movieRent);
            String movieName = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));
            //System.out.println(movieName);
            Movie newMovie = new Movie(movieName, movieRent, movieDate);
            movieList.add(newMovie);
        }
        return movieList;
    }
} 