import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int t = 1; t <= T; t++) {
            String input = scanner.nextLine();
            String output = getSmallestString(input);
            System.out.println("Case #" + t + ": " + output);
        }
    }

    private static String getSmallestString(String input) {
        List<Pair<Character, Integer>> groupedInput = groupCharacters(input);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < groupedInput.size(); i++) {
            Pair<Character, Integer> pair = groupedInput.get(i);
            char current = pair.key;
            int count = pair.value;

            if (i < groupedInput.size() - 1 && current < groupedInput.get(i + 1).key) {
                for (int j = 0; j < 2 * count; j++) {
                    result.append(current);
                }
            } else {
                for (int j = 0; j < count; j++) {
                    result.append(current);
                }
            }
        }

        return result.toString();
    }

    private static List<Pair<Character, Integer>> groupCharacters(String input) {
        List<Pair<Character, Integer>> groups = new ArrayList<>();
        char currentChar = input.charAt(0);
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == currentChar) {
                count++;
            } else {
                groups.add(new Pair<>(currentChar, count));
                currentChar = c;
                count = 1;
            }
        }
        groups.add(new Pair<>(currentChar, count)); // Add the last group
        return groups;
    }

    private static class Pair<K, V> {
        K key;
        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}