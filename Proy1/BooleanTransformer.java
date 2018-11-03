/**
* Implementacion de parseador para boolean
*/
public class BooleanTransformer<V> implements Transformer<Boolean>{
  /**
  * @return Un booleano parseado
  *
  * @param dato es el booleano que se queria traducir
  */
  public Boolean Transform(String dato){
  	System.out.println(Boolean.parseBoolean(dato));
    return Boolean.parseBoolean(dato);

  }

}
