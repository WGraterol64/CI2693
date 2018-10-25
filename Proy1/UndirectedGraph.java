public class UndirectedGraph<V,E> implements Graph{

	public String id;
	public int numOfNodes;
	public int numOfEdges;
	public List<Node> nodeList;
	public List<Edge> edgeList;





	/////// Para el constructor ///////
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
		
		node.setIndex(numOfNodes);
		this.nodeList.add(node);
		this.numOfNodes++;		
	}

	public boolean addNode(String id, V data, double weight){

		if(namesToNodes.get(id) != null)
			return false;

		Node node = new Node<V>(id,data,weight,numOfNodes);
		this.nodeList.add(node);
		this.numOfNodes++;

	}

	public Node getNode(String id) throws RuntimeException{

		Node node = namesToNodes.get(id);
		if(node == null)
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		return node;

	}

	public boolean isNode(String id){
		return (this.namesToNodes.get(id) == null)
	}

	public boolean isEdge(String u, String v){

		if(!this.isNode(u) || !this.isNode(v)) 
			return false;

		Node nodeU = nodeList.get(u);
		for(int i = 0; i<nodeU.adj.size; i++){
			nodeV = nodeU.adj.get(i);
			if(nodeV.getId().equals(v))
				return true;
		}

		return false;
	}

	public boolean removeNode(String id){


	}

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