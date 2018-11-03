import java.util.*;

/**
* Clase de Nodos
**/
public class UNode<V,E> extends Node{


	private String id; 
	private V data;
	private double weight;
	public int degree;
	public HashSet<UNode<V,E>> adjNodes;
	public ArrayList<Edge<V,E>> incEdges;


	public UNode(String id, V data, double weight){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.adjNodes = new HashSet<UNode<V,E>>();
		this.adjEdges = new ArrayList<>();
		this.degree = 0;
	}

	public double getWeight(){
		return this.weight;
	}

	public String getId(){
		return this.id;
	}

	public V getData(){
		return this.data;
	}

	public String toString(){
		
		String vertex = "Identificador: "+this.getId()+"\n";
		vertex += "Dato: "+String.valueOf(this.getData()) + "\n";
		vertex += "Peso: "+String.valueOf(this.getWeight()) + "\n";

		return vertex;
	}

}