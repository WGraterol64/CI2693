import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

/**
* Clase que le permite al usuario tener acceso a la estructura de grafo y todas
* sus operaciones elementales
*
*/
public class ClienteGrafo{
  
 	public static UndirectedGraph ugraph; // Se utilza para implementar el grafo no dirigido
  	public static DirectedGraph dgraph;  // Se utilza para implementar el digrafo
  	private static int type; // El tipo del grafo sera 0 si es digrafo y sera 1 si es no dirigido
  	private static Transformer transV; // Parseador que nos permitira agregar datos genericos de vertices
  	private static Transformer transE; // Parseador que nos permitira agregar datos genericos de lados

 

  	/**
  	* Inicializa la creacion de un grafo desde un archivo
  	* Le solicita al usuario el nombre del archivo donde esta el grafo.
  	*/
 	public static void crearGrafoArchivo(){
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Introduzca el nombre del archivo donde esta el grafo: ");
    	String archivo = br.readLine();
    	archivo = archivo.trim();
    	cargarGrafo(archivo);
  	}

	/**
	* Metodo ue se encarga de crear un grafo que este en un archivo de texto.
	*
	* @param archivo es el archivo en el que se guarda la informacion del grafo
	*
	* @throws IllegalArgumentException si el formato del archivo no es valido
	*/
	public static void cargarGrafo(String archivo)
	  throws IllegalArgumentException{
	// Lazo que extrae la informacion necesaria para inicializar el grafo
	    BufferedReader read = new BufferedReader(new FileReader(archivo));
	    String vType,eType;
	    for(int i=0; i<5; i++){
	        String line = read.readLine();
	        if(i==0){
	            vType = line.trim();
	        }else if(i==1){
	            eType = line.trim();
	        }else if(i==2){
	            if(line == "D"){
	          		type = 0;
	        }else if(line == "N"){
	        		type = 1;
	        }else{
	         	throw new IllegalArgumentException("Formato no valido");
	          	return;
	       		}
	      	}	
	    }
	    try{
	      if(type == 0){
	        // Inizializacion si es un digrafo
	        crearDigrafoVacio(vType, eType);
	        dgraph.loadGraph(archivo);
	      }else if(type == 1){
	        // Inizializacion si es un grafo no dirigido
	        crearGrafoNoDirigidoVacio(vType, eType);
	        ugraph.loadGraph(archivo);
	      }
	    }catch(IllegalArgumentException | UnsupportedOperationException e){
	      throw new IllegalArgumentException("No se pudo cargar el grafo");
	      return;
	    }
	    System.out.println("Se ha creado el grafo exitosamente");
	  }

	  /**
	  * Crea un grafo no dirigido vacio que se mantendra como el atributo ugraph
	  * de la clase. El metodo recibe del usuario los datos necesarios para la creacion
	  * del grafo.
	  *
	  * @throws IllegalArgumentException si los daros que da el usuario no son validos
	  */
	  public static void crearGrafoNoDirigido(){
	    type = 1;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el tipo de dato de los vertices: (B, D o S)");
	    String v = br.readLine();
	    String vType = v.trim();
	    System.out.println("Introduzca el tipo de dato de las aristas: (B, D o S)");
	    String l = br.readLine();
	    String eType = l.trim();
	    try{
	      crearGrafoNoDirigidoVacio(vType, eType);
	    }catch(IllegalArgumentException i){
	      throw new IllegalArgumentException("No se creo el grafo no dirigido");
	      return;
	    }

	    System.out.println("Se ha creado el grafo exitosamente");
	  }

	  /**
	  * Inicializa el grafo no dirigido de esta clase
	  *
	  * @param vType es el tipo de dato de los vertices
	  *
	  * @param eType es el tipo de dato de los lados
	  *
	  * @throws IllegalArgumentException si el formato de los argumentos no es el esperado
	  */
	  public static void crearGrafoNoDirigidoVacio(String vType, String eType)
	  throws IllegalArgumentException{
	    if(vType == "B" && eType =="B"){
	      ugraph = new UndirectedGraph<Boolean,Boolean>();
	      transV = new BooleanTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType == "B" && eType =="D"){
	      ugraph = new UndirectedGraph<Boolean, Double>();
	      transV = new BooleanTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType == "B" && eType =="S"){
	      ugraph = new UndirectedGraph<Boolean,String> ();
	      transV = new BooleanTransformer();
	      transE = new StringTransformer();
	    }else if(vType == "D" && eType =="B"){
	      ugraph = new UndirectedGraph<Double,Boolean> ();
	      transV = new DoubleTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType == "D" && eType =="D"){
	      ugraph = new UndirectedGraph<Double,Double>();
	      transV = new DoubleTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType == "D" && eType =="S"){
	      ugraph = new UndirectedGraph<Double,String>();
	      transV = new DoubleTransformer();
	      transE = new StringTransformer();
	    }else if(vType == "S" && eType =="B"){
	      ugraph = new UndirectedGraph<String, Boolean>();
	      transV = new StringTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType == "S" && eType =="D"){
	      ugraph = new UndirectedGraph<String,Double>();
	      transV = new StringTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType == "S" && eType =="S"){
	      ugraph = new UndirectedGraph<String,String>();
	      transV = new StringTransformer();
	      transE = new StringTransformer();
	    }else{
	      throw new IllegalArgumentException("Tipos de datos no validos");
	      return;
	    }
	  }

