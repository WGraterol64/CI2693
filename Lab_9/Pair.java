/*
* Implementacion de la clase Pair,
* util para nuestra implementacion de Dijkstra.
**/
public class Pair{
	
	public double cost; // Costo de llegar al vertice vertex
	public int vertex;

	/**
	* Constructor de la clase
	* @param cost Costo de llegar a v
	* @param v Vertice
	**/
	public Pair(double cost, int v){
		this.cost = cost;
		this.vertex = v;
	}
}