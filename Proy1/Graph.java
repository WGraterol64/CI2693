public interface Graph<V,E>{

	public Graph();

	public boolean loadGraph(String fileName);

	public int numOfNodes();

	public int numOfEdges();

	public boolean addNode(Node node);

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

}