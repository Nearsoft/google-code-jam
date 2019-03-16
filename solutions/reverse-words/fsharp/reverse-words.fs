open System
open System.IO

let lines = File.ReadAllText("B-large-practice.in").Split [|'\n'|]

for i = 1 to lines.Length - 2 do
  let rev = Array.rev (lines.[i].Split [|' '|])
  let words = String.concat " " rev
  printfn "Case #%d: %s"  i words