const readline = require('readline');

var inputNumber = 1;

var actualCaseNum = 1;

var actualArraySize = 0;

var numTestCases = 0;

var arrays = [];

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const swapValues = (i, start, end) => {
  while (start < end) {
    [arrays[i][start], arrays[i][end]] = [arrays[i][end], arrays[i][start]];
    start++;
    end--;
  }
};

const solution = () => {
  for (let i = 0; i < arrays.length; i++) {
    let cost = 0;
    for (let j = 0; j < arrays[i].length - 1; j++) {
      let minPosition = j;
      for (let k = j; k < arrays[i].length; k++) {
        if (arrays[i][k] < arrays[i][minPosition]) {
          minPosition = k;
        }
      }

      swapValues(i, j, minPosition);

      cost += minPosition - j + 1;
    }

    console.log(`Case #${i + 1}: ${cost}`);
  }

  process.exit();
};

const obtainData = (input) => {
  if (inputNumber === 1) {
    if (!isNaN(parseInt(input))) {
      numTestCases = parseInt(input);
      inputNumber++;
      console.log(`\nType array ${actualCaseNum} size: `);
    } else {
      console.log('Invalid Input');
    }
  } else if (inputNumber === 2) {
    actualArraySize = input;

    inputNumber++;
    console.log(
      `Type ${actualArraySize} elements of array ${actualCaseNum} separated by space: `
    );
  } else if (inputNumber === 3) {
    var array = input.split(' ');
    if (array.length != actualArraySize) {
      console.log('Given more/less than ' + actualArraySize + ' Try again');
    } else {
      arrays.push(array);

      if (actualCaseNum === numTestCases) {
        console.log();
        solution();
      } else {
        actualCaseNum++;
        console.log(`\nType array ${actualCaseNum} size: `);
        inputNumber = 2;
      }
    }
  }
};

const main = () => {
  console.log('Type the number of test cases: ');

  rl.on('line', obtainData);
};

main();
