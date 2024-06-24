(ns clojure-sandbox.cheatingDetection)
(require '[clojure.math.numeric-tower :as math])

;constants
(def numStudentsFloat 100.0)
(def numStudentsInt 100)
(def numAnswersFloat 10000.0)
(def numAnswersInt 10000)

(def rng 6.0)

(def minValNewRange (- 3.0))

(def difficultyOfExtremeQuestions 2.0)

(defn extractExtremeAnsFromReal
  [extremeAnswersDiff studentAnswers realExtremeAns]
  (if (nil? extremeAnswersDiff) realExtremeAns
      (do
        (let [oneExtremeAns (first extremeAnswersDiff)
              oneRealExtremeAns (studentAnswers (first oneExtremeAns))]
          (extractExtremeAnsFromReal (rest extremeAnswersDiff) studentAnswers (conj realExtremeAns oneRealExtremeAns))))))

(defn obtainQuestionsDifficuly [x]
  (+ minValNewRange (/ numStudentsFloat (* rng (- 100 x)))))

(defn scaleAnswer [answers]
  (let [vecRange (range 0 (- numAnswersInt 1))
        oQd (map obtainQuestionsDifficuly answers)]
    (map vector vecRange oQd)))

(defn estimateCorrectAns
  [extremeAnsLen scaleStudentSkills]
  map (* extremeAnsLen (/ 1.0 (+ 1.0 (math/expt 2.718 (- (- scaleStudentSkills 2.0)))))))


(defn loopStudents
  [caseNumber studentAnswers answersDifficulty scaleStudentSkills cnt]

  (if (< cnt numStudentsInt)
    (do
      (let [oneStudentAnswers (vec (Integer/parseInt (read-line)))
            oneStudentSkills (+ (/ (reduce + oneStudentAnswers) numAnswersFloat) minValNewRange)]
        (loopStudents caseNumber (conj studentAnswers oneStudentAnswers) (map + answersDifficulty oneStudentAnswers) (conj scaleStudentSkills oneStudentSkills) (+ cnt 1))))
    (do
      (let [scaledAnswerDifficulty (scaleAnswer answersDifficulty)
            extremeAnswersDiff (filter (fn [[k v]] (> v difficultyOfExtremeQuestions)) scaledAnswerDifficulty)
            realAnswersToExtreme (extractExtremeAnsFromReal extremeAnswersDiff (apply mapv vector studentAnswers) [])
            studentsRealAnsToExtreme (apply mapv vector realAnswersToExtreme)
            numGoodRealExtremeAns (reduce + studentsRealAnsToExtreme)
            extremeAnsLen (count extremeAnswersDiff)
            estimate (estimateCorrectAns extremeAnsLen scaleStudentSkills)
            modelsDifference (map - (numGoodRealExtremeAns) (estimate))
            index (key (apply max-key val modelsDifference))]
        (println "Case #" (+ caseNumber 1) ":" (+ index 1))))))

(defn loopTestCases [numTestCases cnt]
  (if (< cnt numTestCases)
    (do
      (loopStudents 0 [] (repeat numAnswersInt 0.0) [] cnt)
      (loopTestCases numTestCases (+ cnt 1)))))


(defn -main []
  (def numTestCases (Integer/parseInt (read-line)))
  (def winPercet (read-line))
  (loopTestCases numTestCases 0))
