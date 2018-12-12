/*
* Jesus De Aguiar 15-10360
* Wilfredo Graterol 15-10639
* Proyecto III - CI2693 Sep-Dic 2018
*/

import java.io.IOException;
import java.lang.RuntimeException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.ArrayList;
/**
* Clase principal del proyecto en su fase 2, se encarga de procesar la hoja de calculo
*
*/
public class USBDataFlow{
   
    public static int m,n; // Dimensiones de la hoja de calculo
    public static DirectedGraph graph; // Grafo de operaciones de la hoja de calculo
    public static DNode [][] ssheet; // Hoja de calculo

    /**
    * Metodo que se encarga de crear los nodos del grafo y la hoja de calculo
    * basados en el archivo. Cada nodo tendra por id su casilla, por dato su expresion
    * y por peso el resultado de su operacion.
    *
    * @param file es el archivo donde esta la hoja de calculo
    * @throws IllegalArgumentException si el archivo de entrada no existe o no es valido
    */
    public static void storeSheet(String file)
    throws IllegalArgumentException, IOException{
    // Declaramos un reader.
    BufferedReader reader;
        try{
           reader = new BufferedReader(new FileReader(file.trim()));
        }catch(FileNotFoundException e){
            throw new IllegalArgumentException("Archivo no encontrado");
        }
        // Leemos el tamano de la hoja de calculo
        String line = reader.readLine().trim();
        String [] dim = line.split(" ");
        n = Integer.parseInt(dim[0]);
        m = Integer.parseInt(dim[1]);
        // Creamos una matriz para almacenar la entrada
        ssheet = new DNode[n][m];
        // Leemos los elementos de la hoja de calculo
        for(int i=0;i<n;i++){
            // Tomamos una linea, convertimos todas las letras a mayusculas y separamos por espacios
            line = reader.readLine().trim().toUpperCase();
            String[] row = line.split(" ");
            // Revisamos si el numero de casillas es m
            if(row.length!=m)
                throw new IllegalArgumentException("El formato del archivo es invalido"); 
            // Creamos los nodos con la informacion de cada casilla
            for(int j=0; j<m; j++){
                // Creamos un nodo, lo agregamos al grafo y a la hoja de calculo
                // El id del nodo sera su posicion letra-numero en la hoja de calculo,
                // el dato sera el contenido de la casilla almacenado como un string,
                // y el peso sera inicialmente 0
                DNode node = new DNode(asignId(i,j), row[j].replace("\\=",""), 0);
                graph.addNode(node);
                ssheet[i][j] = node;
            }
        }
    }

    /**
    * Metodo que, para cada casilla que contenga una formula, reemplaza
    * los indicadores letra-numero de otras casillas por los valores previamente
    * calculados de ellas. Por ejemplo, si la formula de una casilla es
    * =A1+A2, el metodo busca los valores de A1 y A2 previamente calculados y los reemplaza
    * @param v Nodo que representa la casilla
    * @return El resultado de sustituir los valores en la formula
    **/
    public static String getValues(DNode v){

        // Eliminamos el igual de la formula
        String exp = v.getData().replaceAll("\\=","");
        String id = v.getId();
        ArrayList<DNode> predecessor = graph.predecessor(id); // Los predecesores de v son quienes estan en la formula
        for(DNode u : predecessor){
            String uId = u.getId();
            // Construimos una expresion regular que encuentra todas las ocurrecias del identificador de un predecesor
            String regex = "(?<![A-Z])"+uId+"(?![0-9])";
            // Reemplazamos estas ocurrencias por el valor calculado
            exp = exp.replaceAll(regex,String.valueOf(u.getWeight()));
        }
        return exp;

    }

    /**
    * Metodo que se encarga de crear el grafo de precendencias, encontrar un orden topologica
    * y evaluar las operaciones de la hoja de calculo
    *
    * @throws IllegalArgumentException si hay algun ciclo en el grafo
    */
    public static void doTheMath()
        throws IllegalArgumentException{
        
        // Armamos el grafo de precedencia entre las celdas de la hoja de calculo

        // Recorremos todos los nodos de la hoja de calculo
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                // Esta expresion regular se utiliza para extraer las celdas de las que cada celda depende.
                // Por ejemplo, la expresion =A1+3*MIN(A3,B2) se convierte en un arreglo [A1,A3,B2]
                String[] exp = ssheet[i][j].getData().split("[\\*\\+\\-,\\(\\)\\=]|MAX|SUM|MIN|(?<![A-Z])\\d+");
                // Para todas estas expresiones, agregamos un arco desde su nodo representante al nodo actual,
                // ya que la casilla actual depende del resultado de esa celda.
                for(String s : exp){
                    if(graph.isNode(s)){
                        String t = asignId(i, j);
                        graph.addArc(s + "-" + t, "", 0, s, t);
                    }
                }
            }
        }
        // Realizamos un orden topologico para determinar el orden en que se calcularan las casillas
        // El resultado es retornado en un stack
        Stack<DNode> order = graph.topoSort();
        // Evaluamos las expresiones en orden
        while(!order.isEmpty()){

            DNode v = order.pop();                  // Extraemos un nodo
            char c = v.getData().charAt(0);         // Revisamos si el primer caracter es un '='. Si es asi, es una formula
            if(c=='='){                              // que se debe evaluar
                // Reemplazamos cada Id de las celdas por su valor previamente calculado
                String exp = getValues(v);
                // Evaluamos la expresion
                v.setWeight(Evaluador.evaluate(exp));
            }else{
                // Si no es una formula, es simplemente un numero y no hace falta evaluar
                v.setWeight(Integer.parseInt(v.getData()));
            }
            
        }
    }

    /**
    * Metodo que se encarga de imprimir el resultado de la hoja de hojadecalculo
    *
    */
    public static void printAnswer(){

        // Para todas las casillas, imprimimos el peso
        for(int i=0;i<n;i++){
            for(int j=0; j<m; j++){
                System.out.print(ssheet[i][j].getWeight());
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    /**
    * Funcion que se encarga de asignar a cada nodo, su id dependiendo de en que
    * casilla de la hoja de calculo este.
    *
    * @param i fila en la que esta el nodo (empezando desde 0)
    * @param j fila en la que esta el nodo (empezando desde 0)
    * @return un id unico para el nodo en el formato de ojas de calculo
    */
    public static String asignId(int i, int j){

        Stack<String> stack = new Stack<String>();
        j++;
        while(j-- > 0){
            stack.push(Character.toString((char)(65+j%26)));
            j /= 26;
        }

        String out = "";
        while(!stack.isEmpty()){
            out += stack.pop();
        }

        out += String.valueOf(i+1);

        return out;
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
        if(args.length < 1){
  			System.err.println("Uso: java USBDataFlow <hojadecalculo>");
  			return;
  		}
        graph = new DirectedGraph();
        try{
            storeSheet(args[0]); // Cargamos la hoja de calculo
            doTheMath();         // Evaluamos las operaciones
            printAnswer();        // Imprimimos la respuesta
        }catch(IllegalArgumentException | IOException e){
            System.out.println(e);
            System.exit(1);
        }
}
}
