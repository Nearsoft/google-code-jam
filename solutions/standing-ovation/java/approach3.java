package com.company;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class approach3 {
    public static void main(String[] args) {
        StandingOvation standingOvation = new StandingOvation();
        String input = standingOvation.readFile("A-large-practice.in");
        String output = standingOvation.getResults(input);
        System.out.print(output);
    }
}


public class StandingOvation {

    public String readFile(String filename) {
        String output = "";
        URL url = getClass().getResource(filename);
        try {
            File file = new File(url.getPath());
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                output += input.nextLine() + "\n";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return output;
    }

    public String getResults(String input) {
        String[] lines = input.split("\n");
        ArrayList<Integer> results = new ArrayList<>();

        if(!isTestCasesQuantityValid(lines)) return "Error";

        for(int i = 1; i < lines.length; i++) {
            String testCase = lines[i];
            if (!isTestCaseValid(testCase)) return "Error";
            int testCaseResult = getTestCaseResult(testCase);
            results.add(testCaseResult);
        }

        return printResults(results);
    }

    private int getTestCaseResult(String testCase) {
        int maxShyness = Integer.parseInt(testCase.split(" ")[0]);
        String[] audience = testCase.split(" ")[1].split("");

        int standingPeopleCount = 0;
        int friendsCount = 0;

        for (int shynessLevel = 0; shynessLevel <= maxShyness; shynessLevel++) {
            int peopleCount = Integer.parseInt(audience[shynessLevel]);
            if (shynessLevel > standingPeopleCount) {
                int friendsNeeded = shynessLevel - standingPeopleCount;
                friendsCount += friendsNeeded;
                standingPeopleCount += friendsNeeded + peopleCount;
            } else {
                standingPeopleCount += peopleCount;
            }
        }

        return friendsCount;
    }

    private boolean isTestCaseValid(String testCase) {
        String[] testCaseLine = testCase.split(" ");
        int maxShyness = Integer.parseInt(testCaseLine[0]);
        String audienceString = testCaseLine[1];

        return maxShyness == audienceString.length() - 1 && maxShyness >= 0 && maxShyness <= 1000;
    }

    private boolean isTestCasesQuantityValid(String[] lines) {
        int testCasesQuantity = Integer.parseInt(lines[0]);
        return testCasesQuantity >= 1 && testCasesQuantity <= 100 && lines.length - 1 == testCasesQuantity;
    }

    private String printResults(ArrayList<Integer> results) {
        String output = "";

        for (int i = 0; i < results.size(); i++) {
            int result = results.get(i);
            output += "Case #" + (i+1) + ": " + result + "\n";
        }

        return output;
    }
}