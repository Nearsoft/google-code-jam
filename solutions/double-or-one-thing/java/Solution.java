
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        //input of number of cases
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        scanner.nextLine();
        String[] casesData = new String[numCases];
        for (int i = 0; i < numCases; i++){
            casesData[i] = scanner.nextLine();
        }
        Arrays.toString(casesData);
        //Printing the results
        for (int i = 0; i < numCases; i++){
            System.out.println("Case #" + (i+1) + ": " + firstAphabetical(casesData[i]));
        }
    }

    public static String firstAphabetical (String frase){
        String result = "";
        int repeated = 0;
        String dup = "";

        if (frase.length() <= 1){
            return frase;
        }

        for (int i = 0; i<frase.length()-1;i++){
            char a1 = frase.charAt(i);
            char b2 = frase.charAt(i+1);
            String a = ""+a1;
            String b = ""+b2;
            int compare = Character.compare(a1,b2);

            if(repeated == 0){
                dup = a;
            }else{
                dup = a.repeat(repeated+1);
            }

            if(compare == 0){
                // a = b
                repeated++;
                if(repeated == frase.length()-1){
                    return frase;
                }else if ( i == frase.length()-2){
                    dup = a.repeat(repeated);
                    result += dup;
                }

            } else if(compare < 0){
                // a < b
                //duplicate a
                result += dup.repeat(2);
                repeated = 0;
            } else {
                // a > b
                // No duplicates, just add the normal a
                result += dup;
                repeated = 0;
            }
        }
        //add the final character
        result +=frase.charAt(frase.length()-1);

        return result;
    }
}