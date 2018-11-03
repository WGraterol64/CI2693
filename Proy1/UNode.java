import java.util.*;
/**
* Clase de Nodos para un grafo no dirigido
* Extiende la clase abstacta Node
**/
public class UNode<V,E> extends Node{


	private String id; // Identificador del nodo
	private V data; // Dato del nodo
	private double weight; // Peso del nodo
	public int degree; // Grado del nodo
	public HashSet<UNode<V,E>> adjNodes; // Lista de los nodos adyacentes
	public ArrayList<Edge<V,E>> incEdges; // Lista de los lados incidentes

	/**
	* Constructor de la clase
	* @param id Identificador del nodo
	* @param data Dato del nodo
	* @param weight Peso del nodo
	**/
	public UNode(String id, V data, double weight){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.adjNodes = new HashSet<UNode<V,E>>();
		this.incEdges = new ArrayList<Edge<V,E>>();
		this.degree = 0;
	}

	/**
	* Metodo utilizado para obtener el peso del nodo
	**/
	public double getWeight(){
		return this.weight;
	}

	/**
	* Metodo utilizado para obtener el identificador del nodo
	**/
	public String getId(){
		return this.id;
	}

	/**
	* Metodo utilizado para obtener el dato del nodo
	**/
	public V getData(){
		return this.data;
	}

	/**
	* Metodo utilizado para obtener un string con informacion del nodo
	**/
	public String toString(){
		
		String vertex = "Identificador: "+this.getId()+"\n";
		vertex += "Dato: "+String.valueOf(this.getData()) + "\n";
		vertex += "Peso: "+String.valueOf(this.getWeight()) + "\n";

		return vertex;
	}

}