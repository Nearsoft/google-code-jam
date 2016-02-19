defmodule Credit do

	def find_products(list,x,outputFile,acc\\0) 

	def find_products([],_,_,_) do end

	def find_products(list,x,outputFile, acc) do 
		{a,_,c} = { Enum.at(list, 0), Enum.at(list, 1), Enum.at(list, 2) }
		{totalCredit,_} = Integer.parse(a)
		pricesInteger = String.split(c, " ") |> Enum.map(fn number -> String.to_integer(number) end)

		store_credit(outputFile, totalCredit, pricesInteger, acc+1, false)
		newList = List.delete_at(list, 0) |> List.delete_at(0) |> List.delete_at(0)
		find_products(newList, x - 1,outputFile, acc+1)
	end
	
	def store_credit(outputFile, credit, prices, caseNum, flag, postInd \\ 0)

	def store_credit(_, _, _, _, flag, _) when flag == true do end

	def store_credit(outputFile, credit, prices, caseNum, flag, postInd) do
		prce1 = hd(prices)
		prce2 = credit - prce1
		indx2 = Enum.find_index(prices, fn(x) -> x == prce2 end)
		if indx2 != nil do
			indx1 = Enum.find_index(prices, fn(y) -> 
				total = y + Enum.at(prices, indx2)
				credit == total
			end)
			if indx1 == indx2 do
				indx2 = find_second_index(prices, prce1, false)
			end

			if indx2 > -1 do
				indx1 = indx1 + 1 + postInd
				indx2 = indx2 + 1 + postInd

				result = "Case #" <> to_string(caseNum) <> ": " <> to_string(indx1) <> " " <> to_string(indx2)
				IO.puts outputFile, result
				IO.puts result
				store_credit(outputFile, credit, tl(prices), caseNum, !flag, postInd+1)
			else
				store_credit(outputFile, credit, tl(prices), caseNum, flag, postInd+1)
			end
			

		else
			store_credit(outputFile, credit, tl(prices), caseNum, flag, postInd+1)
		end
	end

	def find_second_index(list, expected, state, acc \\ 0)

	def find_second_index(list, expected, state, acc) when state == true do
		secondIndex = Enum.find_index(list, fn exp -> exp == expected end)
		if secondIndex == nil do
			-1
		else
			acc + secondIndex
		end
	end

	def find_second_index([head|tail], expected, _, acc) do
		if head == expected do
			find_second_index(tail, expected, true, acc + 1)
		else
			find_second_index(tail, expected, false, acc + 1)
		end
	end

end

inputFile = File.read! "../tests/C-large-practice.in"
linesList = String.split(inputFile, "\n")
{cases, _} = Integer.parse(hd(linesList))
{:ok, output} = File.open("output.ex.out", [:write])

Credit.find_products(tl(linesList), cases, output)