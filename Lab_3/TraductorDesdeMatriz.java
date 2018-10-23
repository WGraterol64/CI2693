/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */

import java.util.Vector;

public class TraductorDesdeMatriz extends TraductorGrafo{
  // atributos de la clase
	int [][] grafo; // Matriz de incidencias
	int vertices; // Numero de vertices
	Vector<Integer> pos;  //Arreglo auxiliar

	/**Crea un grafo con el n&uacute;mero de v&eacute;rtices dado
	 *
	 * @param vertices El n&uacute;mero de v&eacute;rtices del grafo
	 */
	TraductorDesdeMatriz(int vertices){
	  	this.vertices = vertices;
		this.grafo = new int [vertices][vertices];
	}

	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal){
		this.grafo[verticeInicial][verticeFinal] = 1;
	}

	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido(){
		System.out.println("El grafo traducido es: ");
		for (int i = 0; i < this.grafo.length; i++) {
		    for (int j = 0; j < this.grafo[i].length; j++) {
		        System.out.print(this.grafo[i][j] + " ");
		    }
		    System.out.println();
			}
			if(grafoNoDirigido()){
				return "El grafo es no dirigido!";
			}else{
				return "El grafo es dirigido...";
			}
	}

	// Funcion extra para evaluar si el grafo es no dirigido

	public boolean grafoNoDirigido(){
		int i = 0;
		while(i<this.grafo.length){
			int j = i;
			while(j<this.grafo[i].length){
				if(this.grafo[i][j] != this.grafo[j][i]){
					return false;
				}
			}
		}
		return true;
	}
	// Vector auxiliar para mantener el orden cuando el input esta desordenado
	public void addpos(int x){
		this.pos.add(x);
	}

	// Metodo para leer un elemento en una casilla del arreglo
	public int getpos(int x){
		int y = this.pos.get(x);
		return y ;
	}
}