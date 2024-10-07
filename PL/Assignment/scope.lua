---@diagnostic disable: cast-local-type
function Add(num1, num2)
    Total = num1 + num2
    local total = num1 + num2
end
Add(10,4)
print(Total) --> 14
print(total) --> nil


local var = "Hello there!"
var = 5
var = {1,2,3,4}
var = true
var = nil

print(var)




