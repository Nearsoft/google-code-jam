
use std::io;
use std::collections::BTreeMap;

#[allow(unused_macros)]
macro_rules! read {
    ($out:ident as $type:ty) => {
        let mut inner = String::new();
        std::io::stdin().read_line(&mut inner).expect("A String");
        let mut $out = inner.trim().parse::<$type>().expect("Parsable");
    };
}

#[allow(unused_macros)]
macro_rules! read_vec {
  ($out:ident as $type:ty) => {
    let mut inner = String::new();
    io::stdin().read_line(&mut inner).unwrap();
    #[warn(unused_variables)]
    #[warn(unused_variables)]
    let mut $out = inner
      .trim()
      .split_whitespace()
      .map(|s| s.parse::<$type>().unwrap())
      .collect::<Vec<$type>>();
  };
}

fn main(){
  let mut cas: i32 = 1;
  let mut solution = BTreeMap::new();
  read!(t as i32);
  while t > 0 {
    read!(n as i32);
    read_vec!(s as i32);    
    s.sort();
    
    let mut curr:i32 = 1;
    for dice in s {
      let dice: i32 = dice;
      if curr > dice {
        continue;
      }
      curr+=1;
    }
    solution.insert(cas,curr-1);
    cas += 1;
    t -= 1;
  }

  for (key, val) in solution.iter() {
    println!("Case #{:?}: {:?}", key, val);
  }
}