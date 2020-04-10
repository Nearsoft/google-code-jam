use std::collections::HashMap; //to use as dictionaries

fn cryptopangram(n: i32, l: i32, integers: Vec<i32>) -> String
{
    // define alphabet
    let alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G','H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

    let l = integers.len();
    // create a list for the ciphered primes and empty message
    let mut primes = vec![-1;l+1];
    let mut message = String::new();

    // calculate the gcd of the two first non equal ciphered primes
    let mut index = 0;
    for i in 0..(integers.len() - 1) {
        if integers[i] != integers[i+1] {
            let factor = gcd(integers[i], integers[i+1]);
            index = i+1;
            primes[index] = factor;
            break;
        }
    }

    // calculates backpropagation
    for i in (1..index+1).rev() {
        let prev_factor = integers[i-1] / primes[i];
        primes[i-1] = prev_factor;
    }

    // calculates frontpropagation
    for i in index..integers.len() {
        let next_factor = integers[i] / primes[i];
        primes[i+1] = next_factor;
    }

    // remove repetitions. copy into new list and sort it
    let mut unique_primes_sorted = primes.to_vec(); //copies vector
    unique_primes_sorted.sort(); //sorts vector
    unique_primes_sorted.dedup(); //removes repetitions

    // map the sorted list to each letter in a dictionary
    let mut prime_map: HashMap<i32, char> = HashMap::new();
    for i in 0 .. unique_primes_sorted.len() {
        prime_map.insert(unique_primes_sorted[i], alphabet[i]);
    }

    for prime in primes.iter() {
        message = message + &prime_map[prime].to_string();
    }


    message
}

fn gcd(mut m: i32, mut n: i32) -> i32
{
    while m != 0 {
        let old_m = m;
        m = n % m;
        n = old_m;
    }
    n.abs()
}

fn test_cryptopangrams()
{
    // reads first line, the number of cases
    //n_cases = ???

    // iterate the number of cases to test
    //for i in 1..n_cases {
        // read line with N and L
        //line = ???
        // read line with list of integers
        //line2 = ???

        // turn line with N and L to array of numbers
        //n = !!!
        //l = !!!

        // turn line with integers into array of numbers
        //integers = ###

        //let message = cryptopangram(n, l, integers.to_vec());
        //println!("Case #{}: {}",i, message);
    //}
}

fn main()
{
    let n = 103;
    let l = 31;
    let integers = [217, 1891, 4819, 2291, 2987, 3811, 1739, 2491, 4717, 445, 65, 1079, 8383, 5353, 901, 187, 649, 1003, 697, 3239, 7663, 291, 123, 779, 1007, 3551, 1943, 2117, 1679, 989, 3053];

    let message = cryptopangram(n, l, integers.to_vec());

    println!("Case #1: {}", message);

    //when test_cryptopangrams is ready, delete the above and uncomment the next line
    //test_cryptopangrams()
}

