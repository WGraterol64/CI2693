import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

/**
* Implementacion de la clase grafo no dirigido
**/
public class UndirectedGraph<V,E> implements Graph{

	public int numOfNodes; // Numero de nodos
	public int numOfEdges; // Numero de aristas
	public HashSet<UNode<V,E> > nodeSet; // Set de nodos
	public HashSet<Edge<V,E>> edgeSet; // Set de aristas
	private HashMap<String,UNode<V,E> > namesToNodes; // Mapa Identificadores -> Nodos
	private HashMap<String,Edge<V,E>> namesToEdges; // Mapa Identificadores -> Aristas.
	private Transformer<V> transV;
	private Transformer<V> transE;

	/**
	* Constructor de la clase
	**/
	public UndirectedGraph(){
		this.namesToNodes = new HashMap<String,UNode<V,E> > ();
		this.namesToEdges = new HashMap<String,Edge<V,E> >();
		this.nodeSet = new HashSet<UNode<V,E> >();
		this.edgeSet = new HashSet<Edge<V,E> >();
		this.numOfNodes = 0;
		this.numOfEdges = 0;
	}

	/**
  	* Carga el grafo desde un archivo de texto.
	*
	* @param fileName Archivo desde el que se desea cargar e grafo
	*
	* @throws IllegalArgumentException si el formato del archivo no es valido
	*
	* @throws UnsupportedOperationException si ocurre algun error parseando datos del archivo
	*/
	public boolean loadGraph(String fileName)
      throws IllegalArgumentException, UnsupportedOperationException{
		BufferedReader read = new BufferedReader(new FileReader(fileName));
		// Lazo que lee las primeros 5 lineas
		String vType, eType, line;
		int n,m;
		boolean result;
		for(int i=0; i<5; i++){
			try{
				line = read.readLine();
				if(i==0){
					vType = line.trim();
				}else if(i==1){
					eType = line.trim();
				}else if(i==3){
					n = Integer.parseInt(line.trim());
				}else{
					m = Integer.parseInt(line.trim());
				}
			}catch(NumberFormatException e){
				throw new UnsupportedOperationException("Formato no valido");
				return false;
			}
		}
		Transformer<V> transV;
		Transformer<E> transE;
		// Inicializacion de los transformadores
		if(vType == "B" && eType =="B"){
			transV = new BooleanTransformer();
			transE = new BooleanTransformer();
		}else if(vType == "B" && eType =="D"){
			transV = new BooleanTransformer();
			transE = new DoubleTransformer();
		}else if(vType == "B" && eType =="S"){
			transV = new BooleanTransformer();
			transE = new StringTransformer();
		}else if(vType == "D" && eType =="B"){
			transV = new DoubleTransformer();
			transE = new BooleanTransformer();
		}else if(vType == "D" && eType =="D"){
			transV = new DoubleTransformer();
			transE = new DoubleTransformer();
		}else if(vType == "D" && eType =="S"){
			transV = new DoubleTransformer();
			transE = new StringTransformer();
		}else if(vType == "S" && eType =="B"){
			transV = new StringTransformer();
			transE = new BooleanTransformer();
		}else if(vType == "S" && eType =="D"){
			transV = new StringTransformer();
			transE = new DoubleTransformer();
		}else if(vType == "S" && eType =="S"){
			transV = new StringTransformer();
			transE = new StringTransformer();
		}
		// Lazo que agrega los nodos
		for(int i=0;i<n;i++){
			line = read.readLine();
			line = line.trim();
			String[] node = line.split(" ");
			result = this.addNode(node[0], transV.Transform(node[1]),
			Double.parseDouble(node[2]));
			if(!result){
				throw new IllegalArgumentException("Entrada no valida: No se pueden agregar nodos");
				return false;
			}
		}
		// Lazo que agrega los lados
		for(int i=0; i<m; i++){
			line = read.readLine().trim();
			String[] edge = line.split(" ");
			result = this.addEdge(edge[0], transE.Transform(edge[1]),
			Double.parseDouble(edge[2]), edge[3], edge[4]);
			 if(!result){
					throw new IllegalArgumentException("Entrada no valida: No se pueden agregar lados");
					return false;
			 }
		}

		return true;
	}

