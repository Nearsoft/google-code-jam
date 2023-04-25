//#region read input input_lines. DO NOT TOUCH!!
declare var require: any;
declare var process: any;

const readline = require('readline');

function read_lines_from_stdin(callback: (input_lines: string[]) => void) {
  const input_lines: string[] = [];
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
  });

  rl.on('line', (input: string) => {
    input_lines.push(input);
  });

  rl.on('close', () => {
    callback(input_lines);
  });
}
//#endregion


function createRow(prefix: string, middle: string, count: number): string {
  return [prefix, ...Array(count + 1).join(middle + ' ').split(' ')].join('') + "\n";
}

function cardBuilder(row: number, col: number): string {
  const rowA = createRow("+", "-+", col);
  const rowB = createRow("|", ".|", col);
  const rowX = createRow("..+", "-+", col - 1);
  const rowY = createRow(".", ".|", col);

  return [
    rowX,
    rowY,
    ...Array(row).join(rowA + rowB).split(' '),
    rowA.substring(0, rowA.length - 1)
  ].join('');
}


//#region Main function.
function main() {
  read_lines_from_stdin((input_lines) => {
    let Times = parseInt(input_lines[0]);

    for (var i: number = 1; i <= Times; i++) {
      let my_nums = input_lines[i].split(' ').map((num) => parseInt(num));
      console.log(`Case #${i}:\n${cardBuilder(my_nums[0], my_nums[1])}`)
    }
  })
}
main();
//#endregion