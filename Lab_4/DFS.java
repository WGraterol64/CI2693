import java.util.stack;
public class DFS{
    // Pila a utilizar para el DFS
    private Stack<Integer> elements;
    // Arreglo en el que se guardan los predecesores de cada nodo
    int[] parent;
    // Arreglo en el que se guarda el camino
    int [] path;
    // Arreglo de etiquetas de los vertices para el recorrido
    int [] colors;
    // Pos del vertice considerado en cada momento del recorrido
    int pos;

    // Constructor
    public DFS(int n){
      this.elements = new Stack<Integer> ();
      this.parent = new int[n];
      this.path = new int [n];
      this. colors = new int [n];
      this.pos= 0;
    }

    // Depth-First Search
    static private void dfs(int s, Graph graph){
      // Dejar vacio el arreglo del camino
      Arrays.fill(this.path,-1);
      // Dejar vacio el arreglo del predecesores
      Arrays.fill(this.path,-1);
      // Colorear a todos los nodos de blanco
      Arrays.fill(this.colors,0);
      // Inicializamos el arreglo de la distancia a la raiz
      Arrays.fill(this.distance,-1);
      // Inicializamos el contador de la pos vacio
      this.pos= 0;
      // Introducimos a la cola el primer vertice a considerar
      this.elements.push(s);
      // Agregamos el primer vertice al recorrido
      this.path[0] = s;
      // Suponemos que s es su propio padre
      this.parent[s] = s;

      // Imprimimos el mensaje de inicio
      System.out.print("Recorrido desde"+s+":\n");
      // Inicilizacion del ciclo
      while(!elements.empty()){
        // Revisamos el elemento en el tope de la pila
        int x = this.elements.pop();
        // Lo pintamos de negro
        this.color[x] = 1;
        // Consideramos todas las adjacencias de x
        for( int v : graph.list[x].adj){
          // Si el vertice no ha sido visitado
          if (this.color[v] == 0){
            // Imprimimos la arista
            System.out.print(x+"-"+v);
            // Agregar el vertice al camino y aumentar la pos
            this.path[this.pos++] = v;
            // Marcamos el predecesor de v
            this.parent[v] = x;
            // Insertar el vertice a la pila
            this.elements.push(v);
          }
          else{
            System.out.print(x+"-"+v+" Ya visitado\n");
          }
        }
      }
    }

   // Funcion que se utilizara en el cliente y retornara
   // si el camino es de hamilton o no
    public boolean findPath(int s, Graph graph){
      // Busqueda de camino
      dfs(s, graph);
      // Verificamos si es hamiltoniano
      int count = 0;
      int x = this.path[graph.size-1];
      while(x != this.parent[x]){
        x = this.parent[x];
        count ++;
      }
      
      return count == graph.size-1;
  }
}