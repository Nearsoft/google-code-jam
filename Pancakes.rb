t = gets.to_i

for testCase in 0..t - 1
  dinners = gets.to_i
  plates = Array.new(dinners)
  for ind in 0..dinners-1
    plates[ind]=gets.to_i
  end
  counts = Array.new(10005)
  for ind in 0..counts.length-1
    counts[ind]=0
  end
  for ind in 0..plates.length-1
    counts[(plates[ind])] +=1
  end
  min=10000
  moves = plates.max.to_i;
  for lim in 1..counts.length
    moves = 0
    for j in 0..counts.length-1
        moves =  moves + (((j - 1) / lim) * counts[j].to_i)
    end

    if moves + lim <= min.to_i
      min = moves + lim
    end
  end

  printf "Case #%d: %d\n", (testCase + 1), min
end