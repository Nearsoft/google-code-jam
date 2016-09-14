package com.company;

public class Main {
    public static void main(String[] args) {
        StandingOvation standingOvation = new StandingOvation();
        String input = standingOvation.readFile("A-large-practice.in");
        String output = standingOvation.getResults(input);
        System.out.print(output);
    }
}
