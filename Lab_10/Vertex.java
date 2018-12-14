import java.util.*;
public class Vertex{

	public ArrayList<Integer> adj; // Lista de adyacentes

	/**
	* Constructor de la clase
	**/
	public Vertex(){
		this.adj = new ArrayList<Integer>();
	}

	/**
	* Metodo que agrega el identificador de un vertice
	* a la lista de adyacentes del vertice actual
	* @param v Posicion en el grafo del vertice v
	**/
	public void addAdj(int v){
		this.adj.add(v);
	}

}
