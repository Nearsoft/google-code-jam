module Main where

-- Data.Char is for digitsToInt; converts a Char into an Int
import Data.Char
-- Control.Monad; replaces if then else construction on loopTestCases
import qualified Control.Monad
-- Data.List brings elemIndex, to find an index of an element, and transpose, for matrixes
import Data.List
-- Data.Maybe gives fromJust; function to retrieve a value from a Maybe construction.
import Data.Maybe

-- Constants paradise --
numStudentsFloat = 100.0
numStudentsInt = 100
numAnswersFloat = 10000.0
numAnswersInt = 10000

-- range is the space between -3 and 3, the limits of the range.
range = 6.0

-- The bottom limit of the new range. We conver from range 0,100, and 0,10000, to -3,3
minValNewRange = -3.0

-- If below 2.0, it is no a extreme question
difficultyOfExtremeQuestions = 2.0

-- End of constants paradise --

loopTestCases :: Int -> Int -> IO ()
loopTestCases numTestCases count = Control.Monad.when ( count < numTestCases )
                                    $ do
                                        -- We loop over all the students (100)
                                        loopStudents 0 [] (replicate numAnswersInt 0.0) [] count
                                        -- Next test case
                                        loopTestCases numTestCases (count + 1)

loopStudents :: Int -> [[Float]] -> [Float] -> [Float] -> Int -> IO ()
loopStudents count
             studentsAnswers
             answersDifficulty
             scaledStudentsSkills
             caseNumber

    = if count < numStudentsInt
        -- If we haven't reached the end of the students
        then do
                oneStudentAnswersString <- getLine
                -- Convert the read string into a array of floats: 1.0 or 0.0
                -- We "map" each value to an Int, then to a Float.
                let oneStudentAnswers =  map (fromIntegral . digitToInt) oneStudentAnswersString
                    -- Convert from range 0,10000 to range -3,3 the number of correct answers per student
                    oneStudentSkills =  ((sum oneStudentAnswers * range) / numAnswersFloat) + minValNewRange
                    -- Next student
                    in loopStudents (count + 1)
                                    -- We pass a new list created by adding the new parsed answer to the old list.
                                    (studentsAnswers ++ [oneStudentAnswers])
                                    -- We create a new list by adding each element of the old list with the new answer
                                    (zipWith (+) answersDifficulty oneStudentAnswers )
                                    -- New list by adding new ans to old list
                                    (scaledStudentsSkills ++ [oneStudentSkills])
                                    -- Test case in which we are
                                    caseNumber

    else do
            -- We scale from 0,100 to -3,3 the number of corrects in an answer
            let scaledAnswersDifficulty = scaleAnswers answersDifficulty
                -- Extract only the answers with difficulty > 2.0
                extremeAnswersDiff = filter (\x -> snd x > difficultyOfExtremeQuestions) scaledAnswersDifficulty
                -- We retrieve the real answers and filter only the extreme answers
                realAnswersToExtreme = extractExtremeAnsFromReal extremeAnswersDiff (transpose studentsAnswers) []
                -- Transpose the answers, so we now get (asnwers,students) in the matrix
                studentsRealAnsToExtreme = transpose realAnswersToExtreme
                -- Add the correct answers PER STUDENT
                numGoodRealExtremeAns = map sum studentsRealAnsToExtreme
                -- Length of the list
                extremeAnsLen = fromIntegral $ length extremeAnswersDiff :: Float

                -- How many questions can correctly answer that are 2.0 in difficulty
                estimate = map (\x -> 1.0 / (1.0 + 2.718 ** (-(x - 2.0))) * extremeAnsLen) scaledStudentsSkills

                -- Calculate the difference between the estimation and the real answers
                modelsDifference = zipWith (-) numGoodRealExtremeAns estimate
                -- Extract the maximum difference
                maxDiff = maximum modelsDifference
                -- The index of the difference
                index = fromJust (elemIndex maxDiff modelsDifference)
                in do
                    putStrLn $ "Case #" ++ show (caseNumber + 1) ++ ": " ++ show (index + 1)

-- Extract the difficult answers from the real answers.
extractExtremeAnsFromReal :: [(Int,Float)] -> [[Float]] -> [[Float]] -> [[Float]]
extractExtremeAnsFromReal extremeAnswersDiff
                            studentsAnswers
                            realExtremeAns

    = if null extremeAnswersDiff
            -- If we're at the end of the list
            -- return the constructed list.
            then  realExtremeAns
        else do
                -- We hace the difficulty and the index in the real answers in oneExtremeAns
                let oneExtremeAns = head extremeAnswersDiff
                    -- We extract the answer from the real answers using the index in the tuple
                    oneRealExtremeAns = studentsAnswers !! fst oneExtremeAns
                    in do
                        -- We go onto the next extreme tuple, since we ised the first one, we pass the tail of the list,
                        -- tail: all from a list except the first
                        extractExtremeAnsFromReal (tail extremeAnswersDiff)
                                                    -- Same list to extract from. If we pass without any member
                                                    -- the indexes doesn't work
                                                    studentsAnswers
                                                    -- We add the extracted answer to the list and pass it.
                                                    (realExtremeAns ++ [oneRealExtremeAns])

-- We scale the answers from range 0,100 to range -3,3
scaleAnswers :: [Float] -> [(Int,Float)]
scaleAnswers answers
    = zip [0..(numAnswersInt - 1)]
                        $ map (\x -> (((100 - x) * range) / numStudentsFloat) + minValNewRange ) answers

main :: IO ()
main = do
        -- Read lines
        numTestCasesString <- getLine
        winPercen <- getLine
        -- Force evaluation of winPercen line
        seq winPercen return 0
        let numTestCases = read numTestCasesString :: Int
        -- We do n test cases
            in loopTestCases numTestCases 0