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
		if(args.length < 1){
			System.err.println("Uso: java Hamilton <nombreArchivo> <BFS/DFS>");
			return;
		}

		try{
			this.graph = loadGraph(args[0]);
		}catch(UnsupportedOperationException e){
			throw e;
		}

		if(args[1]=='DFS'){

			DFS dfsPath = new DFS
		}else if(args[1]=='BFS'){

			BFS bfsPath = new BFS(graph.size);
			boolean found = false;
			for(int u = 0; u < graph.size && !found; u++){
				found = bfsPath.findPath(u,graph);
				if(found){
					System.out.print("Camino hamiltoniano encontrado: ")
					for(int i=0; i < graph.size - 1 ; i++){
						int s = bfsPath.path[i];
						int d = bfsPath.path[i+1];
						System.out.print(s+' '+d)
					}
					System,out.print("\n");
				}
			}
			if(!found){
				System.out.print("Ninguno de los caminos recorridos es hamiltoniano\n")
			}

		}else
			System.err.println("Uso: java Hamilton <nombreArchivo> <BFS/DFS>");


	}
}