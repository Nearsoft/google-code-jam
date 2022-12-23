declare var require: any;
declare var process: any;

const readline = require("readline");
let rl = readline.createInterface(process.stdin, process.stdout);

// Define an interface for the data needed for each test case.
interface TestData {
	testNumber: number;
	x: number;
	y: number;
	s: string;
}

function parseInput(input: string[]) {
	let line: number = 0;
	// Read the number of test cases from the first line.
	const numCases = Number(input[line++]);

	const testData: TestData[] = [];
	for (let testNumber = 1; testNumber <= numCases; ++testNumber) {
		const x = Number(input[line].split(" ")[0]);
		const y = Number(input[line].split(" ")[1]);
		const s = String(input[line].split(" ")[2]);
		line++;
		testData.push({ testNumber, x, y, s });
	}
	return testData;
}

function runTestCase(data: TestData) {
	//with dynamic programming
	let cCost: number = data.s[0] === "J" ? Number.POSITIVE_INFINITY : 0;
	let jCost: number = data.s[0] === "C" ? Number.POSITIVE_INFINITY : 0;

	for (let i: number = 1; i < data.s.length; i++) {
		let cCostNew: number = Number.POSITIVE_INFINITY;
		let jCostNew: number = Number.POSITIVE_INFINITY;

		if (data.s[i] === "C") {
			cCostNew = Math.min(cCost, jCost + data.y);
		} else if (data.s[i] === "J") {
			jCostNew = Math.min(jCost, cCost + data.x);
		} else {
			cCostNew = Math.min(cCost, jCost + data.y);
			jCostNew = Math.min(jCost, cCost + data.x);
		}
		cCost = cCostNew;
		jCost = jCostNew;
	}

	// Print the result
	console.log(`Case #${data.testNumber}:  ${Math.min(cCost, jCost)}`);
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
