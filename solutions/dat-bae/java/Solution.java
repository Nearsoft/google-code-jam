import java.util.*;
import java.io.*;
import java.lang.Math;

class Lista {
    public LinkedList<Stack<String>> IDs;
    public LinkedList<Stack<String>> Responses;
  
    public Lista (int N, int B) {
        IDs = new LinkedList<>();
        for (int i = 0; i<N; i++)
            IDs.add(new Stack<String>());
        Responses = new LinkedList<>();
        for (int i = 0; i<(N-B); i++)
            Responses.add(new Stack<String>());
    }
    
    public String getID(int n) {
        String id = "";
        for (int i = IDs.get(n).size()-1; i >= 0; i--) {
          id += IDs.get(n).get(i);
        }
        return id;
    }
  
    public String getResponse(int n) {
        String id = "";
        for (int i = Responses.get(n).size()-1; i >= 0; i--) {
          id += Responses.get(n).get(i);
        }
        return id;
    }
}

public class Solution {
    public static void main(String[] args) {
        int N; // worker computers
        int B; // workers broken
        int F; // calls to TEST_STORE
        int T; // test cases
        Boolean finish = false;
        Lista l;
        
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        T = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < T && !finish; i++) {
            String readLine = sc.nextLine();
            N = Integer.parseInt(readLine.split(", ")[0]);
            B = Integer.parseInt(readLine.split(", ")[1]);
            F = Integer.parseInt(readLine.split(", ")[2]);
          
            l = new Lista(N,B);
            
            String guess = ""; // This will send to guess, ex. 010101
            String answer = ""; // When we know the answer, ex. 0 2
            String response = ""; // The response of the judge, ex. 1101
            for (int j = 0; j < F && !finish; j++) {
                guess = "";
                int digit = 0;
                double limit = Math.pow(2,j);
                //System.out.println("Limite: "+limit);
                char d = '0';
                for (int k = 0; k < N; k++, digit++) {
                    if (digit >= limit) {
                        d = (d == '0')? '1':'0';
                        digit = 0;
                    }
                    guess += d; // 00110011
                    //System.out.println("Guess temporal: "+guess);
                    l.IDs.get(k).push(""+d);
                }
                System.out.println(guess);
                response = sc.nextLine(); // Response of the judge, ex. 1101
                if (response.equals("-1")){
                    finish = true;
                    break;
                }
                for (int Oof = 0; Oof < response.length(); Oof++) {
                  l.Responses.get(Oof).push(""+response.charAt(Oof));
                }
            }
            if (finish) break;
            // Vamos a comparar
            int O = 0;
            int r=0;
            for (; r < l.Responses.size(); r++) {
              if (!l.getID(r+O).equals(l.getResponse(r))) {
                O++;
                r--;
                answer += (r+O)+" ";
              }
            }
            while (O < B) {
              answer += (r+O)+" ";
              O++;
            }
            System.out.println(answer);
            String veredict = sc.nextLine();
            if (veredict.equals("-1")){
                finish = true;
                break;
            }
          
        }
        
    }
}
