import java.util.Scanner

def ask(a, b, c, in){
    System.out.println(a+" "+b+" "+c)
    System.out.flush()

    return in.nextInt()
}

def is_right(arr, in){
    System.out.println(arr.join(" "))
    System.out.flush()

    return in.nextInt() == 1
}

def median_sort(n, q, in){
    //Initial list
    def numbers = [1,2]

    for(int i = 3; i < (n+1); i++){
        int l = 0
        int r = numbers.size() - 1

        while(((r-l) >= 1) && (q > 0)){
            //Ternary Search
            int lp = l + ((r-l).intdiv(3))
            int rp = r - ((r-l).intdiv(3))

            def median = ask(numbers[lp], numbers[rp], i, in)

            q--

            if (median == numbers[lp]) {
                r = lp - 1
                if (l == r) {
                    r++     
                } 
            }else if (median == numbers[rp]) {
                l = rp + 1
                if (l == r) {
                    l--
                }
            }else{
                l = lp + 1
                r = rp - 1
                if (l == r) {
                    l--
                }
            }

        }
        numbers = numbers.plus(l, i)
    }
    return is_right(numbers, in)
} 

def main() {
    Scanner input = new Scanner(System.in)
    int T = input.nextInt() //T is for number of lists
    int N = input.nextInt() //N is for Size of each list 
    int Q = input.nextInt() //Q is for Max number fo querys

    //Iterate T times (Tests)
    while(T-- != 0) {
        if (!median_sort(N, Q, input)) {
            return
        }
    }
}

main()