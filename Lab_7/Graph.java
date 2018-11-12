import java.util.*;
class Graph{

	public Vertex[] list; // Lista de vertices en el grafo
	private int n; // Numero de vertices del grafo
	private int[] predecessor; // Almacena el vertice predecesor a otro.
	private int[] ordinal; // Almacena el tiempo de visita de cada nodo
	private int[] cost;  // Almacena el costo de llegar a cada nodo
	/**
	* Constructor de la clase
	* @param n Numero de nodos del grafo
	**/
	public Graph(int n){
		this.n = n;
		this.list = new ArrayList<Vertex>();
		// Inicializacion de los vertices del grafo
		for(int i=0; i<n; i++){
			Vertex v = new Vertex(i);
			this.list.add(v);
		}
		// Arreglo de predecesores
		this.predecessor = new int[n];
		Arrays.fill(predecessor,-1);
	}

	//######################################################################################
		public void dijkstra(int s){
			predecessor.fillArray(-1);
			cost.fillArray(-1);
			cost[s] = 0;
			predecessor[s] = s;
			HashSet<Integer> Q = new HashSet();
			while(Q.size()!=0){
				int i = Q.poll();
				Vertex w = this.list.get(i);
				for(v=0; v< w.adj.size(); v++){
					for(e=0; e<w.inc.size(); e++){
						if(w.inc(e).nodeOne == v || w.inc(e).nodeTwo == v){
							if(cost[v]>cost[w]+w.inc(e).weight || cost[v]==-1){
								cost[v] = cost[w]+w.inc(e).weight;
								predecessor[v] = w;
							}
						}
					}
				}
			}
		}
	//######################################################################################
  public Integer minimum(int[] A){
		int x = A[0];
		int j = 0;
		for(i=0; i<A.size(); i++){
			if(A[i]<x){
				x = A[i];
				j = i;
			}
		}
		return j;
	}
}
