def reversed(arr, i, j)
    while i < j
        arr[i], arr[j] = arr[j], arr[i]
        i += 1
        j -= 1
    end
end

def reversort_engineering() 
    n, c = gets.split(" ").map(&:to_i)
    if c < n - 1 or c > ((n * (n + 1)) / 2) - 1
        return "IMPOSSIBLE"
    end
    arrOfRange = (1..n).to_a
    for i in (0..n-2).to_a.reverse()
        m = [c - i, n - i].min
        c -= m
        reversed(arrOfRange, i, i + m - 1)
    end
    return arrOfRange.map(&:to_s).join(" ")
end

(1..gets.chomp.to_i).each do |n|
    puts("case ##{n}: #{reversort_engineering()}")
end


