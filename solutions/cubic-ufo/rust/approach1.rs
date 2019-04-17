use std::io;
use std::f64;
use std::str::FromStr;

fn main() {
    println!("cubicUFO");

    println!("Input test cases.");

    let mut case = String::new();

    io::stdin().read_line(&mut case)
        .expect("Failed to read line"); //read input for cases

    case = case.to_string();
    let cases: Vec<&str> = case.split("\n").collect();
    let case_test = u32::from_str(cases[0]).unwrap(); //convert to integer

    for n in 1..=case_test {
        println!("\nCase #{}", n);

        println!("Input area for case #{}", n);
        let mut area_in = String::new();
        io::stdin().read_line(&mut area_in)
            .expect("Failed to read line"); //read input for desiered area

        area_in = area_in.to_string();
        let mut new_area: Vec<&str> = area_in.split("\n").collect();
        let mut area = f64::from_str(new_area[0]).unwrap(); //convert to f64

        //rotation matrix
        if area <=  f64::consts::SQRT_2 {
            let mut ar = area.powf(2.0) - 1.0;
            let mut theta = ar.asin() / 2.0;
            println!("[ {}, {}, {} ]", 0.5, 0, 0);
            println!("[ {}, {}, {} ]", 0.0, 0.5*theta.cos(), 0.5*theta.sin());
            println!("[ {}, {}, {} ]", 0.0, -0.5*theta.sin(), 0.5*theta.cos());
        } else {
            let three_f64 = 3.0_f64;
            let two_f64 = 2.0_f64;
            let eight_f64 = 8.0_f64;
            let div = two_f64.sqrt() / three_f64.sqrt();
            let thi1 = area / three_f64.sqrt();
            let mut thi = thi1.asin() - div.asin();
            println!("[ {}, {}, {} ]", thi.cos()/2.0, thi.sin()/2.0, 0);
            println!("[ {}, {}, {} ]", -thi.sin() / eight_f64.sqrt(), thi.cos() / eight_f64.sqrt(), 1.0/eight_f64.sqrt());
            println!("[ {}, {}, {} ]", thi.sin() / eight_f64.sqrt(), -thi.cos() / eight_f64.sqrt(), 1.0/eight_f64.sqrt());
        }

    }
}