const fs = require('fs');

// Read the input file and store the contents as a string
const input = fs.readFileSync('ts1_input.txt', 'utf8');

// Split the string into an array of lines
const inputLines = input.trim().split('\n');

// Iterate over the array of lines, skipping every other line
for (let i = 2; i < inputLines.length; i += 2) {
  console.log("Case #"+(i/2)+": "+maxDiceLength(inputLines[i]));
}
console.log("Finished!")

function maxDiceLength(inputStr){
  let length=0;
  let die=inputStr.split(" ")
  for(let i=0;i<die.length;++i){
    die[i]=Number(die[i]);
  }
  die=die.sort(function(a, b) {return a - b;});
  for(let i=0;i<die.length;++i){
    if(die[i]>length) ++length;
  }
  return length;
}
