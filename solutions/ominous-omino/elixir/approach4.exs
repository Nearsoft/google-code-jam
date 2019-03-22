get_winner = fn x, r, c ->
  s = min(r, c)
  l = max(r, c)
  cond do
    rem(s*l, x) != 0 -> "RICHARD"
    x == 3 and s == 1 -> "RICHARD"
    x == 4 and s <= 2 -> "RICHARD"
    x == 5 and (s <= 2 or (s == 3 and l == 5)) -> "RICHARD"
    x == 6 and s <= 3 -> "RICHARD"
    x >= 7 -> "RICHARD"
    true -> "GABRIEL"
  end
end

{:ok, input} = File.open("./inputs/D-large-practice.in", [:read])
{:ok, output} = File.open("./elixir-output-large.out", [:write])

test_cases = elem(Integer.parse(hd(String.split(IO.read(input, :line)))), 0)

iterate = fn test, recur ->
  inputstr = IO.read(input, :line)
  if inputstr != :eof do
    [x, r, c] = for str <- String.split(inputstr), do: elem(Integer.parse(str), 0)
    winner = get_winner.(x, r, c)
    IO.puts(output, "Case ##{test}: #{winner}")
    recur.(test+1, recur)
  end
end

iterate.(1, iterate)
