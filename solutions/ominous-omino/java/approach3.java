import java.util.*;
import java.io.*;

public class Omino{

    public String getWinner(int x, int r, int c){

        int s = 0;
        int l = 0;

        if(r <= c){
            s = r;
            l = c;
        }
        else if(r > c){
            s = c;
            l = r;
        }

        // Cond 1
        if( !this.divide((float)x, (float)(r * c)) ){
            return "RICHARD";
        }

        // Cond 2
        if(x == 3 && s == 1){
            return "RICHARD";
        }

        // Cond 3
        if(x == 4 && s <= 2){
            return "RICHARD";
        }

        // Cond 4
        if(this.fourthCondition(x, s, l)){
            return "RICHARD";
        }

        // Cond 5
        if(x == 6 && s <= 3){
            return "RICHARD";
        }

        // Cond 6
        if(x >= 7){
            return "RICHARD";

        }
        
        return "GABRIEL";

    }

    public boolean fourthCondition(int x, int s, int l){

        if(x == 5){

            if(s <= 2 || (s == 3 && l == 5)){

                return true;

            }

        }

        return false;

    }

    public boolean divide(float x, float grid){

        boolean res = (grid % x) == 0;

        return res;

    }

    public static void main(String[] args){

        Omino o = new Omino();
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int testCases = in.nextInt();

        for (int i = 1; i <= testCases; i++){

          int xOmino = in.nextInt();
          int rGrid = in.nextInt();
          int cGrid = in.nextInt();
          System.out.println("Case #" + i + ": " + o.getWinner(xOmino, rGrid, cGrid));

        }


    }

}
