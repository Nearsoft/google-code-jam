import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testCases = s.nextInt();
        int currentCase = 1;
        StringBuilder result = new StringBuilder();
        while (currentCase <= testCases){
            int n = s.nextInt();
            int[][] a = new int[n][n];
            for (int i = 0; i <n ; i++) {
                for (int j = 0; j <n ; j++) {
                    a[i][j] = s.nextInt();
                }
            }
            //Do the things we have to do with the array
            int k = getk(a);
            int r = getR(a);
            int c = getC(a);
            //Append the current result
            result.append(getStringResult(currentCase, k, r, c));
            result.append("\n");
            //Increase the number of the current case
            currentCase++;
        }
        System.out.println(result);
    }

    private static String getStringResult(int caseNumber,int k, int r, int c){
        String result = "Case #"+ caseNumber + ": " +k+" "+r+" "+c;
        return result;
    }
    private static int getk(int array[][]){
        int sum = 0;
        for (int i = 0, j=0; i <array.length ; i++, j++) {
            sum += array[i][j];
        }
        return sum;
    }
    private static int getR(int array[][]){
        Set<Integer> hashSet = new HashSet<>();
        int length = array.length;
        int sum = 0;
        for (int i = 0; i <length ; i++) {
            hashSet.clear();
            for (int j = 0; j <length ; j++) {
                int current = array[i][j];
                if(hashSet.contains(current)) {
                    sum++;
                    break;
                }
                else hashSet.add(current);
            }
        }
        return sum;
    }
    private static int getC(int array[][]){
        Set<Integer> hashSet = new HashSet<>();
        int length = array.length;
        int sum = 0;
        for (int i = 0; i <length ; i++) {
            hashSet.clear();
            for (int j = 0; j <length ; j++) {
                int current = array[j][i];
                if(hashSet.contains(current)) {
                    sum++;
                    break;
                }
                else hashSet.add(current);
            }
        }
        return sum;
    }
}