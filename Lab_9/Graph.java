import java.util.*;
import java.lang.Math;
import java.lang.Double;
/*
* Implementacion de la clase grafo
*/
class Graph{

	public ArrayList<Vertex> list; // Lista de vertices en el grafo
	public int n; // Numero de vertices del grafo
	public int[] predecessor; // Almacena el vertice predecesor a otro.
	public double[] cost; // Almacena el costo de llegar desde la fuente a cada vertice

	/**
	* Constructor de la clase
	* @param n Numero de nodos del grafo
	**/
	public Graph(int n){

		this.n = n;
		this.list = new ArrayList<Vertex>();
		// Arreglo de predecesores
		this.predecessor = new int[n];
		Arrays.fill(predecessor,-1);
		// Arreglo de costos
		this.cost = new double[n];
		Arrays.fill(cost,Double.POSITIVE_INFINITY);
	}

	/**
	* Metodo para inicializar y agregar un nuevo
	* vertice a la lista del grafo
	* @param x Coordenada x del nuevo vertice
	* @param y COordenada y del nuevo vertice
	**/
	public void addVertex(int x, int y){

		Vertex vert = new Vertex(x,y);
		this.list.add(vert);
	}

	/**
	* Metodo para inicializar agregar un lado
	* entre dos nodos
	* @param u posicion de uno de los vertices en la lista del grafo
	* @param v posicion del otro vertice n la lista del grafo
	**/
	public void addEdge(int u, int v){

		this.list.get(v).addAdj(u);
		this.list.get(u).addAdj(v);
	}

	/**
	* Metodo utilizado para calcular el costo del lado entre dos vertices
	* @param u posicion de uno de los vertices en la lista del grafo
	* @param v posicion del otro vertice n la lista del grafo
	*/
	public double getCost(Vertex u, Vertex v){
		double x1 = (double) u.x;
		double y1 = (double) u.y;
		double x2 = (double) v.x;
		double y2 = (double) v.y;

		double x = (x1-x2);
		double y = (y1-y2);
		x *= x;
		y *= y;

		return Math.sqrt(x+y);

	}

	/**
	* Algoritmo de Dijkstra
	* Utilizado para encontrar los caminos minimos desde la fuente s
	* @param s Fuente desde la que inciara el algoritmo
	* @throws IllegalArgumentException si s no esta en el grafo
	**/
	public int[] dijkstra(int s)
	throws IllegalArgumentException{
   		
   		if(s>=n)
		    throw new IllegalArgumentException("El vertice no esta en el nodo");

		cost[s] = 0; // El costo de llegar a la fuente es 0
		predecessor[s] = s; // Condicion de parada, s es su propio predecesor

		// Inicializamos un comparador de pares, y una priority queue (Heap)
		// que ordenara los elementos bajo este comparador.
		Comparator<Pair> comparator = new PairComparator();
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>(this.n,comparator);

		// Inicializamos un par <0,s> y lo agregamos a la cola
		Pair p = new Pair(0,s);
		pq.add(p);
		// Inicialiacion de contadores de caminos abiertos y cerrados
		int cerrados = 0;
		int abiertos = 1;

		while(!pq.isEmpty()){

			// Extraemos un par de la cola
			Pair pp = pq.remove();
			// Aumentar el numero de caminos cerrados en el recorrido
			cerrados ++;
			// Extraemos el identificador del vertice y el costo de llegar a el
			int u = pp.vertex;

			double costU = pp.cost;

			if(cost[u] != costU) // Revisamos si nos encontramos en el estado optimo hasta ahora
				continue;

			// Para todos los vecinos de u
			for( int v : this.list.get(u).adj){
				// Calculamos el costo del lado entre u y v
				double costUV = getCost(list.get(u),list.get(v));
				// Si el costo de llegar a u + el costo entre u y v es menor que el costo
				// minimo actual de llegar a v, entonces actualizamos el costo de v y
				// lo agregamos a la cola para ser analizado luego.
				if(costU + costUV < cost[v]){
					cost[v] = costU + costUV;
					// Aumentar el numero de caminos abiertos
        			abiertos ++;
					Pair pv = new Pair(cost[v],v);
					predecessor[v] = u; // Marcamos a u como predecesor de v
					pq.add(pv);
				}
			}
		}

		int[] results = new int[2];
		results[0] = abiertos;
		results[1] = cerrados;
		return results;
	}

	public int[] AStar(int s, int f)
	throws IllegalArgumentException{
   		
   		if(s>=n)
		    throw new IllegalArgumentException("El vertice no esta en el nodo");
		
		cost[s] = 0; // El costo de llegar a la fuente es 0
		predecessor[s] = s; // Condicion de parada, s es su propio predecesor


		// Inicializamos un comparador de pares, y una priority queue (Heap)
		// que ordenara los elementos bajo este comparador.
		Comparator<Pair> comparator = new PairComparator();
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>(this.n,comparator);

		// Inicializamos un par <0,s> y lo agregamos a la cola
		Pair p = new Pair(0,s);
		pq.add(p);
		int cerrados = 0;
		int abiertos = 1;

		while(!pq.isEmpty()){

			// Extraemos un par de la cola
			Pair pp = pq.remove();
			// Aumentar el numero de caminos cerrados en el recorrido
			cerrados ++;
			// Extraemos el identificador del vertice y el costo de llegar a el
			int u = pp.vertex;
			if(u == s)
					break;

			double costU = pp.cost;

			if(cost[u] != costU) // Revisamos si nos encontramos en el estado optimo hasta ahora
				continue;

			// Para todos los vecinos de u
			for( int v : this.list.get(u).adj){

				// Calculamos el costo del lado entre u y v
				double costUV = getCost(list.get(u),list.get(v));
				// Si el costo de llegar a u + el costo entre u y v es menor que el costo
				// minimo actual de llegar a v, entonces actualizamos el costo de v y
				// lo agregamos a la cola para ser analizado luego.
				if(costU + costUV < cost[v]){
					cost[v] = costU + costUV;
					// Aumentar el numero de caminos abiertos
      				abiertos ++;
					Pair pv = new Pair(cost[v]+getCost(list.get(v), list.get(s)),v);
					predecessor[v] = u; // Marcamos a u como predecesor de v
					pq.add(pv);
				}
			}
		}

		int[] results = new int[2];
		results[0] = abiertos;
		results[1] = cerrados;
		return results;
	}


}
