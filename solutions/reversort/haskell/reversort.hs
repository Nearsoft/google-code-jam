import Data.List
import Data.Maybe
import Data.Char
import Control.DeepSeq

main = do
        line <- getLine
        let numTestCases = head (map read $ words line :: [Int])
            in
                cycleTestCases numTestCases 1

cycleTestCases :: Int -> Int -> IO ()
cycleTestCases numTestCases count = if count < numTestCases
                                        then do
                                                line <- getLine
                                                line `seq` return 0
                                                listString <- getLine
                                                let list = map read $ words listString :: [Int]
                                                    in do
                                                            putStrLn $ "Case #" ++ show count ++ ": " ++ show (walkArray list 0 0)
                                                            cycleTestCases numTestCases (count + 1)
                                    else do
                                            line <- getLine
                                            line `seq` return 0
                                            listString <- getLine
                                            let list = map read $ words listString :: [Int]
                                                in do
                                                    putStrLn $ "Case #" ++ show count ++ ": " ++ show (walkArray list 0 0)

minElemIndex :: [Int] -> Int
minElemIndex list = fromJust $ elemIndex (minimum list) list

walkArray :: [Int] -> Int -> Int -> Int
walkArray list cost i = if i >= length list
                            then do
                                    cost - 1
                        else let minValIndex = i + minElemIndex (drop i list)
                                 subList = reverse $ take ((minValIndex - i) + 1) $ drop i list
                                 firstPartList = take i list
                                 secondStep = subList ++ drop (length firstPartList + length subList) list
                                 ordList = firstPartList ++ secondStep
                                 in do
                                        walkArray ordList (cost + length subList) (i + 1)


                        
