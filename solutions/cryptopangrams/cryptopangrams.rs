use std::collections::HashMap; //to use as dictionaries
use std::io::*;

//////////////////////////////////////////////////////////////////////////////

fn cryptopangram(_n: i128, _l: i128, integers: Vec<i128>) -> String
{
    // define alphabet
    let alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G','H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

    let l = integers.len();
    // create a list for the ciphered primes and empty message
    let mut primes = vec![-1;l+1];
    let mut message = String::new();

    // calculate the gcd of the two first non equal ciphered primes
    let mut index = 0;
    for i in 0 .. (integers.len() - 1) {
        if integers[i] != integers[i+1] {
            let factor = gcd(integers[i], integers[i+1]);
            index = i+1;
            primes[index] = factor;
            break;
        }
    }

    // calculates backpropagation
    for i in (1 .. index+1).rev() {
        let prev_factor = integers[i-1] / primes[i];
        primes[i-1] = prev_factor;
    }

    // calculates frontpropagation
    for i in index .. integers.len() {
        let next_factor = integers[i] / primes[i];
        primes[i+1] = next_factor;
    }

    // remove repetitions. copy into new list and sort it
    let mut unique_primes_sorted = primes.to_vec(); //copies vector
    unique_primes_sorted.sort(); //sorts vector
    unique_primes_sorted.dedup(); //removes repetitions

    // map the sorted list to each letter in a dictionary
    let mut prime_map: HashMap<i128, char> = HashMap::new();
    for i in 0 .. unique_primes_sorted.len() {
        prime_map.insert(unique_primes_sorted[i], alphabet[i]);
    }

    for prime in primes.iter() {
        message = message + &prime_map[prime].to_string();
    }


    message
}

//////////////////////////////////////////////////////////////////////////////

fn gcd(mut m: i128, mut n: i128) -> i128
{
    while m != 0 {
        let old_m = m;
        m = n % m;
        n = old_m;
    }
    n.abs()
}

//////////////////////////////////////////////////////////////////////////////

fn test_cryptopangrams()
{
    let t: usize = read();
    for ti in 0..t {
        let input = read_vec::<i128>();
        let n = input[0];
        let l = input[1];
        let v: Vec<i128> = read_vec();
        let ans = cryptopangram(n, l, v);
        println!("Case #{}: {}", ti + 1, ans);
    }
}

//////////////////////////////////////////////////////////////////////////////

fn read<T: std::str::FromStr>() -> T
{
    let stdin = stdin();
    let mut buf = String::new();
	let _ = stdin.lock().read_line(&mut buf);
	buf.trim().parse().ok().unwrap()
}

//////////////////////////////////////////////////////////////////////////////

fn read_vec<T: std::str::FromStr>() -> Vec<T>
{
	read::<String>().trim().split_whitespace()
        .map(|w| w.parse().ok().unwrap()).collect()
}

//////////////////////////////////////////////////////////////////////////////

fn main()
{
    test_cryptopangrams();
}

//////////////////////////////////////////////////////////////////////////////

