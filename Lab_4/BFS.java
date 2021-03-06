import java.util.*;
import java.lang.Exception;

// Clase para buscar caminos hamiltonianos con BFS.
// Almacena el camino y parametros utiles para el algoritmo.
public class BFS{

	private Queue<Integer> elements; // Cola util para el algoritmo
	public int[] path; // Arreglo que almacena el camino
	public int[] distance; // Arreglo util para el algoritmo
	public int pos; // Entero que recorre las posiciones del arreglo donde se almacena el camino

	// Constructor de la clase
	public BFS(int n){

		this.elements = new LinkedList<>();
		this.path = new int[n];
		this.distance = new int[n];
		this.pos = 0;
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
				return false;
		
		return true;
	}

	private void bfs(int s, Graph graph){

		// Anadimos la fuente a la cola
		this.elements.add(s);
		// Inicializamos el arreglo de distancias
		Arrays.fill(distance,-1);
		// La distancia de la fuente a ella misma es 0
		this.distance[s] = 0;
		// Guardaremos el camino en las posiciones almacenadas por pos
		pos = 0;
		// Imprimimos un mensaje que anuncia que buscaremos un camino desde s
		System.out.print("Recorrido desde "+s+": \n");

		while(!elements.isEmpty()){

			// Retiramos un elemento de la cola
			int u = this.elements.poll();
			// Revisamos todos los vecinos de u
			for( int v : graph.list[u].adj){ 
				// Si no ha sido visitado, lo visito
				if(distance[v] == -1){
					// Imprimimos el nodo que visitamos
					System.out.print(u+"-"+v+"\n");
					// Almaceno el nodo en el camino y aumento el valor de pos
					this.path[pos++] = v;
					// Guardo la distancia del nodo a la fuente
					this.distance[v] = distance[u] + 1;
					// Encolamos el nodo
					this.elements.add(v);		
				}
				else{
					System.out.print(u+"-"+v+" Ya visitado\n");
				}
			}		
		}
	}
}