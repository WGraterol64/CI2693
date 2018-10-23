public class Edge<E> extends GraphEdges{


	private String id; 
	private E data;
	private double weight;
	private Node nodeA;
	private Node nodeB;


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
		...
	}

	public getFNode(){
		return this.nodeA;
	}

	public getSNode(){
		return this.nodeB;
	}
}