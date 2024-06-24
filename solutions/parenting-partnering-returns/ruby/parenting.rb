class Solve
  attr_reader :activities

  def initialize(activities)
    @activities = activities.map.with_index do |activity, i|
      activity.split(' ').map(&:to_i) << i
    end
  end

  def result
    j_endtime = 0
    c_endtime = 0
    results = []

    activities.sort_by(&:first).each do |activity|
    
      if j_endtime > activity.first
        return 'IMPOSSIBLE' if c_endtime > activity.first

        c_endtime = activity[1]
        results[activity.last] = 'C'
      else
        j_endtime = activity[1]
        results[activity.last] = 'J'
      end
    end

    results.join('')
  end
end

Tcases = gets.to_i
Tcases.times do |t|
  n = gets.chomp.to_i
  activities = n.times.map{ gets.chomp } 
  solver = Solve.new(activities)
  puts "Case ##{t.succ}: #{solver.result}"
end
