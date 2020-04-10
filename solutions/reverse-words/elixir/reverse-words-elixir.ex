#Modulo con metodos recursivos para la impresion de datos inversos
defmodule Cases do

	def print_cases(array, n, cont) when n <= 1 do
		IO.puts "Case #"<>Integer.to_string(cont+1)<>": "<>
		Enum.join(Enum.reverse(String.split(Enum.at(array,cont)," "))," ")
	end

	def print_cases(array, n, cont) do
		IO.puts "Case #"<>Integer.to_string(cont+1)<>": "<>
		Enum.join(Enum.reverse(String.split(Enum.at(array,cont)," "))," ")
		#array|>Enum.at(cont)|>String.split(" ")|>Enum.reverse|>Enum.join(" ")
		print_cases(array, n-1, cont+1)
	end

	def print_cases(array, n) do
		print_cases(array, n, 0)
	end
end

#lee archivo y lo guarda en array o list

content = "input.in"|>File.read!|> String.split("\n") #toma el nombre del archivo, lo lee y lo separa las lineas
num_cases = Enum.at(content,0)	# obtiene el número de cases de la primera linea
cases= List.delete_at(content, 0) # obtiene las oraciones con las que se va a trabajar

{num, _} = Integer.parse(num_cases)# parsea el valor, ignora el remainder_of_binary que devuelve .parse (usando _) y lo almacena en la variable num

IO.puts "INPUT:"
Enum.each(content, fn (x)-> IO.puts x end) #imprime el input

IO.puts "\nOUTPUT:"
Cases.print_cases(cases, num)#se llama al metodo recursivo que imprime las oraciones de forma inversa y con formato
