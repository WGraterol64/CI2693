import java.util.*;
/**
* Clase abstacta de nodos
**/
public abstract class Node<V,E>{


	private String id; // Identificador del nodo
	private V data; // Dato del nodo
	private double weight; // Peso del nodo

	/**
	* Metodo utilizado para obtener el peso del nodo
	**/
	public abstract double getWeight(); 
	/**
	* Metodo utilizado para obtener el identificador del nodo
	**/
	public abstract String getId();
	/**
	* Metodo utilizado para obtener el dato del nodo
	**/
	public abstract V getData();
	/**
	* Metodo utilizado para obtener un string con informacion del nodo
	**/
	public abstract String toString();

}