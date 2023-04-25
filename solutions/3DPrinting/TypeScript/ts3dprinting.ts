//#region read input input_lines. 
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

const logic = (value: string[], tests: number) => {
  let test_num: number = 1;
  for (let i = 1; test_num <= tests; test_num++) {

    const printer_1: string[] = value[i].split(" ");
    const printer_2: string[] = value[++i].split(" ");
    const printer_3: string[] = value[++i].split(" ");
    i++

    // Convert the list of strings to a list of numbers these values represent the cyan ink in each printer
    // Find the printer with the least cyan ink and store that value in cy
    let cy: number = Math.min(
      parseInt(printer_1[0]),
      parseInt(printer_2[0]),
      parseInt(printer_3[0])
    );

    // Same for all other colors (magenta, yellow and black) to complete CMYK
    let ma: number = Math.min(
      parseInt(printer_1[1]),
      parseInt(printer_2[1]),
      parseInt(printer_3[1])
    );

    let ye: number = Math.min(
      parseInt(printer_1[2]),
      parseInt(printer_2[2]),
      parseInt(printer_3[2])
    );

    let bl: number = Math.min(
      parseInt(printer_1[3]),
      parseInt(printer_2[3]),
      parseInt(printer_3[3])
    );
    // Put them in an iterable, so they can be added with reduce()
    const color_out: number[] = [cy, ma, ye, bl];
    const total_ink: number = color_out.reduce((sum, ink) => sum + ink);

    // If we have less than 10^6 there is not enough ink
    if (total_ink < 1000000) {
      console.log(`Case #${test_num}: IMPOSSIBLE`);
    }

    // If we have just enough ink to print
    else if (total_ink === 1000000) {
      console.log(`Case #${test_num}: ${cy} ${ma} ${ye} ${bl}`);
    }

    // There is more than enough so we need to use less ink
    else {
      let dif: number = total_ink - 1000000;

      for (let i: number = 0; i < color_out.length; i++) {
        if (dif >= color_out[i]) {
          dif -= color_out[i];
          color_out[i] = 0;
        } else {
          color_out[i] -= dif;
          break;
        }
      }

      console.log(
        `Case #${test_num}: ${color_out[0]} ${color_out[1]} ${color_out[2]} ${color_out[3]}`
      );
    }
  }
}

function main() {
  read_lines_from_stdin((input_lines) => {
    let tests = parseInt(input_lines[0]);
    logic(input_lines, tests)
  })
}
main();