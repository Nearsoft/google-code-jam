import java.util.*;
class Solution {

    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
      
        int test = scan.nextInt();
     
        for(int i = 0; i < test; i++) {
           
            int row = scan.nextInt();
          
            int column = scan.nextInt();
    
            System.out.println("Case #" + (i+1) + ": \n" +
    
            printer(row, column));
        } 

    }




    public static String printer(int row, int column){
        String chars = "";
        for(int i = 0; i < (row*2+1); i++) {
            for(int j = 0; j < (column*2+1); j++) {
                if(i <=1 && j <=1){
                    chars += ".";
                }else if( j % 2 == 0){
                    if( i % 2 == 0){
                        chars += "+";
                    }else{
                        chars += "|";
                    }
                }else{
                    if( i % 2 == 0){
                        chars += "-";
                    }else{
                        chars += ".";
                    }
                }
                
            }

            chars += "\n";
        }
        return chars;
    } 
}