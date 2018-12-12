import java.util.Stack;

class Test {
  public static String[] letters = {"A","B","C","D","E","F","G","H",
                                      "I","J","K","L","M","N","O","P",
                                      "Q","R","S","T","U","V","W","X",
                                      "Y","Z"};
  public static String asignId(int i, int j){
      String id = "";
      int col = j;
      //if(j == 0)
        //  id += "A";
      Stack<String> s = new Stack<String>();
      while (col!=0){
        if(col<26)
            col--;
          s.push(letters[col%26]);
          col = col/26;
      }
      while(!s.empty())
          id += s.pop();
      id += Integer.toString(i+1);
      return id;
  }
  public static void main (String args[]) {

    int i = 0;
    int j = 0;
    for(int k = 0; k<680;k++){
      System.out.println(asignId(i,j));
      j ++;
    }

  }

}
