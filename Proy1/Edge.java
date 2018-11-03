import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

/**
* 
**/
public class Edge<V,E> extends GraphEdges{


	private String id; 
	private E data;
	private double weight;
	private UNode<V,E> nodeA;
	private UNode<V,E> nodeB;

	public Edge (String id, E data, double weight, UNode<V,E> nodeA, UNode<V,E> nodeB){
		this.id = id;
		this.data = data;
		this.weight = weight;
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

	public UNode<V,E> getFNode(){
		return this.nodeA;
	}

	public UNode<V,E> getSNode(){
		return this.nodeB;
	}

}