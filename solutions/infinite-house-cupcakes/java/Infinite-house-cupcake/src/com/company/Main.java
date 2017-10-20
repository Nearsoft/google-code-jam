package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here

        String result = "";
        try {

            BufferedReader reader = new BufferedReader(new FileReader("/home/ocrisostomo/Downloads/B-large-practice.in"));
            String line = reader.readLine();
            int t=0; // t = total cases
            String s=null,sMax=null,sa=null;
            if(line != null){
                t = Integer.parseInt(line.trim());
            }
            if(t>=1 && t<=100){ //verify that the input is on the limit of the program

                for(int i=0; i<t && line != null; i++){

                    line = reader.readLine();

                    String xcase = reader.readLine();
                    //String xcase[]= reader.readLine().split("");
                    s= xcase;
                    //sMax=xcase[1];
                    //String plates[]=s.split("");
                   // System.out.println(s);

                    //System.out.println(sMax);
                    result+="Case #"+(i+1)+": "+result(s)+"\n";
                }
            }else{ // if t is off the limits then we put an error
                System.out.println("Error: this input exceed the number of test cases accepted");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(result);









/*
        Scanner sc = new Scanner(System.in);
        Main obj = new Main();
        int t = 0; //number of cases
        t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.println("Case #"+(i+1)+": "+obj.result());
        }*/
    }

    public static int result(String plat) {
        int min = 0;
        int aux = 0, aux2 = 0,aux3 = 0;
        int minTot = 0;
       // Scanner sc = new Scanner(System.in);
        //int d = 0; //dinners
        List<Integer> p = new ArrayList<Integer>();// cupcakes per dinner
        List<Integer> minuts = new ArrayList<Integer>();
        List<Integer> maxs = new ArrayList<Integer>();
        String pi;// array with number of cupcake for each dinner
        //d = Integer.valueOf(sc.next());//input the dinners
        //pi = new Scanner(System.in).nextLine();
        //String plates[] = pi.split(" ");
        String plates[] = plat.split(" ");
        int max = 0;
        int si=0;



        for (int i = 0; i < plates.length; i++) {
            p.add(Integer.valueOf(plates[i]));
        }

        Collections.sort(p);
        si=p.size();
        max = p.get(p.size() - 1);
        maxs.add(max);
        minuts.add(min);

        for (int i = 0; i < p.size(); i++) {
            aux = 0;
            aux2 = 0;
            if (minTot <= max) {

                    for (int j = 0; j < p.size(); j++) {
                        if (p.get(j) != 1) {
                            if (p.get(j) == max) {
                                if (p.get(j) % 2 != 0) {
                                    aux = p.get(j) / 2;
                                    aux2 = (p.get(j) / 2) + 1;
                                    p.add(aux);
                                    p.add(aux2);
                                    p.remove(p.get(j));
                                    min++;
                                    minuts.add(min);
                                    Collections.sort(p);
                                } else {

                                    aux = p.get(j) / 2;
                                    aux2 = p.get(j) / 2;
                                    p.add(aux);
                                    p.add(aux2);
                                    p.remove(p.get(j));
                                    min++;
                                    minuts.add(min);
                                    Collections.sort(p);
                                }
                            }
                        }
                    }
            }

            Collections.sort(minuts);
            Collections.sort(p);
            maxs.add(max);
            max = p.get(p.size() - 1);


            minTot=min;

            }
        aux3=minuts.get(0)+maxs.get(0);
        for (int z=0; z<minuts.size();z++){


          if(aux3>(minuts.get(z)+maxs.get(z))){
            aux3=minuts.get(z)+maxs.get(z);
        }

        }
        return aux3;
        }
}


