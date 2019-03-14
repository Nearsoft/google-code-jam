# To import this code use
# Code.require_file "FileManager.exs", __DIR__
# at the beging of file

defmodule FileManager do

  def readFile (file)do
    case File.read(file |> String.trim ) do
      {:ok, body}      -> body |> toList #do something with the body
      {:error, reason} -> IO.puts "Failed to open file, error: #{reason}; exiting"
      exit(:shutdown)# handle the error caused by `reason`
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

  def writeFile(list) do
    case is_list(list) do

    true ->
    stringToWrite = list |> Enum.join("\n")

    case File.write("results.txt", stringToWrite)  do
      :ok -> case File.cwd()  do
        {:ok, cwd} -> IO.puts "Saved results to #{cwd}/results.txt"
        {:error, reason} -> IO.puts "Failed to save. Error #{reason}"
      end

      {:error, reason} ->IO.puts "Failed to save. Error #{reason}"
    end
    false -> IO.puts "Cannot write file, did you pass a list?"
  end
  end
end
