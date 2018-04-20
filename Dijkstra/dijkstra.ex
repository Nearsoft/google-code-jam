defmodule Dijkstra do
  @moduledoc false
  def main do
    read("../small-output.txt","../C-small-practice.in")
  end

  def algorithm(w,n) do
    answer = ""
    word = String.duplicate(w,n)
    arr = String.codepoints(word)
    arrIndex = arr |> Enum.with_index

    ijk = getValue(Enum.reduce(arrIndex,0,fn({x,i},acc)->
      acc = mult(acc,getIndex(x))
    end))

    if ijk != "-1" do
      answer = "NO"
    else

      x = Enum.reduce_while(arrIndex,0,fn({x,i},acc)->
        if i<length(arrIndex) and i>-1 do
          acc = mult(acc,getIndex(x))
          if acc == getIndex("i") do
            arrj = Enum.take(arr,i-length(arr)+1)
            arrIndexj = arrj |> Enum.with_index
            y = Enum.reduce_while(arrIndexj,0,fn({x,j},accj)->
              if j<length(arrIndexj) and j>-1 do
                accj = mult(accj,getIndex(x))
                if accj == getIndex("j") and ijk == "-1"do
                  {:halt,-1}
              else
                  {:cont,accj}
                end
              end
            end)

            if y>-1 do
              {:cont,acc}
            else
              {:halt,-1}
            end
          else
            {:cont, acc}
          end
        end
      end)

      if x == -1 do
        answer = "YES"
      else
        answer = "NO"
      end

    end
    answer
  end

  def test(w,n,index,filename) do
    s = "Case ##{index}: #{algorithm(w,n)}\n"
    IO.puts s
    File.write(filename, s, [:append])
  end

  def mult(a, b) do
    multTable =  [["1", "i", "j", "k", "-1", "-i", "-j", "-k"],
      ["i", "-1", "k", "-j", "-i", "1", "-k", "j"],
      ["j", "-k", "-1", "i", "-j", "k", "1", "-i"],
      ["k", "j", "-i", "-1", "-k", "-j", "i", "1"],
      ["-1", "-i", "-j", "-k", "1", "i", "j", "k"],
      ["-i", "1", "-k", "j", "i", "-1", "k", "-j"],
      ["-j", "k", "1", "-i", "j", "-k", "-1", "i"],
      ["-k", "-j", "i", "1", "k", "j", "-i", "-1"]]

    indexFound = Enum.at(Enum.at(multTable, a),b)
    getIndex(indexFound)
  end

  def getIndex(myvar) do
    arrIndex = ["1", "i", "j", "k", "-1", "-i", "-j", "-k"] |> Enum.with_index
    index = Enum.reduce(arrIndex,-1,fn({x,i},acc) ->
      if String.equivalent?(myvar, x) do
        acc = i
      end
      acc
    end)
    index
  end

  def getValue(index) do
    Enum.at(["1", "i", "j", "k", "-1", "-i", "-j", "-k"],index)
  end

  def read(fileoutput, fileinput) do
    File.write(fileoutput, "")
    x = File.stream!(fileinput)
        |> Stream.map(&String.strip/1)

    x = Enum.take(x,-200)
    IO.inspect Enum.chunk_every(x,2) |> Enum.with_index
               |> Stream.map(fn ({[l1,l2], index}) ->
      list_of_inputs = String.split(l1)
      n = String.to_integer(Enum.at(list_of_inputs, 1))
      test(l2,n,index+1,fileoutput)
    end) |> Stream.run
  end
end

Dijkstra.main()
