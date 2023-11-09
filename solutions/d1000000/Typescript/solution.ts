declare var require: any;
declare var process: any;


const readline = require('readline');
let rl = readline.createInterface(process.stdin, process.stdout);

// Defines an interface for the data needed for each test case
interface TestData {
    testNumber: number;
    dices: number;
    diceSides: number[];
}

/*THIRD
    Funtion processes every dataLine to separate from strings to specific datatype: 
        testNumber: number;
        dices: number;
        diceSides: number[];
*/
function parseInput(input: string[]) {
    //Variable to control de line's number
    let line: number = 0;
    
    // Reads the number of test cases from the first line of input
    const numCases = Number(input[line++]);

    // Array to save the separate data at the end
    const testData: TestData[] = [];

    //Loop to separate the data for every case the user gave. 
    for (let testNumber = 1; testNumber <= numCases; ++testNumber) {

        // Dices Object contains the number of dices for each case
        const dices = Number(input[line++]);

        // and the second object contains the arrays of sides for each dice.
        const diceSides = input[line++].split(' ').map((srt)=>Number(srt));

        //Save the data organized for each case
        testData.push({testNumber, dices, diceSides});
    }

    return testData;
}

/* FOURTH: 
    Function sends the result of each case
    The received data is the information from one case. 
    Loop in the data to find the maxStraight
    Print the result of each case
*/
function runTestCase(data: TestData) {

    //Sort the Array with dice Slices
    data.diceSides.sort((n1, n2)=> n1- n2);

    //We only need two parameters to know if we can keep a straight
    let minSides:number = data.diceSides[0];
    let maxStraight: number = 0;

    //For loop to calculate maxStraight
    for(var index = 0; index < data.dices ; index++ ){

        if((data.diceSides[index] >= minSides) && (data.diceSides[index] > maxStraight)){
            maxStraight += 1;
            
        }else{
            continue
        }
    }

    //Prints the case number and result into Console.
    console.log(`Case #${data.testNumber}: ${maxStraight}`);

}

/* SECOND: 
    Function sends the inputs to Function parseInput()
    The received data are the cases we have
    Loop in the data to run every case by Function runTestCase()
*/
function runAllTests(input: string[]) {
    const testCases = parseInput(input);

    testCases.forEach(runTestCase);
}

//FIRST
// The program starts here
// Reads input from the console and run all the cases
const lines : string[] = [];
rl.on('line', line => lines.push(line)).on('close', () => runAllTests(lines));