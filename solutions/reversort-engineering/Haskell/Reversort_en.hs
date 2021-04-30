import Data.List
l=[1,2,3,4,5]
n=5 --list lenght
c=3 --maximum cost
main = do
    if (n-1) > c || c > (n*(n+1)/2)-1
        print "impossible"
arrayL :: [Int] -> Int -> Int -> Int
arrayL list cost i = if i >= n
   --ruby logic
    arrOfRange = (1..n).to_a
    for i in (0..n-2).to_a.reverse()
        m = [c - i, n - i].min
        c -= m
        reversed(arrOfRange, i, i + m - 1)
    end