import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

public class DirectedGraph<V,E> implements Graph{

	public int numOfNodes;
	public int numOfArcs;
	public HashSet<DNode<V,E>> nodeSet;
	public HashSet<Arc<V,E>> arcSet;
	private HashMap<String,DNode<V,E>> namesToNodes;
	private HashMap<String,Arc<V,E>> namesToArcs;
	private Transformer<V> transV;
	private Transformer<V> transE;

	public DirectedGraph(){ 
		
		this.namesToNodes = new HashMap<String,DNode<V,E>>();
		this.namesToArcs = new HashMap<String,Arc<V,E>>();
		this.nodeSet = new HashSet<DNode<V,E>>();
		this.arcSet = new HashSet<Arc<V,E>>();
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
      throws IllegalArgumentException, UnsupportedOperationException, IOException{
		BufferedReader read = new BufferedReader(new FileReader(fileName));
    
	String vType = "", eType = "", line;
	int n = 0, m = 0;
	boolean result;

    // Lazo que lee las primeros 5 lineas
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
			}
			catch(IOException e){
				throw new UnsupportedOperationException("Formato no valido");
			}
		}
		Transformer<V> transV = new StringTransformer();;
		Transformer<E> transE = new StringTransformer();;
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
			result = this.addNode(node[0], transV.Transform(node[1]),
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
			result = this.addArc(edge[0], transE.Transform(edge[1]),
						Double.parseDouble(edge[2]), edge[3], edge[4]);
			 if(!result){
					throw new IllegalArgumentException("Entrada no valida: No se pueden agregar lados");
			 }
		}

