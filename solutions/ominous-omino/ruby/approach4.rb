input = File.open(ARGV[0])
output = File.new(ARGV[1],"w")
T = input.readline.to_i
T.times do |num_case|
  values = input.readline.split(" ")
  x = values[0].to_i
  r = values[1].to_i
  c = values[2].to_i

  min = (r<c)? r : c
  max = (r>=c)? r : c

  if ((r*c) % x != 0||x>=7) then rich = true end
  if (x==3 && min == 1) then rich = true end
  if (x==4 && min <= 2) then rich = true end
  if (x==5 && (min <= 2 || (min == 3 && max == 5))) then rich = true end
  if (x==6 && min <=3) then rich = true end

  output.print "Case ##{num_case+1}: "
  output.puts (rich)? "RICHARD" : "GABRIEL"
end
