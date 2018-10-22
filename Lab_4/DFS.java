import java.util.*;
public class DFS{
    // Arreglo en el que se guarda el camino
    int [] path;
    // Arreglo donde se almacena el predecesor de cada nodo en el recorrido
    int [] parent;
    // Arreglo de etiquetas de los vertices para el recorrido
    int [] colors;
    // Pos del vertice considerado en cada momento del recorrido
    int pos;

    // Constructor
    public DFS(int n){
        this.parent = new int[n];
        this.path = new int [n];
        this.colors = new int [n];
        this.pos= 0;
    }

    
    // Depth-First Search Recursivo
    private void dfsRec(int u, Graph graph){

        // Marcamos el nodo como visitado
        this.colors[u] = 1;
        // Guardamos el nodo visitado
        this.path[pos++] = u;
        // Para todos los adyacentes
        for(int v : graph.list[u].adj){
            // Si no estan visitados, se imprime el camino y se visita
            if(colors[v]==0){
                System.out.print(u+"-"+v+"\n");
                parent[v] = u; // Marcando a u como padre de v
                dfsRec(v,graph);
            }else
                System.out.print(u+"-"+v+" Ya visitado\n"); // Se imprime que ya fue visitado
        }
    }

    // Depth-First Search 
    private void dfs(int s, Graph graph){
        // Dejar vacio el arreglo del camino
        Arrays.fill(this.path,-1);
        // Colorear a todos los nodos de blanco
        Arrays.fill(this.colors,0);
        // Vaciamos el arreglo de padres
        Arrays.fill(this.parent,-1);
        // Inicializamos el contador de la pos vacio
        this.pos = 0;  
        // Establecemos s como su propio padre
        parent[s] = s;
        // Imprimimos un mensaje que anuncia que buscaremos un camino desde s
        System.out.print("Recorrido desde "+s+": \n");
        // Llamamos a dfs recursivo
        dfsRec(s,graph);
    }

   // Funcion que se utilizara en el cliente y retornara
   // si el camino es de hamilton o no
    public boolean findPath(int s, Graph graph){
        
        // Llamamos a dfs 
        dfs(s, graph);
        // Si no recorremos todos los nodos, el camino no es hamiltoniano
        if(pos != graph.size) 
            return false;
        // Si dos nodos consecutivos en el arreglo de camino no son padre-hijo, 
        // el camino no es hamiltoniano
        for(int i = 1; i<graph.size; i++)
            if(parent[path[i]] != path[i-1])
                return false;   
        
        return true;
    } 
}