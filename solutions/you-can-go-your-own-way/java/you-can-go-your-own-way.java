import java.util.Scanner;

class Solution {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();

        for (int i=1; i<=t; i++) {
            int n = input.nextInt();
            String s = input.next();
            resolve(i, n, s);
        }

        input.close();
    }

    public static void resolve(int t, int n, String s) {
        StringBuffer newStr = new StringBuffer(s);

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'S' || s.charAt(i) == 's'){
                newStr.setCharAt(i, 'E');
            } else if(s.charAt(i) == 'E' || s.charAt(i) == 'e'){
                newStr.setCharAt(i, 'S');
            }
        }


        System.out.println("Case #" + t + ": " + newStr);
    }
}
