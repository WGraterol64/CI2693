import java.util.*;
public class Vertex{

	public ArrayList<Integer> adj; // Lista de adyacentes
	public ArrayList<Edges> inc;
	public int x;
	public int y;

	/**
	* Constructor de la clase
	* @param id Entero identificador del vertice
	**/
	public Vertex(int x, int y){
		this.adj = new ArrayList<Integer>();
		this.inc = new ArrayList<Edges>();
		this. x = x;
		this.y = y:
	}
}
