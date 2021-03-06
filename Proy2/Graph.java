import java.util.*;
import java.io.IOException;
import java.lang.RuntimeException;
/**
* Interfaz Grafo
* Describe los metodos generales de un grafo
**/
public interface Graph{

	/**
	* Metodo que carga un grafo desde un archivo
	* @param fileName el nombre del archivo a cargar
	* @throws IllegalArgumentException si el formato del archivo no es valido
	* @throws IOException si ocurre un error al leer el archivo
	* @throws UnsupportedOperationException si ocurre algun error parseando los datos del archivo
	* @return Booleano que especifica si se cargo exitosamente
	**/
	public boolean loadGraph(String fileName) throws IllegalArgumentException, UnsupportedOperationException, IOException;

	/**
	* Metodo utilizado para obtener el numero de nodos en un grafo
	* @return Numero de nodos
	**/
	public int numOfNodes();

	/**
	* Metodo utilizado para obtener el numero de lados en un grafo
	* @return Numero de lados
	**/
	public int numOfEdges();

	/**
	* Metodo utilizado para buscar un nodo en el grafo
	* @return El nodo cuyo identificador es id
	* @param id Identificador del nodo
	* @throws NoSuchElementException si no existe tal nodo
	**/
	public Node getNode(String id) throws NoSuchElementException;

	/**
	* Metodo que dice si existe en el grafo un nodo con identificador id
	* @param id Identificador a buscar
	* @return Booleano que especifica si el nodo existe o no
	**/
	public boolean isNode(String id);

	/**
	* Metodo que dice si existe en el grafo un lado desde u hasta v
	* @param u Identificador de uno de los nodos del lado
	* @param v Identificador del otro nodo del lado
	* @return Booleano que especifica si el lado pertenece al grafo
	**/
	public boolean isEdge(String u, String v);

	/**
	* Metodo utilizado para eliminar un nodo del grafo
	* @param id Identificador del nodo a eliminar
	* @return Booleano que especifica si se removio el nodo de manera exitosa
	**/
	public boolean removeNode(String id);

	/**
	*  Metodo que busca los nodos de un grafo
	*  @return devuelve una lista con los nodos del grafo
	**/
	public ArrayList<UNode> nodeList();

	/**
	* Metodo que busca los lados de un grafo
	* @return devuelve una lista con los lados del grafo
	**/
	public ArrayList<Edge> edgeList();

	/**
	* Metodo que calcula el grado de un nodo
	* @param id identificador del nodo
	* @return Grado del nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public int degree(String id) throws NoSuchElementException;

	/**
	* Metodo que busca nodos adyacentes a un nodo con identificador id
	* @param id identificador del nodo
	* @return devuelve una lista de adyacentes del nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public ArrayList<UNode> adjacency(String id) throws NoSuchElementException;

	/**
	* Metodo que busca lados incidentes a un nodo con identificador id
	* @param id identificador del nodo
	* @return devuelve una lista de lados incidentes al nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public ArrayList<Edge> incident(String id) throws NoSuchElementException;

	/**
	* Metodo utilizado para clonar el grafo
	* @return clon del grafo
	**/
	public Graph clone();

	/**
	* Metodo utilizado para crear un String con informacion del grafo
	* @return String con informacion del grafo
	**/
	public String toString();

}
