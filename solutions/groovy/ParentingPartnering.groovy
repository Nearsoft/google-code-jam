//Problem to solve: Parenting Partnering Returns

//Function: isOverLap
//Args: a: List of activities from Jamie or Cameron
//      b: List of activities to do
def isOverlap(List a, List b){
    if(a.get(0) == -1) return false
    if ((b.get(0) >= a.get(0) && b.get(0) < a.get(1)) || (b.get(1) >= a.get(0) && b.get(1) < a.get(1))) return true
    return false
}

def reader = new Scanner(System.in) //Define reader for the input
def T = reader.nextInt() //Read T = Number of cases
//Iteration through all the cases
1.upto(T) {ti->
    def N = reader.nextInt() //Read N = Number of activities
    reader.nextLine()
    def list = [] //Define list of activities to sort
    def activities = [] //Define list of activities unsorted
    for(int i = 0; i < N; i++) activities.add(reader.nextLine().trim()) //Read all the activities
    for(int i = 0; i < N; i++){ //Pass the activities from String to Integer list
        String[] h = activities.get(i).split("\\s")
        def tempList = []
        for(int j = 0; j < h.length; j++) tempList.add(h[j] as int)
        list.add(tempList)
    }
    Collections.sort(list, (a, b)->{ //Sort the activities by start time
        return a.get(0) - b.get(0)
    });
    def cameron = [-1,-1] //Define list of activities for Cameron
    def jamie = [-1,-1] //Define list of activities for Jamie
    String res = ""
    def fin = false
    for(int i = 0; i < list.size(); i++){
        if(!isOverlap(cameron, list.get(i))){
            cameron = list.get(i)
            activities.set(activities.indexOf(list.get(i).get(0) + " " +list.get(i).get(1)), "C") //Replace with "C" in the unsorted list
        }else if(!isOverlap(jamie, list.get(i))){
            jamie = list.get(i)
            activities.set(activities.indexOf(list.get(i).get(0) + " " +list.get(i).get(1)), "J") //Replace with "J" in the unsorted list
        }else{
            println("Case #" + ti + ": IMPOSSIBLE")
            fin = true
            break;
        }
    }
    for(int i = 0; i < activities.size(); i++) res = res + activities.get(i)
    if(!fin) println("Case #" + ti + ": " + res) //Print the answer for the case
}