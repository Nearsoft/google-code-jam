n = gets.to_i

for i in 0..n-1
    r,c = gets.split.map(&:to_i)
    puts "Case ##{i+1}:"
    print ".."
    for j in 0..c-2
      print "+-"
    end
    puts "+"
    print ".."
    for j in 0..c-2
      print "|."
    end
    puts "|"
    for k in 0..r-1
        for l in 0..c-1
            print "+-"
        end
        puts "+"
        if k<r-1
            for l in 0..c-1
              print "|."
            end
            puts "|"
        end
    end
end