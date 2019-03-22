
defmodule Pancakes do
IO.puts("hola")
  def minutes do
    {tests,_} = IO.gets("") |> Integer.parse
    for i <- 1..tests do
      {non_empty,_} = IO.gets("") |> Integer.parse
      chain = IO.gets("")
      chain = String.replace(chain, "\n","")
      chain = String.split(chain, " ")
      chain = Enum.map(chain, fn  x -> elem(Integer.parse(x), 0) end)

      maximum = Enum.max(chain)
      result = maximum

      minimo = Enum.map(1..maximum+1, fn j ->  j + (Enum.map(chain, fn p -> (p-1)/j
               |> trunc  end)
               |> Enum.sum) end)
               |> Enum.min
      IO.puts(minimo)
    end


  end

end
pancakes = Pancakes
pancakes.minutes()