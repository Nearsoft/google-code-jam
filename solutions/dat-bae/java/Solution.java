import java.util.*;
import java.io.*;
import java.lang.Math;

class Solution {
    //que pedo con que les hecho la mano
  // quien es xd xd ??
    private static String getPC (String[] arreglo, int n) {
        String id = "";
        for (int i = arreglo.length-1; i >= 0; i--) {
          id += arreglo[i].charAt(n);
        }
        return id;
    }
    
    public static void main(String[] args) {
        int N; // worker computers
        int B; // workers broken
        int F; // calls to TEST_STORE
        int T; // test cases
        String[] IDs, Responses;
        Boolean finish = false;
        
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        T = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < T && !finish; i++) {
            String readLine = sc.nextLine();
            N = Integer.parseInt(readLine.split(" ")[0]);
            B = Integer.parseInt(readLine.split(" ")[1]);
            F = Integer.parseInt(readLine.split(" ")[2]);
          //   5 2 5
            IDs = new String[F];
            Responses = new String[F];
            for (int f = 0; f < F; f++) {
                IDs[f] = "";
                Responses[f] = "";
            }
            
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
                    IDs[j] += ""+d;
                }
                System.out.println(guess);
                response = sc.nextLine(); // Response of the judge, ex. 1101
                if (response.equals("-1")){
                    finish = true;
                    break;
                }
                Responses[j] = response;
            }
            if (finish) break;
            // Vamos a comparar
            int O = 0;
            int r = 0;
            for (; r < N-B; r++) {
              if (!getPC(IDs, r+O).equals(getPC(Responses, r))) {
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
