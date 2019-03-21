import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class approach2
{
    public static void main(String ... orange) throws Exception
    {
        Scanner input = new Scanner(System.in);
        String line;
        
       
        FileWriter fileWriter = new FileWriter("B-small-practice.out");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        FileReader fileReader = new FileReader("B-small-practice.in");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        line = bufferedReader.readLine();
        System.out.println(line);
        int numCases=Integer.parseInt(line);
        
        for (int n = 0; n < numCases; n++)
        {
            line = bufferedReader.readLine();
            int N=Integer.parseInt(line);
            System.out.println(line);
            int[] ps = new int[N];
            line = bufferedReader.readLine();
            System.out.println(line);
            String[] numbers=line.split(" ");
            for (int i = 0; i < N; i++){
                ps[i] =Integer.parseInt(numbers[i]); //input.nextInt();
            }

            int[] counts = new int[1005];
            for (int p : ps)
                counts[p]++;

            int min = 10000;
            for (int lim = 1; lim <= counts.length; lim++)
            {
                int moves = 0;
                for (int i = 0; i < counts.length; i++)
                    moves += ((i - 1) / lim) * counts[i];
                if (moves + lim < min)
                    min = moves + lim;
            }
            printWriter.println("Case #" + (n+1) + ": " + min);
        }
        input.close(); printWriter.close();
    }
}
