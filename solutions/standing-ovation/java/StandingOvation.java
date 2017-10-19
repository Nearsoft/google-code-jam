import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ramon Ernesto Jimenez Rodriguez
 * @version v1.0
 */
public class StandingOvation {

    /**
     * An empty constructor for this class
     */
    public StandingOvation(){
    }

    /**
     * Evaluate the input to know the minimum number of invited persons for avery case
     * @param input it's the input for our program
     *             the rules for the input are:
     *             1.- The first line of the input gives the number of test cases (T) with the limit 1 <= T <= 100.
     *             2.- T test cases follow.
     *             3.- Each consists of one line with Smax followed by a string of Smax + 1 single digits.
     *             4.- The kth digit of this string (counting starting from 0) represents how many people
     *                 in the audience have shyness level k.
     * @return the number of invited persons for every case
     */
    public String evaluateInput(List<String> input){
        String result = "";
        try {
            int t = Integer.parseInt(input.remove(0)); // t = total cases
            if(t>=1 && t<=100){ //verify that the input is on the limit of the program
                if(t == input.size()){ //verify that the number of test cases given is equal to the t
                    for(int i=0; i<t; i++){
                        String testCase[] = input.get(i).split(" ");
                        result+="Case #"+(i+1)+": "+evaluateCase(Integer.parseInt(testCase[0]),testCase[1])+"\n";
                    }
                }else{
                    System.out.println("Error: the number of test cases given doesn't match with the number received");
                }
            }else{ // if t is off the limits then we put an error
                System.out.println("Error: this input exceed the number of test cases accepted");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Converts the string to a integer array
     * @param string the string that contains the digits from our input
     * @return an integer array with the digits in the string
     */
    private int[] convertStringToDigits(String string){
        int digits[] = new int[string.length()];
        try{
            for(int i=0;i<string.length();i++){
                digits[i]=Integer.parseInt(""+string.charAt(i));
            }
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return digits;
    }

    /**
     * Evaluates a case to
     * @param Smax the maximum shyness level in this case
     * @param stringDigits a string that contains the number of persons with a kth shyness level in this case
     * @return the minimum number of persons who have to be invited in this case
     */
    private String evaluateCase(int Smax, String stringDigits){
        int invited=0,aplauding=0;
        int digits[] = convertStringToDigits(stringDigits);
        if(Smax+1 == digits.length){
            for(int i=0;i<digits.length;i++){
                if(digits[i] > 0){
                    if(aplauding < i){
                        invited += (i-aplauding);
                        aplauding += (i-aplauding);
                    }
                    aplauding += digits[i];
                }
            }
        }
        return ""+invited;
    }



    /**
     * The main function of this program
     * @param args
     */
    public static void main(String args[]){

        List<String> input = FileManagement.readFile("");
        StandingOvation ovation = new StandingOvation();
        String result = ovation.evaluateInput(input);
        System.out.println(result);
        FileManagement.saveFile(result,"src/output/result.txt");

    }
}
