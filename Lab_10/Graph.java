import java.util.*;
import java.lang.Math;
import java.lang.Double;
/*
* Implementacion de la clase grafo
*/
class Graph{

	public int[] nodes; // Arreglo de vertices en el grafo
	public int n; // Numero de vertices del grafo
	public int m; // Numero de aristas del grafo
	public int cost; // Costo de construir todos los caminos
	public HashSet<Edge> edges; // Almacena el costo de llegar desde la fuente a cada vertice

	/**
	* Constructor de la clase
	* @param n Numero de nodos del grafo
	* @param m Numero de lados
	**/
	public Graph(int n, int m){
		this.n = n;
		this.m = m;
		this.cost = 0;
		this.nodes = new int[n];
		this.edges = new HashSet<Edge>(m);
	}

	/**
	* Metodo para inicializar agregar un lado
	* entre dos nodos
	* @param x nodo1 de la arista
	* @param y nodo 2 de la arista
	* @param z costo de la arista
	**/
	public void addEdge(int x, int y, int z){
     Edge e = new Edge(x,y,z);
		 this.cost += z;
		 this.edges.add(e);
	}


	/**
	* Algoritmo de Kruskal para calcular el costo de un arbol minimo cobertor
	* @return el costo total de un arbol minimo cobertor del grafo
	**/
	public int kruskal(){

		int ncost = 0;
		DisjointSets cc = new DisjointSets(this.n);
		Comparator<Edge> comparator = new edgeComparator();
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(this.m,comparator);
		for(Edge e : this.edges)
			pq.add(e);
		int nc = this.n;
		while(nc>1){
			Edge e = pq.poll();
			int x = cc.find(e.u);
			int y = cc.find(e.v);
			if(x!=y){
				cc.join(x,y);
				ncost += e.weight;
				nc-=1;
			}
		}
    return ncost;
	}
}
