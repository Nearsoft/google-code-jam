
# Author: Miguel Romero Valdés 

# This is the solution to the Moons and Umbrellas problem of 
# the 2021 Google codejam programming competition. It's the second problem 
# from the qualification.

# This code only works with positive costs, and it passes the first two 
# sample tests.

class Solution

	def initialize(cj_cost, jc_cost, mural)
		@cj_cost = cj_cost
		@jc_cost = jc_cost
		@mural = mural

	end

	def min_cost()

		cost = @mural.gsub('?', '').scan('CJ').length * @cj_cost + \
		@mural.gsub('?', '').scan('JC').length * @jc_cost

		return cost
	end


	t = gets.to_i
	for i in 1..t
		cj_cost, jc_cost, mural = gets.split()
		solution = Solution.new(cj_cost.to_i, jc_cost.to_i, mural)
		puts "Case ##{i}: #{solution.min_cost}"
	end


end #End class



