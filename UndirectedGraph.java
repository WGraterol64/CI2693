import java.util.*;

public class UndirectedGraph<V,E> implements Graph{

	public int numOfNodes;
	public int numOfEdges;
	public Set<Node<V> > nodeSet;
	public Set<Edge<E>> edgeSet;
	private Map<String,Node<V> > namesToNodes;
	private Map<String,Edge<E>> namesToEdges;

	public UndirectedGraph(){ 
		private Map<String,Node<V> > namesToNodes = new HashMap<>();
		private Map<String,Edge<E>> namesToEdges = new HashMap<>();
		public Set<Node<V> > nodeSet = new HashSet<>();
		public Set<Edge<E>> nodeEdge = new HashSet<>();
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

		this.namesToNodes.put(id,node);
		this.nodeSet.add(node);
		this.numOfNodes++;
	}

	public boolean addNode(String id, V data, double weight){

		if(namesToNodes.get(id) != null)
			return false;

		Node<V> node = new Node<V>(id,data,weight);
		this.namesToNodes.put(id,node);
		this.nodeSet.add(node);
		this.numOfNodes++;

	}

	public Node<V> getNode(String id) throws RuntimeException{

		Node<V>  node = namesToNodes.get(id);
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

		Node<V> nodeU = getNode(id); 
		for( Edge<E> edge: nodeU.outEdges ){
			if(edge.getFNode().getId().equals(v) || edge.getSNode().getId().equals(v)) 
				return true;
		}

		return false;
	}

	public boolean removeNode(String id) {

		if(!isNode(id))
			return false;

		Node<V> nodeU = this.getNode(id);
			
		for( Node<V>  nodeV : nodeU.sucNodes ){ 
			if(nodeV.getId().equals(id)) 
				continue;
			while(nodeV.sucNodes.contains(nodeU)){
				nodeV.sucNodes.remove(nodeU);
				nodeV.outdegree--;
			}

			Stack toRemove = new Stack<Edge<E> >();
			for(Edge<E> edge : nodeV.outEdges)
				if(edge.getFNode().getId().equals(id) 
					|| edge.getSNode().getId().equals(id))
						toRemove.push(edge);

			while(!toRemove.empty()){
				Edge<E> e = toRemove.pop();
				nodeV.outEdges.remove(e);
				this.namesToEdges.remove(e.getId());
				this.edgeSet.remove(e);
			}
		}

		for(Edge<E> edge : nodeU.outEdges){
			if(edge.getFNode().getId().equals(id) 
				|| edge.getSNode().getId().equals(id)){

					this.edgeSet.remove(edge);
					this.namesToEdges.remove(e.getId());
			}
		}
		
		this.nodeSet.remove(nodeU);
		this.numOfNodes--;
		this.namesToNodes.remove(id);
	}

	public ArrayList<Node<V> > nodeList(){
		
		List<Node<V> > list = new ArrayList<>(numOfNodes);
		for( Node<V>  v : this.nodeSet)
			list.add(v);
		return list;
	}

	public ArrayList<Edge<E> > edgeList(){
		
		List <Edge<E> > list = new ArrayList<>(numOfEdges);
		for( Edge<E> e : this.edgeSet)
			list.add(e);
		return list;
	}

	public int degree(String id)  throws RuntimeException{
		
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		Node<V>  v = namesToNodes.get(id);
		return v.outdegree;
	}	

	public ArrayList<Node<V> > adjacency(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		Node<V>  node  = this.namesToNodes.get(id);
		return node.sucNodes;
	}

	public ArrayList<Edge<E> > incident(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		Node<V>  node  = this.namesToNodes.get(id);
		return node.outEdges;
	}

	public UndirectedGraph<V,E> clone(){

		Graph newGraph = new UndirectedGraph();
		newGraph.numOfArcs = this.numOfArcs;
		newGraph.numOfNodes = this.numOfNodes;
		newGraph.setOfNodes = (HashSet)this.setOfNodes.clone();
		newGraph.namesToNodes = (HashMap)this.namesToNodes.clone();
		newGraph.setOfArcs = (HashSet)this.setOfArcs.clone();
		newGraph.namesToArcs = (HashMap)this.namesToArcs.clone();
		return newGraph;

	}

	public String toString(){

		System.out.print("Este es un grafo no dirigido.\n");
		System.out.print("Este grafo contiene "+numOfNodes+" nodos: \n");
		for(Node<V> node : this.nodeSet){
			String s = node.toString();
			System.out.print(s+"\n");
		}
		System.out.print("Este grafo contiene "+numOfEdges+" aristas: \n");
		for(Edges<E> edge : this.edgeSet){
			String s = edge.toString();
			System.out.print(s+"\n");
		}
	
	}

	public boolean addEdge(Edge<E> edge){

		String id = edge.getId();
		
		if(isEdge(id))
			return false;

		edgeSet.add(edge);
		namesToNodes.put(id,edge);
		numOfEdges++;
		Node<V>  nodeA = edge.getFNode();
		Node<V>  nodeB = edge.getSNode();
		nodeA.sucNodes.add(nodeB);
		nodeB.sucNodes.add(nodeA);
		nodeA.outEdges.add(edge);
		nodeB.outEdges.add(edge);
		nodeA.outdegree++;
		nodeB.outdegree++;
		return true;
	}

	public boolean addEdge(String id, E data, double weight, String u, String v){
		
		Node<V> nodeA = this.namesToNodes.get(u);
		Node<V> nodeB = this.namesToNodes.get(v);
		if(nodeA == null || nodeB == null || isEdge())
			return false;
		
		Edge<E> edge = new Edge<E>(id,data,weight,nodeA,nodeB);
		edgeSet.add(edge);
		namesToNodes.put(id,edge);
		nodeA.sucNodes.add(nodeB);
		nodeB.sucNodes.add(nodeA);
		nodeA.outEdges.add(edge);
		nodeB.outEdges.add(edge);
		nodeA.indegree++;
		nodeB.indegree++;
		numOfEdges++;
		return true;
	}

	public boolean removeEdge(String id){

		if(!isEdge(id))
			return false;

		Edge<E> edge = getEdge(id);
		Node<V> nodeA = edge.getFNode();
		Node<V> nodeB = edge.getSNode();

		edgeSet.remove(e);
		namesToEdges.remove(id);
		numOfEdges--;
		nodeA.sucNodes.remove(nodeB);
		nodeB.sucNodes.remove(nodeA);
		nodeA.outEdges.remove(edge);
		nodeB.outEdges.remove(edge);
		nodeA.indegree--;
		nodeB.indegree--;
		return true;

	}

	public Edge<E> getEdge(String id) throws RuntimeException{

		Edge<E> edge = this.namesToEdges.get(id);
		if(edge == null)
			throw new NoSuchElementException("No existe un edge con identificador"+id);
		return edge;
	}
}