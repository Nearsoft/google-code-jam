nCases = gets.chomp.to_i 
nCases.times do |i| 
    unnecessary = gets.chomp
    path = gets.chomp
    path = path.chars.map { |ch| ch=="S"? "E" : "S" }
    puts "Case ##{i+1}: #{path.join}"
end