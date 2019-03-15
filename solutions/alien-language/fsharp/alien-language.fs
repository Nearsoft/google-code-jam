open System
open System.IO
open System.Text.RegularExpressions

let filename = "../tests/A-small-practice.in"
let mutable path = Environment.CurrentDirectory+"/"+filename

//Split a line and return a list with all of them
let getLDN (line: string) =
  line.Split [|' '|]

//Return all the tokens given a list
let getTokens (lines: string list) =
  let first = getLDN lines.Head
  let tail = lines.Tail
  let tokens = tail |> List.toArray
  let d = Int32.Parse(first.[1]) - 1
  tokens.[0..d]

//Return all the cases from a given list
let getCases (lines: string list) =
  let first = getLDN lines.Head
  let tail = lines.Tail
  let cases = tail |> List.toArray
  let d = Int32.Parse(first.[1])
  cases.[d..cases.Length-2]

//Return a regular expression from a given string
let getRegexp(case: string) =
  let regexp = case.Replace("(","[")
  regexp.Replace(")","]")

let matchr (regexp: string, token: string) =
  let m = Regex.Match(token, regexp) in
  if m.Success then true else false

//Compare a test case with all the tokens and return the number of matches
let testCase(tokens: string array, case: string) =
  let regexp = getRegexp case
  tokens |> (Array.fold(fun acc token ->
    if matchr(regexp, token) then
      acc + 1
    else acc
  ) 0)

//Process content
let processFile(content: string list) =
  let ldn = getLDN content.[0]
  let tokens = getTokens content
  let cases = getCases content
  for i = 0 to cases.Length-1 do
    let mutable count = testCase(tokens,cases.[i])
    printfn "Case #%i: %i" (i+1) count

//Get lines into an array
let content = File.ReadAllText(path).Split [|'\n'|] |> Array.toList

//Process the file
processFile(content)
