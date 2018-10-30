import java.util.*;
/**
* 
**/
public class Edge<E> extends GraphEdges{


	private String id; 
	private E data;
	private double weight;
	private Node<V> nodeA;
	private Node<V> nodeB;

	public Edge(String id, E data, double weight, Node<V> nodeA, Node<V>, nodeB){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.index = index;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
	}

	public double getWeight(){
		return this.weight;
	}

	public String getId(){
		return this.id;
	}

	public E getData(){
		return this.data;
	}

	public void toString(){
		
		String edge = " ";
		edge += "Id de la arista: "+this.getId()+"\n";
		edge += "Dato: "+String.valueOf(this.getData()) + "\n";
		edge += "Peso: "+String.valueOf(this.getWeight()) + "\n";
		edge += "Esta arista conecta los siguientes vertices:\n";
		edge += this.getFNode().toString();
		edge += this.getSNode().toString();
	}

	public getFNode(){
		return this.nodeA;
	}

	public getSNode(){
		return this.nodeB;
	}
}