import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        int x = 1;
        int unidadesMaximas = 1000000;
        while (num>0) {
            ArrayList<Integer> cyan = new ArrayList<>();
            ArrayList<Integer> magenta = new ArrayList<>();
            ArrayList<Integer> yellow = new ArrayList<>();
            ArrayList<Integer> black = new ArrayList<>();
            for(int i=0;i<3;i++){
                String line = in.nextLine();
                int[] numbers = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                cyan.add(numbers[0]);
                magenta.add(numbers[1]);
                yellow.add(numbers[2]);
                black.add(numbers[3]);
            }
            int minCyan = Collections.min(cyan);
            int minMagenta = Collections.min(magenta);
            int minYellow = Collections.min(yellow);
            int minBlack = Collections.min(black);
            int sumMins = minCyan + minMagenta + minYellow + minBlack;
            if (sumMins == unidadesMaximas){
                System.out.println("Case #" + x + ": " + minCyan + " " +minMagenta + " " +minYellow + " " +minBlack);
            }
            else if (sumMins < unidadesMaximas){
                System.out.println("Case #" + x + ": IMPOSSIBLE");
            }
            else {
                int[] minimos = {minCyan,minMagenta,minYellow,minBlack};
                for(int i=0;i<4;i++){
                    sumMins = 0;
                    for(int j=i+1;j<4;j++) {
                        sumMins+=minimos[j];
                    }
                    if (sumMins >= unidadesMaximas) {
                        minimos[i] = 0;
                    }
                    else {
                        minimos[i]=unidadesMaximas-sumMins;
                        break;
                    }
                }
                System.out.println("Case #" + x + ": " + minimos[0] + " " +minimos[1] + " " +minimos[2] + " " +minimos[3]);
            }
            x++;
            num--;
        }
        in.close();
    }
}
