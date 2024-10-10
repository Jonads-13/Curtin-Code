-- All data types can be assigned the same way
local num = 5
local str = "hi"
local boolean = true
local arr = {1,2,3}
local func = function()
        print("I'm a function")
    end

-- All can be placed inside a table
local tab = {num, str, boolean, arr, func}

-- All data types can be indexed the same
print(tab[1])
print(tab[2])
print(tab[3])
print(tab[4][1])
print(tab[5]())

-- Can pass functions as parameters to other funcitons
-- just like any other type
function Test(otherFunc)
    otherFunc() -- Calls the imported function
end

Test(func) --> I'm a function
