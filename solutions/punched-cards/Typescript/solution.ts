declare var require: any;
declare var process: any;


const readline = require('readline');
let rl = readline.createInterface(process.stdin, process.stdout);

// Defines an interface for the data needed for each test case
interface TestData {
    testNumber: number;
    rows: number;
    cols: number;
}

// Parses each line of input and split into the data needed for each case
function parseInput(input: string[]) {
    let line: number = 0;
    
    // Reads the number of test cases from the first line of input
    const numCases = Number(input[line++]);

    const testData: TestData[] = [];

    for (let testNumber = 1; testNumber <= numCases; ++testNumber) {
        // The first number of each line contains the number of rows of each case
        // and the second number contains the number of columns.
        const rows = Number(input[line].split(' ')[0]);
        const cols = Number(input[line++].split(' ')[1]);

        testData.push({testNumber, rows, cols});
    }

    return testData;
}

function runTestCase(data: TestData) {
    // Prints the case number into the console
    console.log(`Case #${data.testNumber}:`);

    let card: string = '';
    card = card.concat('..');

    for (let i = 0; i < data.cols - 1; i++) {
        card = card.concat('+-');
    }
    card = card.concat('+\n..');
    for (let i = 0; i < data.cols - 1; i++) {
        card = card.concat("|.");
    }
    card = card.concat('|');

    // Prints the first row into the console
    console.log(card);

    let plus: string = '';
    let bars: string = '';

    // Creates the string for the '+' and '-' symbols
    for (let i = 0; i < data.cols; i++) {
        plus = plus.concat('+-');
    }
    plus = plus.concat('+');

    // Creates the string for the '|' and '.' symbols 
    for (let i = 0; i < data.cols; i++) {
        bars = bars.concat('|.');
    }
    bars = bars.concat('|');

    // Using the strings that we defined earlier we can create the rest
    // of the punch card.
    for(let i = 0; i < data.rows - 1; i++) {
        console.log(plus)
        if (i === data.rows) continue;
        else console.log(bars);
    }
    console.log(plus);
}

function runAllTests(input: string[]) {
    const testCases = parseInput(input);

    testCases.forEach(runTestCase);
}

// Reads input from the console and run all the cases
const lines : string[] = [];
rl.on('line', line => lines.push(line)).on('close', () => runAllTests(lines));