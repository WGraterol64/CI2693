import java.util.*;
class Graph{

	public ArrayList<Vertex> list; // Lista de vertices en el grafo
	private int n; // Numero de vertices del grafo
	private int[] distance; // Distancia de cada nodo a la raiz en el algoritmo BFS
	private int[] visited; // Arreglo de nodos visitados en el algoritmo DFS
	private int[] predecessor; // Almacena el vertice predecesor a otro.
	private int[] ordinal; // Almacena el tiempo de visita de cada nodo
	private int time; // Tiempo de descubrimiento de un nodo, aumenta cada vez que se realiza una visita
	private Queue<String> tree; // Cola que almacenara los strings si se quiere imprimir el arbol

	/**
	* Constructor de la clase
	* @param n Numero de nodos del grafo
	**/
	public Graph(int n){
		this.n = n; 
		this.list = new ArrayList<Vertex>(); 
		// Inicializacion de los vertices del grafo
		for(int i=0; i<n; i++){ 
			Vertex v = new Vertex(i);
			this.list.add(v);
		}
		// Arreglo de distancias para el BFS
		this.distance = new int[n];
		Arrays.fill(distance,-1);
		// Arreglo de predecesores 
		this.predecessor = new int[n];
		Arrays.fill(predecessor,-1);
		// Arreglo de colores para el DFS
		this.visited = new int [n];
		Arrays.fill(visited,-1);
		// Arreglo de ordinales
		this.ordinal = new int[n];
		Arrays.fill(ordinal,-1);
		// Cola util para imprimir el arbol
		this.tree = new LinkedList<String>();
	}

	/**
	* Recorrido bfs
	* @param source Fuente del recorrido
	* @param t Maxima profundidad del recorrido (-1 si no esta especificada)
	* @param ptree booleano que especifica si es necesario imprimir el camino
	**/
	private void bfs(int source, int t, boolean ptree){

		// Inicializamos el predecesor de la fuente como el mismo
		predecessor[source] = source;
		// El ordinal del source es siempre 0
		ordinal[source] = 0; 
		// Tiempo de descubrimiento en 1
		this.time = 1;
		//  La distancia a la fuente es 0
		distance[source] = 0;
		// Si hay que imprimir el arbol, agregamos el string a la cola
		if(ptree){
			String s = "Arbol:\n";
			s += String.valueOf(source)+"-"+String.valueOf(source)+" (raiz)";
			this.tree.add(s);
		}
		
		// Cola util para el recorrido de BFS
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source); // Agregamos la fuente

