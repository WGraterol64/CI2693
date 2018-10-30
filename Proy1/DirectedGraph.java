import java.io.BufferedReader;
import java.io.FileReader;

public class DirectedGraph<V,E> implements Graph{

	public int numOfNodes;
	public int numOfArcs;
	public Set<Node> nodeSet;
	public Set<Arc> arcSet;
	private Map<String,Node> namesToNodes;
	private Map<String,Arc> namesToArcs;
	private Transformer transV;
	private Transformer transE;

	public DirectedGraph(){
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

		node.setIndex(numOfNodes);
		this.nodeSet.add(node);
		this.numOfNodes++;
	}

	public boolean addNode(String id, V data, double weight){

		if(namesToNodes.get(id) != null)
			return false;

		Node node = new Node<V>(id,data,weight,numOfNodes);
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

		Node nodeU = getNode(id);
		for( Node nodeV : nodeU.adj ){
			if(nodeV.getId().equals(v))
				return true;
		}

		return false;
	}

	public boolean removeNode(String id){

		if(!isNode(id))
			return false;

		Node nodeU = this.getNode(id);

		for( Node nodeV : nodeU.adj )
			while(nodeV.inc.contains(nodeU))
				nodeV.inc.remove(NoClassDefFoundError);

		for( Node nodeV : nodeU.inc )
			while(nodeV.adj.contains(nodeU))
				nodeV.adj.remove(NoClassDefFoundError);


		this.nodeSet.remove(nodeU);
		this.numOfNodes--;
		this.namesToNodes.remove(id);

		Stack toRemove = new Stack<Arc>();
		for( Arc a : arcSet){
			if(a.getInitNode().getId().equals(id) || a.getEndNode().getId().equals(id))
				toRemove.push(a);
		}

		while(!toRemove.empty()){
			Arc e = toRemove.pop();
			String id = e.getId();
			this.namesToArcs.remove(id);
			arcSet.remove(e);
		}

	}

	public ArrayList<Node> nodeList(){

		List <Node> list = new ArrayList<>(numOfNodes);
		for( Node v : this.nodeSet)
			list.add(v);
		return list;
	}

	public ArrayList<Arc> arcList(){

		List <Arc> list = new ArrayList<>(numOfArcs);
		for( Arc a : this.arcSet)
			list.add(a);
		return list;
	}

	public int degree(String id)  throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		Node v = namesToNodes.get(id);
		return v.indegree + v.outdegree;
	}

	public ArrayList<Node> adjacency(String id) throws RuntimeException{

		Node v = namesToNodes.get(id);
		if(v == null)
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		return v.adj;
	}

	public ArrayList<Node> incident(String id) throws RuntimeException{

		Node v = namesToNodes.get(id);
		if(v == null)
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		return v.inc;
	}


	public Graph clone();

	public String toString();

	public boolean addArc(Arc<E> arc)

	public boolean addArc(String id, E data, double weight, String u, String v)

	public boolean removeArc(String id)

	public Edge<E> getArc(String id)

	public int inDegree(String id) // debe poner un NoSuchElementException

	public int outDegree(String id) // debe poner un NoSuchElementException 

	public ArrayList<Node> successor(String id)

	public ArrayList<Node> predecessor(String id)

}
