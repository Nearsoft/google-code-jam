
class Example {

    /*ef mCostos = []
    def posicion = []
    def costoSobrante=costo-(numero-2)
    print costoSobrante

    for(int i=0; i<numero; i++){
        posicion.add(i)
    }

    for(int i=0;i<numero;i++){
        if(costoSobrante>=numero-i){
            mCostos.add((numero-i)-1)
            costoSobrante-=(numero-1)
        }else{
            mCostos.add(0)
        }
    }*/

    static void resolver(int costo, int numero){
        def remaincost = costo
        def pago = []
        for (int i =numero; i>1; i--){
            def minimo = i-3
            def tryval = i
            while(remaincost - tryval <= minimo){
                tryval--
            }
            remaincost -= tryval
            pago.add(tryval)
        }
        print pago
        def index = []
        def listOfValues = []

        for (int i=1; i<=numero; i++){
            index.add(i-1)
            listOfValues.add(i)
        }
        
        for(int i=0; i<pago.size()-1; i++){
            reversort(index, i, pago[i]);
        }
        print index

        def solucion = [""] * numero
        for (int i=0; i<numero; i++){
            solucion.add(index.get(i), listOfValues(i))
        }
        print index
        print listOfValues
    }

    public static void reversort(ArrayList elements, int begin, int counter){ 
        for(int i = 0; i < counter / 2; i++){
            int tmp = elements[i + begin] //save the 1st element 
            //value of the 1st element gets swap to the last
            elements[i + begin] = elements[counter - i - 1 + begin] 
            //last value of the element gets swap to 1st
            elements[counter - i - 1 + begin] = tmp; 
        }
    }

    static void main(String[] args) {
    /*print 'numero'
    def numero = System.in.newReader().readLine() as Integer
    print 'costo'
    def costo = System.in.newReader().readLine() as Integer

    if(costo>=numero-1&&costo<=(numero*(numero+1)/2)-1){
        for (int i = 0; i<numero-1; i++){
            
        }
    }else{
        print 'IMPOSSIBLE'
    }
    print mCostos
    //print costoS*/

    resolver(6,4);
    }
}