open System
open System.IO

//Get a string and return an Integer array
let parseArray (text :string) n=
    let array = text.Split [|' '|]
    let mutable p = [|1..n|]
    for i = 0 to n-1 do
        p.[i] <- Int32.Parse(array.[i])
    p

//Get indexes the combination that matches to maxValue
let getMaxComb (array :int[]) maxValue =
    let mutable index1 = 0 
    let mutable index2 = 0
    let mutable curMax = 0
    for i = 0 to array.Length-1 do
        for j = 0 to array.Length-1 do
            if i <> j then
                let sum = array.[i] + array.[j]
                if sum <= maxValue && sum > curMax then
                    index1 <- i + 1
                    index2 <- j + 1
                    curMax <- sum
    //return a two-elements array with the indexes sorted
    Array.sort [|index1; index2|]            
         
[<EntryPoint>]
let main args =
    //Get file
    let mutable file = "C-small-practice.in"
    if(args.Length = 1) then 
        file <- args.[0]
    //Get lines into an array
    let lines = File.ReadAllText(file).Split [|'\n'|]
    //Get params
    let N = Int32.Parse(lines.[0])
    //Iterate over test cases
    for i = 0 to N-1 do
        let C = Int32.Parse(lines.[i*3+1])
        let I = Int32.Parse(lines.[i*3+2])
        let P = parseArray lines.[i*3+3] I
        let indexes = getMaxComb P C
        printfn "Case #%d: %d %d" (i+1) indexes.[0] indexes.[1]
    0