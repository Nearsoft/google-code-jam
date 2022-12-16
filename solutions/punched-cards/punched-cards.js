"use strict";

process.stdin.resume();
process.stdin.setEncoding("utf-8");

let inputString = "";
let currentLine = 0;

process.stdin.on("data", (inputStdin) => {
	inputString += inputStdin;
});

process.stdin.on("end", (_) => {
	inputString = inputString
		.trim()
		.split("\n")
		.map((string) => {
			return string.trim();
		});

	main();
});

function readline() {
	return inputString[currentLine++];
}

// Make a Snippet for the code above this and then write your logic in main();

function main() {
	// Declare and read the number of test cases.
	var T;
	T = parseInt(readline());

	// Loop over the number of test cases.
	for (var test_no = 1; test_no <= T; test_no++) {
		process.stdout.write("Case #" + test_no + ": \n");
		solve();
	}
}

function solve() {
	// Declare variables N and M.
	var rows, cols;
	// Read the integers from the standard input.
	[rows, cols] = readline()
		.split(" ")
		.map((x) => parseInt(x));
	//get the real size
	rows = rows * 2 + 1;
	cols = cols * 2 + 1;

	//this will keep the state of the next character to be printed
	let nextIndexOdd = 0;
	let nextIndexEven = 0;

	//rotates the next character between + and -
	const nextOdd = () => {
		const chars = ["-", "+"];
		nextIndexOdd = (nextIndexOdd + 1) % chars.length;
		return chars[nextIndexOdd];
	};

	//rotates the next character between | and .
	const nextEven = () => {
		const chars = [".", "|"];
		nextIndexEven = (nextIndexEven + 1) % chars.length;
		return chars[nextIndexEven];
	};

	//loop through each row
	for (let row = 1; row <= rows; row++) {
		//give the correct characters to each row
		let next = row % 2 !== 0 ? nextOdd : nextEven;
		for (let col = 1; col <= cols; col++) {
			//the first square is always empty
			if (row <= 2 && col <= 2) {
				process.stdout.write(".");
			} else {
				process.stdout.write(next());
			}
		}
		//restart the character counter for the next line
		nextIndexEven = 0;
		nextIndexOdd = 0;
		process.stdout.write(`\n`);
	}
}
