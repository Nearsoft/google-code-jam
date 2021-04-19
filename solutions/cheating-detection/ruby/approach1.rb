=begin
Algorithm to solve one test case:

  1. Store player answers in an array, where the index number + 1 matches
     the player number. Let's say this array is named ANSWERS (A). The values
     in the array are strings.

  === Gather questions data ===
  2. Estimate the difficulty for each question.
     Formula:
       (players_who_got_it_right * -6 / total_players) + 3
       Example: (40 * -6 / 100) + 3
     a) Iterate over A checking the ith character of every element of A. The
        ith character represents the questions number, accumulate in another
        array (let's call it CORRECT_ANSWERS) the correct answers for each
        question.
     b) After this, compute the difficulty of each questions and substitute it
        in-place in array CORRECT_ANSWERS.
  3. Find the top hardest questions (extreme questions).
     These are questions with difficulte greater or equal to 2.0.

  ---Alternate steps 2 and 3 (save memory and compute time)---
  2. Iterate over A checking the ith character of every element of A. The
     ith character represents the questions number. Accumulate the number
     of correct results for such ith question in a variable.
  3. After the all answers have been checked for the ith question, apply
     the formula to determine the difficulty of the question. If the
     difficulty is greater or equal to 2.0, store the number of the question
     in the array of extreme questions (let's call it EXTREME_QUESTIONS).

  === Gather players data ===
  4. Estimate the skill level for each player.
     Formula:
       (right_answers * 6 / number_of_questions) - 3
       Example: (5000 * 6 / 10000) - 3
     Iterate over each element of A doing the following:
       a) counting the total number of correctly answered questions, and
       b) counting the number of correctly answered extreme questions.
     Store (a) and (b) in arrays, let's call them SCORES and EXTREME_SCORES.

  === Find the cheater ===
  5. Calculate the estimated amount of extreme questions each player should have
     answered correctly, use the sigmoid function:
       1 / (1 + e ^ -(player_skill - 2.0)), where e is Euler's number.
     For each ith player, store EXTREME_SCORES[i] - estimate in an array, let's
     call this array DIFFERENCES.
  6. Find the largest element in the array DIFFERENCES, its index + 1 is the number
     of the cheating player.
=end

=begin
What I need to learn about the programming language:
  - functions
  - variables
  - string manipulation
  - arithmetic
  - lists
  - stdin / stdout
  - control structures (for, if, etc.)
=end
