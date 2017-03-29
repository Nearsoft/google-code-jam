package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
        for(int l = 0; l< k; l++) {
            int r = s.nextInt();
            ArrayList<Integer> lista = new ArrayList<>();
            for (int i = 0; i < r; i++)
                lista.add(s.nextInt());
            int max = Collections.max(lista);

            int min = max;

            for (int i = 1; i < max; i++) {
                int count = i;
                for (int j : lista) {
                    count += (j - 1) / i;
                }
                min = Math.min(min, count);
            }
            System.out.println("Case #"+(l+1)+": "+min);
        }
    }
}
