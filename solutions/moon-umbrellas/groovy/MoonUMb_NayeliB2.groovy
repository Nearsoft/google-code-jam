
BufferedReader br = new BufferedReader(new InputStreamReader(System.in))

//Ask for the number of cases
int numT = Integer.parseInt(br.readLine())

for(int i = 0; i<numT; i++) {
    //Ask for the sentence with x, y and s values
    String sentece = br.readLine()
    String[] arr= sentece.split(" ")
    int x = Integer.parseInt(arr[0])
    int y = Integer.parseInt(arr[1])
    String mural = arr[2]
    moonAndUmbrellas_TO(x, y, mural, i)

}

void moonAndUmbrellas(int x, int y, String mural, int iterator){
    int contX = 0, contY = 0, sum = 0
    mural = mural.toLowerCase()

    if(x != 0 && y != 0){
        for(int i=0; i<mural.length()-1;i++){
            if(mural.charAt(i) == (char)'c' && mural.charAt(i+1) == (char)'j'){
                contX+=x
            }
            else if(mural.charAt(i) == (char)'j' && mural.charAt(i+1) == (char)'c'){
                contY+=y
            }
            else if(mural.charAt(i) == (char)'c' && mural.charAt(i+1) == (char)'?' ){
                mural = mural.substring(0,i+1)+"c"+mural.substring(i+2)

            }
            else if(mural.charAt(i) == (char)'j' && mural.charAt(i+1) == (char)'?' ){
                mural = mural.substring(0,i+1)+"j"+mural.substring(i+2)
            }
            else if(mural.charAt(i) == (char)'?' && mural.charAt(i+1) == (char)'c' ){
                mural = mural.substring(0,i)+"c"+mural.substring(i+1)
            }
            else if(mural.charAt(i) == (char)'?' && mural.charAt(i+1) == (char)'j' ){
                mural = mural.substring(0,i)+"j"+mural.substring(i+1)
            }
            sum = contX+contY
        }
    }
    else
    {
        println("Case #"+(iterator+1)+": "+0)
    }

    println("Case #"+(iterator+1)+": "+sum)
}

void moonAndUmbrellas_TernaryOperator(int x, int y, String mural, int iterator){
    int contX = 0, contY = 0, sum = 0
    mural = mural.toLowerCase()

    if(x != 0 && y != 0){
        for(int i=0; i<mural.length()-1;i++){
            //Does the summatory of x
            if(mural.charAt(i) == (char)'c' && mural.charAt(i+1) == (char)'j'){
                contX+=x
            }
            else if(mural.charAt(i) == (char)'j' && mural.charAt(i+1) == (char)'c'){
                //Does the summatory of y
                contY+=y
            }
            //Checks all the possible cases, and change the ? to the not convenient letter
            mural = (mural.charAt(i) == (char)'c' && mural.charAt(i+1) == (char)'?' ) ?
            mural.substring(0,i+1)+"c"+mural.substring(i+2) :
            (mural.charAt(i) == (char)'j' && mural.charAt(i+1) == (char)'?' ) ?
            mural.substring(0,i+1)+"j"+mural.substring(i+2) :
            (mural.charAt(i) == (char)'?' && mural.charAt(i+1) == (char)'c' ) ?
            mural.substring(0,i)+"c"+mural.substring(i+1) :
            (mural.charAt(i) == (char)'?' && mural.charAt(i+1) == (char)'j' ) ?
            mural.substring(0,i)+"j"+mural.substring(i+1) : mural

            sum = contX+contY

        }
    }
    else
    {
        println("Case #"+(iterator+1)+": "+0)
    }

    println("Case #"+(iterator+1)+": "+sum)
}

void moonAndUmbrellas_TO(int x, int y, String mural, int iterator){
    int contX = 0, contY = 0, sum = 0
    mural = mural.toLowerCase()

    int[][] array = new int[2][mural.length()]

    for(int i = 0; i<array.length; i++){
        for(int j = 0; j <array[i].length; j++){
            array[i][j] = 100000
        }
    }

    if(mural.charAt(0) != (char)'c' ){
        array[1][0] = 0
    }
    if(mural.charAt(0) != (char)'j' ){
        array[0][0] = 0
    }

    if(x != 0 && y != 0){
        for(int i=1; i<mural.length();i++){

            if(mural.charAt(i) != (char)'c'){
                array[1][i] = Math.min(array[1][i-1],array[0][i-1]+x)
            }
            if(mural.charAt(i) != (char)'j'){
                array[0][i] = Math.min(array[0][i-1],array[1][i-1]+y)
            }

        }
        suma = Math.min(array[0][mural.length()-1], array[1][mural.length()-1])

    }
    else
    {
        println("Case #"+(iterator+1)+": "+0)
    }

    println("Case #"+(iterator+1)+": "+Math.min(array[0][mural.length()-1], array[1][mural.length()-1]))
}