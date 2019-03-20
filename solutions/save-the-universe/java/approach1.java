import java.util.Scanner;

public class approach1 {

    public static void main(String[] args) {
        int T, D;
        String P;
        char[] PC;

        Scanner sc = new Scanner(System.in);
        String TInp;
        do {
            System.out.println("How many tests?");
            TInp = sc.nextLine();
        }while(!TInp.matches("\\d+"));
        T = Integer.parseInt(TInp);
        String[] results = new String[T];
        int i = 0;
        while (i<T) {
            System.out.println("Case #" + (i+1));
            String[] splitted = inputVal(sc);
            D = Integer.parseInt(splitted[0]);
            P = splitted[1];
            System.out.println("Max Damage: " + D);
            System.out.println("Attack pattern: " + P);
            PC = P.toCharArray();
            int M = 0;
            int TA = countAttack(PC);

            boolean possible = true;
            while(D<TA){
                char temp1, temp2;
                int chSearch = 1;
                try {
                    do {
                        temp1 = PC[PC.length - chSearch];
                        temp2 = PC[PC.length - chSearch - 1];
                        chSearch++;
                    } while ((temp1 != 'S' || temp2 == 'S'));
                    chSearch--;
                    PC[PC.length - chSearch] = temp2;
                    PC[PC.length - chSearch - 1] = temp1;
                    M++;
                    TA = countAttack(PC);
                }catch (ArrayIndexOutOfBoundsException e){
                    possible = false;
                    break;
                }
            }
            if (possible) {
                results[i] = "Case #" + (i + 1) + ": " + M;
            }else{
                results[i] = "Case #" + (i + 1) + ": IMPOSSIBLE";
            }
            i++;
        }
        System.out.println("\nResults:");
        for (String r:results) {
            System.out.println(r);
        }
    }

    private static String[] inputVal(Scanner sc) {
        String inp;
        do{
            System.out.println("Input shield power and attack patern:");
            inp = sc.nextLine();
        }while (!(inp.matches("\\d+[ ][S|C]+")));
        return inp.split(" ");
    }

    private static int countAttack(char[] PC) {
        int CA = 1, TA = 0;
        for (char A : PC) {
            if (A == 'S'){
                TA += CA;
            }else{
                CA = CA*2;
            }
        }
        System.out.print("Array: ");
        for (char A: PC) {
            System.out.print(A);
        }
        System.out.println("\nDamage: " + TA);
        return TA;
    }
}