import Data.List
main :: IO()
main = do
    nCases <- getLine
    testCase 1 (read nCases::Int)

testCase :: Int -> Int -> IO()
testCase currentCase totalCases = do {
    if currentCase <= totalCases
        then do
            getPrinterData 1 [1000000,1000000,1000000,1000000] currentCase
            testCase (currentCase+1) totalCases
    else pure()
}

getPrinterData :: Int -> [Integer] -> Int -> IO()
getPrinterData currentPrinter minInk currentCase = do {
    if currentPrinter <= 3
        then do
            ink <- getLine
            let newMinInk = storeMinInk ink minInk
            getPrinterData (currentPrinter+1) newMinInk currentCase
    else
        putStrLn ("Case #" ++ show currentCase ++ ": " ++ checkIfSolvable minInk)
}

storeMinInk :: String -> [Integer] -> [Integer]
storeMinInk ink minInk = do
    let currentInk = map (read::String->Integer) (words ink)
    zipWith (\ new prev -> (if new < prev then new else prev)) currentInk minInk

checkIfSolvable :: [Integer] -> String
checkIfSolvable ink
    | sum ink < 1000000 = "IMPOSSIBLE"
    | sum ink == 1000000 = unwords (map show ink)
    | otherwise = unwords (map show (substractExcess ink))

substractExcess :: [Integer] -> [Integer]
substractExcess list
    | sum list == 1000000 = list
    | sum (tail list) > 1000000 = 0 : substractExcess (tail list)
    | otherwise = (1000000 - sum (tail list)) : tail list