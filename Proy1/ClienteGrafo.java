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

  	private static int type; // El tipo del grafo sera 0 si es digrafo y sera 1 si es no dirigido

  	/**
  	* Inicializa la creacion de un grafo desde un archivo
  	* Le solicita al usuario el nombre del archivo donde esta el grafo.
    *
    * @param ugraph es el grafo no dirigido sobre el que se cargara el Archivo
    *
    * @param dgraph es el digrafo sobre el que se cargara el Archivo
    *
    * @param transV es el parseador para los datos de los vertices
    *
    * @param transE es el parseador para los datos de los lados
    *
    * @throws IOException si hay problemas leyendo datos por el terminal
  	*/
 	public static void crearGrafoArchivo(UndirectedGraph ugraph, DirectedGraph dgraph,
                                       Transformer transV, Transformer transE)
    throws IOException{
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     System.out.println("Introduzca el nombre del archivo donde esta el grafo: ");
     String archivo = "";
     try{
       archivo = br.readLine();
     }catch(IOException i){
      throw new IOException(i);
      }
    	archivo = archivo.trim();
      try{
          cargarGrafo(archivo, ugraph, dgraph, transV, transE);
      }catch(IllegalArgumentException | IOException e){
        throw new IOException(" Hubo un error al cargar el archivo");
      }
  	}

	/**
	* Metodo ue se encarga de crear un grafo que este en un archivo de texto.
	*
	* @param archivo es el archivo en el que se guarda la informacion del grafo
	*
  * @param ugraph es el grafo no dirigido sobre el que se cargara el Archivo
  *
  * @param dgraph es el digrafo sobre el que se cargara el Archivo
  *
  * @param transV es el parseador para los datos de los vertices
  *
  * @param transE es el parseador para los datos de los lados
  *
  * @throws IOException si hay problemas leyendo datos por el terminal
  *
	* @throws IllegalArgumentException si el formato del archivo no es valido
	*/
	public static void cargarGrafo(String archivo, UndirectedGraph ugraph, DirectedGraph dgraph,
                                       Transformer transV, Transformer transE)
	  throws IllegalArgumentException, IOException{

      BufferedReader read = new BufferedReader(new FileReader(archivo));
	    String vType = "";
      String eType = "";
      String line;
	    for(int i=0; i<5; i++){
	        try{
	        	line = read.readLine();
	        }catch(IOException e){
	        	throw new IOException("Entrada no valida");
	        }

	        if(i==0){
	            vType = line.trim();
	        }else if(i==1){
	            eType = line.trim();
	        }else if(i==2){
	            if(line.equals("D")){
	          		type = 0;
	        }else if(line.equals("N")){
	        		type = 1;
	        }else{
	         	throw new IllegalArgumentException("Formato no valido");

	       		}
	      	}
	    }
	    try{
	      if(type == 0){
	        // Inizializacion si es un digrafo
	        System.out.println("Entre aqui"+vType+eType);
	        crearDigrafoVacio(vType, eType, dgraph, transV, transE);
	        dgraph.loadGraph(archivo);
	      }else if(type == 1){
	        // Inizializacion si es un grafo no dirigido
	        crearGrafoNoDirigidoVacio(vType, eType, ugraph, transV, transE);
	        ugraph.loadGraph(archivo);
	      }
	    }catch(IllegalArgumentException | UnsupportedOperationException e){
	      throw new IllegalArgumentException("No se pudo cargar el grafo");

	    }
       System.out.println("Se ha creado el grafo exitosamente");
	  }

	  /**
	  * Crea un grafo no dirigido vacio . El metodo recibe del usuario los datos
	  * necesarios para la creacion del grafo.
	  *
    * @param ugraph es la variable donde se guardara el grafo no dirigido que se creara
    *
    * @param transV es el parseador para los datos de los vertices del grafo nuevo
    *
    * @param transE es el parseador para los datos de los lados del grafo nuevo
    *
    * @throws IOException si hay problemas leyendo datos por el terminal
    *
	  * @throws IllegalArgumentException si los datos que da el usuario no son validos
	  */
	  public static void crearGrafoNoDirigido(UndirectedGraph ugraph, Transformer transV,
                                            Transformer transE)
      throws IOException{
      type = 1;
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Introduzca el tipo de dato de los vertices: (B, D o S)");
      String v = "";
      try{
        v = br.readLine();
      }catch(IOException e){
        throw new IOException("Entrada no valida");
      }
      String vType = v.trim();
      System.out.println("Introduzca el tipo de dato de las aristas: (B, D o S)");
      String l = "";
      try{
        l = br.readLine();
      }catch(IOException e){
        throw new IOException("Entrada no valida");
      }
      String eType = l.trim();
	    try{
	      crearGrafoNoDirigidoVacio(vType, eType, ugraph, transV, transE);
	    }catch(IllegalArgumentException i){
	      throw new IllegalArgumentException("No se creo el grafo no dirigido");
	    }

	    System.out.println("Se ha creado el grafo exitosamente");
	  }

	  /**
	  * Inicializa el grafo no dirigido
	  *
	  * @param vType es el tipo de dato de los vertices
	  *
	  * @param eType es el tipo de dato de los lados
    *
    * @param ugraph es el grafo no dirigido que se inicializara
    *
    * @param transV es el parseador para los datos de los vertices que se inicializara
    *
    * @param transE es el parseador para los datos de los lados que se inicializara
	  *
	  * @throws IllegalArgumentException si el formato de los argumentos no es el esperado
	  */
	  public static void crearGrafoNoDirigidoVacio(String vType, String eType,UndirectedGraph ugraph,
                                         Transformer transV, Transformer transE)
	  throws IllegalArgumentException{
	    if(vType.equals("B") && eType.equals("B")){
	      ugraph = new UndirectedGraph<Boolean,Boolean>();
	      transV = new BooleanTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType.equals("B") && eType.equals("D")){
	      ugraph = new UndirectedGraph<Boolean, Double>();
	      transV = new BooleanTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType.equals("B")&& eType.equals("S")){
	      ugraph = new UndirectedGraph<Boolean,String> ();
	      transV = new BooleanTransformer();
	      transE = new StringTransformer();
	    }else if(vType.equals("D")&& eType.equals("B")){
	      ugraph = new UndirectedGraph<Double,Boolean> ();
	      transV = new DoubleTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType.equals("D") && eType.equals("D")){
	      ugraph = new UndirectedGraph<Double,Double>();
	      transV = new DoubleTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType.equals("D")&& eType.equals("S")){
	      ugraph = new UndirectedGraph<Double,String>();
	      transV = new DoubleTransformer();
	      transE = new StringTransformer();
	    }else if(vType.equals("S")&& eType.equals("B")){
	      ugraph = new UndirectedGraph<String, Boolean>();
	      transV = new StringTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType.equals("S") && eType.equals("D")){
	      ugraph = new UndirectedGraph<String,Double>();
	      transV = new StringTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType.equals("S") && eType.equals("S")){
	      ugraph = new UndirectedGraph<String,String>();
	      transV = new StringTransformer();
	      transE = new StringTransformer();
	    }else{
	      throw new IllegalArgumentException("Tipos de datos no validos");

	    }
	  }

    /**
	  * Crea un digrafo . El metodo recibe del usuario los datos
	  * necesarios para la creacion del grafo.
	  *
    * @param dgraph es la variable donde se guardara el digrafo que se creara
    *
    * @param transV es el parseador para los datos de los vertices del grafo nuevo
    *
    * @param transE es el parseador para los datos de los lados del grafo nuevo
    *
    * @throws IOException si hay problemas leyendo datos por el terminal
    *
	  * @throws IllegalArgumentException si los datos que da el usuario no son validos
	  */
	  public static void crearDigrafo(DirectedGraph dgraph, Transformer transV,
                                    Transformer transE)
	    throws IllegalArgumentException, IOException{
	    type = 0;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el tipo de dato de los vertices: (B, D o S)");
      String v = "";
      try{
        v = br.readLine();
      }catch(IOException e){
        throw new IOException("Entrada no valida");
      }
      String vType = v.trim();
      System.out.println("Introduzca el tipo de dato de las aristas: (B, D o S)");
      String l = "";
      try{
        l = br.readLine();
      }catch(IOException e){
        throw new IOException("Entrada no valida");
      }
      String eType = l.trim();
	    try{
	      crearDigrafoVacio(vType, eType, dgraph, transV, transE);
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
    * @param dgraph es el digrafo que se inicializara
    *
    * @param transV es el parseador para los datos de los vertices que se inicializara
    *
    * @param transE es el parseador para los datos de los lados que se inicializara
	  *
	  * @throws IllegalArgumentException si el formato de los argumentos no es el esperado
	  */
	  public static void crearDigrafoVacio(String vType, String eType, DirectedGraph dgraph,
                                         Transformer transV, Transformer transE)
	  throws IllegalArgumentException{
	    if(vType.equals("B") && eType.equals("B")){
	      dgraph = new DirectedGraph<Boolean,Boolean>();
	      transV = new BooleanTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType.equals("B")  && eType.equals("D")){
	      dgraph = new DirectedGraph<Boolean, Double>();
	      transV = new BooleanTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType.equals("B")  && eType.equals("S")){
	      dgraph = new DirectedGraph<Boolean,String>();
	      transV = new BooleanTransformer();
	      transE = new StringTransformer();
	    }else if(vType.equals("D") && eType.equals("B")){
	   	  System.out.println("Aqui tambien");
	      dgraph = new DirectedGraph<Double,Boolean>();
	      transV = new DoubleTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType.equals("D") && eType.equals("D")){
	      dgraph = new DirectedGraph<Double,Double>();
	      transV = new DoubleTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType.equals("D") && eType.equals("S")){
	      dgraph = new DirectedGraph<Double,String> ();
	      transV = new DoubleTransformer();
	      transE = new StringTransformer();
	    }else if(vType.equals("S") && eType.equals("B") ){
	      dgraph = new DirectedGraph<String, Boolean>();
	      transV = new StringTransformer();
	      transE = new BooleanTransformer();
	    }else if(vType.equals("S")&& eType.equals("D")){
	      dgraph = new DirectedGraph<String,Double>();
	      transV = new StringTransformer();
	      transE = new DoubleTransformer();
	    }else if(vType.equals("S")&& eType.equals("S")){
	      dgraph = new DirectedGraph<String,String>();
	      transV = new StringTransformer();
	      transE = new StringTransformer();
	    }else{
	      throw new IllegalArgumentException("Tipos de datos no validos");
	    }
	  }

	  /**
	  * Agrega un nodo en el grafo en el que se este usando. Todos los datos son
	  * solicitados internamente al usuario.
	  *
    * @param ugraph es el grafo no dirigido en el que se agregaran los nodos
    *
    * @param dgraph es el digrafo en el que se agregaran los nodos
    *
    * @param transV es el parseador para los datos de los vertices
    *
	  * @throws IllegalArgumentException si los datos que da el usuario no son validos
	  *                                  o si hay algun vertice con el mismo id.
    * @throws IOException si hay errores en la entrada por el terminal
	  */
	  public static void agregarNodo(UndirectedGraph ugraph, DirectedGraph dgraph,
                                         Transformer transV)
	  throws IllegalArgumentException, IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = "";
      try{
        id = br.readLine();
      }catch(IOException e){
        throw new IOException("Entrada no valida");
      }
	    id = id.trim();
	    System.out.println("Introduzca el dato del vertice: ");
	    String data = "";
      try{
        data = br.readLine();
      }catch(IOException e){
        throw new IOException("Entrada no valida");
      }
	    data = data.trim();
	    System.out.println("Introduzca el peso: ");
      String s ="";
      try{
        s = br.readLine();
      }catch(IOException e){
        throw new IOException("Entrada no valida");
      }
	    s = s.trim();
	    if(data.length() < 1 || s.length() < 1 ){
	      throw new IllegalArgumentException("No se pueden sar datos vacios");
	    }
	    double p = Double.parseDouble(s);
	    boolean result = false;
	    try{
	      if(type == 0){
	        result = dgraph.addNode(id, transV.Transform(data), p);
	      }else if(type == 1){
	        result = ugraph.addNode(id, transV.Transform(data), p);
	      }
	    }catch(IllegalArgumentException i){
	      throw new IllegalArgumentException("No se pudo agregar el vertice ");
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
    * @param ugraph es el grafo no dirigido en el que se agregaran los lados
    *
    * @param dgraph es el digrafo sobre el que se agregaran los lados
    *
    * @param transE es el parseador para los datos de los lados
    *
	  * @throws IllegalArgumentException si los datos que da el usuario no son validos
    *
    * @throws IOException si hay errores en la entrada por el terminal
	  */
	  public static void agregarLado(UndirectedGraph ugraph, DirectedGraph dgraph,
                                         Transformer transE)
	  throws IllegalArgumentException, IOException{
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

	      }
	      double p = Double.parseDouble(s.trim());
	      System.out.println("Introduzca el id del vertice inicial: ");
	      String vInicial = br.readLine().trim();
	      System.out.println("Introduzca el id del vertice final: ");
	      String vFinal = br.readLine().trim();
	      boolean result = false;
	      try{
	        if(type == 0){
	          result = dgraph.addArc(id, transE.Transform(data.trim()), p, vInicial, vFinal);
	        }else if(type == 1){
	          result = ugraph.addEdge(id, transE.Transform(data.trim()), p, vInicial, vFinal);
	        }
	      }catch(IllegalArgumentException i){
	        throw new IllegalArgumentException("No se pudo agregar el lado ");

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
    *
    * @param ugraph es el grafo no dirigido del cual se quiere saber el numero de nodos
    *
    * @param dgraph es el digrafo del cual se quiere saber el numero de nodos
	  */
	  public static void obtenerNumVertices(UndirectedGraph ugraph, DirectedGraph dgraph){
	    int n = 0;
	    if(type == 0){
	      n = dgraph.numOfNodes();
	    }else if(type == 1){
	      n = ugraph.numOfNodes();
	    }
	    System.out.println("El numero de vertices en el grafo es: ");
	    System.out.println(n);
	  }

	  /**
	  * Imrpime en pantalla el numero de lados que hay en el grafo. Le solicita
	  * al usuario el id del lado para poder realizar la operacion.
    *
    * @param ugraph es el grafo no dirigido del cual se quiere el numero de lados
    *
    * @param dgraph es el digrafo del cual se quiere el numero de lados
	  */
	  public static void obtenerNumLados(UndirectedGraph ugraph, DirectedGraph dgraph){
	    int n = 0;
	    if(type == 0){
	      n = dgraph.numOfEdges();
	    }else if(type == 1){
	      n = ugraph.numOfEdges();
	    }
	    System.out.println("El numero de lados en el grafo es: ");
	    System.out.println(n);
	  }

	  /**
	  * Le solicita al usuario el id de un vertice e imprime en pantalla un mensaje
	  * indicando si ya esta en el grafo o no
    *
    * @param ugraph es el grafo no dirigido sobre el cual esta el vertice a buscar
    *
    * @param dgraph es el digrafo sobre el cual esta el vertice a buscar
    *
    * @throws IOException si hay un error en la entrada de los datos por terminal
	  */
	  public static void buscarVertice(UndirectedGraph ugraph, DirectedGraph dgraph)
    throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
	    String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
	    boolean is = false;
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
    *
    * @param ugraph es el grafo no dirigido sobre el cual esta el lado a buscar
    *
    * @param dgraph es el digrafo sobre el cual esta el lado a buscar
    *
    * @throws IOException si hay un error en la entrada de los datos por terminal
	  */
	  public static void buscarLado(UndirectedGraph ugraph, DirectedGraph dgraph)
    throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del lado: ");
      String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
	    boolean is = false;
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
    *
    * @param ugraph es el grafo no dirigido sobre el cual esta el vertice a eliminar
    *
    * @param dgraph es el digrafo sobre el cual esta el vertice a eliminar
    *
    * @throws IOException si hay un error en la entrada de los datos por terminal
	  */
	  public static void eliminarVertice(UndirectedGraph ugraph, DirectedGraph dgraph)
    throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
      String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
	    boolean result = false;
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
    *
    * @param ugraph grafo no dirigido cuyos nodos se imprimiran
    *
    * @param dgraph drafo dirigido cuyos nodos se imprimiran
	  */
	  public static void imprimirVertices(UndirectedGraph ugraph, DirectedGraph dgraph){

      ArrayList<Node> vertices = new ArrayList<Node>();
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
	        System.out.println(vertices.get(i).toString());
	      }
	    }
	  }

	  /**
	  * Imprime en pantalla los id de todos los lados del grafo
    *
    * @param ugraph grafo no dirigido cuyos lados se imprimiran
    *
    * @param dgraph drafo dirigido cuyos lados se imprimiran
	  */
	  public static void imprimirLados(UndirectedGraph ugraph, DirectedGraph dgraph){
	    ArrayList<GraphEdges> lados = new ArrayList<GraphEdges>();
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
	        System.out.println(lados.get(i).toString());
	      }
	    }
	  }

	  /**
	  * Imprime en pantalla los id de todos los vertices adyacentes a un vertice.
	  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
    *
    * @param ugraph es el grafo no dirigido sobre el cual esta el vertice de interes
    *
    * @param dgraph es el digrafo sobre el cual esta el vertice de interes
    *
    * @throws IOException si hay un error en la entrada de los datos por terminal
	  *
	  */
	  public static void imprimirAdyacentes(UndirectedGraph ugraph, DirectedGraph dgraph)
    throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
      String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
	    ArrayList<Node> adj = new ArrayList<Node>();
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
    *
    * @param ugraph es el grafo no dirigido sobre el cual esta el vertice de interes
    *
    * @param dgraph es el digrafo sobre el cual esta el vertice de interes
    *
    * @throws IOException si hay un error en la entrada de los datos por terminal
	  */
	  public static void imprimirIncidentes(UndirectedGraph ugraph, DirectedGraph dgraph)
    throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
      String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
	    ArrayList<GraphEdges> inc = new ArrayList<GraphEdges>();
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
    * @param ugraph grafo no dirigido que se imprimira
    *
    * @param dgraph digrafo dirigido que se imprimira
	  */
	  public static void imprimirGrafo(UndirectedGraph ugraph, DirectedGraph dgraph){
	    imprimirVertices(ugraph, dgraph);
	    imprimirLados(ugraph, dgraph);
	  }

	  /**
	  * Imprime en pantalla el grado de un vertice.
	  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
    *
    * @param ugraph grafo no dirigido sobre el que estan los vertices
    *
    * @param dgraph drafo dirigido sobre el que estan los vertices
    *
    * @throws IOException si hay error en la entrada por consola
	  */
	  public static void grado(UndirectedGraph ugraph, DirectedGraph dgraph)
    throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
      String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
	    int g = 0;
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
    * @param dgraph digrafo dirigido sobre el que estan los nodos
    *
	  * @throws UnsupportedOperationException si se intenta aplicar sobre un grafo no dirigido
    *
    * @throws IOException si hay error en la entrada por consola
	  */
	  public static void gradoInterior(DirectedGraph dgraph)
	  throws UnsupportedOperationException, IOException{
	    if(type!=0){
	      throw new UnsupportedOperationException("Grado interior no esta definido para grafos no dirigidos");

	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
      String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
      int g = 0;
      try{
	       g = dgraph.outDegree(id);
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
    * @param dgraph digrafo dirigido sobre el que estan los nodos
    *
	  * @throws UnsupportedOperationException si se intenta aplicar sobre un grafo no dirigido
    *
    * @throws IOException si hay error en la entrada por consola
	  */
	  public static void gradoExterior( DirectedGraph dgraph)
	  throws UnsupportedOperationException, IOException{
	    if(type!=0){
	      throw new UnsupportedOperationException("Grado exterior no esta definido para grafos no dirigidos");

	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
      String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
	    int g = 0;
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
	  * Imprime en pantalla una lista de sucesores de un vertice.
	  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
    *
    * @param dgraph digrafo dirigido sobre el que estan los nodos
    *
	  * @throws UnsupportedOperationException si se intenta aplicar sobre un grafo no dirigido
    *
    * @throws IOException si hay error en la entrada por consola
	  */
	  public static void imprimirSucesores( DirectedGraph dgraph)
	  throws UnsupportedOperationException, IOException{
	    if(type!=0){
	      throw new UnsupportedOperationException("Sucesores no esta definido para grafos no dirigidos");
	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
      String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
      ArrayList<DNode> suc = new ArrayList<DNode>();
	    try{
	      suc = dgraph.successor(id);
	    }catch(NoSuchElementException i){
	      System.out.println("No se puede realizar la operacion, el nodo no esta en el grafo");
        return;
      }

	    System.out.print("Los sucesores del nodo son: ");
      for(int i=0; i<suc.size(); i++){
	      System.out.println(suc.get(i).getId());
	    }
	  }

    /**
	  * Imprime en pantalla una lista de predecesores de un vertice.
	  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
    *
    * @param dgraph digrafo dirigido sobre el que estan los nodos
    *
	  * @throws UnsupportedOperationException si se intenta aplicar sobre un grafo no dirigido
    *
    * @throws IOException si hay error en la entrada por consola
	  */
	  public static void imprimirPredecesores( DirectedGraph dgraph)
	  throws UnsupportedOperationException, IOException{
	    if(type!=0){
	      throw new UnsupportedOperationException("Predecesores no esta definido para grafos no dirigidos");
	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Introduzca el id del vertice: ");
      String id = "";
      try{
        id = br.readLine().trim();
      }catch(IOException e){
        throw new IOException("Error de entrada");
      }
      ArrayList<DNode> pred = new ArrayList<DNode>();
	    try{
	      pred = dgraph.successor(id);
	    }catch(NoSuchElementException i){
	      System.out.println("No se puede realizar la operacion, el nodo no esta en el grafo");
        return;
      }
	    System.out.print("Los predecesores del nodo son: ");
      for(int i=0; i<pred.size(); i++){
	      System.out.println(pred.get(i).getId());
	    }
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
    * @param ugraph es el grafo no dirigido que se utilizara a lo largo de la ejecucion
    *               del programa
    *
    * @param dgraph es el digrafo que se utilizara a lo largo de la ejecucion del programa
    *
    * @param transV es el parseador para los datos que se utilizara a lo largo del programa
    *
    * @param transE es el parseador para los datos que se utilizara a lo largo del programa
    *
	  * @throws IOException si hubo algun error en la ejecucion de cualquiera de los subprogramas
	  */
	  public static void menu(UndirectedGraph ugraph, DirectedGraph dgraph,
                                         Transformer transV, Transformer transE)
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
                     .append("19.- Imprimir lista de sucesores de un vertice (solo para digrafos) \n")
                     .append("20.- Imprimir lista de predecesores de un vertice (solo para digrafos) \n")
	                   .append("0.- Salir \n")
	                   .toString());

	     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     int n;
	     try{
	        n = Integer.parseInt(br.readLine());
	     }catch(NumberFormatException | IOException e){
	       throw new IOException("Entrada no valida, debe ser un numero");
	     }
	     try{
	       switch (n) {
	         case 1: crearGrafoArchivo(ugraph, dgraph, transV, transE);
	         break;
	         case 2: crearGrafoNoDirigido(ugraph, transV, transE);
	         break;
	         case 3: crearDigrafo(dgraph, transV, transE);
	         break;
	         case 4: agregarNodo(ugraph, dgraph, transV);
	         break;
	         case 5: agregarLado(ugraph, dgraph, transE);
	         break;
	         case 6: obtenerNumVertices(ugraph, dgraph);
	         break;
	         case 7: obtenerNumLados(ugraph, dgraph);
	         break;
	         case 8: buscarVertice(ugraph, dgraph);
	         break;
	         case 9: buscarLado(ugraph, dgraph);
	         break;
	         case 10: eliminarVertice(ugraph, dgraph);
	         break;
	         case 11: imprimirVertices(ugraph, dgraph);
	         break;
	         case 12: imprimirLados(ugraph, dgraph);
	         break;
	         case 13: imprimirAdyacentes(ugraph, dgraph);
	         break;
	         case 14: imprimirIncidentes(ugraph, dgraph);
	         break;
	         case 15: imprimirGrafo(ugraph, dgraph);
	         break;
	         case 16: grado(ugraph, dgraph);
	         break;
	         case 17: gradoInterior(dgraph);
	         break;
	         case 18: gradoExterior(dgraph);
	         break;
           case 19: imprimirSucesores(dgraph);
	         break;
           case 20: imprimirPredecesores(dgraph);
	         break;
	         case 0: salir();
	         break;
	       }
	     }catch(IllegalArgumentException | UnsupportedOperationException | IOException e){
	       throw new IOException(e);
	     }
	  }

	  /**
	  * Metodo principal de la clase.
	  *
    * @param args datos dados en la llamada al programa (no se hace nada con ellos)
    *
	  * @throws IOException si hubieron errores en la ejecucion y sale del programa
	  */
	  	public static void main(String[] args)
	  	throws IOException{
        UndirectedGraph ugraph = new UndirectedGraph(); // Se utilza para implementar el grafo no dirigido
        DirectedGraph dgraph = new DirectedGraph();  // Se utilza para implementar el digrafo
        Transformer transV = new StringTransformer(); // Parseador que nos permitira agregar datos genericos de vertices
        Transformer transE = new StringTransformer(); // Parseador que nos permitira agregar datos genericos de lados
		    try{
		        while(true)
		            menu(ugraph, dgraph, transV, transE);
		    }catch(IllegalArgumentException | IOException e){
		        System.out.println(e);
		        System.exit(1);
		    }
		}
	}
