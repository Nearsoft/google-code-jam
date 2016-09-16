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
