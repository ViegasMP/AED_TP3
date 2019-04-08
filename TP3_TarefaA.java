import java.io.IOException;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.*;
import java.util.Scanner;
import java.util.StringTokenizer;

//descobrir pq nao consigo ler direto do ficheiro ->
//Exception in thread "main" java.util.NoSuchElementException: No line found
//        at java.base/java.util.Scanner.nextLine(Scanner.java:1651)

//null
//Exception in thread "main" java.lang.NullPointerException
//at TP3_TarefaA.createDataBase(TP3_TarefaA.java:134)
//at TP3_TarefaA.main(TP3_TarefaA.java:101)

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
        InsertionSort isD = new InsertionSort(dataBase);
        isD.sortGivenArray_date();
        System.out.println("\n------------Old to New------------");
        @SuppressWarnings("unchecked")
        ArrayList<Movie> oldToNew = (ArrayList<Movie>) isD.getInputArray().clone();
        for(int i=0; i < oldToNew.size(); i++){
            System.out.println(oldToNew.get(i).name);
        }

        //ordenacao conforme popularidade
        InsertionSort isP = new InsertionSort(dataBase);
        isP.sortGivenArray_popularity();
        System.out.println("\n------------Popularity------------");
        @SuppressWarnings("unchecked")
        ArrayList<Movie> popular = (ArrayList<Movie>)isP.getInputArray().clone();
        for(int i=0; i < popular.size(); i++){
            System.out.println(popular.get(i).name);
        }

        //ordem alfabetica
        InsertionSort isA = new InsertionSort(dataBase);
        isA.sortGivenArray_name();
        System.out.println("\n------------Alphabetical------------");
        @SuppressWarnings("unchecked")
        ArrayList<Movie> alphabetical = (ArrayList<Movie>)isA.getInputArray().clone();
        for(int i=0; i < alphabetical.size(); i++){
            System.out.println(alphabetical.get(i).name);
        }


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
                System.out.println(alphabetical.get(pesquisa-1).name);
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