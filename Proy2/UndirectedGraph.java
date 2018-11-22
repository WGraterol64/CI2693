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
public class UndirectedGraph implements Graph{

	public int numOfNodes; // Numero de nodos
	public int numOfEdges; // Numero de aristas
	public HashSet<UNode > nodeSet; // Set de nodos
	public HashSet<Edge> edgeSet; // Set de aristas
	private HashMap<String,UNode > namesToNodes; // Mapa Identificadores -> Nodos
	private HashMap<String,Edge> namesToEdges; // Mapa Identificadores -> Aristas

	/**
	* Constructor de la clase
	**/
	public UndirectedGraph(){
		this.namesToNodes = new HashMap<String,UNode > ();
		this.namesToEdges = new HashMap<String,Edge >();
		this.nodeSet = new HashSet<UNode >();
		this.edgeSet = new HashSet<Edge >();
		this.numOfNodes = 0;
		this.numOfEdges = 0;
	}

	/**
  	* Carga el grafo desde un archivo de texto.
	* @param fileName Archivo desde el que se desea cargar e grafo
	* @throws IllegalArgumentException si el formato del archivo no es valido
	* @throws IOException si ocurre un error al leer el archivo
	* @throws UnsupportedOperationException si ocurre algun error parseando datos del archivo
	**/
	public boolean loadGraph(String fileName)
      throws IllegalArgumentException, UnsupportedOperationException, IOException{

			BufferedReader read = new BufferedReader(new FileReader(fileName));
			// Lazo que lee las primeros 2 lineas
			String line;
			int n = 0 ,m = 0;
			boolean result;
			for(int i=0; i<2; i++){
				try{
					line = read.readLine();
					if(i==0){
						n = Integer.parseInt(line.trim());
					}else if(i==1){
						m = Integer.parseInt(line.trim());
					}
				}catch(NumberFormatException e){
					throw new UnsupportedOperationException("Formato no valido");
				}
				catch(IOException e){
					throw new UnsupportedOperationException("Formato no valido");
				}
			}
			// Lazo que agrega los nodos
			for(int i=0;i<n;i++){
				line = read.readLine();
				line = line.trim();
				String[] node = line.split(" ");
				result = this.addNode(node[0],Integer.parseInt(node[1]),
				Integer.parseInt(node[2]));
				if(!result){
					throw new IllegalArgumentException("Entrada no valida: No se pueden agregar nodos");
				}
			}
			// Lazo que agrega los lados
			for(int i=0; i<m; i++){
				line = read.readLine().trim();
				String[] edge = line.split(" ");
				result = this.addEdge(Integer.toString(i), Integer.parseInt(edge[2]),
				Double.parseDouble(edge[3]), edge[0], edge[1]);
				 if(!result){
						throw new IllegalArgumentException("Entrada no valida: No se pueden agregar lados");
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
	* @return Numero de lados
	**/
	public int numOfEdges(){
		return this.numOfEdges;
	}

	/**
	* Metodo que crea y agrega un nodo al set de nodos del grafo
	*
	* @return Un booleano que especifica si el nodo se agrego satisfactoriamente
	* @param id Identificador del nuevo nodo
	* @param data Dato que almacena el nuevo nodo
	* @param weight Peso del nuevo nodo
	*
	**/
	public boolean addNode(String id, Integer data, Integer weight){

		// Se verifica si el nodo existia en el grafo
		if(namesToNodes.get(id) != null)
			return false;

		UNode node = new UNode(id,data,weight); // Se crea un nuevo nodo
		this.namesToNodes.put(id,node); // Se coloca el nodo en el mapa
		this.nodeSet.add(node); // Se agrega el nodo al conjunto de nodos
		this.numOfNodes++; // Se aumenta el contador de nodos
		return true;

	}

	/**
	* Metodo que agrega un nodo al grafo
	* @param node Nodo a agregar
	* @return booleano que identifica si se agrego exitosamente
	**/
	public boolean addNode(UNode node){

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
	* Metodo utilizado para buscar un nodo en el grafo
	* @return El nodo cuyo identificador es id
	* @param id Identificador del nodo
	* @throws NoSuchElementException si no existe tal nodo
	**/
	public UNode getNode(String id) throws NoSuchElementException{

		UNode  node = namesToNodes.get(id);  // Buscamos al nodo en el mapa
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
	* Metodo que dice si existe en el grafo una arista con identificador id
	* @param id Identificador de la arista
	* @return Booleano que especifica si la arista pertenece al grafo
	**/
	public boolean isEdge(String id){
		return (this.namesToEdges.get(id) != null);
	}

	/**
	* Metodo que dice si existe en el grafo una arista entre u y v
	* @param u Identificador del primer nodo
	* @param v Identificador del segundo nodo
	* @return Booleano que especifica si el arco pertenece al grafo
	**/
	public boolean isEdge(String u, String v){

		// Verificamos que u y v pertenezcan al grafo
		if(!this.isNode(u) || !this.isNode(v))
			return false;

		UNode nodeU = getNode(u);
		// Buscamos en las aristas incidentes a U
		for( Edge edge: nodeU.incEdges ){
			if(edge.getFNode().getId().equals(v) || edge.getSNode().getId().equals(v))
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
		UNode nodeU = this.getNode(id);

		// Para todos los adyacentes V del nodo U
		for( UNode  nodeV : nodeU.adjNodes ){

			if(nodeV.getId().equals(id))
				continue;

			// Eliminamos U de la lista de adyacentes de V
			// Reducimos el degree de V
			while(nodeV.adjNodes.contains(nodeU)){
				nodeV.adjNodes.remove(nodeU);
				nodeV.degree--;
			}

			// Agregamos las aristas que salen unen V y U a un stack
			Stack<Edge> toRemove = new Stack<>();
			for(Edge edge : nodeV.incEdges)
				if(edge.getFNode().getId().equals(id)
					|| edge.getSNode().getId().equals(id))
						toRemove.push(edge);

			// Eliminamos todas estas aristas del grafo y de la lista
			// de arcos incidentes V
			while(!toRemove.empty()){
				Edge e = toRemove.pop();
				nodeV.incEdges.remove(e);
				this.namesToEdges.remove(e.getId());
				this.edgeSet.remove(e);
			}
		}

		// Eliminamos del grafo todas las aristas incidentes a U
		for(Edge edge : nodeU.incEdges){
			if(edge.getFNode().getId().equals(id)
				|| edge.getSNode().getId().equals(id)){
					this.edgeSet.remove(edge);
					this.namesToEdges.remove(edge.getId());
			}
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
	public ArrayList<UNode> nodeList(){

		ArrayList<UNode> list = new ArrayList<UNode>(numOfNodes);
		for( UNode  v : this.nodeSet)
			list.add(v);
		return list;
	}

	/**
	* Metodo que busca los lados de un grafo
	* @return devuelve una lista con los lados del grafo
	**/
	public ArrayList<Edge> edgeList(){

		ArrayList <Edge> list = new ArrayList<Edge>(numOfEdges);
		for( Edge e : this.edgeSet)
			list.add(e);
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

		UNode  v = namesToNodes.get(id);
		return v.degree;
	}

	/**
	* Metodo que busca nodos adyacentes a un nodo con identificador id
	* @return devuelve una lista de adyacentes del nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public ArrayList<UNode> adjacency(String id) throws NoSuchElementException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);

		UNode  node  = this.namesToNodes.get(id);
		ArrayList<UNode > list = new ArrayList<UNode>(node.degree);
		for( UNode v : node.adjNodes )
			list.add(v);
		return list;
	}

	/**
	* Metodo que busca aristas incidentes a un nodo con identificador id
	* @return devuelve una lista de lados incidentes al nodo
	* @throws NoSuchElementException Si el nodo no existe
	**/
	public ArrayList<Edge> incident(String id) throws NoSuchElementException{

		if(!isNode(id))
			throw new NoSuchElementException("No existe un nodo con identificador "+id);
		UNode  node  = this.namesToNodes.get(id);
		ArrayList<Edge> list = new ArrayList<Edge>();
		for( Edge edge : node.incEdges )
			list.add(edge);
		return list;
	}

	/**
	* Metodo utilizado para clonar el grafo
	* @return un grafo con los mismos nodos y aristas que este
	**/
	public UndirectedGraph clone(){

      	UndirectedGraph g = new UndirectedGraph();

			for(UNode v : this.nodeSet){
			    UNode u = new UNode(v);
				g.addNode(u);
			}

			for(Edge e : this.edgeSet){
		      	UNode u = g.getNode(e.getFNode().getId());
				UNode v = g.getNode(e.getSNode().getId());
				Edge f = new Edge(e, u, v);
				g.addEdge(f);
			}

      return g;
	}

	/**
	* Metodo para agregar una arista al grafo
	* @param edge Arista a agregar
	* @return booleano que especifica si se agrego satisfactoriamente
	**/
	public boolean addEdge(Edge edge){

		// Obtenemos el id
		String id = edge.getId();

		// Si ya pertenece al grafo, no realizamos la operacion
		if(isEdge(id))
			return false;

		edgeSet.add(edge); // Agregamos el edge al conjunto
		namesToEdges.put(id,edge); // Agregamos la arista al mapa
		numOfEdges++; // Aumentamos el numero de aristas
		UNode  nodeA = edge.getFNode();
		UNode  nodeB = edge.getSNode();
		nodeA.adjNodes.add(nodeB);  // Agregamos B a los adyacentes de A
		nodeB.adjNodes.add(nodeA); // Agregamos A a los adyacentes de B
		nodeA.incEdges.add(edge); // Agregamos la arista a las incidentes a A
		nodeB.incEdges.add(edge); // Agregamos la arista a las incidentes a B
		nodeA.degree++; // Aumentamos el grado de los nodos
		nodeB.degree++;
		return true;
	}

	/**
	* Metodo para agregar una arista al grafo
	* @param id Identificador de la arista
	* @param data dato de la arista
	* @param weight peso de la arista
	* @param u Identificador del primer nodo
	* @param v Identificador del segundo nodo
	* @return booleano que especifica si se agrego satisfactoriamente
	**/
	public boolean addEdge(String id, int cap, double weight, String u, String v){

		// Extraemos los nodos extremos
		UNode nodeA = this.namesToNodes.get(u);
		UNode nodeB = this.namesToNodes.get(v);
		// Revisamos si los nodos pertenecen al grafo, o si ya existia una
		// arista con ese identificador
		if(nodeA == null || nodeB == null || isEdge(id))
			return false;

		// Creamos el nuevo edge
		Edge edge = new Edge(id,cap,weight,nodeA,nodeB);
		edgeSet.add(edge); // Agregamos el edge al conjunto
		namesToEdges.put(id,edge);  // Agregamos la arista al mapa
		nodeA.adjNodes.add(nodeB); // Agregamos B a los adyacentes de A
		nodeB.adjNodes.add(nodeA);  // Agregamos A a los adyacentes de B
		nodeA.incEdges.add(edge); // Agregamos la arista a las incidentes a A
		nodeB.incEdges.add(edge); // Agregamos la arista a las incidentes a B
		// Aumentamos el grado de los nodos
		nodeA.degree++;
		nodeB.degree++;
		numOfEdges++;
		return true;
	}

	/**
	* Metodo para eliminar una arista del grafo
	* @param id Identificador dla arista a eliminar
	* @return booleano que especifica si se elimino satisfactoriamente
	**/
	public boolean removeEdge(String id){

		// Verificamos si la arista pertenece al grafo
		if(!isEdge(id))
			return false;

		Edge edge = getEdge(id);
		UNode nodeA = edge.getFNode();
		UNode nodeB = edge.getSNode();
		// Eliminamos todas las incidencias de la arista en el grafo
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

	/**
	* Metodo utilizado para buscar una arista en el grafo
	* @return Arista cuyo identificador es id
	* @param id Identificador de la arista
	* @throws NoSuchElementException si no existe tal nodo
	**/
	public Edge getEdge(String id) throws NoSuchElementException{

		Edge edge = this.namesToEdges.get(id);
		if(edge == null)
			throw new NoSuchElementException("No existe un edge con identificador"+id);
		return edge;
	}

	public boolean BellmanFord(String s){

		for( UNode v : this.nodeSet){
			v.distanceToSource = Double.POSITIVE_INFINITY;
			v.cameFrom = null;
		}

		UNode source = this.namesToNodes.get(s);
		source.distanceToSource = 0.0;

		boolean change = true;
		for(int i = 0; i < this.numOfNodes && change; i++){
			change = false;
			for( Edge e : this.edgeSet ){
				if(e.getCap() == 0) continue;
				UNode nodeU = e.getFNode();
				UNode nodeV = e.getSNode();
				double dist = e.getWeight();

				if(nodeV.getId().equals("sink")){

					if(nodeV.distanceToSource > nodeU.distanceToSource + dist){
						change = true;
						nodeV.distanceToSource = nodeU.distanceToSource + dist;
						nodeV.cameFrom = e;
					}
					continue;
				}

				if(nodeU.distanceToSource > nodeV.distanceToSource + dist){
					change = true;
					nodeU.distanceToSource = nodeV.distanceToSource + dist;
					nodeU.cameFrom = e;
				}
				else if(nodeV.distanceToSource > nodeU.distanceToSource + dist){
					change = true;
					nodeV.distanceToSource = nodeU.distanceToSource + dist;
					nodeV.cameFrom = e;
				}
			}
		}

		return !change;
	}

	public void printPath(Stack<String> stack, int capacity, String dest, double distance){

		System.out.println(capacity+" personas a "+dest);
		System.out.print("\tRuta: ");
		String building = stack.pop();
		System.out.print(building);
		while(!stack.isEmpty()){
			System.out.print(" - ");
			System.out.print(stack.pop());
		}
		System.out.print(" ("+distance+" m)\n");

	}
	public void solve(String sId, int people){

		UNode source = this.namesToNodes.get(sId);
		UNode sink = this.namesToNodes.get("sink");

		while(people > 0){

			this.BellmanFord(sId);

			if(sink.distanceToSource == Double.POSITIVE_INFINITY)
				break;

			Edge e = sink.cameFrom;
			double distance = sink.distanceToSource;
			int capacity = e.getCap();
			UNode last = e.getFNode();

			String dest = last.getId();

			e = last.cameFrom;

			Stack<String> stack = new Stack<String>();
			stack.push(last.getId());

			while(e != null){

				if(e.getCap()<capacity)
					capacity = e.getCap();

				UNode nodeU = e.getFNode();
				UNode nodeV = e.getSNode();


				if(nodeU.getId().equals(last.getId())){
					stack.push(nodeV.getId());
					last = nodeV;
				}else{
					stack.push(nodeU.getId());
					last = nodeU;
				}

				e = last.cameFrom;

			}

			if(people<capacity)
				capacity = people;

			people -= capacity;

			e = sink.cameFrom;
			last = sink;

			while(e != null){

				e.changeCap(-capacity);
				UNode nodeU = e.getFNode();
				UNode nodeV = e.getSNode();

				if(nodeU.getId().equals(last.getId()))
					last = nodeV;
				else
					last = nodeU;

				e = last.cameFrom;
			}

			printPath(stack,capacity,dest,distance);
		}

		if(people>0)
			System.out.println(people+" personas sin asignar");

		return;
	}

	public void changeFloor(String id, Integer k){
		  this.getNode(id).changeW(k);
	}

}
