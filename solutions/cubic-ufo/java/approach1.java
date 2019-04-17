/**
 * cubicUFO
 */
import java.util.Scanner;

 public class approach1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Number of test cases: ");
        int cases = input.nextInt();
        double area;

        for (int i=1; i <= cases; i++ ){
            System.out.print("Desiered area " + i + ": ");
            area = input.nextDouble();
            calculateShadow(cases, area);
        }
        input.close();
        
    }

    public static void calculateShadow(int cases, double area){
        double theta;
        if(area<= Math.sqrt(2)){
            theta = Math.asin(Math.pow(area, 2)-1)/2;
            System.out.println(0.5 + ", " + 0 + ", "  + 0);
            System.out.println(0 + ", " + 0.5*Math.cos(theta) + ", " + 0.5*Math.sin(theta));
            System.out.println(0 + ", " + -0.5*Math.sin(theta) + ", " + 0.5*Math.cos(theta));
        } else {
            theta = Math.asin(area/Math.sqrt(3)) - Math.asin(Math.sqrt(2)/Math.sqrt(3));
            System.out.println(Math.cos(theta)/2 + ", " + Math.sin(theta)/2 + ", "  + 0);
            System.out.println(-Math.sin(theta)/Math.sqrt(8) + ", " + Math.cos(theta)/Math.sqrt(8) + ", " + 1/Math.sqrt(8));
            System.out.println(Math.sin(theta)/Math.sqrt(8) + ", " + -Math.cos(theta)/Math.sqrt(8) + ", " + 1/Math.sqrt(8));
        }
    }
}