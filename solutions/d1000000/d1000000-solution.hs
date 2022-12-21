import Data.List ()
main :: IO()
main = do
    line <- getLine 
    game 0 (read line::Int)
    
game :: Int -> Int -> IO()
game c l = do{
    if c < l
        then do
            miss <- getLine
            line <- getLine
            let n = tqsort (map (read::String->Int) (words line))
            putStrLn ("Case #"++show (c+1) ++": "++ show (straight n 0))
            game (c+1) l
    else pure ()
}
straight:: [Int] -> Int -> Int
straight d c = do
    if null d 
        then c
    else if head d > c
        then 
            straight (tail d) (c+1)
        else
            straight (tail d) c

tqsort :: Ord a => [a] -> [a]
tqsort [] = []
tqsort (x:xs) = sortp xs [] [x] [] 
  where
    sortp [] us ws vs     = tqsort us ++ ws ++ tqsort vs
    sortp (y:ys) us ws vs =
      case compare y x of 
        LT -> sortp ys (y:us) ws vs 
        GT -> sortp ys us ws (y:vs)
        _  -> sortp ys us (y:ws) vs