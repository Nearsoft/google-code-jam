
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


    public static String dijkstra (String stringToChange){ //Will find the answer recursively
        //Scape conditions
        if (stringToChange.charAt(0) == theLetters[theLetterIndex] && !isNegative){ //If the first letter of the string is i, j, k on its correct order
            theLetterIndex ++;
            if (theLetterIndex == 3 && stringToChange.length() <= 1){ //If you have found all 3 letters and the string is empty
                return "Yes";
            }
            else if (stringToChange.length() <= 1){ //if your array is no longer available for recursion
                return "No";
            }
            return dijkstra(stringToChange.substring(1, stringToChange.length())); //return the string except the first char
        }
        else if (stringToChange.length() <= 1){ //if your array is no longer available for recursion
            return "No";
        }

        String multiplicationResult = compare(stringToChange.charAt(0),stringToChange.charAt(1)); //multiply the first two chars
        String shortenedString = stringToChange.substring(2,stringToChange.length() ); // take out the first two chars from the string

        return dijkstra(multiplicationResult.concat(shortenedString)); //concat the result of multiplication and the substring created
    }

    public static String compare(char x, char y){ //search in the table
        negatives(x, y);
        return "" + multiplicationMatrix[lettersToMult.indexOf(x)][lettersToMult.indexOf(y)];
    }

    public static void negatives(char x, char y){ //see if the result is negative
        if (x != '1' && y != '1') {
            if (x == y || (x == 'k' && y == 'j') || (x == 'j' && y == 'i') || (x == 'i' && y == 'k')) {
                isNegative = !isNegative;
            }
        }
    }


}
