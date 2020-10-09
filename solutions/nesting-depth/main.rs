use std::io;

fn main() {
    //First, we declare our variable that determines how many
    //test cases we're going to have, we need it as a string
    let mut cicles = String::new();
    io::stdin()                         //We ask the standard input
        .read_line(&mut cicles)         //To read the line and assign it to "cicles"
        .expect("Failed to read line"); //This one catches errors in reading
    //We need cicles as an unsigned int, since it's a string.
    let cicles: u32 = cicles
        .trim()     //We remove the whitespace (spaces or end of line -> \n)
        .parse()    //And we parse it to an unsigned int
        .expect("Uhm, not a number sweetie");   //This is an error catcher

    //Ok, now, we cicle as many times as we asked, this is the main code.
    for x in 0..cicles{
        let mut nums = String::new();   //We need another String to read all the numbers
        io::stdin()
            .read_line(&mut nums)
            .expect("Failed to read line");
        //This turns the string into an iterable array of chars while removing whitespace
        let nums = nums.trim().chars(); 

        let mut current_count:u32 = 0;  //This will count the level of parenthesis we're in
        let mut resulting_string = String::new(); //This will be the final string, now empty

        //For each character in the integer string we do the following
        for num in nums {
            //If the current count is smaller than the number, we append a '(' to the string
            while current_count < num.to_digit(10).unwrap(){
                resulting_string.push('(');
                current_count += 1;
            }
            //If the current count is greater than the number, we append a ')' to the string
            while current_count > num.to_digit(10).unwrap(){
                resulting_string.push(')');
                current_count -= 1;
            }
            //We append the current number to the string
            resulting_string.push(num);
        }
        //This is to close any remaining parenthesis left open
        while current_count > 0{
            resulting_string.push(')');
            current_count -= 1;
        }
        //we print our result
        println!("Case #{}: {}",x+1,resulting_string);
    }
}