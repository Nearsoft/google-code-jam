# Read the number of test cases.
t = gets.to_i

def solve

    #get string n and c
    n, c = gets.split(" ").map(&:to_i)

    # cost until the last element
    c -= n - 1

    costsArr = Array.new()
    
    # is it possible?
    for cost in (n - 1).downto(1)
        if c >= cost
            costsArr.push(cost)
            c -= cost
        end
    end

    if c != 0
        return "IMPOSSIBLE"
    end

    ans = Array.new()
    for i in 1.upto(n)
        ans.push(i)
    end

    #there's n - 2 numbers to go
    for i in (costsArr.size - 1).downto(0)
        ans[n-1-costsArr[i], n] = ans[n-1-costsArr[i], n].reverse
    end
    return ans.join(" ")
end

# Loop over the number of test cases.
1.upto(t) do |ti|
    puts "Case ##{ti}: #{solve}"
end
