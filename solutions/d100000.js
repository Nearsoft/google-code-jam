
let readline = require('readline');

let rl = readline.createInterface(
     process.stdin, process.stdout);
     

let data = [ ];

let testData = {
  test: 0,
  d_sides: [ ]
};

function parse(data){
 let line = 0;
 const numCases = Number(data[line++]);
 let cases = [];
 

  for(let i=1; i<=numCases; i++){
        const dices = Number(data[line++]);
        const sides = data[line++].split(' ').map((str)=>Number(str))
        cases.push({i,dices,sides});
  }
  return cases;
}
function solve(input){
  
    input.sides.sort((n1, n2)=> n1- n2);

    let min = input.sides[0];
    let max = 0;

    for(let j = 0; j < input.dices ; j++ ){

        if((input.sides[j] >= min) && (input.sides[j] > max)){
            max += 1;

        }else{
            continue
        }
    }

    console.log(`Case #${input.i}: ${max}`);
   
  
}

function testCases(data){
  const input = parse(data);
  input.forEach((el)=> solve(el));
};

rl.on('line', input => data.push(input)).on('close', () => testCases(data));