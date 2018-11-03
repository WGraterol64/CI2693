import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

public class Arc<V,E> extends GraphEdges{


	private String id;  private E data; private double weight; private
	DNode<V,E> initNode; private DNode<V,E> endNode;

	public Arc(String id, E data, double weight, DNode<V,E> initNode, DNode<V,E> endNode){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.initNode = initNode;
		this.endNode = endNode;
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
		edge += "Id del arco: "+this.getId()+"\n";
		edge += "Dato: "+String.valueOf(this.getData()) + "\n";
		edge += "Peso: "+String.valueOf(this.getWeight()) + "\n";
		edge += "Este arco conecta los siguientes vertices:\n";
		edge += "Inicial: \n"+this.getInitNode().toString();
		edge += "Final: \n"+this.getEndNode().toString();
		return edge;
	}

	public DNode<V,E> getInitNode(){
		return this.initNode;
	}

	public DNode<V,E> getEndNode(){
		return this.endNode;
	}

}