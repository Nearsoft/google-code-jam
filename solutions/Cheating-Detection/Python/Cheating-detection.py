# Nicolas Restrepo

def  cheating_detection():
    players =  100
    questions = 10000
    answers = []

    for player in range(players):
        answers += list(map(int, (input().strip())))
    questions_right_answers = []

    for question in range(questions):
        question_index = question + 1
        sum_correct_answers = 0

        for player in range(players):
            if answers[question_index - 1] == 1:
                sum_correct_answers += 1
            question_index += questions

        questions_right_answers.append(sum_correct_answers)

    players_right_answers =  []
    player_index =  0
    for player in  range(players):
        correct_answers =  sum(
        answers[player_index:  (player_index)  + questions])
        players_right_answers.append(correct_answers)
        player_index += questions

    questions_difficulty =  []
    for question in  range(questions):
        difficulty =  round(
        (((questions_right_answers[question]  *  (-6.00))  / players)  +  3.00),  2)
        questions_difficulty.append(difficulty)

    players_skill =  []
    for player in  range(players):
        skill =  round(
            (((players_right_answers[player]  *  6.00)  / questions)  -  3.00),  2)
        players_skill.append(skill)

    extreme_questions =  []
    for question in  range(questions):
        if questions_difficulty[question]  >  2.0:
            extreme_questions.append(question)

    question_per_player = 0
    players_difference = []

    for player in range(players):
        difference = 0
        question_difficulty = 2.0
        player_skill = players_skill[player]
        probability_of_right_answer = round(
            1 / (1 + 2.718 ** -(player_skill - question_difficulty)), 2)

        quantity_of_extreme_questions = len(extreme_questions)
        quantity_of_player_posible_right_extreme_answers = quantity_of_extreme_questions * probability_of_right_answer
        player_right_extreme_answers = 0

        for answer in range(len(extreme_questions)):
            player_answer = answers[int(extreme_questions[answer]) + question_per_player]

            if player_answer == 1:
                player_right_extreme_answers += 1

        difference = player_right_extreme_answers - quantity_of_player_posible_right_extreme_answers
        players_difference.append(difference)
        question_per_player += questions

    cheater = players_difference.index(max(players_difference))  +  1
    return(cheater)

if __name__ ==  '__main__':
	test_cases =  int(input())
	p =  int(input())
	for test_case in  range(1, test_cases +  1):
		print(f"Case #{test_case}: {cheating_detection()}")