import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = sc.nextInt();
        //cn = case number
        for (int cn = 1; cn <= T; cn++) {

            //Size of the array
            int Size = sc.nextInt();
            //Required cost
            int Cost = sc.nextInt();

            //This couple cases are impossible to do...
            if(Cost < Size-1){
                 System.out.println("Case #" + cn + ": IMPOSSIBLE");
                }
            else if(Cost >= (Size*(Size+1)/2)){
                System.out.println("Case #" + cn + ": IMPOSSIBLE");
            }
            //Possible case...
            else {
                int arr[] = new int[Size];
                boolean isReverse = true;
                int s = 1;
                Cost -= (Size-1);

                for(int i=1; i<=Size; i++) {
                    arr[i-1] = i;
                }

                revertedArr(0, Size-1, false, Cost, arr);

                System.out.print("Case #" + cn + ":");
                for(int i: arr){
                    System.out.print(" " + i);
                }
                System.out.println();
            }
        }
        sc.close();
    }

    public static void revertedArr(int s, int e, boolean isReverse, int c, int[] arr){
        if(c > (e-s)){
            c = c-(e-s);
            reverse(s,e,arr);
            if(!isReverse){
                revertedArr(s,e-1, true, c, arr);
            } else{
                revertedArr(s+1,e, false, c, arr);
            }
        }

        else{
            if(!isReverse){
                reverse(s, s+c, arr);
            } else{
                reverse(e-c, e, arr);
            }

        }
    }

    public static void reverse(int s, int e, int[] arr){
        for(int a=s; a<e; a++, e--){
            int temp = arr[a];
            arr[a] = arr[e];
            arr[e] = temp;
        }
    }
}