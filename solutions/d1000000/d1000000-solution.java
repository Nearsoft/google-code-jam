import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        int x = 1;
        while(T>0) {
            int N = Integer.parseInt(in.nextLine());
            String line = in.nextLine();
            int[] dices = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(dices);
            int length = 0;
            for (int dice:dices) {
                if (dice > length) {
                    length++;
                }
            }
            System.out.println("Case #" + x + ": " + length);
            x++;
            T--;
        }
        in.close();
    }
}
