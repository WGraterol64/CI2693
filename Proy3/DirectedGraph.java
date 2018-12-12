import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;
/**
* Clase que recrea grafos dirigidos
* Implementa los metodos de la clase abstracta Graph
**/
public class DirectedGraph{

	public int numOfNodes; // Numero de nodos del grafo
	public int numOfArcs; // Numero de arcos del grafo
	public HashSet<DNode> nodeSet; // Conjunto de nodos del grafo
	public HashSet<Arc> arcSet; // Conjunto de arcos del grafo
	private HashMap<String,DNode> namesToNodes; // Mapa  id -> Nodos
	private HashMap<String,Arc> namesToArcs; // Mapa id -> Arcos

	/**
	* Constructor de la clase
	**/
	public DirectedGraph(){

		this.namesToNodes = new HashMap<String,DNode>();
		this.namesToArcs = new HashMap<String,Arc>();
		this.nodeSet = new HashSet<DNode>();
		this.arcSet = new HashSet<Arc>();
		this.numOfNodes = 0;
		this.numOfArcs = 0;
	}


	/**
	* Metodo utilizado para obtener el numero de nodos del grafo
	* @return Numero de lados
	**/
	public int numOfNodes(){
		return this.numOfNodes;
	}

	/**
	* Metodo utilizado para obtener el numero de arcos del grafo
	* @return Numero de lados
	**/
	public int numOfEdges(){
		return this.numOfArcs;
	}

	/**
	* Metodo que agrega un nodo al grafo
	* @param node Nodo a agregar
	* @return booleano que identifica si se agrego exitosamente
	**/
	public boolean addNode(DNode node){

		String id = node.getId();

		// Verificamos si el nodo existe en el grafo
		if(namesToNodes.get(id) != null)
			return false;


		this.namesToNodes.put(id,node); // Agregamos el nodo al mapa
		this.nodeSet.add(node); // Agregamos el nodo al conjunto de nodos
		this.numOfNodes++; // Aumentamos el contador de nodos
		return true;
	}

	/**
	* Metodo que agrega un nodo al grafo
	* @param id Identificador del nodo a agregar
	* @param data Dato del nodo a agregar
	* @param weight Peso del nodo a agregar
	* @return booleano que identifica si se agrego exitosamente
	**/
	public boolean addNode(String id, String data, int weight){

		// Se verifica si el nodo existia en el grafo
		if(namesToNodes.get(id) != null)
			return false;

		DNode node = new DNode(id,data,weight); // Se crea un nuevo nodo
		this.namesToNodes.put(id,node); // Se coloca el nodo en el mapa
		this.nodeSet.add(node); // Se agrega el nodo al conjunto de nodos
		this.numOfNodes++; // Se aumenta el contador de nodos
		return true;
	}

	/**
	* Metodo utilizado para buscar un nodo en el grafo
	* @return El nodo cuyo identificador es id
	* @param id Identificador del nodo
	* @throws NoSuchElementException si no existe tal nodo
	**/
	public DNode getNode(String id) throws NoSuchElementException{

		DNode node = namesToNodes.get(id); // Buscamos al nodo en el mapa
		// Si no existe, se arroja una excepcion
		if(node == null)
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		// Se encontro, y se retorna
		return node;

	}

	/**
	* Metodo que dice si existe en el grafo un nodo con identificador id
	* @param id Identificador a buscar
	* @return Booleano que especifica si el nodo existe o no
	**/
	public boolean isNode(String id){
		return (this.namesToNodes.get(id) != null);
	}

	/**
	* Metodo que dice si existe en el grafo un arco con identificador id
	* @param id Identificador del arco
	* @return Booleano que especifica si el arco pertenece al grafo
	**/
	public boolean isEdge(String id){
		return (this.namesToArcs.get(id) != null);
	}

	/**
	* Metodo que dice si existe en el grafo un arco desde u hasta v
	* @param u Identificador del nodo inicial del arco
	* @param v Identificador del nodo final del arco
	* @return Booleano que especifica si el arco pertenece al grafo
	**/
	public boolean isEdge(String u, String v){

		// Verificamos que u y v pertenezcan al grafo
		if(!this.isNode(u) || !this.isNode(v))
			return false;

		DNode nodeU = getNode(u);
		// Buscamos en los arcos que salen de u
		for( Arc arc: nodeU.outEdges ){
			if(arc.getEndNode().getId().equals(v))
				return true;
		}

		return false;
	}


