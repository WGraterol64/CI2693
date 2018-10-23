/**Almacena un grafo que puede crecer din&aacute;micamente para prop&oacute;sitos
 * de traduci&oacute;n a Matriz de Adyacencias. Esta clase est&aacute; pensada para ser
 * usada al leer grafos en formato Lista de Adyacencias desde un archivo.
 */
import java.util.Vector;
import java.util.Arrays;

public class TraductorDesdeLista extends TraductorGrafo{
	
	public Vector<Integer> vertices;
	public Vector<Vector<Integer> > list;
	protected int[][] grafo;

		/**Crea un grafo minimal*/
	TraductorDesdeLista(){
		
		vertices = new Vector<Integer>(10,1);
		list = new Vector<Vector<Integer> >(10,1);
		
	}
	
	/**Agrega un v&eacute;rtice al grafo. Si el v&eacute;rtice ya existe, el m&eacute;todo no hace
	 * nada.
	 * 
	 * @param id El n&uacute;mero del v&eacute;rtice que se desea agregar
	 * @return Indice del vertice en la lista
	 */
	public int agregarVertice(int id){

		int s = vertices.size();
		for(int i = 0; i<s; i++){
			if(vertices.get(i)==id)
				return i;
		}
		int index = list.size();
		Vector<Integer> newVertex = new Vector<Integer>(10,1);
		list.add(newVertex);
		vertices.add(id);
		return index;
	}
	
	/**{@inheritDoc}**/
	public void agregarArco(int verticeInicial, int verticeFinal){
		list.elementAt(verticeInicial).add(verticeFinal);
	}
	
	/**{@inheritDoc}**/
	public String imprimirGrafoTraducido(){

		int s = vertices.size();
		System.out.print("   ");
		for(int i=0; i<s; i++)
			System.out.print(i+" ");
		System.out.print("\n");
		for(int i=0; i<2*s+2; i++)
			System.out.print("-");
		System.out.print("\n");
		for(int i=0; i<s; i++){
			System.out.print(i+" | ");
			for(int j=0; j<s; j++)
				System.out.print(this.grafo[i][j]+" ");
			System.out.print("\n");
		}
		if(grafoNoDirigido()){
				return "El grafo es no dirigido!";
		}else{
			return "El grafo es dirigido...";
		}
	}

	public boolean grafoNoDirigido(){
		int s = vertices.size();
		for(int i=0; i<s; i++){
			for(int j=0; j<s; j++){
				if(this.grafo[i][j] != this.grafo[j][i]){
					return false;
				}
			}
		}
		return true;
	}

	public void fillArray(){ 

		int s = vertices.size();
		this.grafo = new int[s][s];		
		for(int i = 0; i<s; i++)
			for(int j = 0; j<s; j++)
				this.grafo[i][j] = 0;

		for(int i = 0; i<s; i++){
			int x = vertices.get(i);
			Vector<Integer> row = list.get(i);
			for(int j = 0; j<row.size(); j++){
				int y = row.get(j);
				this.grafo[x][y] += 1;
			}
		}
	}
}