Code.require_file "FileManager.exs", __DIR__

defmodule InfinitePancakes do

def infintePancakes do
  FileManager.readFile
  |> getNumberOfCases  #get tuple in the form {number_of_cases, cases}
  |> InfinitePancakes.validateNumberOfCases
  |> evaluateTestCases([], 1)
  |> FileManager.writeFile
end

def getNumberOfCases(input) do
  {number_of_cases, cases} = List.pop_at(input,0)
  {String.to_integer(number_of_cases), Enum.chunk_every(cases, 2)}
end

def evaluateTestCases({number_of_cases, cases}, file_to_write, current_test) do
  case number_of_cases != 0 do
    true -> {this_case, cases} = List.pop_at(cases,0)
    non_empty_plates = Enum.at(this_case,1)
    |> String.replace(" ","")
    |> String.to_integer
    |> Integer.digits
    file_to_write = file_to_write ++ InfinitePancakes.evaluateThisCase(non_empty_plates, findMaxPancakes(non_empty_plates), 0, current_test)
    InfinitePancakes.evaluateTestCases({number_of_cases-1, cases}, file_to_write, current_test+1)

    _ -> file_to_write
  end
end

def findMaxPancakes(non_empty_plates) do
  non_empty_plates
  |> Enum.sum
  |> :math.sqrt
  |> :math.floor
  |> round
end

def evaluateThisCase(non_empty_plates, max_pancakes, special_minutes, current_test) do
  case Enum.max(non_empty_plates) > max_pancakes  do
    true -> pancakes_to_change = Enum.max(non_empty_plates)
    List.delete(non_empty_plates,pancakes_to_change)
    |> List.insert_at(-1, max_pancakes)
    |> List.insert_at(-1, pancakes_to_change - max_pancakes)
    |> InfinitePancakes.evaluateThisCase(max_pancakes, special_minutes+1, current_test)

    _ -> ["Case ##{current_test}: #{special_minutes + Enum.max(non_empty_plates)}"]

  end
end

def validateNumberOfCases({number_of_cases, cases}) do
  case number_of_cases == length cases do
    true -> {number_of_cases, cases}
    _ -> IO.puts "Error:\nNumber of test cases given: #{number_of_cases} \nNumber of test cases found: #{length cases} \nExitin."
        exit(:shutdown)
  end
end


end

InfinitePancakes.infintePancakes
