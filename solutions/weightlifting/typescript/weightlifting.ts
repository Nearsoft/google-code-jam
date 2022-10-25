declare var require: any;
declare var process: any;


const readline = require('readline');
let rl = readline.createInterface(process.stdin, process.stdout);

// Defines an interface for the data needed for each test case
interface TestData {
    testCase: number;
    e: number;
    w: number;
    a: number[][];
}

// Parses each line of input and split into the data needed for each case
function parseInput(input: string[]) {
    let line: number = 0;
    
    // Reads the number of test cases from the first line of input
    const numCases = Number(input[line++]);

    const testData: TestData[] = [];

    for (let testCase = 1; testCase <= numCases; testCase++) {
        // The first number of each line contains the number of rows of each case
        // and the second number contains the number of columns.
        const e = Number(input[line].split(' ')[0]);
        const w = Number(input[line++].split(' ')[1]);

        let a = new Array();
        let t = new Array();
        for(let i = 0; i < e; i++) {
            t = input[line++].split(' ').map((srt)=>Number(srt));
            a.push(t);
        }
        testData.push({testCase, e, w, a});
    }

    return testData;
}

function runTestCase(data: TestData) {
    let dp: number[][] = new Array();
    for (let x = 0; x < data.e; x++) {
        dp[x] = ([0])
    }

    let current = new Array();
    
    for(let i = 0; i < data.e; i++) {
        for (let b = 0; b < data.w; b++) current[b] = Infinity; 
        for (let j = i; j < data.e; j++) {
            for(let k = 0; k < data.w; k++) {
                current[k] = Math.min(current[k], data.a[j][k])
            }
            dp[i][j] = current.reduce((partialSum, b) => partialSum + b, 0);
        }
    }
    
    let dpDouble: number[][] = new Array();
    let dummy: number[] = new Array();
    for (let x = 0; x < data.e; x++) {
        dummy[x] = (Infinity);
    }
    for (let x = 0; x < data.e; x++) {
        dpDouble[x] = [...dummy];
    }
    
    let tempArr: number[] = new Array();
    for (let row = 0; row < data.e; row++) {
        dpDouble[row][row] = 2*dp[row][row]
        for(let revRow = row - 1; revRow >= 0; revRow--){
            for(let k = revRow; k < row; k++) {
                let minval;
                for(let min = revRow; min < row; min++) {
                    if ( min == revRow) {
                        minval = dpDouble[revRow][min]+dpDouble[min+1][row]-2*dp[revRow][row];
                    } else {
                        let curr = dpDouble[revRow][min]+dpDouble[min+1][row]-2*dp[revRow][row];
                        if (curr < minval) {
                            minval = curr;
                        }
                    }
                }
            dpDouble[revRow][row] = minval;
            //    tempArr[k] = (dpDouble[revRow][k]+dpDouble[k+1][row]-2*dp[revRow][row]);
            }
            //dpDouble[revRow][row] = Math.min(...tempArr);
        }
    }
    console.log(`Case #${data.testCase}: ${dpDouble[0][data.e-1]}`)
}

function runAllTests(input: string[]) {
    const testCases = parseInput(input);

    testCases.forEach(runTestCase);
}

// Reads input from the console and run all the cases
const lines : string[] = [];
rl.on('line', line => lines.push(line)).on('close', () => runAllTests(lines));
