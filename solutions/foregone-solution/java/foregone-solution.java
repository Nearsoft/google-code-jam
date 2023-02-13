import java.util.Scanner;
public class Foregone {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();

        for (int i = 1; i <= t; i++ ) {
            String n = input.next();
            foregoneAlgorithm(i, n);
        }
        input.close();
    }
    public static void foregoneAlgorithm(int t, String n) {
        String[] substrings = n.split("");

        String a = "";
        String b = "";

        for(String i : substrings) {
            if (i.equals("4")){
                a = a.concat("2");
                b = b.concat("2");
            } else {
                a = a.concat(i);
                b = b.concat("0");
            }
        }
        System.out.println("Case #"+ t + ": " + a + " " + b);
    }
}
