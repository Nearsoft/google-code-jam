defmodule Ominoes do

  def omino_game(index,x,r,c) do
    IO.puts "Before firstIndicator"
    firstIndicator = 7

    multiplicationResult = r * c

    secondIndicator = rem(multiplicationResult, x)

    thirdIndicator = x

    fourthIndicator = 0

    if rem(x,2) == 0 do
      fourthIndicator = div(x, 2)
    else
      fourthIndicator = div(x, 2) + 1
    end

    fifthIndicator = thirdIndicator * fourthIndicator

    test1 = div(x, 2) + 1

    winner = "RICHARD"
    if x < firstIndicator do
      if secondIndicator == 0 do
        if r >= thirdIndicator || c >= thirdIndicator do
          if r >= thirdIndicator do
            if c >= fourthIndicator do
              if x > 3 do
                if r * c - fifthIndicator != 0 && rem(r * c - fifthIndicator, x) == 0 && c > (x / 2) do
                  winner = "GABRIEL"
                end
              else
                winner = "GABRIEL"
              end
            end
          end  
          if c >= thirdIndicator do
            if r >= fourthIndicator do
              if x > 3 do
                if (r * c - fifthIndicator) != 0 && rem(r * c - fifthIndicator, x) == 0 && r > x / 2 do
                  winner = "GABRIEL"
                end
              else
                winner = "GABRIEL"
              end
            end
          end
        end
      end
    end
    File.write("output.txt", "Case ##{index}: #{winner}\r\n", [:append])
    IO.puts "winner #{winner}"
  end
end
  File.stream!("D-large-practice.in")
  |> Stream.map(&String.trim/1)
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
