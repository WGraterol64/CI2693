import java.util.*;

/**
* Clase de Nodos
**/
public class Node<V>{


	private String id; 
	private V data;
	private double weight;
	public int indegree;
	public int outdegree;
	public List<Node<V> > preNodes;
	public List<Node<V> > sucNodes;
	public List<Edge<E> > inEdges;
	public List<Edge<E> > outEdges;


	public Node(String id, V data, double weight){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.preNodes = new ArrayList<Node<V> >();
		this.sucNodes = new ArrayList<Node<V> >();
		this.inEdges = new ArrayList<Edge<E> >();
		this.outEdges = new ArrayList<Edge<E> >();
		this.indegree = 0;
		this.outdegree = 0;
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