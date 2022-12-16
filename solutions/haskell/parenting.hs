{-
  The following code solves the Parenting Partnering problem.

  To better understand the comments, read them from the bottom-most blocks and upwards, since
  functions start at "main" and keep calling the previous ones.
-}

import Data.List
import System.IO
import Control.Monad
import Data.Char
import Data.Function


-- This prepares the solution in the desired format to send as output.
str_gen :: String -> Int -> Int -> String
str_gen str ind t   | ind == t = "Case #" ++ (show ind) ++ ": " ++ str
                    | ind /= t = "Case #" ++ (show ind) ++ ": " ++ str ++ "\n"

-- This function places any output.
put_res :: String -> IO ()
put_res str = do
    putStrLn str

-- This function recieves any imput.
get_res :: IO String
get_res = do
    m <- getLine
    return m

-- This function delegates the current activity to someone, or sends "IMPOSSIBLE" if there is no available person.
delegate :: [[Int]] -> Int -> [Int] -> [Int] -> [Int] -> String -> IO String
                                        -- If C is available, it delegates it to C, placing a "C" as next character in the solution.
delegate act ind prevC prevJ input str  | (prevC !! 1) <= (input !! 0) = test act (ind+1) input prevJ (str ++ "C")
                                        -- If J is available, it delegates it to J, placing a "J" as next character in the solution.
                                        | (prevJ !! 1) <= (input !! 0) = test act (ind+1) prevC input (str ++ "J")
                                        -- If nobody is available, then it is IMPOSSIBLE.
                                        | otherwise = test act (ind+1) prevC prevJ ("IMPOSSIBLE")

-- This function checks to see if a specific case has already been solved (or if it is impossible). If not, it analyzed the next activity
-- to see if it can be delegated to any available person, C or J.
test :: [[Int]] -> Int -> [Int] -> [Int] -> String -> IO String
test act ind prevC prevJ str    | ind == (length act) || str == "IMPOSSIBLE" = do
                                                                return str
                                | otherwise = do
                                            delegate act ind prevC prevJ (act !! ind) str

-- This fucntion receives as input every activity to then save it in a list and return it.
get_act :: [[Int]] -> Int -> IO [[Int]]
get_act act n   | n == 0 = do
                            return act
                | otherwise = do
                                inp <- get_res
                                let ls = words inp
                                    s = read (ls !! 0) :: Int
                                    e = read (ls !! 1) :: Int
                                get_act (act ++ [[s,e]]) (n-1)

-- This functions organizes the list of activites in ascending order by the starting time of each activity.
sorter :: [[Int]] -> [[Int]]
sorter act = sortBy (\e1 e2 -> compare (e1 !! 0) (e2 !! 0)) act

-- This function iterates to solve each case that is sent.
iterator :: Int -> Int -> String -> IO ()
iterator t ind res  | ind > t = put_res res
                    | otherwise = do
                        -- we set the initial solution as an empty string.
                        let str = ""
                            -- We set the previous activity for both parents, C and J, as (0,0).
                            prevC = [0,0]
                            prevJ = [0,0]
                        -- We now receive how many activities we will be given as input.
                        n <- getLine
                        -- We then get all of those activities and save them on a list.
                        inputs <- get_act [] (read n :: Int)
                        -- We send those activities to see how to delegate them, or to see if it is impossible.
                        ls <- test (sorter inputs) 0 prevC prevJ str
                        -- Then we call the iterator to check the next case, or to send the final answer for all cases.
                        iterator t (ind+1) (res ++ str_gen ls ind t)

-- First, we read the number of test cases (T).
main :: IO ()
main = do
    t <- readLn
    iterator t 1 ""

