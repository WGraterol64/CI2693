import java.lang.RuntimeException;
/**
* Implementacion de parseador para double
*/
public class IntegerTransformer<V> implements Transformer<Integer>{

  /**
  * @return un double parseado
  *
  * @param dato es el double que se queria traducir
  *
  * @throws IllegalArgumentException si el argumento no es un double
  */
  public Integer Transform(String dato)
  throws IllegalArgumentException{
    try{
      return Integer.parseInt(dato);
    }catch(NumberFormatException e){
      throw new IllegalArgumentException("El dato dado no es un entero");
    }

  }
}
