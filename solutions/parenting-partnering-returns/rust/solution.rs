use std:: io;
fn main(){
    let mut n_activities = String::new ();
    io:: stdin().read_line(& mut n_activities).expect("Not a valid string");
    let n_activities_num: i32 = n_activities.trim().parse().expect("Not a valid number");
    for i in 1..n_activities_num + 1 {
        let mut n = String::new ();
        io:: stdin().read_line(& mut n).expect("Not a valid string");
        let mut n: i32 = n.trim().parse().expect("Not a valid number");
        let mut vec: Vec<Activity> = Vec::new ();
        for x in 0..n {
            // let mut vec = Vec::<Activity>::with_capacity(n as usize);
            let mut input_string = String::new ();
            input_string.clear();
            io:: stdin().read_line(& mut input_string).unwrap();
            let mut split = input_string.split_whitespace();
            let mut aux = split.collect::<Vec<& str>>();
            let mut index: i32 = x;
			let mut startTime = aux[0].parse::<i32>().unwrap();
        	let mut endTime = aux[1].parse::<i32>().unwrap();
            let mut actv = Activity::new(index,startTime, endTime,);
            vec.insert(0 as usize, actv);
         }
        vec.sort_by(|a, b| a.endTime.cmp(&b.endTime));
       
        
        let mut parents = vec!["C","J"];
		let mut currentParent: i32 = 0;
		let mut finishTime: Vec<i32> = Vec::with_capacity(n as usize);
        let mut ans: Vec<String> = Vec::with_capacity(n as usize);
        
		for p in 0..n {
            finishTime.push(i);
			ans.push(i.to_string());
		}
		ans[vec[0].index as usize] = "C".to_string();
        finishTime[0] = vec[0].endTime;
        finishTime[1] = 0;
        currentParent = 0;
        
		let mut isPossible = true;
		for j in 1..n {
            let mut newPosition: i32 = j-1;
			let mut finish: i32 = vec[newPosition as usize].endTime;
            let mut start: i32 = vec[j as usize].startTime;
            
			if start >= finish {
               
                ans[vec[j as usize].index as usize] = parents[currentParent as usize].to_string();
				finishTime[currentParent as usize] = vec[j as usize].endTime;
			} else {
				if currentParent == 0 {
                    currentParent = 1;
				} else {
                    currentParent = 0;
                }
               
				if start >= finishTime[currentParent as usize] {
                    
                    ans[vec[j as usize].index as usize] = parents[currentParent as usize].to_string();
                    finishTime[currentParent as usize] = vec[j as usize].endTime;
				} else {
                    
                    isPossible = false;
					break;
				}
			}
		}
		let mut answerString: String = "".to_owned();
		if isPossible {
			for whoIs in ans {
                answerString.push_str(& whoIs);
			}
		}else {
            answerString = "IMPOSSIBLE".to_string();
		}
		println!("Case #{}{}{}", i, ": " , answerString);
  }
	struct Activity {
        index: i32,
		startTime: i32,
		endTime: i32
	}
	impl Activity {
        pub fn new(index: i32, startTime: i32, endTime: i32) -> Self {
            Activity {index, startTime, endTime}
        }   
    }
}
    