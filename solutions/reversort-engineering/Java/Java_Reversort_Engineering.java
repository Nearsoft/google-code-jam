import java.util.*;
 
class Solution {
   static void reverse(int[] arr, int i, int j) {
     while (i < j) {
       int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
      i += 1;
      j -= 1;
     }
   }
 
   static String reversortEngineering(int N, int C) {
 
     if (N - 1 > C || C > ((N * (N + 1)) / 2) - 1) {
       return "IMPOSSIBLE";
     }
 
     int[] arrOfRange = new int[N];
     for (int i = 0; i < N; i++) {
      arrOfRange[i] = i + 1;
     }
     for (int i = N - 2; i >= 0; i--) {
       int m = Math.min(C - i, N - i);
      C -= m;
      reverse(arrOfRange, i, i + m - 1);
     }
 
     String response = "";
     for (int i = 0; i < N; i++) {
       String temp = Integer.toString(arrOfRange[i]);
      response = response + temp + " ";
     }
     return response;
   }
 
   public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
     int T = input.nextInt();
    input.nextLine();
 
     for (int i = 1; i < T + 1; i++) {
       String[] userInput = input.nextLine().split(" ");
       int N = Integer.parseInt(userInput[0]);
       int C = Integer.parseInt(userInput[1]);
       System.out.println("case #" + i + ": " + reversortEngineering(N, C));
     }
    input.close();
   }
}