public class Node<V>{

	private String id; 
	private V data;
	private double weight;

	public double getWeight(){
		return this.weight;
	}

	public String getId(){
		return this.id;
	}

	public V getData(){
		return this.data;
	}

	public void toString(){
		...
	}

}