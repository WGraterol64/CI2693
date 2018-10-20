import java.io.Exception;
import java.io.BufferedReader;
import java.io.FileReader;

public class Hamilton{

	public Graph graph;

	public Graph loadGraph(String fileName)
		throws UnsupportedOperationException
	{	
		// Variables que utilizaremos para crear el grafo
		int n, m, x, y;
		// Declaramos un lector de buffer
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		// Leemos la primera linea, que almacena el entero n
		String line = reader.readLine().trim();
		try{
			n = Integer.parseInt(line);
		}catch(ParseException e){
			throw new UnsupportedOperationException("Entrada no valida");
		}
		// Leemos la segunda linea, que almacena el entero m
		line = reader.readLine().trim();
		try{
			m = Integer.parseInt(line);
		}catch(ParseException e){
			throw new UnsupportedOperationException("Entrada no valida");
		}
		// Declaramos el grafo
		Graph returnGraph = new Graph(n);
		// Cargamos los arcos
		for(int i = 0; i<m; i++){
			line = reader.readLine().trim();
			String[] input = line.split(" ");
			try{
				x = Integer.parseInt(input[0]);
				y = Integer.parseInt(input[1]);
			}catch(ParseException e){
				throw new UnsupportedOperationException("Entrada no valida");
			}
			try{
				returnGraph.addAdj(x,y);
			}catch(UnsupportedOperationException e){
				throw new UnsUnsupUnsupportedOperationException("Entrada no valida");
			}
		}

		return returnGraph;
	}

	public static void main(String[] args)
		throws UnsupportedOperationException;
	{	
		// Debemos recibir al menos dos argumento
		if(args.length < 2){
			System.err.println("Uso: java Hamilton <nombreArchivo> <BFS/DFS>");
			return;
		}

		// Intentamos cargar el grafo
		try{
			this.graph = loadGraph(args[0]);
		}catch(UnsupportedOperationException e){
			System.err.println("El grafo leido no es valido");
		}


		// Buscamos los caminos segun el algoritmo solicitado
		if(args[1]=="DFS"){

			DFS dfsPath = new DFS

		}else if(args[1]=="BFS"){

			// Creamos una instancia de la clase BFS
			BFS bfsPath = new BFS(graph.size);
			boolean found = false;
			// Buscamos un camino desde cada nodo
			for(int u = 0; u < graph.size && !found; u++){
				found = bfsPath.findPath(u,graph);
				// Si el camino encontrado es hamiltoniano
				if(found){
					// Imprimimos el camino
					System.out.print("Camino hamiltoniano encontrado: ")
					for(int i=0; i < graph.size - 1 ; i++){
						int s = bfsPath.path[i];
						int d = bfsPath.path[i+1];
						System.out.print(s+' '+d)
					}
					System,out.print("\n");
				}
			}
			// Si ninguno de los caminos es hamiltonianos, terminamos la busqueda
			if(!found){
				System.out.println("Ninguno de los caminos recorridos es hamiltoniano")
			}

		}else
			System.err.println("Uso: java Hamilton <nombreArchivo> <BFS/DFS>");


	}
}