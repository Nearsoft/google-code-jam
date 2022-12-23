{-
  The following code solves the Median Sort problem.

  To better understand the comments, read them from the bottom-most blocks and upwards, since
  functions start at "main" and keep calling the previous ones.
-}

import Data.List
import System.IO
import Control.Monad
import Data.Char

-- This function just converts the solution list into string format so that it can be outputed as an answer.
str_gen :: [Int] -> String -> String
str_gen ls str  | length ls == 1 = str ++ (show (head ls))
                | otherwise = str_gen (tail ls) (str ++ (show (head ls) ++ " "))

-- This function sends to the judge three numbers. Then, it receives one of those numbers back, indicating
-- that it is the median of the three in the solution we are seeking.
get_res :: [Int] -> IO String
get_res ls = do
    putStrLn (str_gen ls "")
    hFlush stdout
    m <- getLine
    return m

-- This just generates a number for the get_i_j function. It was tedious to create it inside said function,
-- since the function has to check if thelength of the list is even or odd.
get_aux :: Int -> Int
get_aux len | len `mod` 3 == 0 = (len `div` 3)
            | otherwise = (len `div` 3) + 1

-- This calculates the indexes that are approximately at 1/3 and 2/3 of the list.
get_i_j :: [Int] -> [Int]
get_i_j ls = [i,j]
                where
                    aux = get_aux (length ls)
                    i = aux - 1
                    j = (2 * aux) - 1

-- Here, we evaluate where the current new number (index) must be placed.
insert_ind :: Int -> Int -> Int -> Int -> Int -> [Int] -> [Int] -> [Int] -> IO ()
                                    -- First, if the median was the new number, and i and j are right next to each other,
                                    -- then we can just place that number right there and keep going with the next number.
insert_ind n i j m ind prev ls aft  | m == ind && j - i == 1 = solve n (ind+1) [] (prev ++ (take (i+1) ls) ++ [ind] ++ (drop j ls) ++ aft) []
                                    -- If there are more numbers between i and j, then we need to repeat the process with that
                                    -- new sublist from i to j, inclusive.
                                    | m == ind && j - i /= 1 = solve n ind (prev ++ (take i ls)) (drop i (take j ls)) ((drop j ls) ++ aft)
                                    -- In case the median was the element at i, and i is actually the first element in the list,
                                    -- then we can just place the new element at the very beginning and keep going with the next number.
                                    | m == (ls !! i) && i == 0 = solve n (ind+1) [] (prev ++ [ind] ++ ls ++ aft) []
                                    -- If there are more numbers to the left of the i-th element, then we repeat the process with the new
                                    -- sublist that goes from index 0 up until i, inclusive.
                                    | m == (ls !! i) && i /= 0 = solve n ind prev (take (i+1) ls) ((drop (i+1) ls) ++ aft)
                                    -- In case the median was the j-th element, and it is actually the last element, then we can just
                                    -- append the new number at the end of the list and keep going with the next number.
                                    | m == (ls !! j) && j == ((length ls)-1) = solve n (ind+1) [] (prev ++ ls ++ [ind] ++ aft) []
                                    -- If there were more numbers to the right of the j-th element, then we repeat the process with the
                                    -- new sublist from j up until the end of the list, inclusive.
                                    | m == (ls !! j) && j /= ((length ls)-1) = solve n ind (prev ++ (take j ls)) (drop j ls) aft

-- This function checks if the answer is ready or, if not, repeats the insertion process with the next value.
solve :: Int -> Int -> [Int] -> [Int] -> [Int] -> IO ()
solve n ind prev ls aft | ind > n = do
                                get_res ls
                                return ()
                        | otherwise = do
                                -- Here we get the new i and J values of the current list.
                                let ij = get_i_j ls
                                    i = ij !! 0
                                    j = ij !! 1
                                -- Here we receive the input from the judge about which is the median.
                                -- We ask sending the values from our list at indexes i and j, as well
                                -- as the new index.
                                m <- get_res [(ls !! i), (ls !! j), ind]
                                insert_ind n i j (read m :: Int) ind prev ls aft

-- The iterator will repeat the algorithm T times.                           
iterator t n    | t == 0 = return ()
                | otherwise = do
                    -- We pass an initial form of the soultion as [1,2] and we begin with index 3
                    -- looking for its position.
                    solve n 3 [] [1,2] []
                    iterator (t-1) n

-- First, we recieve T, N and Q. We ignore Q, since the algorithm never takes as many tries to find the solution.
main = do
    hSetBuffering stdout NoBuffering
    [t, n, q] <- fmap(map read.words)getLine
    iterator t n
