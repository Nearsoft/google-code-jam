test_cases = gets.to_i

# test_cases = 4
# test_case_1 = "2 3 CJ?CC?"
# test_case_2 = "4 2 CJCJ"
# test_case_3 = "1 3 C?J"
# test_case_4 = "2 5 ??J???"

# test_cases = [test_case_1, test_case_2, test_case_3, test_case_4]

for test_case in 1..test_cases
  temp = gets.split(" ")
  
  x = temp[0]
  y = temp[1]
  painting = temp[2]
  puts painting

  painting = painting.split("")

  if painting[0] === "?"
    prev = 0
  else
    prev = 1
  end
  ans = ""

  painting.each_with_index do |letters, index|
    if painting[index] === "?"
      if prev === 0 && painting[index + 1] === "C"
        for k in (0..index + 1)
          painting[k] = "C"
          prev = 1
        end
      elsif prev === 0 && painting[index + 1] === "J"
        for k in (0..index + 1)
          painting[k] = "J"
          prev = 1
        end
      elsif prev === 1
        painting[index] = painting[index - 1]
      end
    end

    arr = ""
    for g in painting
      arr.concat(g.to_s)
    end

    if index === painting.length - 1
      cj = arr.enum_for(:scan, /(?=CJ)/).map { Regexp.last_match.offset(0).first }
      jc = arr.enum_for(:scan, /(?=JC)/).map { Regexp.last_match.offset(0).first }
      cj = cj.length
      jc = jc.length
      ans = (cj * x.to_i) + (jc * y.to_i)
    end
  end

  puts "Case #" + (test_case).to_s + ": " + ans.to_s
end
