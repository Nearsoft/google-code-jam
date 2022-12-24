
    // Punched Cards
  
    // Individual Problem - Manuel Cota - Apprentice 2022 D

// The following code is here so that the program can correctly communicate
// with the input and output format in the Google Code Jam platform.
//----------------------------------------------------------------------
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
//----------------------------------------------------------------------


// This function draws the card saving it in a variable "card".
function punched_cards(R, C) {

    // We initiate by creating the top row, which will always be there.
    // The following code types the top edge depending on the # of columns.
    let row = "..+";
    for (let j = 0; j < (C-1); j++) {
        row += "-+";
    }
    row += "\n";

    let card = row;

    // Now, we create each new row, starting with an exception for the first iteration.
    // This is because the top left corner of the card is only dots.
    row = "..|";
    let row2 = "+-+";
    for (let j = 0; j < R; j++) {
        for (let k = 0; k < (C-1); k++) {
            row += ".|";
            row2 += "-+";
        }
        row += "\n";
        row2 += "\n"
        card += row + row2

        // With this resets, all following iterations will only have "+-+", unlike the top left corner.
        row = "|.|";
        row2 = "+-+";
    }

    return card;
}

// This function receives the values for R and C in each iteration from all the T cases.
// It uses those values to draw the punched card using the punched_cards function and then
// gives it as output.
function solve() {
    // Declare variables R and C.
    var R, C;
    // Read the integers from the standard input.
    [R, C] = readline().split(' ').map(x => parseInt(x));
  
    // Obtains the card from the punched_cards function.
    var card = punched_cards(R, C);
  
    // Print the result onto the standard output.
    process.stdout.write(card);
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
