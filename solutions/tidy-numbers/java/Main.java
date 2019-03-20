import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main {

    private static final String FILENAME = "C:\\B-small-practice.in";
    private static final String RESULTNAME = "C:\\Result\\ResultSmall.out";

    public static void main(String[] args) {

        tidyNumber();
    }

    public static void tidyNumber() {
        try {
            FileReader fr = new FileReader(FILENAME);
            BufferedReader br = new BufferedReader(fr);
            //Scanner sc = new Scanner(System.in);
//        Inputs
            int caseNum = Integer.parseInt(br.readLine());
            List<String> numbers = new ArrayList<>(caseNum);
            for (int i = 0; i < caseNum; i++) {
                numbers.add(br.readLine());
            }
            validateAndPrintNumber(caseNum, numbers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateAndPrintNumber(int caseNum, List<String> numbers) {
        char[] charArray;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(RESULTNAME);
            bw = new BufferedWriter(fw);
            for (int i = 0; i < caseNum; i++) {
                String number = numbers.get(i);
                charArray = number.toCharArray();

                for (int j = number.length() - 1; j > 0; j--) {
                    if (charArray[j - 1] > charArray[j]) {
                        charArray[j - 1]--;
                        for (int x = j; x < number.length(); x++) {
                            charArray[x] = '9';
                        }
                    }
                }

                int caseNumber = i + 1;
                bw.write(("Case #" + caseNumber + ": " + (new String(charArray).replace('0', ' ')).trim()));
                System.out.println(("Case #" + caseNumber + ": " + (new String(charArray).replace('0', ' ')).trim()));
                bw.newLine();

            }
            //print(charArray, i + 1);
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            if (bw != null) {

                bw.close();
            }
            if (fw != null) {

                fw.close();
            }
        } catch (IOException e) {

        }

    }


}
