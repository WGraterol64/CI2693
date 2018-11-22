import java.io.IOException;
import java.lang.RuntimeException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
import java.util.*;

public class EncontrarAgua{
    static UndirectedGraph campus;

    public static boolean caseStudy(BufferedReader read, String file)
    throws IOException{
        String line = "";
        String caseName = "";
        int e = 0;
        int a=0;
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
            state.addEdge("sink"+Integer.toString(i), Integer.MAX_VALUE, Math.abs(u.getWeight())*25,
            building[0], "sink");
        }
        for(int i=0;i<a;i++){
            state.removeEdge(Integer.toString(i));
        }
        // Aqui viene el state.Bellman y la parte de imprimir
        return false;
    }

    public static void main(String[] args)
    throws IOException, IllegalArgumentException{
        if(args.length < 4){
            System.err.println("Uso: java  EncontrarAgua <grafo> <casos> <edif> <personas>");
        		return;
      	}
        Boolean load = campus.loadGraph(args[0]);
        if(!load)
            throw new IOException("No se pudo cargar el grafo");
        BufferedReader read = new BufferedReader(new FileReader(args[1]));
        Boolean end = false;
        while(!end){
            end = caseStudy(read, args[1]);
        }
    }
}
