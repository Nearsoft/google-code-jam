#Figure out how to do a single index matrix
OP_IDX = {
    '1' => 0,
    'i' => 1,
    'j' => 2,
    'k' => 3,
    '-1' => 4,
    '-i' => 5,
    '-j' => 6,
    '-k' => 7
}

IDX_OP = ['1', 'i', 'j', 'k', '-1', '-i', '-j', '-k']

MUL = [
    [0, 1, 2, 3, 4, 5, 6, 7],
    [1, 4, 3, 6, 5, 0, 7, 2],
    [2, 7, 4, 1, 6, 3, 0, 5],
    [3, 2, 5, 4, 7, 6, 1, 0],
    [4, 5, 6, 7, 0, 1, 2, 3],
    [5, 0, 7, 2, 1, 4, 3, 6],
    [6, 3, 0, 5, 2, 7, 4, 1],
    [7, 6, 1, 0, 3, 2, 5, 4]
]

# We do the calculation 
def calculate(x, y)
  IDX_OP[MUL[OP_IDX[x]][OP_IDX[y]]]
end

def solve(seq)
  curr = '1'

  already_i = false
  already_k = false

  seq.each_char do |c|
    curr = calculate(curr, c)

    if curr == 'i' and !already_i
      already_i = true
    elsif curr == 'k' and already_i and !already_k
      already_k = true
    end
  end

  if already_i and already_k and curr == '-1'
    return 'YES'
  else
    return 'NO'
  end
end

File.open("C-small-practice.in", 'r') do |fileInput|
  lines = fileInput.readlines
	
  # Finally understood what does test cases really meant.
  cases = lines[0].to_i

  File.open("outputDijkstra.out", 'w') do |fileOut|
  1.upto(cases) do |i|
    len, times = lines[i*2-1].split(/ /).map { |s| s.to_i }
    fragment = lines[i*2].strip
	
	# Same as python kind of liking ruby
    all = fragment * times

    fileOut.puts "Case ##{i}: #{solve(all)}"
    end
  end
end
