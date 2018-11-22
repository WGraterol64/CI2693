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

	// Parametros utiles para el algoritmo BellmanFord
	public double distanceToSource; // Distancia desde el nodo a la fuente en el grafo
	public Edge cameFrom; // Edge incidente a el nodo en el camino minimo desde la fuente hasta el

	/**
	* Constructor de la clase
	* @param id Identificador del nodo
	* @param cap Capacidad del nodo
	* @param weight Peso del nodo
	**/
	public UNode(String id, int cap, Integer weight){
		this.id = id;
		this.cap = cap;
		this.weight = weight;
		this.adjNodes = new HashSet<UNode>();
		this.incEdges = new ArrayList<Edge>();
		this.degree = 0;
	}

	/**
	* Constructor de la clase
	* Util para copiar un nodo
	* @param v nodo a copiar
	**/
	public UNode(UNode v){
		this.id = v.getId();
		this.weight = v.getWeight();
		this.cap = v.getCap();
		this.adjNodes = new HashSet<UNode>();
		this.incEdges = new ArrayList<Edge>();
		this.degree = 0;
	}

	/**
	* Metodo utilizado para obtener el peso del nodo
	**/
	public int getWeight(){
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

	public int getCap(){
		return this.cap;
	}

	/**
	* Metodo utilizado para modificar el peso de un nodo
	* @param n Valor a agregar al peso
	**/
	public void changeW(int n){
		this.weight += n;
	}

}
