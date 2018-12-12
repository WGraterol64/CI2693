/*
* Jesus De Aguiar 15-10360
* Wilfredo Graterol 15-10639
* Proyecto III - CI2693 Sep-Dic 2018
*/
import java.lang.Integer;
import java.lang.Character;
import java.lang.String;
import java.lang.RuntimeException;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
/**
* Clase Evaluador, utilizada para evaluar expresiones aritmeticas y retornar su resultado
**/
public class Evaluador{

	/**
	* Metodo que retorna el minimo entre dos enteros
	* @param a Primer entero 
	* @param b Segundo entero
	* @return Variable entera, el minimo entre a y b
	**/
	private static int MIN(int a, int b){
		return a<=b ? a : b;
	}
	/**
	* Metodo que retorna el maximo entre dos enteros
	* @param a Primer entero 
	* @param b Segundo entero
	* @return Variable entera, el maximo entre a y b
	**/
	private static int MAX(int a, int b){
		return a>=b ? a : b;
	}
	/**
	* Metodo que retorna el valor de la funcion SUM de la hoja de calculo
	* @param n Entero a evaluar
	* @return Valor de la funcion SUM de la hoja de calculo
	**/
	private static int SUM(int n){
		return n>=0 ? (n*(n+1))/2 : (n*(-n+1))/2;
	}

	/**
	* Metodo que realiza un recorrido postOrder sobre el evaluation tree
	* Sirve para realizar las operaciones sobre el evaluation tree y calcular
	* el resultado de evaluar todas las expresiones aritmeticas
	* @param v Nodo actual
	* @param tree Arbol evaluador
	**/
	private static void postOrder_evaluate(DNode v,DirectedGraph tree){

		// Extraemos el tipo de nodo
		char type = v.getData().charAt(0);
		// Si es un nodo que representa un digito, llegamos a una hoja y retornamos
		if(type == 'd')
			return;

		// Estamos en un operador, revisamos los hijos
		ArrayList<DNode> vSucessors = tree.successor(v.getId());
		
		// Extraemos el primer hijo y evaluamos recursivamente sobre el
		DNode u = vSucessors.get(0);
		postOrder_evaluate(u,tree);
		int a = u.getWeight();

		// Si el operador es unario, realizamos la evaluacion y retornamos
		if(type == 's'){
			v.setWeight(SUM(a));
			return;
		}
		if(type == '-'){
			v.setWeight(-a);
			return;
		}

		// El operador es binario, luego, extraemos el segundo nodo y evaluamos recursivamente sobre el
		DNode w = vSucessors.get(1);
		postOrder_evaluate(w,tree);
		int b = w.getWeight();
		// Realizamos las evaluaciones de la operacion correspondiente y retornamos
		if(type == '+'){
			v.setWeight(a+b);
			return;
		}
		if(type == '*'){
			v.setWeight(a*b);
			return;
		}
		if(type == 'M'){
			v.setWeight(MAX(a,b));
			return;
		}
		if(type == 'm'){
			v.setWeight(MIN(a,b));
			return;
		}
		return;
	}

	/**
	* Metodo principal para evaluar el evaluation tree
	* @param tree Arbol a evaluar
	* @return Entero que representa el resultado de la evaluacion
	**/
	private static int evaluateTree(DirectedGraph tree){

		int ret = 0;
		for( DNode v : tree.nodeSet)
			// Buscamos la raiz y realizamos el recorrido desde ahi
			if(tree.inDegree(v.getId()) == 0){ 
				postOrder_evaluate(v,tree);
				ret = v.getWeight();
				break;
			}
		return ret;	
	}

	/**
	* Metodo utilizado para construir un evaluation tree. Este es un arbol
	* donde cada nodo representa o bien una operacion o un valor, que luego
	* puede ser recorrido para evaluar. 
	* @param expression Cola que contiene una expresion en notacion posfix
	* @return el arbol creado
	**/
	public static DirectedGraph buildTree(Queue<Character> expression){
		
		// Creamos el arbol
		DirectedGraph tree = new DirectedGraph();
		// Cola util para el algoritmo
		Queue<DNode> queue = new LinkedList<>();
		// Numero util para asignarle un id a los nodos
		int numOfNodes = 0;
		while(!expression.isEmpty()){
			
			// Extraemos un token de la lista
			char token = expression.poll();
			// Declaramos el nodo a crear
			DNode v;
			
			if(token == '|'){
				String number = "";
				do{
					number += String.valueOf(expression.poll());
				}while(expression.peek()!='|');
				expression.poll();
				v = new DNode(String.valueOf(numOfNodes++),"d",Integer.parseInt(number));
			}
			// Nodo de operador
			else
				v = new DNode(String.valueOf(numOfNodes++),String.valueOf(token),0);

			// Lo agregamos al arbol
			tree.addNode(v);
			// Agregamos el nodo a la nueva cola
			queue.add(v);
		}
		// Stack util para construir el arbol
		Stack<DNode> stack = new Stack<>();
		// Variable util para identificar cada arco
		int numOfArcs = 0;
		// Mientras queden nodos en la cola
		while(!queue.isEmpty()){
			// Extraemos un nodo y verificamos que token representa
			DNode v  = queue.poll();
			char token = v.getData().charAt(0);
			
			// Si es un digito, lo agregamos a la pila
			if(token == 'd')
				stack.push(v);
			
			else{
				// Operadores unarios, solo necesitan un operando
				if(token == '-' || token == 's'){
					DNode u = stack.pop();
					tree.addArc(String.valueOf(numOfArcs++),"",0,v.getId(),u.getId());
					stack.push(v);
				// Operadores binarios
				}else{
					DNode w = stack.pop();
					DNode u = stack.pop();
					tree.addArc(String.valueOf(numOfArcs++),"",0,v.getId(),u.getId());
					tree.addArc(String.valueOf(numOfArcs++),"",0,v.getId(),w.getId());
					stack.push(v);
				}				
			}
		}

		return tree;
	}

