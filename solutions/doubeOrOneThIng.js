let readline = require('readline');
let data = [ ];
let rl = readline.createInterface(
     process.stdin, process.stdout);
let strings;

let testData = {
  T: 0,
  S: [ ]
};


//we pass our data here
function parse(data){
 testData.T = data[0];
 strings =  testData.S;
 for (let i = 1; i <= testData.T; i++){
  strings.push(data[i])
 }
 
}
//for each data set we run solve()
//we run solve algorithm and get word after it was higlighted
function solve(input){
 // console.log("hello " + input)
 
 let number = testData.T;
 let strings = testData.S;
 let counter = 0;
 
 strings.forEach((el)=>{
    let result = [ ];
    let equal = 0;
    
    for(let i=0; i<el.length; i++){
        result.push(el[i]);   
        if(el[i]<el[i+1]){
        result.push(el[i]);
        }
        if(el[i] == el[i+1])
        //result.push(el[i]);
          //if(el[i]<el[i+2]){
          result.push(el[i]);
          //}
    };
    counter++;
    
    //return result.join("");
    let answer = result.split
    console.log(`Case #${counter}: ${result.join('')}`);
    

 })
 
 
}

function testCases(data){
  parse(data)
  solve()
  
};

//we have to pass in parsed data to solve
/*
  const testCases = parseInput(input);
  testCases.forEach(double_or_one_thing);
*/


rl.on('line', input => data.push(input)).on('close', () => testCases(data));