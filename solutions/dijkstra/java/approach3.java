import java.util.Scanner;

public class Dijkstra {

    private static int L;
    private static long X;
    private static String block;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        final int T = input.nextInt();

        for (int caseNum = 1; caseNum <= T; caseNum++) {

            L = input.nextInt();
            X = input.nextLong();
            block = input.next();

            Quaternion blockQuaternion = Quaternion.get('1');
            for (int i = 0; i < L; i++) {
                blockQuaternion = multiply(blockQuaternion, Quaternion.get(block.charAt(i)));
            }

            int xX = (int) (X % 4);
            boolean isPossible = false;

            Quaternion tmp = Quaternion.get('1');
            for (int i = 0; i < xX; i++) {
                tmp = multiply(tmp, blockQuaternion);
            }

            Quaternion negOne = new Quaternion(true, '1');
            if (tmp.equals(negOne)) {
                // Further check
                isPossible = isPossible();
            }

            String pos = (isPossible ? "YES" : "NO");

            String result = String.format("Case #%d: %s", caseNum, pos);
            System.out.println(result);
        }
    }

    public static Quaternion multiply(Quaternion i, Quaternion j) {
        boolean sign = i.sign ^ j.sign;
        char value = '1';

        switch (i.value) {
            case '1':
                value = j.value;
                break;
            case 'i':
                switch (j.value) {
                    case '1':
                        value = 'i';
                        break;
                    case 'i':
                        sign = !sign;
                        value = '1';
                        break;
                    case 'j':
                        value = 'k';
                        break;
                    case 'k':
                        sign = !sign;
                        value = 'j';
                        break;
                    default:
                        break;
                }
                break;
            case 'j':
                switch (j.value) {
                    case '1':
                        value = 'j';
                        break;
                    case 'i':
                        sign = !sign;
                        value = 'k';
                        break;
                    case 'j':
                        sign = !sign;
                        value = '1';
                        break;
                    case 'k':
                        value = 'i';
                        break;
                    default:
                        break;
                }
                break;
            case 'k':
                switch (j.value) {
                    case '1':
                        value = 'k';
                        break;
                    case 'i':
                        value = 'j';
                        break;
                    case 'j':
                        sign = !sign;
                        value = 'i';
                        break;
                    case 'k':
                        sign = !sign;
                        value = '1';
                        break;
                    default:
                        break;
                }
                break;
        }
        Quaternion q = new Quaternion(sign, value);
        return q;
    }

    private static boolean isPossible() {

        Quaternion left = Quaternion.get('1');
        Quaternion right = Quaternion.get('1');

        for (int i = 0; i < Math.min(4 * L, L * X); i++) {
            left = multiply(left, Quaternion.get((block.charAt(i % L))));
            if (isValidFirst(left)) {
                for (long j = L * X - 1; j >= Math.max(L * X - 4 * L, i); j--) {
                    right = multiply(Quaternion.get(block.charAt((int) (j % L))), right);
                    if (isValidLast(right)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private static boolean isValidFirst(Quaternion first) {
        return first.equals(Quaternion.get('i'));
    }

    private static boolean isValidLast(Quaternion last) {
        return last.equals(Quaternion.get('k'));
    }
}

public class Quaternion {
    public Quaternion(char value) {
       this.sign = false;
       this.value = value;
    }
    public Quaternion(boolean sign, char value) {
       this.sign = sign;
       this.value = value;
    }
    public boolean sign;
    public char value;

    private static final Quaternion instance1 = new Quaternion('1');
    private static final Quaternion instancei = new Quaternion('i');
    private static final Quaternion instancej = new Quaternion('j');
    private static final Quaternion instancek = new Quaternion('k');

    public static Quaternion get(char i) {
       switch (i) {
       case '1':
          return instance1;
       case 'i':
          return instancei;
       case 'j':
          return instancej;
       default:
          return instancek;
       }
    }

    @Override
    public String toString() {
       String result = (sign ? "-" : "");
        return result + value;
    }

    @Override
    public boolean equals(Object other) {
       if (other instanceof Quaternion) {
           Quaternion that = (Quaternion) other;
           return (this.sign == that.sign) && (this.value == that.value);
        } else {
           return false;
        }
    }

}