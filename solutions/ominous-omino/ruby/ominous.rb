require_relative 'ominous_omino'
require_relative 'input_manager'

if(ARGV[0])

# ARGV.each do|a|
  inputManager = InputManager.new(ARGV[0])
  ominous = OminousOmino.new()
  inputManager.getLine()
  # while line=inputManager.getLine()
  #   ominous.testCase(line)
  # end
end