
  def readFile
    File.foreach("/Users/sergiosilva/Documents/A-large-practice.in").with_index do |line, line_num|
      if line.include? " "
        total_friend_invited = get_total_friends_invited(line)
        puts "Case ##{line_num}: #{total_friend_invited}"
      end
    end
  end

  def get_total_friends_invited(line)
    array = get_array(line)

     total_people_aplouded = 0;
    total_friends_invited = 0;

    array.each_with_index {|value, index |

      begin
        if total_people_aplouded >= index
          total_people_aplouded += value.to_i
          break
        else
          total_friends_invited += 1
          total_people_aplouded += 1
        end
      end while total_people_aplouded <= index
    }
    return total_friends_invited
  end

  def get_array(line)
    line = line.gsub(/\s+/m, ' ').strip.split(" ")
    line = line[1]
    # Convert string to int[]
    line.split("")
  end

  readFile
