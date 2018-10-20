// Clase de grafo, se implementa como un arreglo que contiene una instancia de
// la clase Vertex en cada casilla.
public class Graph{

  public int size;
  public Vertex [] graph;

  // Crea el arreglo donde se guardaran los n vertices
  public void Graph(int n){
    this.size = n;
    this.graph = new Vertex [n];
  }

  // Agrega una adyacencia a un vertice
  public void addAdj(int x, int y) throws IllegalArgumentException
  { 
    try{
      // Intenta anadir una nueva arista
      this.graph[x].adj.addVertex(y);
      this.graph[y].adj.addVertex(x);
    }catch(UnsupportedOperationException e){
      // La arista ya existe
      throw new UnsupportedOperationException("Lista no valida");
    }
    
  }
  
}
