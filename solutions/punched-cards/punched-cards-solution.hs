import Data.List ()
main :: IO()
main = do
    line <- getLine 
    card 1 (read line::Int)
    
card :: Int -> Int -> IO()
card c l = do{
    if c <= l
        then do
            line <- getLine
            let n = map (read::String->Int) (words line)
            putStrLn ("Case #"++show c ++": ")
            punch (head n) (last n) 0
            card (c+1) l
    else pure ()
}
punch :: Int -> Int -> Int -> IO()
punch r c n = do
    if r > n
        then do
            if n == 0 then do
                putStrLn (".."++getEdge (c-1))
                putStrLn (".."++getRow (c-1))
            else do
                putStrLn (getEdge c)
                putStrLn (getRow c)
            punch r c (n+1)
    else
        putStrLn (getEdge c)

getEdge :: Int -> String
getEdge n = do
    if n > 0
        then do
            "+-" ++ getEdge (n-1)
    else
        "+"

getRow :: Int -> String
getRow n = do
    if n > 0
        then do
            "|." ++ getRow (n-1)
    else
        "|"