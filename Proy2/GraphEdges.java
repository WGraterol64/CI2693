/**
*
* Clase abstacta que implementa los lados genericos de un grafo
*
**/
public abstract class GraphEdges{

	private String id; // Identificador del lado
	private int cap; // Capacidad del lado
	private double weight; // Peso del lado

	/**
	* Metodo utilizado para obtener el peso del lado
	*
	* @return peso del lado
	**/
	public abstract double getWeight();

	/**
	* Metodo utilizado para obtener el identificador del lado
	*
	* @return identificador del lado
	**/
	public abstract String getId();

	/**
	* Metodo utilizado para obtener el dato del lado
	*
	* @return dato especial del lado
	**/
	public abstract int getCap();

}
