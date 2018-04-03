def eat_pancakes(pancakes_on_diners_dish, minutes_spent_eating)
  # Decrease pancake on every plate by one
  pancakes_on_diners_dish.each_with_index do |element, index|
    pancakes_on_diners_dish[index] = element - 1
  end

  minutes_spent_eating = minutes_spent_eating + 1

  # Filter out 0s from array
  pancakes_on_diners_dish = pancakes_on_diners_dish.select do |element|
    element > 0
  end

  print "Cakes: #{pancakes_on_diners_dish}\n
  Minutes spent eating so far: #{minutes_spent_eating}\n"
  new_values = [pancakes_on_diners_dish, minutes_spent_eating]
  return new_values
end

def mutate_pancakes_array(pancakes_array)
  print "Dividing max value in #{pancakes_array}\n"

  max_value = pancakes_array.max
  index_of_max = pancakes_array.index(max_value)

  new_value = max_value / 2
  pancakes_array[index_of_max] = new_value
  pancakes_array << new_value

  print "New divided array: #{pancakes_array}\n"
  return pancakes_array
end

def new_max_value(old_max, pancakes_on_diners_dish)
  unique_values = pancakes_on_diners_dish.uniq
  if unique_values.length == 1
    return old_max / 2
  end

  second_largest = unique_values.sort[-2]
  if old_max / 2 > second_largest
    return old_max / 2
  else
     return second_largest
  end
end

def find_minimum_time_to_finish(input)
  max_value = input["pancakes_array"].max

  if input["pancakes_array"].length == 0
    total_time = input["minutes_spent_eating"] + input["accumulated_special_minutes"]
    print "Time to finish: #{total_time}\n"

    output = 'Case #' + "#{input["test_case"]}: #{total_time}\n"
    open("./large_dataset.out", "a") do |f|
      f.puts output
    end
    return
  end

  if max_value % 2 != 0
    new_values = eat_pancakes(input["pancakes_array"], input["minutes_spent_eating"])
    pancakes_array = new_values[0]
    input["pancakes_array"] = pancakes_array
    minutes_spent_eating = new_values[1]
    input["minutes_spent_eating"] = minutes_spent_eating
    return find_minimum_time_to_finish(input)
  end

  if max_value % 2 == 0
    new_max = new_max_value(max_value, input["pancakes_array"])
    # number of times maxValue is repeated
    n = input["pancakes_array"].count(max_value)

    resulting_time_if_divided = n + input["accumulated_special_minutes"] + new_max + input["minutes_spent_eating"]

    print "current it time: #{input["current_iteration_time_to_finish"]} vs #{resulting_time_if_divided}\n"

    if resulting_time_if_divided < input["current_iteration_time_to_finish"]
      input["pancakes_array"] = mutate_pancakes_array(input["pancakes_array"])
      while input["pancakes_array"].max == max_value
        input["pancakes_array"] = mutate_pancakes_array(input["pancakes_array"])
      end

      input["current_iteration_time_to_finish"] = resulting_time_if_divided
      input["accumulated_special_minutes"] = input["accumulated_special_minutes"] + n

      print "Going for another iteration with #{input["pancakes_array"]}\n"

      return find_minimum_time_to_finish(input)
    else
      new_values = eat_pancakes(input["pancakes_array"], input["minutes_spent_eating"])
      input["pancakes_array"] = new_values[0]
      input["minutes_spent_eating"] = new_values[1]
      return find_minimum_time_to_finish(input)
    end
  end
end

File.open("./B-large-practice.in", "r") do |f|
  f.each_with_index do |line, index|
    if index == 0
      next
    end

    if index % 2 != 0
      next
    end

    string_arr = line.split(" ")
    pancakes_on_diners_dish = string_arr.each.map { |x| x.to_i }
    current_iteration_time_to_finish = pancakes_on_diners_dish.max
    minutes_spent_eating = 0
    accumulated_special_minutes = 0

    inputs = Hash.new("input")
    inputs = {
      "test_case" => index / 2,
      "pancakes_array" => pancakes_on_diners_dish,
      "current_iteration_time_to_finish" => current_iteration_time_to_finish,
      "minutes_spent_eating" => minutes_spent_eating,
      "accumulated_special_minutes" => accumulated_special_minutes
    }

    find_minimum_time_to_finish(inputs)
  end
end