	/**
	* Metodo utilizado para eliminar un nodo del grafo
	* @param id Identificador del nodo a eliminar
	* @return Booleano que especifica si se removio el nodo de manera exitosa
	**/
	public boolean removeNode(String id) {

		// Verificamos si el nodo pertenece al grafo
		if(!isNode(id))
			return false;

		// Buscamos el nodo con identificador id
		DNode nodeU = this.getNode(id);

		// Para todos los predecesores V del nodo U
		for( DNode  nodeV : nodeU.preNodes ){

			if(nodeV.getId().equals(id))
				continue;

			// Eliminamos U de la lista de sucesores de V
			// Y reducimos el outdegree de V
			while(nodeV.sucNodes.contains(nodeU)){
				nodeV.outdegree--;
				nodeV.sucNodes.remove(nodeU);
			}

			// Agregamos los arcos que salen de V hasta U a un stack
			Stack<Arc> toRemove = new Stack<>();
			for(Arc arc : nodeV.outEdges)
				if(arc.getEndNode().getId().equals(id))
					toRemove.push(arc);

			// Eliminamos todos estos arcos del grafo y de la lista
			// de arcos salientes de V
			while(!toRemove.empty()){
				Arc a = toRemove.pop();
				nodeV.outEdges.remove(a);
				this.namesToArcs.remove(a.getId());
				this.arcSet.remove(a);
			}
		}

		// Eliminamos todos los arcos que salen de U y
		// reducimos el grado interno de los nodos terminales
		for(Arc arc : nodeU.outEdges){
			DNode nodeV = arc.getEndNode();
			nodeV.indegree--;
			this.arcSet.remove(arc);
			this.namesToArcs.remove(arc.getId());
		}

		// Eliminamos el nodo del conjunto de nodos del grafo
		this.nodeSet.remove(nodeU);
		this.numOfNodes--; // Se reduce el numero de nodos del grafo
		this.namesToNodes.remove(id); // Se saca del mapa
		return true;
	}

	/**
	*  Metodo que busca los nodos de un grafo
	*  @return devuelve una lista con los nodos del grafo
	**/
	public ArrayList<DNode> nodeList(){

		ArrayList<DNode > list = new ArrayList<DNode>(numOfNodes);
		for( DNode  v : this.nodeSet)
			list.add(v);
		return list;
	}

	/**
	* Metodo que busca los lados de un grafo
	* @return devuelve una lista con los lados del grafo
	**/
	public ArrayList<Arc> edgeList(){

		ArrayList <Arc > list = new ArrayList<Arc>(numOfArcs);
		for( Arc a : this.arcSet)
			list.add(a);
		return list;
	}

	/**
	* Metodo que calcula el grado de un nodo
	* @return Grado del nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public int degree(String id)  throws NoSuchElementException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		DNode v = namesToNodes.get(id);
		return v.indegree + v.outdegree;
	}

	/**
	* Metodo que busca nodos adyacentes a un nodo con identificador id
	* @return devuelve una lista de adyacentes del nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public ArrayList<DNode> adjacency(String id) throws NoSuchElementException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		DNode  node  = this.namesToNodes.get(id);
		ArrayList<DNode > list = new ArrayList<DNode >(node.outdegree);
		for( DNode v : node.sucNodes )
			list.add(v);
		for( DNode v : node.preNodes )
			list.add(v);
		return list;
	}

	/**
	* Metodo que busca arcos incidentes a un nodo con identificador id
	* @return devuelve una lista de lados incidentes al nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public ArrayList<Arc> incident(String id) throws NoSuchElementException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		DNode  node  = this.namesToNodes.get(id);
		ArrayList<Arc > list = new ArrayList<Arc>(degree(node.getId()));
		for( Arc arc : node.outEdges)
			list.add(arc);
		for( Arc arc : node.inEdges)
			list.add(arc);
		return list;
	}

	/**
	* Metodo utilizado para clonar el grafo
	* @return Grafo clonado
	**/
	public DirectedGraph clone(){

		DirectedGraph newGraph = new DirectedGraph();
		newGraph.numOfArcs = this.numOfArcs;
		newGraph.numOfNodes = this.numOfNodes;
		newGraph.nodeSet.addAll(this.nodeSet);
		newGraph.namesToNodes.putAll(this.namesToNodes);
		newGraph.arcSet.addAll(this.arcSet);
		newGraph.namesToArcs.putAll(this.namesToArcs);
		return newGraph;

	}

	/**
	* Metodo utilizado para crear un String con informacion del grafo
	* @return String con informacion del grafo
	**/
	public String toString(){

		String out = "Este es un grafo dirigido.\n";
		out += "Este grafo contiene "+numOfNodes+" nodos: \n";
		for(DNode node : this.nodeSet){
			out += node.toString();
		}
		out += "Este grafo contiene "+numOfArcs+" arcos: \n";
		for(Arc arc : this.arcSet){
			out += arc.toString();
		}

		return out;
	}

