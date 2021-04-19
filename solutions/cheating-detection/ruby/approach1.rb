=begin
Algorithm:

  === Gather questions data ===
  1. Find top 5% of hard questions.
     These are questions that at most 0.05 * 100 = 5 players got right.
     Store the numbers of these questions in a list.
     * Loop once vertically *

  === Gather players data ===
  For each player, an object which represents it exists. The object stores:
      - Number of correctly answered top 5% hard questions (extreme questions).
      - Estimated skill level.
      - Player number.
  2. Estimate skill level of each player.
     This estimation is based on the proportion of correct answers, e.g.
     if a player answered right 7000 out of 10000 questions, then his
     skill level is equal to 7000 * 10 / 10000 = 7000 / 1000 = 7.
     I consider skill levels to be in the interval [0, 10].
     Assign each player object their skill level.
  3. Count number of correct top 5% hard questions per player. e.g. if
     only 24 questions were answered by 5 or less people, then player 3
     might have 6 of those questions right, and so on.
     Assign each player their number of correctly answered questions.
     * Loop once their answers string, getting data for both steps 2 and 3. *
  4. Sort player objects, in descending order, by estimated skill level.
     Store the sorted player objects in a list.

  === Analyse neighbors on extreme questions ===
  5. For every player, compute the max difference of correctly answered
     extreme questions in comparison to neighbors with a +-1 skill level.
     Store every computated value in the object being compared.
     Example:
     Total number of extreme questions: 24.
     Currently compared player: #1; skill level:10.0; correct  ext. q: 21.
     Player  Skill level  Correct extreme questions  Difference
     2       10.0         20                          1
     3       9.2          22                         -1
     4       9.0          18                          3
     Max difference: 3.
  6. Find which player has the biggest max difference of correctly
     answered extreme questions in comparison to its neighbors.
     This is the cheater.
=end

