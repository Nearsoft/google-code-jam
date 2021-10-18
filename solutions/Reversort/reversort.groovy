class Reversort{
    //search minimum number position in elements array
    public static int findMinI(int[] elements, int begin){
        int index = begin; 
        for(int i = begin; i < elements.length; i++){
            if(elements[i] < elements[index]){
                index = i; 
            }
        }
        return index
    }
    

    //swap elements of the array
    public static void reversort(int[] elements, int begin, int counter){ 
        for(int i = 0; i < counter / 2; i++){
            int tmp = elements[i + begin] //save the 1st element 
            //value of the 1st element gets swap to the last
            elements[i + begin] = elements[counter - i - 1 + begin] 
            //last value of the element gets swap to 1st
            elements[counter - i - 1 + begin] = tmp; 
        }
    }
    //cost of each iteration
    public static int counter(int[] elements){
        int total = 0
        for(int i = 0; i < elements.length - 1; i++){
            int j = findMinI(elements, i)
            int reverseC = j - i + 1; 
            reversort(elements, i, reverseC)
            total += reverseC 
        }
        return total
    }
public static void main(String[] args){
    InputStream inpt = System.in
    try(Scanner scan = new Scanner(inpt)){ 
        int tstCounter  = scan.nextInt()
        for(int tstNumber = 1; tstNumber <= tstCounter; tstNumber++){
            int elementCount = scan.nextInt()
            int[] elements = new int[elementCount]
            for(int i=0; i < elementCount; i++){ 
                elements[i] = scan.nextInt()
            }
            int total = counter(elements)
            println "Case# ${tstNumber} :  ${total}"
        }
    }
}
}