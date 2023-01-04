defmodule Problem do 
  
   def solve do 

   one = %{c: 300000, y: 200000, m: 300000, k: 500000}
   two = %{c: 300000, y: 200000, m: 500000, k: 300000}
   three = %{c: 300000, y: 500000, m: 300000, k: 200000}
   c = Enum.min([one.c, two.c, three.c]);
   y = Enum.min([one.m, two.m, three.m]);
   m = Enum.min([one.y, two.y, three.y]);
   k = Enum.min([one.k, two.k, three.k]);
   
   if c+y+m+k == 1000000 do 
    IO.puts "Case 1: #{c,y,m,k}"
   if c+y+m+k < 1000000 do
     IO.puts "IMPOSSIBLE"
   else 
   else{
    if m + y + k <= 1000000 do
      IO.puts "Case 1: #{(1000000-(m+y+k)),y,m,k}"
   if y+k<= 1000000 do
¡      IO.puts "Case 1: #{(1000000-(y+k)),y, k}"
   if k <= 1000000 do
      final.push(0, 0, (final- min_k), min_y);
      IO.puts "Case 1: #{0,0,(1000000-k),y}"
  else
      final.push(0,0,0,limit);
      IO.puts "Case 1: #{0,0,0,100000}"

    }
   end 
   
end


Problem.solve()