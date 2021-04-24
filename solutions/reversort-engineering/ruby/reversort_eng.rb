def reversed(arr, i, j)
    while i < j
        arr[i], arr[j] = arr[j], arr[i]
        i += 1
        j -= 1
    end
end

def reversort_engineering() 
    sizeOfArray, cost = gets.split(" ").map(&:to_i)
    if cost < sizeOfArray - 1 or cost > ((sizeOfArray * (sizeOfArray + 1)) / 2) - 1
        return "IMPOSSIBLE"
    end
    arrOfRange = (1..sizeOfArray).to_a
    for i in (0..sizeOfArray-2).to_a.reverse()
        minimunCost = [cost - i, sizeOfArray - i].min
        cost -= minimunCost
        reversed(arrOfRange, i, i + minimunCost - 1)
    end
    return arrOfRange.map(&:to_s).join(" ")
end

(1..gets.chomp.to_i).each do |caseNumber|
    puts("case ##{caseNumber}: #{reversort_engineering()}")
end


