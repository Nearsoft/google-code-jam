import Data.Char
import Data.List
import Data.Ord
import Data.Function
import Data.Maybe
import System.IO
import Control.DeepSeq

range = 6.0
numStudents = 100
numQuestions = 10000
minValueNewRange = -3
numTopBottomAns = 100
trueAnsProb = 0.53     
ansDiffTop = 2.5
ansDiffBottom = -2.5
allAround = 0.05

loopTestCases :: Int -> Int -> Handle -> IO Int
loopTestCases x numTestCases contents = if x > 0 
                                            then do
                                                loopStudents numStudents [] (replicate numQuestions 0.0) [] x numTestCases contents
                                                loopTestCases (x - 1) numTestCases contents
                                            else return 0

loopStudents :: Int -> [Float] -> [Float] -> [[Float]] -> Int -> Int -> Handle -> IO Int
loopStudents x studSkills ans studAns testCase numTestCases contents = if x > 0 
                                                                            then do
                                                                                    line <- hGetLine contents
                                                                                    let studentLineFloat = map (fromIntegral . digitToInt) line
                                                                                        sumStudentAns = sum studentLineFloat
                                                                                        skill = ((sumStudentAns * range) / fromIntegral numQuestions) + minValueNewRange

                                                                                        in do    
                                                                                                print $ "Student #" ++ show x ++ " :" ++ show sumStudentAns
                                                                                                -- print $ "Student " ++ show x ++ show studentLineFloat
                                                                                                loopStudents (x - 1) (studSkills ++ [skill]) (zipWith (+) studentLineFloat ans) (studAns ++ [studentLineFloat]) testCase numTestCases contents
                                                                        else do
                                                                                let 
                                                                                    newTestCase = numTestCases - testCase + 1
                                                                                    firstStep = map (* range) ans
                                                                                    secondStep = map (/ fromIntegral numStudents) firstStep
                                                                                    thirdStep = map (subtract (-minValueNewRange)) secondStep
                                                                                    scaledAns = map (*(-1)) thirdStep
                                                                                    -- minSkill = minimum studSkills
                                                                                    minSkill = -2.171
                                                                                    -- maxSkill = maximum studSkills
                                                                                    maxSkill = 2.021
                                                                                    ansModels = createAnsModels studSkills scaledAns [] numQuestions minSkill maxSkill
                                                                                    ansStud = transpose studAns
                                                                                    diffInModels = compareModels ansStud ansModels (replicate numStudents 0.0)
                                                                                    maxDiff = minimum diffInModels
                                                                                    index = fromJust (elemIndex maxDiff diffInModels)
                                                                                    in do
                                                                                            putStrLn $ "Case #" ++ show newTestCase ++ ": " ++ show index 
                                                                                            -- print $ length ansModels
                                                                                            -- print $ minimum studSkills
                                                                                            -- print $ maximum studSkills
                                                                                            -- print $ minimum scaledAns
                                                                                            -- print $ maximum scaledAns
                                                                                            -- print studSkills
                                                                                            -- print scaledAns
                                                                                            -- print ansModels
                                                                                            return 0

createAnsModels :: [Float] -> [Float] -> [(Int,[Float])] -> Int -> Float -> Float -> [(Int,[Float])]
createAnsModels studSkills ansDiff ansModels count minSkill maxSkill= if count <= 0
                                                                            then ansModels
                                                                        else
                                                                            let ans = head ansDiff
                                                                                tupleStudsAns = createTupleStudsAns studSkills ans count
                                                                                in 
                                                                                    if ans > ansDiffTop
                                                                                        then do
                                                                                                createAnsModels studSkills (tail ansDiff) (tupleStudsAns:ansModels) (count - 1) minSkill maxSkill
                                                                                    else
                                                                                        createAnsModels studSkills (tail ansDiff) ansModels (count - 1) minSkill maxSkill

createTupleStudsAns :: [Float] -> Float -> Int -> (Int,[Float])
createTupleStudsAns studSkills ansDiff ansIndex = ( ansIndex, map (\x -> if sigmodFunction x ansDiff > trueAnsProb then 1 else 0 ) studSkills )

sigmodFunction :: Float -> Float -> Float
sigmodFunction x y = 1.0 / (1.0 + exp (-(x - y)))

compareModels :: [[Float]] -> [(Int,[Float])] -> [Float] -> [Float]
compareModels ansStud ansModels diffInModels = if null ansModels
                                                    then diffInModels
                                                else
                                                    let ansModel = head ansModels
                                                        comparedModel = compareModel (snd ansModel) (ansStud !! fst ansModel)
                                                        in do
                                                            compareModels ansStud (tail ansModels) (zipWith (+) comparedModel diffInModels)
                                                            
compareModel :: [Float] -> [Float] -> [Float]
compareModel = zipWith (\ x y -> if x == y then 1 else 0)

main :: IO Int
main = do
        contents <- openFile "/Users/luis.nieto/HaskellTest/test2/test/test1.txt" ReadMode
        line <- hGetLine contents
        winPer <- hGetLine contents
        let numTestCases = (read :: String -> Int) line
            in
                loopTestCases numTestCases numTestCases contents

-- !H,!O,!::,!...