
 TABLE=[
	[ 0, 0 , 0 , 0 , 0],
    [ 0, 1 , 2 , 3 , 4],
    [ 0, 2 ,-1 , 4, -3],
    [ 0, 3 ,-4 , -1, 2],
    [ 0, 4 , 3 , -2,-1]]

def power(x,y)
	value=1
	(1..(y % 4)).each do |i|
		value=mul(value,x)
	end
	return value
end

			
def getNum(letter)
	case letter
	when "i"
		return "2"
	when "j"
		return "3"
	when "k" 
		return "4"
	end
end

def mul(x,y)
	if x*y>0
		sign=1
	else
		sign=-1
	end
	return sign * TABLE[x.abs][y.abs]
end

def multiply_all(s, l, x)
	value=1
	(0..l-1).each do |i|
		value=mul(value,s[i+2].to_i)
	end
	return power(value,x)
end

def construct_first(s, l, x)
	i_value=1
	j_value=1
	(0..x-1).each do |i|
		(0..l-1).each do |j|
			if i_value!=2
				i_value=mul(i_value,s[j+2].to_i)
			elsif j_value!=3
				j_value=mul(j_value,s[j+2].to_i)
			end
		end
	end
	return i_value==2 && j_value==3
end

counter=0
ARGF.gets
while !ARGF.eof?
counter+=1
l,x=ARGF.gets.split.map(&:to_i)
s=ARGF.gets.split().to_s
(0..(s.length-5)).each do |n|
	s[n+2]=getNum(s[n+2])
end

if((multiply_all(s,l,x)==-1) && construct_first(s, l, [8,x].min))
	puts "Case ##{counter}: YES"
else
	puts "Case ##{counter}: NO"
end
end

