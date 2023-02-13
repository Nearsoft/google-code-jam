import java.util.Scanner;

public class NestingDepth {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();

        for (int i = 1; i <= t; i++ ) {
            String n = input.next();
            nestingAlgorithm(i, n);
        }
        input.close();
    }

    public static void nestingAlgorithm(int t, String s) {
        s = "0" + s + "0";
        String[] substrings = s.split("");

        String[] newArray = substrings;

        int count = 0;

        for(int i = 1; i < substrings.length; i++) {
            int dif = substrings[i].compareTo(substrings[i-1]);
            int abs = Math.abs(dif);
            int index = i + count;

            if (dif > 0) {
                newArray = addOpeningParenthesis(newArray, index, abs);
                count++;
            } else if (dif < 0){
                newArray = addClosingParenthesis(newArray, index, abs);
                count++;
            }

        }

        String result = String.join("", newArray);
        result = result.substring(1, result.length()-1);

        System.out.println("Case #"+ t + ": " + result);
    }

    public static String[] addClosingParenthesis(String[] array, int index, int times) {
        String parenthesis = ")".repeat(times);
        String[] newArray = new String[array.length + 1];

        int j = 0;

        for(int i = 0; i < newArray.length; i++) {
            if(i == index) {
                newArray[i] = parenthesis;
            }else {
                newArray[i] = array[j];
                j++;
            }
        }

        return newArray;
    }

    public static String[] addOpeningParenthesis(String[] array, int index, int times) {
        String parenthesis = "(".repeat(times);
        String[] newArray = new String[array.length + 1];

        int j = 0;

        for(int i = 0; i < newArray.length; i++) {
            if(i == index) {
                newArray[i] = parenthesis;
            }else {
                newArray[i] = array[j];
                j++;
            }
        }

        return newArray;
    }
}
