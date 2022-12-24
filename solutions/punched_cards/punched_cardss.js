'use strict';

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', inputStdin => {
  inputString += inputStdin;
});

process.stdin.on('end', _ => {
  inputString = inputString.trim().split('\n').map(string => {
    return string.trim();
  });

  main();
});

function readline() {
  return inputString[currentLine++];
}

// Make a Snippet for the code above this and then write your logic in main();

const draw = (row, col) => {
    let rowN = '';
    for (let r=0; r<row;r++){
        for (let c=0; c<col; c++){
            if(r<=1 && c<=1)
                rowN += '.' ;
            else if (c % 2 === 0){
                if (r % 2 === 0)
                    rowN += '+';
                else
                    rowN += '|';
            }
            else {
                if (r % 2 !== 0)
                    rowN += '.';
                else
                    rowN += '-';
            }
        }
        rowN += '\n';
    }
    return rowN;
};

function solve() {
    // Declare variables N and M.
    var row, col;
    // Read the integers from the standard input.
    [row, col] = readline().split(' ').map(x => parseInt(x));
    row = (row*2) +1; 
    col = (col*2) +1; 
  
    // Compute the value of the sum modulo M.
    let drawing = draw(row, col);
  
    // Print the result onto the standard output.
    process.stdout.write(drawing);
}

function main() {
  // Declare and read the number of test cases.
  var T;
  T = parseInt(readline());

  // Loop over the number of test cases.
  for (var test_no = 1; test_no <= T; test_no++) {
    process.stdout.write('Case #' + test_no + ': \n');
    solve();
  }
}

