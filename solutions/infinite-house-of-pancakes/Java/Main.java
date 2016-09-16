package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();

        for (int i = 1; i <= cases; i++) {
            int numPlates = input.nextInt();
            int[] pancakes = new int[numPlates];
            for (int j = 0; j < numPlates; j++) {
                pancakes[j] = input.nextInt();
            }
            int max=0;
            for (int a = 0; a < pancakes.length ; a++) {
                if(pancakes[a]>max){
                    max=pancakes[a];
                }
            }

            int[] counter = new int[max+1];

            for (int j = 0; j <pancakes.length ; j++) {
                counter[pancakes[j]]++;
            }
            int moves = max;

            for (int k = 1; k <= counter.length; k++) { // num pancakes destinados para comer
                int splits = 0;
                for (int l = 1; l < counter.length; l++) { // pancakes a separar
                    splits += ((l - 1) / k) * counter[l];
                }
                if (splits + k <= moves) {
                    moves = splits + k; //splits + minutes to eat
                }
            }
            System.out.println("Case #" + i + ": " + moves);
        }
    }
}
