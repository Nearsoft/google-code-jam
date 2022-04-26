use std::io;
fn main() {
    let mut r;
    let mut c;
    let mut cas = 1;
    let mut tests = String::new();
    io::stdin()
        .read_line(&mut tests)
        .expect("Failed to read line");
    let mut t: i32 = tests.trim().parse().expect("Input not an integer");
    
    for _k in 0..t {
      let mut val = String::new();
      io::stdin()
                .read_line(&mut val)
                .expect("Failed to read line");
                val = val.trim().parse().expect("Input not an integer");
  
      let mut substr_iter = val.split_whitespace();
      let mut next_num = || -> usize {
          substr_iter.next().expect("Not enough input numbers")
                     .parse().expect("Input is not a number")
      };
      r = next_num();
      c = next_num();
      
      println!("Case #{}:", cas);
      cas+=1;
      for i in 0..r {
            if i == 0 {
              print!("..");
                for _j in 0..c-1 {
                  print!("+-");
                }
              println!("+");
            }
            for j in 0..c-1 {
                if i == 0 && j == 0 {
                    print!("..");
                } else if i!=0 && j==c-2{
                  print!("|.");
                }
                print!("|.");
            }
            println!("|");
            for _j in 0..c{
                print!("+-");   
            }
            println!("+");
      }
      t-=1;
    }
}