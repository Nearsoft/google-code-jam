
"use strict"
var fs  = require("fs");

let final_text = '';
let case_number = 0;
let words = [];
let datasetSize;
let regex_model = ""
let matches = 0;
let getNumberOfWords = hints => {
    let regex_model = new RegExp(hints.replace(/\(/g,'[').replace(/\)/g,']'),'gi');
    matches = 0;
    words.forEach(word =>{
      matches = (word.match(regex_model) !== null) ? matches+1 : matches;
    });
    final_text += `Case #${(case_number - datasetSize)} :  ${matches}\n`

};

fs.readFileSync('../../../../codejamcases/alien-language-input.txt').toString().split('\n').forEach(line => {
    if (line !== "" && case_number === 0) {
      datasetSize = line.split(' ')[1];
    }else if(case_number <= datasetSize){
      words.push(line);
    }else if(line!==""){
      getNumberOfWords(line);
    }
    case_number++;
  });

fs.writeFile('alien-output.es6.out', final_text, error => {
    (error) ? console.log(error) : console.log('File Saved!')
});
