defmodule Dijkstra do
    def run_test(start, finish) do
        if start < finish  do
            test(start)
            run_test(start + 1, finish)
        else
            test(start)
        end
    end

    def test(numberOfCase) do

        input1 = String.trim(IO.gets "L y X ")
        input2 = String.trim(IO.gets "el string ")

        [ll, xx] = String.split(input1, " ")
        {l, _} = Integer.parse(ll)
        {x, _} = Integer.parse(xx)
        word = String.duplicate(input2, x)

        IO.puts word
        
        # Cosas de evaluacion de las cadenas
        
        
    end
end

defmodule Quaternion do
   def mult(a, b) do
       if hd(a) 
        ["1", "i", "j", "k", "-1", "-i", "-j", "-k"]
      tabla =  [["1", "i", "j", "k", "-1", "-i", "-j", "-k"],
                ["i", "-1", "k", "-j", "-i", "1", "-k", "j"],
                ["j", "-k", "-1", "i", "-j", "k", "1", "-i"],
                ["k", "j", "-i", "-1", "-k", "-j", "i", "1"],
                ["-1", "-i", "-j", "-k", "1", "i", "j", "k"],
                ["-i", "1", "-k", "j", "i", "-1", "k", "-j"],
                ["-j", "k", "1", "-i", "j", "-k", "-1", "i"],
                ["-k", "-j", "i", "1", "k", "j", "-i", "-1"]]
       
       Enum.at(Enum.at(tabla, 0),0)

   end
end

#Recogemos los datos de usuario
{tests, _} = Integer.parse(IO.gets "hom many times? ")

# Corremos la funcion tests veces 
Dijkstra.run_test(1, tests)