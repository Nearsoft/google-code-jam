import Data.Array
import Data.Ix ()

data Node = Node {
    fun :: Int,
    children :: [Int]
} deriving Show

main :: IO ()
main = do
    line <- getLine
    cases 1 (read line::Int)
cases :: Int -> Int -> IO ()
cases c l = do
    if c <= l
        then do
            line1 <- getLine
            line2 <- getLine
            line3 <- getLine
            let
                limit = read line1::Int
                funs = listArray (0,limit-1) (map (read::String->Int) (words line2))
                parents = listArray (0,limit-1) (map (read::String->Int) (words line3))
                nodes = addNode 0 limit funs parents [Node 0 []]
                (max,total) = dfs 0 nodes 0
            putStrLn ("Case #"++ show c ++ ": " ++ show (total+max))
            cases (c+1) l

    else
        pure ()


addNode :: Int -> Int -> Array Int Int -> Array Int Int -> [Node] -> [Node]
addNode n limit funs parents nodes = do
    if n < limit then do
        let (a,b) = splitAt (parents!n) nodes
        let newParent = addChild (nodes!!(parents ! n)) (n+1)
        addNode (n+1) limit funs parents (a ++ [newParent] ++ tail b ++ [Node (funs ! n) []])
    else
        nodes
addChild :: Node -> Int -> Node
addChild parent id = parent {children = children parent ++ [id]}

dfs :: Int -> [Node] -> Int -> (Int,Int)
dfs id nodes total  = do
    if null (children (nodes !! id))
        then (fun (nodes !! id),total)
    else do
        let (values,s) = countFun (children (nodes !! id)) nodes [] total
        let min = minimum values
        (maximum [fun (nodes !! id),min],s+(total + sum values) - min)

countFun :: [Int] -> [Node] -> [Int] -> Int -> ([Int],Int)
countFun [] nodes values sum = (values,sum)
countFun (child:children) nodes values sum = do
    let (max,total) = dfs child nodes 0
    countFun children nodes (values ++ [max]) (sum+total)

