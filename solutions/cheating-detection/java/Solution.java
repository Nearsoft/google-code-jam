// import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Solution
{

    public static class Question implements Comparable<Question>
    {
        public int ID;
        public Integer count;
        public Question()
        {
            count = 0;
        }

        @Override
        public int compareTo(Solution.Question other) {
            return this.count.compareTo(other.count);
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T =  Integer.parseInt(sc.nextLine());
        sc.nextLine();
        int totalPlayers = 100;
        int totalQuestions = 10000;
        for (int i=0; i < T; i++)
        {
            int[][] results = new int[totalPlayers][totalQuestions];
            Solution.Question[] questions = new Question[totalQuestions];
            for (int playerIndex = 0; playerIndex < totalPlayers; playerIndex++)
            {
                    char[] answers = sc.nextLine().toCharArray();
                    for (int questionIndex = 0; questionIndex < totalQuestions; questionIndex++)
                    {
                        if (questions[questionIndex] == null)
                        {
                            questions[questionIndex] = new Question();
                            questions[questionIndex].ID = questionIndex;
                        }
                        if (answers[questionIndex] == '1')
                        {
                            questions[questionIndex].count += 1;
                        }
                        results[playerIndex][questionIndex] = Character.getNumericValue(answers[questionIndex]);
                    }
            }
            Arrays.sort(questions); 

            int[] ranks = new int[totalQuestions];
            for (int rank = 0; rank < totalQuestions; rank++)
            {
                Question question = questions[rank];
                ranks[question.ID] = rank;
            }
            double[] playersMeanRanks = new double[totalPlayers];

            for (int playerIndex = 0; playerIndex < totalPlayers; playerIndex++)
            {
                List values = new ArrayList<Integer>();
                for (int questionIndex = 0; questionIndex < totalQuestions; questionIndex++)
                {
                    if (results[playerIndex][questionIndex] == 1)
                    {
                        values.add(ranks[questionIndex]);
                    }
                }

                playersMeanRanks[playerIndex] = standardDeviation(values);
            }

            System.out.println("Case #"+(i + 1)+": " + (maxValueIndex(playersMeanRanks) + 1));
        }
    }

    static int maxValueIndex(double[] sequence) {
        int index = 0;
        for (int i = 0; i < sequence.length; i++)
        {
            if (sequence[index] < sequence[i])
            {
                index = i;
            }
        }
        return index;
    }

    static double standardDeviation(List<Integer> sequence)
    {
        double result = 0;
        double mean = average(sequence);
        for (int counter = 0; counter < sequence.size(); counter++)
        {
            result += (sequence.get(counter) - mean)*(sequence.get(counter) - mean);
        }
        result = result/(sequence.size()-1);
        result = Math.sqrt(result);
        return result;
    }

    static double average(List<Integer> sequence)
    {
        double result = 0;
        for(int i = 0; i < sequence.size(); i++)
        {
            result += sequence.get(i);
        }
        result = result/sequence.size();
        return result;
    }
}





