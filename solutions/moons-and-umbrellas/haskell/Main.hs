--Moons and umbrellas solution
-- Explanation: In order not to generate an expense, we should not change the
-- character, it is convenient for us that it be the same as the previous one.
-- Since we don't need to move the character, it should stay the same as the previous one.
-- We can remove all the question marks, this makes the character equal to the previous one.
-- And in the end we only count the times that the character change occurred
-- Multiply them by the value



import Data.List

-- Method to read a String and cast to IO Int
getInt = read `fmap` getLine :: IO Int


-- Can't use a simple for, but my needs are only a simple for
-- Creation of a list and iterate in it
for list action = mapM_ action list

main = do
    -- Get integer
    n <- getInt
    --Iterate on the number of cases
    for [0..(n-1)] (\ i -> do
            --Get the whole line of the input
            input <- getLine

            --Turn it to a list
            list <- return (words input)

            --Head of the list! the first element
            costX <- return (read (head list) :: Int)

            --Head of the tail is the middle element
            costY <- return (read (head (tail list)) :: Int)

            --Head of the tail of the tail is the last element
            mural <- return (head (tail (tail list)))

            -- Remove question mark
            let muralClean = filter (not . (`elem` "?")) mural

            -- Count JC in the string
            let qtyJC = length . filter (isPrefixOf "JC") . tails $ muralClean

            -- Count CJ in the string
            let qtyCJ = length . filter (isPrefixOf "CJ") . tails $ muralClean

            --Print the answer
            putStrLn $ "Case #" ++ show (i+1) ++ ": " ++ show ((qtyCJ*costX) + (qtyJC*costY))
        )
