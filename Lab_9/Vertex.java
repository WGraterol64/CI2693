import java.util.*;
public class Vertex{

	public ArrayList<Integer> adj; // Lista de adyacentes
	public int x; // Coordenada x del vertice
	public int y; // Coordenada y del vertice

	/**
	* Constructor de la clase
	* @param x Coordenada x del vertice
	* @param y Coordenada y del vertice
	**/
	public Vertex(int x, int y){
		this.adj = new ArrayList<Integer>();
		this.x = x;
		this.y = y;
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
