package cryptopangrams;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author rafa_
 */
class Solution {

    /**
     * @param args the command line arguments
     */
    private static String crypto(BigInteger N, int L, BigInteger[] cipherText) {
        /*
        N is the upper limit for the primes used
        L is the length of the list of values
        cipherText is the list of ciphered integers
         */

        //Define alphabet
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        //Initialize an array
        BigInteger[] primes = new BigInteger[L + 1];
        Arrays.fill(primes, BigInteger.valueOf(-1));

        //Initialize string "message" as empty
        String message = "";

        //Calculate the gcd of the two first non equal ciphered primes
        int index = -1;
        for (int i = 0; i < L - 1; i++) {
            if (cipherText[i] != cipherText[i + 1]) {
                BigInteger factor = cipherText[i].gcd(cipherText[i + 1]);
                index = i + 1;
                primes[index] = factor;
            }
        }

        //Calculates back-propagation
        for (int i = index; i > 0; i--) {
            BigInteger prev_factor = cipherText[i - 1].divide(primes[i]);
            primes[i - 1] = prev_factor;
        }

        //Calculates front-propagation
        for (int i = index; i < cipherText.length; i++) {
            BigInteger next_factor = cipherText[i].divide(primes[i]);
            primes[i + 1] = next_factor;
        }

        //Sort "primes"
        SortedSet<BigInteger> sorted = new TreeSet<>(Arrays.asList(primes));

        //Map the sorted list to each letter in a dictionary
        Map<BigInteger, String> prime_map = new HashMap<>();

        //Assign each prime to a letter of the alphabet
        int i = 0;
        for (BigInteger prime : sorted) {
            prime_map.put(prime, alphabet[i]);
            i++;
        }

        //Make the plaintext message
        for (BigInteger prime : primes) {
            message += prime_map.get(prime);
        }

        //Return plaintext message to main
        return message;
    }

    public static void main(String[] args) throws IOException {
        //Initialize Scanner()
        Scanner sc = new Scanner(System.in);
        //Input number of cases
        int n_cases = sc.nextInt();
        for (int i = 0; i < n_cases; i++) {
            /*
            DIFFERENCE BETWEEN common Scanner.next methods
            next() returns what comes before a delimiter (by default it is whitespace)
            nextLine() reads the remainder of the current line even if it is empty
            nextInt() reads an integer but does not read the escape sequence "\n"
            */
            
            //Input N and L (on the same line)
            BigInteger N = new BigInteger(sc.next());
            int L = sc.nextInt();

            //Input the CipherText
            BigInteger[] cipherText = new BigInteger[L];
            for (int j = 0; j < L; j++) {
                cipherText[j] = new BigInteger(sc.next());
            }
            //Start method "crypto" with parameters
            String message = crypto(N, L, cipherText);
            //Print deciphered message
            System.out.println("Case #" + (i + 1) + ": " + message);
        }
    }
}

/*
Test the program with the following input:

3
103 31
217 1891 4819 2291 2987 3811 1739 2491 4717 445 65 1079 8383 5353 901 187 649 1003 697 3239 7663 291 123 779 1007 3551 1943 2117 1679 989 3053
10000 25
3292937 175597 18779 50429 375469 1651121 2102 3722 2376497 611683 489059 2328901 3150061 829981 421301 76409 38477 291931 730241 959821 1664197 3057407 4267589 4729181 5335543
197 29
15 15 15 15 21 49 77 143 221 323 437 667 899 1147 1517 1763 2021 2491 3127 3599 4087 4757 5183 5767 6557 7387 8633 9797 10403

*/
