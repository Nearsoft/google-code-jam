// declare require and process to allow use of readline
declare const require: any;
declare const process: any;

// import readline library and create interface for reading input
const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

// define function to calculate maximum straight length given an array of numbers and its length
function maximumStraightLength(S: number[], n: number): number {
  
  // Sort numerically because default is lexicographical sort:
  S.sort((a,b)=>a-b);
  
  let length = 0;
  
  // iterate through array to determine maximum straight length
  for (let si=0; si<n; si++) {
    if (S[si] > length){
      length+=1;
    }
  }
  return length;
}

// define variable for number of test cases
let t: number;

// read number of test cases from input and call runTests function
rl.question("", (numTests:string) => {
  t = parseInt(numTests);
  runTests();
});

// define function to process test cases
function runTests() {
  let testCount = 0;
  let n = 0;
  
  // listen for input from readline interface
  rl.on("line", (line:string) => {
    
    // check if all test cases have been processed
    if (testCount === t * 2) {
      rl.close();
      return;
    }
    
    // if even, input is number of dice
    if (testCount % 2 === 0) {
      n = parseInt(line);
      testCount++;
      
    // if odd, input is values of dice
    } else {
      const dice = line.split(" ").map(Number);
      const y = maximumStraightLength(dice,n);
      const x = Math.floor(testCount / 2) + 1;

      console.log(`Case #${x}: ${y}`);

      testCount++;
    }
  });
}
