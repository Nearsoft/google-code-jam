//Requirements to read the input file
const fs = require('fs');
const inputFile = fs.readFileSync(0, 'utf8').trim().split('\n');

// Function definition
const punchedCards = (input) => {
    // Declaring variables
    let testCases = input[0],
        cols = 0,
        rows = 0,
        caseNum = 1;
    // Loop for each case
        while (caseNum <= testCases) {
            // Saving rows and columns numbers and create the matrix
            rows = input[caseNum].split(' ')[0] * 2;
            cols = input[caseNum].split(' ')[1] * 2;
            let card = [];
            // Loop to circle each row
            for (let i = 0; i <= rows; i++) {
                card.push(['']);
                // Second loop to circle each column
                if (i % 2 == 0) {
                    for (let j = 0; j <= cols; j++) {
                        card[i][j] = ((j % 2 == 0)? '+' : '-');
                    }
                } 
                else {
                    for (let j = 0; j <= cols; j++) {
                        card[i][j] = ((j % 2 == 0)? '|' : '.');
                    }
                }
            }
            // Replacing the top-left cell
            card[0][0] = '.';
            card[0][1] = '.';
            card[1][0] = '.';
            card[1][1] = '.';
            // Printing the result
            console.log(`Case #${caseNum}:`);
                for (let i = 0; i <= rows; i++) {
                    console.log(`${card[i].join('')}\n`);
                }
        caseNum++;
        }
};
// Function call
punchedCards(inputFile);