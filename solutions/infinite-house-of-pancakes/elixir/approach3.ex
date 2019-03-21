#no pueden existir metodos sin haber modulos, por eso lo usamos aqui
defmodule Pancaakes do
def pancake() do
    {:ok, fin} = File.open("small.in", [:read])
    #se lee la primera linea para calcular el numero de casos y se convierte a entero por que esta en string
    #la funcion strip quita cual espacion o tab que haya en la linea ya se al inicio o al final
    numcases = IO.read(fin, :line) |> String.strip |> String.to_integer
    #se va hacer este ciclo desde 1 hasta el numero de casos
    1..numcases |> Enum.each(fn(x)->
        #nos brincamos una linea en cada iteracion por que no la necesitamos
        ignore = IO.read(fin, :line)
        #se lee una linea y sin espacios al inicio o al final y se guarda en la variable str
        str = IO.read(fin, :line) |> String.strip
        #calcula los minutos que le toma a ese caso para que todos los pancakes se terminen
        minutes = calculatemin(str)
        #imprime el numero de caso y los minutos que le toma terminar todos los pacakes
        IO.puts( "case ##{x}: #{minutes}")
        #aqui termina el ciclo o la iteracion y vuelve a empezar
      end)

    File.close(fin)

  end

  def calculatemin(str) do
    #se guarda en una lista la linea que recibio y cada elemento que esta separado por espacio lo guarda en plates
    #y lo convierte a entero porque es un string en principio
    plates = str |> String.split(" ") |> Enum.map(&String.to_integer(&1))
    #calcula el numero maximo de pacakes que hay un todos los platos de la lista -plates-
    max = plates |> Enum.max
    limits = max..1
    #para este ejemplo son 1000 datos y contando el cero 10001
    smallsample=1000+1
    #reduce es un ciclo desde smallsimple, en donde cada iteracion se hace step=plates
    limits |> Enum.reduce(smallsample, fn(limit, min1)-> step = plates |> Enum.map(fn(plates)->
                    if (plate > limit) do
                      #regresa un entero dado un numero float redondeandolo hacia abajo
                      trunc(Float.ceil(plate/ limit)) - 1
                    else
                      0
                    end
                  end)
                #hace la suma de todo lo anterior
                |> Enum.sum
        #min es el num minimo de minitos necesarios para terminar todos los pacakes
        min = limits + step
        #si min es mayor que min1 regresa min1 sino regresa min
        if (min > min1), do: min1, else: min
        #termina el ciclo
      end)
      #termina la funcion
  end
#termina el modulo
end
