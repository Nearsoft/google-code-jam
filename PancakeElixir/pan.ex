defmodule PC do

  def Star do
    numberOfInputsAndList = getFileTextInformation()
    {:ok, file} = File.open "output.txt", [:write]
    looppancakes(1,String.to_integer(hd(numberOfInputsAndList)), hd(tl(numberOfInputsAndList)), file)
    File.close file
  end

  def getFileTextInformation do
    fileText = String.split(File.read!("input.in"),"\n")       
    [hd(fileText), tl(fileText)]
  end
  
  def looppancakes(caseNumber, numberId, listOfcase, file) do
    if numberId > 0 do
      pancakeToWorkOn = hd(listOfcase)                    
      resultString=String.split(flip(0,pancakeToWorkOn,0)," ")
      #IO.puts(List.last(resultString))
      pancake = hd(resultString);
      listlength =length(resultString)
      result = 
      if pancake =~ "-" do
        "IMPOSSIBLE"
      else    
        List.last(resultString)    
      end     
      if listlength ==2 do
        if pancake =~ "-" do
        IO.binwrite file, "Case #" <> Integer.to_string(caseNumber) <> ": " <> "IMPOSSIBLE" <> "\n"  
        else
                IO.binwrite file, "Case #" <> Integer.to_string(caseNumber) <> ": " <> "0" <> "\n"  
        end
      else
        IO.binwrite file, "Case #" <> Integer.to_string(caseNumber) <> ": " <> result <> "\n"  
      end                  
      looppancakes(caseNumber+1,numberId-1,tl(listOfcase), file)

    end
  end

def flip(pos,par,con) do    
   y=String.split(par," ");
    pancake=hd(y)   
    lengthflip=String.to_integer(hd(tl(y)))      
    if pos<= String.length(pancake)-lengthflip do
    
    if (String.at(pancake,pos) === "-") do              
       
        begin=
        if (pos>0) do
         String.slice(pancake,0,pos)         
        else
        ""
        end        
        change =turnPancakes(String.slice(pancake,pos,lengthflip),Integer.to_string(0))       
       final =
        if(pos<String.length(par)) do     
         String.slice(pancake,pos+lengthflip,String.length(par))         
        else
        ""
        end        
       flip(pos+1, begin<>change<>final<> " "<>Integer.to_string(lengthflip)<>" "<> Integer.to_string(con+1),con+1 )     
    else                
       flip(pos+1,par,con)
    end
    else       
    IO.puts(par)
    par
    
  end
  
end


  def turnPancakes(pancake,pos) do
   
    if (String.to_integer(pos) < String.length(pancake) ) do     
     if (String.at(pancake,String.to_integer(pos))=="-") do              
       "+" <>  turnPancakes(pancake,Integer.to_string(String.to_integer(pos)+1))             
     else 
        "-" <> turnPancakes(pancake,Integer.to_string(String.to_integer(pos)+1))
      end 
      else
      ""   
    end
  end



end