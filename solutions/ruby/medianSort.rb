$stdout.sync = true

def valid res
  if res == -1
    exit 0
  end
  return res
end

def printsAnswer res
  puts res.join(' ')
  $stdout.flush
  judge = STDIN.gets.chomp.to_i;
  valid(judge)
end

def query(i, j, k)
  puts "#{i} #{j} #{k}"
  $stdout.flush
  
  res = STDIN.gets.chomp.to_i

  return valid(res)
end

def solve ans
  res = [1, 2]    
  n = ans.size

  # try new index
  for number in 3.upto(n)
    start = 0
    finish = res.size - 1 

  
    while finish-start >= 1

      # divide list in 3 parts
      a = start + (finish - start) / 3
      b = finish - (finish - start) / 3

      # query median
      median = query(res[a], res[b], number)

      # move limits of parts according to median
      if median == res[a]
        finish = a - 1
        if start == finish
          finish += 1
        end
      elsif median == res[b]
        start = b + 1
        if start == finish
          start -= 1
        end
      elsif median == number
        start = a + 1
        finish = b - 1
        if start == finish
          start -= 1
        end
      end
    end
    # insert at correct index
    res.insert(start, number)
  end


  printsAnswer(res)
end

t, n, q = STDIN.gets.split.map &:to_i;

0.upto(t - 1) do
  answer = Array(1..n)
  solve(answer)
end

exit 0