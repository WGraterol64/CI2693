import java.util.*

// Clase para buscar caminos hamiltonianos con BFS.
// Almacena el camino y parametros utiles para el algoritmo.
public class BFS{

	private Queue<Integer> elements; // Cola util para el algoritmo
	public int[] path; // Arreglo que almacena el camino
	public int[] distance; // Arreglo util para el algoritmo
	public int pos; // Entero que recorre las posiciones del arreglo donde se almacena el camino

	// Constructor de la clase
	public void BFS(int n){

		this.elements = new Queue<Integer>();
		this.path = new int[n];
		this.distance = new int[n];
		this,pos = 0;
	}

	// Metodo que verifica si el camino recorrido desde el nodo s,
	// es un camino hamiltoniano.
	public boolean findPath(int s,Graph graph){

		// Realizamos un bfs
		bfs(s, graph);
		// Si no recorremos todos los nodos, el camino no es hamiltoniano
		if(pos != graph.size) 
			return false;
		// Si existe una bifurcacion en el camino, el camino no es hamiltoniano
		for(int i = 0; i<pos-1; i++)
			if(distance[i] == distance[i+1]) 
				return false
		
		return true;
	}

	public void bfs(int s, Graph graph){

		// Anadimos la fuente a la cola
		elements.add(s);
		// Inicializamos el arreglo de distancias
		Arrays.fill(distance,-1);
		// La distancia de la fuente a ella misma es 0
		distance[s] = 0;
		// Guardaremos el camino en las posiciones almacenadas por pos
		pos = 0;

		while(!elements.isEmpty()){

			// Retiramos un elemento de la cola
			int u = elements.poll();
			// Revisamos todos los vecinos de u
			for( v : graph[u].adj){ 
				// Si no ha sido visitado, lo visito
				if(d[v] == -1){
					// Almaceno el nodo en el camino y aumento el valor de pos
					path[pos++] = v;
					// Guardo la distancia del nodo a la fuente
					distance[v] = distance[u] + 1;
					// Encolamos el nodo
					elements.add(v);		
				}	
			}		
		}
	}
}