package com.carlosm.mediansort;
import java.util.Scanner;

//Remember change MedianSort
public class MedianSort {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int tests = input.nextInt();
        int numbers = input.nextInt();
        int querys = input.nextInt();
        //Loop according to number of test cases
        for (int i=1; i<=tests; i++){
            //Creating list of number
            int [] arrayNumbers = {1, 2};
            //Adding numbers depending on int N
            for(int ii=2; ii<numbers; ii++){
                arrayNumbers=addElement(arrayNumbers, ii+1, input);
            }
            if(!printAnswers(arrayNumbers, input)){
                return;
            }
        }
    }

    //This function add the rest of number
    static int[] addElement(int[] list, int numbertoAdd, Scanner input){
        //Getting the index of the adding number
        int targetIndex=getTargetIndex(list, numbertoAdd, 0, list.length, input);

        //Creating new array, because of new element
        int[] response = new int[list.length + 1];

        //Adding the elements of the old array to the new one
        for(int i=0; i<targetIndex; i++){
            response[i]=list[i];
        }

        //Adding new element
        response[targetIndex]=numbertoAdd;

        //Sorting list when element is located in any of the first position of the list
        for (int i=targetIndex+1; i<response.length; i++) {
            response[i]=list[i-1];
        }
        return response;
    }
    //Obtaining the index of the answer of google
    static int getTargetIndex(int[] list, int toAdd, int l, int r, Scanner input){
        //This is a recursive function.
        // We have to know if the index gotten needs to be compare to other elements.
        // If l(left) and r(right) is equal, recursive functions breaks, because current list is sorted for the moment
        if (l==r){
            return l;
        }

        if (l+1==r){
            if (r!=list.length) r++;
            else l--;
        }

        int leftmostpivot = l;
        int rightmostpivot = r-1;

        //Selecting elements according to pivots
        int number1 = leftmostpivot+((rightmostpivot-leftmostpivot)/3);
        int number2 = rightmostpivot-((rightmostpivot-leftmostpivot)/3);

        //Asking to judge
        int med = ask(toAdd,list[number1], list[number2], input);

        if(med==list[number1]){
            return getTargetIndex(list, toAdd, l, number1, input);
        }
        else if(med==list[number2]){
            return getTargetIndex(list, toAdd, number2+1, r, input);
        }
        else {
            return getTargetIndex(list, toAdd, number1+1, number2, input);
        }
    }

    //Asking to user (Google) to choose the median
    static int ask(int a, int b, int c, Scanner fs){
        System.out.println(a+" "+b+" "+c);
        return fs.nextInt();
    }

    //Checking if sorted numbers are correct to continue
    static boolean printAnswers(int[] an, Scanner input){
        for (int i=0; i<an.length; i++){
            if (i!=0) System.out.print(" ");
            System.out.print(an[i]);
        }
        System.out.println();
        int response = input.nextInt();
        return response==1;
    }
}
