class Test {

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
  public static void main (String args[]) {

    int i = 10;
    int j = 3;

    System.out.println(asignId(i,j));

  }

}