	  /**
	  * Crea un grafo no dirigido vacio que se mantendra como el atributo ugraph
	  * de la clase. El metodo recibe del usuario los datos necesarios para la creacion
	  * del grafo.
	  *
	  * @throws IllegalArgumentException si los datos que da el usuario no son validos
	  */
	  public static void crearDigrafo()
	    throws IllegalArgumentException{
	    type = 0;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el tipo de dato de los vertices: (B, D o S)");
	    String v = br.readLine();
	    String vType = v.trim();
	    System.out.println("Introduzca el tipo de dato de los arcos: (B, D o S)");
	    String l = br.readLine();
	    String eType = l.trim();
	    try{
	      crearDigrafoVacio(vType, eType);
	    }catch(IllegalArgumentException i){
	      throw new IllegalArgumentException("No se creo el digrafo");
	    }

	    System.out.println("Se ha creado el grafo exitosamente");
	  }

	  /**
	  * Inicializa el Digrafo de esta clase
	  *
	  * @param vType es el tipo de dato de los vertices
	  *
	  * @param eType es el tipo de dato de los lados
	  *
	  * @throws IllegalArgumentException si el formato de los argumentos no es el esperado
	  */
	  public static void crearDigrafoVacio(String vType, String eType)
	  throws IllegalArgumentException{
	    if(vType == "B" && eType =="B"){
	      dgraph = new DirectedGraph<Boolean,Boolean>();
	      transV = new BooleanTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType == "B" && eType =="D"){
	      dgraph = new DirectedGraph<Boolean, Double>();
	      transV = new BooleanTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType == "B" && eType =="S"){
	      dgraph = new DirectedGraph<Boolean,String>();
	      transV = new BooleanTransformer();
	      transE = new StringTransformer();
	    }else if(vType == "D" && eType =="B"){
	      dgraph = new DirectedGraph<Double,Boolean>();
	      transV = new DoubleTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType == "D" && eType =="D"){
	      dgraph = new DirectedGraph<Double,Double>();
	      transV = new DoubleTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType == "D" && eType =="S"){
	      dgraph = new DirectedGraph<Double,String> ();
	      transV = new DoubleTransformer();
	      transE = new StringTransformer();
	    }else if(vType == "S" && eType =="B"){
	      dgraph = new DirectedGraph<String, Boolean>();
	      transV = new StringTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType == "S" && eType =="D"){
	      dgraph = new DirectedGraph<String,Double>();
	      transV = new StringTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType == "S" && eType =="S"){
	      dgraph = new DirectedGraph<String,String>();
	      transV = new StringTransformer();
	      transE = new StringTransformer();
	    }else{
	      throw new IllegalArgumentException("Tipos de datos no validos");
	      return;
	    }
	  }

	  /**
	  * Agrega un nodo en el grafo en el que se este usando. Todos los datos son
	  * solicitados internamente al usuario.
	  *
	  * @throws IllegalArgumentException si los datos que da el usuario no son validos
	  *                                  o si hay algun vertice con el mismo id.
	  */
	  public static void agregarNodo()
	  throws IllegalArgumentException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = br.readLine();
	    id = id.trim();
	    System.out.println("Introduzca el dato del vertice: ");
	    String data = br.readLine();
	    data = data.trim();
	    System.out.println("Introduzca el peso: ");
	    String s = br.readLine();
	    s = s.trim();
	    if(data.length() < 1 || s.length() < 1 ){
	      throw new IllegalArgumentException("No se pueden sar datos vacios");
	      return;
	    }
	    double p = Double.parseDouble(s);
	    boolean result;
	    try{
	      if(type == 0){
	        result = dgraph.addNode(id, transV.Transform(data), p);
	      }else if(type == 1){
	        result = ugraph.addNode(id, transV.Transform(data), p);
	      }
	    }catch(IllegalArgumentException i){
	      throw new IllegalArgumentException("No se pudo agregar el vertice ");
	      return;
	    }

