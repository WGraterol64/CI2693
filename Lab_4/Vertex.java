import java.util.ArrayList;
import java.lang.Exception;

public class Vertex{
	
	// Arreglo de adyacencias de un vertice
	public ArrayList<Integer> adj;

	// Constructor de la clase
	public Vertex(){

		this.adj = new ArrayList<Integer>();
	}

	// Funcion que agrega un vertice a la lista
	// de adyacencias de otro
	public void addVertex(int v) throws IllegalArgumentException{
		
		// Verificamos si el vertice ya esta en la lista de adjacencias
		for(int u : this.adj) 
			if(u == v)
				throw new UnsupportedOperationException("Aristas repetidas");

      	this.adj.add(v);
	}
}