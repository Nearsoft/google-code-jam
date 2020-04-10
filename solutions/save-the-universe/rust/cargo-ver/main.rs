extern crate regex;
use regex::Regex;
#[macro_use] extern crate text_io;

fn main() {
    let t: i32;
    loop{
        println!("Enter number of iterations");
        let tinp : String = read!();
        let re = Regex::new(r"\d+").unwrap();
        if re.is_match(tinp.as_str()){
            println!("T: {}", tinp);
            t = tinp.parse::<i32>().unwrap();;
            break;
        }
    }
    let mut results = vec![String::new()];
    let mut i = 0;
    loop{
        println!("\nCase #{}", i+1);
        let mut inp=String::new();
        loop{
            println!("Enter shield power and attack pattern:");
            inp = read!("{}\n");
            let re = Regex::new(r"\d+[ ][S|C]+").unwrap();
            if re.is_match(inp.as_str()) {
                break;
            }
        }
        let vec = inp.split(" ").collect::<Vec<&str>>();
        println!("Shield power: {}", vec[0]);
        println!("Attack pattern: {}", vec[1]);
        let d = vec[0].parse::<i32>().unwrap();
        let mut p: Vec<char> = vec[1].chars().collect();
        let mut m = 0;
        let mut ta = count_attack(p.clone());
        let mut possible = true;
        let p_len = p.len();
        println!("Pattern longitude: {}", p_len);
            'outer: loop{
            if ta<=d {
                break;
            }
            let mut temp1;
            let mut temp2;
            let mut ch_search = 1;
            loop{
                println!("ch_search: {}", ch_search);
                temp1 = p[p_len - ch_search];
                temp2 = p[p_len - ch_search - 1];
                ch_search += 1;
                if temp1 == 'S' && temp2 == 'C'{
                    ch_search -= 1;
                    break;
                }else if p_len - ch_search - 1 == 0 {
                    possible = false;
                    break 'outer;
                }
            }
            p[p_len - ch_search] = temp2;
            p[p_len - ch_search - 1] = temp1;
            m += 1;
            ta = count_attack(p.clone());
        }
        if possible {
            results.push(format!("Case #{}: {}", i+1, m));
        }else {
            results.push(format!("Case #{}: IMPOSSIBLE", i+1));
        }
        i += 1;
        if t <= i {
            break;
        }
    }
    println!("");
    for res in results {
    println!("{}", res);
    }
}

fn count_attack(p: Vec<char>) -> i32 {
    let mut ca = 1;
    let mut ta = 0;
    for a in p {
        if a == 'S' {
            ta += ca;
        }else{
            ca = ca*2;
        }
    }
    println!("Attack: {}", ta);
    return ta;
}