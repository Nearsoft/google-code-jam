package com.carlosm.mediansort;
import java.util.*;

//Remember change MedianSort
public class MedianSort {
    public static void main(String[] args) {
        Scanner fs = new Scanner(System.in);
        int T = fs.nextInt();
        int N = fs.nextInt();
        int Q = fs.nextInt();
        //Loop according to number of test cases
        for (int i=1; i<=T; i++){
            //Creating list of number
            int [] list = {1, 2};
            //Adding numbers depending on int N
            for(int ii=2; ii<N; ii++){
                //System.out.println("Iteracion: " + ii);
                list=add(list, ii+1, fs);
            }
            if(!printAnswers(list, fs)){
                return;
            }
        }
    }
    //This function add the rest of number
    static int[] add(int[] list, int toAdd, Scanner fs){
//        System.out.println("Tamaño de lista: " + list.length);
//        System.out.println("Añadido: "+ toAdd);
        int targetIndex=getTargetIndex(list, toAdd, 0, list.length, fs);
//        System.out.println("Target: " + targetIndex);
        int[] response = new int[list.length + 1];
        for(int i=0; i<targetIndex; i++){
            response[i]=list[i];
        }
        response[targetIndex]=toAdd;
        for (int i=targetIndex+1; i<response.length; i++) {
            response[i]=list[i-1];
            //System.out.println("i de add: "+ i);
        }
//        for (int element: response){
//            System.out.println("Elemento de response: " + element);
//        }
        return response;
    }
    //Obtaining the index of the answer of google
    static int getTargetIndex(int[] list, int toAdd, int l, int r, Scanner fs){
        if (l==r) return l;

        if (l+1==r){
            if (r!=list.length) r++;
            else l--;
        }
//        System.out.println("l: " + l);
//        System.out.println("r: " + r);
        int leftmostpivot=l, rightmostpivot=r-1;
        //System.out.println("pivote izq: " + leftmostpivot);
//        System.out.println("pivote der: " + rightmostpivot);
        int nSplits = rightmostpivot-leftmostpivot+1;
        //System.out.println("numero de divisiones: " + nSplits);
        int n1= leftmostpivot+(nSplits-1)/3;
        //System.out.println(n1);
        int n2= rightmostpivot-(nSplits-1)/3;
        //System.out.println(n2);
        //Asking user(google)
        int med = ask(toAdd,list[n1], list[n2], fs);
        if(med==list[n1]){
//            System.out.println("Opcion 1");
            return getTargetIndex(list, toAdd,l,n1,fs);
        }
        else if(med==list[n2]){
//            System.out.println("Opcion 2");
            return getTargetIndex(list, toAdd, n2+1, r, fs);
        }
        else {
//            System.out.println("Opcion 3");
            return getTargetIndex(list, toAdd, n1+1,n2,fs);
        }
    }

    //Asking to user (Google) to choose the median
    static int ask(int a, int b, int c, Scanner fs){
        System.out.println(a+" "+b+" "+c);
        return fs.nextInt();
    }

    //Checking if sorted numbers are correct
    static boolean printAnswers(int[] an, Scanner fs){
        for (int i=0; i<an.length; i++){
            if (i!=0) System.out.print(" ");
            System.out.print(an[i]);
        }
        System.out.println();
        int response = fs.nextInt();
        return response==1;
    }
}
