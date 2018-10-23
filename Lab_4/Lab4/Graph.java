import java.lang.Exception;

// Clase de grafo, se implementa como un arreglo que contiene una instancia de
// la clase Vertex en cada casilla.
public class Graph{

  public int size;
  public Vertex [] list;

  // Crea el arreglo donde se guardaran los n vertices
  public Graph(int n){
    this.size = n;
    this.list = new Vertex[n];
    for(int i = 0; i<n; i++){
      list[i] = new Vertex();
    }
  }

  // Agrega una adyacencia a un vertice
  public void addAdj(int x, int y) throws IllegalArgumentException
  { 
    try{
      // Intenta anadir una nueva arista
      this.list[x].addVertex(y);
      this.list[y].addVertex(x);
    }catch(UnsupportedOperationException e){
      // La arista ya existe
      throw new UnsupportedOperationException("Lista no valida");
    }
    
  }
  
}
