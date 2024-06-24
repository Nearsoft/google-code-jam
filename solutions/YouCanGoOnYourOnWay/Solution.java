import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCasesNumber, boardSizes;
        testCasesNumber= scan.nextInt();
        for (int i = 1; i <= testCasesNumber; i++) {
            boardSizes = scan.nextInt();
            String takenPaths = scan.next();
            takenPaths = takenPaths.replaceAll("S", "X").replaceAll("E", "S").replaceAll("X", "E");
            System.out.println("Case #" + i + ": " + takenPaths);
        }
    }
}
