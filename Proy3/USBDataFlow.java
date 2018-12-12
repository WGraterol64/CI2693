import java.io.IOException;
import java.lang.RuntimeException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
* Clase principal del proyecto en su fase 2, se encarga de procesar la hoja de calculo
*
*/
public class USBDataFlow{
   
    public static int m,n; // Dimensiones de la hoja de calculo
    public static DirectedGraph graph; // Grafo de operaciones de la hoja de calculo
    public static DNode [][] ssheet; // Hoja de calculo
    public static String[] letters = {"A","B","C","D","E","F","G","H",
                                    "I","J","K","L","M","N","O","P",
                                    "Q","R","S","T","U","V","X","W",
                                    "Y","Z"}; // Arreglo para la asignacion de id's

    /**
    * Metodo que se encarga de crear los nodos del grafo y la hoja de calculo
    * basados en el archivo. Cada nodo tendra por id su casilla, por dato su expresion
    * y por peso el resultado de su operacion.
    *
    * @param file es el archivo donde esta la hoja de calculo
    * @throws IllegalArgumentException si el archivo de entrada no existe o no es valido
    */
    public static void storeSheet(String file)
    throws IllegalArgumentException{
    BufferedReader reader;
        try{
           reader = new BufferedReader(new FileReader(file.trim()));
        }catch(FileNotFoundException e){
            throw new IllegalArgumentException("Archivo no encontrado");
        }
        String line = reader.readLine().trim();
        String [] dim = line.split(" ");
        n = Integer.parseInt(dim[0]);
        m = Integer.parseInt(dim[1]);
        ssheet = new DNode[n][m];
        for(int i=0;i<n;i++){
            line = reader.readLine().trim().toUpperCase();
            String[] row = line.split(" ");
            if(row.length!=m)
                throw new IllegalArgumentException("El formato del archivo es invalido"); // Posiblemente tambien verificar columnas (?)

            for(int j=0; j<m; j++){
                DNode node = new DNode(asignId(i,j), row[j], 0);
                boolean is = graph.addNode(node);
                ssheet[i][j] = node;
            }
        }
    }

    /**
    * Metodo que se encarga de agregar cada uno de los arcos al grafo para generar el
    * grafo de precedencias y realiza las operaciones mediante durante un recorrido dfs.
    *
    * @throws IllegalArgumentException si hay algun ciclo en el grafo
    */
    public static void calcSheet()
        throws IllegalArgumentException{
      
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                String[] exp = ssheet[i][j].getData().split("[\\*\\+\\-,\\(\\)]|MAX|SUM|MIN|(?<![a-zA-Z])\\d+");
                for(String s : exp){
                    if(graph.isNode(s)){
                        String t = asignId(i, j);
                        graph.addArc(s + "-" + t, "", 0, s, t);
                    }
                }
            }
        }
        
        graph.dfs_calc(ssheet);
    }

    /**
    * Metodo que se encarga de imprimir el resultado de la hoja de hojadecalculo
    *
    */
    public static void printAns(){
        for(int i=0;i<n;i++){
            for(int j=0; j<m; j++){
                System.out.print(ssheet[i][j].getWeight());
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    /**
    * Funcion que se encarga de asignar a cada nodo, su id dependiendo de en que
    * casilla de la hoja de calculo este.
    *
    * @param i fila en la que esta el nodo (empezando desde 0)
    * @param j fila en la que esta el nodo (empezando desde 0)
    * @return un id unico para el nodo en el formato de ojas de calculo
    */
    public static String asignId(int i, int j){
        String id = "";
        int col = j;
        if(col == 0)
            id += "A";
        Stack<String> s = new Stack<String>();
        while (col!=0){
            s.push(letters[col%26]);
            col /= 26;
        }
        
        while(!s.isEmpty())
            id += s.pop();
        
        id += String.valueOf(i+1);
        return id;
    }

    /**
    * Metodo principal de la clase.
    *
    * @param args datos dados en la llamada al programa (no se hace nada con ellos)
    *
    * @throws IOException si hubieron errores en la ejecucion y sale del programa
    */
  	public static void main(String[] args)
  	throws IOException{
        if(args.length < 1){
  			System.err.println("Uso: java USBDataFlow <hojadecalculo>");
  			return;
  		}
        graph = new DirectedGraph();
        try{
            storeSheet(args[0]);
            calcSheet();
            printAns();
        }catch(IllegalArgumentException | IOException e){
            System.out.println(e);
            System.exit(1);
        }
}
}
