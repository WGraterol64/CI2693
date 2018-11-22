import java.util.*;
/**
* Clase abstacta de nodos
**/
public abstract class Node{


	private String id; // Identificador del nodo
	private int cap; // Capacidad del nodo
	private int weight; // Peso del nodo

	/**
	* Metodo utilizado para obtener el peso del nodo
	* @return peso del nodo
	**/
	public abstract int getWeight();
	/**
	* Metodo utilizado para obtener el identificador del nodo
	* @return identificador del nodo
	**/
	public abstract String getId();
	
	/**
	* Metodo utilizado para obtener la capacidad del nodo
	* @return capacidad del nodo
	**/
	public abstract int getCap();

}
