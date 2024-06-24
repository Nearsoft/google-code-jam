# For testing run:
# python3 interactive_runner.py python3 testing_tool.py 0 -- ruby median_sort.rb 
#                                                       ^
#                                                       |
#                                                       ----- Number of test(0, 1, 2)



# Values for test
T, N, Q = gets.chomp.strip.split.map{|_| Integer(_)} 


def query(i, j, k)
  # Get the value of the elements for test
  puts "#{i} #{j} #{k}"
  STDOUT.flush
  return gets.chomp.to_i
end


def judge(result)
  # Check the judge answers, mapping the answer to string
  puts(result.map{|_| String(_)}.join(" "))
  STDOUT.flush
  pass = gets.chomp.strip
  if pass != "1"
      exit 0
  end
end


def median_sort()
  result = [1, 2] # Minumum number of elements
  for l in 3.upto(N)
    # Query elemets to test
    left, right = 0, result.length() - 1
    while right - left >= 1
      side_a = left + (right - left) / 3
      side_b = right - (right - left) / 3
      values = query(result[side_a], result[side_b], l)
      # Check the values [side_a, side_b] 
      if values == result[side_a]
        right = side_a - 1
        if left == right
          right += 1
        end
      elsif values == result[side_b]
        left = side_b + 1 
        if left == right
          left -= 1
        end
      else
        # Check the next values
        left, right = side_a + 1, side_b - 1
        if left == right
          left -= 1
        end
      end
    end
    # Insert sorted element
    result.insert(left, l)
  end
  judge(result)
end


for cases in 1.upto(T)
  # Test Cases
  median_sort()
end
