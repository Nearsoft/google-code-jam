declare var process: any;
declare var require: any;

let T: number;                  // number of cases
let counter: number = 0;        // counts the number of cases solved 
let part: string = "first";     // used to divide each case in two different parts

var judge_gives: Array<number> = []; //Judge gives N
var set_b: Array<number> = [];
var set_a: Array<number> = [];



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
   let value = input.split(' ').map((str) => Number(str));
   return value;
    
}

/*
    This function generates a tactical N sets of numbers made of powers of 2
    and the remaining N-30 numbers will be the last N numbers before 10**9 inclusive.
    As N is always 100, this fuction is always performed without problems
    */
function powerOfTwo(N: number){
    const a: Array<number> = [];
    
    //append first 30 powers of two
    for(var t = 0; t < 30; t++ ){
        let base: number = 2;
        let exponent: number = t;
        let result: number = Math.pow(base, exponent);
        a.push(result);
    }

    // Append the other 70 garbage numbers (in this case the last 70 allowed)
    for(var t = 0; t < N-30; t++ ){
        let base: number = 10;
        let exponent: number = 9;
        let max_value: number = Math.pow(base, exponent);
        a.push(max_value - t);

    }

    return a;

}
/*
This function implements a greedy approach to minimize the sum difference
among two sets A and B
*/
function solveSum(setA: number[], setB: number[]){
    //Join both subsets and sort them in reversed order
    let C: Array<number> = [...setA,...setB];
    //console.log(C);
    C.sort((n1, n2)=> n2- n1);

    let a_sum:number = 0;
    let b_sum:number = 0;

    // A set containing N_1 numbers with equal sum as the N_2 numbers
    let equal_sum_set: Array<number> = []; 
    for(let i= 0 ; i < C.length ; i++){
        if (a_sum > b_sum){
            equal_sum_set.push(C[i]);
            b_sum += C[i];
        }
        else{
            a_sum += C[i];
        }
    }
    return equal_sum_set;

}



rl.question('', (cases) => {
    // Assigns T cases through line of input
    T = Number(cases);
 
    rl.on('line', (input) => {
        // receives the integer to give the length'Set
        if (part === "first") {
            judge_gives = parseInteger(input);
            // Generate our A set
            set_a = powerOfTwo(judge_gives[0]);
            let str: string = set_a.join(" ");
            console.log(" " + str);
            

            part = "second";
        }else{
            //Read the B set provided by machine
            set_b = parseInteger(input);

            // Generate the equal sum
            var result: Array<number> = solveSum(set_a, set_b)
            //Print the result into the console
            var str: string = result.join(" ");
            console.log(" " + str);


            counter += 1;
            part ="first";
        }
        if ((counter === T) || (judge_gives[0] < -1)) {
            rl.close()
        }

    }).on('close', () => {
    process.exit    
    })
})
