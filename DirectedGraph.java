public class DirectedGraph<V,E> implements Graph{

	public int numOfNodes;
	public int numOfArcs;
	public Set<Node> nodeSet;
	public Set<Arc> arcSet;
	private Map<String,Node> namesToNodes;
	private Map<String,Arc> namesToArcs;
	private Transformer transV;
	private Transformer transE;

	public UndirectedGraph(){ 
		private Map<String,Node> namesToNodes = new HashMap<>();
		private Map<String,Arc> namesToArc = new HashMap<>();
		public Set<Node> nodeSet = new HashSet<>();
		public Set<Arc> nodeArc = new HashSet<>();
		public numOfNodes = 0;
		public numOfArcs = 0;
	}

	/**
  	* Carga el grafo desde un archivo de texto.
	*
	* @param fileName Archivo desde el que se desea cargar e grafo
	*
	* @throws IllegalArgumentException si el formato del archivo no es valido
	*
	* @throws UnsupportedOperationException si ocurre algun error parseando datos del archivo
	**/
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
			}catch(NumberFormatException){
				throw new UnsupportedOperationException("Formato no valido");
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
			}
		}
		// Lazo que agrega los arcos
		for(int i=0; i<m; i++){
			line = read.readLine();
			line = line.trim();
			String[] edge = line.split(" ");
			result = this.addEdge(edge[0], this.transE.Transform(edge[1]),
																 Double.parseDouble(edge[2]), edge[3], edge[4]);
			 if(!result){
					throw new IllegalArgumentException("Entrada no valida: No se pueden agregar lados");
			 }
		}
	}


	public int numOfNodes(){
		return this.numOfNodes;
	}

	public int numOfArcs(){
		return this.numOfArcs;
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

	public Node getNode(String id) throws RuntimeException{

		Node node = namesToNodes.get(id);
		if(node == null)
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		return node;

	}

	public boolean isNode(String id){
		return (this.namesToNodes.get(id) == null)
	}

	public boolean isArc(String id){
		return (this.namesToArcs.get(id) == null)
	}

	public boolean isArc(String u, String v){

		if(!this.isNode(u) || !this.isNode(v)) 
			return false;

		Node<V> nodeU = getNode(id); 
		for( Arc<E> arc: nodeU.outEdges ){
			if(arc.getInitNode().getId().equals(v) || arc.getEndNode().getId().equals(v)) 
				return true;
		}

		return false;
	}


	public boolean removeNode(String id) {

		if(!isNode(id))
			return false;

		Node<V> nodeU = this.getNode(id);
			
		for( Node<V>  nodeV : nodeU.preNodes ){ 
			if(nodeV.getId().equals(id)) 
				continue;
			while(nodeV.sucNodes.contains(nodeU)){
				nodeV.outdegree--;
				nodeV.sucNodes.remove(nodeU);
			}

			Stack toRemove = new Stack<Arc<E> >();
			for(Arc<E> arc : nodeV.outEdges)
				if(arc.getEndNode().getId().equals(id))
					arc.push(arc);

			while(!toRemove.empty()){
				Arc<E> a = toRemove.pop();
				nodeV.outEdges.remove(a);
				this.namesToArcs.remove(a.getId());
				this.arcSet.remove(a);
			}
		}

		for(Arc<E> arc : nodeU.outEdges){
			Node<V> nodeV = arc.getEndNode();
			nodeV.indegree--;
			this.arcSet.remove(arc);
			this.namesToEdges.remove(arc.getId());
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

	public ArrayList<Arc<E> > arcList(){
		
		List <Arc<E> > list = new ArrayList<>(numOfArcs);
		for( Arc<E> a : this.arcSet)
			list.add(a);
		return list;
	}


	public int degree(String id)  throws RuntimeException{
		
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		Node v = namesToNodes.get(id);
		return v.indegree + v.outdegree;
	}	

	public ArrayList<Node<V> > adjacency(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		Node<V>  node  = this.namesToNodes.get(id);
		return node.sucNodes;
	}

	public ArrayList<Arc<E> > incident(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		Node<V>  node  = this.namesToNodes.get(id);
		List<Arc<E> > list = new ArrayList<>(node.degree());
		for( Arc<E> arc : noode.outEdges)
			list.add(arc);
		for( Arc<E> arc : node.inEdges)
			list.add(arc);
		return list;
	}


	public DirectedGraph<V,E> clone(){

		Graph newGraph = new DirectedGraph<V,E>();
		newGraph.numOfEdges = this.numOfEdges;
		newGraph.numOfNodes = this.numOfNodes;
		newGraph.setOfNodes = (HashSet)this.setOfNodes.clone();
		newGraph.namesToNodes = (HashMap)this.namesToNodes.clone();
		newGraph.setOfEdges = (HashSet)this.setOfEdges.clone();
		newGraph.namesToEdges = (HashMap)this.namesToEdges.clone();
		return newGraph;

	}

	public String toString(){

		System.out.print("Este es un grafo dirigido.\n");
		System.out.print("Este grafo contiene "+numOfNodes+" nodos: \n");
		for(Node<V> node : this.nodeSet){
			String s = node.toString();
			System.out.print(s+"\n");
		}
		System.out.print("Este grafo contiene "+numOfArcs+" arcos: \n");
		for(Arc<E> arc : this.arcSet){
			String s = arc.toString();
			System.out.print(s+"\n");
		}
	}

	public boolean addArc(Arc<E> arc){
		
		String id = arc.getId();
		
		if(isArc(id))
			return false;

		arcSet.add(arc);
		namesToNodes.put(id,arc);
		numOfArcs++;
		Node<V>  nodeA = arc.getInitNode();
		Node<V>  nodeB = arc.getENdNode();
		nodeA.sucNodes.add(nodeB);
		nodeB.preNodes.add(nodeA);
		nodeA.outEdges.add(arc);
		nodeB.inEdges.add(arc);
		nodeA.outdegree++;
		nodeB.indegree++;
		return true;
	}

	public boolean addArc(String id, E data, double weight, String u, String v){

		Node<V> nodeA = this.namesToNodes.get(u);
		Node<V> nodeB = this.namesToNodes.get(v);
		if(nodeA == null || nodeB == null || isEdge())
			return false;
		
		Arc<E> arc = new Arc<E>(id,data,weight,nodeA,nodeB);
		arcSet.add(arc);
		namesToNodes.put(id,arc);
		numOfArcs++;
		nodeA.sucNodes.add(nodeB);
		nodeB.preNodes.add(nodeA);
		nodeA.outEdges.add(arc);
		nodeB.inEdges.add(arc);
		nodeA.outdegree++;
		nodeB.indegree++;
		return true;
	}

	public boolean removeArc(String id){
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

	public Arc<E> getArc(String id){
		Arc<E> arc = this.namesToArcs.get(id);
		if(edge == null)
			throw new NoSuchElementException("No existe un edge con identificador"+id);
		return arc;
	}

	public int inDegree(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		Node v = namesToNodes.get(id);
		return v.indegree;
	}

	public int outDegree(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		Node v = namesToNodes.get(id);
		return v.outdegree;
	}

	public ArrayList<Node> successor(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		Node<V>  node  = this.namesToNodes.get(id);
		return node.sucNodes;
	}

	public ArrayList<Node> predecessor(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		Node<V>  node  = this.namesToNodes.get(id);
		return node.preNodes;
	}

}