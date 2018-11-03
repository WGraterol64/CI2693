/**
* Implementacion de parseador para double
*/
public class DoubleTransformer<V> implements Transformer<Double>{

  /**
  * Retona un double parseado
  *
  * @param dato es el double que se queria traducir
  *
  * @throws IllegalArgumentException si el argumento no es un double
  */
  public Double Transform(String dato)
  throws IllegalArgumentException{
    try{
      return Double.parseDouble(dato);
    }catch(NumberFormatException e){
      throw new IllegalArgumentException("El dato dado no es un doble");
    }

  }
}
