{-
  The following code solves the Moons and Umbrellas problem.

  To better understand the comments, read them from the bottom-most blocks and upwards, since
  functions start at "main" and keep calling the previous ones.
-}

import Data.List
import System.IO
import Control.Monad
import Data.Char

-- This function is called when encountering a ? and its previous and following characters are actually the same.
equals :: Integer -> Integer -> Integer -> Integer -> Char -> String -> [Integer]
equals x y contX contY prev str | prev == 'C' && x + y <= 0 = checker x y (contX+1) (contY+1) 'J' (tail str)
                                | prev == 'J' && x + y <= 0 = checker x y (contX+1) (contY+1) 'C' (tail str)
                                | prev == 'C' && x + y > 0 = checker x y contX contY 'C' (tail str)
                                | prev == 'J' && x + y > 0 = checker x y contX contY 'J' (tail str)

-- Both sum_greater and sum_less deal with the possibilities in case of an initial sequence of only ?. One is used for when
-- the sum of X + Y is greater than 0, and the other onw for when it is less.
sum_greater :: Integer -> Integer -> Integer -> Integer -> Integer -> String -> [Integer]
sum_greater x y contX contY num str | x <= 0 && y > 0 && head (tail str) == 'C' = checker x y contX (contY+1) (head (tail str)) (tail (tail str))
                                    | x > 0 && y <= 0 && head (tail str) == 'J' = checker x y (contX+1) contY (head (tail str)) (tail (tail str))
                                    | otherwise = checker x y contX contY (head (tail str)) (tail (tail str))

sum_less :: Integer -> Integer -> Integer -> Integer -> Integer -> String -> [Integer]
sum_less x y contX contY num str    | num `mod` 2 == 0 = checker x y (contX+(fromIntegral (num `div` 2))) (contY+(fromIntegral (num `div` 2))) (head (tail str)) (tail (tail str))
                                    | x <= 0 && y > 0 && head (tail str) == 'C' = checker x y (contX+(fromIntegral ((num-1) `div` 2))) (contY+(fromIntegral ((num-1) `div` 2))) (head (tail str)) (tail (tail str))
                                    | x <= 0 && y > 0 && head (tail str) == 'J' = checker x y (contX+(fromIntegral ((num+1) `div` 2))) (contY+(fromIntegral ((num-1) `div` 2))) (head (tail str)) (tail (tail str))
                                    | x > 0 && y <= 0 && head (tail str) == 'C' = checker x y (contX+(fromIntegral ((num-1) `div` 2))) (contY+(fromIntegral ((num+1) `div` 2))) (head (tail str)) (tail (tail str))
                                    | x > 0 && y <= 0 && head (tail str) == 'J' = checker x y (contX+(fromIntegral ((num-1) `div` 2))) (contY+(fromIntegral ((num-1) `div` 2))) (head (tail str)) (tail (tail str))
                                    | x <= 0 && y <= 0 && head (tail str) == 'C' = checker x y (contX+(fromIntegral ((num-1) `div` 2))) (contY+(fromIntegral ((num+1) `div` 2))) (head (tail str)) (tail (tail str))
                                    | x <= 0 && y <= 0 && head (tail str) == 'J' = checker x y (contX+(fromIntegral ((num+1) `div` 2))) (contY+(fromIntegral ((num-1) `div` 2))) (head (tail str)) (tail (tail str))

-- This function is called if ever the situation comes when the string has only ? characters.
all_blanks :: Integer -> Integer -> Integer -> Integer -> Integer -> String -> [Integer]
all_blanks x y contX contY num str  | x > 0 && y > 0 = [contX, contY]
                                    | x + y < 0 && num `mod` 2 /= 0 = [(contX+(fromIntegral (num `div` 2))), (contY+(fromIntegral (num `div` 2)))]
                                    | x + y > 0 && x <= 0 && y > 0 = [(contX+1), contY]
                                    | x + y > 0 && x > 0 && y <= 0 = [contX, (contY+1)]
                                    | x <= 0 && num `mod` 2 == 0 && x < y = [(contX+(fromIntegral (num `div` 2))), (contY+(fromIntegral (num `div` 2))-1)]
                                    | y <= 0 && num `mod` 2 == 0 && y < x = [(contX+(fromIntegral (num `div` 2))-1), (contY+(fromIntegral (num `div` 2)))]
                                    

-- This functin is called when the string actually begins with one or more ?. 
first_different :: Integer -> Integer -> Integer -> Integer -> Integer -> String -> [Integer]
first_different x y contX contY num str | length str == 1 = all_blanks x y contX contY num str
                                        | head (tail str) == '?' = first_different x y contX contY (num+1) (tail str)
                                        -- Depending on if the sum of the pattern prices is negative or positive, differnet sequences
                                        -- are convenient.
                                        | x + y <= 0 = sum_less x y contX contY num str
                                        | x + y > 0 = sum_greater x y contX contY num str

