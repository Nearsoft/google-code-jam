
var lineNumber = 0;
var allTests = [];
var test = [];
var cases;
var perce;
const students = 100;
const questions = 10000;

var input = [];

var studentId = 0;

function readInput() {
    const readline = require('readline')
    const rl = readline.createInterface({
      input: process.stdin,
      output: process.stdout,
      terminal: false
    })
  
  
    rl.on('line', function (line) {
        input.push(line);
    })
  
    .on('close', () => {
      // Finished processing input, now solve question
      solveProblem(input)
      process.exit()
    })
  }

  function solveProblem(inpt) {
    inpt.forEach(e => {
        lineNumber++;
        switch (lineNumber) {
            case 1:
                cases = parseInt(e);
                break;
            case 2:
                perce = parseInt(e);
                break;
            default:
                //test input
                studentId++;
                if (studentId > 100) {
                    studentId = 1;
                }
                test.push({
                    student: studentId,
                    ans: e,
                    skill: (((e.toString().split('1').length*6)/10000)-3).toFixed(4)
                })
                if (studentId == 100) {
                    allTests.push(test);
                    test = [];
                    studentId =0;
                }
                break;
        }
    });

    var acumCase = 0;
    //outs
    allTests.forEach(tst => {

        //Difficulty
        let qDifficulty = [];
        for (let ansIndx = 0; ansIndx < questions; ansIndx++) {
            let wrongCount = 0;
            tst.forEach(res => {
                if (res.ans.toString().charAt(ansIndx)=='0') {
                    wrongCount++;
                }
            });
            qDifficulty.push({
                question: ansIndx,
                difficulty: (((wrongCount*6)/students)-3).toFixed(4)
            });
        }
        //High skill students, easy questions
        tst.sort(function(a, b) { 
            return b.skill - a.skill;
        });
        qDifficulty.sort(function(a, b) { 
            return a.difficulty - b.difficulty;
        });
        var studentLevels = tst.map(x => ({skill: x.skill, student: x.student}));
        //console.log(studentLevels);
        var easiestQ = qDifficulty.reverse().slice(9500).reverse();
        //console.log(easiestQ);
        var stdDeviationList = [];
        tst.forEach(stu => {
            let ranking = [];
            qDifficulty.forEach(q => {
                if (stu.ans.charAt(q.question)==true) {
                    ranking.push(q.difficulty*10000);
                }
            });
            stdDeviationList.push({
                stdDeviation: getStandardDeviation(ranking),
                studentId: stu.student
            });
        });
        stdDeviationList.sort(function(a, b) { 
            return b.stdDeviation - a.stdDeviation;
        });
        acumCase++;
        console.log("Case #"+acumCase+": "+stdDeviationList[0]["studentId"]);
    });
  }
  
  readInput()

  function getStandardDeviation (array) {
    const n = array.length
    const mean = array.reduce((a, b) => a + b) / n
    return Math.sqrt(array.map(x => Math.pow(x - mean, 2)).reduce((a, b) => a + b) / n)
  }

function sigmoid(s,q) {
    let x = s-q;
    let res = 1/(1+(2.718^-x));
    //console.log(res.toFixed(4));
    return res.toFixed(4);
}