	/**
	* Metodo auxiliar para evaluar si un caracter representa a un operador
	* @param c Caracter a evaluar
	* @return Booleano que evalua si el caracter representa a un operador
	**/
	private static boolean isOperator(char c){
		return c == '+' || c == '-' || c == '*';
	}

	/**
	* Metodo auxiliar para obtener la precedencia de un operador
	* @param c Caracter a evaluar
	* @return Entero que representa la precendencia del operador
	**/
	private static int getPrecedence(char c){
		if(c == '+') return 0;
		if(c == '*') return 1;
		return 2; // c == '-'
	}

	/**
	* Algoritmo se Shunting Yard. Este es un algoritmo propuesto
	* por Dijkstra que transforma una expresion de notacion infix
	* a notacion posfix. En internet puede encontrarse una mejor documentacion
	* del algoritmo.
	* @param s String que contiene la expresion
	* @return Cola de caracteres que contiene la operacion en posfix notation
	**/
	public static Queue<Character> shuntingYard(String s){

		// Stack util para el algoritmo
		Stack<Character> stack = new Stack<>();
		Queue<Character> queue = new LinkedList<>();

		// Recorremos todos los elementos de la expresion
		for(int i = 0; i < s.length(); i++){
			// c es el caracter actual
			char token = s.charAt(i);
			// Si es un numero, se agrega a la salida.
			// Utilizamos los caracteres | para delimitar los digitos
			// de dos numeros distintos.
			if(Character.isDigit(token)){
				queue.add('|');
				char c = token;
				do{
					queue.add(c);
					i++;
					if(i == s.length()) break;
					c = s.charAt(i);
				}while(Character.isDigit(c));
				queue.add('|');
				i--;
			}
				
			// Si es un token de funcion, lo agregamos a la pila
			if(Character.isLetter(token))
				stack.push(token);
			// Si el caracter es una coma, terminamos esa subexpresion
			if(token == ',')	
				while(stack.peek() != '(')
					queue.add(stack.pop());  // Sacamos de la pila y lo guardamos en la cola
			
			// Consideramos el caso cuando el token es un operador
			if(isOperator(token)){
				if(token == '+' || token == '*'){
					if(!stack.isEmpty()){
						while(isOperator(stack.peek()) && getPrecedence(token) <= getPrecedence(stack.peek())){
						queue.add(stack.pop());
						if(stack.isEmpty())
							break;
						}
					}
					
				}
				stack.push(token);
			}
			// Si es un parentesis abierto, simplemente lo agregamos a la pila
			if(token == '(')
				stack.push(token);
			// Si es un parentesis cerrados, terminamos esa subexpresion
			if(token == ')'){
				while(stack.peek() != '(')
					queue.add(stack.pop());  	// Agregamos los operadores a la salida hasta conseguir el parentesis abierto
				stack.pop(); 					// Retiramos de la pila el parentesis abierto
				// Si el tope de la pila es un token de funcion, lo agregamos a la salida
				if(Character.isLetter(stack.peek())) 
					queue.add(stack.pop());
			}
		}
		while(!stack.isEmpty()){
			queue.add(stack.pop());
		}

		return queue;
	}

	/**
	* Metodo que recibe una expresion en un string y devuelve el resultado de evaluar la expresion
	* en un entero
	* @param input Expresion a evaluar
	* @return Entero con el resultado de la evaluacion
	**/
	public static int evaluate(String input){

		// Simplificamos la sintaxis de la expresion.
		input = input.replaceAll("MAX\\(","M("); // Cambiamos todas las ocurrencias de "MAX" por "M"
		input = input.replaceAll("MIN\\(","m("); // Cambiamos todas las ocurrencias de "MIN" por "m"
		input = input.replaceAll("SUM\\(","s("); // Cambiamos todas las ocurrencias de "SUM" por "s"

		// Hacemos distincion de los "-" unarios y binarios.
		input = input.replaceAll("(?<=[0-9\\)])-","+-");

		// Ejecutamos el algoritmo Shunting Yard de Dijkstra para transformar la expresion
		// de infix notation a posfix notation. Luego, construimos un evaluation tree con
		// esta expresion.
		DirectedGraph evaluationTree = buildTree(shuntingYard(input)); 

		// Evaluamos la expresion y obtenemos el resultado.
		int result = evaluateTree(evaluationTree);

		return result;
	}


	/**
	* Metodo principal de la clase utilizado para cargar un archivo que contenga una lista
	* de expresiones y evaluar cada una de ellas.
	* @param args Archivo a cargar
	**/
	public static void main(String[] args) throws IllegalArgumentException, IOException{

		if(args.length < 1)
			System.err.println("Error. Uso: Java Evaluador <archivo>");

		// Cargamos el lector del archivo
		BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(args[0]));
        }catch(FileNotFoundException e){
            throw new IllegalArgumentException("Archivo no encontrado");
        }


        while(true){
        	// Leemos una linea
        	String line = reader.readLine();
      		// Si esta vacia, terminamos
        	if(line == null)
        		break;
        	// Si no, evaluamos la expresion
        	System.out.println(evaluate(line));
        }		
	}
}