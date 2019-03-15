require 'pry'

num_cases = gets.to_i

for case_number in 0..number_of_cases - 1
  num_plates = gets.to_i
  pancakes = Array.new(num_plates)
  pancakes = gets.split(' ').map(&:to_i)

  max_p = pancakes.max
  counter = Array.new(max_p)
  max_p += 1


  for counter_index in 0..counter.length-1
    counter[counter_index]=0
  end

  for j in 0..pancakes.length-1

    counter[(pancakes[j]-1)] += 1
  end

  moves = pancakes.max.to_i;

  # min_minutes = 10_000
  for k in 0..counter.length-1
    splits = 0

    pk = k +1
    for l in 0..counter.length-1
      pl = l+1
      splits =  splits + (((pl - 1) / pk) * counter[l].to_i)

    end

    if splits + pk.to_i <= moves.to_i
      moves = splits + pk
    end

    # puts "Splits:   "+pk.to_s+ "   "+counter[k].to_s

  end

  printf "Case #%d: %d\n", (case_number + 1), moves
end
