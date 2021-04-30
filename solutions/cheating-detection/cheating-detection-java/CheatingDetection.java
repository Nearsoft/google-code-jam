import java.util.*;

class Solution {
  static Double getMax(Double[] inputArray) {
    Double maxValue = inputArray[0];
    for (int i = 1; i < inputArray.length; i++) {
      if (inputArray[i] > maxValue) {
        maxValue = inputArray[i];
      }
    }
    return maxValue;
  }

  static int cheatingDetection(Scanner input) {
    
    int players = 100;
    int questions = 10000;
    int[] answers = new int[players * questions];
    int player_answers_index = 0;

    for (int player = 0; player < players; player++) {
      String playerAnswers = input.nextLine();
      for (int question = 0; question < questions; question++) {
        answers[player_answers_index] = Character.getNumericValue(playerAnswers.charAt(question));
        player_answers_index += 1;
      }
    }

    int[] questionsRightAnswers = new int[questions];
    for (int question = 0; question < questions; question++) {
      int questionIndex = question + 1;
      int sumCorrectAnswers = 0;
      for (int player = 0; player < players; player++) {
        if (answers[questionIndex - 1] == 1) {
          sumCorrectAnswers += 1;
        }
        questionIndex += questions;
      }
      questionsRightAnswers[question] = sumCorrectAnswers;
    }

    int[] playerRightAnswers = new int[players];
    int playerIndex = 0;
    for (int player = 0; player < players; player++) {
      int sumCorrectAnswers = 0;
      for (int question = 0; question < questions; question++) {
        if (answers[question + playerIndex] == 1) {
          sumCorrectAnswers += 1;
        }
      }
      playerRightAnswers[player] = sumCorrectAnswers;
      playerIndex += questions;
    }

    Double[] questionsDifficulty = new Double[questions];
    for (int question = 0; question < questions; question++) {
      Double difficulty = (((questionsRightAnswers[question] * (-6.00)) / players) + 3.00);
      questionsDifficulty[question] = difficulty;
    }

    Double[] playersSkill = new Double[players];
    for (int player = 0; player < players; player++) {
      Double skill = (((playerRightAnswers[player] * (6.00)) / questions) - 3.00);
      playersSkill[player] = skill;
    }

    ArrayList<Integer> extremeQuestions = new ArrayList<Integer>();
    for (int question = 0; question < questions; question++) {
      if (questionsDifficulty[question] > 2.0) {
        extremeQuestions.add(question);
      }
    }

    int questionPerPlayer = 0;
    Double[] playersDifference = new Double[players];
    for (int player = 0; player < players; player++) {
      Double difference = 0.0;
      Double questionDifficulty = 2.0;
      Double playerSkill = playersSkill[player];
      Double probabilityOfRightAnswer = 1 / (1 + Math.pow(2.718, -(playerSkill - questionDifficulty)));
      int quantityOfExtremeQuestions = extremeQuestions.size();
      Double quantityOfPlayerPossibleRightExtremeAnswers = quantityOfExtremeQuestions * probabilityOfRightAnswer;
      int playerRightExtremeAnswers = 0;
      for (int extremeAnswer = 0; extremeAnswer < extremeQuestions.size(); extremeAnswer++) {
        int playerAnswer = answers[extremeQuestions.get(extremeAnswer) + questionPerPlayer];
        if (playerAnswer == 1) {
          playerRightExtremeAnswers += 1;
        }
      }
      difference = playerRightExtremeAnswers - quantityOfPlayerPossibleRightExtremeAnswers;
      playersDifference[player] = difference;
      questionPerPlayer += questions;
    }
    Double cheaterValue = getMax(playersDifference);
    int cheaterIndex = Arrays.asList(playersDifference).indexOf(cheaterValue) + 1;
    return cheaterIndex;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int testCases = input.nextInt();
    int percentageOfAccuracy = input.nextInt();
    input.nextLine();
    for (int testCase = 1; testCase < testCases + 1; testCase++) {
      System.out.println("Case #" + testCase + ": " + cheatingDetection(input));
    }
    input.close();
  }
}
