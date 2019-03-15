const fs = require("fs");
const lines = fs.readFileSync("./inputs/D-large-practice.in", "utf8").split("\n");
const outputFilePath = './output.txt'
const numberOfCases = parseInt(lines.shift());

const fillRange = (start, end) => Array(end - start + 1).fill().map((_, index) => start + index);

const deleteOutputFile = () => {
  if (fs.existsSync(outputFilePath)) {
    fs.unlink(outputFilePath, (err) => {
      if (err) throw `Run the program again ${err}`;
    });
  }
}

const removeElemFromArray = (array, elem) => array.splice(array.indexOf(elem), 1);

const zip = (a, b) => a.map((key, i) => [key, b[i]])

const printMatrix = (matrix) => {
  matrix.map((row, i) => console.log(`${i}\t${row.toString()}`));
}

const orderByLength = (matrix) => {
  const len = matrix.length;
  let indices = fillRange(0, len - 1);
  return indices.sort(function (a, b) {
    const A = matrix[a].length;
    const B = matrix[b].length;
    if (A < B) {
      return -1;
    } else if (A > B) {
      return 1;
    } else { // a must be equal to b
      if (a < b) {
        return -1;
      } else if (a > b) {
        return 1;
      }
    }
    return 0;
  });
}

const containsSubArray = (array, subArray) => array.some(elem => elem.toString() === subArray.toString());

const createMatrix = (N, fill = '') => Array(N).fill().map(() => Array(N).fill(fill));

const fashionShow = () => {
  deleteOutputFile();
  for (let i = 0; i < numberOfCases; i++) {
    const line = lines.shift().split(' ')
    let [N, M] = line;
    N = parseInt(N);
    M = parseInt(M);
    let fashionPoints = 0;

    // Create the Matrix
    let matrix = createMatrix(N, '.');
    let freeRows = fillRange(0, N - 1);
    let freeCols = fillRange(0, N - 1);
    let freePositiveDiag = fillRange(0, (2 * N) - 2);
    let freeNegativeDiag = fillRange(-(N - 1), N - 1);
    let modelsPlaced = new Array();
    let freeDiag = new Array();
    let results = new Array();
    for (let i = 0; i < M; i++) {
      const line = lines.shift().split(' ');
      let [model, R, C] = line;
      R = parseInt(R) - 1;
      C = parseInt(C) - 1;
      if (model === 'x' || model === 'o') {
        matrix[R][C] = model;
        removeElemFromArray(freeRows, R);
        removeElemFromArray(freeCols, C)
      }
      if (model === '+' || model === 'o') {
        matrix[R][C] = model;
        removeElemFromArray(freePositiveDiag, (R + C));
        removeElemFromArray(freeNegativeDiag, (R - C));
      }
    }
    for (let [R, C] of zip(freeRows, freeCols)) {
      if (matrix[R][C] == "+") {
        matrix[R][C] = "o"
        modelsPlaced.push([R, C])
      } else if (matrix[R][C] == ".") {
        matrix[R][C] = "x"
        modelsPlaced.push([R, C])
      }
    }
    for (let R = 0; R < N; R++) {
      for (let C = 0; C < N; C++) {
        if (!freeDiag[R + C]) {
          freeDiag[R + C] = [
            [R, C]
          ];
        } else {
          const arr = freeDiag[R + C]
          arr.push([R, C]);
          freeDiag[R + C] = arr;
        }
      }
    }

    const sortedIndex = orderByLength(freeDiag);
    for (let index = 0; index < sortedIndex.length; index++) {
      const i = sortedIndex[index];
      const length = freeDiag[i].length - 1;
      if (!freePositiveDiag.includes(i)) {
        continue;
      }
      for (let j = length; j >= 0; j--) {
        const [R, C] = freeDiag[i][j];
        if (freeNegativeDiag.includes(R - C)) {
          if (matrix[R][C] === 'x') {
            matrix[R][C] = 'o';
            if (!containsSubArray(modelsPlaced, [R, C])) {
              modelsPlaced.push([R, C])
            }
          } else if (matrix[R][C] === '.') {
            matrix[R][C] = '+'
            modelsPlaced.push([R, C])
          }
          removeElemFromArray(freePositiveDiag, (R + C));
          removeElemFromArray(freeNegativeDiag, (R - C));
          break;
        }
      }
    }
    // printMatrix(matrix);
    for (let R = 0; R < N; R++) {
      for (let C = 0; C < N; C++) {
        const cell = matrix[R][C]
        if (cell === 'x' || cell === '+') {
          fashionPoints = fashionPoints + 1
        } else if (cell === 'o') {
          fashionPoints = fashionPoints + 2
        }
      }
    }

    results.push(`Case #${(i + 1)}: ${fashionPoints} ${modelsPlaced.length}\n`);
    for (let [R, C] of modelsPlaced) {
      results.push(`${matrix[R][C]} ${R+1} ${C+1}\n`)
    }
    fs.appendFile(outputFilePath, results.join(""), (err) => {
      if (err) throw err;
    });
  }
};

fashionShow();