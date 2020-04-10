
class Pancakes

  def minutes
    tests_cases = gets.chomp
    tests_cases = tests_cases.to_i
    for i in 1..tests_cases
      non_empty_plates = gets.chomp
      non_empty_plates = non_empty_plates.to_i
      chain = gets.chomp
      plates = chain.split(" ").map(&:to_i)
      max = plates.max
      result = max
      for k in 1..max
        total_moves = 0
        for p in plates
          total_moves += (p-1)/k


        end
        result = [result, total_moves + k].min
      end
      puts(result)
    end
  end
end

pancake = Pancakes.new()
pancake.minutes
