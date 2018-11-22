import java.io.IOException;
import java.lang.RuntimeException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
import java.util.*;

/**
* Cliente del proyecto 2 de CI2693:
* Autores:
*     Jesus De Aguiar 15-10360
*     Wilfredo Graterol 15-10639
**/
public class EncontrarAgua{

    /**
    * Funcion que carga un caso del archivo de casos, lo resuelve y lo imprime
    * @param campus es el grafo original de la universidad en condiciones ideales
    * @param read es el lector del archivo de texto de los casos
    * @param file es el archivo de los casos
    * @param source es el edificio desde el cual se quiere iniciar el recorrido
    * @param people es el numero de personas que se desean mover desde source
    * @throws IOException si hay algun error leyendo el archivo de casos
    * @return un booleano indicando si se llego al final del archivo o no
    **/
    public static boolean caseStudy(UndirectedGraph campus, BufferedReader read, String file, String source, String people)
    throws IOException{

        String line = "";
        String caseName = "";
        int e = 0;
        int a = 0;

        UndirectedGraph state = campus.clone();
        state.addNode("sink", 0, 0);

        for(int i = 0; i<3; i++){
            line = read.readLine();
            if(line == null)
                return true;
            if(i==0){
                caseName = line.trim();
            }else if(i==1){
                e = Integer.parseInt(line.trim());
            }else{
                a = Integer.parseInt(line.trim());
            }
        }

        for(int i=0;i<e;i++){
            line = read.readLine();
            String[] building = line.trim().split(" ");
            if(building.length > 1){
                state.changeFloor(building[0], Integer.parseInt(building[1]));
            }
            UNode u = state.getNode(building[0]);
            state.addEdge("sink"+Integer.toString(i), u.getCap(), Math.abs(u.getWeight())*25,
            building[0], "sink");
        }
        for(int i=0;i<a;i++){
            line = read.readLine().trim();
            state.removeEdge(line);
        }

        System.out.println(caseName);
        state.solve(source, Integer.parseInt(people));

        return false;
    }
    /**
    * Metodo principal del cliente, recibe los datos necesarios para correr,
    * en el metodo se carga el grafo y se inicializa el analisis de los casos.
    * El programa seguira hasta que no se haya terminado de leer el archivo de casos
    * @param args Es un arreglo de strings que debe contener el archivo a
    * partir del cual se cargara el grafo, el archivo con los casos, el edificio
    * de partida y el numero de personas a mover.
    * @throws IOException si hay algun error cargando los datos de entrada
    * @throws IllegalArgumentException si los datos de entrada no son validos
    **/
    public static void main(String[] args)
    throws IOException, IllegalArgumentException{
        if(args.length < 4){
            System.err.println("Uso: java  EncontrarAgua <grafo> <casos> <edif> <personas>");
        		return;
      	}
        UndirectedGraph campus = new UndirectedGraph();
        boolean load = campus.loadGraph(args[0]);
        if(!load)
            throw new IOException("No se pudo cargar el grafo");
        BufferedReader read = new BufferedReader(new FileReader(args[1]));
        boolean end = false;
        while(!end){
            end = caseStudy(campus, read, args[1], args[2], args[3]);
        }
    }
}
