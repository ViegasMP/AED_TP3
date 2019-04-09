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

class Sort {    

    public ArrayList<Movie> movieList = new ArrayList<Movie>();

    public ArrayList<Movie> getInputArray() {
        return movieList;
    }

    public Sort(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    //Merge Sort para data de lancamento
    //metodo recursivo
    public void sortGivenArray_date(){
        movieList = mergeSort(movieList);
    }

    public ArrayList<Movie> mergeSort( ArrayList<Movie> list) {
        ArrayList<Movie> left = new ArrayList<Movie>();
        ArrayList<Movie> right = new ArrayList<Movie>();
        int center;
        
        if (list.size() == 1){
            return list;
        } else {
            center = list.size()/2;
            for(int i=0; i < center; i++) {
                left.add(list.get(i));
            }
            for(int i=center; i < list.size(); i++) {
                right.add(list.get(i));
            }

            left = mergeSort(left);
            right = mergeSort(right);

            merge(left, right, list);
        }
        return list;       
    }
        
    private void merge(ArrayList<Movie> left, ArrayList<Movie> right, ArrayList<Movie> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
    
        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( left.get(leftIndex).date < right.get(rightIndex).date) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }
    
        ArrayList<Movie> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }
    
        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

    
    //Shell Sort para popularidade
    public void sortGivenArray_popularity() { 
        int i, j, k;                  
        for(i = movieList.size()/2; i > 0; i /= 2) {
            for(j = i; j < movieList.size(); j++) {
                Movie key = movieList.get(j);
                for(k = j; k >= i; k -= i) {
                    if(key.rents > movieList.get(k-i).rents) {
                        movieList.set(k, movieList.get(k-i));
                    } else {
                        break; 
                    }
                }
                movieList.set(k, key);
            }
        }       
    }
    

    /*
    //Quick Sort para o nome do filme
    public void sortGivenArray_name(){
        movieList = quickSort(movieList);
    }

    public ArrayList<Movie> quickSort(ArrayList<Movie> list) {
        if (list.size() <= 1) 
            return list; // Already sorted 

        ArrayList<Movie> sorted; 
        ArrayList<Movie> smaller = new ArrayList<Movie>();
        ArrayList<Movie> greater = new ArrayList<Movie>();
        Movie pivot = list.get(0);
        for(int i=1; i < list.size(); i++) {
            Movie j = list.get(i);
            if(j.name.compareTo(pivot.name) < 0) {
                smaller.add(j);
            } else {
                greater.add(j);
            }
        }

        smaller=quickSort(smaller);  // capitalise 's'
        greater=quickSort(greater);  // sort both halfs recursively
        smaller.add(pivot);          // add initial pivot to the end of the (now sorted) smaller Vehicles
        smaller.addAll(greater);     // add the (now sorted) greater Vehicles to the smaller ones (now smaller is essentially your sorted list)
        sorted = smaller;            // assign it to sorted; one could just as well do: return smaller
        return sorted;
    }
    */
    public void printGivenArray(){
        for(int i=0; i < movieList.size(); i++){
            System.out.println(movieList.get(i).name);
        }
    }
}

public class TP3_TarefaB {
    public static void main (String[] arguments) {
        String input, comando;
        int pesquisa;
        StringTokenizer st;
        
        //quantidade de filmes
        String movieQt = readLn(200);
        //System.out.println(Integer.parseInt(movieQt));

        //lista de todos os filmes conforme inseridos
        ArrayList<Movie> dataBase = new ArrayList<Movie>();
        dataBase = createDataBase(Integer.parseInt(movieQt));

        
        //ordenacao conforme data
        Sort isD = new Sort(dataBase);
        isD.sortGivenArray_date();
        System.out.println("\n------------Old to New------------");
        //isD.printGivenArray();
        @SuppressWarnings("unchecked")
        ArrayList<Movie> oldToNew = (ArrayList<Movie>) isD.getInputArray().clone();
        for(int i=0; i < oldToNew.size(); i++){
            System.out.println(oldToNew.get(i).name);
        }
        
        
        //ordenacao conforme popularidade
        Sort isP = new Sort(dataBase);
        isP.sortGivenArray_popularity();
        System.out.println("\n------------Popularity------------");
        //isP.printGivenArray();
        @SuppressWarnings("unchecked")
        ArrayList<Movie> popular = (ArrayList<Movie>)isP.getInputArray().clone();
        for(int i=0; i < popular.size(); i++){
            System.out.println(popular.get(i).name);
        }
        
        /*
        //ordem alfabetica
        Sort isA = new Sort(dataBase);
        isA.sortGivenArray_name();
        System.out.println("\n------------Alphabetical------------");
        //isA.printGivenArray();
        @SuppressWarnings("unchecked")
        ArrayList<Movie> alphabetical = (ArrayList<Movie>)isA.getInputArray().clone();
        for(int i=0; i < alphabetical.size(); i++){
            System.out.println(alphabetical.get(i).name);
        }
        */

        do {  // enquanto houver mais linhas para ler...
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
                //System.out.println(alphabetical.get(pesquisa-1).name);
            }
            else if (comando.equals("TERMINA")){
                return;
            }
        } while (true);
        

    }

    //ler inputs de filme, criar Movie e adicionar ao arraylist de Movie
    public static ArrayList<Movie> createDataBase (int movieQt){ //utility function to read from stdin
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for(int i=0; i < movieQt; i++){
            String movieInfo = readLn(200);
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

    // leitura do input
    static String readLn (int maxLg){ //utility function to read from stdin
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