public class UndirectedGraph<V,E> implements Graph{

	public int numOfNodes;
	public int numOfEdges;
	public Set<Node> nodeSet;
	public Set<Edge> edgeSet;
	private Map<String,Node> namesToNodes;
	private Map<String,Edge> namesToEdges;

	public UndirectedGraph(){ 
		private Map<String,Node> namesToNodes = new HashMap<>();
		private Map<String,Edge> namesToEdges = new HashMap<>();
		public Set<Node> nodeSet = new HashSet<>();
		public Set<Edge> nodeEdge = new HashSet<>();
		public numOfNodes = 0;
		public numOfEdges = 0;
	}
	
	public boolean loadGraph(String fileName){

	}

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
		this.nodeSet.add(node);
		this.numOfNodes++;
	}

	public boolean addNode(String id, V data, double weight){

		if(namesToNodes.get(id) != null)
			return false;

		Node node = new Node<V>(id,data,weight,numOfNodes);
		this.nodeSet.add(node);
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

	public boolean isEdge(String id){
		return (this.namesToEdges.get(id) == null)
	}

	public boolean isEdge(String u, String v){

		if(!this.isNode(u) || !this.isNode(v)) 
			return false;

		Node nodeU = getNode(id); 
		for( Node nodeV : nodeU.adj ){
			if(nodeV.getId().equals(v))
				return true;
		}

		return false;
	}

	public boolean removeNode(String id) {

		if(!isNode(id))
			return false;

		Node nodeU = this.getNode(id);
			
		for( Node nodeV : nodeU.adj )
			while(nodeV.adj.contains(nodeU))
				nodeV.adj.remove(NoClassDefFoundError);
		

		this.nodeSet.remove(nodeU);
		this.numOfNodes--;
		this.namesToNodes.remove(id);

		Stack toRemove = new Stack<Edge>();
		for( Edge e : edgeSet){
			if(e.getFNode().getId().equals(id) || e.getSNode().getId().equals(id))
				toRemove.push(e);
		}
		
		while(!toRemove.empty()){
			Edge e = toRemove.pop();
			String id = e.getId();
			this.namesToEdges.remove(id);
			edgeSet.remove(e);
		}
	}

	public ArrayList<Node> nodeList(){
		
		List <Node> list = new ArrayList<>(numOfNodes);
		for( Node v : this.nodeSet)
			list.add(v);
		return list;
	}

	public ArrayList<Edge> edgeList(){
		
		List <Edge> list = new ArrayList<>(numOfEdges);
		for( Edge e : this.edgeSet)
			list.add(e);
		return list;
	}

	public int degree(String id){
		
		if(numOfNodes == 0)
			return 0;

		int deg = -1;
		for(Node v : nodeSet){
			if(deg == -1)
				deg = v.indegree;
			deg = min(deg, v.indegree);
		}

		return deg;
	}	

	public ArrayList<Node> adjacency(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		Node node = this.namesToNodes.get(id);
		return node.adj;
	}

	public ArrayList<Node> incident(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		return this.adjacency(id);
	}

	public Graph clone();

	public String toString();

	public boolean addEdge(Edge<E> edge){

		String id = edge.getId();
		
		if(this.isEdge(id))
			return false;

		edgeSet.add(edge);
		numOfEdges++;
		Node u = edge.getFNode();
		Node v = edge.getSNode();
		u.adj.add(v);
		v.adj.add(u);
		u.indegree++;
		v.indegree++;
		u.outdegree++;
		v.outdegree++;
		return true;
	}

	public boolean addEdge(String id, E data, double weight, String u, String v){
		
		Node<V> nodeA = this.namesToNodes.get(u);
		Node<V> nodeB = this.namesToNodes.get(v);
		Edge<E> e = new Edge<>(id,data,weight,nodeA,nodeB);
		return  addEdge(e);
	}

	public boolean removeEdge(String id){

		if(!isEdge(id))
			return false;

		Edge<E> e = getEdge(id);
		Node<V> nodeA = e.getFNode();
		Node<V> nodeB = e.getSNode();

		edgeSet.remove(e);
		numOfEdges--;
		v.adj.remove(u);
		u.adj.remove(v);
		u.indegree--;
		v.indegree--;
		u.outdegree--;
		v.outdegree--;
		return true;

	}

	public Edge<E> getEdge(String id){

		Edge<E> edge = this.namesToEdges.get(id);
		if(edge == null)
			return false;
		return edge;
	}
}