-- This function is called when both the previous and the following character of a ? are different between them.
-- In this case, the possibilities are dealt with, depending on if X and/or Y are negativo or positive.
different :: Integer -> Integer -> Integer -> Integer -> Char -> String -> [Integer]
different x y contX contY prev str  | head (tail str) == '?' && x > 0 && y > 0 = missing x y contX contY prev (tail str)
                                    | prev == 'C' && x <= 0 = checker x y (contX+1) contY 'J' (tail str)
                                    | prev == 'J' && y <= 0 = checker x y contX (contY+1) 'C' (tail str)
                                    | prev == 'C' = checker x y (contX+1) contY 'J' (tail str)
                                    | prev == 'J' = checker x y contX (contY+1) 'C' (tail str)
                                    | prev == 'a' = first_different x y contX contY 1 str

-- If the current character is a ?, then we check what is more convenient to treat it as: a C or a J.
missing :: Integer -> Integer -> Integer -> Integer -> Char -> String -> [Integer]
                                    -- Depending on if X and/or Y are negative or positive, we place whatever gives the
                                    -- artist more profit in that particular section of the string.
missing x y contX contY prev str    | length str == 1 && x > 0 && y > 0 = [contX, contY]
                                    | length str == 1 && prev == 'C' && x <= 0 = [contX+1, contY]
                                    | length str == 1 && prev == 'J' && y <= 0 = [contX, contY+1]
                                    | length str == 1 = [contX, contY]
                                    | prev == (head (tail str)) = equals x y contX contY prev str
                                    | prev /= (head (tail str)) = different x y contX contY prev str   
                                    | prev /= 'C' && prev /= 'J' && head (tail str) == 'C' && y <= 0 = checker x y contX contY 'J' (tail str)
                                    | prev /= 'C' && prev /= 'J' && head (tail str) == 'J' && x <= 0 = checker x y contX contY 'C' (tail str)
                                    | prev /= 'C' && prev /= 'J' && head (tail str) == 'C' && y > 0 = checker x y contX contY 'C' (tail str)
                                    | prev /= 'C' && prev /= 'J' && head (tail str) == 'J' && x > 0 = checker x y contX contY 'J' (tail str)
                                    
-- This function is mostly a counter for CJ or JC patterns. If it finds a ?, it calls for other functions that, depending
-- on the situation, increase one, both or none of the counters for CJ or JC patterns.
checker :: Integer -> Integer -> Integer -> Integer -> Char -> String -> [Integer]
checker x y contX contY prev str    | actual == '?' = missing x y contX contY prev str
                                    -- All these following cases are for when we are analyzing the last element of the string.
                                    | length str == 1 && prev == 'J' && actual == 'C' = [contX, contY+1]
                                    | length str == 1 && prev == 'C' && actual == 'J' = [contX+1, contY]
                                    | length str == 1 && actual == '?' = different x y contX contY prev str
                                    | length str == 1 = [contX, contY]
                                    -- These cases simply increase a counter when finding a pattern.
                                    | prev == 'J' && actual == 'C' = checker x y contX (contY+1) actual (tail str)
                                    | prev == 'C' && actual == 'J' = checker x y (contX+1) contY actual (tail str)
                                    | otherwise = checker x y contX contY actual (tail str)
                                    where
                                        actual = (head str)


-- The iterator function solves each case and then jumps to the next one.
iterator :: [String] -> Integer -> IO ()
iterator inputCases index   | length inputCases == 1 = appendFile "output.txt" ("Case #" ++ (show index) ++ ": " ++ (show total))
                            | otherwise = do
                                appendFile "output.txt" ("Case #" ++ (show index) ++ ": " ++ (show total) ++ "\n")
                                iterator (tail inputCases) (index+1)
                            where
                                -- In case the first element of the string must be checked, this sends 'a' as a previous character
                                -- in the string. That way, when analyzing, we can distinguish if it is the first one.
                                prev = 'a'
                                -- Setting counters to 0.
                                contX = 0
                                contY = 0
                                thisCase = words (head inputCases)
                                -- We obtain the number of counts by calling the "checker" function and analyzing the given string.
                                counts = (checker (read (thisCase !! 0) :: Integer) (read (thisCase !! 1) :: Integer) contY contX prev (thisCase !! 2))
                                total = (((counts !! 0) * (read (thisCase !! 0) :: Integer)) + ((counts !! 1) * (read (thisCase !! 1) :: Integer)))


-- The function receives the input as a text file whose path must be established here. It then reads how many
-- cases it must evaluate, their costs for X and Y and the strings to evaluate.
main = do  
        let list = []
        handleInput <- openFile "input.txt" ReadMode
        contents <- hGetContents handleInput
        let inputCases = lines contents

        iterator (tail inputCases) 1

        hClose handleInput   




