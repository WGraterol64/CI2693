import java.util.*;
/** 
* Implementacion de la clase grafo no dirigido
**/
public class UndirectedGraph<V,E> implements Graph{

	public int numOfNodes; // Numero de nodos
	public int numOfEdges; // Numero de aristas
	public Set<Node<V,E> > nodeSet; // Set de nodos
	public Set<Edge<V,E>> edgeSet; // Set de aristas
	private Map<String,Node<V,E> > namesToNodes; // Mapa Identificadores -> Nodos
	private Map<String,Edge<V,E>> namesToEdges; // Mapa Identificadores -> ARistas

	/**
	* Constructor de la clase
	**/
	public UndirectedGraph(){ 
		this.namesToNodes = new HashMap<String,Node<V,E> > ();
		this.namesToEdges = new HashMap<String,Edge<V,E> >();
		this.nodeSet = new HashSet<Node<V,E> >();
		this.nodeEdge = new HashSet<Edge<V,E> >();
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
		for(int i=0; i<5; i++){
			try{
				String line = read.readLine();
				if(i==0){
					String vType = line.trim();
				}else if(i==1){
					String eType = line.trim();
				}else if(i==3){
					int n = Integer.parseInt(line.trim());
				}else{
					int m = Integer.parseInt(line.trim());
				}
			}catch(NumberFormatException e){
				throw new UnsupportedOperationException("Formato no valido");
				return false;
			}
		}
		// Inicializacion de los transformadores
		if(vType == "B" && eType =="B"){
			this.transV = new BooleanTransformer();
			this.transE = new BooleanTransformer();
		}else if(vType == "B" && eType =="D"){
			this.transV = new BooleanTransformer();
			this.transE = new DoubleTransformer();
		}else if(vType == "B" && eType =="S"){
			this.transV = new BooleanTransformer();
			this.transE = new StringTransformer();
		}else if(vType == "D" && eType =="B"){
			this.transV = new DoubleTransformer();
			this.transE = new BooleanTransformer();
		}else if(vType == "D" && eType =="D"){
			this.transV = new DoubleTransformer();
			this.transE = new DoubleTransformer();
		}else if(vType == "D" && eType =="S"){
			this.transV = new DoubleTransformer();
			this.transE = new StringTransformer();
		}else if(vType == "S" && eType =="B"){
			this.transV = new StringTransformer();
			this.transE = new BooleanTransformer();
		}else if(vType == "S" && eType =="D"){
			this.transV = new StringTransformer();
			this.transE = new DoubleTransformer();
		}else if(vType == "S" && eType =="S"){
			this.transV = new StringTransformer();
			this.transE = new StringTransformer();
		}
		// Lazo que agrega los nodos
		for(int i=0;i<n;i++){
			line = read.readLine();
			line = line.trim();
			String[] node = line.split(" ");
			boolean result = this.addNode(node[0], this.transV.Transform(node[1]),
			Double.parseDouble(node[2]));
			if(!result){
				throw new IllegalArgumentException("Entrada no valida: No se pueden agregar nodos");
				return false;
			}
		}
		// Lazo que agrega los lados
		for(int i=0; i<m; i++){
			line = read.readLine();
			line = line.trim();
			String[] edge = line.split(" ");
			result = this.addEdge(edge[0], this.transE.Transform(edge[1]),
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
	* Metodo que agrega un nodo al set de nodos del grafo
	*
	* @return Un booleano que especifica si el nodo se agrego satisfactoriamente
	*
	* @param node El nodo a agregar
	*
	**/ 
	public boolean addNode(Node<V,E> node){

		String id = node.getId();

		if(namesToNodes.get(id) != null)
			return false;

		this.namesToNodes.put(id,node);
		this.nodeSet.add(node);
		this.numOfNodes++;
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

		Node<V,E> node = new Node<V,E>(id,data,weight);
		this.namesToNodes.put(id,node);
		this.nodeSet.add(node);
		this.numOfNodes++;

	}

	public Node<V,E> getNode(String id) throws RuntimeException{

		Node<V,E>  node = namesToNodes.get(id);
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

		Node<V,E> nodeU = getNode(id); 
		for( Edge<V,E> edge: nodeU.outEdges ){
			if(edge.getFNode().getId().equals(v) || edge.getSNode().getId().equals(v)) 
				return true;
		}

		return false;
	}

	public boolean removeNode(String id) {

		if(!isNode(id))
			return false;

		Node<V,E> nodeU = this.getNode(id);
			
		for( Node<V,E>  nodeV : nodeU.sucNodes ){ 
			if(nodeV.getId().equals(id)) 
				continue;
			while(nodeV.sucNodes.contains(nodeU)){
				nodeV.sucNodes.remove(nodeU);
				nodeV.outdegree--;
			}

			Stack toRemove = new Stack<Edge<V,E> >();
			for(Edge<V,E> edge : nodeV.outEdges)
				if(edge.getFNode().getId().equals(id) 
					|| edge.getSNode().getId().equals(id))
						toRemove.push(edge);

			while(!toRemove.empty()){
				Edge<V,E> e = toRemove.pop();
				nodeV.outEdges.remove(e);
				this.namesToEdges.remove(e.getId());
				this.edgeSet.remove(e);
			}
		}

		for(Edge<V,E> edge : nodeU.outEdges){
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

	public ArrayList<Node<V,E> > nodeList(){
		
		List<Node<V,E> > list = new ArrayList<>(numOfNodes);
		for( Node<V,E>  v : this.nodeSet)
			list.add(v);
		return list;
	}

	public ArrayList<Edge<V,E> > edgeList(){
		
		List <Edge<V,E> > list = new ArrayList<>(numOfEdges);
		for( Edge<V,E> e : this.edgeSet)
			list.add(e);
		return list;
	}

	public int degree(String id)  throws RuntimeException{
		
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		Node<V,E>  v = namesToNodes.get(id);
		return v.outdegree;
	}	

	public ArrayList<Node<V,E> > adjacency(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		Node<V,E>  node  = this.namesToNodes.get(id);
		List<Node<V,E> > list = new ArrayList<Node<V,E> >(node.outegree);
		for( Node<V,E> v : node.sucNodes )
			list.add(v);
		return list;
	}

	public ArrayList<Edge<V,E> > incident(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		Node<V,E>  node  = this.namesToNodes.get(id);
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
		for(Node<V,E> node : this.nodeSet){
			String s = node.toString();
			System.out.print(s+"\n");
		}
		System.out.print("Este grafo contiene "+numOfEdges+" aristas: \n");
		for(Edges<E> edge : this.edgeSet){
			String s = edge.toString();
			System.out.print(s+"\n");
		}
	
	}

	public boolean addEdge(Edge<V,E> edge){

		String id = edge.getId();
		
		if(isEdge(id))
			return false;

		edgeSet.add(edge);
		namesToNodes.put(id,edge);
		numOfEdges++;
		Node<V,E>  nodeA = edge.getFNode();
		Node<V,E>  nodeB = edge.getSNode();
		nodeA.sucNodes.add(nodeB);
		nodeB.sucNodes.add(nodeA);
		nodeA.outEdges.add(edge);
		nodeB.outEdges.add(edge);
		nodeA.outdegree++;
		nodeB.outdegree++;
		return true;
	}

	public boolean addEdge(String id, E data, double weight, String u, String v){
		
		Node<V,E> nodeA = this.namesToNodes.get(u);
		Node<V,E> nodeB = this.namesToNodes.get(v);
		if(nodeA == null || nodeB == null || isEdge())
			return false;
		
		Edge<V,E> edge = new Edge<V,E>(id,data,weight,nodeA,nodeB);
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

		Edge<V,E> edge = getEdge(id);
		Node<V,E> nodeA = edge.getFNode();
		Node<V,E> nodeB = edge.getSNode();

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

	public Edge<V,E> getEdge(String id) throws RuntimeException{

		Edge<V,E> edge = this.namesToEdges.get(id);
		if(edge == null)
			throw new NoSuchElementException("No existe un edge con identificador"+id);
		return edge;
	}
}