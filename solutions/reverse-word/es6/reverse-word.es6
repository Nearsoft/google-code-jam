"use strict"
var fs  = require("fs");
let output_string = '';
let input_array;
let case_number = 0;

fs.readFileSync('../../../../codejamcases/reverse-word-input.txt').toString().split('\n').forEach(line => {
    if (line !== "" && case_number !== 0) {
        input_array = line.split(" ");
        for (let i = input_array.length - 1; i >= 0; i--) {
          output_string += (input_array[i]) + ' ';
        };
        console.log('Case #'+ case_number + ': '+output_string.trim());
        output_string = '';
    };
    case_number++;
  });
