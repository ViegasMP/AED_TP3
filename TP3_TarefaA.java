import java.io.IOException;
import java.util.StringTokenizer;
import java.text.NumberFormat;
import java.util.*;

class Movie{

}

public class TP3_TarefaA {
    public static void main (String[] arguments) {
        String input, comando;
        int posMovie;
	    StringTokenizer st;
        
        do {  // enquanto houver mais linhas para ler...
            input = readLn(200);
            st= new StringTokenizer(input.trim());
            comando = st.nextToken();
            if (comando.equals("DATA")) {
                posMovie = Integer.parseInt(st.nextToken());
            } else if (comando.equals("POPULARIDADE")) {
                posMovie = Integer.parseInt(st.nextToken());
            } else if (comando.equals("NOME")) {
                posMovie = Integer.parseInt(st.nextToken());
            } else {

            }
        } while (true);

    }
    // leitura do input
    static String readLn (int maxLg){ //utility function to read from stdin
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        //String line = "";
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