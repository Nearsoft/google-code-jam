#Define reverse function
def reverse(arr, i, min_idx)
    while i <= min_idx
        tmp = arr[i]
        arr[i] = arr[min_idx]
        arr[min_idx] = tmp
      i = i+1
      min_idx = min_idx-1
    end
  
    arr
end

#Get number of tests set them to int
T = gets.to_i
#For each test get the number of elements in the list, get the list and convert it to array of ints.
[*1..T].each do |t|
    NumElm = gets.to_i
    list = gets.split(' ').map(&:to_i)
    cost = 0
    
#For each list element get current element to iterate the list from this element on, set the cost and reverse the list
    [*1..NumElm-1].each do |e|
      i = e-1
      min = nil
      min_idx = nil
  
#Iterate the list to find the min number and it's index
      [*i..NumElm-1].each do |j|
        if min.nil? || min > list[j]
          min_idx = j
          min = list[j]
        end
      end
  
      cost += min_idx-i+1
  
      list = reverse(list, i, min_idx)
    end
    puts "Case ##{t}: #{cost}"
end
