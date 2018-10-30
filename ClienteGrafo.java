import java.lang.StringBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;

public class ClienteGrafo{
  public Graph<V,L> graph;
  public int type; // El tipo del grafo sera 0 si es digrafo y sera 1 si es no orientado
  public String vType;
  public String eType;
  
  public void crearGrafoArchivo(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el nombre del archivo donde esta el grafo: ");
    String archivo = br.readLine();
    archivo = archivo.trim();
    this.cargarGrafo(archivo);
  }

  public void cargarGrafo(String archivo){
		BufferedReader read = new BufferedReader(new FileReader(archivo));
    for(i=0; i<5; i++){
      // Excepciones de formato everywheeeereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
      String line = read.readLine();
      if(i==0){
        this.vType = line.trim();
      }else if(i==1){
        this.eType = line.trim();
      }else if(i==2){
        if(line == "D"){
          this.type = 0;
        }else if(line == "N"){
          this.type = 1;
        }
      }else if(i==3){
        int n = Integer.parseInt(line.trim());
      }else{
        int m = Integer.parseInt(line.trim());
      }
    }
    // aqui tendrian que ir los dos ciclos pero aja, los tipos de dato joden
    if(this.vType == "B" && this.eType =="B"){
      for(i=0;i<n;i++){
        line = read.readLine();
        line = line.trim();
        String[] node = line.split(" ");
        boolean result = this.graph.addNode(node[0], Boolean.parseBoolean(node[1]),
                                            Double.parseDouble(node[2]));
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
      for(i=0; i<m; i++){
        line = read.readLine();
        line = line.trim();
        String[] edge = line.split(" ");
        if(this.type == 0){
          result = this.graph.addArc(edge[0], Boolean.parseBoolean(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else if(this.type == 1){
          result = this.graph.addEdge(edge[0], Boolean.parseBoolean(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else{
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
    }else if(this.vType == "B" && this.eType =="D"){
      for(i=0;i<n;i++){
        line = read.readLine();
        line = line.trim();
        String[] node = line.split(" ");
        boolean result = this.graph.addNode(node[0], Boolean.parseBoolean(node[1]),
                                            Double.parseDouble(node[2]));
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
      for(i=0; i<m; i++){
        line = read.readLine();
        line = line.trim();
        String[] edge = line.split(" ");
        if(this.type == 0){
          result = this.graph.addArc(edge[0], Double.parseDouble(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else if(this.type == 1){
          result = this.graph.addEdge(edge[0],Double.parseDouble(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else{
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
    }else if(this.vType == "B" && this.eType =="S"){
      for(i=0;i<n;i++){
        line = read.readLine();
        line = line.trim();
        String[] node = line.split(" ");
        boolean result = this.graph.addNode(node[0], Boolean.parseBoolean(node[1]),
                                            Double.parseDouble(node[2]));
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
      for(i=0; i<m; i++){
        line = read.readLine();
        line = line.trim();
        String[] edge = line.split(" ");
        if(this.type == 0){
          result = this.graph.addArc(edge[0], edge[1], Double.parseDouble(edge[2]),
                                     edge[3], edge[4]);
        }else if(this.type == 1){
          result = this.graph.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]),
                                      edge[3], edge[4]);
        }else{
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
    }else if(this.vType == "D" && this.eType =="B"){
      for(i=0;i<n;i++){
        line = read.readLine();
        line = line.trim();
        String[] node = line.split(" ");
        boolean result = this.graph.addNode(node[0], Double.parseDouble(node[1]),
                                            Double.parseDouble(node[2]));
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
      for(i=0; i<m; i++){
        line = read.readLine();
        line = line.trim();
        String[] edge = line.split(" ");
        if(this.type == 0){
          result = this.graph.addArc(edge[0], Boolean.parseBoolean(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else if(this.type == 1){
          result = this.graph.addEdge(edge[0], Boolean.parseBoolean(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else{
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
    }else if(this.vType == "D" && this.eType =="D"){
      for(i=0;i<n;i++){
        line = read.readLine();
        line = line.trim();
        String[] node = line.split(" ");
        boolean result = this.graph.addNode(node[0], Double.parseDouble(node[1]),
                                            Double.parseDouble(node[2]));
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
      for(i=0; i<m; i++){
        line = read.readLine();
        line = line.trim();
        String[] edge = line.split(" ");
        if(this.type == 0){
          result = this.graph.addArc(edge[0], Double.parseDouble(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else if(this.type == 1){
          result = this.graph.addEdge(edge[0], Double.parseDouble(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else{
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
    }else if(this.vType == "D" && this.eType =="S"){
      for(i=0;i<n;i++){
        line = read.readLine();
        line = line.trim();
        String[] node = line.split(" ");
        boolean result = this.graph.addNode(node[0], Double.parseDouble(node[1]),
                                            Double.parseDouble(node[2]));
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
      for(i=0; i<m; i++){
        line = read.readLine();
        line = line.trim();
        String[] edge = line.split(" ");
        if(this.type == 0){
          result = this.graph.addArc(edge[0], edge[1], Double.parseDouble(edge[2]),
                                     edge[3], edge[4]);
        }else if(this.type == 1){
          result = this.graph.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]),
                                     edge[3], edge[4]);
        }else{
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
    }else if(this.vType == "S" && this.eType =="B"){
      for(i=0;i<n;i++){
        line = read.readLine();
        line = line.trim();
        String[] node = line.split(" ");
        boolean result = this.graph.addNode(node[0], node[1], Double.parseDouble(node[2]));
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
      for(i=0; i<m; i++){
        line = read.readLine();
        line = line.trim();
        String[] edge = line.split(" ");
        if(this.type == 0){
          result = this.graph.addArc(edge[0], Boolean.parseBoolean(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else if(this.type == 1){
          result = this.graph.addEdge(edge[0], Boolean.parseBoolean(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else{
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
    }else if(this.vType == "S" && this.eType =="D"){
      for(i=0;i<n;i++){
        line = read.readLine();
        line = line.trim();
        String[] node = line.split(" ");
        boolean result = this.graph.addNode(node[0], node[1], Double.parseDouble(node[2]));
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
      for(i=0; i<m; i++){
        line = read.readLine();
        line = line.trim();
        String[] edge = line.split(" ");
        if(this.type == 0){
          result = this.graph.addArc(edge[0], Double.parseDouble(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else if(this.type == 1){
          result = this.graph.addEdge(edge[0], Double.parseDouble(edge[1]),
                                     Double.parseDouble(edge[2]), edge[3], edge[4]);
        }else{
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
    }else if(this.vType == "S" && this.eType =="S"){
      for(i=0;i<n;i++){
        line = read.readLine();
        line = line.trim();
        String[] node = line.split(" ");
        boolean result = this.graph.addNode(node[0], node[1], Double.parseDouble(node[2]));
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
      for(i=0; i<m; i++){
        line = read.readLine();
        line = line.trim();
        String[] edge = line.split(" ");
        if(this.type == 0){
          result = this.graph.addArc(edge[0], edge[1], Double.parseDouble(edge[2]),
                                     edge[3], edge[4]);
        }else if(this.type == 1){
          result = this.graph.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]),
                                      edge[3], edge[4]);
        }else{
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
        if(!result){
          return;
          //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
        }
      }
    }
    System.out.println("Se ha creado el grafo exitosamente");
    }
  }

  public void crearGrafoNoDirigido(){
    this.type = 1;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el tipo de dato de los vertices: (B, D o S)");
    String v = br.readLine();
    this.vType = v.trim();
    System.out.println("Introduzca el tipo de dato de las aristas: (B, D o S)");
    String l = br.readLine();
    this.eType = l.trim();
    if(this.vType == "B" && this.eType =="B"){
      this.graph = new UndirectedGraph<boolean,boolean>();
    }else if(this.vType == "B" && this.eType =="D"){
      this.graph = new UndirectedGraph<boolean, double>();
    }else if(this.vType == "B" && this.eType =="S"){
      this.graph = new UndirectedGraph<boolean,String> ();
    }else if(this.vType == "D" && this.eType =="B"){
      this.graph = new UndirectedGraph<double,boolean> ();
    }else if(this.vType == "D" && this.eType =="D"){
      this.graph = new UndirectedGraph<double,double>();
    }else if(this.vType == "D" && this.eType =="S"){
      this.graph = new UndirectedGraph<double,String>();
    }else if(this.vType == "S" && this.eType =="B"){
      this.graph = new UndirectedGraph<String, boolean>();
    }else if(this.vType == "S" && this.eType =="D"){
      this.graph = new UndirectedGraph<String,double>();
    }else if(this.vType == "S" && this.eType =="S"){
      this.graph = new UndirectedGraph<String,String>();
    }
    System.out.println("Se ha creado el grafo exitosamente");
  }

  public void crearDigrafo(){
    this.type = 0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el tipo de dato de los vertices: (B, D o S)");
    String v = br.readLine();
    this.vType = v.trim();
    System.out.println("Introduzca el tipo de dato de los arcos: (B, D o S)");
    String l = br.readLine();
    this.eType = l.trim();
    if(this.vType == "B" && this.eType =="B"){
      this.graph = new DirectedGraph<boolean,boolean>();
    }else if(this.vType == "B" && this.eType =="D"){
      this.graph = new DirectedGraph<boolean, double>();
    }else if(this.vType == "B" && this.eType =="S"){
      this.graph = new DirectedGraph<boolean,String>();
    }else if(this.vType == "D" && this.eType =="B"){
      this.graph = new DirectedGraph<double,boolean>();
    }else if(this.vType == "D" && this.eType =="D"){
      this.graph = new DirectedGraph<double,double>();
    }else if(this.vType == "D" && this.eType =="S"){
      this.graph = new DirectedGraph<double,String> ();
    }else if(this.vType == "S" && this.eType =="B"){
      this.graph = new DirectedGraph<String, Boolean>();
    }else if(this.vType == "S" && this.eType =="D"){
      this.graph = new DirectedGraph<String,Double>();
    }else if(this.vType == "S" && this.eType =="S"){
      this.graph = new DirectedGraph<String,String>();
    }
    System.out.println("Se ha creado el grafo exitosamente");
  }

  public void agregarVertice(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine();
    id = id.trim();
    System.out.println("Introduzca el dato del vertice: ");
    String data = br.readLine();
    data = data.trim();
    if(this.vType == "B"){
      boolean dato = Boolean.parseBoolean(data);
    }else if(this.vType == "D"){
      double dato = Double.parseDouble(data);
    }else if(this.vType == "S"){
      String dato = data;
    }
    System.out.println("Introduzca el peso: ");
    String s = br.readLine();
    double p = Double.parseDouble(s.trim());
    boolean result = this.graph.addNode(id, dato, p);
    if(result){
      System.out.println("Se ha agregado exitosamente el vertice. ");
    }else{
      System.out.println("Ya existe un vertice con ese id. ");
    }
  }

  public void agregarLado(){
    if(this.type==0){
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Introduzca el id del arco: ");
      String id = br.readLine();
      id = id.trim();
      System.out.println("Introduzca el dato del arco: ");
      String data = br.readLine();
      data = data.trim();
      if(this.eType == "B"){
        boolean dato = Boolean.parseBoolean(data);
      }else if(this.eType == "D"){
        double dato = Double.parseDouble(data);
      }else if(this.eType == "S"){
        String dato = data;
      }
      System.out.println("Introduzca el peso: ");
      String s = br.readLine();
      double p = Double.parseDouble(s.trim());
      System.out.println("Introduzca el id del vertice inicial: ");
      String vInicial = br.readLine();
      vInicial = vInicial.trim();
      System.out.println("Introduzca el id del vertice final: ");
      String vFinal = br.readLine();
      vFinal = vFinal.trim();
      boolean result = this.graph.addArc(id, dato, p, vInicial, vFinal);
      if(result){
        System.out.println("Se ha agregado exitosamente el arco. ");
      }else{
        System.out.println("Ya existe un arco con ese id. ");
      }
    }else{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Introduzca el id de la arista: ");
      String id = br.readLine();
      id = id.trim();
      System.out.println("Introduzca el dato de la arista: ");
      String data = br.readLine();
      data = data.trim();
      if(this.eType == "B"){
        boolean dato = Boolean.parseBoolean(data);
      }else if(this.eType == "D"){
        double dato = Double.parseDouble(data);
      }else if(this.eType == "S"){
        String dato = data;
      }
      System.out.println("Introduzca el peso: ");
      String s = br.readLine();
      double p = Double.parseDouble(s.trim());
      System.out.println("Introduzca el id del vertice 1: ");
      String u = br.readLine();
      u = u.trim();
      System.out.println("Introduzca el id del vertice 2: ");
      String v = br.readLine();
      v = v.trim();
      boolean result = this.graph.addArc(id, dato, p, u, v);
      if(result){
        System.out.println("Se ha agregado exitosamente la arista. ");
      }else{
        System.out.println("Ya existe una arista con ese id. ");
      }
    }
  }

  public void obtenerNumVertices(){
    int n = this.graph.numOfNodes();
    System.out.print("El numero de vertices en el grafo es: ");
    System.out.print(n);
    System.out.print("\n");
  }

  public void obtenerNumLados(){
    int n = this.graph.numOfEdges();
    System.out.println("El numero de lados en el grafo es: ");
    System.out.print(n);
    System.out.print("\n");
  }

  public Node obtenerVertice(){
    //try{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Introduzca el id del vertice: ");
      String id = br.readLine();
      id = id.trim();
      Node v = this.graph.getNode(id);
      System.out.println("Se obtuvo el vertice exitosamente ");
      return v;
    //}catch(NoSuchElementException){
      //blaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
  //  }
  }

  public void buscarVertice(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine();
    id = id.trim();
    boolean is = this.graph.isNode(id);
    if(is){
      System.out.println("El nodo esta en el grafo ");
    }else{
      System.out.println("El nodo no esta en el grafo ");
    }
  }

  public void buscarLado(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del lado: ");
    String id = br.readLine();
    id = id.trim();
    boolean is = this.graph.isEdge(id);
    if(is){
      System.out.println("El lado esta en el grafo ");
    }else{
      System.out.println("El lado no esta en el grafo ");
    }
  }

  public void eliminarVertice(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine();
    id = id.trim();
    boolean result = this.graph.removeNode(id);
    if(result){
      System.out.println("Se ha eliminado el nodo exitosamente. ");
    }else{
      System.out.println("No existe un nodo con ese id. ");
    }
  }

  public void imprimirVertices(){
    ArrayList<Node> vertices = this.graph.nodeList();
    if(vertices.size() == 0){
      System.out.println("No hay nodos en el grafo. ");
    }else{
      System.out.println("Los nodos en el grafo son: ");
      for(i=0; i<vertices.size(); i++){
        System.out.println(vertices.get(i).id);
      }
    }
  }

  public void imprimirLados(){
    ArrayList<Node> lados = this.graph.edgeList();
    if(lados.size() == 0){
      System.out.println("No lados en este grafo. ");
    }else{
      System.out.println("Los lados en el grafo son: ");
      for(i=0; i<lados.size(); i++){
        System.out.println(lados.get(i).id);
      }
    }
  }

  public void imprimirAdyacentes(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine();
    id = id.trim();
    ArrayList adj = this.graph.adjacency(id);
    System.out.println("Los vertices adjacentes a " + id + "son: ");
    for(i=0; i<adj.size(); i++){
      System.out.println(adj.get(i).id);
    }
  }

  public void imprimirIncidentes(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine();
    id = id.trim();
    ArrayList inc = this.graph.incident(id);
    System.out.println("Los lados adjacentes a " + id + "son: ");
    for(i=0; i<inc.size(); i++){
      System.out.println(inc.get(i).id);
    }
  }

  public void grado(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine();
    id = id.trim();
    int g = this.graph.degree(id);
    System.out.print("El grado del vertice es: ");
    System.out.print(g);
    System.out.print("\n");
  }

  public void gradoInterior(){
    if(this.type!=0){
      System.out.println("Grado interior no esta definido para grafos no dirigidos");
      return;
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine();
    id = id.trim();
    int g = this.graph.inDegree(id);
    System.out.print("El grado interior del vertice es: ");
    System.out.print(g);
    System.out.print("\n");
  }

  public void gradoExterior(){
    if(this.type!=0){
      System.out.println("Grado exterior no esta definido para grafos no dirigidos");
      return;
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Introduzca el id del vertice: ");
    String id = br.readLine();
    id = id.trim();
    int g = this.graph.outDegree(id);
    System.out.print("El grado exterior del vertice es: ");
    System.out.print(g);
    System.out.print("\n");
  }

  public void salir(){
    System.out.println("Hasta luego!");
    System.exit(0);
  }


  // Como hacer imprimir grafo?????????????????????????????????????????




  public void menu(){
    System.out.println(new StringBuilder("Introduzca la operacion que desee realizar: \n")
                   .append("1.- Crear Grafo desde archivo \n")
                   .append("2.- Crear Grafo no dirigido vacio \n")
                   .append("3.- Crear Digrafo vacio \n")
                   .append("4.- Agregar Vertice \n")
                   .append("5.- Agregar Lado \n")
                   .append("6.- Obtener numero de vertices \n")
                   .append("7.- Obtener numero de lados \n")
                   .append("8.- Obtener un vertice \n")
                   .append("9.- Ver si esta un vertice \n")
                   .append("10.- Ver si esta lado \n")
                   .append("11.- Eliminar vertice \n")
                   .append("12.- Imprimir lista de vertices \n")
                   .append("13.- Imprimir lista de lados \n")
                   .append("14.- Imprimir lista de vertices adyacentes a un vertice \n") // recordar lanzar el NoSuchElementException
                   .append("15.- Imprimir lista de lados incidentes a un vertice \n") // recordar lanzar el NoSuchElementException
                   .append("16.- Imprimir Grafo \n") // Falta la funcion de clonar
                   .append("17.- Calcular grado de un vertice \n")
                   .append("18.- Calcular el grado interior de un vertice (solo para digrafos) \n")
                   .append("19.- Calcular el grado externo de un vertice (solo para digrafos) \n")
                   .append("20.- Salir \n")
                   .toString());

     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     int n = Integer.parseInt(br.readLine());    // Verificar si aqui puede salir una excepcion
     switch (n) {
      case 1: this.crearGrafoArchivo();
              break;
      case 2: this.crearGrafoNoDirigido();
              break;
      case 3: this.crearDigrafo();
              break;
      case 4: this.agregarVertice();
              break;
      case 5: this.agregarLado();
              break;
      case 6: this.obtenerNumVertices();
              break;
      case 7: this.obtenerNumLados();
              break;
      case 8: this.obtenerVertice();
              break;
      case 9: this.buscarVertice();
              break;
      case 10: this.buscarLado();
              break;
      case 11: this.eliminarVertice();
              break;
      case 12: this.imprimirVertces();
              break;
      case 13: this.imprimirLados();
              break;
      case 14: this.imprimirAdyacentes();
              break;
      case 15: this.imprimirIncidentes();
              break;
      case 16: this.imprimirGrafo();
              break;
      case 17: this.grado();
              break;
      case 18: this.gradoInterior();
              break;
      case 19: this.gradoExterior();
              break;
      case 20: this.salir();
              break;
     }
  }

  public static void main(String[] args){
    if(args.length > 0)
			cargarGrafo(args);
    while(true){
      this.menu();
    }
	}
}
