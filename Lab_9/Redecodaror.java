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
public class Redecorador{

     /**
     * Funcion para cargar un grafo desde un archivo en el formato elegido
     * @param arch archivo de texto en el cual esta el grafo que se quiere cargar
     * @return grafo cargado en el programa
     * @throws IllegalArgumentException si el archivo no se encuentra o
     * no esta en formato valido
     */
    public static Graph loadGraph(String arch)
    throws IllegalArgumentException{

        // Inicializacion del grafo
        Graph graph;

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(arch.trim()));
        }catch(FileNotFoundException e){
            throw new IllegalArgumentException("Archivo no encontrado");
        }

        int n;
        String line;
        try{
            // n es el numero de vertices del grafo
            n = Integer.parseInt(reader.readLine().trim());
        }catch(IOException | NumberFormatException e){
            throw new IllegalArgumentException("Entrada no valida");
        }

        graph = new Graph(n); // Construccion del grafo

        // Ciclo para agregar todos los nodos al grafo
        int x, y;
        for(int i=0; i<n; i++){

            try{
              line = reader.readLine().trim();
            }catch(IOException e){
              throw new IllegalArgumentException("Archivo no valido");
            }

            String[] xy = line.split(" ");
            try{
              // En cada nodo se guardan sus coordenadas (x, y)
            	x = Integer.parseInt(xy[0]);
            	y = Integer.parseInt(xy[1]);
            }catch(NumberFormatException e){
            	throw new IllegalArgumentException("Entrada no valida");
            }
            // agregar el nodo
            graph.addVertex(x,y);
         }

        int m;
        try{
            // m es el numero de lados en el grafo
            m = Integer.parseInt(reader.readLine().trim());
        }catch(IOException | NumberFormatException e){
            throw new IllegalArgumentException("Archivo no valido");
        }

        // Ciclo para agregar todos los lados
        for(int i=0; i<m; i++){
            try{
              line = reader.readLine().trim();
            }catch(IOException e){
              throw new IllegalArgumentException("Archivo no valido");
            }
            String[] ends = line.split(" ");
            int u, v;
            try{
                // u,v son los nodos que estan en el lado
            	  u = Integer.parseInt(ends[0]);
            	  v = Integer.parseInt(ends[1]);
            }catch(NumberFormatException e){
            	  throw new IllegalArgumentException("Entrada no valida");
            }

            // Si el lado no esta en el grafo, lanzar excepcion
            if(v>=n || u>=n)
              throw new IllegalArgumentException("Lado no valido");

            // Agregar un lado que conecte a u y a v
            graph.addEdge(u, v);
        }

        return graph;
    }
    public static void printPathsAStar(Graph graph, int s, int n){
        long time = System.currentTimeMillis();
        for(int i=0; i<n; i++){
          printPathsA(graph,s, i)
        }
        time = time-System.currentTimeMillis();
        time = time/1000;
        System.out.println("El tiempo de ejecucion total de A* fue:" + Long.toString(time));
      }
    public static void printPathsA(Graph graph, int s, int f){
      try{
          int [] results = graph.AStar(s,f);
      }catch(IllegalArgumentException e){
          throw e;
      }
      // Definimos el numero de cifras de significativas en el resultado
      DecimalFormat dformat = new DecimalFormat("0.00");
      // Pila que nos ayudara a recuperar los caminos
      Stack<Integer> stack = new Stack<Integer>();
      // String en el que guardaremos e imprimiremos el camino
      String t;

    }
    /**
    * Recupera los recorridos generados por Dijkstra y los imprime junto a sus costos
    * @param graph es el grafo al que se le haran los caminos de costo minimo
    * @param s es el vertice de inicio del recorrido
    */
    public static void printPaths(Graph graph, int s){
        long time = System.currentTimeMillis();
        try{
          // Calculamos los caminos
          graph.dijkstra(s);
        }catch(IllegalArgumentException e){
          throw e;
        }
        // Definimos el numero de cifras de significativas en el resultado
        DecimalFormat dformat = new DecimalFormat("0.00");
        // Pila que nos ayudara a recuperar los caminos
        Stack<Integer> stack = new Stack<Integer>();
        // String en el que guardaremos e imprimiremos el camino
        String t;

        for(int i=0; i<graph.n; i++){

            t = "Nodo " + String.valueOf(i) + " : " ;

            // Si el nodo es alcanzable
            if(graph.predecessor[i] != -1){

                t += String.valueOf(s);
                int p = i;
                while(p!=s){
                  // Agregamos el nodo a la pila y seguimos con el siguiente pred
                	stack.push(p);
                	p = graph.predecessor[p];
                }

                int numOfEdges = stack.size();

                while(!stack.empty())
                    // Sacamos uno a uno los elementos de la pila y con ellos hacemos el camino
                    t += "->" + String.valueOf(stack.pop());

                    t +=  "		" + String.valueOf(numOfEdges) + " lados (Costo " + String.valueOf(dformat.format(graph.cost[i]))+ ")";
            }

            // Si no es alcanzable
            else
            	t += "Nodo no alcanzable desde la fuente.	(Costo infinito)";

            System.out.println(t);
        }
    }

    /**
    * Rutina principal de la clase, recibira dos Strings y cargara el grafo
    * en el archivo recibido en el primero y luego imprimira los caminos de costo
    * minimo partiendo del vertice en el segundo argumento
    * @param args arreglo de Strings que tiene en la primera coordenada el nombre
    * del archivo y en la segunda el nodo inicial
    * @throws IOException si hay algun error durante la excepcion
    * @throws IllegalArgumentException si hay algun dato de entrada invalido
    */
    public static void main(String[] args)
  	throws IOException, IllegalArgumentException{
  		if(args.length < 2){
  			System.err.println("Uso: java Mesero <nombreArchivo> <Origen>");
  			return;
  		}

  		Graph problemGraph;
        try{
           // Cargar el grafo
           problemGraph = loadGraph(args[0]);
        }catch(IllegalArgumentException e){
          throw e;
        }

        int n = problemGraph.n;
        int s;
        try{
        	s = Integer.parseInt(args[1]);
        }catch(NumberFormatException e){
           	throw new IllegalArgumentException("Entrada no valida");
        }

        try{
          // Imprimir caminos
          printPathsDijkstra(problemGraph,s, n);
        }catch (IllegalArgumentException e) {
          throw e;
        }
  	 }
  }
