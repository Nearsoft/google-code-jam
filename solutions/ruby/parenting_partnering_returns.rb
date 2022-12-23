# Read the number of test cases.
t = gets.to_i

def solve

    # get number of activities
    n = gets.to_i
    activities = Array.new()

    # get activities and order them by start time
    0.upto(n-1) do |id|
        start, finish = gets.split(" ").map(&:to_i)
        activities.push([start, finish, id])
    end
    
    # sort by ascending order of start time
    activities.sort_by! {|e| e[0]}

    c = 0
    j = 0
    strAnswer = "x" * n

    # assigns activity one by one if possible, otherwise return
    activities.each do |activity|
        start = activity[0]
        finish = activity[1]
        index = activity[2]

        if c <= start
            c = finish
            strAnswer[index] = "C"
        elsif j <= start
            j = finish
            strAnswer[index] = "J"
        else
            strAnswer = "IMPOSSIBLE"
            break
        end 
    end

    return "#{strAnswer}"    
end

# Loop over the number of test cases.
1.upto(t) do |ti|
    puts "Case ##{ti}: #{solve}"
end
