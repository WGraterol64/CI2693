public class Arc<E> extends GraphEdges{


	private String id; 
	private E data;
	private double weight;
	private Node initNode;
	private Node endNode;


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

	public getInitNode(){
		return this.initNode;
	}

	public getEndNode(){
		return this.endNode;
	}
}