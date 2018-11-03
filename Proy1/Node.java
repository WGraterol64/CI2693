import java.util.*;

/**
* Clase de Nodos
**/
public abstract class Node<V,E>{


	private String id; 
	private V data;
	private double weight;

	public abstract double getWeight();

	public abstract String getId();

	public abstract V getData();

	public abstract String toString();

}