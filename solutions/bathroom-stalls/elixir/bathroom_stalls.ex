defmodule BathroomStalls do

  def st do
    numberOfInputsAndList = getFileTextInformation()
    {:ok, file} = File.open "output.out", [:write]
    loopNumbers(1,String.to_integer(hd(numberOfInputsAndList)), hd(tl(numberOfInputsAndList)), file)
    File.close file
  end

  def getFileTextInformation do
    fileText = String.split(File.read!("input.in"),"\n")
    [hd(fileText), tl(fileText)]
  end

  def loopNumbers(caseNumber, numberId, data, file) do
    if numberId > 0 do
      numbersToWorkOn = hd(data)
      values = String.split(numbersToWorkOn," ")
      numberOfStalls = String.to_integer(hd(values))
      numberOfPeople = String.to_integer(hd(tl(values)))
      answer = solveBathroomStalls(numberOfStalls, numberOfPeople)
      IO.binwrite file, "Case #" <> Integer.to_string(caseNumber) <> ": #{answer}\n"
      loopNumbers(caseNumber+1,numberId-1,tl(data), file)

    end
  end

  def solveBathroomStalls(stalls, people) do
    if people == 1 do
      left = div(stalls, 2)
      right = div(stalls - 1, 2)
      "#{left} #{right}"
    else
      if rem(people, 2) == 0 do
        solveBathroomStalls(div(stalls, 2), div(people, 2))
      else
          solveBathroomStalls(div(stalls - 1, 2), div(people, 2))
      end
    end
  end
end
