defmodule M do

  def main do
    read("small-output.txt","B-small-practice.in")
    read("large-output.txt","B-large-practice.in")
    #algorithm("4 8 7 8 3")
  end

  def algorithm(pancakes) do
    #pancakes = "4"
    #get int array
    arr = String.split(pancakes," ")
    arr = Enum.map(arr, fn  x -> elem(Integer.parse(x), 0) end)
    max = Enum.max(arr)

    #for i..max
    firstFor = for i<- 1..max, do: i

    min = Enum.reduce(firstFor, 9000, fn(time,accmin) ->

      moves = Enum.reduce(arr,0,fn(p,accmoves) ->
        something = Float.ceil((p/time)-1)
        accmoves + something
      end)

      newmin = accmin
      if accmin>moves+time do
        #IO.puts "moves = #{moves}    time = #{time}    #{moves+time}"
        newmin = moves+time
      end

      newmin
    end) |> trunc

    #output in Acc
    min
  end


  def test(index,input,filename) do
    #IO.puts "Hello. I'm a test #{index} #{input}"
    s = "Case ##{index}: #{algorithm(input)}\n"
    IO.puts s
    File.write(filename, s, [:append])
  end


  def read(fileoutput, fileinput) do
    File.write(fileoutput, "")

    File.stream!(fileinput)
    |> Stream.map(&String.strip/1)
    |> Stream.with_index
    |> Stream.map(fn ({line, index}) ->
      if index != 0 and rem(index,2)==0 do
        input = line
        test(index/2 |> trunc,input,fileoutput)
      end

    end)
    |> Stream.run

  end

end
