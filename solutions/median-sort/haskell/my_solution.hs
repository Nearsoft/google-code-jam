import Control.Monad (foldM, replicateM)
import System.IO (hFlush, stdout)
import Data.Tuple (swap)
import Data.List.Split ( splitOn )
import System.Exit ( die )

-- Converts list with strings to list with numbers
parseToNumbers :: [String] -> [Int]
parseToNumbers = map read

checkError :: Int -> IO Int
checkError result = if result == -1 then System.Exit.die "-1" else return result

-- Print values and get the median of them
getMedian :: Show a => (a, a, a) -> IO Int
getMedian (a,b,c) = do
  let 
    raw_query = map show [a,b,c]
    query = unwords raw_query
  putStrLn query
  hFlush stdout
  answer <- readLn
  checkError answer

-- Sort the left part of the (a,b,c)
sortLeft :: Int -> Int -> Int -> IO (Int, Int)
sortLeft left a b = 
  median <$> getMedian (left, a, b) where
  median c | c == a = (a,b) 
           | c == b = (b,a)

-- Sort the right part of the (a,b,c)
sortRight :: Int -> Int -> Int -> IO (Int, Int)
sortRight right a b =
  median <$> getMedian (right, a, b) where
  median c | c == a = (b,a)
           | c == b = (a,b)

-- Order the values according to their position of the (left, center, right) recursively
order :: [Int] -> (Int, Int) -> IO [Int]
order [] (a, b) = return [a, b] -- Returns until there is no more values to check
order result (a, b) = 
  do
    (left, center, right) <- getPositionMedian (a, b) result -- Gets the position, we define a < b
    
    -- Orders until leftest value
    most_left <- case left 
      of
        (new_a:new_b:most_most_left) -> sortRight a new_a new_b >>= order most_most_left
        most_most_left -> return most_most_left
    
    -- Orders until centerest value
    most_center <- case center of
            (new_a:new_b:most_most_center) -> sortLeft a new_a new_b >>= order most_most_center
            most_most_center -> return most_most_center
    
    -- Orders until rightest value
    most_right <- case right of
            (new_a:new_b:most_most_right) -> sortLeft b new_a new_b >>= order most_most_right
            most_most_right -> return most_most_right
    
    -- Until it finishes returns the ordered result
    let result = most_left ++ a : most_center ++ b : most_right
    
    return result

-- Gets the position of the median (a,b,c) where c is the number we checked
getPositionMedian :: Foldable t => (Int, Int) -> t Int -> IO ([Int], [Int], [Int])
getPositionMedian (a, b) = foldM compareMedianResult ([], [], []) 
  where
    -- Gets what is the median given by the judge
    compareMedianResult (new_a, new_b, new_c) number = 
      median <$> getMedian (a, b, number) 
      where
        median m | m == a = (number:new_a, new_b, new_c)
                 | m == b = (new_a, new_b, number:new_c)
                 | m == number = (new_a, number:new_b, new_c)

-- Loops until all test cases are done
loopTestCase :: Int -> IO ()
loopTestCase index =
  -- We start by 3 because if there is only 2 numbers, the list is already sorted
  order [3..index] (1,2) >>=
  putStrLn  . unwords . map show >>
  hFlush stdout >>
  getLine >>
  return ()

main :: IO [()]
main = do
  -- Gets the judge initial input
  line1 <- getLine
  let 
    -- Saves the values in a int list
    values = splitOn " " line1
    int_values = parseToNumbers values
    -- Assign the values in a variable
    t = head int_values
    n = int_values !! 1
    q = int_values !! 2
  -- Starts the sorted according to the test cases given
  replicateM t (loopTestCase n)