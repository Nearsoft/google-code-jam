  def readFile
    File.foreach("/Users/sergiosilva/Downloads/B-small-practice.in").with_index do |line, line_num|
      if line_num != 0 && line_num.even?
        result = result(line)
        puts "Case ##{line_num/2}: #{result}"
      end
    end
  end

  def result(platos)
    min = 0
    aux = 0
    aux2 =0
    aux3 =0
    minute_total = 0
    max = 0

    # Turn string into int array
    array = get_array(platos)

    array = array_string_to_int(array)

    # SORT ARRAY BY NUMBER
    array = array.sort
    # Get Max number from
    max = array.max
    # Add highest number to maxs list
    maxs = [array.max]
    # add min value to minutes list
    minutes = [min]
    i = 0
    while i < array.length do
      #i += 1
      aux = 0
      aux2 = 0

      if minute_total <= max

        index = 0
        while index< array.length do

          if array[index] != 1 && array[index] == max

            if  array[index] % 2 != 0

              aux = array[index] /2
              aux2 = (array[index] / 2) + 1

              array.push(aux)
              array.push(aux2)

              # remove value from array
              array.delete_at(index)
              #continue
              min += 1
              minutes.push(min)
              array = array.sort
            else
              aux = array[index] / 2
              aux2 = array[index] / 2
              array.push(aux)
              array.push(aux2)
              # remove value from array
              array.delete_at(index)
              #continue
              min += 1
              minutes.push(min)
              array = array.sort
            end
          end
          index += 1
        end
      end

      # more code
      minutes =  minutes.sort
      array = array.sort
      maxs.push(max)
      # get some wierd value
      max = array[array.length - 1]
      #continue
      minute_total = min

      i += 1
    end

    aux3 = minutes[0] + maxs[0]

    z = 0
    while z<minutes.length do

      if aux3 > minutes[z] + maxs[z]
        aux3 = minutes[z] + maxs[z]
      end
      z += 1
    end

    return aux3

  end

  def get_array(line)
     string = ""
     line = line.gsub(/\s+/m, ' ').strip.split(" ")
     line.each_with_index {|value,index |
       string += value
     }


     return line
  end

  def array_string_to_int(array)

    array.map {|x| x.to_i}

  end

  readFile