package main

import (
	"fmt"
)

func main() {

	//Inicialización e input del número de pruebas a hacer
	var iteraciones int
	fmt.Scanln(&iteraciones)

	//Ciclo para hacer la anidación de las cadenas
	for l := 0; l < iteraciones; l++ {
		//Petición de la cadena de numeros
		var nums string
		fmt.Scanln(&nums)

		//Declaramos variables auxiliares, depth nos indicará la profundidad en la que iniciamos
		//cad es la cadena a la que irémos agregando los numeros con su respectiva anidación
		depth := int(nums[0]) - 48
		cad := ""

		//Este ciclo va recorriendo la cadena numerica insertada, caracter por caracter
		for i, n := range nums {
			//iniciamos la variable cad con el primer numero con su nivel de anidación
			//Esto tambien se hace, para que en las iteraciones posteriores a la primera
			//Se pueda comparar si el numero anterior es igual, meterlo en el mismo nivel
			if i == 0 {
				for j := 0; j < int(n-48); j++ {
					cad += "("
				}
				cad += string(n)
			} else {
				//Condicional para comprobar si el numero anterior fue igual, si lo es,
				//agregarlo justo enseguida
				if int(n) == int(nums[i-1]) {
					cad += string(n)
				}
				//Si el número de la cadena en el que vamos, es mayor al número anterior
				//agregaremos profundidad al anidamiento y agregamos el número en su
				//profundidad y se actualiza la variable de profundidad
				if int(n) > int(nums[i-1]) {
					for j := 0; j < (int(n) - int(nums[i-1])); j++ {
						cad += "("
						depth++
					}
					cad += string(n)
					//Si el número de la cadena en el que vamos, es menor al número anterior
					//quitaremos profundidad al anidamiento y agregaremos el número en su
					//profundidad y se actualiza la variable de profundidad
				} else if int(n) < int(nums[i-1]) {
					for j := 0; j < (int(nums[i-1]) - int(n)); j++ {
						cad += ")"
						depth--
					}
					cad += string(n)

				}

			}

		}
		//Este último ciclo, cerramos la profundidad del último nivel en el que nos quedamos
		for i := 0; i < depth; i++ {
			cad += ")"
		}
		//Imprimimos el resultado
		fmt.Printf("Case #%d: %s\n", l+1, cad)
	}
}
