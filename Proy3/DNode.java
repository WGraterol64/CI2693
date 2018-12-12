import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
/**
* Clase de Nodos para un grafo dirigido
* Extiende la clase abstacta Node
**/
public class DNode extends Node{


	private String id; // Identificador del nodo
	private String data; // Dato del nodo
	private int weight; // Peso del nodo
	public int indegree; // Grado interno
	public int outdegree; // Grado externo del nodo
	public int color; // Dato utilizado para hacer el recorrido sobre el nodo
	public HashSet<DNode> preNodes; // Lista de los predecesores de un nodo
	public HashSet<DNode> sucNodes; // Lista de los sucesores de un nodo
	public ArrayList<Arc> inEdges; // Lista de arcos que llegan al nodo
	public ArrayList<Arc> outEdges; // Lista de arcos que salen del nodo

	/**
	* Constructor de la clase
	* @param id Identificador del nodo
	* @param data Dato del nodo
	* @param weight Peso del nodo
	**/
	public DNode(String id, String data, int weight){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.preNodes = new HashSet<DNode>();
		this.sucNodes = new HashSet<DNode>();
		this.inEdges = new ArrayList<Arc>();
		this.outEdges = new ArrayList<Arc>();
		this.indegree = 0;
		this.outdegree = 0;
		this.color = -1;
	}

	/**
	* Metodo utilizado para obtener el peso del nodo
	**/
	public int getWeight(){
		return this.weight;
	}

	/**
	* Metodo utilizado para poner el peso del nodo
	* @param n Nuevo peso del nodo
	**/
	public void setWeight(int n){
		this.weight = n;
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
	public String getData(){
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
