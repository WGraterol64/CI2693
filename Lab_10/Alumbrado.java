import java.io.IOException;
import java.lang.RuntimeException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.text.DecimalFormat;
/**
* Clase principal para el problema de camino mas corto en Pizza Planeta
*/
public class Alumbrado{

     /**
     * Funcion para cargar un grafo desde un archivo en el formato elegido
     * @param arch archivo de texto en el cual esta el grafo que se quiere cargar
     * @return grafo cargado en el programa
     * @throws IllegalArgumentException si el archivo no se encuentra o
     * no esta en formato valido
     */
    public static void reduceCost(String file)
    throws IllegalArgumentException, IOException{

        // Inicializacion del grafo
        Graph graph;

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(file.trim()));
        }catch(FileNotFoundException e){
            throw new IllegalArgumentException("Archivo no encontrado");
        }

        int n = -1, m = -1;
        String line = "";

        try{
          line = reader.readLine().trim();
        }catch(IOException e){
          throw new IllegalArgumentException("Archivo no valido");
        }
        String[] lcase = line.split(" ");
        m = Integer.parseInt(lcase[0]);
        n = Integer.parseInt(lcase[1]);

        while(m!=0 && n!=0){


        graph = new Graph(m,n);
        for(int k=0;k<n;k++){
          line = reader.readLine().trim();
          String[] trip = line.split(" ");
          graph.addEdge(Integer.parseInt(trip[0]), Integer.parseInt(trip[1]), Integer.parseInt(trip[2]));
        }

        int savings = graph.cost - graph.kruskal();
        System.out.print(savings);
        System.out.println(" UT ahorradas");

        try{
          line = reader.readLine().trim();
        }catch(IOException e){
          throw new IllegalArgumentException("Archivo no valido");
        }
        lcase = line.split(" ");
        m = Integer.parseInt(lcase[0]);
        n = Integer.parseInt(lcase[1]);
      }
    }

    public static void main(String[] args)
  	throws IOException, IllegalArgumentException{
  		if(args.length < 1){
  			System.err.println("Uso: java Alumbrado <instancia>");
  			return;
  		}

        try{
          reduceCost(args[0]);
        }catch(IllegalArgumentException e){
          throw e;
        }
  	 }
  }
