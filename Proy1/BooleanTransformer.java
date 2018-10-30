/**
* Implementacion de parseador para boolean
*/
public class BooleanTransformer implements Transformer<Boolean>{
  /**
  * Retorna un booleano parseado
  *
  * @param dato es el booleano que se queria traducir
  */
  public Boolean Transform(String dato){
    return Boolean.parseBoolean(dato);
  }

}
