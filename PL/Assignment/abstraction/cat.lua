require("animal")

-- Inherit from base class
Cat = Animal:new()

-- Child constructor
function Cat:new(o, name, age, breed, colour, furTexture)
    o = o or Animal:new(nil, name, age, breed, colour) -- Create from parent
    setmetatable(o, self)
    self.__index = self

    -- Assign child specific fields
    o.furTexture = furTexture
    return o
end

-- Override parent functions
function Cat:talk()
    print(self.name.." the "..self.breed.." goes \"meow\" at age "..self.age)
end

function Cat:move()
    print(self.name.." the cat is walking")
end