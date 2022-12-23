declare var require: any;
declare var process: any;

const readline = require("readline");
let rl = readline.createInterface(process.stdin, process.stdout);

// Define an interface for the data needed for each test case.
interface TestData {
	testNumber: number;
	N: number;
	C: number;
}

function parseInput(input: string[]) {
	let line: number = 0;
	// Read the number of test cases from the first line.
	const numCases = Number(input[line++]);

	const testData: TestData[] = [];
	for (let testNumber = 1; testNumber <= numCases; ++testNumber) {
		const N = Number(input[line].split(" ")[0]);
		const C = Number(input[line++].split(" ")[1]);
		testData.push({ testNumber, N, C });
	}
	return testData;
}

function runTestCase(data: TestData) {
	let maxCost: number = -1 + (data.N * (data.N + 1)) / 2; //max cost of entire algorithm
	let minCost: number = data.N - 1; //min cost of entire algorithm
	let iterations: number = data.N - 2; //how many iterations does it have left
	let min: number = 1; //min cost of the current operation/round
	let max: number = data.N; //max cost of the current operation/round
	let array: number[] = []; //base array of positions
	let sortedArray: number[] = []; //array from 1 to N
	let subArray: number[] = []; //Array to be reversed
	let x: number = max; //must be between min and max
	let result: number[] = [];

	//Checks if it's possible in the first place
	if (data.C < minCost || data.C > maxCost) {
		process.stdout.write(`Case #${data.testNumber}: IMPOSSIBLE\n`);
		return;
	}

	//Creates 2 arrays; one  array that'll be our base array of positions and the other one will be for the numbers themselves
	for (let i: number = 0; i < data.N; ++i) {
		sortedArray[i] = i + 1;
		array[i] = i;
	}
	/*The trick is to subtract the cost of each operation from the goal cost C,
  each operation's cost must be between the min 1, and the max n (min is always 1 while the max changes for each iteration -1)*/
	for (let i: number = 0; i <= data.N - 2; ++i) {
		if (data.C - x >= 0 && data.C - x >= iterations) {
			subArray = array.slice(i, i + x);
			subArray.reverse();
			array = [...array.slice(0, i), ...subArray, ...array.slice(i + x, array.length)];
			data.C = data.C - x;
			max += -1;
			iterations += -1;
			x = max;
			if (data.C == 0) break;
		} else {
			x += -1;
			i += -1;
			continue;
		}
	}
	for (let i: number = 0; i < array.length; ++i) {
		result[array[i]] = sortedArray[i];
	}
	process.stdout.write(`Case #${data.testNumber}: ${result.join(" ")}\n`);
}

function runAllTests(input: string[]) {
	// Parse the input into TestData objects.
	const testCases = parseInput(input);

	// Run each test case.
	testCases.forEach(runTestCase);
}

// Read the input data and run all test cases.
const lines: string[] = [];
rl.on("line", (line) => lines.push(line)).on("close", () => runAllTests(lines));
