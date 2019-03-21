defmodule Main do
    def start do
        t= IO.gets("")  |> String.trim |> String.to_integer
        Main.testCases(0,t)    
    end
    def testCases(i,t) when i==t do

    end
    def testCases(i,t) do
        n=IO.gets("")  |> String.trim |> String.to_integer
        r=IO.gets("")  |> String.trim |> String.to_integer
        c=IO.gets("")  |> String.trim |> String.to_integer
        arr=[r,c]
        min=Enum.min(arr)
        max=Enum.max(arr)
        
        if (rem(r*c,n)) !=0 do
            Main.winRich(t)
        else
            if(n>=7) do
                Main.winRich(t)
            else
                if (n==6 )do
                    if min<=3 do
                        Main.winRich(t)
                    else
                        Main.winGab(t)
                    end
                else
                    if(n==5) do
                        if(min<=2) do
                            Main.winRich(t)
                        else
                             Main.winGab(t)
                        end
                        if(min==3) do
                            if(max==5) do
                                Main.winRich(t)
                            else
                                 Main.winGab(t)
                            end
                        end
                    else
                        if(n==4) do
                            if(min<=2) do
                                Main.winRich(t)
                            else
                                 Main.winGab(t)
                            end
                        else
                            if(n==3) do
                                if(min <= 1) do
                                    Main.winRich(t)
                                else
                                    Main.winGab(t)
                                end
                            else
                                 Main.winGab(t)
                            end
                        end
                    end
                end
            end
       
        end
        testCases(i+1,t)
    end  
    def winRich(t) do
        IO.puts("Case ##{t}: RICHARD");
    end
    def winGab(t) do
        IO.puts("Case ##{t}: GABRIEL");
    end
end

Main.start