public abstract class GraphEdges<E>{

	private String id; 
	private E data;
	private double weight;

	public abstract double getWeight();

	public abstract String getId();

	public abstract E getData();

	public abstract void toString();

}