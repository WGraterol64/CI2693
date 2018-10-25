public class Node{

	private String id; 
	private E data;
	private double weight;
	public ArrayList<Integer> adj;
	public int index;

	public Node(String id, E data, double weight, int index){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.index = index;
		this.adj = new ArrayList<Integer>();
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
		...
	}

}