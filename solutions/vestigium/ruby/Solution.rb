	#Obtenemos el numero de casos a examinar
	n_cases = gets.chomp.to_i
	
	
	#Hacemos el loop para los casos a examinar
	
	k = 0
	
	while k < n_cases do
	
		trace = 0 
		
		matrix = []
		
		i = 0
	
		#Obtenemos el tamano de la matriz
		size = gets.chomp.to_i
		
		#Llenamos la matriz con los datos del usuario
		until i == size 
	
			matrix << gets.chomp.split
			i += 1
			
		end 
		
		matrix.each { |sr| sr.map!(&:to_i) }
		
		i = 0
		#Calculamos el trace
		until i == size 
		
			trace += matrix[i][i]
			i += 1
			
		end 
		
		#Calculamos el numero de renglones repetidas
		ren_rep = 0
		j = 0
		
		until j == size 
		
			ren_hash = Hash.new
			
			l = 0
		
			until l == size 
			
				key = matrix[j][l].to_s
				
			
				if ren_hash.keys.include?(key) 
					
					ren_rep += 1
					break 
					
				else 
				
					ren_hash[matrix[j][l].to_s] = 0
					
				end
				
			l += 1
				
			end
		
		j += 1
		
		end
		
		#Calculamos el numero de columnas repetidas
		col_rep = 0
		j = 0
		
		until j == size 
		
			col_hash = Hash.new
			
			l = 0
		
			until l == size 
			
				key = matrix[l][j].to_s
				
				if col_hash.keys.include?(key) 
					
					col_rep += 1
					break 
					
				else 
				
					col_hash[matrix[l][j].to_s] = 0
					
				end
				
			l += 1
			

				
			end
		
		j += 1
		
		end
			
		print "Case ##{k+1}: ", trace," ", ren_rep," ", col_rep
		puts nil 
		
		k += 1
		
	end