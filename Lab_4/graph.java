// Clase de grafo, se implementa como un arreglo que contiene una instancia de
// la clase Vertex en cada casilla.
public class graph{

  public int size;
  public Vertex [] graph;

  // Crea el arreglo donde se guardaran los n vertices
  public void grafo(int n){
    this.size = n;
    this.graph = new Vertex [n];
  }

  // Agrega una adyacencia a un vertice
  public void addAdj(int x, int y){
    this.graph[x] = this.graph[x].addAdj(y);
  }
  
}
