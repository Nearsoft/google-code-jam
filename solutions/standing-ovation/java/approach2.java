import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FullOvation {
    private int shyMax; // Maximum number of shyness level.
    private ArrayList<Integer> numPersons; // Number of persons with their respective shy level.
    private int numPeopleNeeded;

    public FullOvation(int shyMax, ArrayList<Integer> numPersons) {
        this.shyMax = shyMax;
        this.numPersons = numPersons;
        this.numPeopleNeeded = 0;
    }

    public void setShyMax(int shyMax) {
        this.shyMax = shyMax;
        this.numPeopleNeeded = 0;
    }

    public void setNumPersons(ArrayList<Integer> numPersons) {
        this.numPersons = numPersons;
    }

    public int getNumPeopleNeeded() {
        return numPeopleNeeded;
    }

    public void calculateNumberOfFriends(){
        int length = numPersons.size();
        int sum = numPersons.get(0);
        for(int index = 1; index < length; index++){
            if((sum + numPeopleNeeded) < index){
                numPeopleNeeded = index - sum;
            }
            sum += numPersons.get(index);
        }
    }

    public static void main(String[] args){
        String filename = "A-large-practice.in";
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            String[] data;
            ArrayList<Integer> numPeople = new ArrayList<Integer>();
            FullOvation fullOvation = new FullOvation(0, new ArrayList<Integer>());
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.out"));

            int T = Integer.parseInt(br.readLine()); // Number of test cases.
            int caseNumber = 1;

            //while(T-- < 0) {
            while ((line = br.readLine()) != null) {
                data = line.split(" ");
                fullOvation.setShyMax(Integer.parseInt(data[0]));
                data = data[1].split("");
                for(String d : data)
                    numPeople.add(Integer.parseInt(d));

                fullOvation.setNumPersons(numPeople);

                fullOvation.calculateNumberOfFriends();
                bw.write("Case #" + caseNumber + ": " +fullOvation.getNumPeopleNeeded() + "\n");

                caseNumber++;
                numPeople.clear();
            }
            //}

            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
