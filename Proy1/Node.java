public class Node<V>{

	private String id; 
	private E data;
	private double weight;
	private int indegree;
	private int outdegree;
	public int index;
	public List<Node> adj;

	public Node(String id, V data, double weight){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.adj = new ArrayList<Node>();
	}

	public Node(String id, V data, double weight, int index){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.adj = new ArrayList<Node>();
		this.index = index;
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

	public void setIndex(int i){
		this.index = i;
	}

	public String toString(){
		
		String vertex = "Identificador: "+this.getId()+"\n";
		vertex += "Dato: "+String.valueOf(this.getData()) + "\n";
		vertex += "Peso: "+String.valueOf(this.getWeight()) + "\n";

		return vertex;
	}

}