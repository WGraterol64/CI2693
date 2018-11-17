/**
* Interfaz para un parseador generico
*/
public interface Transformer<E>{

  public E Transform(String dato);

}
