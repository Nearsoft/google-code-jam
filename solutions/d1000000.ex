dice = IO.gets("")
di= String.to_integer(dice)
_array = IO.read(:stdio, :line)
#array
#    |> String.split(" ")
#    |> Enum.map(fn n -> String.to_integer(n) end)
#    |> IO.inspect
cont=Enum.reduce(
    [6,10,12,8], # input
    {0},      # initial accumulator
    fn x, {ac} ->
   cond do
    x>di -> {ac+1}
  end
end)
IO.puts("Case #1:")
IO.inspect(cont)