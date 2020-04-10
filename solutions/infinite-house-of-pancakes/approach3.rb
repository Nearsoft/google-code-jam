def waiter(diners, pancakes_array, test_case)
  mins_array = []

  max = pancakes_array.max
  mins_array[0] = max

  i = 1
  loop do

    split = 0
    pancakes_array.each do |pancake|
      if pancake > i
        v = pancake.to_f / i
        split = split + (v.ceil - 1)
      end
    end

    mins_array[i] = split + i

    if i > 1000
      break
    end

    i = i + 1
  end

  min = mins_array.min

  output = 'Case #' + "#{test_case}: #{min}\n"
  print "#{output}\n"

  open("./small_dataset.out", "a") do |f|
    f.puts output
  end
end

File.open("./B-small-practice.in", "r") do |f|
  f.each_with_index do |line, index|
    if index == 0
      next
    end

    if index % 2 != 0
      next
    end

    if index % 2 == 0
      string_arr = line.split(" ")
      pancakes_array = string_arr.each.map { |x| x.to_i }
      diners = pancakes_array.length
      test_case = index / 2

      print "Test case #{test_case}\n"
      waiter(diners, pancakes_array, test_case)
    end
  end
end
