import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            int testCases = in.nextInt(), numberOfElements = in.nextInt(), numberOfQuestions = in.nextInt();
            while (testCases > 0) {
                if (!medianSort(numberOfElements, numberOfQuestions, in)) {
                    return;
                }
                testCases--;
            }
        } catch (InputMismatchException ex) {
            System.out.println("Incorrect input: an integer is required");
            in.nextLine();
        }
    }

    public static boolean medianSort(int numberOfElements, int numberOfQuestions, Scanner in) {
        int[] numbers = {1, 2};
        for (int i = 3; i < numberOfElements+1; i++) {
            // Ternary search
            int left = 0, right = numbers.length - 1;
            while (right - left >= 1 && numberOfQuestions > 0) {
                int leftPivot = left + ((right-left) / 3);
                int rightPivot = right - ((right-left) / 3);
                int median = askGoogle(numbers[leftPivot], numbers[rightPivot], i, in);
                numberOfQuestions--;
                if (median == numbers[leftPivot]) {
                    right = leftPivot - 1;
                    if (left == right) {
                        right += 1;
                    }
                } else if (median == numbers[rightPivot]) {
                    left = rightPivot + 1;
                    if (left == right) {
                        left -= 1;
                    }
                } else {
                    left = leftPivot + 1;
                    right = rightPivot - 1;
                    if (left == right) {
                        left -= 1;
                    }
                }
            }
            numbers = insert(numbers, i, left);
        }
        return checkResult(numbers, in);
    }

    static boolean checkResult(int[] numbers, Scanner in) {
        StringBuilder str = new StringBuilder();
        for (int number : numbers) {
            str.append(String.format("%d ", number));
        }
        System.out.println(str.toString().trim());
        System.out.flush();
        return in.nextInt() == 1;
    }

    static int askGoogle(int left, int right, int newNumber, Scanner in) {
        System.out.printf("%d %d %d%n", left, right, newNumber);
        System.out.flush();
        return in.nextInt();
    }

    private static int[] insert(int[] array, int key, int index)
    {
        int[] result = new int[array.length + 1];
        if (index >= 0) System.arraycopy(array, 0, result, 0, index);
        result[index] = key;
        if (array.length + 1 - (index + 1) >= 0)
            System.arraycopy(array, index + 1 - 1, result, index + 1, array.length + 1 - (index + 1));
        return result;
    }
}

// T is the number of test cases.
// N is the number of elements to sort within each test case.
// Q is the total number of questions you are allowed across all test cases, respectively.