//Problem to solve: Moons and Umbrellas

class MoonsAndUmbrelas{
    static void main(String[] args) {
        //Define reader for the input
        def reader = new Scanner(System.in)
        //T = Number of cases
        def T = reader.nextInt()
        //Iteration through all the cases
        1.upto(T){ti->
            def X = reader.nextInt() //X = CJ Cost
            def Y = reader.nextInt() //Y = JC Cost
            def S = reader.nextLine()
            S = S.trim() //S = String to analyse
            //With dynamic programming
            def cCost = S.charAt(0) == "J"? Double.POSITIVE_INFINITY: 0
            def jCost = S.charAt(0) == "C"? Double.POSITIVE_INFINITY: 0
            for(int i = 1; i < S.length(); i++){
                def cCostNew = Double.POSITIVE_INFINITY
                def jCostNew = Double.POSITIVE_INFINITY
                if(S.charAt(i) == "C"){
                    cCostNew = Math.min(cCost, jCost + Y)
                }else if(S.charAt(i) =="J"){
                    jCostNew = Math.min(jCost, cCost + X)
                }else{
                    cCostNew = Math.min(cCost, jCost + Y)
                    jCostNew = Math.min(jCost, cCost + X)
                }
                cCost = cCostNew
                jCost = jCostNew
            }
            //Print the final result for the case
            println("Case #" + ti + ": " + (Math.min(cCost, jCost) as int) )
        }
    }
}