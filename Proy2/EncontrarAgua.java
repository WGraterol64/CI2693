import java.io.IOException;
import java.lang.RuntimeException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class EncontrarAgua{
    static UndirectedGraph campus;

    public void caseStudy(){

    }

    public static void main(String[] args)
    throws IOException, IllegalArgumentException{
        if(args.length < 4){
            System.err.println("Uso: java  EncontrarAgua <grafo> <casos> <edif> <personas>");
        		return;
      	}
        Boolean load = campus.loadGraph(args[0]);
        BufferedReader read = new BufferedReader(new FileReader(args[1]));
        while(read != null){

        }
    }
}
