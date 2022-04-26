T = gets.to_i
for t in 1..T
    n = gets.to_i
    s = gets.split.map(&:to_i).to_a.sort
    curr = 1
    for i in s
        if curr>i
            next
        end
        curr+=1
    end    
    puts "Case ##{t}: #{curr-1}"
end