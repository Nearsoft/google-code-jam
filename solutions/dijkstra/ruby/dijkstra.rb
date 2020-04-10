class Dijkstra

  def initialize
    @multiplicationMatrix = {"11"=>"1","1i"=>"i","1j"=>"j","1k"=>"k",
                             "i1"=>"i","ii"=>"-1","ij"=>"k","ik"=>"-j",
                             "j1"=>"j","ji"=>"-k","jj"=>"-1","jk"=>"i",
                             "k1"=>"k","ki"=>"j","kj"=>"-i","kk"=>"-1"}
  end

  def evaluateInput(input)
    result=""
    t = input.shift.to_i
    if input.size == (t*2)
      for i in 0...t
        lx=input[i*2].split
        l = lx[0].to_i
        x = lx[1].to_i
        if l == input[(i*2)+1].size
          result << "Case ##{(i+1)}: #{dijkstra(getLX(input[(i*2)+1],x))}\n"
        else
          puts "Error: the test case is not the size that was given"
        end
      end
    else
      puts "Error: The number of test cases given is wrong"
    end
    result
  end

  private
  def getLX(l,x)
    x=x%4+24 if x > 24
    lx = l*x
  end

  def dijkstra(testCase)
    weExpect="ijk"
    i=0
    while !testCase.empty?
      if i < 3 && testCase[0] == weExpect[i]
        i+=1
        testCase = testCase[1..testCase.size]
      elsif testCase.size >= 2
        break if testCase.size == 2 && testCase[0] == "-"
        xy = getFirstElements(testCase)
        testCase.sub!(xy,getMultiplication(xy))
      else
        testCase="" if testCase == "1"
        break
      end
    end
    return "YES" if i == 3 && testCase.empty?
    return "NO"
  end

  def getFirstElements(value)
    return value[0..2] if value[0] == "-"
    return value[0..1]
  end

  def getMultiplication(xy)
    negative=false
    if xy.size == 3
      xy=xy[1..2]
      negative=true
    end
    res = @multiplicationMatrix[xy]
    return res = negate(res) if negative
    return res
  end

  def negate(value)
    return value[1..value.size] if value[0] == "-"
    return "-"+value
  end

end

require './file_management'

manager = FileManagement.new
input = manager.read_file("C-small-practice.in")
dij = Dijkstra.new
result = dij.evaluateInput(input)
puts result
manager.save_file(result,"result.txt")
