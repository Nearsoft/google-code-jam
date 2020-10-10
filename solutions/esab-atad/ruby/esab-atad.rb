def query(i)
	#we output the index we want to know, then wait for the judge's response
	puts i
	STDOUT.flush
	r = gets.to_i
	STDIN.flush
	return r
end

def reverseFrom(arr)
	#reverse the given array (arr) starting at index 1
    j = 1
    k = arr.length()-1
    while j < k do
        aux = arr[j]
        arr[j] = arr[k]
        arr[k] = aux
        j+=1
        k-=1
    end
    return arr
end

def test_case()
	answer = Array.new($B+1)
	l = 1
	r = $B
	nr = 1
	while true do
		if nr % 10 == 1 && nr != 1
			found = -1
			found_diff = -1
			for i in 1..l-1 do
				if answer[i] == answer[$B+1-i]
					found = i
				else
					found_diff = i
				end
			end
			if found == -1
				new_value = query(1)
				query(1)
				if new_value != answer[1]
					for i in 1..l do
						answer[i] ^= 1
					end
					for i in r..$B do
						answer[i] ^= 1
					end
				end
			else
				one = query(found)
				if one != answer[found]
					for i in 1..l do
						answer[i] ^= 1
					end
					for i in r..$B do
						answer[i] ^= 1
					end
				end
				if found_diff == -1
					query(found);
				else
					if query(found_diff) != answer[found_diff]
						answer = reverseFrom(answer)
					end
				end
			end
			nr += 2;
		end
		answer[l] = query(l)
		answer[r] = query(r)
		l += 1
		r -= 1
		if l > r
			for i in 1..$B do
				print answer[i]
			end
			print "\n"
			STDOUT.flush
			judge = gets
			STDIN.flush
			if judge.length
    			if judge[0] != 'Y'
    			    exit(0)
    			end
    		else 
    		    exit(0)
    		end
			return
		end
	nr += 2
	end
end

general = gets.split()
STDIN.flush
t = general[0].to_i
$B = general[1].to_i
for rey_sux in 1..t
    test_case()
end