//Problem to solve: Median Sort

//Function: getIndex
//Args: list: Actual list of ordered numbers
//      a: Number to place in the list
def getIndex(List list, int a){
    //Define reader for the input
    def reader = new Scanner(System.in)
    int i = Math.floorDiv(list.size(), 3) //Define j as 2/3 from the length of the list
    int j = Math.floorDiv(list.size() * 2, 3) //Define j as 2/3 from the length of the list
    int inicio = 0, fin = list.size() - 1 //Define beginning and end of the subarray
    while (true){
        println(list.get(i) + " " + a + " " + list.get(j)) //Print the 3 values to work with
        def res = reader.nextInt() //Read the answer for the median of the 3 values given
        if(res == -1) System.exit(-1) //Finish program if answer = -1
        if(res == a){                   //Case  [0].....[1/3].....[a].....[2/3].....[1]
            if(j - i == 1){
                return j
            }else{
                inicio = i
                fin = j
                j = inicio + Math.floorDiv((fin - inicio) * 2, 3)
                i = inicio + Math.floorDiv((fin - inicio), 3)
            }
        }else if( res == list.get(i)){  //Case  [0].....[a].....[1/3].....[2/3].....[1]
            if(i == 0){
                return 0
            }else if(j - i == 1){
                j = i
                i--
            }else{
                fin = i
                j = inicio + Math.floorDiv((i - inicio) * 2, 3)
                i = inicio + Math.floorDiv((i - inicio), 3)
            }
        }else{                          //Case  [0].....[1/3].....[2/3].....[a].....[1]
            if(j == list.size() - 1){
                return list.size()
            }else if(j - i == 1){
                i = j
                j++
            }else{
                inicio = j
                i = j + Math.floorDiv((fin - j), 3)
                j = j + Math.floorDiv((fin - j) * 2, 3)
            }
        }
        if(i == j) j++
    }
}

def reader = new Scanner(System.in) //Define reader for the input
def T = reader.nextInt() //Read T = Number of cases
def N = reader.nextInt() //Read N = Length of the list
def Q = reader.nextInt() //Read Q = Max number of queries
//Iteration through all the cases
1.upto(T) {ti->
    println("1 2 3") //Initial question
    def l = reader.nextInt()
    if(l == -1)System.exit(-1)
    def list = [] //Initial list
    if(l == 1) list = [2, 1, 3]
    else if(l == 2) list = [1, 2, 3]
    else list = [1, 3, 2]
    while(list.size() < N){ //Iterate every number until the length of the final list
        def nextElm = list.size() + 1
        def indx = getIndex(list, nextElm)
        list.add(indx, nextElm)
    }
    String res = ""
    for(int i = 0; i < list.size(); i++){
        res = res + list.get(i)
        if( i < list.size() -1)res = res + " "
    }
    println(res) //Print final result
    def isOk = reader.nextInt()
    if(isOk == -1) System.exit(-1) //If answer is -1, finish the program
}
