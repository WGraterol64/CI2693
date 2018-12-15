import java.util.Comparator;
/**
* Comparador de aristas, util para nuestra implementacion de Kruskal
**/
public class edgeComparator implements Comparator<Edge> {

	// Comparamos dos aristas por el costo
	@Override
	public int compare(Edge e1, Edge e2){

		if(e1.weight < e2.weight)
			return -1;
		else if(e2.weight < e1.weight)
			return 1;
		return 0;
	}
}
