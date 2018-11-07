import java.util.*;
class Vertex{

	public int id;
	public ArrayList<Integer> adj; // Lista de adyacentes

	/**
	* Constructor de la clase
	* @param id Entero identificador del vertice
	**/
	public Vertex(int id){

		this.id = id;
		this.adj = new ArrayList<Integer>();
	}

	/**
	* Metodo para agregar el vertice v a los adyacentes al vertice actual
	* @param v Identificador del vertice adyacente
	**/
	public void addAdj(int v){
		this.adj.add(v);
	}

}