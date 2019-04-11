import java.io.IOException;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.*;
import java.util.Scanner;
import java.util.StringTokenizer;

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

    public ArrayList<Movie> movieList = new ArrayList<Movie>();

    public ArrayList<Movie> getInputArray() {
        return movieList;
    }

    public InsertionSort(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }
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
        String input, comando;
        int pesquisa;
        StringTokenizer st;

        input = readLn(200);
        st= new StringTokenizer(input.trim());
        String movieQt = st.nextToken();
        
        ArrayList<Movie> dataBase = new ArrayList<Movie>();
        dataBase = createDataBase(Integer.parseInt(movieQt));

        InsertionSort isD = new InsertionSort(dataBase);
        isD.sortGivenArray_date();
        @SuppressWarnings("unchecked")
        ArrayList<Movie> oldToNew = (ArrayList<Movie>) isD.getInputArray().clone();

        InsertionSort isP = new InsertionSort(dataBase);
        isP.sortGivenArray_popularity();
        @SuppressWarnings("unchecked")
        ArrayList<Movie> popular = (ArrayList<Movie>)isP.getInputArray().clone();

        InsertionSort isA = new InsertionSort(dataBase);
        isA.sortGivenArray_name();
        @SuppressWarnings("unchecked")
        ArrayList<Movie> alphabetical = (ArrayList<Movie>)isA.getInputArray().clone();



        do {
            input = readLn(200);
            st= new StringTokenizer(input.trim());
            comando = st.nextToken();
            if (comando.equals("DATA")){
                pesquisa = Integer.parseInt(st.nextToken());
                System.out.println(oldToNew.get(pesquisa-1).name);
            }
            else if (comando.equals("POPULARIDADE")){
                pesquisa = Integer.parseInt(st.nextToken());
                System.out.println(popular.get(pesquisa-1).name);
            }
            else if (comando.equals("NOME")){
                pesquisa = Integer.parseInt(st.nextToken());
                System.out.println(alphabetical.get(pesquisa-1).name);
            }
            else if (comando.equals("TERMINA")){
                return;
            }
        } while (true);
        

    }

    public static ArrayList<Movie> createDataBase (int movieQt){ 
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for(int i=0; i < movieQt; i++){
            String movieInfo = readLn(200);
            String[] parts = movieInfo.split(" ");
            int movieDate = Integer.parseInt(parts[0]);
            int movieRent = Integer.parseInt(parts[1]);
            String movieName = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));
            Movie newMovie = new Movie(movieName, movieRent, movieDate);
            movieList.add(newMovie);
        }
        return movieList;
    }

    static String readLn (int maxLg){
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";
        try {
            while (lg < maxLg){
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e){
            return (null);
        }
        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }
} 