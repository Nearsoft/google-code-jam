require_relative 'ominous_omino'
require_relative 'input_manager'

if(ARGV[0])
  inputManager = InputManager.new(ARGV[0])
  ominous = OminousOmino.new()
  i=0
  File.open('output/result.txt', 'w') { |file|
    while line = inputManager.getLine()
      file.puts "Case #"+(i+=1).to_s+": "+ominous.testCase(line)
    end
  }
end