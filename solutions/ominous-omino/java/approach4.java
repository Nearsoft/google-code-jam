import java.util.Scanner;

/**
 * Created by alan_bm@hotmail.com on 26/03/2017.
 */
public class Ominos {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
            int X = in.nextInt();
            int R = in.nextInt();
            int C = in.nextInt();
            boolean rich_wins = false;

            int min = (C<R)? C : R;
            int max = (C>=R)? C : R;
            if ((R*C)%X!=0||X>=7) rich_wins = true;
            if (X==3 && min == 1) rich_wins = true;
            if (X==4 && min <=2) rich_wins = true;
            if (X==5 && (min <=2 || (min == 3 && max == 5))) rich_wins = true;
            if (X==6 && min <=3) rich_wins = true;

            System.out.println("Case #"+(i+1)+": "+((rich_wins)?"RICHARD":"GABRIEL"));
        }
    }
}