	/**
	* Metodo que devuelve el numero de nodos del grafo
	**/
	public int numOfNodes(){
		return this.numOfNodes;
	}

	/**
	* Metodo que devuelve el numero de aristas del grafo
	**/
	public int numOfEdges(){
		return this.numOfEdges;
	}

	/**
	* Metodo que crea y agrega un nodo al set de nodos del grafo
	*
	* @return Un booleano que especifica si el nodo se agrego satisfactoriamente
	*
	* @param id Identificador del nuevo nodo
	* @param data Dato que almacena el nuevo nodo
	* @param weight Peso del nuevo nodo
	*
	**/
	public boolean addNode(String id, V data, double weight){

		if(namesToNodes.get(id) != null)
			return false;

		UNode<V,E> node = new UNode(id,data,weight);
		this.namesToNodes.put(id,node);
		this.nodeSet.add(node);
		this.numOfNodes++;
		return true;

	}
	/**
	* Metodo que agrega un nodo al set de nodos del grafo
	*
	* @return Un booleano que especifica si el nodo se agrego satisfactoriamente
	*
	* @param node El nodo a agregar
	*
	**/
	
	public boolean addNode(UNode<V,E> node){

		String id = node.getId();

		if(namesToNodes.get(id) != null)
			return false;

		this.namesToNodes.put(id,node);
		this.nodeSet.add(node);
		this.numOfNodes++;
		return true;
	}

	

	public UNode<V,E> getNode(String id) throws RuntimeException{

		UNode<V,E>  node = namesToNodes.get(id);
		if(node == null)
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		return node;

	}

	public boolean isNode(String id){
		return (this.namesToNodes.get(id) == null);
	}

	public boolean isEdge(String id){
		return (this.namesToEdges.get(id) == null);
	}

	public boolean isEdge(String u, String v){

		if(!this.isNode(u) || !this.isNode(v))
			return false;

		UNode<V,E> nodeU = getNode(u);
		for( Edge<V,E> edge: nodeU.incEdges ){
			if(edge.getFNode().getId().equals(v) || edge.getSNode().getId().equals(v))
				return true;
		}

		return false;
	}

	public boolean removeNode(String id) {

		if(!isNode(id))
			return false;

		UNode<V,E> nodeU = this.getNode(id);

		for( UNode<V,E>  nodeV : nodeU.adjNodes ){
			if(nodeV.getId().equals(id))
				continue;
			while(nodeV.adjNodes.contains(nodeU)){
				nodeV.adjNodes.remove(nodeU);
				nodeV.degree--;
			}

			Stack<Edge<V,E>> toRemove = new Stack<>();
			for(Edge<V,E> edge : nodeV.incEdges)
				if(edge.getFNode().getId().equals(id)
					|| edge.getSNode().getId().equals(id))
						toRemove.push(edge);

			while(!toRemove.empty()){
				Edge<V,E> e = toRemove.pop();
				nodeV.incEdges.remove(e);
				this.namesToEdges.remove(e.getId());
				this.edgeSet.remove(e);
			}
		}

		for(Edge<V,E> edge : nodeU.incEdges){
			if(edge.getFNode().getId().equals(id)
				|| edge.getSNode().getId().equals(id)){

					this.edgeSet.remove(edge);
					this.namesToEdges.remove(edge.getId());
			}
		}

		this.nodeSet.remove(nodeU);
		this.numOfNodes--;
		this.namesToNodes.remove(id);
	}

	public ArrayList<UNode<V,E> > nodeList(){

		ArrayList<UNode<V,E> > list = new ArrayList<>(numOfNodes);
		for( UNode<V,E>  v : this.nodeSet)
			list.add(v);
		return list;
	}

	public ArrayList<Edge<V,E> > edgeList(){

		ArrayList <Edge<V,E> > list = new ArrayList<>(numOfEdges);
		for( Edge<V,E> e : this.edgeSet)
			list.add(e);
		return list;
	}

