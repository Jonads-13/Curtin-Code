local string = "hello there"
local array = {"hello", "there", "general", "kenobi"}


function SayHello(message)
    Global = "why can I do this?"
    print(message)
end

function Loop(list)
    for k,v in pairs(list) do -- really?
        print(k..v)
    end
end


local age = 25
local ternary = age < 40 and "young" or "old"

SayHello(string)
Loop(array)

print(ternary)

print(Global) -- This should be illegal

print("hello".."world")
print(8+9)
print(#array) -- Length