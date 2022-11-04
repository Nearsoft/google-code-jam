import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        //Dame # casos
        int cases = sc.nextInt();
        sc.nextLine();
        
        for(int a=1; a <= cases; a++){
            //Dame # dados
            int n = sc.nextInt();
            int lineaPosible = n;
            sc.nextLine();

            //Dame # de lados
            String s = sc.nextLine();
            String[] newStr = s.split("\\s+");
            
            //Variables
            int cont = 0;
            int numNext = 1;
                
            //String pasa a un array
            ArrayList<Integer> lados = new ArrayList<Integer>();
                
            //Array de string pasa a Arraylist de int
            for (int i = 0; i < newStr.length; i++) {
                lados.add(Integer.parseInt(newStr[i]));
            }
            
            //ordenar arraylist
            Collections.sort(lados);
                
            //verificar numeros
            for (int i = 0; i < lineaPosible; i++) {
                int temp2 = lados.get(i);
    	        if (numNext <= temp2) {
    		        cont= cont+1;
    		        numNext = numNext+1;
		        }
            }
            
            System.out.println("Case #"+a+": "+cont);
        }
    }
    
}