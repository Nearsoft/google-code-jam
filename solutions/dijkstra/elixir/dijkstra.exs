
Code.require_file "FileManager.exs", __DIR__

defmodule Dijkstra do
  @multiplicationMatrix %{"11"=>"1","1i"=>"i","1j"=>"j","1k"=>"k",
                         "i1"=>"i","ii"=>"-1","ij"=>"k","ik"=>"-j",
                         "j1"=>"j","ji"=>"-k","jj"=>"-1","jk"=>"i",
                         "k1"=>"k","ki"=>"j","kj"=>"-i","kk"=>"-1"}

  def getFileName do
    IO.gets("Enter test file name: ")
    |> getInput
  end

  def getInput(fileName) do
     fileName
     |> FileManager.readFile
  end

  def hola do
    input = Dijkstra.getFileName
    IO.puts(input)
  end

  def evaluate_input do
    input = Dijkstra.getFileName

    {t, input} = input |> List.pop_at(0)
    t = t |> String.to_integer()
    result = []
    i = 0
    if t == length(input)/2 do
      input = Enum.chunk_every(input, 2)
      Enum.each input, fn(testCase) ->
        i = i+1
        IO.puts(i)
        l = testCase |> Enum.at(0) |> String.split(" ") |> Enum.at(0) |> String.to_integer
        x = testCase |> Enum.at(0) |> String.split(" ") |> Enum.at(1) |> String.to_integer
        IO.puts(Enum.at(testCase, 1))
        if l == String.length(Enum.at(testCase, 1)) do
          result= result ++ ["Case ##{i}: #{Dijkstra.dijkstra(Dijkstra.getLX(Enum.at(testCase,1),x),0)}\n"]
          IO.puts(result)
        else
          IO.puts "Error: the test case is not the size that was given"
        end
      end
    else
      IO.puts "Error: The number of test cases given is wrong"
    end

    FileManager.writeFile(result)
  end

  def getLX(l,x) do
    if x > 24 do
      x = rem(x,4)+24
    end
    lx = String.duplicate(l, x)
  end

  def dijkstra(testCase, counter) do
  weExpect = "ijk"
  case !testCase == ""  do
    true -> cond do
          counter < 3 && String.at(testCase, 0) == String.at(weExpect,counter) ->
            Dijkstra.dijkstra(String.slice(testCase, 1..-1),counter + 1)
          (length testCase) >= 2 ->
              if (length testCase) == 2 && String.at(testCase,0) == "-"  do
                "No"
              end
              xy = Dijkstra.getFirstElements(testCase)
              Dijkstra.dijkstra(String.replace_prefix(testCase, xy, Dijkstra.getMultiplication(xy)), counter)
          true ->
            if testCase == "1" do
              Dijkstra.dijkstra("", counter)
            end
          end
        _ -> if counter == 3 && testCase == "" do
              "YES"
            else
              "NO"
            end
    end
  end

  def getFirstElements(value) do
    case String.slice(value, 0..0) == "-" do
      true -> String.slice(value, 0..2)
      false -> String.slice(value, 0..1)
    end
  end

  def getMultiplication(xy) do
    negative = false
    if String.length(xy) == 3 do
      xy = String.slice(xy, 1..2)
      negative = false
    end
    res = @multiplicationMatrix[xy]
    case negative do
      true -> negate(res)
      false -> res
    end
  end

  def negate(value) do
    case String.slice(value, 0..0) == "-" do
      true -> String.slice(value, 1..-1)
      false -> "-"<>value
    end
  end


end

Dijkstra.evaluate_input
