function GetUserInput()
    local num = io.read()
    return tonumber(num) -- returns nil if not a valid number
end

function Add(num1, num2)
    return num1 + num2 -- will cause error doing arithmetic with a nil value
end

local num = GetUserInput()
local status, result = pcall(Add, num, num) -- protected call
if not status then -- Error occurred
    print "Oopsie"
    print(result) -- Prints error message
else -- Succcess, no error
    print "Yippee"
    print(result) -- Prints output of function that was called
end