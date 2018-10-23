public class UndirectedGraph<V,E> implements Graph{

	public UndirectedGraph();

	public boolean loadGraph(String fileName);

	public int numOfNodes();

	public int numOfEdges();

	public boolean addNode(Node<V> node);

	public boolean addNode(String id, V dato, double weight);

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

	public boolean addEdge(Edge<E> edge) 

	public boolean addEdge(String id, E data, double weight, String u, String v) 

	public boolean removeEdge(String id)

	public Edge<E> getEdge(String id)

}