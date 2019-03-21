textr = File.read! "../tests/A-small-practice.in"
lines = String.replace(textr,"(","[") |> String.replace(")","]") |> String.split("\n")

params = Enum.fetch!(lines,0) |> String.split(" ")
l = String.to_integer(Enum.fetch!(params,0))
d = String.to_integer(Enum.fetch!(params,1))
n = String.to_integer(Enum.fetch!(params,2))

words = Enum.slice(lines, 1, d)
cases = Enum.slice(lines,d+1,n) |> Enum.with_index

result = Enum.map(cases, fn({x,i}) ->
	reg = Regex.compile!(x)
	n = Enum.count(words, fn x->
		Regex.match?(reg,x)
	end)
	"Case ##{i+1}: #{n}"
end)
content = Enum.join(result, "\n")
File.write("AlienLanguajeEX.txt", content)
