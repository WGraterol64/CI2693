import java.util.*;
/**
* Clase de Nodos para un grafo dirigido
* Extiende la clase abstacta Node
**/
public class DNode<V,E> extends Node{


	private String id; // Identificador del nodo
	private V data; // Dato del nodo
	private double weight; // Peso del nodo
	public int indegree; // Grado interno
	public int outdegree; // Grado externo del nodo
	public HashSet<DNode<V,E>> preNodes; // Lista de los predecesores de un nodo
	public HashSet<DNode<V,E>> sucNodes; // Lista de los sucesores de un nodo
	public ArrayList<Arc<V,E>> inEdges; // Lista de arcos que llegan al nodo
	public ArrayList<Arc<V,E>> outEdges; // Lista de arcos que salen del nodo

	/**
	* Constructor de la clase
	* @param id Identificador del nodo
	* @param data Dato del nodo
	* @param weight Peso del nodo
	**/
	public DNode(String id, V data, double weight){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.preNodes = new HashSet<DNode<V,E>>();
		this.sucNodes = new HashSet<DNode<V,E>>();
		this.inEdges = new ArrayList<Arc<V,E>>();
		this.outEdges = new ArrayList<Arc<V,E>>();
		this.indegree = 0;
		this.outdegree = 0;
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