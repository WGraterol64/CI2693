import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;	
/*
* Implementacion de la clase grafo
*/
class Graph{

	public int n; // Numero de vertices del grafo
	public int m; // Numero de aristas del grafo
	public int cost; // Sumatoria de los costos de todas las aristas del grafo
	public ArrayList<Edge> edges; // Almacena las aristas del grafo
	public int[] parent; // Arreglo util para el algoritmo de kruskal, 
						 // almacena el representante del conjunto al que pertenece cada nodo.
	public int[] rank;   // Arrego util para el algoritmo de kruskal, 
						 // almacena el rank del conjunto al que peretenece cada nodo.

	/**
	* Constructor de la clase
	* @param n Numero de nodos del grafo
	* @param m Numero de lados
	**/
	public Graph(int n, int m){
		this.n = n;
		this.m = m;
		this.cost = 0; 
		this.edges = new ArrayList<Edge>();
	}

	/**
	* Metodo para inicializar agregar un lado
	* entre dos nodos
	* @param x nodo1 de la arista
	* @param y nodo2 de la arista
	* @param z costo de la arista
	**/
	public void addEdge(int x, int y, int z){
    	
    	Edge edge = new Edge(x,y,z);
		this.cost += z;
		this.edges.add(edge);
	}

	/**
	* Implementacion de la funcion find de los disjoint set
	* @param v Vertice cuyo representante se buscara
	* @return El representante del nodo.
	**/
	public int find(int v){
		// Si v es su propio padre, el es el representante
		if(parent[v] == v)
			return v;
		// Si no, su padre sera el find de su padre
		return parent[v] = find(parent[v]);
	}

	/**
	* Implementacion de la funcion union de los disjoint set
	* @param x primer representante de una componente a unir.
	* @param y representante de otra componente a unir.
	**/
	public void union(int x, int y){
		// En x mantenemos el de mayor ranking
		if(rank[x] < rank[y]){
			int temp = x;
			x = y;
			y = temp;
		}
		// Asignamos x como representande de y
		parent[y] = x;
		// Util para las optimizaciones de kruskal.
		if(rank[x] == rank[y])
			rank[x]++;
	}

	/**
	* Algoritmo de Kruskal para calcular el costo de un arbol minimo cobertor
	* Disminuimos en el grafo el costo del minimo cobertor. Este sera la cantidad ahorrada.
	* @return el costo total de un arbol minimo cobertor del grafo
	**/
	public int kruskal(){

		// Ordenamos las aristas del grafo de manera decreciente por el peso
		Collections.sort(edges, new edgeComparator());
		// Inicializamos los arreglos necesarios para el algoritmo 
		this.parent = new int[this.n];
		this.rank = new int[this.n];
		for(int i = 0; i<this.n; i++){
			parent[i] = i;
			rank[i] = 0;
		}
		// Establecemos el numero de componentes conexas en N
		int conected_components = this.n;
		int i = 0;
		// Realizamos el ciclo
		while(conected_components > 1 ){
			// Extraemos un edge
			Edge edge = this.edges.get(i++);
			int x = find(edge.vertex1);
			int y = find(edge.vertex2);
			// Si no pertenecen a la misma componente, los unimos
			if(x!=y){
				union(x,y);
				this.cost -= edge.weight;
				conected_components--;
			}
		}
    	return this.cost;
	}
}
