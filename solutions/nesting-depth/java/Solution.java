import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int caseNumber = 0; caseNumber < t; caseNumber++) {
            System.out.print("Case #" + (caseNumber + 1) + ": ");
            String s = scanner.next();
            int parenthesisLeft = 0;
            int[] numbersNested = new int[s.length()];
            for (int j = 0; j < numbersNested.length; j++) {
                numbersNested[j] = Character.getNumericValue(s.charAt(j));
            }

            for (int i : numbersNested) {

                if (i > parenthesisLeft) {
                    System.out.print(new String(new char[i - parenthesisLeft]).replace("\0", "("));
                    parenthesisLeft += (i - parenthesisLeft);
                } else if (i < parenthesisLeft) {
                    System.out.print(new String(new char[parenthesisLeft - i]).replace("\0", ")"));
                    parenthesisLeft -= (parenthesisLeft - i);
                }

                System.out.print(i);
            }
            if (parenthesisLeft > 0) {
                System.out.print(new String(new char[parenthesisLeft]).replace("\0", ")"));
            }



            System.out.println();
        }
    }
}
