import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

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
		
		this.namesToNodes = new HashMap<>();
		this.namesToArc = new HashMap<>();
		this.nodeSet = new HashSet<>();
		this.nodeArc = new HashSet<>();
		this.numOfNodes = 0;
		this.numOfArcs = 0;
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
			}catch(NumberFormatException e){
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

	public int numOfEdges(){
		return this.numOfArcs;
	}

	public boolean addNode(Node<V,E> node){

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

		Node<V,E> node = new Node<V,E>(id,data,weight);
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
		return (this.namesToNodes.get(id) == null);
	}

	public boolean isEdge(String id){
		return (this.namesToArcs.get(id) == null);
	}

	public boolean isEdge(String u, String v){

		if(!this.isNode(u) || !this.isNode(v)) 
			return false;

		Node<V,E> nodeU = getNode(id); 
		for( Arc<V,E> arc: nodeU.outEdges ){
			if(arc.getEndNode().getId().equals(v)) 
				return true;
		}

		return false;
	}


	public boolean removeNode(String id) {

		if(!isNode(id))
			return false;

		Node<V,E> nodeU = this.getNode(id);
			
		for( Node<V,E>  nodeV : nodeU.preNodes ){ 
			if(nodeV.getId().equals(id)) 
				continue;
			while(nodeV.sucNodes.contains(nodeU)){
				nodeV.outdegree--;
				nodeV.sucNodes.remove(nodeU);
			}

			Stack toRemove = new Stack<Arc<V,E> >();
			for(Arc<V,E> arc : nodeV.outEdges)
				if(arc.getEndNode().getId().equals(id))
					arc.push(arc);

			while(!toRemove.empty()){
				Arc<V,E> a = toRemove.pop();
				nodeV.outEdges.remove(a);
				this.namesToArcs.remove(a.getId());
				this.arcSet.remove(a);
			}
		}

		for(Arc<V,E> arc : nodeU.outEdges){
			Node<V,E> nodeV = arc.getEndNode();
			nodeV.indegree--;
			this.arcSet.remove(arc);
			this.namesToEdges.remove(arc.getId());
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

	public ArrayList<Arc<V,E> > edgeList(){
		
		List <Arc<V,E> > list = new ArrayList<>(numOfArcs);
		for( Arc<V,E> a : this.arcSet)
			list.add(a);
		return list;
	}


	public int degree(String id)  throws RuntimeException{
		
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		Node v = namesToNodes.get(id);
		return v.indegree + v.outdegree;
	}	

	public ArrayList<Node<V,E> > adjacency(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		Node<V,E>  node  = this.namesToNodes.get(id);
		List<Node<V,E> > list = new ArrayList<Node<V,E> >(node.outegree);
		for( Node<V,E> v : node.sucNodes )
			list.add(v);
		for( Node<V,E> v : node.preNodes )
			list.add(v);
		return list;
	}

	public ArrayList<Arc<V,E> > incident(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		Node<V,E>  node  = this.namesToNodes.get(id);
		List<Arc<V,E> > list = new ArrayList<>(node.degree());
		for( Arc<V,E> arc : node.outEdges)
			list.add(arc);
		for( Arc<V,E> arc : node.inEdges)
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
		for(Node<V,E> node : this.nodeSet){
			String s = node.toString();
			System.out.print(s+"\n");
		}
		System.out.print("Este grafo contiene "+numOfArcs+" arcos: \n");
		for(Arc<V,E> arc : this.arcSet){
			String s = arc.toString();
			System.out.print(s+"\n");
		}
	}

	public boolean addArc(Arc<V,E> arc){
		
		String id = arc.getId();
		
		if(isEdge(id))
			return false;

		arcSet.add(arc);
		namesToNodes.put(id,arc);
		numOfArcs++;
		Node<V,E>  nodeA = arc.getInitNode();
		Node<V,E>  nodeB = arc.getENdNode();
		nodeA.sucNodes.add(nodeB);
		nodeB.preNodes.add(nodeA);
		nodeA.outEdges.add(arc);
		nodeB.inEdges.add(arc);
		nodeA.outdegree++;
		nodeB.indegree++;
		return true;
	}

	public boolean addArc(String id, E data, double weight, String u, String v){

		Node<V,E> nodeA = this.namesToNodes.get(u);
		Node<V,E> nodeB = this.namesToNodes.get(v);
		if(nodeA == null || nodeB == null || isEdge())
			return false;
		
		Arc<V,E> arc = new Arc<V,E>(id,data,weight,nodeA,nodeB);
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

		Arc<V,E> arc = getArc(id);
		Node<V,E> nodeA = arc.getFNode();
		Node<V,E> nodeB = arc.getSNode();

		arcSet.remove(arc);
		namesToArcs.remove(id);
		numOfEdges--;
		nodeA.sucNodes.remove(nodeB);
		nodeB.preNodes.remove(nodeA);
		nodeA.outEdges.remove(arc);
		nodeB.inEdges.remove(arc);
		nodeA.outdegree--;
		nodeB.indegree--;
		return true;
	}

	public Arc<V,E> getArc(String id){
		Arc<V,E> arc = this.namesToArcs.get(id);
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

	public ArrayList<Node<V,E>> successor(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		Node<V,E>  node  = this.namesToNodes.get(id);
		List< Node<V,E> > list = new ArrayList<Node<V,E>>();
		for( Node<V,E> v : node.sucNodes)
			list.add(v);
		return list;
	}

	public ArrayList<Node<V,E>> predecessor(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		Node<V,E>  node  = this.namesToNodes.get(id);
		List< Node<V,E> > list = new ArrayList<Node<V,E>>();
		for( Node<V,E> v : node.preNodes)
			list.add(v);
		return list;
	}

}