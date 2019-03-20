import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class approach2{
    String[][] mult = {{"1", "i", "j", "k", "-1", "-i", "-j", "-k"},
            {"i", "-1", "k", "-j", "-i", "1", "-k", "j"},
            {"j", "-k", "-1", "i", "-j", "k", "1", "-i"},
            {"k", "j", "-i", "-1", "-k", "-j", "i", "1"},
            {"-1", "-i", "-j", "-k", "1", "i", "j", "k"},
            {"-i", "1", "-k", "j", "i", "-1", "k", "-j"},
            {"-j", "k", "1", "-i", "j", "-k", "-1", "i"},
            {"-k", "-j", "i", "1", "k", "j", "-i", "-1"}};

    private String data;
    private int L;
    private int X;
    private boolean isPossible;

    public Dijkstra(String data, int l, int x) {
        L = l;
        X = x;
        this.data = data;
        isPossible = false;
        for(int i = 1; i < X; i++) {
            this.data += data;
        }
    }

    public boolean seekij() {
        String aux1 = "1";
        String aux2 = "1";
        String[] splitted = data.split("");

        int length = data.length();
        for(int i = 0; i < length; i++){
            aux1 = quaternionMultiplication(aux1, splitted[i]);
            if (aux1.equals("i"))
                for(int j = i + 1; j < length; j++) {
                    aux2 = quaternionMultiplication(aux2, splitted[j]);
                    if (aux2.equals("j"))
                        return true;
                }
        }
        return false;
    }

    public void solve() {
        String[] splitWord = data.split("");
        String reduced = splitWord[0];

        for(int i = 1; i < data.length(); i++)
            reduced = quaternionMultiplication(reduced, splitWord[i]);

        if(reduced != "-1")
            isPossible = false;
        else if(seekij())
            isPossible = true;
        else
            isPossible = false;
    }

    public String quaternionMultiplication(String a, String b) {
        String[] indices = {"1", "i", "j", "k", "-1", "-i", "-j", "-k"};

        int ia = -1;
        int ib = -1;

        for (int i = 0; i < indices.length; i++) {
            if(a.equals(indices[i]))
                ia = i;
            if(b.equals(indices[i]))
                ib = i;
        }
        if(ia == -1 || ib == -1)
            return "";
        return mult[ia][ib];
    }


    public static void main(String args[]){
        String filename = "C-small-practice.in";
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            String[] data;
            Dijkstra dijkstra;
            int T = Integer.parseInt(br.readLine()); // Number of test cases.
            int caseNumber = 1;

            //while(T-- < 0) {
            while ((line = br.readLine()) != null) {
                data = line.split(" ");

                line = br.readLine();
                dijkstra = new Dijkstra(line, Integer.parseInt(data[0]), Integer.parseInt(data[1]));
                dijkstra.solve();

                if(dijkstra.isPossible)
                    System.out.println("Case #" + caseNumber + ": YES" + "\n");
                else
                    System.out.println("Case #" + caseNumber + ": NO" + "\n");

                caseNumber++;
            }
            //}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
