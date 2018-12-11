import java.io.IOException;
import java.lang.RuntimeException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class USBDataFlow{
  public static int m,n;
  public static String [][] ssheet;
  public static int [][] ans;
  public static String[26] letters = {"A","B","C","D","E","F","G","H",
                                      "I","J","K","L","M","N","O","P",
                                      "Q","R","S","T","U","V","X","W",
                                      "Y","Z"};

  public static void storeSheet(String file)
  throws IllegalArgumentException{

      BufferedReader reader;
      try{
          reader = new BufferedReader(new FileReader(file.trim()));
      }catch(FileNotFoundException e){
          throw new IllegalArgumentException("Archivo no encontrado");
      }
      String line = reader.readLine().trim();
      String [] dim = line.split(" ");
      n = Integer.parseInt(dim[0]);
      m = Integer.parseInt(dim[1]);
      ssheet = new String[n][m];
      ans = new int [n][m];
      for(int i=0;i<n;i++){
          line = reader.readLine().trim().toUpperCase();
          String row = line.split(" ");
          if(row.length!=m)
              throw new IllegalArgumentException("El formato del archivo es invalido"); // Posiblemente tambien verificar columnas (?)
          for(int j=0; j<m; j++)
              ssheet[i][j] = row[j];
      }
  }

  public static void calcSheet(){

  }

  public static void printAns(){
      for(int i=0;i<n;i++){
          for(int j=0; j<m; j++){
            System.out.print(ans[i][j]);
            System.out.print(" ");
          }
          System.out.println("");
      }
  }

  public static String asignId(int i, int j){
      String id = "";
      int col = j;
      do {
          id += letters[col/26 - 1];
          col = col%26;
      } while (col>26);
      col += String.toString(i);
      return col;
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
      try{
          storeSheet(args);
          calcSheet();
          printAns();
      }catch(IllegalArgumentException | IOException e){
          System.out.println(e);
          System.exit(1);
      }
}