	public int degree(String id)  throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		UNode<V,E>  v = namesToNodes.get(id);
		return v.degree;
	}

	public ArrayList<UNode<V,E> > adjacency(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		UNode<V,E>  node  = this.namesToNodes.get(id);
		ArrayList<UNode<V,E> > list = new ArrayList<>(node.degree);
		for( UNode<V,E> v : node.adjNodes )
			list.add(v);
		return list;
	}

	public ArrayList<Edge<V,E> > incident(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		UNode<V,E>  node  = this.namesToNodes.get(id);
		ArrayList<Edge<V,E>> list = new ArrayList<>();
		for( Edge<V,E> edge : node.incEdges )
			list.add(edge);
		return list;
	}

	public UndirectedGraph<V,E> clone(){

		UndirectedGraph<V,E> newGraph = new UndirectedGraph();
		newGraph.numOfEdges = this.numOfEdges;
		newGraph.numOfNodes = this.numOfNodes;
		newGraph.nodeSet.addAll(this.nodeSet);
		newGraph.namesToNodes.putAll(this.namesToNodes);
		newGraph.edgeSet.addAll(this.edgeSet);
		newGraph.namesToEdges.putAll(this.namesToEdges);
		return newGraph;

	}

	public String toString(){

		System.out.print("Este es un grafo no dirigido.\n");
		System.out.print("Este grafo contiene "+numOfNodes+" nodos: \n");
		for(UNode<V,E> node : this.nodeSet){
			String s = node.toString();
			System.out.print(s+"\n");
		}
		System.out.print("Este grafo contiene "+numOfEdges+" aristas: \n");
		for(Edge<V,E> edge : this.edgeSet){
			String s = edge.toString();
			System.out.print(s+"\n");
		}

	}

	public boolean addEdge(Edge<V,E> edge){

		String id = edge.getId();

		if(isEdge(id))
			return false;

		edgeSet.add(edge);
		namesToEdges.put(id,edge);
		numOfEdges++;
		UNode<V,E>  nodeA = edge.getFNode();
		UNode<V,E>  nodeB = edge.getSNode();
		nodeA.adjNodes.add(nodeB);
		nodeB.adjNodes.add(nodeA);
		nodeA.incEdges.add(edge);
		nodeB.incEdges.add(edge);
		nodeA.degree++;
		nodeB.degree++;
		return true;
	}

	public boolean addEdge(String id, E data, double weight, String u, String v){

		UNode<V,E> nodeA = this.namesToNodes.get(u);
		UNode<V,E> nodeB = this.namesToNodes.get(v);
		if(nodeA == null || nodeB == null || isEdge(id))
			return false;

		Edge<V,E> edge = new Edge<>(id,data,weight,nodeA,nodeB);
		edgeSet.add(edge);
		namesToEdges.put(id,edge);
		nodeA.adjNodes.add(nodeB);
		nodeB.adjNodes.add(nodeA);
		nodeA.incEdges.add(edge);
		nodeB.incEdges.add(edge);
		nodeA.degree++;
		nodeB.degree++;
		numOfEdges++;
		return true;
	}

	public boolean removeEdge(String id){

		if(!isEdge(id))
			return false;

		Edge<V,E> edge = getEdge(id);
		UNode<V,E> nodeA = edge.getFNode();
		UNode<V,E> nodeB = edge.getSNode();

		edgeSet.remove(edge);
		namesToEdges.remove(id);
		numOfEdges--;
		nodeA.adjNodes.remove(nodeB);
		nodeB.adjNodes.remove(nodeA);
		nodeA.incEdges.remove(edge);
		nodeB.incEdges.remove(edge);
		nodeA.degree--;
		nodeB.degree--;
		return true;

	}

	public Edge<V,E> getEdge(String id) throws RuntimeException{

		Edge<V,E> edge = this.namesToEdges.get(id);
		if(edge == null)
			throw new NoSuchElementException("No existe un edge con identificador"+id);
		return edge;
	}
}
