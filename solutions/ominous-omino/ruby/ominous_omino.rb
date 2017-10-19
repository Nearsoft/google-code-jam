class OminousOmino
  def initialize()
  end
  def testCase(line)
    values = line.split(" ").map(&:to_i)
    puts values.inspect
  end
end


