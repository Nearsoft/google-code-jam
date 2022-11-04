let readline = require('readline');

let rl = readline.createInterface(
     process.stdin, process.stdout);
     
let strings;
let data = [ ];
let testData = {
  T: 0,
  one: [],
  two: [],
  three: [],
};
let cases = [];
function parse(data){
  testData.T = Number(data[0]);
  let testNum = testData.T;
  let line = 0;
  const numCases = Number(data[line++]);

  let cases = [];
  for(let i=1; i<=numCases; i++){
        const one = data[line++].split(' ').map((str)=>Number(str));
        const two = data[line++].split(' ').map((str)=>Number(str));
        const three = data[line++].split(' ').map((str)=>Number(str));
        cases.push({i, one,two,three});
  }
  return cases;
}

//this is the algorithm implemented to determine our answer
function solve(input){
 let final = [];
 let limit = 1000000;
 let c = [input.one[0], input.two[0], input.three[0]];
 let n = [input.one[1], input.two[1], input.three[1]];
 let y = [input.one[2], input.two[2], input.three[2]];
 let k = [input.one[3], input.two[3], input.three[3]];
 
 let min_c = Math.min(...c);
 let min_n = Math.min(...n);
 let min_y = Math.min(...y);
 let min_k = Math.min(...k); 
 let quantity = min_c + min_n+ min_y + min_k;
 if (quantity == limit){
   final.push(min_c, min_n, min_y, min_k);
   final = final.join(' ');
 }else if(quantity < limit){
   final = "IMPOSSIBLE";
 }else{
    if(min_n + min_y + min_k <= limit){
      final.push((limit-(min_n + min_y + min_k)), min_n, min_y, min_k)
      final = final.join(' ');
    }else if(min_y+min_k <= limit){
      final.push(0, (limit-(min_y + min_k)), min_y, min_k)
      final = final.join(' ')
    }else if(min_k <= limit){
      final.push(0, 0, (final- min_k), min_y);
      final = final.join(' ');
    }else{
      final.push(0,0,0,limit);
      final = final.join(' ');
    }
   
 }
 
  
  console.log(`Case #${input.i}: ${final}`);

 }

function testCases(data){
  const input = parse(data)
  input.forEach((el)=>solve(el))
  
};

rl.on('line', input => data.push(input)).on('close', () => testCases(data));