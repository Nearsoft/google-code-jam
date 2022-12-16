//Problem to solve: Reversort Engineering
class Reversort {
    static void main(String[] args) {
        //Define reader for the input
        def reader = new Scanner(System.in)
        //T = Number of cases
        def T = reader.nextInt()
        //Iteration through all the cases
        1.upto(T) { ti ->
            def N = reader.nextInt() //N = Size of the list
            def C = reader.nextInt() //C = Desired cost
            def minValue = N - 1; //Minimum cost of a list with size N: N-1
            def maxValue = ((N * (N+1))/2)-1; 
            ////Maximum cost of a list with size N: (N * (N - 1)) / 2 - 1
            if(C < minValue || C > maxValue){
                println("Case #"+ti+": IMPOSSIBLE") //IMPOSSIBLE case when C is not in range [N-1, (N * (N - 1)) / 2 - 1]
            }else{
                def arr = [] //List
                for(int i = 0; i < N; i++){
                    arr.add(i+1)
                }

                def maxArr = [] //List of size N with Max value of cost
                for(int i = 0; i < N/2; i++){
                    maxArr.putAt(i, (i+1)*2);
                }
                for(int i = N-1, m = 1; i > N/2 - 1; i--,m+=2){
                    maxArr.putAt(i,m);
                }
                String sArr = ""; //Final List
                switch(C) {
                    case minValue: //When C = N - 1
                            for(int i = 0; i < arr.size(); i++){
                                sArr = sArr + (i+1);
                                if(i < arr.size() - 1) sArr = sArr + " ";
                            }
                    break
                    case maxValue: //When C = (N * (N - 1)) / 2 - 1
                        for(int i = 0; i < maxArr.size(); i++){
                                sArr = sArr + maxArr.get(i);
                                if(i < maxArr.size() - 1) sArr = sArr + " ";
                            }
                    break
                    default:
                        //When C is in range (N-1, (N * (N - 1)) / 2 - 1)
                        int j, k = N - 1, cost = minValue + 1, f = 0;
                        def tArr = arr;
                        def fArr = [];
                        while(k>0){
                            j = k -1;
                            while(j > f-1){
                                fArr = [];
                                fArr.addAll(tArr.subList(0, f));
                                fArr.addAll(tArr.subList(f, j));
                                fArr.addAll(tArr.subList(j,k+1).reverse());
                                if (k + 1 < tArr.size())fArr.addAll(tArr.subList(k+1, tArr.size()))
                                if(cost == C){
                                    for(int i = 0; i < fArr.size(); i++){
                                        sArr = sArr + fArr.get(i);
                                        if(i < fArr.size() - 1) sArr = sArr + " ";
                                    }
                                    j = f -1;
                                    k = 0;
                                }
                                cost++;
                                j--;
                            }
                            if(fArr.get(f) == maxArr.get(f)){
                                f++;
                            }
                            if(fArr.get(k) == maxArr.get(k)){
                                k--;
                            }
                            tArr = fArr;
                        }
                }
                //Print final answer for the case
                println("Case #"+ti+": " + sArr)
            }
        }
    }

}
