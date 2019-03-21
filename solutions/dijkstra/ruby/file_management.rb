class FileManagement

  require 'find'

  def read_file(root)
    lines = Array.new
    File.open(root,'r') do |input|
      input.each { |line| lines.push(line.gsub("\n","")) }
    end
    lines
  end

  def save_file(data,name)
    File.open(name,'w') do |output|
      output.puts(data)
    end
  end

end
