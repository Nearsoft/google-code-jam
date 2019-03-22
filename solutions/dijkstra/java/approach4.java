package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class test {

    static int [][]M =
    {
        {0, 0,  0,  0,  0},
        {0, 1,  2,  3,  4},
        {0, 2, -1,  4, -3},
        {0, 3, -4, -1,  2},
        {0, 4,  3, -2, -1}
    };
    
    public static int min(long a, long b)
    {
      return (int) (a < b ? a : b);
    }

    public static int mul(int a, long b)
    {
      int sign = a * b > 0 ? 1 : -1;
      return sign * M[(int)Math.abs(a)][(int) Math.abs(b)];
    }

    public static int power(long a, long b)
    {
      int value = 1;
      for (int i = 0; i < b % 4; i++)
      {
        value = mul(value, a);
      }
      return value;
    }

    public static boolean multiply_all(long[] S, long L, long X)
    {
      int value = 1;
      for (int i = 0; i < L; i++)
      {
        value = mul(value, S[i]);
      }
      return power(value, X) == -1 ? true : false;
    }

    public static boolean construct_first_two(long[] S, long L, long X)
    {
      int i_value = 1;
      int j_value = 1;
      for (int i = 0; i < X; i++)
      {
        for (int j = 0; j < L; j++)
        {
          if (i_value != 2)
          {
            i_value = mul(i_value, S[j]);
          }
          else if (j_value != 3)
          {
            j_value = mul(j_value, S[j]);
          }
        }
      }
      return i_value == 2 && j_value == 3 ? true : false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C-small-practice.in");
        //File file = new File("C-large-practice.in");
        Scanner filein = new Scanner(file);
        PrintWriter fileout = new PrintWriter("C-small-practice.out");
        //PrintWriter fileout = new PrintWriter("C-large-practice.out");
        long T;
        long L;
        long X;
        String sstring;
        T = filein.nextInt();
        for (int tc = 0; tc < T; tc++) {
            L = filein.nextLong();
            X = filein.nextLong();
            sstring = filein.next();
            long S[] = new long[(int) L];
            for (int i = 0; i < L; i++)
            {
              S[i] = sstring.charAt(i) - 'i' + 2;
            }
            boolean ok1 = multiply_all(S, L, X);
            boolean ok2 = construct_first_two(S, L, min(X, 8));
            fileout.print("Case #" + (tc + 1) + ": ");
            if (ok1 && ok2)
            {
              fileout.println("YES");
            }
            else
            {
              fileout.println("NO");
            }
        }
        fileout.flush();
        fileout.close();
        filein.close();
    }
}