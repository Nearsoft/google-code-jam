package com.company;


import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws Exception{
        String inputFilePath = "input2.txt";
        String outputFilePath = "output2.txt";

        BufferedReader bufferedReader=new BufferedReader(new FileReader(inputFilePath));
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(outputFilePath));

        int numberOfCases = Integer.parseInt(bufferedReader.readLine().trim());

        //Splitting the string in the input file into 2 values
        StringTokenizer stringSplit;

        for(int caseNumber=1;caseNumber<=numberOfCases;caseNumber++){
            bufferedWriter.write("Case #"+caseNumber+": ");
            stringSplit=new StringTokenizer(bufferedReader.readLine().trim());
            long numberOfStalls=Long.parseLong(stringSplit.nextToken());
            long numberOfPeople=Long.parseLong(stringSplit.nextToken());
            long[] caseResult= solveBathroomStalls(numberOfStalls,numberOfPeople);
            bufferedWriter.write(caseResult[0]+" "+caseResult[1]+"\n");
        }
        bufferedReader.close();
        bufferedWriter.close();
        return;
    }

    public static long[] solveBathroomStalls(long numberofStalls, long numberOfPeople){
        if(numberOfPeople==1){
            long[] arr=new long[2];
            arr[0]=numberofStalls/2;
            arr[1]=(numberofStalls-1)/2;
            return arr;
        }
        if(numberOfPeople%2==0){
            return solveBathroomStalls(numberofStalls/2,numberOfPeople/2);
        }
        return solveBathroomStalls((numberofStalls-1)/2,numberOfPeople/2);

    }
}
