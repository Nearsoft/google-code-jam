def reset_globals()
  $audience_string = ""
  $chars = []
end

def main(index, chars)
  standing_people = 0
  invited = 0

  chars.each_with_index do |char, index|
    number_of_people = char.to_i

    if number_of_people > 0
      if index <= standing_people
        standing_people = standing_people + number_of_people
      else
        invited = invited + (index - standing_people)
        standing_people = standing_people + number_of_people + index - standing_people
      end
    end
  end

  print "Persons to be invited: #{invited}\n"
  # Write to output file
  output = 'Case #' + "#{index}: #{invited}\n"
  #IO.write(, output)
  open("./large_dataset.out", "a") do |f|
    f.puts output
  end
end

File.open("./A-large-practice.in", "r") do |f|
  f.each_with_index do |line, index|
    if index == 0
      next
    end

    line_arr = line.split(" ")
    $audience_string = line_arr[1]
    $chars = $audience_string.split("")
    print "Case #{index} #{$chars}\n"
    main(index, $chars)
    reset_globals()
  end
end
