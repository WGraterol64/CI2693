import java.lang.Integer;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

class DeepWeb{

	/**
	* Metodo utilizado para cargar los grafos del problema
	* @param filename Nombre del archivo a cargar
	* @return Estructura cargada
	* @throws IOException si ocurre un problema al cargar el archivo
	* @throws RuntimeException si ocurre un problema con el formato del archivo
	**/
	public static Graph loadGraph(String filename) 
		throws IOException,RuntimeException{

		// Declaracion de variables a utilizar
		Graph graph;
		int n,m;
		String line;
		// Nuevo lector
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		try{
			line = reader.readLine().trim();
		}catch(IOException e){
			throw new IOException("Error, entrada no valida.");
		}

		try{
			n = Integer.parseInt(line);
		}catch(NumberFormatException e){
			throw new UnsupportedOperationException("Error, entrada no valida,");
		}

		try{
			line = reader.readLine().trim();
		}catch(IOException e){
			throw new IOException("Error, entrada no valida.");
		}

		try{
			m = Integer.parseInt(line);
		}catch(NumberFormatException e){
			throw new UnsupportedOperationException("Error, entrada no valida,");
		}

		// Inicializamos la estructura
		graph = new Graph(n);
		// Leemos los lados
		for(int i = 0; i<m; i++){
			try{
				line = reader.readLine().trim();
			}catch(IOException e){
				throw new UnsupportedOperationException("Error, entrada no valida,");
			}
			String[] neighbours = line.split(" ");
			int u, v;
			try{
				u = Integer.parseInt(neighbours[0]);
				v = Integer.parseInt(neighbours[1]);
			}catch(NumberFormatException e){
				throw new UnsupportedOperationException("Error, entrada no valida,");
			}

			graph.list.get(u).addAdj(v);
		}
		

		// Retornamos la estructura creada
		return graph;
	}


	public static void main(String[] args) throws IOException, UnsupportedOperationException{
			
		if(args.length < 3){
			System.err.println("Uso: >java DeepWeb <instancia> <origen> <dfs/bfs> [--trunc #] [--arb] [--ord] [--pred]");
			return;
		}

		// Nuevo grafo
		Graph graph;
		// Cargamos el grafo
		graph = loadGraph(args[0]);
		
		// Leemos la fuente
		int source;
		try{
			source = Integer.parseInt(args[1].trim());
		}catch(NumberFormatException e){
			throw new UnsupportedOperationException("Error, entrada no valida.");
		}

		// Leemos el tipo de algoritmo
		boolean algorithm;
		if(args[2].trim().equals("dfs"))
			algorithm = false;
		else if(args[2].trim().equals("bfs"))
			algorithm = true;
		else{
			System.err.println("Error, entrada no valida.");
			return;
		}

		// Leemos los parametros adicionales
		boolean tree=false,ord=false,pred=false;
		int trunc = -1;

		for(int i = 3; i<args.length; i++){
			
			if(args[i].equals("--trunc")){
				
				if(i+1 == args.length){ 
						System.err.println("Uso: >java DeepWeb <instancia> <origen> <dfs/bfs> [--trunc #] [--arb] [--ord] [--pred]");
						return;
				}
				try{
					trunc = Integer.parseInt(args[i+1].trim());
				}catch(NumberFormatException e){
					throw new UnsupportedOperationException("Error, entrada no valida.");
				}
				i++;
				continue;
			}
			
			if(args[i].equals("--arb")){
				tree = true;
				continue;
			}
			
			if(args[i].equals("--ord")){
				ord = true;
				continue;
			}

			if(args[i].equals("--pred")){
				pred = true;
				continue;
			}

		}

		graph.solve(source,algorithm,pred,tree,ord,trunc);

	}
} 