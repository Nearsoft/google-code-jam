{:ok, content} = File.read("C-small-practice.in")
lista = String.split(content,"\n")
numMax = hd(lista)
lista = tl(lista)
defmodule Tabla do
	def tabla1(a,b) when a == 1 do
		case b do
			1 -> 1
			2 -> 2
			3 -> 3
			4 -> 4
			_ -> IO.puts "No se encontro el valor"
		end
	end
	def tabla1(a,b) when a == 2 do
		case b do
			1 -> 2
			2 -> -1
			3 -> 4
			4 -> -3
			_ -> IO.puts "No se encontro el valor"
		end
	end
	def tabla1(a,b) when a == 3 do
		case b do
			1 -> 3
			2 -> -4
			3 -> -1
			4 -> 2
			_ -> IO.puts "No se encontro el valor"
		end
	end
	def tabla1(a,b) when a == 4 do
		case b do
			1 -> 4
			2 -> 3
			3 -> -2
			4 -> -1
			_ -> IO.puts "No se encontro el valor"
		end
	end
	def tabla1(a,_) when a > 4 do
		IO.puts "a no esta entre los valores 1 y 4"
	end
end
defmodule Cambio do
	def cambio(letra) do
		case letra do
			'i' -> 2
			'j' -> 3
			'k' -> 4
			"i" -> 2
			"j" -> 3
			"k" -> 4
			_ -> IO.puts "No se encontro el valor pp"
		end
	end
end
defmodule Mul do
	def mul(a,b) do
		sign = 
			if a * b < 0 do
				-1
			else
				1
			end
		a =
			if a < 0 do
				 a  * -1
			else
				a
			end
		b =
			if b < 0 do
				 b  * -1
			else
				b
			end
		sign * Tabla.tabla1(a,b)
	end
end
defmodule FirstTwo do
	def firsttwo(s,l,x) do
		i_value = 1
		j_value = 1
		cicloX(s,l,x,i_value,j_value)
	end
	def cicloX(s,l,x,i,j) when x <= 1 do
		cicloL(s,l,i,j,0)
	end
	def cicloL(_,_,i,j,_) when i == 2 and j == 3 do
		true
	end
	def cicloL(_,l,_,_,ll) when ll >= l do
		false
	end
	def cicloL(s,l,i,j,ll)  do
		value2 = String.slice(s,ll,1)
		value3 = Cambio.cambio(value2)
		j =
		if i == 2 do 
			Mul.mul(j,value3)
		else
			j
		end
		i =
		if i != 2 do
			Mul.mul(i,value3)
		else
			i
		end
		cicloL(s,l,i,j,ll+1)
	end
end
defmodule Multiply_all do
	def multiply_all(s,l,x,numero) do
		value = 1
		l = String.to_integer(l)
		x = String.to_integer(x)
		value = cicloX(s,l,x,value)
		if value == -1 do
			x = 
			if x > 8 do
				8
			else
				x
			end
			if l == 1 do
				IO.puts "Case #"<>to_string(numero)<>": NO"
			else
				s = String.duplicate(s,x)
				l = l * x
				sePudo = FirstTwo.firsttwo(s,l,1)
				if sePudo do
					IO.puts "Case #"<>to_string(numero)<>": YES"
				else
					IO.puts "Case #"<>to_string(numero)<>": NO"
				end
			end
		else
			IO.puts "Case #"<>to_string(numero)<>": NO"
		end
	end
	def cicloX(s,l,x,value) when x <= 1 do
		cicloL(s,l,x,value,0)
	end
	def cicloX(s,l,x,value) do
		value = cicloL(s,l,x,value,0)
		cicloX(s,l,x-1,value)
	end
	def cicloL(_,l,_,value,ll) when ll >= l do
		value
	end
	def cicloL(s,l,x,value,ll) do
		value2 = String.slice(s,ll,1)
		value3 = Cambio.cambio(value2)
		value = Mul.mul(value,value3)
		cicloL(s,l,x,value,ll+1)
	end
end
defmodule Main do
	def main1(numero,numMax,listaa) when numero >= numMax do
		lista = tl(listaa)
		ll = String.split(hd(listaa)," ")
		l = hd(ll)
		x = tl(ll)
		x = hd(x)
		str = hd(lista)
		Multiply_all.multiply_all(str,l,x,numero)
	end
	def main1(numero,numMax,listaa) do
		lista = tl(listaa)
		ll = String.split(hd(listaa)," ")
		l = hd(ll)
		x = tl(ll)
		x = hd(x)
		str = hd(lista)
		lista = tl(lista)
		Multiply_all.multiply_all(str,l,x,numero)
		main1(numero + 1,numMax,lista)
	end
end
numMax = String.to_integer(numMax)
Main.main1(1,numMax,lista)
