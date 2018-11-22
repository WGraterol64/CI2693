import java.util.*;
/**
* Clase de Nodos para un grafo no dirigido
* Extiende la clase abstacta Node
**/
public class UNode extends Node{


	private String id; // Identificador del nodo
	private int cap; // Capacidad del nodo
	private int weight; // Peso del nodo
	public int degree; // Grado del nodo
	public HashSet<UNode> adjNodes; // Lista de los nodos adyacentes
	public ArrayList<Edge> incEdges; // Lista de los lados incidentes

	public double distanceToSource;
	public Edge cameFrom;

	/**
	* Constructor de la clase
	* @param id Identificador del nodo
	* @param cap Capacidad del nodo
	* @param weight Peso del nodo
	**/
	public UNode(String id, boolean cap, Integer weight){
		this.id = id;
		this.cap = cap;
		this.weight = weight;
		this.adjNodes = new HashSet<UNode>();
		this.incEdges = new ArrayList<Edge>();
		this.degree = 0;
	}

	/**
	* Metodo utilizado para obtener el peso del nodo
	**/
	public Integer getWeight(){
		return this.weight;
	}

	/**
	* Metodo utilizado para obtener el identificador del nodo
	**/
	public String getId(){
		return this.id;
	}

	/**
	* Metodo utilizado para obtener la capacidad del nodo
	**/
	public boolean getCap(){
		return this.cap;
	}

	public void changeW(int n){
		this.weight += n;
	}

}
