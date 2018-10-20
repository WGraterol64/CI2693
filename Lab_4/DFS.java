import java.util.stack;
import Graph;
import Vertex;
public class DFS{
  // Pila a utilizar para el DFS
  private Stack<Integer> elements;
  // Arreglo en el que se guarda el camino
  int [] path;
  // Arreglo de etiquetas de los vertices para el recorrido
  int [] colors;
  // Arreglo donde se guardan las disttancias
  int [] distance;
  // Pos del vertice considerado en cada momento del recorrido
  int pos;

  // Constructor
  static void DFS(int n){
    this.elements = new Stack<Integer> ();
    this.path = new int [n];
    this. colors = new int [n];
    this.pos= 0;
  }

  // Depth-First Search
  static private void dfs(int s, Graph graph){
    // Dejar vacio el arreglo del camino
    Arrays.fill(this.path,-1);
    // Colorear a todos los nodos de blanco
    Arrays.fill(this.colors,0);
    // Inicializamos el arreglo de la distancia a la raiz
    Arrays.fill(this.distance,-1);
    // Inicializamos el contador de la pos vacio
    this.pos= 0;
    // Introducimos a la cola el primer vertice a considerar
    this.elements.push(s);
    // La distancia de la raiz a si misma es 0
    this.distance[s] = 0
    // Agregamos el primer vertice al recorrido
    this.path[0] = s
    // Imprimimos el mensaje de inicio
    System.out.print("Recorrido desde"+s+":\n");
    // Inicilizacion del ciclo
    while(!elements.empty()){
      // Revisamos el elemento en el tope de la pila
      int x = this.elements.pop();
      // Lo pintamos de negro
      this.color[x] = 1;
      // Consideramos todas las adjacencias de x
      for(v : graph.graph[x].adj){
        // Si el vertice no ha sido visitado
        if (this.color[v] == 0){
          // Imprimimos la arista
          System.out.print(x+"-"+v);
          // Agregar el vertice al camino y aumentar la pos
          this.path[this.pos++] = v;
          // Aumentamos la distancia
          this.distance[v] = this.distance[x] + 1
          // Insertar el vertice a la pila
          this.elements.push(v)
        }
      }
    }
  }

 // Funcion que se utilizara en el cliente y retornara
 // si el camino es de hamilton o no
  public boolean findPath(int s, Graph graph){
    // Busqueda de camino
    dfs(s, graph);

    // Si el tamano del camino es menor que el numero de vertices
    if(pos != graph.size){
      // No es camino hamiltoneano
      return false;
    }
    for(int i = 0; i<this.pos-1; i++){
      if(this.distance[i] == this.distance[i+1]){
        return false;
      }
    }
    return true;
  }
}
