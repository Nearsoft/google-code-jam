defmodule Ominoes do

    def omino_game(index,x,r,c) do
    IO.puts "Before firstIndicator"
    firstIndicator = 7

    multiplicationResult = r * c

    secondIndicator = rem(multiplicationResult, x)

    thirdIndicator = x

    fourthIndicator = 0

    if rem(x,2) == 0 do
     fourthIndicator = x / 2
    else
     fourthIndicator = (x/2) + 1
    end

    winner = "Richard"

    if x < firstIndicator do
     if secondIndicator == 0 do
       if r >= thirdIndicator || c >= thirdIndicator do
        if r >= thirdIndicator do
        if c >= fourthIndicator do
            winner = "Gabriel"
        end
        if c >= thirdIndicator do
            winner = "Gabriel"
        end
        end
       end
     end
    end
    File.write("output.txt", "Case ##{index}: #{winner}\n", [:append])
    IO.puts "winner #{winner}"
    end

end



  File.stream!("D-large-practice.in")
  |> Stream.map(&String.strip/1)
  |> Stream.with_index
  |> Stream.map(fn ({line, index}) ->
    if index != 0 do
      list_of_inputs = String.split(line)
      xx = String.to_integer(Enum.at(list_of_inputs, 0))
      rr = String.to_integer(Enum.at(list_of_inputs, 1))
      cc = String.to_integer(Enum.at(list_of_inputs, 2))
      Ominoes.omino_game(index,xx,rr,cc)
    end

  end)
  |> Stream.run

