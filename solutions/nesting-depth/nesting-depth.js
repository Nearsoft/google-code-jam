//Solution in JS
const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split("\n");

let currentline = 0;
function readline() {
  return input[currentline++];
}

let T = readline();
for (let i = 1; i <= T; i++) {
  let nums = readline().split("").map(Number);
  console.log(`Case #${i}: ${nestingDepth(nums)}`);
}

function nestingDepth(nums) {
  let newStr = "";
  let parenthesis = 0;
  //Get digit of the string
  for (let digit of nums) {
    //Open and closing parenthesis depending of the digit
    if (parenthesis < digit) {
      newStr += "(".repeat(digit - parenthesis);
    } else if (parenthesis > digit) {
      newStr += ")".repeat(parenthesis - digit);
    }
    parenthesis = digit;

    //Insert the number between the parenthesis
    if (parenthesis == digit) {
      newStr += digit;
    }
  }
  // Closing parethesis at the end
  while (parenthesis > 0) {
    newStr += ")";
    parenthesis--;
  }

  return newStr;
}