	    if(result){
	      System.out.println("Se ha agregado exitosamente el vertice. ");
	    }else{
	      System.out.println("Ya existe un vertice con ese id. ");
	    }
	  }

	  /**
	  * Agrega un lado en el grafo en el que se este usando. Todos los datos son
	  * solicitados internamente al usuario.
	  *
	  * @throws IllegalArgumentException si los datos que da el usuario no son validos
	  */
	  public static void agregarLado()
	  throws IllegalArgumentException{
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      System.out.println("Introduzca el id del lado: ");
	      String id = br.readLine().trim();
	      System.out.println("Introduzca el dato del lado: ");
	      String data = br.readLine();
	      data = data.trim();
	      System.out.println("Introduzca el peso: ");
	      String s = br.readLine();
	      s = s.trim();
	      if(data.length() < 1 || s.length() < 1 ){
	        throw new IllegalArgumentException("No se pueden sar datos vacios");
	        return;
	      }
	      double p = Double.parseDouble(s.trim());
	      System.out.println("Introduzca el id del vertice inicial: ");
	      String vInicial = br.readLine().trim();
	      System.out.println("Introduzca el id del vertice final: ");
	      String vFinal = br.readLine().trim();
	      boolean result;
	      try{
	        if(type == 0){
	          result = dgraph.addArc(id, transE.Transform(data.trim()), p, vInicial, vFinal);
	        }else if(type == 1){
	          result = ugraph.addArc(id, transE.Transform(data.trim()), p, vInicial, vFinal);
	        }
	      }catch(IllegalArgumentException i){
	        throw new IllegalArgumentException("No se pudo agregar el lado ");
	        return;
	      }
	      if(result){
	        System.out.println("Se ha agregado exitosamente el arco. ");
	      }else{
	        System.out.println("Ya existe un lado con ese id. ");
	      }
	  }

	  /**
	  * Imrpime en pantalla el numero de vertices que hay en el grafo. Le solicita
	  * al usuario el id del nodo para poder realizar la operacion.
	  */
	  public static void obtenerNumVertices(){
	    int n;
	    if(type == 0){
	      n = dgraph.numOfNodes();
	    }else if(type == 1){
	      n = ugraph.numOfNodes();
	    }
	    System.out.print("El numero de vertices en el grafo es: ");
	    System.out.print(n);
	    System.out.print("\n");
	  }

	  /**
	  * Imrpime en pantalla el numero de lados que hay en el grafo. Le solicita
	  * al usuario el id del lado para poder realizar la operacion.
	  */
	  public static void obtenerNumLados(){
	    int n;  
	    if(type == 0){
	      n = dgraph.numOfEdges();
	    }else if(type == 1){
	      n = ugraph.numOfEdges();
	    }
	    System.out.println("El numero de lados en el grafo es: ");
	    System.out.print(n);
	    System.out.print("\n");
	  }

	  /**
	  * Le solicita al usuario el id de un vertice e imprime en pantalla un mensaje
	  * indicando si ya esta en el grafo o no
	  */
	  public static void buscarVertice(){
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = br.readLine().trim();
	    boolean is;
	    if(type == 0){
	      is = dgraph.isNode(id);
	    }else if(type == 1){
	      is = ugraph.isNode(id);
	    }
	    if(is){
	      System.out.println("El nodo esta en el grafo ");
	    }else{
	      System.out.println("El nodo no esta en el grafo ");
	    }
	  }

	  /**
	  * Le solicita al usuario el id de un lado e imprime en pantalla un mensaje
	  * indicando si ya esta en el grafo o no
	  */
	  public static void buscarLado(){
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del lado: ");
	    String id = br.readLine().trim();
	    boolean is;
	    if(type == 0){
	      is = dgraph.isEdge(id);
	    }else if(type == 1){
	      is = ugraph.isEdge(id);
	    }
	    if(is){
	      System.out.println("El lado esta en el grafo ");
	    }else{
	      System.out.println("El lado no esta en el grafo ");
	    }
	  }

	  /**
	  * Le solicita al usuario el id de un vertice y
	  * Si esta, lo elimina e imprime un mensaje idicando que fue eliminado
	  * Si no esta, imprime un mensaje indicando que no esta en el grafo
	  */
	  public static void eliminarVertice(){
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = br.readLine().trim();
	    boolean result;
	    if(type == 0){
	        result = dgraph.removeNode(id);
	    }else if(type == 1){
	        result = ugraph.removeNode(id);
	    }
	    if(result){
	      System.out.println("Se ha eliminado el nodo exitosamente. ");
	    }else{
	      System.out.println("No existe un nodo con ese id. ");
	    }
	  }

	  /**
	  * Imprime en pantalla los id de todos los nodos del grafo
	  */
	  public static void imprimirVertices(){
	    
	    ArrayList<Node<V,E>> vertices;
	    if(type == 0){
	        vertices = dgraph.nodeList();
	    }else if(type == 1){
	        vertices = ugraph.nodeList();
	    }
	    if(vertices.size() == 0){
	      System.out.println("No hay nodos en el grafo. ");
	    }else{
	      System.out.println("Los nodos en el grafo son: ");
	      for(int i=0; i<vertices.size(); i++){
	        System.out.println(vertices.get(i).getId());
	      }
	    }
	  }

	  /**
	  * Imprime en pantalla los id de todos los lados del grafo
	  */
	  public static void imprimirLados(){
	    ArrayList<Node<V,E>> lados;
	    if(type == 0){
	        lados = dgraph.edgeList();
	    }else if(type == 1){
	        lados = ugraph.edgeList();
	    }
	    if(lados.size() == 0){
	      System.out.println("No lados en este grafo. ");
	    }else{
	      System.out.println("Los lados en el grafo son: ");
	      for(int i=0; i<lados.size(); i++){
	        System.out.println(lados.get(i).getId());
	      }
	    }
	  }

	  /**
	  * Imprime en pantalla los id de todos los vertices adyacentes a un vertice.
	  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
	  *
	  */
	  public static void imprimirAdyacentes(){
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = br.readLine().trim();
	    ArrayList<Node<V,E> > adj;
	    try{
	      if(type == 0){
	            adj = dgraph.adjacency(id);
	      }else if(type == 1){
	            adj = ugraph.adjacency(id);
	      }
	    }catch(NoSuchElementException i){
	      System.out.println("No se puede realizar la operacion");
	      return;
	    }
	    System.out.println("Los vertices adjacentes a " + id + "son: ");
	    for(int i=0; i<adj.size(); i++){
	      System.out.println(adj.get(i).getId());
	    }
	  }

	  /**
	  * Imprime en pantalla los id de todos los lados incidentes a un vertice.
	  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
	  */
	  public static void imprimirIncidentes(){
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = br.readLine().trim();
	    ArrayList<Edge<V,E>> inc;
	    try{
	        if(type == 0){
	            inc = dgraph.incident(id);
	        }else if(type == 1){
	            inc = ugraph.incident(id);
	        }
	        }catch(NoSuchElementException i){
	            System.out.println("No se pudo realizar la operacion");
	            return;
	        }
	        System.out.println("Los lados incidentes a " + id + "son: ");
	        for(int i=0; i<inc.size(); i++){
	            System.out.println(inc.get(i).getId());
	        }
	    }

	  /**
	  * Imprime en pantalla todos los vertices y lados del grafo
	  */
	  public static void imprimirGrafo(){
	    imprimirVertices();
	    imprimirIncidentes();
	  }

	  /**
	  * Imprime en pantalla el grado de un vertice.
	  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
	  */
	  public static void grado(){
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = br.readLine().trim();
	    int g;
	    try{
	      if(type == 0){
	        g = dgraph.degree(id);
	      }else if(type == 1){
	        g = ugraph.degree(id);
	      }
	    }catch(NoSuchElementException i){
	      System.out.println("No se puede realizar la operacion, el nodo no esta en el grafo");
	      return;
	    }
	    System.out.print("El grado del vertice es: ");
	    System.out.print(g);
	    System.out.print("\n");
	  }

	  /**
	  * Imprime en pantalla el grado interior de un vertice.
	  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
	  *
	  * @throws UnsupportedOperationException si se intenta aplicar sobre un grafo no dirigido
	  */
	  public static void gradoInterior()
	  throws UnsupportedOperationException{
	    if(type!=0){
	      throw new UnsupportedOperationException("Grado interior no esta definido para grafos no dirigidos");
	      return;
	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = br.readLine().trim();
	    int g;
	    try{
	      g = dgraph.inDegree(id);
	    }catch(NoSuchElementException i){
	      System.out.println("No se puede realizar la operacion, el nodo no esta en el grafo");
	      return;
	    }
	    System.out.print("El grado interior del vertice es: ");
	    System.out.print(g);
	    System.out.print("\n");
	  }

	  /**
	  * Imprime en pantalla el grado exterior de un vertice.
	  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
	  *
	  * @throws UnsupportedOperationException si se intenta aplicar sobre un grafo no dirigido
	  */
	  public static void gradoExterior()
	  throws UnsupportedOperationException{
	    if(type!=0){
	      throw new UnsupportedOperationException("Grado exterior no esta definido para grafos no dirigidos");
	      return;
	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = br.readLine().trim();
	    int g;
	    try{
	       g = dgraph.outDegree(id);
	    }catch(NoSuchElementException i){
	      System.out.println("No se puede realizar la operacion, el nodo no esta en el grafo");
	      return;
	    }
	    System.out.print("El grado exterior del vertice es: ");
	    System.out.print(g);
	    System.out.print("\n");
	  }

	  /**
	  * Imprime en pantalla un mensaje de salida y termina la ejecucion del programa
	  */
	  public static void salir(){
	    System.out.println("Hasta luego!");
	    System.exit(0);
	  }

	  /**
	  * Imprime en pantalla un menu que le da acceso al usuario a todas las operaciones del grafo.
	  * Solo se le permite mantener un grafo a la vez.
	  *
	  * @throws IOException si hubo algun error en la ejecucion de cualquiera de los subprogramas
	  */
	  public static void menu()
	  throws IOException{
	    System.out.println(new StringBuilder("Introduzca la operacion que desee realizar: \n")
	                   .append("1.- Crear Grafo desde archivo \n")
	                   .append("2.- Crear Grafo no dirigido vacio \n")
	                   .append("3.- Crear Digrafo vacio \n")
	                   .append("4.- Agregar Vertice \n")
	                   .append("5.- Agregar Lado \n")
	                   .append("6.- Obtener numero de vertices \n")
	                   .append("7.- Obtener numero de lados \n")
	                   .append("8.- Ver si esta un vertice \n")
	                   .append("9.- Ver si esta lado \n")
	                   .append("10.- Eliminar vertice \n")
	                   .append("11.- Imprimir lista de vertices \n")
	                   .append("12.- Imprimir lista de lados \n")
	                   .append("13.- Imprimir lista de vertices adyacentes a un vertice \n")
	                   .append("14.- Imprimir lista de lados incidentes a un vertice \n")
	                   .append("15.- Imprimir Grafo \n")
	                   .append("16.- Calcular grado de un vertice \n")
	                   .append("17.- Calcular el grado interior de un vertice (solo para digrafos) \n")
	                   .append("18.- Calcular el grado externo de un vertice (solo para digrafos) \n")
	                   .append("0.- Salir \n")
	                   .toString());

	     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     int n;
	     try{
	        n = Integer.parseInt(br.readLine());
	     }catch(NumberFormatException e){
	       throw new IOException("Entrada no valida, debe ser un numero");
	     }
	     try{
	       switch (n) {
	         case 1: crearGrafoArchivo();
	         break;
	         case 2: crearGrafoNoDirigido();
	         break;
	         case 3: crearDigrafo();
	         break;
	         case 4: agregarNodo();
	         break;
	         case 5: agregarLado();
	         break;
	         case 6: obtenerNumVertices();
	         break;
	         case 7: obtenerNumLados();
	         break;
	         case 8: buscarVertice();
	         break;
	         case 9: buscarLado();
	         break;
	         case 10: eliminarVertice();
	         break;
	         case 11: imprimirVertices();
	         break;
	         case 12: imprimirLados();
	         break;
	         case 13: imprimirAdyacentes();
	         break;
	         case 14: imprimirIncidentes();
	         break;
	         case 15: imprimirGrafo();
	         break;
	         case 16: grado();
	         break;
	         case 17: gradoInterior();
	         break;
	         case 18: gradoExterior();
	         break;
	         case 0: salir();
	         break;
	       }
	     }catch(IllegalArgumentException | UnsupportedOperationException e){
	       throw new IOException("Hubieron errores en ejecucion por argumentos invalidos");
	       return;
	     }
	  }

	  /**
	  * Metodo principal de la clase. 
	  *
	  * @throws IOException si hubieron errores en la ejecucion y sale del programa
	  */
	  	public static void main(String[] args)
	  	throws IOException{
		    try{
		        while(true)
		            menu();
		    }catch(IllegalArgumentException | IOException e){
		        System.out.println("Errores en la ejecucion: Saliendo...");
		        System.exit(1);
		    }
		}
	}