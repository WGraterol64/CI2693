import java.util.*;
/**
* Clase de Nodos para un grafo no dirigido
* Extiende la clase abstacta Node
**/
public class UNode extends Node{


	private String id; // Identificador del nodo
	private Integer data; // Dato del nodo
	private Integer weight; // Peso del nodo
	public int degree; // Grado del nodo
	public HashSet<UNode> adjNodes; // Lista de los nodos adyacentes
	public ArrayList<Edge> incEdges; // Lista de los lados incidentes

	/**
	* Constructor de la clase
	* @param id Identificador del nodo
	* @param data Dato del nodo
	* @param weight Peso del nodo
	**/
	public UNode(String id, Integer data, Integer weight){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.adjNodes = new HashSet<UNode>();
		this.incEdges = new ArrayList<Edge>();
		this.degree = 0;
	}

	public UNode(UNode v){
		  this.id = v.getId();
		  this.weight = v.getWeight();
			this.data = v.getData();
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
	* Metodo utilizado para obtener el dato del nodo
	**/
	public Integer getData(){
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

	public void changeD(Integer k){
		this.data += k;
	}

	public void changeW(Integer k){
		this.data += k;
	}

}