		return true;
	}


	public int numOfNodes(){
		return this.numOfNodes;
	}

	public int numOfEdges(){
		return this.numOfArcs;
	}

	public boolean addNode(DNode<V,E> node){

		String id = node.getId();

		if(namesToNodes.get(id) != null)
			return false;

		this.namesToNodes.put(id,node);
		this.nodeSet.add(node);
		this.numOfNodes++;
		return true;
	}

	public boolean addNode(String id, V data, double weight){

		if(namesToNodes.get(id) != null)
			return false;

		DNode<V,E> node = new DNode<V,E>(id,data,weight);
		this.namesToNodes.put(id,node);
		this.nodeSet.add(node);
		this.numOfNodes++;
		return true;
	}

	public DNode<V,E> getNode(String id) throws RuntimeException{

		DNode<V,E> node = namesToNodes.get(id);
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

		DNode<V,E> nodeU = getNode(u); 
		for( Arc<V,E> arc: nodeU.outEdges ){
			if(arc.getEndNode().getId().equals(v)) 
				return true;
		}

		return false;
	}


	public boolean removeNode(String id) {

		if(!isNode(id))
			return false;

		DNode<V,E> nodeU = this.getNode(id);
			
		for( DNode<V,E>  nodeV : nodeU.preNodes ){ 
			if(nodeV.getId().equals(id)) 
				continue;
			while(nodeV.sucNodes.contains(nodeU)){
				nodeV.outdegree--;
				nodeV.sucNodes.remove(nodeU);
			}

			Stack<Arc<V,E>> toRemove = new Stack<>();
			for(Arc<V,E> arc : nodeV.outEdges)
				if(arc.getEndNode().getId().equals(id))
					toRemove.push(arc);

			while(!toRemove.empty()){
				Arc<V,E> a = toRemove.pop();
				nodeV.outEdges.remove(a);
				this.namesToArcs.remove(a.getId());
				this.arcSet.remove(a);
			}
		}

		for(Arc<V,E> arc : nodeU.outEdges){
			DNode<V,E> nodeV = arc.getEndNode();
			nodeV.indegree--;
			this.arcSet.remove(arc);
			this.namesToArcs.remove(arc.getId());
		}
		
		this.nodeSet.remove(nodeU);
		this.numOfNodes--;
		this.namesToNodes.remove(id);
		return true;	
	}

	public ArrayList<DNode<V,E> > nodeList(){
		
		ArrayList<DNode<V,E> > list = new ArrayList<DNode<V,E>>(numOfNodes);
		for( DNode<V,E>  v : this.nodeSet)
			list.add(v);
		return list;
	}

	public ArrayList<Arc<V,E> > edgeList(){
		
		ArrayList <Arc<V,E> > list = new ArrayList<Arc<V,E>>(numOfArcs);
		for( Arc<V,E> a : this.arcSet)
			list.add(a);
		return list;
	}


	public int degree(String id)  throws RuntimeException{
		
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		DNode<V,E> v = namesToNodes.get(id);
		return v.indegree + v.outdegree;
	}	

	public ArrayList<DNode<V,E> > adjacency(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		DNode<V,E>  node  = this.namesToNodes.get(id);
		ArrayList<DNode<V,E> > list = new ArrayList<DNode<V,E> >(node.outdegree);
		for( DNode<V,E> v : node.sucNodes )
			list.add(v);
		for( DNode<V,E> v : node.preNodes )
			list.add(v);
		return list;
	}

	public ArrayList<Arc<V,E> > incident(String id) throws RuntimeException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		DNode<V,E>  node  = this.namesToNodes.get(id);
		ArrayList<Arc<V,E> > list = new ArrayList<Arc<V,E>>(degree(node.getId()));
		for( Arc<V,E> arc : node.outEdges)
			list.add(arc);
		for( Arc<V,E> arc : node.inEdges)
			list.add(arc);
		return list;
	}


	public DirectedGraph<V,E> clone(){

		DirectedGraph<V,E> newGraph = new DirectedGraph<V,E>();
		newGraph.numOfArcs = this.numOfArcs;
		newGraph.numOfNodes = this.numOfNodes;
		newGraph.nodeSet.addAll(this.nodeSet);
		newGraph.namesToNodes.putAll(this.namesToNodes);
		newGraph.arcSet.addAll(this.arcSet);
		newGraph.namesToArcs.putAll(this.namesToArcs);
		return newGraph;

	}

	public String toString(){

		String out = "Este es un grafo dirigido.\n";
		out += "Este grafo contiene "+numOfNodes+" nodos: \n";
		for(DNode<V,E> node : this.nodeSet){
			out += node.toString();
		}
		out += "Este grafo contiene "+numOfArcs+" arcos: \n";
		for(Arc<V,E> arc : this.arcSet){
			out += arc.toString();
		}

		return out;
	}

	public boolean addArc(Arc<V,E> arc){
		
		String id = arc.getId();
		
		if(isEdge(id))
			return false;

		arcSet.add(arc);
		namesToArcs.put(id,arc);
		numOfArcs++;
		DNode<V,E>  nodeA = arc.getInitNode();
		DNode<V,E>  nodeB = arc.getEndNode();
		nodeA.sucNodes.add(nodeB);
		nodeB.preNodes.add(nodeA);
		nodeA.outEdges.add(arc);
		nodeB.inEdges.add(arc);
		nodeA.outdegree++;
		nodeB.indegree++;
		return true;
	}

	public boolean addArc(String id, E data, double weight, String u, String v){

		DNode<V,E> nodeA = this.namesToNodes.get(u);
		DNode<V,E> nodeB = this.namesToNodes.get(v);
		if(nodeA == null || nodeB == null || isEdge(id))
			return false;
		
		Arc<V,E> arc = new Arc<V,E>(id,data,weight,nodeA,nodeB);
		arcSet.add(arc);
		namesToArcs.put(id,arc);
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
		DNode<V,E> nodeA = arc.getInitNode();
		DNode<V,E> nodeB = arc.getEndNode();

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

	public Arc<V,E> getArc(String id){
		Arc<V,E> arc = this.namesToArcs.get(id);
		if(arc == null)
			throw new NoSuchElementException("No existe un edge con identificador"+id);
		return arc;
	}

	public int inDegree(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		DNode<V,E> v = namesToNodes.get(id);
		return v.indegree;
	}

	public int outDegree(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		DNode<V,E> v = namesToNodes.get(id);
		return v.outdegree;
	}

	public ArrayList<DNode<V,E>> successor(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		DNode<V,E>  node  = this.namesToNodes.get(id);
		ArrayList< DNode<V,E> > list = new ArrayList<DNode<V,E>>();
		for( DNode<V,E> v : node.sucNodes)
			list.add(v);
		return list;
	}

	public ArrayList<DNode<V,E>> predecessor(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		DNode<V,E>  node  = this.namesToNodes.get(id);
		ArrayList< DNode<V,E> > list = new ArrayList<DNode<V,E>>();
		for( DNode<V,E> v : node.preNodes)
			list.add(v);
		return list;
	}

}