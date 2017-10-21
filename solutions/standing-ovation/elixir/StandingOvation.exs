Code.require_file "FileManager.exs", __DIR__

defmodule StandingOvation do

  def getFileName do
    IO.gets("Enter test file name: ")
    |> getInput
  end

  def getInput(fileName) do
     fileName
     |> FileManager.readFile
  end

  def doOvation() do
    input = StandingOvation.getFileName

    {t, input} = input |> List.pop_at(0)
    t = t |> String.to_integer()

    fileToWrite =
    case t == length input do
      true -> getCases(input, [], 1)

      false -> IO.puts "Amount of test cases does not match!"
      exit(:shutdown)
    end

    FileManager.writeFile(fileToWrite)
  end

  def getCases(tests, fileToWrite, counter) do
    case !Enum.empty?(tests) do
      true -> {thisTest, tests} = tests |> List.pop_at(0)
      [s_max, attendants] = thisTest |> String.split
      [s_max, attendants] = [String.to_integer(s_max),(String.split(attendants, "" , parts: :infinity))]
      attendants = attendants |> List.delete_at((length attendants) - 1)

      willInvite = StandingOvation.willEvaluate(s_max, attendants)
      fileToWrite = fileToWrite ++ ["Case ##{counter}: #{willInvite}"]
      getCases(tests, fileToWrite, counter+1)

      false -> fileToWrite
    end

  end

  def willEvaluate(s_max, attendants) do
    case s_max+1 == length attendants do
      true -> evaluateCases(s_max, attendants, 0, 0, 0)  #Evaluate
      false -> 0 #Need 0 invites
    end
  end

  def evaluateCases(s_max, attendants, invited, applauding, counter) when counter < length attendants do
    lenghtOfAttendats = length attendants
    {invited, applauding} =
      case lenghtOfAttendats > 0 do
        true -> {temp_i, temp_a} =
          case applauding < counter do
            true -> {invited + counter - applauding, counter}
            _ -> {invited, applauding}
          end
          {temp_i,temp_a + (Enum.at(attendants,counter) |> String.to_integer()   )}
        _ -> {invited, applauding}
      end
      StandingOvation.evaluateCases(s_max, attendants, invited,applauding,counter+1)
  end

  def evaluateCases(s_max, attendants, invited, applauding, counter) do
   invited
  end

end

StandingOvation.doOvation
