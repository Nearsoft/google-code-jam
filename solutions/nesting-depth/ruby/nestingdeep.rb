T = gets.chomp.to_i

for	casos in 1..T do
	salida = ""
	nP = 0
	S = gets.chomp

	for	i in 1..S.length do
		x = S[i-1].to_i - 0
		while nP < x
			salida << "("
			nP += 1
		end
		while nP > x
			salida << ")"
			nP -= 1
		end			
		salida << S[i-1]
	end
	while nP > 0
		salida << ")"
		nP -= 1
	end
	puts "Case ##{casos}: " << salida
end