{-
  The following code solves the Reversort Engineering problem.

  To better understand the comments, read them from the bottom-most blocks and upwards, since
  functions start at "main" and keep calling the previous ones.
-}

import Data.List
import System.IO
import Control.Monad
import Data.Char

-- This function sets the solution to the desired output format.
str_gen :: [Integer] -> String -> String
str_gen res str | length res == 1 = str ++ (show (head res))
                | otherwise = str_gen (tail res) (str ++ (show (head res) ++ " "))

-- This function mutates the list so that it increments the cost in 1.
pusher :: [Integer] -> Integer -> [Integer]
pusher ls num = (rest ++ pushed ++ elem) -- 1 3 2
                where
                    elem = [head (drop (fromIntegral num) ls)]
                    pushed = tail (drop (fromIntegral num) ls)
                    rest = take (fromIntegral num) ls


-- This function mutates the solution for the minimum possible cost, incrementing its cost by 1 in each iteration. It iterates
-- the number of times needed for the cost to get to desired amount, and then outputs the mutated sequence.
c_finder :: Integer -> Integer -> Integer -> Integer -> Integer -> [Integer] -> [Integer] -> [Integer] -> [Integer]
c_finder n c i step stage ls prev aft   | i == (no_steps + 1) = (prev ++ ls ++ aft)
                                        | step > (n - stage) && (stage+1) `mod` 2 == 0 = c_finder n c i 1 (stage+1) (drop ((fromIntegral (stage-1)) `div` 2) (take ((fromIntegral n) - ((fromIntegral (stage+1)) `div` 2)) (prev ++ ls ++ aft))) (take ((fromIntegral (stage-1)) `div` 2) [2,4..]) (reverse (take ((fromIntegral (stage+1)) `div` 2) [1,3..]))
                                        | step > (n - stage) && (stage+1) `mod` 2 /= 0 = c_finder n c i 1 (stage+1) (drop ((fromIntegral (stage-1)) `div` 2) (take ((fromIntegral n) - ((fromIntegral stage) `div` 2)) (prev ++ ls ++ aft))) (take ((fromIntegral (stage-1)) `div` 2) [2,4..]) (reverse (take ((fromIntegral stage) `div` 2) [1,3..]))
                                        | otherwise = c_finder n c (i+1) (step+1) stage (pusher ls ((fromIntegral (length ls)) - step - 1)) prev aft
                                        where
                                            no_steps = c - n + 1

                                   
-- This function checks if the solution can be given immediatley or if we must generate it.
sorter :: Integer -> Integer -> [Integer]
            -- If the cost given is the minimum or maximum possible, the answer can be created manually immediately.
sorter n c  | c < min_c || c > max_c = [-1]
            | c == min_c = [1..n]
            | c == max_c && n `mod` 2 == 0 = (take ((fromIntegral n) `div` 2) [2,4..]) ++ reverse ((take ((fromIntegral n) `div` 2) [1,3..]))
            | c == max_c && n `mod` 2 /= 0 = (take ((fromIntegral (n-1)) `div` 2) [2,4..]) ++ reverse ((take ((fromIntegral (n+1)) `div` 2) [1,3..]))
            -- However, if it is only in the possible range, then we need to generate it.
            | otherwise = c_finder n c 1 1 1 [1..n] [] []
            where
                min_c = n-1
                max_c = (n * (n+1)) `div` 2


-- This function iterates over every case received, solving it or stating if it is impossible with the given cost C.
iterator :: [String] -> Integer -> IO ()
iterator inputCases index   | length inputCases == 1 && res /= [-1] = appendFile "output.txt" ("Case #" ++ (show index) ++ ": " ++ sol)
                            | length inputCases == 1 && res == [-1] = appendFile "output.txt" ("Case #" ++ (show index) ++ ": IMPOSSIBLE")
                            | res /= [-1] = do
                                appendFile "output.txt" ("Case #" ++ (show index) ++ ": " ++ sol ++ "\n")
                                iterator (tail inputCases) (index+1)
                            | res == [-1] = do
                                appendFile "output.txt" ("Case #" ++ (show index) ++ ": IMPOSSIBLE\n")
                                iterator (tail inputCases) (index+1)
                            where
                                thisCase = words (head inputCases)
                                res = sorter (read (thisCase !! 0) :: Integer) (read (thisCase !! 1) :: Integer)
                                sol = str_gen res ""


-- We first ask for all cases, reading the input text file.
main = do  
        let list = []
        handleInput <- openFile "input.txt" ReadMode
        contents <- hGetContents handleInput
        -- Every line from the file is a case we need analyze.
        let inputCases = lines contents
        -- We call the iterator, sending the index as 1 to start.
        iterator (tail inputCases) 1
        hClose handleInput
