import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

public interface Graph<V,E>{


	public boolean loadGraph(String fileName);

	public int numOfNodes();

	public int numOfEdges();

	public boolean addNode(Node<V,E> node);

	public boolean addNode(String id, V dato, double weight);

	public Node getNode(String id);

	public boolean isNode(String id);

	public boolean isEdge(String u, String v);

	public boolean removeNode(String id);

	public ArrayList<Node<V,E> > nodeList();

	public ArrayList<Node<V,E> > edgeList();

	public int degree(String id);

	public ArrayList<Node<V,E>> adjacency(String id);

	public ArrayList<Node<V,E>> incident(String id);

	public Graph clone();

	public String toString();

}
