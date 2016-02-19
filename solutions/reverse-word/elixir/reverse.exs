raw = File.read! "../tests/B-small-practice.in"

list = String.split(raw, "\n")
list_wthoNo = List.delete_at(list, 0)

list_wthoNo |> Enum.with_index |> Enum.each( fn {n, i} ->
  words = Enum.reverse(String.split(n, " "))
  IO.puts "Case #"<>Integer.to_string(i+1)<>": "<>Enum.join(words, " ")
end)