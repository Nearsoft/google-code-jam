declare var require: any;
declare var process: any;

const readline = require('readline');
let rl = readline.createInterface(process.stdin, process.stdout);
// Defines an interface for the data needed for each test case
interface TestData {
    testNumber: number;
    S: string;
}

// Parses each line of input and split into the data needed for each case
function parseInput(input: string[]) {
    let line: number = 0;
    
    // Reads the number of test cases from the first line of input
    const numCases = Number(input[line++]);

    const testData: TestData[] = [];
    //Loop that determines the time of repetitions in the cicle
    for (let testNumber = 1; testNumber <= numCases; ++testNumber) {
        // Each line is one of the cases, every case its a different sting
        const S = input[line++];
        
        testData.push({testNumber, S});
    }

    return testData;
}
function double_or_one_thing(data: TestData) {
    let S = data.S.split('')
    let cnt = 1;
    let result: string[] = [];
    //Take a character (letter) and advance through the line, character by character (one by one)
    for (let i = 0; i < S.length; i++) {
      //if the character on the right its different to the lenght of the string then keep on
      if (i + 1 !== S.length) {
        //Here starts comparing the letters, basically if the value it's equal it mantains one letter, 
        // but if the letter on the left it's smaller it doubles it through the loop.
        if (S[i] === S[i + 1]) {
          cnt += 1;
        } else {
          if (S[i] < S[i + 1]) {
            for (let j = 0; j < cnt; j++) {
              result.push(S[i])
            }
          }
  
          cnt = 1;
        }
      }
      result.push(S[i]);
    }

    let answer: string = result.join("");
  
    console.log(`Case #${data.testNumber}: ${answer}`);
}
  
function runAllTests(input: string[]) {
  const testCases = parseInput(input);
  testCases.forEach(double_or_one_thing);
}
    
// Reads input from the console and run all the cases
const lines : string[] = [];
rl.on('line', line => lines.push(line)).on('close', () => runAllTests(lines));
  