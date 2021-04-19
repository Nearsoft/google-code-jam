#Define reverse function
def rev(arr, i, min_idx)
    while i <= min_idx
        tmp = arr[i]
        arr[i] = arr[min_idx]
        arr[min_idx] = tmp
      i = i+1
      min_idx = min_idx-1
    end
  
    arr
end
  
T = gets.to_i
  
[*1..T].each do |t|
    N = gets.to_i
    l = gets.split(' ').map(&:to_i)
    cost = 0
  
    [*1..N-1].each do |x|
      i = x-1
      min = nil
      min_idx = nil
  
      [*i..N-1].each do |j|
        if min.nil? || min > l[j]
          min_idx = j
          min = l[j]
        end
      end
  
      cost += min_idx-i+1
  
      l = rev(l, i, min_idx)
    end
    puts "Case ##{t}: #{cost}"
end