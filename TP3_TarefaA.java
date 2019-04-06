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

class InsertionSort {    

    private static ArrayList<Movie> movieList = new ArrayList<Movie>();

    public static ArrayList<Movie> getInputArray() {
        return movieList;
    }

    public InsertionSort(ArrayList<Movie> movieList) {
        InsertionSort.movieList = movieList;
    }

    //algoritmo de ordenacao: INSERCAO
    //para data de lancamento
    public void sortGivenArray_date() {                   
        for(int i=1;i<movieList.size();i++) {
            Movie key = movieList.get(i);
            for(int j=i-1; j >=0 ; j--) {
                if(key.date < movieList.get(j).date) {
                    movieList.set(j+1,movieList.get(j));
                    if(j==0) movieList.set(0, key);
                } else {
                    movieList.set(j+1, key);
                    break; 
                }
            }
        }       
    }

    //para popularidade
    public void sortGivenArray_popularity() {                   
        for(int i=1; i < movieList.size(); i++) {
            Movie key = movieList.get(i);
            for(int j=i-1; j >= 0; j--) {
                if(key.rents > movieList.get(j).rents) {
                    movieList.set(j+1, movieList.get(j));
                    if(j==0) movieList.set(0, key);
                } else {
                    movieList.set(j+1, key);
                    break; 
                }
            }
        }       
    }

    //para o nome do filme
    public void sortGivenArray_name() {                   
        for(int i=1; i < movieList.size(); i++) {
            Movie key = movieList.get(i);
            for(int j=i-1; j>=0; j--) {
                if(key.name.compareTo(movieList.get(j).name) < 0) {
                    movieList.set(j+1,movieList.get(j));
                    if(j==0) movieList.set(0, key);
                } else {
                    movieList.set(j+1, key);
                    break; 
                }
            }
        }       
    }

    public void printGivenArray(){
        for(int i=0; i < movieList.size(); i++){
            System.out.println(movieList.get(i).name);
        }
    }
}

public class TP3_TarefaA {
    public static void main (String[] arguments) {
        //quantidade de filmes
        Scanner sc = new Scanner(System.in);
        String movieQt = sc.nextLine();

        //lista de todos os filmes conforme inseridos
        ArrayList<Movie> dataBase = new ArrayList<Movie>();
        dataBase = createDataBase(Integer.parseInt(movieQt));

        //ordenacao conforme data
        InsertionSort oldToNew = new InsertionSort(dataBase);
        oldToNew.sortGivenArray_date();
        System.out.println("\n------------Old to New------------");
        oldToNew.printGivenArray();

        //ordenacao conforme popularidade
        InsertionSort popular = new InsertionSort(dataBase);
        popular.sortGivenArray_popularity();
        System.out.println("\n------------Popularity------------");
        popular.printGivenArray();

        //ordem alfabetica
        InsertionSort alphabetical = new InsertionSort(dataBase);
        alphabetical.sortGivenArray_name();
        System.out.println("\n------------Alphabetical------------");
        alphabetical.printGivenArray();
        

    }

    //ler inputs de filme, criar Movie e adicionar ao arraylist de Movie
    public static ArrayList<Movie> createDataBase (int movieQt){ //utility function to read from stdin
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