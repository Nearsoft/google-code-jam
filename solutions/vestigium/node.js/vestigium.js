"use strict";
var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

var output = "";

var lineNumber = 1;
var actualCaseLooping = 1;
var cases = 0;
var matrixSize = 0;
var row = 0;
var arrOfLines = [];

rl.on('line', function(lineValue){

    //Starts the reading lines per input
    switch (lineNumber) {
        //The number of cases is set (T)
        case 1:
            cases = parseInt(lineValue);
            lineNumber++;
            break;
        //The size of the matrix is set (N)
        case 2:
            arrOfLines = []
            matrixSize = parseInt(lineValue);
            lineNumber++;
            row = 0;
            break;
        //The row is set, pushing the numbers to an array
        case 3:
            arrOfLines[row] = lineValue.split(" ");
            row++;
            //Checking if we have finished drawing the matrix, then we call the mainProess function, and start recibing the next case
            if( row >= matrixSize ){
                mainProcess(arrOfLines);
                if(actualCaseLooping<cases){
                    lineNumber = 2;
                    actualCaseLooping++;
                    break;
                } else{
                    lineNumber++;
                }
            }else{
                break;
            }
        default:
            //if theres no more cases we exit the program and return output
            outputAndExit(output);
            process.exit();
    }
});

function mainProcess(arrOfLines){

    var trace = 0;
    var numOfRepeatedRows = 0;
    var numOfRepeatedCols =0;

    //we add all the numbers of the trace
    for (let i = 0; i < matrixSize; i++) {
        trace += parseInt(arrOfLines[i][i]);
    }

    //we call the function to see how many rows are repeated
    for (let j = 0; j < arrOfLines.length; j++) {
        if (hasRepeat(arrOfLines[j])) {
            numOfRepeatedRows++;
        }
    }

    //we change the direction of the arr to see now ho many columns are repeated
    arrOfLines = transpose(arrOfLines);

    //we call the function to see how many colums are repeated
    for (let i = 0; i < arrOfLines.length; i++) {
        if (hasRepeat(arrOfLines[i])) {
            numOfRepeatedCols++;
        }
    }

    output += `Case #${actualCaseLooping}: ${trace} ${numOfRepeatedRows} ${numOfRepeatedCols}\n`;
}

//fucntion to ecit the program
function outputAndExit(output) {
    process.stdout.write(output);
    process.exit();
}

//function to see how many rows or colums are repeated
function hasRepeat(arr) {
    var temp = arr.filter((value, index, self) =>{
        return self.indexOf(value) === index;
    })
    return arr.length != temp.length;
}

//function to invert the direction of the matrix
function transpose(arrOfLines) { 
    for (let i = 0; i < arrOfLines.length; i++) {
        for (let j = 0; j < i; j++) {
            const tmp = arrOfLines[i][j];
            arrOfLines[i][j] = arrOfLines[j][i]; 
            arrOfLines[j][i] = tmp;
        }
    }
    return arrOfLines;
}