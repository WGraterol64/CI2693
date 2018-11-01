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
  public UndirectedGraph ugraph; // Se utilza para implementar el grafo no dirigido
  public DirectedGraph dgraph;  // Se utilza para implementar el digrafo
  private int type; // El tipo del grafo sera 0 si es digrafo y sera 1 si es no dirigido
  private Transformer transV; // Parseador que nos permitira agregar datos genericos de vertices
  private Transformer transE; // Parseador que nos permitira agregar datos genericos de lados

  /**
  * Inicializa la creacion de un grafo desde un archivo
  * Le solicita al usuario el nombre del archivo donde esta el grafo.
  */
  public void crearGrafoArchivo(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el nombre del archivo donde esta el grafo: ");
    String archivo = br.readLine();
    archivo = archivo.trim();
    this.cargarGrafo(archivo);
  }

  /**
  * Metodo ue se encarga de crear un grafo que este en un archivo de texto.
  *
  * @param archivo es el archivo en el que se guarda la informacion del grafo
  *
  * @throws IllegalArgumentException si el formato del archivo no es valido
  */
  public void cargarGrafo(String archivo)
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
          this.type = 0;
        }else if(line == "N"){
          this.type = 1;
        }else{
          throw new IllegalArgumentException("Formato no valido");
          return;
        }
      }
    }
    try{
      if(this.type == 0){
        // Inizializacion si es un digrafo
        this.crearDigrafoVacio(vType, eType);
        this.dgraph.loadGraph(archivo);
      }else if(this.type == 1){
        // Inizializacion si es un grafo no dirigido
        this.crearGrafoNoDirigidoVacio(vType, eType);
        this.ugraph.loadGraph(archivo);
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
  public void crearGrafoNoDirigido(){
    this.type = 1;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el tipo de dato de los vertices: (B, D o S)");
    String v = br.readLine();
    String vType = v.trim();
    System.out.println("Introduzca el tipo de dato de las aristas: (B, D o S)");
    String l = br.readLine();
    String eType = l.trim();
    try{
      this.crearGrafoNoDirigidoVacio(vType, eType);
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
  public void crearGrafoNoDirigidoVacio(String vType, String eType)
  throws IllegalArgumentException{
    if(vType == "B" && eType =="B"){
      this.ugraph = new UndirectedGraph<Boolean,Boolean>();
      this.transV = new BooleanTransformer();
      this.transE = new BooleanTransformer();
    }else if(vType == "B" && eType =="D"){
      this.ugraph = new UndirectedGraph<Boolean, Double>();
      this.transV = new BooleanTransformer();
      this.transE = new DoubleTransformer();
    }else if(vType == "B" && eType =="S"){
      this.ugraph = new UndirectedGraph<Boolean,String> ();
      this.transV = new BooleanTransformer();
      this.transE = new StringTransformer();
    }else if(vType == "D" && eType =="B"){
      this.ugraph = new UndirectedGraph<Double,Boolean> ();
      this.transV = new DoubleTransformer();
      this.transE = new BooleanTransformer();
    }else if(vType == "D" && eType =="D"){
      this.ugraph = new UndirectedGraph<Double,Double>();
      this.transV = new DoubleTransformer();
      this.transE = new DoubleTransformer();
    }else if(vType == "D" && eType =="S"){
      this.ugraph = new UndirectedGraph<Double,String>();
      this.transV = new DoubleTransformer();
      this.transE = new StringTransformer();
    }else if(vType == "S" && eType =="B"){
      this.ugraph = new UndirectedGraph<String, Boolean>();
      this.transV = new StringTransformer();
      this.transE = new BooleanTransformer();
    }else if(vType == "S" && eType =="D"){
      this.ugraph = new UndirectedGraph<String,Double>();
      this.transV = new StringTransformer();
      this.transE = new DoubleTransformer();
    }else if(vType == "S" && eType =="S"){
      this.ugraph = new UndirectedGraph<String,String>();
      this.transV = new StringTransformer();
      this.transE = new StringTransformer();
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
  public void crearDigrafo()
    throws IllegalArgumentException{
    this.type = 0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el tipo de dato de los vertices: (B, D o S)");
    String v = br.readLine();
    String vType = v.trim();
    System.out.println("Introduzca el tipo de dato de los arcos: (B, D o S)");
    String l = br.readLine();
    String eType = l.trim();
    try{
      this.crearDigrafoVacio(vType, eType);
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
  public void crearDigrafoVacio(String vType, String eType)
  throws IllegalArgumentException{
    if(vType == "B" && eType =="B"){
      this.dgraph = new DirectedGraph<Boolean,Boolean>();
      this.transV = new BooleanTransformer();
      this.transE = new BooleanTransformer();
    }else if(vType == "B" && eType =="D"){
      this.dgraph = new DirectedGraph<Boolean, Double>();
      this.transV = new BooleanTransformer();
      this.transE = new DoubleTransformer();
    }else if(vType == "B" && eType =="S"){
      this.dgraph = new DirectedGraph<Boolean,String>();
      this.transV = new BooleanTransformer();
      this.transE = new StringTransformer();
    }else if(vType == "D" && eType =="B"){
      this.dgraph = new DirectedGraph<Double,Boolean>();
      this.transV = new DoubleTransformer();
      this.transE = new BooleanTransformer();
    }else if(vType == "D" && eType =="D"){
      this.dgraph = new DirectedGraph<Double,Double>();
      this.transV = new DoubleTransformer();
      this.transE = new DoubleTransformer();
    }else if(vType == "D" && eType =="S"){
      this.dgraph = new DirectedGraph<Double,String> ();
      this.transV = new DoubleTransformer();
      this.transE = new StringTransformer();
    }else if(vType == "S" && eType =="B"){
      this.dgraph = new DirectedGraph<String, Boolean>();
      this.transV = new StringTransformer();
      this.transE = new BooleanTransformer();
    }else if(vType == "S" && eType =="D"){
      this.dgraph = new DirectedGraph<String,Double>();
      this.transV = new StringTransformer();
      this.transE = new DoubleTransformer();
    }else if(vType == "S" && eType =="S"){
      this.dgraph = new DirectedGraph<String,String>();
      this.transV = new StringTransformer();
      this.transE = new StringTransformer();
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
  public void agregarNodo()
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
      if(this.type == 0){
        result = this.dgraph.addNode(id, this.transV.Transform(data), p);
      }else if(this.type == 1){
        result = this.ugraph.addNode(id, this.transV.Transform(data), p);
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
  public void agregarLado()
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
        if(this.type == 0){
          result = this.dgraph.addArc(id, this.transE.Transform(data.trim()), p, vInicial, vFinal);
        }else if(this.type == 1){
          result = this.ugraph.addArc(id, this.transE.Transform(data.trim()), p, vInicial, vFinal);
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
  public void obtenerNumVertices(){
    int n;
    if(this.type == 0){
      n = this.dgraph.numOfNodes();
    }else if(this.type == 1){
      n = this.ugraph.numOfNodes();
    }
    System.out.print("El numero de vertices en el grafo es: ");
    System.out.print(n);
    System.out.print("\n");
  }

  /**
  * Imrpime en pantalla el numero de lados que hay en el grafo. Le solicita
  * al usuario el id del lado para poder realizar la operacion.
  */
  public void obtenerNumLados(){
    int n;  
    if(this.type == 0){
      n = this.dgraph.numOfEdges();
    }else if(this.type == 1){
      n = this.ugraph.numOfEdges();
    }
    System.out.println("El numero de lados en el grafo es: ");
    System.out.print(n);
    System.out.print("\n");
  }

  /**
  * Le solicita al usuario el id de un vertice e imprime en pantalla un mensaje
  * indicando si ya esta en el grafo o no
  */
  public void buscarVertice(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine().trim();
    boolean is;
    if(this.type == 0){
      is = this.dgraph.isNode(id);
    }else if(this.type == 1){
      is = this.ugraph.isNode(id);
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
  public void buscarLado(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del lado: ");
    String id = br.readLine().trim();
    boolean is;
    if(this.type == 0){
      is = this.dgraph.isEdge(id);
    }else if(this.type == 1){
      is = this.ugraph.isEdge(id);
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
  public void eliminarVertice(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine().trim();
    boolean result;
    if(this.type == 0){
        result = this.dgraph.removeNode(id);
    }else if(this.type == 1){
        result = this.ugraph.removeNode(id);
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
  public void imprimirVertices(){
    
    ArrayList<Node> vertices;
    if(this.type == 0){
        vertices = this.dgraph.nodeList();
    }else if(this.type == 1){
        vertices = this.ugraph.nodeList();
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
  public void imprimirLados(){
    ArrayList<Node> lados;
    if(this.type == 0){
        lados = this.dgraph.edgeList();
    }else if(this.type == 1){
        lados = this.ugraph.edgeList();
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
  public void imprimirAdyacentes(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine().trim();
    ArrayList<Node> adj;
    try{
      if(this.type == 0){
            adj = this.dgraph.adjacency(id);
      }else if(this.type == 1){
            adj = this.ugraph.adjacency(id);
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
  public void imprimirIncidentes(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine().trim();
    ArrayList<Edge> inc;
    try{
        if(this.type == 0){
            inc = this.dgraph.incident(id);
        }else if(this.type == 1){
            inc = this.ugraph.incident(id);
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
  public void imprimirGrafo(){
    imprimirVertices();
    imprimirIncidentes();
  }

  /**
  * Imprime en pantalla el grado de un vertice.
  * Para realizar la operacion, le solicita al usuario el id del vertice de interes.
  */
  public void grado(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine().trim();
    int g;
    try{
      if(this.type == 0){
        g = this.dgraph.degree(id);
      }else if(this.type == 1){
        g = this.ugraph.degree(id);
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
  public void gradoInterior()
  throws UnsupportedOperationException{
    if(this.type!=0){
      throw new UnsupportedOperationException("Grado interior no esta definido para grafos no dirigidos");
      return;
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine().trim();
    int g;
    try{
      g = this.dgraph.inDegree(id);
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
  public void gradoExterior()
  throws UnsupportedOperationException{
    if(this.type!=0){
      throw new UnsupportedOperationException("Grado exterior no esta definido para grafos no dirigidos");
      return;
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine().trim();
    int g;
    try{
       g = this.dgraph.outDegree(id);
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
  public void salir(){
    System.out.println("Hasta luego!");
    System.exit(0);
  }

  /**
  * Imprime en pantalla un menu que le da acceso al usuario a todas las operaciones del grafo.
  * Solo se le permite mantener un grafo a la vez.
  *
  * @throws IOException si hubo algun error en la ejecucion de cualquiera de los subprogramas
  */
  public void menu()
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
         case 1: this.crearGrafoArchivo();
         break;
         case 2: this.crearGrafoNoDirigido();
         break;
         case 3: this.crearDigrafo();
         break;
         case 4: this.agregarNodo();
         break;
         case 5: this.agregarLado();
         break;
         case 6: this.obtenerNumVertices();
         break;
         case 7: this.obtenerNumLados();
         break;
         case 8: this.buscarVertice();
         break;
         case 9: this.buscarLado();
         break;
         case 10: this.eliminarVertice();
         break;
         case 11: this.imprimirVertices();
         break;
         case 12: this.imprimirLados();
         break;
         case 13: this.imprimirAdyacentes();
         break;
         case 14: this.imprimirIncidentes();
         break;
         case 15: this.imprimirGrafo();
         break;
         case 16: this.grado();
         break;
         case 17: this.gradoInterior();
         break;
         case 18: this.gradoExterior();
         break;
         case 0: this.salir();
         break;
       }
     }catch(IllegalArgumentException | UnsupportedOperationException e){
       throw new IOException("Hubieron errores en ejecucion por argumentos invalidos");
       return;
     }
  }

  /**
  * Metodo principal de la clase. Si el usuario desea cargar directamente el grafo
  * un archivo, puede dar el nombre del archivo al ejecutar el programa, de lo contrario
  * se le dirigira al menu de inmediato
  *
  * @param args es el nombre del archivo desde el que se quiere cargar el grafo
  *
  * @throws IOException si hubieron errores en la ejecucion y sale del programa
  */
  public static void main(String[] args)
  throws IOException{
    try{
      if(args.length > 0)
        cargarGrafo(args[0]);
      while(true){
        this.menu();
      }
    }catch(IllegalArgumentException | IOException e){
      System.out.println("Errores en la ejecucion: Saliendo...");
      System.exit(1);
    }
    }
}
