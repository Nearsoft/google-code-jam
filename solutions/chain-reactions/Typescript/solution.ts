declare var require: any;
declare var process: any;


const readline = require('readline');
let rl = readline.createInterface(process.stdin, process.stdout);

// Defines an interface for the data needed for each test case
interface TestData {
    testNumber: number;
    numberOfElements: number;
    funN: number[];
    pointerN: number[];
}

// Parses each line of input and split into the data needed for each case
function parseInput(input: string[]) {

    let line: number = 0;
    
    // Reads the number of test cases from the first line of input
    const numCases = Number(input[line++]);

    const testData: TestData[] = [];

    for (let testNumber = 1; testNumber <= numCases; ++testNumber) {
        //User Inputs
        const numberOfElements = Number(input[line++]);
        var funN = input[line++].split(' ').map((str)=>Number(str));
        var pointerN = input[line++].split(' ').map((str)=>Number(str));

        //add 0 at begining of arrays
        funN.unshift(0);
        pointerN.unshift(0);
        //Inserts case data into Interface object 
        testData.push({testNumber, numberOfElements, funN, pointerN});
    }
    //returns the interface object
    return testData;
}

function runTestCase(data: TestData) {
    //Creates a temporal array to store potential candidates
    let temp: number[] = new Array(data.numberOfElements+1);
    
    //fill array with a large number to avoid compile error
    //temp.fill(999999999999);
    for(var i = 0; i<=data.numberOfElements;i++){
        temp[i] = 999999999999;
    }
    //sum fun of all nodes
    let total : number = data.funN.reduce((a, b) => a + b, 0);

    for (var i = data.numberOfElements; i >= 0; i--) {
        //If temp is LargeNumber (it has not been evaluated)
        //change value to 0
        if(temp[i]==999999999999){
            temp[i] = 0;
        }
        //Substract from total the descarted nodes (the one with less Fun value)
        total -= Math.min(data.funN[i], temp[i]);
        //get the node with more Fun value of the ones beings evaluated 
        let funMax = Math.max(data.funN[i], temp[i]);
        //Store funMax in Temp position to be evaluated later against the node that Fun(i) is pointing 
        temp[data.pointerN[i]] = Math.min(temp[data.pointerN[i]],funMax);
    }
    //print the result 
    console.log(`Case #${data.testNumber}: ${total}`);
}

function runAllTests(input: string[]) {
    const testCases = parseInput(input);
    testCases.forEach(runTestCase);
}
// Reads input from the console and run all the cases
const lines : string[] = [];
rl.on('line', (input:string) => lines.push(input)).on('close', () => runAllTests(lines));