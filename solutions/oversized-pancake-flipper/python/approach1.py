import sys  
import os

def main():

    filepath = '/home/brian/Downloads/input.txt'
    output = open("output.txt", "w+")
    with open(filepath) as text:
        for cnt, line in enumerate(text):

            pancakes = line  #guarda la linea completa en el string pancakes
            pancakes2 = []   #guarda la linea resuelta de + y -
            panchar = ""     #string de pancakes (solo + y -)
            kchar = ""       #valor de k guardado como string

            k = 0 #tamaño de la espatula
            s = 0 #longitud de la linea de pancakes

            p = 0 #posicion de la espatula
            steps = 0 #numero de pasos necesarios para resolver

            c = 0 #contador (cuenta la longitud de la linea en caracteres)
            x = 0 #contador auxiliar



            #PARA OBTENER VALOR DE S
            for ch in pancakes:
                c=c+1

                #print (ch)
                if ch == "-":
                    pancakes2.append('-')

                else:
                    if ch == "+":
                        pancakes2.append('+')

                    else:
                        s = len(pancakes2)



            #print(pancakes2)

            if s > 0:  #si hay pancakes en la línea:

                # lk = cantidad de digitos de k
                lk = c - s - 2

                #print("pancakes:",s, "// digitos de k:",lk)

                #OBTENER VALOR DE K
                x=s+1
                while lk > 0:
                    kchar = kchar+line[x]
                    #print("char k es", kchar, "// lk es", lk)   #usado para pruebas
                    x=x+1
                    lk=lk-1

                #obtener string de pancakes (solo + y -)
                x=0
                while x<s:
                    panchar = panchar+line[x]
                    x=x+1

                k = int(kchar)
                #print("valor de k:", k)

                #código para voltear los pancakes
                for ch in pancakes:

                    while ((k+p)<=s):
                        if pancakes2[p] == "+":
                            p = p + 1
                            #print("avanza 1")   #usado para pruebas

                        else:

                            if pancakes2[p] == "-":
                                # voltear
                                #print("voltea")  #usado para pruebas
                                i = 0
                                x = p #posicion temporal que se usa para voltear sin cambiar el valor de p
                                while i<k:
                                    if pancakes2[x] == "-":
                                        pancakes2[x] = "+"
                                        i=i+1
                                        x=x+1
                                    else:
                                        if pancakes2[x] == "+":
                                            pancakes2[x] = "-"
                                            i=i+1
                                            x=x+1
                                        else:
                                            output.write ("Error: valor no esperado en la lista 'pancakes2'")
                                steps = steps + 1
                                p = p + 1


                            else:
                                output.write ("error loko")
                x=0
                resuelto = True
                while resuelto and x<s:
                    if pancakes2[x]=='+':
                        x = x + 1
                    else:
                        if pancakes2[x]=='-':
                            resuelto = False
                        else:
                            output.write("Error: valor no esperado en la lista 'pancakes2'")

                output.write(" ")
                #print(pancakes2)
                if resuelto:
                    output.write(f'Case #{cnt}: {panchar} {k} {steps}\n')

                else:
                    output.write(f'Case #{cnt}: {panchar} {k} IMPOSIBLE\n')

    output.close()
    text.close()










if __name__ == '__main__':

   main()