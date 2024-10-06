function Demo()
    local people = {
        {age = 22, job = "Developer", cool = false},
        {age = 30, job = "Actor", cool = true},
        {age = 19, job = "Cashier", cool = false}
    }

    for i = 1, #people do
        local p = people[i]
        if p.age > 18 and p.job == "Developer" or p.cool then
            print("approved")
        else
            print("rejected")
        end
    end
end

Demo()