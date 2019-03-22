defmodule Audience do


  def ovation do

    {tests,_} = IO.gets("") |> Integer.parse
     for i <- 1..tests do
     chain = IO.gets("")
     chain = String.replace(chain, "\n","")
     chain = String.split(chain, " ")
     max_s = elem(Enum.at(chain, 0) |> Integer.parse,0)

     chain = Enum.at(chain, 1)
     chain = String.split(chain, "", trim: true)
     chain = Enum.map(chain,fn  x -> elem(Integer.parse(x), 0) end)


     array1 = 0..max_s
     arreglo = Enum.map_reduce(array1,0, fn(n, ac) -> {n-ac, ac + Enum.at(chain,n)} end)
     IO.puts(Enum.max(elem(arreglo,0)))

     t = 0
     min_invited = 0


     end

  end
  
end
audience = Audience
audience.ovation()