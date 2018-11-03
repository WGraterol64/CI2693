import java.util.*;

/**
* Clase de Nodos
**/
public class DNode<V,E> extends Node{


	private String id; 
	private V data;
	private double weight;
	public int indegree;
	public int outdegree; 
	public HashSet<DNode<V,E>> preNodes;
	public HashSet<DNode<V,E>> sucNodes;
	public ArrayList<Arc<V,E>> inEdges;
	public ArrayList<Arc<V,E>> outEdges;

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