	/**
	* Metodo para agregar un arco al grafo
	* @param arc Arco a agregar
	* @return booleano que especifica si se agrego satisfactoriamente
	**/
	public boolean addArc(Arc arc){

		String id = arc.getId();

		// Verificamos si el arco pertenece al grafo
		if(isEdge(id))
			return false;

		arcSet.add(arc); // Agregamos el arco al conjunto de lados
		namesToArcs.put(id,arc); // Agregamos el arco al mapa
		numOfArcs++; // Aumentamos el contador de arcos
		DNode  nodeA = arc.getInitNode();
		DNode  nodeB = arc.getEndNode();
		nodeA.sucNodes.add(nodeB); // Agregamos a B como sucesor de A
		nodeB.preNodes.add(nodeA); // Agregamos a A como predecesor de B
		nodeA.outEdges.add(arc); // Agregamos el arco a los lados que salen de A
		nodeB.inEdges.add(arc); // Agregamos el arco a los lados que llegan a B
		nodeA.outdegree++;
		nodeB.indegree++;
		return true;
	}
	/**
	* Metodo para agregar un arco al grafo
	* @param id Identificador del arco
	* @param data Dato del arco
	* @param weight Peso del arco
	* @param u Identificador del nodo inicial
	* @param v Identificador del nodo final
	* @return booleano que especifica si se agrego satisfactoriamente
	**/
	public boolean addArc(String id, String data, double weight, String u, String v){

		DNode nodeA = this.namesToNodes.get(u);
		DNode nodeB = this.namesToNodes.get(v);;
		// Verificamos si el arco pertenece, o si los nodos no existen
		if(nodeA == null || nodeB == null || isEdge(id))
			return false;
		// Creamos un nuevo arco
		Arc arc = new Arc(id,data,weight,nodeA,nodeB);
		arcSet.add(arc); // Agregamos el arco al conjunto de lados
		namesToArcs.put(id,arc);  // Agregamos el arco al mapa
		numOfArcs++; // Aumentamos el contador de arcos
		nodeA.sucNodes.add(nodeB); // Agregamos a B como sucesor de A
		nodeB.preNodes.add(nodeA); // Agregamos a A como predecesor de B
		nodeA.outEdges.add(arc); // Agregamos el arco a los lados que salen de A
		nodeB.inEdges.add(arc); // Agregamos el arco a los lados que llegan a B
		nodeA.outdegree++;
		nodeB.indegree++;
		return true;
	}

	/**
	* Metodo para eliminar un arco del grafo
	* @param id Identificador del arco a eliminar
	* @return booleano que especifica si se elimino satisfactoriamente
	**/
	public boolean removeArc(String id){
		// Verificamos si el arco pertenece al grafo
		if(!isEdge(id))
			return false;

		Arc arc = getArc(id);
		DNode nodeA = arc.getInitNode();
		DNode nodeB = arc.getEndNode();
		// Eliminamos todas las incidencias del arco en el grafo
		arcSet.remove(arc);
		namesToArcs.remove(id);
		numOfArcs--;
		nodeA.sucNodes.remove(nodeB);
		nodeB.preNodes.remove(nodeA);
		nodeA.outEdges.remove(arc);
		nodeB.inEdges.remove(arc);
		nodeA.outdegree--;
		nodeB.indegree--;
		return true;
	}

	/**
	* Metodo utilizado para buscar un arco en el grafo
	* @return El arco cuyo identificador es id
	* @param id Identificador del arco
	* @throws NoSuchElementException si no existe tal nodo
	**/
	public Arc getArc(String id){
		Arc arc = this.namesToArcs.get(id);
		if(arc == null)
			throw new NoSuchElementException("No existe un edge con identificador"+id);
		return arc;
	}

	/**
	* Metodo que calcula el grado interno de un nodo
	* @param id identificador del nodo
	* @return Grado del interno nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public int inDegree(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		DNode v = namesToNodes.get(id);
		return v.indegree;
	}

	/**
	* Metodo que calcula el grado externo de un nodo
	* @param id identificador del nodo
	* @return Grado externo del nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public int outDegree(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		DNode v = namesToNodes.get(id);
		return v.outdegree;
	}

	/**
	* Metodo que busca nodos sucesores a un nodo con identificador id
	* @param id Identificador del nodo
	* @return devuelve una lista de sucesores del nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public ArrayList<DNode> successor(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		DNode  node  = this.namesToNodes.get(id);
		ArrayList< DNode > list = new ArrayList<DNode>();
		for( DNode v : node.sucNodes)
			list.add(v);
		return list;
	}

	/**
	* Metodo que busca nodos predecisers a un nodo con identificador id
	* @param id Identificador del nodo
	* @return devuelve una lista de predecesores del nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public ArrayList<DNode> predecessor(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		DNode  node  = this.namesToNodes.get(id);
		ArrayList< DNode > list = new ArrayList<DNode>();
		for( DNode v : node.preNodes)
			list.add(v);
		return list;
	}

}