		while(!queue.isEmpty()){


			// Extraemos un elemento de la cola
			int u = queue.poll();
			
			// Revisamos si es necesario truncar
			if(distance[u]==t)
				break;

			// Para todos los vecinos
			for( int v : list.get(u).adj){


				// Si no estan visitados
				if(distance[v]==-1){
					// Actualizamos su distancia
					distance[v] = distance[u] + 1;
					predecessor[v] = u; // Marcamos a u como predecesor de v
					ordinal[v] = time++; // Guardamos el tiempo de descubrimiento de v
					queue.add(v); // Lo agregamos a la cola
					
					// Chequeamos si es necesario imprimir el arbol
					if(ptree){

						String s = "";
						for(int i=0; i<distance[v]; i++) // Calculamos la cantidad de espacios a dejar
							s += "  ";
						s += String.valueOf(u)+"-"+String.valueOf(v)+" (Arco de Camino)";
						this.tree.add(s);
					}

				}else{

					// Si ya esta visitado, solo nos interesa revisar si es necesario imprimir el arbol
					if(ptree){
						String s = "";
						for(int i=0; i<distance[u]+1; i++)
							s += "  ";
						s += String.valueOf(u)+"-"+String.valueOf(v)+" (Arco de Subida)";
						this.tree.add(s);
					}
				}
			}
		}
	}

	/**
	* Recorrido dfs
	* @param source Fuente del recorrido
	* @param t Maxima profundidad del recorrido (-1 si no esta especificada)
	* @param ptree booleano que especifica si es necesario imprimir el camino
	**/
	private void dfs(int source, int t, boolean ptree){
		// Reviscamos si es necesario imprimir el arbol, de ser asi agregamos el string a la cola
		if(ptree){
			String s = "Arbol:\n";
			s += String.valueOf(source)+"-"+String.valueOf(source)+" (raiz)";
			this.tree.add(s);
		}
		// Inicializamos el tiempo de descubrimiento en 0
		this.time = 0;
		// Marcamos a la fuente como su propio predecesor
		predecessor[source] = source;
		// Realizamos dfs recursivo
		dfs_rec(source,t,0,ptree);
	}

	/**
	* Recorrido dfs recursivo
	* @param u Vertice actual
	* @param t Maxima profundidad del recorrido (-1 si no esta especificada)
	* @param depth Profundidad actual del camino
	* @param ptree booleano que especifica si es necesario imprimir el camino
	**/
	private void dfs_rec(int u, int t, int depth, boolean ptree){
		
		// Agregamos el nodo al arreglo de visitados
		ordinal[u] = time++;
		// Marcamos el tiempo de descubrimiento de cada nodo
		// Chequeamos si es necesario truncar
		if(depth == t)
			return;
		
		// Marcamos el nodo como visitado, pero con un camino abierto
		visited[u] = 0;
		
		// Para todos los vecinos
		for( int v : list.get(u).adj){
			// Si no estan visitados
			if(visited[v]==-1){
				// Chequeamos si es necesario imprimir el arbol
				if(ptree){
					String s = "";
					for(int i=0; i<depth+1; i++)
						s += "  ";
					s += String.valueOf(u)+"-"+String.valueOf(v)+" (Arco de Camino)";
					this.tree.add(s);
				}
				// Marcamos a u como predecesor de v
				predecessor[v] = u;
				// Visitamos u
				dfs_rec(v,t,depth+1,ptree);
			}
			else{
				// Si ya esta visitado pero el camino esta abierto, es un arco de subida
				if(ptree && visited[v]==0){
					String s = "";
					for(int i=0; i<depth+1; i++)
						s += "  ";
					s += String.valueOf(u)+"-"+String.valueOf(v)+" (Arco de Subida)";
					this.tree.add(s);
				}
				// Si ya esta visitado pero el camino esta cerrado, es un arco cruzado
				if(ptree && visited[v]==1){
					String s = "";
					for(int i=0; i<depth+1; i++)
						s += "  ";
					s += String.valueOf(u)+"-"+String.valueOf(v)+" (Arco Cruzado)";
					this.tree.add(s);
				}

			}
		}
		// Cerramos el camino
		visited[u] = 1;
		return;
	}

	/**
	* Metodo para hallar la solucion al problema
	* @param algorithm Tipo de recorrido a realizar
	* @param source Fuente de recorrido
	* @param pred Booleano que especifica si se deben imprimir los sucesores
	* @param tree Booleano que especifica si se debe imprimir el arbol
	* @param ordinal Booleano que especifica si se deben imprimir los ordinales
	* @param trunc Entero que especifica donde truncar (-1 si no se debe truncar el recorrido)
	**/
	public void solve(int source, boolean algorithm, boolean printPred, boolean printTree, boolean printOrd, int trunc){

		// algorithm == true Indica que se debe realizar bfs
		if(algorithm){

			// Realizamos el recorrido
			bfs(source,trunc,printTree);
			// Chequeamos si queda alguna pagina sin visitar
			boolean found = false;
			for(int i=0; i<n; i++){
				if(distance[i] == -1){ // No ha sido visitada
					if(!found){
						found = true;
						System.out.print(i);
						continue;
					}
					if(found)
						System.out.print(","+i);
				}
			}
			// Si no se encuentra ninguna, imprimimos que visitamos todas
			if(!found)
				System.out.println("Todas las paginas son parte de la internet visible.");
			else
				System.out.print("\n");
		}
		else{
			// Realizamos el recorrido dfs
			dfs(source,trunc,printTree);

			// Chequeamos si queda alguno sin visitar
			boolean found = false;
			for(int i=0; i<n; i++){
				if(visited[i] == -1){ 
					if(!found){
						found = true;
						System.out.print(i);
						continue;
					}
					if(found)
						System.out.print(","+i);
				}
			}
			// Si no encontramos alguno sin visitar, imprimimos ese resultado.
			if(!found)
				System.out.println("Todas las paginas son parte de la internet visible.");
			else
				System.out.print("\n");
		}
		
		// Chequeamos si es necesario imprimir los predecesores
		if(printPred){
			System.out.println("Predecesores:");
			for(int i = 0; i<this.n; i++)
				System.out.println(i+": "+predecessor[i]);
			
		}
		// Chequeamos si debemos imprimir el arbol
		if(printTree){
			System.out.println("Arbol:");
			while(!this.tree.isEmpty())
				System.out.println(this.tree.poll());
			
		}
		// Chequeamos si debemos imprimir los ordinales
		if(printOrd){
			System.out.println("Ordinales:");
			for(int i = 0; i<this.n; i++)
				System.out.println(i+": "+ordinal[i]);
		}	
	}
	
}