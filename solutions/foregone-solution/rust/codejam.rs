
use std::io;
fn main() {
//contador de casos
let mut case =1;
//entrada de texto para el numero de casos
let mut input_t=String::new();
let mut _first_number = String::new();
let mut _second_number = String::new();
match io::stdin().read_line(&mut input_t)
  {
    Ok(_)=> {
    },
   Err(e)=>println!("No valid: {}",e)
  }
let _i: i32 = input_t.trim().parse().unwrap();
let mut _t=_i;

while _t>0
{
  // Separar numeros
  let mut input_n=String::new();
  match io::stdin().read_line(&mut input_n)
  {
    Ok(_)=>{
    },Err(e)=> println!("No valid= {}",e)
  }
  for x in 0..input_n.len()-1
  {
  
    let char_vec:Vec<char> = input_n.chars().collect();
    let ch = char_vec[x];
    if ch=='4'
    {
      _first_number.push('2');
      _second_number.push('2');
    }
    else {
      _first_number.push(ch);
      _second_number.push('0');
    } 
  }
  println!("Case #{}: {} {}",case,_first_number,_second_number);
  _first_number.clear();
  _second_number.clear();
  case=case+1;
  _t=_t-1;
}
}


