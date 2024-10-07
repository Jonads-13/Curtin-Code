function Demo()
    local people = {
        {age = 22, job = "Developer", cool = false},
        {age = 30, job = "Actor", cool = true},
        {age = 19, job = "Barista", cool = false},
        {age = 15, job = "Musician", cool = true}
    }
    for i = 1, #people do -- loop through table
        local p = people[i]
        if p.age > 18 and (p.job == "Developer" or p.cool) then
            print("approved")
        elseif p.job ~= "Barista" then
            print "It is acceptable"
        else
            print("rejected")
        end
    end
end

function MultipleReturnValues(num1, num2)
    return num1^2, num2^2 -- square of both numbers
end

local square1, square2 = MultipleReturnValues(3, 4)
print(square1, square2) --> 9.0    16.0

Demo() --> approved approved rejected rejected