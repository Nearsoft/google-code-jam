/**
 * Created by enrique on 23/03/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Shyness
{
    //File Path
    private static final String pathFile = "C:/A-large-practice.in";

    public static void main(String args[])
    {
        //Array for the members of the audience
        String [] arrayAudience;
        
        /*Variables
        /* @int testCase - It holds the number of test cases
        /* @int maxShyness - It holds the max level of Shyness for each testcase
        /* @int numStanding - It holds the number of people already standing
        /* @int numFriends - It holds the total number of friends to invite
        /* @int lastShynessCovered - It holds the last j level of shyness covered with the people standing
        /* @int personNeeded - It holds the person needed on each Sj to cover the j shyness level
        */
        int testCase, maxShyness, numStanding, numFriends, lastShynessCovered, personNeeded;

        try(BufferedReader br = new BufferedReader(new FileReader(pathFile)))
        {
            //Read the first line and assign to current line
            String currentLine = br.readLine();

            //Get the test cases into testCase variable from the first line
            testCase = Integer.parseInt(currentLine);

            //For to loop into the testCase variable
            for( int i = 0; i <= (testCase-1); i++ )
            {
                //Read the next document's line
                currentLine = br.readLine();

                //Reset (or set) the control variables
                numStanding = 0;
                numFriends = 0;
                lastShynessCovered = 0;
                personNeeded = 0;

                //Get the maxShyness from the first index (0) result of split the current line with blank spaces delimiter
                maxShyness = Integer.parseInt(currentLine.split("\\s")[0]);
                //Get the array of audience members from the previos split, plus a split wothout delimiter
                arrayAudience = currentLine.split("\\s")[1].split("");

                //For to loop into the maxShyness level
                for(int j = 0; j <= maxShyness; j++)
                {
                    //If the standing people is greater or equal than the j shyness level
                    if(numStanding >= j)
                    {
                        //Mark the current j shyness level as covered
                        lastShynessCovered = j;
                        
                        //If the j shyness level has 0 people to stand up, just continue
                        if( Integer.parseInt(arrayAudience[j]) == 0 )
                            continue;
                    }
                    else //If the standing people is smaller than the j shyness level
                    {
                        //If the amount of people in the j shyness evel is 0, just continue
                        if( Integer.parseInt(arrayAudience[j]) == 0 )
                            continue;

                        //Obtain the person needed to cover the j shyness level
                        personNeeded = j - numStanding;
                        
                        //Sum the person needed to the audience in the last j shyness level covered
                        arrayAudience[lastShynessCovered] = "" + (Integer.parseInt(arrayAudience[lastShynessCovered]) + personNeeded);
                        
                        //Sum the person needed to the person already standing
                        numStanding += personNeeded;
                        
                        //Sum the person needed to the number of friends
                        numFriends += personNeeded;
                    }
                    
                    //Sum the amount of people standing in the j shyness level covered to the number of already standing people
                    numStanding += Integer.parseInt(arrayAudience[j]);
                }

                //Print the result
                System.out.println("Case #" + (i+1) + ": " + numFriends);
            }
        }
        catch(IOException e)
        {
            System.out.print(e);
        }
    }
}
