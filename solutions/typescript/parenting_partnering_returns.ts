declare var require: any;
declare var process: any;

const readline = require("readline");
let rl = readline.createInterface(process.stdin, process.stdout);

// Define an interface for the data needed for each test case.
interface TestData {
	testNumber: number;
	nActivities: number;
	activities: string[];
}

function parseInput(input: string[]) {
	let line: number = 0;
	// Read the number of test cases from the first line.
	const numCases = Number(input[line++]);

	const testData: TestData[] = [];
	for (let testNumber = 1; testNumber <= numCases; ++testNumber) {
		let activities: string[] = [];
		const nActivities = Number(input[line++].split(" ")[0]);
		for (let i = 1; i <= nActivities; i++) {
			activities.push(input[line++]);
		}
		testData.push({ testNumber, nActivities, activities });
	}
	return testData;
}

function runTestCase(data: TestData) {
	//sort the activity list by start time
	let list: number[][] = data.activities
		.map((x) =>
			String(x)
				.split(" ")
				.map((x) => Number(x))
		)
		.sort(sortByStart);
	//declare the lists where we will save the last activity for each kid
	let cameron: number[] = [-1, -1];
	let jamie: number[] = [-1, -1];

	//loop through the activities list to assign them to the kids
	for (let i: number = 0; i < list.length; i++) {
		//if the activity overlaps with the last activity from cameron
		//give it to jamie
		if (!isOverlap(cameron, list[i])) {
			cameron = list[i];
			//replace the activity in the unsorted array with "C"
			data.activities[data.activities.indexOf(list[i].join(" "))] = "C";
		} else if (!isOverlap(jamie, list[i])) {
			jamie = list[i];
			//replace the activity in the unsorted array with "J"
			data.activities[data.activities.indexOf(list[i].join(" "))] = "J";
		} else {
			console.log(`Case #${data.testNumber}: IMPOSSIBLE`);
			return;
		}
	}

	// Print the result
	console.log(`Case #${data.testNumber}: ${data.activities.join("")} `);
}

//function to check if two activities overlap with eachother
function isOverlap(a1: number[], a2: number[]) {
	if (a1[0] === -1) {
		return false;
	}
	//if the start or end of an activity is in between another activity
	if (a2[0] < a1[1]) {
		return true;
	}
	return false;
}

function sortByStart(a: number[], b: number[]) {
	if (a[0] === b[0]) {
		return 0;
	} else {
		return a[0] < b[0] ? -1 : 1;
	}
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
