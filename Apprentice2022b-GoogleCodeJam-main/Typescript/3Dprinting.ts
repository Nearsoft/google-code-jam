declare var require: any;
declare var process: any;
const readline = require('readline');
let rl = readline.createInterface(process.stdin, process.stdout);
// Defines the data needed for each test case
interface TestData {
    testNumber: number;
    printer_a: number[];
    printer_b: number[];
    printer_c: number[];
}

// Parses each line of input and split into the data needed for each case
function parseInput(input: string[]) {
    let line: number = 0;
    
    // Reads the number of test cases from the first line of input
    const numCases = Number(input[line++]);

    const testData: TestData[] = [];
    for (let testNumber = 1; testNumber <= numCases; ++testNumber) {
        // The next lines contains the ink available with each printer, this lines ask to separate each number for a clasification on the next steps
        const printer_a = input[line++].split(' ').map((str)=>Number(str));
        const printer_b = input[line++].split(' ').map((str)=>Number(str));
        const printer_c = input[line++].split(' ').map((str)=>Number(str));

        testData.push({testNumber, printer_a, printer_b, printer_c});
    }
    return testData;
}

function runTestCase(data: TestData) {
//Assign a name for the arrays to the different colors on each printer, they're constructed with a given position of the input data
    var black, cyan, magenta, min_black, min_cyan, min_magenta, min_yellow, result, total, yellow;
    cyan = [data.printer_a[0], data.printer_b[0], data.printer_c[0]];
    magenta = [data.printer_a[1], data.printer_b[1], data.printer_c[1]];
    yellow = [data.printer_a[2],data.printer_b[2], data.printer_c[2]];
    black = [data.printer_a[3], data.printer_b[3],data.printer_c[3]];
    //Calculate the minimum ink in the array of each color
    min_cyan = Math.min(...cyan);
    min_magenta = Math.min(...magenta);
    min_yellow = Math.min(...yellow);
    min_black = Math.min(...black);
    total = 1000000;
    result = [];

  
    //the statement compares the minimum ink with the condition given on the problem. (it has to be >=10e6)
    if (min_cyan >= total) {
      result.push(total.toString());
      total = 0;
    } else {
      result.push(min_cyan.toString());
      total -= min_cyan;
    }
  
    if (min_magenta >= total) {
      result.push(total.toString());
      total = 0;
    } else {
      result.push(min_magenta.toString());
      total -= min_magenta;
    }
  
    if (min_yellow >= total) {
      result.push(total.toString());
      total = 0;
    } else {
      result.push(min_yellow.toString());
      total -= min_yellow;
    }
  
    if (min_black >= total) {
      result.push(total.toString());
      total = 0;
    } else {
      result.push(min_black.toString());
      total -= min_black;
    }
  
    if (total !== 0) {
      result = "IMPOSSIBLE";
    } else {
      result = result.join(' ');
    }
  
    console.log(`Case #${data.testNumber}: ${result}`);
}

    function runAllTests(input: string[]) {
        const testCases = parseInput(input);
        testCases.forEach(runTestCase);
    }
    // Reads input from the console and run all the cases
    const lines : string[] = [];
    rl.on('line', line => lines.push(line)).on('close', () => runAllTests(lines));