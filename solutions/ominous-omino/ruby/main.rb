require_relative 'ominous_omino'
require_relative 'input_manager'

if(ARGV[0])

# ARGV.each do|a|
  inputManager = InputManager.new(ARGV[0])
  ominous = OminousOmino.new()
  i=0
  while line = inputManager.getLine()
    puts "Case #"+(i+=1).to_s+": "+ominous.testCase(line)

  end
end