var readline = require('readline');
var rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal: false
});


let ln = 0;
rl.on('line', function(line){
  if(ln == 0){
    if(line < 1 || line > 100){
      console.log('Number of cases excedes limits');
      process.exit();
      return;
    }
    ln++;
    return;
  }else{
    const solution = solve_case(line);
    console.log('CASE #' + ln + ': ' + solution);
    ln++;
  }
})

function solve_case(shieldAndRobotProgram) {
  const validator = /^\d+ [S|C]+$/;

  if(!(validator.test(shieldAndRobotProgram))){
    console.log("Alien's program has an incorrect format");
    process.exit();
  }
  let d = parseInt(shieldAndRobotProgram.split(' ')[0]);
  if(d < 1 || d > Math.pow(10, 9)){
    console.log("Shield's defence too high");
    process.exit();
  }
  let p = shieldAndRobotProgram.split(' ')[1];
  if(p.length < 2 || p.length > 30){
    console.log("Alien's program too long");
    process.exit();
  }
  p = p.split('');
  let hacks = 0;

  do {
    let damage = damage_calc(p);
    if (damage <= d){
      return hacks;
    }
    hacks++;
  }while(hack(p));

  return 'IMPOSSIBLE';
}

// Calculates the damage of a robot program
function damage_calc(program){
  let total_damage = 0;
  let damage = 1;

  for (let i = 0; i < program.length; i++) {
    if (program[i] == 'S') {
      total_damage += damage;
    } else {
      damage *= 2;
    }
  }
  return total_damage;
}

// Evaluates if hack can be made
function hack(program){
  for (let i = program.length-1; i > 0; i--) {
    if (program[i] == 'S' && program[i-1] == 'C') {
      [program[i], program[i-1]] = [program[i-1], program[i]];
      return true;
    }
  }
  return false;
}
