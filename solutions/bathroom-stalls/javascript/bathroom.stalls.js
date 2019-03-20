const bigInt = require("big-integer");
const fs = require("fs");
const lines = fs.readFileSync("./inputs/C-small-practice-2.in", "utf8").split("\n");

const numOfCases = parseInt(lines.shift());
let output = [];

for (let i = 0; i < numOfCases; i++) {
  const input = lines[i].split(' ');
  const numberOfStalls = bigInt(input[0]); // N stalls 
  const numberOfPeople = bigInt(input[1]); // numberOfPeople people are about to enter the bathroom;
  const result = solveBathroomStalls(numberOfStalls, numberOfPeople);
  output.push(`Case #${(i + 1)}: ${result[0]} ${result[1]}`);
}

fs.writeFile('./output.txt', output.join("\n"), (err) => {
  if (err) throw err;
  console.log('Finished👌😊 \nCheck output.txt file for the results');
});

function solveBathroomStalls(numberofStalls, numberOfPeople) {
  if (bigInt(numberOfPeople).equals(1)) {
    const arr = new Array();
    arr[0] = bigInt(numberofStalls).divide(2);
    arr[1] = bigInt(numberofStalls).minus(1).divide(2);
    return arr;
  }
  if (bigInt(numberOfPeople).mod(2).equals(0)) {
    return solveBathroomStalls(bigInt(numberofStalls).divide(2), bigInt(numberOfPeople).divide(2));
  }
  return solveBathroomStalls(bigInt(numberofStalls).minus(1).divide(2), bigInt(numberOfPeople).divide(2));
}