class InputManager

  #opens the input file and create an array with all cases to evaluate
  #params:
  # file_name: name of input file
  def initialize(file_name)
    File.open(file_name) do |file|
      if(@totalCases = file.gets)
        @cases = Array.new
        while line = file.gets
          @cases.push line
        end
      end
    end
  end
  #returns next line of input
  def getLine
    if !@cases.empty?
      @cases.shift
    end
  end
end