use std::io;
use std::collections::HashMap;
use std::vec;

// Function to read a line with multiple numbers
fn read_line() -> String{
    let mut temp_str = String::new();
    io::stdin()
        .read_line(&mut temp_str)
        .expect("Failed to read line");
    temp_str
}
// Function to read a line of a single number
fn read_num()-> usize {
    let num = read_line().trim().parse::<usize>().unwrap();
    num
}


fn main() {
    // Reads number of cases
    let cases = read_num();
    
    // Loop for every case
    for n in 0..cases{
        // Variables to be used
        let mut vestigium : i32 = 0;
        let mut repeated_rows : i32 = 0;
        let mut repeated_cols : i32 = 0;
        let size : usize = read_num() as usize;

        // Create a 2D Array filled with 0
        let mut matrix = vec![vec![0;size];size];
        
        // Fill the matrix with the values received
        for i in 0..size{
            let row = read_line();
            // Split the line by whithespaces to get individual number
            let row_split = row.split_whitespace(); 
            // Iterate over each number
            for (j,c) in row_split.enumerate(){
                matrix[i][j]= c.trim().parse::<i32>().unwrap();
            }
        }

        // check row and cols duplicates
        for i in 0..size{
            let mut row_occurrences = HashMap::<i32,bool>::new();
            let mut col_occurrences = HashMap::<i32,bool>::new();
            
            let mut row_dup = false;
            let mut col_dup = false;

            for j in 0..size{
                // vestigium sum
                if i == j{
                    vestigium += matrix[i][j];
                }
                // Cols ocurrences
                if !col_occurrences.contains_key(&matrix[j][i]){
                    col_occurrences.insert(matrix[j][i], true);
                }else{
                    col_dup = true;
                }

                // rows ocurrences
                if !row_occurrences.contains_key(&matrix[i][j]){
                    row_occurrences.insert(matrix[i][j], true);
                }else{
                    row_dup = true;
                }
            }
            // add to the counter
            if row_dup {
                repeated_rows += 1;
            }
            if col_dup {
                repeated_cols += 1;
            }
        }

    println!("Case #{}: {} {} {}", n+1, vestigium, repeated_rows, repeated_cols);
    }
}