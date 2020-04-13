  t = gets.to_i
for i in (1..t) do
  n = gets
  arr_1 = n.chomp.split(//)
  indexes = arr_1.each_index.select{ |x| arr_1[x] == '4' }
  a = arr_1
  b = [].fill(0, 0..arr_1.size-1)
  indexes.each do |idx|
    a[idx] = '3'
    b[idx] = '1'
  end 
 puts "Case #"+"#{i}"+":"+" #{a.join('')} #{b[indexes.first..-1].join('')}"
end