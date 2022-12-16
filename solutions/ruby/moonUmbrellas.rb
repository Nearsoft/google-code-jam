# Read the number of test cases.
t = gets.to_i

def solve

    #get string of C and J and ?
    a, b, str = gets.split(" ")

    # get costs
    x = a.to_i
    y = b.to_i

    # with dynamic programming
    cCost = (str[0] == 'J' ? 1e9 : 0)
    jCost = (str[0] == 'C' ? 1e9 : 0)

    for i in 1 ... str.size
        cCostNew = 1e9
        jCostNew = 1e9
        if str[i] == 'C'
            cCostNew = [cCost, jCost + y].min
        elsif str[i] == 'J'
            jCostNew = [jCost, cCost + x].min
        else
            cCostNew = [cCost, jCost + y].min
            jCostNew = [jCost, cCost + x].min
        end
        cCost = cCostNew
        jCost = jCostNew
    end

    return [cCost, jCost].min
end

# Loop over the number of test cases.
1.upto(t) do |ti|
    puts "Case ##{ti}: #{solve}"
end
