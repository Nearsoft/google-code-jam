import java.io.*;
import java.util.*;

public class Dijkstra{
    
	public static void main(String arg[]){
		new Dijkstra();
	}
	
	public Dijkstra(){
        
        //Set the number of tests to be conducted

        Scanner miScanner = new Scanner(System.in);

        int T = Integer.parseInt(miScanner.nextLine());


        // Do T tests
        for(int numberOfCase = 1; numberOfCase <= T; numberOfCase++){

            String input1 = miScanner.nextLine();

            String[] splitted = input1.split(" ");

            int L = Integer.valueOf(splitted[0]);
            int X = Integer.valueOf(splitted[1]);

            String input2 = miScanner.nextLine();

            String word = "";

            for(int i = 0; i < X; i++) {
                word += input2;
            }

            if(!reduceWordTo1(word).equals("-1")) System.out.println("Case #" + numberOfCase + ": NO");
            else if(seekijk(word)) System.out.println("Case #" + numberOfCase + ": YES");
            else System.out.println("Case #" + numberOfCase + ": NO");
        }
    }

    public boolean seekijk(String word) {
                
        String tmpi = "1";
        String tmpj = "1";
        String tmpk = "1";
        String[] splitted = word.split("");

        int i = 0;
        int j = 0;
        int k = 0;
        
        while(i < word.length()) {
            
            tmpi = quaternionMult(tmpi, splitted[i]);

            if (tmpi.equals("i")) {
                
                j = i + 1;
                
                while(j < word.length()) {
            
                    tmpj = quaternionMult(tmpj, splitted[j]);

                    if (tmpj.equals("j")) {

                        k = j + 1;
                        
                        while(k < word.length()) {
            
                            tmpk = quaternionMult(tmpk, splitted[k]);

                            if (tmpk.equals("k")) {
                                return true;
                            }
                            k++;
                        }
                    }
                    j++;
                }
            }
            i++;
        }
        
        return false;
    }
    
    
    public String reduceWordTo1(String word) {

        // Split the input string char by char
        // for the for loop to multiply them
        // in succession.

        String[] splitWord = word.split("");
        String reduced = splitWord[0];
        
        for(int i = 1; i < word.length(); i++) {
            reduced = quaternionMult(reduced, splitWord[i]);  
        }
        return reduced;
    }
    
    // Quaternions multiplication. It uses the table of multiplication,
    // when argumets are recived the algorithm finds its place in the
    // table and returns the correct string for the answer.
    
    public String quaternionMult(String a, String b) {

        String[] indices = {"1", "i", "j", "k", "-1", "-i", "-j", "-k"};

        int ia = -1;
        int ib = -1;

        for (int i = 0; i < indices.length; i++) {
            if(a.equals(indices[i])) ia = i;
            if(b.equals(indices[i])) ib = i;
        }

        if(ia == -1 || ib == -1) return "";

        String[][] mult = {{"1", "i", "j", "k", "-1", "-i", "-j", "-k"},
                           {"i", "-1", "k", "-j", "-i", "1", "-k", "j"},
                           {"j", "-k", "-1", "i", "-j", "k", "1", "-i"},
                           {"k", "j", "-i", "-1", "-k", "-j", "i", "1"},
                           {"-1", "-i", "-j", "-k", "1", "i", "j", "k"},
                           {"-i", "1", "-k", "j", "i", "-1", "k", "-j"},
                           {"-j", "k", "1", "-i", "j", "-k", "-1", "i"},
                           {"-k", "-j", "i", "1", "k", "j", "-i", "-1"}};

        return mult[ia][ib];
    }
}