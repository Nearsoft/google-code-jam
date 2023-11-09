/******************
    Qualification Round - Google Code Jam 2021

    Problem #5: Cheating Detection.
    https://codingcompetitions.withgoogle.com/codejam/round/000000000043580a/00000000006d1155

    Written by: Carlos Cuevas <@cavesdev> 18/10/21
    for the Encora Apprentice Program

    The approach used here is taken from the Analysis section of the same problem:
    Estimate the players' and questions' skill and difficulty level, using number of correct answers. (probability)
    Sort by skill level
    Calculate the hardest questions and use the sigmoid function to estimate number of corrects.
    If estimated number of corrects is far from the actual results (results are much better), that player is the cheater.


*******************/

import kotlin.math.floor
import kotlin.math.exp

val PLAYERS = 100
val QUESTIONS = 10000
// val EXTREME_QUESTIONS_RATE = floor(QUESTIONS * 0.05)
val EXTREME_QUESTIONS_THRESHOLD = 2.0

fun getCorrectAnswersPerQuestion(answers: List<List<Int>>): List<Int> {
    var correctAnswersNum = mutableListOf<Int>()

    for(questionNumber in 0..(QUESTIONS - 1)){
        var questionSum = 0
        for(playerAnswers in answers) {
            if (playerAnswers[questionNumber] == 1) {
                questionSum++
            }
        }
        correctAnswersNum.add(questionSum)
    }
    return correctAnswersNum
}

fun getCorrectAnswersPerPlayer(answers: List<List<Int>>): List<Int>{
    var playerCorrectAnswers = mutableListOf<Int>()

    for (playerNum in 0..(PLAYERS - 1)) {
        val sum: Int = answers[playerNum].reduce { acc, num -> acc + num }
        playerCorrectAnswers.add(sum)
    }
    return playerCorrectAnswers
}

fun estimateQuestionsDifficulty(correctAnswersNum: List<Int>): List<Pair<Int,Double>> {
    var questionsDifficulty = mutableListOf<Pair<Int,Double>>()

    for (questionNum in 0..(QUESTIONS - 1)) {
        // x (si - qi) = ln(y/(1-y)) ??
        // prob -> [-3, 3]
        val questionDifficulty: Double = (correctAnswersNum[questionNum] * -6.0 / PLAYERS) + 3.0
        questionsDifficulty.add(Pair(questionNum, questionDifficulty))
    }
    return questionsDifficulty
}

fun estimatePlayersSkill(playerCorrectAnswers: List<Int>): List<Double> {
    var playersSkill = mutableListOf<Double>()

    for (playerNum in 0..(PLAYERS - 1)) {
        // x (si - qi) = ln(y/(1-y)) ??
        val playerSkill = (playerCorrectAnswers[playerNum] * 6.0 / QUESTIONS) - 3.0
        playersSkill.add(playerSkill)
    }
    return playersSkill
}

fun calculateExtremeQuestions(questionsDifficulty: List<Pair<Int,Double>>): List<Int> {
    var extremeQuestions = mutableListOf<Int>()
    
    for ((questionNum, questionDifficulty) in questionsDifficulty) {
        if (questionDifficulty > EXTREME_QUESTIONS_THRESHOLD ) {
            extremeQuestions.add(questionNum)
        }
    }
    return extremeQuestions
}

fun calculateProbabilityVariation(answers: List<List<Int>>, playersSkill: List<Double>, extremeQuestions: List<Int>): MutableList<Pair<Int,Double>> {
    var correctAnswerProbabilityVariations = mutableListOf<Pair<Int,Double>>()

    for (playerNum in 0..(PLAYERS - 1)) {
        var playerCorrectExtreme = 0
        val correctAnswerProbability = 1 / (1 + exp(-(playersSkill[playerNum] - EXTREME_QUESTIONS_THRESHOLD)))
        val playerCorrectAnswerProbability = extremeQuestions.size * correctAnswerProbability
        for (questionNum in extremeQuestions) {
            if (answers[playerNum][questionNum] == 1) {
                playerCorrectExtreme++
            }
        }
        val probabilityVariation = playerCorrectExtreme - playerCorrectAnswerProbability
        correctAnswerProbabilityVariations.add(Pair(playerNum + 1, probabilityVariation))
    }
    return correctAnswerProbabilityVariations
}

fun getCheater(): Int {

    // save every line from current test case (players' answers)
    var answers = mutableListOf<List<Int>>()

    for(`_` in 0..(PLAYERS - 1)){
        val playerAnswers = readLine()
        val answerArray: List<Int> = playerAnswers!!.map { it.toString().toInt() }   
        answers.add(answerArray)
    }

    var correctAnswersNum = getCorrectAnswersPerQuestion(answers)

    var playerCorrectAnswers = getCorrectAnswersPerPlayer(answers)

    var questionsDifficulty = estimateQuestionsDifficulty(correctAnswersNum)

    var playersSkill = estimatePlayersSkill(playerCorrectAnswers)

    var extremeQuestions = calculateExtremeQuestions(questionsDifficulty)

    var correctAnswerProbabilityVariations = calculateProbabilityVariation(answers, playersSkill, extremeQuestions)
    correctAnswerProbabilityVariations.sortByDescending { it.second }
    return correctAnswerProbabilityVariations[0].first
}

fun main() {
    var testCases: Int = readLine()!!.toInt()
    readLine() // get p percent of accuracy to pass test cases

    for(case in 1..testCases) {
        println("Case #${case}: ${getCheater()}")
    }
}