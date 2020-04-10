defmodule Ovation do
  def standing() do
    {:ok, fin} = File.open("./A-large-practice.in", [:read])
    {:ok, fout} = File.open("./A-large-attempt1.out", [:write])

    t = IO.read(fin, :line) |> String.strip |> String.to_integer
    1..t
    |> Enum.each(fn(i)->
        str = IO.read(fin, :line) |> String.strip
        totalFriends = ovation(str)
        IO.puts(fout, "Case ##{i}: #{totalFriends}")
      end)

    File.close(fin)
    File.close(fout)
  end

  def ovation(str) do
    str = str |> String.split() |> Enum.at(1)
    len = str |> String.length
    {_, friends} = 0..(len-1)
    |> Enum.reduce({0, 0}, fn(i, {shynessLevel, standing})->
        curr = str |> String.at(i) |> String.to_integer
        if i > shynessLevel do
          standing = standing + (i-shynessLevel)
          shynessLevel = shynessLevel + (i-shynessLevel)
        end
        shynessLevel = shynessLevel + curr

        {shynessLevel, standing}
      end)
    friends
  end
end
