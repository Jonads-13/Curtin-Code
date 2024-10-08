-- Multiple declarations on one line
local num, string, bool, dict = 1, "hello", true, {message = "cool stuff", value = 4}

-- Adding a new key, value pair to an existing table
dict.newValue = 3

local array = {2,4,6,8,10}

-- Simple functions for manipulating tables
table.insert(array, 12) --> {2,4,6,8,10,12}
table.remove(array, 2) --> {2,6,8,10,12}
table.insert(array, 2, 0) --> {2,0,6,8,10,12}
table.sort(array) --> {0,2,6,8,10,12}

-- string of all tables values joined with a
string = table.concat(array, ", ") --> "0, 2, 6, 8, 10, 12"

-- If step value is 1, then don't need to specify it
for i = 1, #array do
    print(array[i])
end

-- For-each style loop over a table with key,value pairs
for k,v in pairs(dict) do
    print(k,v)
end

local v1 = {x = 3, y = 4} -- Define table

setmetatable(v1, { -- Overload the '+' operator
    __add = function(a, b)
        return {x = a.x + b.x, y = a.y + b.y}
    end
})

local v2 = {x = 1, y = 1}

local vector = v1 + v2 -- Can now add tables with x and y values together
print(vector.x, vector.y) --> 4     5

local a = 5
local b = 3
print(a,b) --> 5    3

a,b = b,a -- swap two numbers
print(a,b) --> 3    5
