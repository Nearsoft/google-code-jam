// Uses tsconfig.json to compile this file.
//
// To compile use the command `tsc` inside a folder containing
// write the command `node <compiled JS file>` replacing with the name 
// of the compiled file.

declare var process: any;
declare var require: any;

// using module 'readline'
const readline = require('readline');
// creates an interface to receive input and print output
let rl = readline.createInterface({
    input: process.stdin, 
    output: process.stdout,
    terminal: false
});

// function to parse strings into two different variables
const parseInteger = (input: string) => {
    let [a, b] = input.split(' ').map((str) => Number(str))
    return [a, b]
}

var candidates = new Set();

let T: number;                  // number of cases
let counter: number = 0;        // counts the number of cases solved
let case_counter: number = 0;   // counter used in each individual case
let part: string = "first";     // used to divide each case in two different parts

let [N, K]: [number, number] = [0, 0];
let [R, P]: [number, number] = [0, 0];

let degree = 0;
let degree_T = 0;
let count_T = 1;

rl.question('', (cases) => {
    // Assigns T cases through line of input
    T = Number(cases)

    rl.on('line', (input) => {
        // Parses number of rooms and number of operations
        if (part === "first") {
            [N, K] = parseInteger(input)
            // makes set of N elements from 1 to N
            candidates = new Set()
            for (let i = 1; i <= N; i++) {
                candidates.add(i)
            }
            part = "second";

        // Parses current room and observable passages
        } else if (part === "second") {
            [R, P] = parseInteger(input)
            
            // if K operations are done then end current case
            if (case_counter === K) {
                if (candidates.has(R)) {
                    candidates.delete(R)
                }
                let degree_avg = degree_T / count_T
                let result = Math.floor((degree + degree_avg * candidates.size) / 2)

                console.log(`E ${result}`)

                part = "first";
                case_counter = 0;
                counter += 1;

            } else {
                // checks if its the first R, P input
                if (case_counter === 0) {
                    if (candidates.has(R)) {
                        candidates.delete(R)
                    }
                    degree = P
                    degree_T = degree

                    console.log('W')
                } else if (case_counter % 2 === 0) {
                    // walk
                    console.log('W');
                } else {
                    // teleport
                    let a = candidates.values().next()
                    console.log(`T ${a.value}`);
                    degree_T += P;
                    count_T += 1;
                }
                // if room is in candidates delete it from set
                if (candidates.has(R)) {
                    candidates.delete(R)
                    degree += P;
                }
                case_counter += 1;
            }
        }
        if (counter === T) {
            rl.close()
        }

    }).on('close', () => {
        process.exit
    })
})
