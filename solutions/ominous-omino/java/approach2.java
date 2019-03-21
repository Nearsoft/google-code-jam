import java.util.Scanner;


public class approach2
{
    static int N, R, C;

    public static void main(String ... orange) throws Exception
    {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        for (int n = 0; n < t; n++)
        {
            N = input.nextInt();
            R = input.nextInt();
            C = input.nextInt();
            boolean win=winner();
            if(win)
                System.out.printf("Case #%d: %s\n", n + 1,"GABRIEL");
            else
                System.out.printf("Case #%d: %s\n", n + 1,"RICHARD");
        }
    }

    static boolean winner()
    {
        if (R * C % N != 0)
            return false;

        if (N >= 7)
            return false;
        else if (N == 6)
        {
            if (Math.min(R, C) <= 3)
                return false;
        }
        else if (N == 5)
        {
            if (Math.min(R, C) <= 2 ||
                    Math.min(R, C) == 3 && Math.max(R, C) == 5)
                return false;
        }
        else if (N == 4)
        {
            if (Math.min(R, C) <= 2)
                return false;
        }
        else if (N == 3)
        {
            if (Math.min(R, C) <= 1)
                return false;
        }
        return true;
    }
}