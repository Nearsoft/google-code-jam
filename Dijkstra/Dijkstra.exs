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
        _ = numberOfCase
        _ = l
        IO.puts word
        
        # Cosas de evaluacion de las cadenas
        
        
    end
end

defmodule Quaternion do
    def mult(a, b) do
       indx = String.to_atom(a)
       indy = String.to_atom(b)
       
       mult = %{:"1" => %{:"1" => "1", :"i" => "i", :"j" => "j", :"k" => "k", :"-1" => "-1", :"-i" => "-i", :"-j" => "-j", :"-k" => "-k"},
                :"i" => %{:"1" => "i", :"i" => "-1", :"j" => "k", :"k" => "-j", :"-1" => "-i", :"-i" => "1", :"-j" => "-k", :"-k" => "j"},
                :"j" => %{:"1" => "j", :"i" => "-k", :"j" => "-1", :"k" => "i", :"-1" => "-j", :"-i" => "k", :"-j" => "1", :"-k" => "-i"},
                :"k" => %{:"1" => "k", :"i" => "j", :"j" => "-i", :"k" => "-1", :"-1" => "-k", :"-i" => "-j", :"-j" => "i", :"-k" => "1"},
                :"-1" => %{:"1" => "-1", :"i" => "-i", :"j" => "-j", :"k" => "-k", :"-1" => "1", :"-i" => "i", :"-j" => "j", :"-k" => "k"},
                :"-i" => %{:"1" => "-i", :"i" => "1", :"j" => "-k", :"k" => "j", :"-1" => "i", :"-i" => "-1", :"-j" => "k", :"-k" => "-j"},
                :"-j" => %{:"1" => "-j", :"i" => "k", :"j" => "1", :"k" => "-i", :"-1" => "j", :"-i" => "-k", :"-j" => "-1", :"-k" => "i"},
                :"-k" => %{:"1" => "-k", :"i" => "-j", :"j" => "i", :"k" => "1", :"-1" => "k", :"-i" => "j", :"-j" => "-i", :"-k" => "-1"}}
    
        pre = mult[indx]
        pre[indy]
    end
    
    def reduceWordTo1(word) do
        
        if String.length(word) > 1 do
            h = String.at(word, 0)
            t = String.slice(word, 1..-1) 0r

            mult(h, reduceWordTo1(t))
        else 
            word
        end
    end

    def seekijk(word) do
        word
    end
end

##Recogemos los datos de usuario
#{tests, _} = Integer.parse(IO.gets "hom many times? ")
#
## Corremos la funcion tests veces 
#Dijkstra.run_test(1, tests)


IO.puts Quaternion.seekijk("ijk")

