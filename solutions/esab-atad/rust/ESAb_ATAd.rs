use std::process;
use std::io::{self, Write};

fn main(){
    let mut input = String::new();
    io::stdout().flush();
     //println!("Say something");
    match io::stdin().read_line(&mut input){
        Ok(_) => {
          //println!("Succes, you said {}", input);
            
      },
      Err(e) => process::exit(1)
    }
    let mut split = input.split_whitespace();
    let vec = split.collect::<Vec<&str>>();
    let mut T = vec[0].parse::<i32>().unwrap();
    let mut B = vec[1].parse::<i32>().unwrap();
  
    for i in 0..T {
        esab_atad(B as usize);   
    }
}

fn esab_atad(b: usize){
    let mut result =  Vec::<i32>::with_capacity(b+1);
    for i in 0..(b+1) {
    result.push(0);
  } 
    let mut L = 1;
    let mut R = b;
    let mut nr = 1;
    while true {
        //println!("nr del for {}",nr);
        //println!("L {}",L);
        //println!("R {}",R);
        if nr%10 == 1 && nr != 1{
            //println!("cambia algo");
            let mut found: i32 = -1;
            let mut found_diff = -1;
            for i in 1..L {
                //println!("i en primer for {}", i);
                if result[i as usize] == result[(b+1-i) as usize]{
                    found = i as i32;
                }else{
                    found_diff = i as i32;
                }
            }
            if(found == -1){
                let mut new_value = query(1);
                query(1);
                if new_value != result[1] {
                    for i in 1..L+1{
                        //println!("i en segundo for {}", i);
                        result[i as usize] ^= 1;
                    }
                    for i in R..b+1{
                       // println!("i en tercer for {}", i);
                        result[i as usize] ^= 1;
                    }
                }
            }else{
                let mut one = query(found);
                if one != result[found as usize]{
                    for i in 1..L+1{
                        //println!("i en cuarto for {}", i);
                        result[i as usize] ^= 1;
                    }
                     for i in R..b+1{
                         //println!("i en quinto for {}", i);
                        result[i as usize] ^= 1;
                    }
                }
                if found_diff == -1{
                    query(found);
                }else{
                    if query(found_diff) != result[found_diff as usize] {
                        result.remove(0);    
                        result.reverse();      
                        result.push(0);    
                        for i in (1..result.len()).rev(){
                            let mut aux = result[(i-1)];
                            result[(i-1)] = result[i];
                            result[i] = aux;
                        } 
                    }
                }
            }
            nr = nr+2;
        }
        result[L as usize] = query(L as i32);
        result[R as usize] = query(R as i32);
        L = L+1;
        R = R-1;
        if L > R {
            let mut output = result.clone();
            let mut output2: String = output.into_iter().map(|i| i.to_string()).collect::<String>();
            output2.remove(0);
            println!("{}",output2);
            io::stdout().flush();
            let mut line2 = String::new();            
            match io::stdin().read_line(&mut line2){
                Ok(_) => {
                    if line2 != "Y" {
                        //process::exit(1);
                        return;
                    }       
                },
                Err(e) => process::exit(1)
            }  
        }
        nr = nr+2;
    }
}

fn query(i: i32) -> i32{
    
    let mut line = String::new();  
    let mut input = 0;
    println!("{}",i);//aqui
    io::stdout().flush();
    match io::stdin().read_line(&mut line){
      Ok(_) => {
                          
      },
      Err(e) =>process::exit(1)
    }  
    io::stdout().flush();
    let mut split = line.split_whitespace();  
    let vec = split.collect::<Vec<&str>>();
    input = vec[0].parse::<i32>().unwrap();
   // println!("return del query {}", input);
    return input;

}