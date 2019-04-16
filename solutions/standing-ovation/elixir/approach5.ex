defmodule M do

	def main do
		read("small-output.txt","A-small-practice.in")
		read("large-output.txt","A-large-practice.in")
		#algorithm("1011021")
	end

	def algorithm(arr) do
		#smax = IO.gets "Max "
		#arr = IO.gets "input "
		#arr = arr |> String.replace(~r/\r\n/, "")
		#arr = "1011021"
		arr = String.codepoints(arr)

		arr = Enum.map(arr, fn  x -> elem(Integer.parse(x), 0) end)

		arrIndex = arr |> Enum.with_index

		abc = Enum.reduce(arrIndex, 0, fn({x,i},acc) ->
			howmany = 0
			standingpeople = (Enum.take(arr,i) |> Enum.sum)
			#IO.puts "shyness lvl: #{i} => #{x}     Acc = #{acc}    standingpeople = #{standingpeople}"
			if x>0 and i>standingpeople+acc do
					#IO.puts "hago algo"
					invite = i - standingpeople-acc
					#IO.puts "Necesita invitarse personas: #{invite}"
					#IO.puts "==========="
					howmany=invite
			end

			acc+howmany
		end)


		abc
	end

	def test(index,smax,input,filename) do
		#IO.puts "Hello. I'm a test #{index} #{smax} #{input}"
		s = "Case ##{index}: #{algorithm(input)}\n"
		File.write(filename, s, [:append])
	end


	def read(fileoutput, fileinput) do
		File.write(fileoutput, "")

		File.stream!(fileinput)
	  |> Stream.map(&String.strip/1)
	  |> Stream.with_index
	  |> Stream.map(fn ({line, index}) ->
	    if index != 0 do
	      list_of_inputs = String.split(line)
	      smax = String.to_integer(Enum.at(list_of_inputs, 0))
	      input = Enum.at(list_of_inputs, 1)
	      test(index,smax,input,fileoutput)
	    end

	  end)
  	|> Stream.run

	end

end
