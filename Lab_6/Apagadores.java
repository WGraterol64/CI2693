import java.lang.Integer;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

class Apagadores{

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
		int r, d, s;
		String line;
		// Nuevo lector
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		try{
			line = reader.readLine().trim();
		}catch(IOException e){
			throw new IOException("Error, entrada no valida.");
		}

		String[] arg = line.split(" ");
		// Cargamos r, d y s
		try{
			r = Integer.parseInt(arg[0]);
			d = Integer.parseInt(arg[1]);
			s = Integer.parseInt(arg[2]);
		}catch(NumberFormatException e){
			throw new UnsupportedOperationException("Error, entrada no valida,");
		}
		// Inicializamos la estructura
		graph = new Graph(r);
		// Leemos el grafo de cuartos vecinos
		for(int i = 0; i<d; i++){
			try{
				line = reader.readLine().trim();
			}catch(IOException e){
				throw new UnsupportedOperationException("Error, entrada no valida,");
			}
			String[] neighbours = line.split(" ");
			int u, v;
			try{
				u = Integer.parseInt(neighbours[0]) - 1;
				v = Integer.parseInt(neighbours[1]) - 1;
			}catch(NumberFormatException e){
				throw new UnsupportedOperationException("Error, entrada no valida,");
			}

			graph.list.get(u).addRoom(v);
			graph.list.get(v).addRoom(u);
		}
		// Leemos el grafo de enendedores
		for(int i = 0; i<s; i++){
			try{
				line = reader.readLine().trim();
			}catch(IOException e){
				throw new UnsupportedOperationException("Error, entrada no valida,");
			}

			String[] neighbours = line.split(" ");
			int u, v;
			try{
				u = Integer.parseInt(neighbours[0]) - 1;
				v = Integer.parseInt(neighbours[1]) - 1;
			}catch(NumberFormatException e){
				throw new UnsupportedOperationException("Error, entrada no valida,");
			}
			
			graph.list.get(u).addSwitch(v);
		}

		// Retornamos la estructura creada
		return graph;
	}


	public static void main(String[] args) throws IOException{
			
		if(args.length < 1){
			System.err.println("Uso: java Apagadores <nombreArchivo>");
			return;
		}

		// Nuevo grafo
		Graph graph;
		// Cargamos el grafo
		graph = loadGraph(args[0]);
		// Revisamos la solucion
		int sol = graph.backtracking();
		// Si la solucion es -1, no hay solucion posible. En otro caso, sol almacena
		// el numero minimo de pasos a dar para llegar a la solucion
		if(sol == -1){
			System.out.println("El problema no puede ser resuelto.");
		}else{
			System.out.println("El problema puede ser resuelto en "+sol+" pasos.");
			graph.printSol();
		}
	}
}	