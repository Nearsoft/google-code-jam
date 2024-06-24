use std::io;

fn main() {
    let mut n_cases_string = String::new();
    io::stdin().read_line(&mut n_cases_string)
    .expect("Failed to read line");
    let n_cases: i32 = n_cases_string.trim().parse()
    .expect("Failed to covert to int");

    for i in 0..n_cases {
        let mut unnecessary = String::new();
        io::stdin().read_line(&mut unnecessary)
        .expect("Failed to read line");
        let mut path = String::new();
        io::stdin().read_line(&mut path)
        .expect("Failed to read line");

        let my_path = path.replace("E","X").replace("S","E").replace("X","S");

        print!("Case #{}: {}", i+1, my_path);
    }
}