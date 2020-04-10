
mul_table = {{0,  0,  0,  0,  0},
             {0,  1,  2,  3,  4},
             {0,  2, -1,  4, -3},
             {0,  3, -4, -1,  2},
             {0,  4,  3, -2, -1}}

table_ref = fn row, col ->
  elem(elem(mul_table, row), col)
end

mul = fn a, b ->
  sign = if (a * b) > 0 do 1 else -1 end
  sign * table_ref.(abs(a), abs(b))
end

exponentiate = fn value, times ->
  iterate = fn limit, power, recur ->
    case limit do
      0 -> power
      _ -> recur.(limit-1, mul.(power, value), recur)
    end
  end
  iterate.(rem(times, 4), 1, iterate)
end

reduce = fn codes, times ->
  iterate = fn codes, value, recur ->
    case codes do
      [] -> exponentiate.(value, times)
      [x | xs] -> recur.(xs, mul.(value, x), recur)
    end
  end
  iterate.(codes, 1, iterate)
end

viable_split? = fn ocodes, times ->
  iterate = fn codes, times, maybe2, maybe3, recur ->
    cond do
      0 == times -> (2 == maybe2 and 3 == maybe3)
      0 == length(codes) -> recur.(ocodes, times-1, maybe2, maybe3, recur)
      2 != maybe2 -> recur.(tl(codes), times, mul.(maybe2, hd(codes)), maybe3, recur)
      3 != maybe3 -> recur.(tl(codes), times, maybe2, mul.(maybe3, hd(codes)), recur)
      true -> true
    end
  end
  iterate.(ocodes, times, 1, 1, iterate)
end

{:ok, input} = File.open("./inputs/C-large-practice.in", [:read])
{:ok, output} = File.open("./elixir-output-large.out", [:write])

test_cases = elem(Integer.parse(hd(String.split(IO.read(input, :line)))), 0)

map_recur = fn
  (_, [], _) -> []
  (f, [x | xs], recur) -> [f.(x) | recur.(f, xs, recur)]
end

map = fn(f, xs) ->
  map_recur.(f, xs, map_recur)
end

iterate = fn test, recur ->
  metastr = IO.read(input, :line)
  codestr = IO.read(input, :line)
  if metastr != :eof and codestr != :eof do
    [l, x] = for str <- String.split(metastr), do: elem(Integer.parse(str), 0)
    i = map.(fn ch -> ch - 105 + 2 end, to_charlist(hd(String.split(codestr))))
    test1 = reduce.(i, x) == -1
    test2 = viable_split?.(i, min(8, x))
    IO.puts(output, "Case ##{test}: #{if test1 and test2 do "YES" else "NO" end}")
    recur.(test+1, recur)
  end
end

iterate.(1, iterate)
