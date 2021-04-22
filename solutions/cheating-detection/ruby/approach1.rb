=begin
Algorithm to solve one test case:

  1. Store player answers in an array, where the index number + 1 matches
     the player number. Let's say this array is named ANSWERS (A). The values
     in the array are strings.

  === Gather questions data ===
  2. Iterate over A checking the ith character of every element of A. The
     ith character represents the questions number. Accumulate the number
     of correct results for such ith question in a variable.
  3. After all answers have been checked for the ith question, apply
     the formula to estimate the difficulty of the question:
        (correct_answers * -6 / number_of_players) + 3
        Example: (89 * -6 / 100) + 3
     If the difficulty is greater or equal to 2.0, keep track of the
     question number using an array (let's call it EXTREME_QUESTIONS).
     Count the total number of extreme questions (needed for (5)).

  === Gather players data ===
  4. Estimate the skill level for each player according to the formula:
       (correct_answers * 6 / number_of_questions) - 3
       Example: (5000 * 6 / 10000) - 3
     Iterate over each element of A doing the following:
       a) counting the total number of correctly answered questions, and
       b) counting the number of correctly answered extreme questions.
     Store in arrays. Let's call them SKILL_LEVELS, for the array that holds
     the computation of the skill level according to the formula; and
     EXTREME_SCORES, for the array that holds (b).

  === Find the cheater ===
  5. Calculate the estimated amount of extreme questions each player should have
     answered correctly, use the sigmoid function:
       1 / (1 + e ^ -(player_skill - 2.0)) * number_of_extreme_questions,
       where e is Euler's number.
     For each ith player, compute EXTREME_SCORES[i] - estimate and keep track of
     the largest difference and the player number it belongs to.
  6. The cheater is the player number of the largest difference found in (5).
=end

NUMBER_OF_PLAYERS = 100.0
NUMBER_OF_QUESTIONS = 10000.0
EXTREME_DIFFICULTY = 2.0

def estimate_question_difficulty(correct_answers)
  (correct_answers * -6.0 / NUMBER_OF_PLAYERS) + 3.0
end

def estimate_skill_level(correct_answers)
  (correct_answers * 6.0 / NUMBER_OF_QUESTIONS) - 3.0
end

def estimate_correct_extreme_questions(skill_level, number_of_extreme_questions)
  1.0 / (1.0 + 2.718 ** -(skill_level - EXTREME_DIFFICULTY)) *
      number_of_extreme_questions
end

def solve_test_case(test_case_number)
  ########
  # Step 1
  ########
  answers = []
  100.times do
    answers << gets.chomp
  end

  ########
  # Step 2
  ########
  extreme_questions = []
  number_of_extreme_questions = 0
  answered_rigth = 0 # Correct total answers for current question.

  for i in 0..(NUMBER_OF_QUESTIONS - 1)
    for j in 0..(answers.length() - 1)
      answered_rigth += answers[j][i].to_i
    end

    ########
    # Step 3
    ########
    difficulty = estimate_question_difficulty(answered_rigth)

    if difficulty > EXTREME_DIFFICULTY
      extreme_questions[i] = true
      number_of_extreme_questions += 1
    end
    answered_rigth = 0 # Reset right answers counter.
  end

  ########
  # Step 4
  ########
  skill_levels = []
  extreme_scores = []

  for i in 0..(answers.length() - 1)
    # Zero correct answers by default.
    score = 0
    extreme_scores[i] = 0

    for j in 0..(answers[i].length - 1)
      score += answers[i][j].to_i
      if extreme_questions[j]
        extreme_scores[i] += answers[i][j].to_i
      end
    end
    skill_levels << estimate_skill_level(score)
  end

  ########
  # Step 5
  ########
  largest_difference = -NUMBER_OF_QUESTIONS
  player_with_largest_difference = nil

  for i in 0..(NUMBER_OF_PLAYERS - 1)
    difference = extreme_scores[i] - estimate_correct_extreme_questions(
        skill_levels[i],
        number_of_extreme_questions)
    if difference > largest_difference
      largest_difference = difference
      player_with_largest_difference = i + 1
    end
  end

  ########
  # Step 6
  ########
  puts "Case ##{test_case_number}: #{player_with_largest_difference}"
end

def main
  test_cases = gets.chomp.to_i
  percentage = gets.chomp.to_i
  
  for i in 1..test_cases
    solve_test_case(i)
  end
end

main()
