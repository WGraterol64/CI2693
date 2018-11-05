import java.util.*;
/**
* Clase Grafo. Implementa una estructura que almacena
* el grafo de posibles vecinos para cada habitacion
* y el grafo de luces que se pueden encender desde cada habitacion.
* Con la informacion de estos grafos, realiza un algoritmo
* de backtracking sobre los posibles estados del problema.
* Para ello, realiza un recorrido con BFS sobre un grafo
* abstracto que representa los distintos estados.
**/
class Graph{

	public ArrayList<Vertex> list; // Lista de vertices en el grafo
	private int r; // Numero de vertices del grafo
	private int[][] distance; // Distancia de un estado al estado inicial
	private Pair[][] predecessor; // Almacena el estado predecesor a un estado

	/**
	* Constructor de la clase
	* @param n Numero de nodos del grafo
	**/
	public Graph(int n){
		this.r = n; 
		this.list = new ArrayList<Vertex>(); 
		// Inicializacion de los vertices del grafo
		for(int i=0; i<n; i++){ 
			Vertex v = new Vertex(i);
			this.list.add(v);
		}
	}

	/**
	* Metodo que agrega una arista entre los vertices u y v.
	* Esto representa que se puede llegar a la habitacion u
	* desde la habitacion v y viceversa. Se implementa agregando
	* v a los cuartos adyacentes a u, y u a los cuartos adyacentes a v
	* @param u Uno de los vertices vecinos
	* @param v Uno de los vertices vecinos
	**/
	public void addRoomTransition(int u, int v){

		this.list.get(u).addRoom(v);
		this.list.get(v).addRoom(u);
	}

	/**
	* Metodo que agrega un arco desde el vertice u hasta el vertice v en
	* el grafo de encendedores. Esto representa que desde la habitacion
	* u se puede encender la luz de la habitacion v. Se implementa
	* agregando v a la lista de cuartos que se pueden encender desde u.
	* @param u vertice inicial del arco
	* @param v vertice final del arco
	**/
	public void addSwitchTransition(int u, int v){

		this.list.get(u).addSwitch(v);
		this.list.get(v).addSwitch(u);
	}

	/** 
	* Algoritmo para resolver el problema. El algoritmo utiliza el recorrido
	* de BFS para pasearse sobre los distintos estados del problema. 
	* Cada estado se representa con un vertice v y una mascara de bits m.
	* m es un entero donde, si el iesimo bit (izqd - der) esta encendido, entonces la 
	* (i+1)esima habitacion tiene la luz encendida. De esta manera, podemos hacer transiciones
	* desde una habitacion con una configuracion de luces, a la misma habitacion
	* con una configuracion distinta de luces, o de una habitacion a otra con la
	* misma configuracion de luces. Para realizar estas transiciones, utilizamos
	* los grafos almacenados que representan los caminos entre habitaciones y
	* las luces que se pueden encender desde cada habitacion. Para realizar ciertas
	* verificaciones, se usan operaciones sobre los bits de las mascaras.
	* @return Entero que cuenta la cantidad minima de pasos para llegar a la solucion (-1 si es imposible)
	**/
	public int backtracking(){

		this.predecessor = new Pair[r][(1<<r)]; // Creamos el arreglo de predecesores
		this.distance = new int[r][(1<<r)]; // Creamos el arreglo de distancias
		for( int[] row : distance)
			Arrays.fill(row,-1); // Inicializamos las distancias en -1 (no visitado)
		
		/*  El vertice inicial es siempre 0.
			El estado inicial es todas las luces apagadas 
		    excepto por la habitacion 0. Este estado se 
		    representa con la mascara de bits 1 
		    (el primer bit de derecha a izquierda encendido)  */

		Pair p = new Pair(0,1); // Marcamos el estado inicial como su propio predecesor.
		predecessor[0][1] = p; // Esto sera util para imprimir el resultado
		distance[0][1] = 0; // La distancia al estado inicial es cero.
		bfs(0); // Realizamos el recorrido
		
		// Retornamos el numero minimo de pasos necesarios para llegar a la solucion (-1 si es imposible)
		return distance[r-1][(1<<(r-1))];
	}

	public void bfs(int r){

		// Cola para el BFS
		Queue<Pair> queue = new LinkedList<>();
		Pair source = new Pair(r,(1<<r)); // Inicio del recorrido
		queue.add(source); // Agregamos a la cola
		while(!queue.isEmpty()){

			Pair p = queue.poll(); // Extraemos un elemento de la cola
			int u = p.first; // Vertice actual
			int mask = p.second; // Configuracion de luces actual

			// Para todos las habitaciones cuyas luces puedo cambiar
			for(int v : list.get(u).lSwitch){
				
				int newMask = mask ^ (1<<v); // newMask representa la posible nueva configuracion de luces
				
				// Si el estado no ha sido revisado, lo revisamos
				if(distance[u][newMask]==-1){ 
					distance[u][newMask] = distance[u][mask] + 1; // Actualizamos la distancia
					Pair pp = new Pair(u,newMask);
					queue.add(pp); // Agregamos el nuevo estado a la cola
					predecessor[u][newMask] = p; // Actualizamos el predecesor del estado
				}
			}

			// Luego, para todas las habitaciones a las que se puede llegar desde u
			for(int v : list.get(u).rooms){
				// Revisamos si es posible llegar, esto es:
				// Si no ha sido visitado y la luz de esa habitacion esta encendida
				if( ((mask & (1<<v))!=0) && (distance[v][mask]==-1)){
					distance[v][mask] = distance[u][mask] + 1; // Visitamos la habitacion y actualizamos la distancia
					Pair pp = new Pair(v,mask);
					queue.add(pp); // Agregamos el siguiente estado a la cola
					predecessor[v][mask] = p; // Actualizamos el predecesor del estado
				}
			}			
		}
	}

	public void printSol(){

		// Stack para imprimir la solucion en orden
		Stack<String> stack = new Stack<String>();
		// Estado final del problema
		int u = r-1;
		int mask = (1<<(r-1));

		while( u!=0 || mask!=1 ){

			// Extraemos el estado anterior
			int v = predecessor[u][mask].first;
			int pMask = predecessor[u][mask].second;

			// v==u, por lo tanto en la transicion cambio la configuracion de luces
			if(u==v){

				// Revisamos cual luz cambio
				int dif = mask ^ pMask;
				int m=0;
				while(dif != 1){
					dif = (dif>>1);
					m++;
				}

				// Si ahora esta encendida, es porque fue encendida
				if(((1<<m) & mask)!=0){
					String s = "- ";
					s += "Enciende la luz en la habitacion ";
					s += String.valueOf(m+1);
					s += ".";
					stack.push(s);
				}else{

					// Fue apagada 
					String s = "- ";
					s += "Apaga la luz en la habitacion ";
					s += String.valueOf(m+1);
					s += ".";
					stack.push(s);
				}
			}else{

				// u != v, por lo tanto nos movimos de habitacion
				String s = "- ";
					s += "Muevete a la habitacion ";
					s += String.valueOf(u+1);
					s += ".";
					stack.push(s);
			}

			u = v;
			mask = pMask;
		}

		// Imprimimos el recorrido en orden
		while(!stack.empty()){

			String s = stack.pop();
			System.out.println(s);
		}

	}
}