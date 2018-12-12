import java.util.Stack;

class Test {
  
    public static String asignId(int i, int j){

        Stack<String> stack = new Stack<String>();
        j++;
        while(j-- > 0){
            stack.push(Character.toString((char)(65+j%26)));
            j /= 26;
        }

        String out = "";
        while(!stack.isEmpty()){
            out += stack.pop();
        }

        out += String.valueOf(i+1);

        return out;
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
