import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

public class Mesero0{
  static Graph0 g;

    public static void loadGraph(String arch)
    throws IllegalArgumentException{
      BufferedReader read;
      try{
        read = new BufferedReader(new FileReader(arch));
      }catch(FileNotFoundException e){
        throw new IllegalArgumentException("Archivo no encontrado");
      }
      int n;
      String line;
      try{
        n = Integer.parseInt(read.readLine().trim());
      }catch(IOException e){
        throw new IllegalArgumentException("Archivo no valido");
      }
      g = new Graph0(n);
      int[][] coord = new int[2][n];
      for(int i=0; i<n; i++){
        try{
          line = read.readLine().trim();
        }catch(IOException e){
          throw new IllegalArgumentException("Archivo no valido");
        }
          String[] xy = line.split(" ");
          coord[0][i] = Integer.parseInt(xy[0]);
          coord[1][i] = Integer.parseInt(xy[1]);
      }
      int m;
      try{
        m = Integer.parseInt(read.readLine().trim());
      }catch(IOException e){
        throw new IllegalArgumentException("Archivo no valido");
      }
      for(int i=0; i<m; i++){
        try{
          line = read.readLine().trim();
        }catch(IOException e){
          throw new IllegalArgumentException("Archivo no valido");
        }
        String[] ends = line.split(" ");
        int u = Integer.parseInt(ends[0]);
        int v = Integer.parseInt(ends[1]);
        if(v>n || u>n){
          throw new IllegalArgumentException("Lado no valido");
        }
        double w = (coord[0][v] - coord[0][u])*(coord[0][v] - coord[0][u]);
        w = w + (coord[1][v] - coord[1][u])*(coord[1][v] - coord[1][u]);
        w = Math.sqrt(w);
        g.addVertex(u, v, w);
      }
    }
    public static void printPaths(int s){
        Stack<Integer> c = new Stack<Integer>();
        String t;
        int e;
        g.dijkstra(s);
        for(int i=0; i<g.n; i++){
            if(g.predecessor[i] != -1){
                e = 0;
                t = "Nodo " + Integer.toString(i) + " : " + Integer.toString(s);
                int p = i;
                while(p != s){
                    c.push(p);
                    p = g.predecessor[p];
                }
                while(!c.empty()){
                    t = t + "->" + Integer.toString(c.pop());
                    e = e+1;
                }
                t = t + "  " + Integer.toString(e) + " lados (costo " + Double.toString(g.cost[i]) + ")";
                System.out.println(t);
            }
        }
    }
    public static void main(String[] args)
    throws IOException, IllegalArgumentException{
        if(args.length < 1){
          System.err.println("Uso: java Cliente <nombreArchivo>");
          return;
        }

        try{
          loadGraph(args[0]);
          int s = Integer.parseInt(args[1]);
          printPaths(s);
        }catch(IllegalArgumentException e){
          throw e;
        }
     }

}
