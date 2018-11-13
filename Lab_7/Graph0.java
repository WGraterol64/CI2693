import java.util.*;
class Graph0{

	public double[][] g; // Matriz de adyacencias
	public int n; // Numero de vertices del grafo
	public int[] predecessor; // Almacena el vertice predecesor a otro.
	public double[] cost;  // Almacena el costo de llegar a cada nodo
	/**
	* Constructor de la clase
	* @param n Numero de nodos del grafo
	**/
		public Graph0(int n){
			this.n = n;
			this.g = new double[n][n];
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					this.g[i][j] = Double.NaN;
				}
			}
			this.predecessor = new int[n];
			this.cost = new double[n];
		}

		public void addVertex(int u, int v, double w){
			this.g[u][v] = w;
			this.g[v][u] = w;
		}

		public void dijkstra(int s)
		throws IllegalArgumentException{
			Arrays.fill(predecessor, -1);
			Arrays.fill(cost, Double.POSITIVE_INFINITY);
			try{
				cost[s] = 0;
				predecessor[s] = s;
			}catch(ArrayIndexOutOfBoundsException e){
				throw new IllegalArgumentException("El vertice no esta en el grafo");
			}
			HashSet<Integer> closed = new HashSet<Integer>();
			while(closed.size() != n){
				int w = Integer.MIN_VALUE;
				double x = Double.POSITIVE_INFINITY;
				for(int k=0;k<n;k++){
					if(cost[k]<x && !closed.contains(k)){
						w = k;
						x = cost[k];
					}
				}
				closed.add(w);
				for(int v=0; v < n; v++){
					if(g[w][v]!=Double.NaN){
						if(cost[v] > cost[w] + g[w][v]){
							cost[v] = cost[w] + g[w][v];
							predecessor[v] = w;
						}
					}
				}
			 }
			}
}
