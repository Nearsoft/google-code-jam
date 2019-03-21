defmodule Omino do

    def divide(x, grid) do
        rem(grid, x) === 0
    end

    def getWinner(x, r, c) do

        s = min(r, c)
        l = max(r, c)

        cond do
            not divide(x, s*l) -> "RICHARD"
            x === 3 and s === 1 -> "RICHARD"
            x === 4 and s <= 2 -> "RICHARD"
            x === 5 and ( (s <= 2) || (s === 3 and l === 5) ) -> "RICHARD"
            x === 6 and s <= 3 -> "RICHARD"
            x >= 7 -> "RICHARD"
            true -> "GABRIEL"
        end


    end

    def getWinnerLoop(times, total) when times<=1 do
        input = IO.gets ""

        xOmino = hd Tuple.to_list((Integer.parse hd String.split(input, " ")))
        rGrid = hd Tuple.to_list(( Integer.parse hd(tl(String.split(input, " "))) ))
        cGrid = hd Tuple.to_list( Integer.parse(hd tl tl String.split(input)) )

        IO.puts "Case ##{(total - times) + 1}: #{getWinner(xOmino, rGrid, cGrid)}"
    end

    def getWinnerLoop(times, total) do
        input = IO.gets ""

        xOmino = hd Tuple.to_list((Integer.parse hd String.split(input, " ")))
        rGrid = hd Tuple.to_list(( Integer.parse hd(tl(String.split(input, " "))) ))
        cGrid = hd Tuple.to_list(Integer.parse(hd tl tl String.split(input, " ")))

        IO.puts "Case ##{(total - times) + 1}: #{getWinner(xOmino, rGrid, cGrid)}"
        getWinnerLoop(times - 1, total)
    end

end

times = IO.gets ""
timesConverted = elem(Integer.parse(times), 0)

Omino.getWinnerLoop(timesConverted, timesConverted)
