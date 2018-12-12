/*
* Jesus De Aguiar 15-10360
* Wilfredo Graterol 15-10639
* Proyecto III - CI2693 Sep-Dic 2018
*/

/**
* Clase abstacta de nodos
**/
public abstract class Node{


	private String id; // Identificador del nodo
	private String data; // Dato del nodo
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
	* Metodo utilizado para obtener el dato del nodo
	* @return dato del nodo
	**/
	public abstract String getData();
	/**
	* Metodo utilizado para obtener un string con informacion del nodo
	* @return string con la informacion del nodo
	**/
	public abstract String toString();

}
