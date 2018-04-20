testCase = 0
IO.foreach("A-small-practice.in") {
	|x| 

	if testCase > 0
		y = x.split(" ")
		sMax = y[0]
		array = y[1]
		sum = 0
		peopleNeeded = 0
		index = 0
		for i in array.split("")
			if ((sum + peopleNeeded) < index)
				peopleNeeded = index - sum
			end
			sum += i.to_i
			index += 1
		end

		print peopleNeeded.to_s + "\n"
	end
	testCase += 1
}

