defmodule FileManager do
  #Method wo read the file
  def readFile (file)do
    case File.read(file) do #in case one
      {:ok, body}      -> body |> toList #do something with the body
      {:error, reason} -> "Failed to open file, error: #{reason}"# handle the error caused by `reason`
    end
  end

  defp toList(string) do
    string
    |> String.trim(" ")
    |> String.split("\n")
    |> FileManager.deleteWhiteSpaces
  end

  def deleteWhiteSpaces(list) do
    case Enum.any?(list, &(&1 == ""))  do
      true -> FileManager.deleteWhiteSpaces(List.delete(list,""))
      false -> list
    end
  end

  #This method write in the file the content of the result
  def writeFile(list) do
    case is_list(list) do
      true -> stringToWrite = list |> Enum.join ("\n")
      case File.write("results.txt", stringToWrite)  do
        :ok -> case File.cwd()  do
            {:ok, cwd} -> "Saved results to #{cwd}/results.txt"
            {:error, reason} -> "Failed to save. Error #{reason}"
          end
        {:error, reason} -> "Failed to save. Error #{reason}"
      end
      false -> "Cannot write file, did you pass a list?"
    end
  end
end