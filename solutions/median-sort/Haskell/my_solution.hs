import System.IO ( hFlush, stdout )
import System.Exit ( die )
import Data.List.Split ( splitOn )
import Data.List ( intercalate, intersperse )

-- query :: (Int, Int, Int) -> IO Int
-- query (i, j, k) = putStrLn (unwords $ map show [i, j, k]) >> hFlush stdout >> readLn

-- Check if there is an error
checkError :: Int -> IO Int
checkError result = if result == -1 then System.Exit.die "-1" else return result

-- Converts list with numbers to list with strings
parseToPrintable :: [Int] -> [String]
parseToPrintable = map show

-- Converts list with strings to list with numbers
parseToNumbers :: [String] -> [Int]
parseToNumbers = map read

-- Gets the medium value from the judge
ask :: [Int] -> IO Int
ask query = do
  let raw_query = parseToPrintable query
  let query = unwords raw_query
  putStrLn query
  hFlush stdout
  raw_answer <- getLine
  let values = splitOn " " raw_answer
  let int_values = map read values :: [Int]
  let answer = head int_values
  checkError answer
  return answer

-- Check Answer
checkAnswer :: [Int] -> IO Int
checkAnswer result = do
  let raw_result = parseToPrintable result
  let result = unwords raw_result
  putStrLn result
  hFlush stdout
  raw_answer <- getLine
  let values = splitOn " " raw_answer
  let int_values = map read values :: [Int]
  let answer = head int_values
  checkError answer

-- Insert a value in the list
insertAt :: a -> Int -> [a] -> [a]
insertAt newElement 0 as = newElement:as
insertAt newElement i (a:as) = a : insertAt newElement (i - 1) as

-- Median sort
medianSort i_list = do
  -- Variables
  let result = [1,2]
  let i_size = length i_list
  let index_for = i_size - 2
  let for_index_right = i_size + 1
  let range = [3..for_index_right]
  -- let answer = map 
  print i_size

  -- Process

-- For Loop
-- loopNumbers [0, result] = checkAnswer result
-- loopNumbers [n, result] = do
--   let left = 0
--   let right = length result
--   loopNumbers [n-1, result]

-- While loop
-- loopWhile [0, right, left, [result], number] = insertAt left number
-- loopWhile [condition, right, left, [result], number] = do
--   let first_part = left + (right - left)
--   let second_part = right + (right - left)
--   let a = div first_part 3
--   let b = div second_part 3
--   let condition = right - left
--   let question = [a]
--   let median = ask 
--   loopWhile [condition, right, left, [result], number]

-- # In the case that the median is a, (a,b,c)
--             if median == result[a]:
--                 right = a - 1
--                 if left == right:  
--                     right += 1

--             # In the case that the median is b, (a,b,c)
--             elif median == result[b]:
--                 left = b + 1
--                 if left == right:
--                     left -= 1

--             # In the case that the median is c, (a,b,c)
--             elif median == number:
--                 left = (a + 1)
--                 right = (b - 1)
--                 if left == right:
--                     left -= 1

  -- if median == result !! a
  --   then right = a - 1
  --   if left == right
  --     then right += 1

  -- if median == result !! b
  --   then left = b + 1
  --   if left == right
  --     then left -= 1

  -- if median == number
  --   then left = a + 1
  --   right = b - 1
  --   if left == right
  --     left -= 1
  
  -- return(median, a, b, left, right, result)



-- Loops until all test cases are done
loopTestCase [0, i_list] = System.Exit.die "1"
loopTestCase [n, i_list] =
  do
    let initial_list = [1..i_list]
    ask initial_list
    loopTestCase [n - 1, i_list]

-- Main function
main = do
        -- Gets the judge input
        line1 <- getLine
        -- Saves the values in a int list
        let values = splitOn " " line1
        let int_values = parseToNumbers values
        -- Assign the values in a variable
        let t = head int_values
        let n = int_values !! 1
        let q = int_values !! 2
        do
          loopTestCase [t, n]
          --checkError x