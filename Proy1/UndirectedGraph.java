public class UndirectedGraph<V,E> implements Graph{

	public int numOfNodes;
	public int numOfEdges;
	public Lis<Node> nodeList = new ArrayList<>();
	private Map<String,Node> namesToNodes = new HashMap<>();
	private Map<String,Edge> namesToEdges = new HashMap<>();


	public UndirectedGraph(){ 
	}
	
	public boolean loadGraph(String fileName);

	public int numOfNodes(){
		return this.numOfNodes;
	}

	public int numOfEdges(){
		return this.numOfEdges;
	}

	public boolean addNode(Node<V> node){

		String id = node.getId();
		if(namesToNodes.get(id) != null)
			return false;
		
		this.nodeList.add(node);
		this.numOfNodes++;		
	}

	public boolean addNode(String id, V data, double weight){

		if(namesToNodes.get(id) != null)
			return false;

		Node node = new Node<V>(id,data,weight);

	}

	public Node getNode(String id);

	public boolean isNode(String id);

	public boolean isEdge(String u, String v);

	public boolean removeNode(String id);

	public ArrayList<Node> nodeList(String id);

	public ArrayList<Node> edgeList(String id);

	public int degree(String id);

	public ArrayList<Node> adjacency(String id);

	public ArrayList<Node> incident(String id);

	public Graph clone();

	public String toString();

	public boolean addEdge(Edge<E> edge){

		String newId = edge.getId();
		boolean is = this.set.contains(newId);

		if(is)
			return false;
		
		Node u = edge.getFNode();
		Node v = edge.getSNode();

		if()
	}

	public boolean addEdge(String id, E data, double weight, String u, String v) 

	public boolean removeEdge(String id)

	public Edge<E> getEdge(String id)

}