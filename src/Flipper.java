import java.io.*;
import java.util.List;
import java.util.Objects;

public class Flipper {
    public static void main(String[] args) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("output.txt", "UTF-8");
            char[][] casos = getCases();

            for(int i = 1; i <= 100; i++){
                String result = solver(casos[i]);

                writer.println("Case #" + i + ": " + result);
                System.out.println("Case #" + i + ": " + result);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




    }

    static int getK(char[] caso){

        String k = "";

        for(int i =0 ; i < caso.length ; i++){
            if(Objects.equals(Character.toString(caso[i])," ")){
                i++;
                while (i < caso.length){
                    k += String.valueOf(caso[i]);
                    i++;
                }
            }
        }

        return Integer.valueOf(k);
    }

    static String solver(char[] caso){

        final int k = getK(caso);

        final int s = caso.length - (String.valueOf(k).length()+1);

        int posicion = 0;

        int steps = 0;

        while (k + posicion <= s){

            if(Objects.equals(Character.toString(caso[posicion]),"+")){
                posicion ++;
                continue;
            }

            if(Objects.equals(Character.toString(caso[posicion]),"-")){

                for(int i=0; i < k; i++){
                    if(Objects.equals(Character.toString(caso[posicion + i]),"-")){
                        caso[posicion + i] = '+';
                    }else if(Objects.equals(Character.toString(caso[posicion + i]),"+")){
                        caso[posicion + i] = '-';
                    }
                }
                posicion++;
                steps++;
            }
        }

        for(int i =0; i < s; i++){
            if(Objects.equals(Character.toString(caso[i]),"-"))return "IMPOSSIBLE";
        }

        return String.valueOf(steps);
    }

    static char[][] getCases(){
        String fileName= "largeInput.txt";

        String  line = null;
        char[][] cases = new char[101][];


        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            for(int j = 0; j < 101; j++) {
                line = bufferedReader.readLine();

                int arrayLength = line.length();

                cases[j] = new char[arrayLength];

                for(int i=0; i < line.length(); i++) {
                    cases[j][i] = line.charAt(i);
                }

            }

            // Always close files.
            bufferedReader.close();

        }
        catch(FileNotFoundException ex) {
            System.out.println(ex);
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }

        return cases;
    }

}
