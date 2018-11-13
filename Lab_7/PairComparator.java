import java.util.*;
/**
* Comparador de pares, util para nuestra implementacion de Dijkstra
**/
public class PairComparator implements Comparator<Pair> {

	// Comparamos dos pares por el costo
	@Override
	public int compare(Pair p1, Pair p2){

		if(p1.cost < p2.cost)
			return 1;
		else if(p1.cost < p1.cost)
			return -1;

		return 0;
	}
}