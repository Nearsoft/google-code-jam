defmodule M do

  def main do
    read("small-output.txt","C-small-practice.in")
    #read("large-output.txt","C-large-practice.in")
  end

  def algorithm(w,n) do
    answear = ""
    # n=1
    # w="ikikijkjijiikkikjjjkkkjjiiiikkjkjkjikjkikjikjkkjkjkjkikkjjiikjiijiiiikijikjjjkkjikjjijiikjikikjjkjikikikkkjiikjkjjikkjkijkkjkikikikkiijiiijjiikjikikkkjjkjkjjjkiiikkjkjiikkjjjkkkkjikkjjiijkjkjkjiijijjjikkijiijjjjkjiikjikijjiikikjijjjjikikkkikjjiikikjkikkjjkkjjiikikiiikkjikikkjkkkkiikkjjkjjkjjjjjjikkkjikjkkjkjjkkikikikiijijijkikjijikjjkjikikiiikjikkikikikkjjiikiikkkjjiiikjjkkkiikikkkjikjikkjkkjjkijijikkikkjijjkijkikkjikjjjiikikikjikijjkkijjkkjijijkjijijkikkkjjikkjjjjkkjkjkjikkikkjjiikkikjjjikkjkijkijiikikijjkkijkjkiijikjikikijiikkjjijikjkkjjkkjjjijjiiikijijjjiikikkjikjjikjikiikjjkiijjikjikkkjkkkkkjjkjkkjijikikjkikjiikjiiijkjikijikkkjkjjikkjijiikkiikkikkjjijikijjiijiiiiikjjikikiikjjiikkiikkkjjikijiiikikikkjjiiiiikikkijkkkiijiijjiikijikjijiikikjiikjkkkjiikiiikiijiikijjikjkji"
    #
    word = String.duplicate(w,n)
    arr = String.codepoints(word)
    arrIndex = arr |> Enum.with_index

    ijk = getValue(Enum.reduce(arrIndex,0,fn({x,i},acc)->
      acc = mult(acc,getIndex(x))
    end))

    #IO.puts ijk
    if ijk != "-1" do
      answear = "NO"
    else

    x = Enum.reduce_while(arrIndex,0,fn({x,i},acc)->
      if i<length(arrIndex) and i>-1 do
        acc = mult(acc,getIndex(x))
        #IO.puts "acc #{getValue(acc)}"
        if acc == getIndex("i") do
          #IO.puts "Encontre la i."

          arrj = Enum.take(arr,i-length(arr)+1)
          arrIndexj = arrj |> Enum.with_index
          y = Enum.reduce_while(arrIndexj,0,fn({x,j},accj)->
            if j<length(arrIndexj) and j>-1 do
              accj = mult(accj,getIndex(x))
              #IO.puts "acc #{getValue(accj)}"
              if accj == getIndex("j") and ijk == "-1"do
                #IO.puts "Encontre la j. DETENTE"
                {:halt,-1}

              #   arrk = Enum.take(arrj,j-length(arrj)+1)
              #   arrIndexk = arrk |> Enum.with_index
              #   z = Enum.reduce_while(arrIndexk,0,fn({x,k},acck)->
              #     if k<length(arrIndexk) and k>-1 do
              #       acck = mult(acck,getIndex(x))
              #       #IO.puts "acc #{getValue(acck)}"
              #       if acck == getIndex("k") and length(Enum.take(arrj,k-length(arrk)+1))==0 do
              #         #IO.puts "Encontre la k. DETENTE"
              #         {:halt,-1}
              #       else
              #         {:cont,acck}
              #       end
              #     end
              #   end)
              #
              #   if z==-1 do
              #   {:halt,-1}
              # else
              #   {:cont,accj}
              # end
            else
              {:cont,accj}
            end
          end
          end)

          #IO.puts "y = #{y}"

          if y>-1 do
            {:cont,acc}
          else
            {:halt,-1}
          end
          # if y==-1 do
          #   {:halt,-1}
          # else
          #   {:cont, acc}
          # end
        else
          {:cont, acc}
        end
      end
    end)

    # Enum.reduce_while(1..100,0,fn i,acc ->
    #   x = main()
    #   if i < 3 do
    #     {:cont, acc + i}
    #   else
    #     {:halt, acc}
    #   end
    # end)

    if x == -1 do
      answear = "YES"
    else
      answear = "NO"
    end


  end#123
  answear
  end


  def test(w,n,index,filename) do
    #IO.puts "index: #{index}  string: #{w}     n:#{n}     "
    s = "Case ##{index}: #{algorithm(w,n)}\n"
    IO.puts s
    File.write(filename, s, [:append])
  end



  def mult(a, b) do
      tabla =  [["1", "i", "j", "k", "-1", "-i", "-j", "-k"],
                ["i", "-1", "k", "-j", "-i", "1", "-k", "j"],
                ["j", "-k", "-1", "i", "-j", "k", "1", "-i"],
                ["k", "j", "-i", "-1", "-k", "-j", "i", "1"],
                ["-1", "-i", "-j", "-k", "1", "i", "j", "k"],
                ["-i", "1", "-k", "j", "i", "-1", "k", "-j"],
                ["-j", "k", "1", "-i", "j", "-k", "-1", "i"],
                ["-k", "-j", "i", "1", "k", "j", "-i", "-1"]]

       s = Enum.at(Enum.at(tabla, a),b)

       getIndex(s)

   end

   def getIndex(myvar) do
     arrIndex = ["1", "i", "j", "k", "-1", "-i", "-j", "-k"] |> Enum.with_index
     index = Enum.reduce(arrIndex,-1,fn({x,i},acc) ->
       if String.equivalent?(myvar, x) do
         acc = i
       end
       acc
     end)
     index
   end

   def getValue(index) do
     Enum.at(["1", "i", "j", "k", "-1", "-i", "-j", "-k"],index)
   end

   def read(fileoutput, fileinput) do
     File.write(fileoutput, "")
 		x = File.stream!("C-small-practice.in")
 	  |> Stream.map(&String.strip/1)

    x = Enum.take(x,-200)
 	  IO.inspect Enum.chunk_every(x,2) |> Enum.with_index
    |> Stream.map(fn ({[l1,l2], index}) ->
	      list_of_inputs = String.split(l1)
	      n = String.to_integer(Enum.at(list_of_inputs, 1))
        test(l2,n,index+1,fileoutput)
	  end)
  	|> Stream.run

    end

end
