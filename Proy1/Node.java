import java.util.*;

/**
* Clase de Nodos
**/
public class Node<V,E>{


	private String id; 
	private V data;
	private double weight;
	public int indegree;
	public int outdegree;
	public Set<Node<V,E> >  preNodes;
	public Set<Node<V,E> >  sucNodes;
	public List<Edge<V,E> > inEdges;
	public List<Edge<V,E> > outEdges;


	public Node(String id, V data, double weight){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.preNodes = new HashSet<Node<V,E> >();
		this.sucNodes = new HashSet<Node<V,E> >();
		this.inEdges = new ArrayList<Edge<V,E> >();
		this.outEdges = new ArrayList<Edge<V,E> >();
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