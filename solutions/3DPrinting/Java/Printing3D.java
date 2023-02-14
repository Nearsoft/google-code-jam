import java.util.Arrays;
import java.util.Scanner;

public class Printing3D {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();

        for (int i = 1; i <= t; i++) {
            int[][] a = new int[3][4];
            for (int j = 0; j < 3; j++) {
                for(int k = 0; k < 4; k++) {
                    a[j][k] = input.nextInt();
                }
            }

            printingAlgorithm(i, a);
        }
        input.close();
    }

    public static void printingAlgorithm(int t, int[][] array) {
        int[] minInkAmounts = new int[4];

        for(int i = 0; i < 4; i++) {
                minInkAmounts[i] = Math.min(Math.min(array[0][i], array[1][i]), array[2][i]);
        }

        int target = 1000000;
        int total = Arrays.stream(minInkAmounts).sum();

        if (total < target) {
            System.out.println("Case #"+ t + ": IMPOSSIBLE");
        } else {
            for(int i = 0; i < 4; i++) {
                int aux;
                if(total > target) {
                    aux = Math.min(total - target, minInkAmounts[i]);
                    minInkAmounts[i] -= aux;
                    total -= aux;
                }
            }
            System.out.println("Case #"+ t + ": " + minInkAmounts[0] + " " + minInkAmounts[1] + " "+ minInkAmounts[2] + " "+ minInkAmounts[3]);
        }
    }
}
