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
public class DirectedGraph<V,E> implements Graph{

	public int numOfNodes; // Numero de nodos del grafo
	public int numOfArcs; // Numero de arcos del grafo
	public HashSet<DNode<V,E>> nodeSet; // Conjunto de nodos del grafo
	public HashSet<Arc<V,E>> arcSet; // Conjunto de arcos del grafo
	private HashMap<String,DNode<V,E>> namesToNodes; // Mapa  id -> Nodos
	private HashMap<String,Arc<V,E>> namesToArcs; // Mapa id -> Arcos
	private Transformer<V> transV; // Transformador de tipos genericos
	private Transformer<V> transE; // Transformadosr de tipos genericos

	/**
	* Constructor de la clase
	**/
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
	* @throws IOException si ocurre un error al leer el archivo
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
				}else if(i==2){
					continue;
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
		if(vType.equals("B") && eType.equals("B")){
			this.transV = new BooleanTransformer();
			this.transE = new BooleanTransformer();
		}else if(vType.equals("B") && eType.equals("D")){
			this.transV = new BooleanTransformer();
			this.transE = new DoubleTransformer();
		}else if(vType.equals("B") && eType.equals("S")){
			this.transV = new BooleanTransformer();
			this.transE = new StringTransformer();
		}else if(vType.equals("D") && eType.equals("B")){
			this.transV = new DoubleTransformer();
			this.transE = new BooleanTransformer();
		}else if(vType.equals("D") && eType.equals("D")){
			this.transV = new DoubleTransformer();
			this.transE = new DoubleTransformer();
		}else if(vType.equals("D") && eType.equals("S")){
			this.transV = new DoubleTransformer();
			this.transE = new StringTransformer();
		}else if(vType.equals("S") && eType.equals("B")){
			this.transV = new StringTransformer();
			this.transE = new BooleanTransformer();
		}else if(vType.equals("S") && eType.equals("D")){
			this.transV = new StringTransformer();
			this.transE = new DoubleTransformer();
		}else if(vType.equals("S")&& eType.equals("S")){
			this.transV = new StringTransformer();
			this.transE = new StringTransformer();
		}
		// Lazo que agrega los nodos
		for(int i=0;i<n;i++){
			line = read.readLine().trim();
			String[] node = line.split(" ");
			result = this.addNode(node[0], transV.Transform(node[1]),
			Double.parseDouble(node[2]));
			if(!result){
				throw new IllegalArgumentException("Entrada no valida: No se pueden agregar nodos");
			}
		}
		// Lazo que agrega los arcos
		for(int i=0; i<m; i++){
			line = read.readLine().trim();
			String[] edge = line.split(" ");
			result = this.addArc(edge[0], transE.Transform(edge[1]),
						Double.parseDouble(edge[2]), edge[3], edge[4]);
			 if(!result){
					throw new IllegalArgumentException("Entrada no valida: No se pueden agregar lados");
			 }
		}
		return true;
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
	public boolean adNode(DNode<V,E> node){

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
	public boolean addNode(String id, V data, double weight){

		// Se verifica si el nodo existia en el grafo
		if(namesToNodes.get(id) != null)
			return false;

		DNode<V,E> node = new DNode<V,E>(id,data,weight); // Se crea un nuevo nodo
		this.namesToNodes.put(id,node); // Se coloca el nodo en el mapa
		this.nodeSet.add(node); // Se agrega el nodo al conjunto de nodos
		this.numOfNodes++; // Se aumenta el contador de nodos
		return true;
	}

	/**
	* Metodo utilizado para buscar un nodo en el grafo
	* @return El nodo cuyo identificador es id
	* @param id Identificador del nodo
	* @throws RuntimeException si no existe tal nodo
	**/
	public DNode<V,E> getNode(String id) throws RuntimeException{

		DNode<V,E> node = namesToNodes.get(id); // Buscamos al nodo en el mapa
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

		DNode<V,E> nodeU = getNode(u); 
		// Buscamos en los arcos que salen de u
		for( Arc<V,E> arc: nodeU.outEdges ){
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
		DNode<V,E> nodeU = this.getNode(id);
		
		// Para todos los predecesores V del nodo U
		for( DNode<V,E>  nodeV : nodeU.preNodes ){ 

			if(nodeV.getId().equals(id)) 
				continue;

			// Eliminamos U de la lista de sucesores de V
			// Y reducimos el outdegree de V
			while(nodeV.sucNodes.contains(nodeU)){
				nodeV.outdegree--;
				nodeV.sucNodes.remove(nodeU);
			}

			// Agregamos los arcos que salen de V hasta U a un stack
			Stack<Arc<V,E>> toRemove = new Stack<>();
			for(Arc<V,E> arc : nodeV.outEdges)
				if(arc.getEndNode().getId().equals(id))
					toRemove.push(arc);

			// Eliminamos todos estos arcos del grafo y de la lista
			// de arcos salientes de V
			while(!toRemove.empty()){
				Arc<V,E> a = toRemove.pop();
				nodeV.outEdges.remove(a);
				this.namesToArcs.remove(a.getId());
				this.arcSet.remove(a);
			}
		}

		// Eliminamos todos los arcos que salen de U y 
		// reducimos el grado interno de los nodos terminales
		for(Arc<V,E> arc : nodeU.outEdges){
			DNode<V,E> nodeV = arc.getEndNode();
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
	public ArrayList<DNode<V,E>> nodeList(){
		
		ArrayList<DNode<V,E> > list = new ArrayList<DNode<V,E>>(numOfNodes);
		for( DNode<V,E>  v : this.nodeSet)
			list.add(v);
		return list;
	}

	/**
	* Metodo que busca los lados de un grafo
	* @return devuelve una lista con los lados del grafo
	**/
	public ArrayList<Arc<V,E>> edgeList(){
		
		ArrayList <Arc<V,E> > list = new ArrayList<Arc<V,E>>(numOfArcs);
		for( Arc<V,E> a : this.arcSet)
			list.add(a);
		return list;
	}

	/**
	* Metodo que calcula el grado de un nodo
	* @return Grado del nodo
	* @throws RuntimeException Si el nodo no existe
	**/
	public int degree(String id)  throws RuntimeException{
		
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		DNode<V,E> v = namesToNodes.get(id);
		return v.indegree + v.outdegree;
	}	

	/**
	* Metodo que busca nodos adyacentes a un nodo con identificador id
	* @return devuelve una lista de adyacentes del nodo
	* @throws RuntimeException Si el nodo no existe
	**/
	public ArrayList<DNode<V,E>> adjacency(String id) throws RuntimeException{

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

	/**
	* Metodo que busca arcos incidentes a un nodo con identificador id
	* @return devuelve una lista de lados incidentes al nodo
	* @throws RuntimeException Si el nodo no existe
	**/
	public ArrayList<Arc<V,E>> incident(String id) throws RuntimeException{

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

	/**
	* Metodo utilizado para clonar el grafo
	* @return Grafo clonado
	**/
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

	/**
	* Metodo utilizado para crear un String con informacion del grafo
	* @return String con informacion del grafo
	**/
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

	/** 
	* Metodo para agregar un arco al grafo
	* @param arc Arco a agregar
	* @return booleano que especifica si se agrego satisfactoriamente
	**/
	public boolean addArc(Arc<V,E> arc){
		
		String id = arc.getId();
		
		// Verificamos si el arco pertenece al grafo
		if(isEdge(id))
			return false;

		arcSet.add(arc); // Agregamos el arco al conjunto de lados
		namesToArcs.put(id,arc); // Agregamos el arco al mapa
		numOfArcs++; // Aumentamos el contador de arcos
		DNode<V,E>  nodeA = arc.getInitNode();
		DNode<V,E>  nodeB = arc.getEndNode();
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
	public boolean addArc(String id, E data, double weight, String u, String v){

		DNode<V,E> nodeA = this.namesToNodes.get(u);
		DNode<V,E> nodeB = this.namesToNodes.get(v);;
		// Verificamos si el arco pertenece, o si los nodos no existen
		if(nodeA == null || nodeB == null || isEdge(id))
			return false;
		// Creamos un nuevo arco
		Arc<V,E> arc = new Arc<V,E>(id,data,weight,nodeA,nodeB);
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

		Arc<V,E> arc = getArc(id);
		DNode<V,E> nodeA = arc.getInitNode();
		DNode<V,E> nodeB = arc.getEndNode();
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
	* @throws RuntimeException si no existe tal nodo
	**/
	public Arc<V,E> getArc(String id){
		Arc<V,E> arc = this.namesToArcs.get(id);
		if(arc == null)
			throw new NoSuchElementException("No existe un edge con identificador"+id);
		return arc;
	}

	/**
	* Metodo que calcula el grado interno de un nodo
	* @return Grado del interno nodo
	* @throws RuntimeException Si el nodo no existe
	**/
	public int inDegree(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		DNode<V,E> v = namesToNodes.get(id);
		return v.indegree;
	}

	/**
	* Metodo que calcula el grado externo de un nodo
	* @return Grado externo del nodo
	* @throws RuntimeException Si el nodo no existe
	**/
	public int outDegree(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		
		DNode<V,E> v = namesToNodes.get(id);
		return v.outdegree;
	}

	/**
	* Metodo que busca nodos sucesores a un nodo con identificador id
	* @param id Identificador del nodo
	* @return devuelve una lista de sucesores del nodo
	* @throws RuntimeException Si el nodo no existe
	**/
	public ArrayList<DNode<V,E>> successor(String id){
		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		DNode<V,E>  node  = this.namesToNodes.get(id);
		ArrayList< DNode<V,E> > list = new ArrayList<DNode<V,E>>();
		for( DNode<V,E> v : node.sucNodes)
			list.add(v);
		return list;
	}

	/**
	* Metodo que busca nodos predecisers a un nodo con identificador id
	* @param id Identificador del nodo
	* @return devuelve una lista de predecesores del nodo
	* @throws RuntimeException Si el nodo no existe
	**/
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