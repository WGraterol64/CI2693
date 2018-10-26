public class Node<V>{

	private String id; 
	private E data;
	private double weight;
	public int indegree;
	public int outdegree;
	public List<Node> adj;
	public List<Node> inc;


	public Node(String id, V data, double weight){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.adj = new ArrayList<Node>();
		this.inc = new ArrayList<Node>();
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

	public void setIndex(int i){
		this.index = i;
	}

	public int getIndex(){
		return this.index;
	}



	public String toString(){
		
		String vertex = "Identificador: "+this.getId()+"\n";
		vertex += "Dato: "+String.valueOf(this.getData()) + "\n";
		vertex += "Peso: "+String.valueOf(this.getWeight()) + "\n";

		return vertex;
	}

}