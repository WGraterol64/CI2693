import java.io.IOException;
import java.lang.RuntimeException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.text.DecimalFormat;

public class Mesero{
    

    public static Graph loadGraph(String arch)
    throws IllegalArgumentException{

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
            n = Integer.parseInt(reader.readLine().trim());
        }catch(IOException | NumberFormatException e){
            throw new IllegalArgumentException("Entrada no valida");
        }

        graph = new Graph(n);

        int x, y;
        for(int i=0; i<n; i++){
           
            try{
              line = reader.readLine().trim();
            }catch(IOException e){
              throw new IllegalArgumentException("Archivo no valido");
            }

            String[] xy = line.split(" ");
            try{
            	x = Integer.parseInt(xy[0]);
            	y = Integer.parseInt(xy[1]);
            }catch(NumberFormatException e){
            	throw new IllegalArgumentException("Entrada no valida");
            }
            graph.addVertex(x,y);
         }

        int m;
        try{
            m = Integer.parseInt(reader.readLine().trim());
        }catch(IOException | NumberFormatException e){
            throw new IllegalArgumentException("Archivo no valido");
        }

        for(int i=0; i<m; i++){
            try{
              line = reader.readLine().trim();
            }catch(IOException e){
              throw new IllegalArgumentException("Archivo no valido");
            }
            String[] ends = line.split(" ");
            int u, v;
            try{
            	u = Integer.parseInt(ends[0]);	
            	v = Integer.parseInt(ends[1]);
            }catch(NumberFormatException e){
            	throw new IllegalArgumentException("Entrada no valida");
            }

            if(v>=n || u>=n)
              throw new IllegalArgumentException("Lado no valido");

            graph.addEdge(u, v);
        }

        return graph;
    }

    public static void printPaths(Graph graph, int s){
        
        graph.dijkstra(s);
        DecimalFormat dformat = new DecimalFormat("0.00");
        Stack<Integer> stack = new Stack<Integer>();
        String t;

        for(int i=0; i<graph.n; i++){

            t = "Nodo " + String.valueOf(i) + " : " ;

            if(graph.predecessor[i] != -1){
                
                t += String.valueOf(s);
                int p = i;
                while(p!=s){
                	stack.push(p);
                	p = graph.predecessor[p];
                }

                int numOfEdges = stack.size();

                while(!stack.empty())
                    t += "->" + String.valueOf(stack.pop());
                               
                t +=  "		" + String.valueOf(numOfEdges) + " lados (Costo " + String.valueOf(dformat.format(graph.cost[i]))+ ")";
            }

            else
            	t += "Nodo no alcanzable desde la fuente.	(Costo infinito)";
            

            System.out.println(t);
        }
    }

    public static void main(String[] args)
  	throws IOException, IllegalArgumentException{
  		if(args.length < 2){
  			System.err.println("Uso: java Mesero <nombreArchivo> <Origen>");
  			return;
  		}

  		Graph problemGraph;
        try{
           problemGraph = loadGraph(args[0]);
        }catch(IllegalArgumentException e){
          throw e;
        }
        
        int s;
        try{
        	s = Integer.parseInt(args[1]);
        }catch(NumberFormatException e){
           	throw new IllegalArgumentException("Entrada no valida");
        }

        printPaths(problemGraph,s);
  	 }
  }