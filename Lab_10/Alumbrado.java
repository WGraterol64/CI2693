//**
//** Jesus De Aguiar 15-10360
//** Wilfredo Graterol 15-10639
//** Lab_10 CI2693 Sep-Dic 2018 
//**
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.RuntimeException;
/**
* Clase principal para el problema de alumbrado publico
*/
public class Alumbrado{

    /**
    * Funcion para cargar un grafo desde un archivo en el formato elegido
    * @param arch archivo de texto en el cual esta el grafo que se quiere cargar
    * @return grafo cargado en el programa
    * @throws IllegalArgumentException si el archivo no se encuentra o
    * no esta en formato valido
    * @throws IOException si ocurre algun problema con la entrada
    */
    public static void loadAndSolve(String file)
    throws IllegalArgumentException, IOException{

        // Inicializacion del grafo
        Graph graph;
        // Lector del archivo
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(file.trim()));
        }catch(FileNotFoundException e){
            throw new IllegalArgumentException("Archivo no encontrado");
        }
        // Variables utiles para cargar el grafo
        int n = 0, m = 0;
        String line = "";
        // Leemos la primera linea
        try{
            line = reader.readLine().trim();
        }catch(IOException e){
            throw new IllegalArgumentException("Archivo no valido");
        }
        // Extraemos n y m
        String[] lcase = line.split(" ");
        m = Integer.parseInt(lcase[0]);
        n = Integer.parseInt(lcase[1]);
        // Mientras no consigamos la condicion de parada de la entrada
        while(m!=0 || n!=0){
            // Creamos un grafo
            graph = new Graph(m,n);
            for(int k=0;k<n;k++){
                // Leemos todas las aristas
                line = reader.readLine().trim();
                String[] trip = line.split(" ");
                graph.addEdge(Integer.parseInt(trip[0]), Integer.parseInt(trip[1]), Integer.parseInt(trip[2]));
            }
            // Resolvemos e imprimimos
            System.out.print(graph.kruskal());
            System.out.println(" UT ahorradas");
            // Leemos la nueva linea del siguiente caso de prueba
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

    /**
    * Metodo principal de la clase
    * @param args Argumentos de la ejecucion
    **/
    public static void main(String[] args)
  	throws IOException, IllegalArgumentException{
  		if(args.length < 1){
  			  System.err.println("Uso: java Alumbrado <instancia>");
  			  return;
  		}

        try{
            loadAndSolve(args[0]);
        }catch(IllegalArgumentException e){
            throw e;
        }
    }
}
