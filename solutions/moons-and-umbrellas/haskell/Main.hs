--Moons and umbrellas solution

-- Explanation: For test case 1 and 2 we can window through each element
-- checking the actual and the previous character. We can be greedy
-- and asume that the best case is that '?' stays as the last one
-- This solution works! The greedy solution works for test case 1 and 2

-- Problems that I encountered using Haskell.
-- Can't iterate so it has to be a recursive approach
-- Can't send a lot of parameters to functions.
-- Variables are inmutable outside the actual block of code.
-- Variables are inmutable!!
-- If I need a result I have to send data to methods
-- Data can only be modified through methods, if it's a String or list you need recursion.
-- Can't just take the input normally...

-- Solution for a recursive approach
-- Recursion over the string saving the actual and prev
-- Two whole separated recursive methods counting the CJ and JC occurrences
-- print numCJ*X + numJC*Y

-- Method to read a String and cast to IO Int
getInt = read `fmap` getLine :: IO Int

-- Can't use a simple for, but my needs are only a simple for
-- Creation of a list and iterate in it
for list action = mapM_ action list



--Method that counts the occurrences of a substring within a string
count :: Eq a => [a] -> [a] -> Int
--Error prints
count []  = error "empty substring"
count sub = go
  where
    go = scan sub . dropWhile (/= head sub)
    scan _ [] = 0
    scan [] xs = 1 + go xs
    scan (x:xs) (y:ys) | x == y    = scan xs ys
                       | otherwise = go ys



main = do
    --Get Integer
    n <- getInt
    --Iterate on the number of cases
    for [0..(n-1)] (\ i -> do
            --Get the whole line of the input
            inp <- getLine
            --Turn it to a list
            lst <- return (words inp)
            --Head of the list! the first element
            x <- return (read (head lst) :: Int)
            --Head of the tail is the middle element
            y <- return (read (head (tail lst)) :: Int)
            --Head of the tail of the tail is the last element
            mural <- return (head (tail (tail lst)))
            --Clean the String because recursion might fail
            let muralString=" " ++mural++" "
            --Super greedy mode, remove ?
            --Funtion that filters a string, recursion
            let muralStringLimpia = filter (not . (`elem` "?")) muralString
            --Get the count of CJ sometimes fails
            let x1 = count "CJ" muralStringLimpia
            --Get the count of JC sometimes fails
            let x2 = count "JC" muralStringLimpia
            --Print the result
            putStrLn $ "Case #" ++ show (i+1) ++ ": " ++ show ((x1*x) + (x2*y))



        )
