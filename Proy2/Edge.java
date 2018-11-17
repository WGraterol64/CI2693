import java.lang.StringBuilder;
import java.util.*;
/**
*
* Clase Edge, representa las aristas en un grafo no dirigido.
* Extiende la clase abstracta GraphEdges
*
**/
public class Edge<V,E> extends GraphEdges{


	private String id; // Identificador de la arista
	private E data; // Dato de la arista
	private double weight; // Peso de la arista
	private UNode<V,E> nodeA; // Primer nodo de la arista
	private UNode<V,E> nodeB; // Segundo de la arista

	/**
	* Constructor de la clase
	* @param id Identificador de la arista a crear
	* @param data Dato almacenado la arista
	* @param weight Peso de la arista
	* @param nodeA Nodo inicial de la arista
	* @param nodeB Nodo final de la arista
	**/
	public Edge (String id, E data, double weight, UNode<V,E> nodeA, UNode<V,E> nodeB){
		this.id = id;
		this.data = data;
		this.weight = weight;
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
	* Metodo utilizado para obtener el dato de la arista
	**/
	public E getData(){
		return this.data;
	}

	/**
	* Metodo utilizado para crear un String con informacion
	* sobre la arista
	**/
	public String toString(){

		String edge = " ";
		edge += "Id de la arista: "+this.getId()+"\n";
		edge += "Dato: "+String.valueOf(this.getData()) + "\n";
		edge += "Peso: "+String.valueOf(this.getWeight()) + "\n";
		edge += "Esta arista conecta los siguientes vertices:\n";
		edge += this.getFNode().toString();
		edge += this.getSNode().toString();
		return edge;
	}

	/**
	* Metodo que devuelve el primer nodo de la arista
	* @return Uno de los nodos de la arista
	**/
	public UNode<V,E> getFNode(){
		return this.nodeA;
	}

	/**
	* Metodo que devuelve el segundo nodo de la arista
	* @return Uno de los nodos de la arista
	**/
	public UNode<V,E> getSNode(){
		return this.nodeB;
	}

	public void modData(Integer b){
		this.data += b;
	}
}
