
public class Main {

    public static boolean isNegative = false;
    public static char[] theLetters = {'i','j','k'};
    public static String lettersToMult = "1ijk";
    public static int theLetterIndex = 0;
    public static char[][] multiplicationMatrix = { {'1','i','j','k'},
                                                    {'i','1','k','j'},
                                                    {'j','k','1','i'},
                                                    {'k','j','i','1'}
                                                                        };

    public static void main(String[] args) {
        String aver = "ijk";
        System.out.println(dijkstra(aver));
    }


    public static String dijkstra (String stringToChange){
        if (stringToChange.charAt(0) == theLetters[theLetterIndex] && !isNegative){
            theLetterIndex ++;
            if (theLetterIndex == 3 && stringToChange.length() <= 1){
                return "Yes";
            }
            else if (stringToChange.length() <= 1){
                return "No";
            }
            return dijkstra(stringToChange.substring(1, stringToChange.length()));
        }
        else if (stringToChange.length() <= 1){
            return "No";
        }

        String multiplicationResult = compare(stringToChange.charAt(0),stringToChange.charAt(1));
        String shortenedString = stringToChange.substring(2,stringToChange.length() );

        return dijkstra(multiplicationResult.concat(shortenedString));
    }

    public static String compare(char x, char y){
        negatives(x, y);
        return "" + multiplicationMatrix[lettersToMult.indexOf(x)][lettersToMult.indexOf(y)];
    }

    public static void negatives(char x, char y){
        if (x != '1' && y != '1') {
            if (x == y || (x == 'k' && y == 'j') || (x == 'j' && y == 'i') || (x == 'i' && y == 'k')) {
                isNegative = !isNegative;
            }
        }
    }


}
