use std::io;
use std::cmp;

fn read_info()-> usize { //Get single number from line
    let mut n = String::new();
    io::stdin().read_line(&mut n).unwrap();
    return n.trim().parse().unwrap();
}

fn get_line()-> Vec<usize>{
    let mut line = String::new();
    io::stdin().read_line(&mut line).unwrap();
    let mut a: Vec<usize> = vec![0];
    let mut result: Vec<usize> = line.split_whitespace()
                            .map(|x| x.parse::<usize>().unwrap())
                            .collect();
    a.append(&mut result);
    return a;
}

fn dfs(curr_mdr:& usize,md: &Vec<Vec<usize>>,fun: &Vec<usize>, ans: &mut usize)->usize{
    let curr_md = *curr_mdr;
    let mut children: Vec<usize> = vec![];
    let mut min_val = 1000000000;
    let mut sum = 0;
    for next_md in &md[curr_md]{
        let child = dfs(&next_md,&md,&fun,ans);
        children.push(child);
        min_val = cmp::min(min_val,child);
        sum += child;
    }
    if children.len()==0{
        return fun[curr_md];
    }
    *ans += sum - min_val;
    return cmp::max(fun[curr_md],min_val);
}

fn main(){
    
	let t = read_info();
    for c in 0..t{
        let n = read_info();
        let fun = get_line();
        let pos = get_line();
        let mut md: Vec<Vec<usize>> = vec![vec![];n+1];
        for i in 0..n{
            md[pos[i+1]].push(i+1);
        }
        let mut ans: usize = 0;
        ans += dfs(&0, &md, &fun,&mut ans);
        println!("Case #{}: {}",c+1,ans);
    }
}