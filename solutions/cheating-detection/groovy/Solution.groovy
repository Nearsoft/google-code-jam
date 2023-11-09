import java.util.stream.*

public class Solution {
  static final int PARTICIPANTS_NUMBER = 100;
  static final int QUESTIONS_NUMBER = 10000;

  public static int findCheater(String[] answers) {
    int[] playerCorrectAns = Arrays.stream(answers)
      .mapToInt(ans -> (int) ans.chars().filter(ch -> ch == '1').count())
      .toArray(); // Count correct answers
    
    int[] sortedPlayers = IntStream.range(0, PARTICIPANTS_NUMBER)
      .boxed()
      .sorted(Comparator.comparing(i -> playerCorrectAns[i]))
      .mapToInt(x -> x)
      .toArray(); // Sort players by skill
            
    double[] skill = new double[PARTICIPANTS_NUMBER]; // Skill array

    for (int i = 0; i < skill.length; ++i) {
      skill[sortedPlayers[i]] = -3 + 6.0 / (PARTICIPANTS_NUMBER - 1) * i;
    }

    int[] questionCorrectParts = IntStream.range(0, QUESTIONS_NUMBER)
      .map(i -> (int) Arrays.stream(answers).filter(ans -> ans.charAt(i) == '1').count())
      .toArray(); // Count correct answers by question

    int[] sortedQuestions = IntStream.range(0, QUESTIONS_NUMBER)
      .boxed()
      .sorted(Comparator.comparing(i -> questionCorrectParts[i]))
      .mapToInt(x -> x)
      .toArray(); // Sort answers by difficulty

    double[] questions = new double[QUESTIONS_NUMBER];

    for (int i = 0; i < questions.length; ++i) { // Questions difficulty dist
      questions[sortedQuestions[i]] = 3 - 6.0 / (QUESTIONS_NUMBER - 1) * i;
    }

    int[] diffs = new int[PARTICIPANTS_NUMBER]; // Differences between participant and next participant value

    for (int i = 1; i < PARTICIPANTS_NUMBER - 1; ++i) {
      diffs[i] = evaluate(
        questions,
        answers[sortedPlayers[i]], // To evaluate participant
        skill[sortedPlayers[i]],
        answers[sortedPlayers[i - 1]], // Previous participant
        skill[sortedPlayers[i - 1]]
      );
    }

    int[] sortedDiffs = Arrays.stream(diffs)
      .boxed()
      .sorted(Comparator.reverseOrder())
      .mapToInt(x -> x)
      .toArray(); // Sort desc differences

    if (sortedDiffs[0] - sortedDiffs[1] >= 50) { // Half is meaningful
      return sortedPlayers[
        IntStream.range(1, PARTICIPANTS_NUMBER - 1)
          .boxed()
          .max(Comparator.comparing(i -> diffs[i]))
          .get()
      ] + 1;
    }

    int maxPlayerCorrectNums = Arrays.stream(playerCorrectAns).max().getAsInt();

    return IntStream.range(0, answers.length)
      .filter(i -> playerCorrectAns[i] == maxPlayerCorrectNums)
      .findAny()
      .getAsInt() + 1;
  }

  static int evaluate(
    double[] questions,
    String answers,
    double skill,
    String previousAnswers,
    double previousSkill ) {

    int number = IntStream.range(0, QUESTIONS_NUMBER).filter(
      i -> skill - questions[i] >= 1 && answers.charAt(i) == '0' && previousAnswers.charAt(i) == '1').count();

    return number
  }

  static main(args) {
    Scanner scanner = new Scanner(System.in);
    int numberOfTests = scanner.nextInt();

    scanner.nextInt(); // Perc of success cases num

    for (int testCase = 1; testCase <= numberOfTests; ++testCase) {
      String[] answers = new String[PARTICIPANTS_NUMBER];

      for (int i = 0; i < answers.length; ++i) {
        answers[i] = scanner.next();
      }

      int cheater = findCheater(answers)

      println "Case #$testCase: $cheater"
    }

    scanner.close();
  }
}