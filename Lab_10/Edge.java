public class Edge{

	public int vertex1; // First node
	public int vertex2; // Second node
	public int weight; // weight of the edge

	/**
	* Constructor de la clase
	* @param u Primer vertice de la arista
	* @param v Segundo vertice de la arista
	* @param weight peso de la arista
	**/
	public Edge(int u, int v, int weight){
		this.vertex1 = u;
		this.vertex2 = v;
		this.weight = weight;
	}
}
