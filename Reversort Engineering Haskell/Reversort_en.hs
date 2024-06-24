module Main(main) where

import Control.Monad (forM_,guard,mzero)

default (Int)

main :: IO ()
main = do
  t <- readLn
  forM_ [1..t] $ \i -> do
    [n,c] <- map read . words <$> getLine
    putStrLn $ "Case #" ++ show i ++ ": " ++
      maybe "IMPOSSIBLE" (unwords . map show) (reversing n c)

reversing :: Int -> Int -> Maybe [Int]
reversing n c = go (n - 1) [n] (c - n + 1) where
  go _ _ b | b < 0 = mzero
  go 0 l b = guard (b == 0) *> pure l
  go i l b =
    let p = min (n - i) b
        (before,after) = splitAt p l
    in go (i - 1) (reverse (i : before) ++ after) (b - p)