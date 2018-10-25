public class DirectedGraph<Vertex,Edge> implements Graph{

  public String id;
  public arrayList <Node> graph;
	private Hashset <Strings> ids;

	public DirectedGraph(){

	}

	public boolean loadGraph(String fileName){

	}

	public int numOfNodes(){
		int x = this.graph.length;
		return x;
	}

	public int numOfEdges(){
		int n = this.graph.length;
		int s = 0;
		for(i=0; i<n; i++){
			x = this.graph.get(i);
			s = s + x.Adjacency.size();
		}
		return s;
	}

	public boolean addNode(Node v){
    if(ids.contains(v.id)==false){
			this.graph.add(v);
			return true;
		}else{
			return false;
		}
	}

	public boolean addNode(String id, V dato, double weight){
		if(ids.contains(id)==false){
			v = new Node(id, dato, weight)
			this.graph.add(v);
			return true;
		}else{
			return false;
		}
	}

  // Me da fastidio buscar lo de las excepciones
	public Node getNode(String id){

	}

	public boolean isNode(String id){
		return ids.contains(id);
	}

  // Esperar a que jesus termine con los nodos
	public boolean isEdge(String u, String v){
		int n = this.graph.size();
		int i = 0;
		Node x = this.graph.get(i);
		while(i<n || x.id != u){
			i = i + 1
		}
		Node x = this.graph.get(i);
		while(i<n || x.id != u){
			i = i + 1
		}
		if(i == n){
			// Lanzar excepcion
		}else{
			return x.Adjacency.contains()
		}
	}

	public boolean removeNode(String id);

	public ArrayList<Node> nodeList(String id);

	public ArrayList<Node> edgeList(String id);

	public int degree(String id);

	public ArrayList<Node> adjacency(String id);

	public ArrayList<Node> incident(String id);

	public Graph clone();

	public String toString();

	public boolean addArc(Arc<E> arc)

	public boolean addArc(String id, E data, double weight, String u, String v)

	public boolean removeArc(String id)

	public Edge<E> getArc(String id)

	public int inDegree(String id)

	public int outDegree(String id)

	public ArrayList<Node> successor(String id)

	public ArrayList<Node> predecessor(String id)

}
