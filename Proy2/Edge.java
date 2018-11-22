import java.lang.StringBuilder;
import java.util.*;
/**
*
* Clase Edge, representa las aristas en un grafo no dirigido.
* Extiende la clase abstracta GraphEdges
*
**/
public class Edge extends GraphEdges{


	private String id; // Identificador de la arista
	private int cap; // Dato de la arista
	private double weight; // Peso de la arista
	private UNode nodeA; // Primer nodo de la arista
	private UNode  nodeB; // Segundo de la arista

	/**
	* Constructor de la clase
	* @param id Identificador de la arista a crear
	* @param cap Capacidad de la arista
	* @param weight Peso de la arista
	* @param nodeA Nodo inicial de la arista
	* @param nodeB Nodo final de la arista
	**/
	public Edge (String id, int cap, double weight, UNode nodeA, UNode nodeB){
		this.id = id;
		this.cap = cap;
		this.weight = weight;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
	}
	public Edge(Edge e, UNode nodeA, UNode nodeB){
		this.id = e.getId();
		this.cap = e.getCap();
		this.weight = e.getWeight();
		this.nodeA = nodeA;
		this.nodeB = nodeB;
	}

	/**
	* Metodo utilizado para obtener el peso de la arista
	**/
	public double getWeight(){
		return this.weight;
	}

	/**
	* Metodo utilizado para obtener el identificador de la arista
	**/
	public String getId(){
		return this.id;
	}

	/**
	* Metodo utilizado para obtener la capacidad de la arista
	**/
	public int getCap(){
		return this.cap;
	}

	/**
	* Metodo que devuelve el primer nodo de la arista
	* @return Uno de los nodos de la arista
	**/
	public UNode getFNode(){
		return this.nodeA;
	}

	/**
	* Metodo que devuelve el segundo nodo de la arista
	* @return Uno de los nodos de la arista
	**/
	public UNode getSNode(){
		return this.nodeB;
	}

	public void changeCap(int n){
		this.cap += n;
	}
}
