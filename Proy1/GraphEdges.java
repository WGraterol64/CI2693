/**
*
* Clase abstacta que implementa los lados genericos de un grafo
*
**/
public abstract class GraphEdges<V,E>{

	private String id; // Identificador del lado
	private E data; // Dato que almacena el lado
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
	public abstract E getData();

	/**
	* Metodo utilizado para crear un String con informacion
	* sobre el lado
	*
	* @return string con la informacion del lado
	**/
	public abstract String toString();
}
