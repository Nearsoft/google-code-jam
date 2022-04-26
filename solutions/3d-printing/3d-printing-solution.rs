use std::io;

fn get_case_num()-> u32 { //Get the number of cases to read
    let mut test_cases = String::new();
    io::stdin().read_line(&mut test_cases).unwrap();
    let test_cases: u32 = test_cases.trim().parse().unwrap();
    return test_cases;
}

fn get_min_ink(printers:u32) -> [u32;4]{ //Get minimum ink level for each color (C,M,Y,K)
    let mut case = String::new();
    let mut i = 0;
        let mut min_ink: [u32;4]= [9999999,9999999,9999999,9999999];
        while i<printers{
            io::stdin().read_line(&mut case).unwrap();
            let ink: Vec<u32> = case.split_whitespace()
                                    .map(|x| x.parse::<u32>().unwrap())
                                    .collect();
            case.clear();
            i = i+1;
            for j in 0..4{
                if min_ink[j]>ink[j]{
                    min_ink[j]=ink[j];
                }
            }
        }
    return min_ink;
}

fn get_ink_level(t:u32,req_ink:u32,min_ink: &mut [u32;4]) -> String { //Get a possible ink combination for printing
    let mut sum: u32 = min_ink.iter().sum();
    if sum <req_ink{
        return format!("Case #{}: IMPOSSIBLE",t+1);
    }else{
        for i in 0..4{
            sum = 0;
            for j in (i+1)..4{
                sum = sum+min_ink[j];
            }
            if sum>req_ink{
                min_ink[i]=0;
            }else{
                min_ink[i] = req_ink - sum;
                break;
            }
        }
        return format!("Case #{}: {} {} {} {}",t+1,min_ink[0],min_ink[1],min_ink[2],min_ink[3]);
   }
}

fn main() {

    let case_lines = 3;
    let req_ink = 1000000;
    let test_cases = get_case_num();
    for t in 0..test_cases {
        let mut min_ink = get_min_ink(case_lines);
        let result = get_ink_level(t,req_ink,&mut min_ink);
        println!("{}",result);
    }
}