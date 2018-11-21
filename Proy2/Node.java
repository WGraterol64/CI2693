import java.util.*;
/**
* Clase abstacta de nodos
**/
public abstract class Node{


	private String id; // Identificador del nodo
	private boolean data; // Dato del nodo
	private int weight; // Peso del nodo

	/**
	* Metodo utilizado para obtener el peso del nodo
	* @return peso del nodo
	**/
	public abstract Integer getWeight();
	/**
	* Metodo utilizado para obtener el identificador del nodo
	* @return identificador del nodo
	**/
	public abstract String getId();
	/**
	* Metodo utilizado para obtener el dato del nodo
	* @return dato del nodo
	**/
	public abstract int getData();
	/**
	* Metodo utilizado para obtener un string con informacion del nodo
	* @return string con la informacion del nodo
	**/
	public abstract String toString();

}
