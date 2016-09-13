def r_wins(x,r,c)
  s=[r,c].min
  l=[r,c].max
  return true if (s*l) %x !=0
  return true if x == 3 && s == 1
  return true if x == 4 && s <= 2
  return true if x == 5 && (s <= 2 or [s, l] == [3, 5])
  return true if x == 6 && s <= 3
  return true if x >=7
  return false
end
ARGF.gets
while !ARGF.eof?
  x,r,c=ARGF.gets.split.map(&:to_i)
  name=r_wins(x,r,c) ? "RICHARD" : "GABRIEL"
  puts "Case ##{ARGF.lineno-1}: #{name}"
end
