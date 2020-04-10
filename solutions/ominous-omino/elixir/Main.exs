Code.require_file "FileManager.exs", __DIR__

defmodule Mainominous do
    #Call the FileManager for choose a file
    def getFileName do
        IO.gets("Enter test file name: ")
        |> getInput
    end

    #Call the File Manager for choose a name of file and read
    def getInput(fileName) do
         fileName
        |> FileManager.readFile
    end

    #Call to evaluate the input and split the large string into litle content of the string
    def evaluate_input do

        input = Mainominous.getFileName
        {t, input} = input |> List.pop_at(0)
        t = t |> String.to_integer()
        result = ""
        i = 0

        if t == length(input) do
            input = Enum.chunk_every(input, 2)
            Enum.each input, fn(testCase) ->
                i = i+1
                l = testCase 
                |> Enum.at(0) 
                |> String.split(" ") 
                |> Enum.at(0) 
                |> String.to_integer
        end
        FileManager.writeFile(result)
    end

    #Evaluate the case for Gabriel
    def evaluate_case(testCase, counter) do
        winner = "GABRIEL"
        area = 0 
        #check the case when go to the repeat and when is doesn't
        case !testCase == ""  do
          true -> cond do
            counter < 3 && String.at(testCase, 0) == String.at(weExpect,counter) ->
            Mainominous.evaluate_case(String.slice(testCase, 1..-1),counter + 1)
            (length testCase) >= 2 ->
            if (length testCase) == 2 && String.at(testCase,0) == "-"  do
                "No"
            end
              xy = Mainominous.getFirstElements(testCase)
              Mainominous.evaluate_case(String.replace_prefix(testCase, xy, counter)
          true -> # I think that missed a case WIP
            if testCase == "1" do
              Mainominous.evaluate_case("", counter)
            end
          end
            _ -> if counter == 3 && testCase == "" do
                "YES"
              else
                "NO"
              end
          end
        end
    end

    def getFirstElements(value) do
        case String.slice(value, 0..0) == "-" do
            true -> String.slice(value, 0..2)
            false -> String.slice(value, 0..1)
        end
    end

    #The method who say if the xnomino is in the are for some filters
    def perfectPieceExist(xomino, rows, columns) do
      length = xomino
      height = 1
      result = false

      #pecial case for omino 4 that always block the area to be filled
      if xomino == 4 do
        if rows == 2 || columns == 2 do
          result = true
        end
      end  
      #special case for omino 5 that always block the area to be filled
      if xomino == 5 do
        if rows == 2 || columns == 2 || rows == 3 && columns == 5 || rows == 5 && columns == 3 do
          result = true
        end
      end
      #special case for omino 6 that always block area to be failled
      if xomino == 6 do
        if rows == 2 || columns == 2 || rows == 3 || columns == 3 do
          result true
        end
      end
      for height..length do
        if length <= rows && height <= columns || height <= rows && length >= columns do
          length--
          height++
        else
          result true
        end
      end
      result = false
    end


end
#the first call to the program
Mainominous.evaluate_input