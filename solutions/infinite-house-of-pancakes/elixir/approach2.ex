defmodule Main do
    def _start do
        t = IO.gets("")    |> String.trim |> String.to_integer
        Main.testCases(t,0,[])
    end    
    def testCases(t,i,listPlates) when t==i do
        
    end
    def testCases(t,i,listPlates) do
        d = IO.gets("")    |> String.trim |> String.to_integer
        Main.dinners(0,d,[],t)
        Main.testCases(t,i+1,listPlates)
    end
    def dinners(i,max,plates,t) when i==max do
        llenaPlatos(0,1005,[],plates,t)
    end
    def dinners(i,max,plates,t) do
        p = IO.gets("")    |> String.trim |> String.to_integer
        Main.dinners(i+1,max,plates++[p],t)
    end
    def llenaPlatos(i,max,counts,plates,t) when i==max do
        Main.activaB(0,counts,plates,t)
    end
    def llenaPlatos(i,max,counts,plates,t) do
        Main.llenaPlatos(i+1,max,counts++[0],plates,t)
    end
    def activaB(i,counts,plates,t) when i==length(plates) do
        Main.algorithm(1,10000,0,counts,0,t)
    end
    def activaB(i,counts,plates,t) do
        Main.activaB(i+1,List.insert_at(counts, Enum.at(plates,i), 1),plates,t)
    end
    def algorithm(i,minMoves,moves,counts,vieneJ,t) when i==length(counts) do
        IO.puts("Case ##{t}: #{minMoves}");
    end
    def algorithm(i,minMoves,moves,counts,vieneJ,t) do
        if(vieneJ==0) do
            Main.jCicle(0,minMoves,0,counts,i,vieneJ,t)
        else
            if(moves+i<minMoves) do
                Main.algorithm(i+1,moves+1,0,counts,0,t)

            else
                Main.algorithm(i+1,minMoves,0,counts,0,t)
            end
        end
    end
    def jCicle(j,minMoves,moves,counts,i,vieneJ,t) when j==length(counts) do
        Main.algorithm(i,minMoves,moves,counts,1,t)
    end
    def jCicle(j,minMoves,moves,counts,i,vieneJ,t) do
        Main.jCicle(j+1,minMoves,(moves+(((j-1)/i)*Enum.at(counts,j))),counts,i,vieneJ,t)
    end
end
Main._start