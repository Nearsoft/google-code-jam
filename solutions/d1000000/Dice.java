import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Dice{
    static Scanner inputObject = new Scanner(System.in); //Scaner for the input
    public static void main(String[] args) {
        int T = inputObject.nextInt(); //Read T = Number of cases
        for(int i = 1; i <= T; i++){//Iterate through all the cases
            int N = inputObject.nextInt();//Read N = Length of dice list
            List<Integer> S = new ArrayList<Integer>();
            for(int j = 0; j < N; j++){//Read the list of dice
                S.add(inputObject.nextInt());
            }
            Collections.sort(S); //Sort the list
            int length = 0;//Length of the straight
            for(int j =0; j<S.size();j++){
                if(S.get(j) > length) length++; //Add +1 to the length if the die size is bigger that the actual length
            }
            System.out.println("Case #" + i + ": " + length); //Print the length of the straight
        }
    }
}