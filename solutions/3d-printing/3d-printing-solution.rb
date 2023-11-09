t = gets.to_i
maxUnits = 1000000

for c in 0..t-1
    print "Case ##{c+1}: "
    c_list = []
    m_list = []
    y_list = []
    k_list = []
    for p in 0..2
        inkValues = gets.split.map(&:to_i).to_a
        c_list << inkValues[0]
        m_list << inkValues[1]
        y_list << inkValues[2]
        k_list << inkValues[3]
    end
    mins = [c_list.min,m_list.min,y_list.min,k_list.min]
    sumMins = mins.sum
    if sumMins == maxUnits
        puts "#{mins[0]} #{mins[1]} #{mins[2]} #{mins[3]}"
    elsif sumMins < maxUnits
        puts "IMPOSSIBLE"
    else
        for i in 0..3
            sumMins = 0;
            for j in (i+1)..3
                sumMins+=mins[j]
            end
            if sumMins >= maxUnits
                mins[i] = 0
            else
                mins[i]=maxUnits-sumMins
                break
            end
        end
        puts "#{mins[0]} #{mins[1]} #{mins[2]} #{mins[3]}"
    end
end