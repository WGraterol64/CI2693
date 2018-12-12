/**
*
* Clase Arc, representa los arcos en un grafo dirigido.
* Extiende la clase abstracta GraphEdges
*
**/
public class Arc extends GraphEdges{


	private String id;  // Identificador del arco
	private String data;  // Dato almacenado por el arco
	private double weight;  // Peso del arco
	private	DNode initNode; // Nodo inicial en el arco
	private DNode endNode; // Nodo final del arco

	/**
	* Constructor de la clase
	* @param id Identificador del arco a crear
	* @param data Dato almacenado por el arco
	* @param weight Peso del arco a crear
	* @param initNode Nodo inicial del arco
	* @param endNode Nodo final del arco
	**/
	public Arc(String id, String data, double weight, DNode initNode, DNode endNode){
		this.id = id;
		this.data = data;
		this.weight = weight;
		this.initNode = initNode;
		this.endNode = endNode;
	}

	/**
	* Metodo utilizado para obtener el peso del arco
	**/
	public double getWeight(){
		return this.weight;
	}

	/**
	* Metodo utilizado para obtener el identificador del arco
	**/
	public String getId(){
		return this.id;
	}

	/**
	* Metodo utilizado para obtener el dato del arco
	**/
	public String getData(){
		return this.data;
	}

	/**
	* Metodo utilizado para crear un String con informacion
	* sobre el arco
	**/
	public String toString(){

		String edge = " ";
		edge += "Id del arco: "+this.getId()+"\n";
		edge += "Dato: "+String.valueOf(this.getData()) + "\n";
		edge += "Peso: "+String.valueOf(this.getWeight()) + "\n";
		edge += "Este arco conecta los siguientes vertices:\n";
		edge += "Inicial: \n"+this.getInitNode().toString();
		edge += "Final: \n"+this.getEndNode().toString();
		return edge;
	}

	/**
	* Metodo que devuelve el nodo inicial del arco
	* @return Nodo inicial del arco
	**/
	public DNode getInitNode(){
		return this.initNode;
	}

	/**
	* Metodo que devuelve el nodo final del arco
	* @return Nodo final del arco
	**/
	public DNode getEndNode(){
		return this.endNode;
	}

}
