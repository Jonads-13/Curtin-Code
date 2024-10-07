require("animal")

-- Inherit from base class
Snake = Animal:new()

-- Child constructor
function Snake:new(o, name, age, breed, colour, isPoisonous)
    o = o or Animal:new(nil, name, age, breed, colour)
    setmetatable(o, self)
    self.__index = self

    -- Assign child specific field
    o.isPoisonous = isPoisonous
    return o
end

-- Override parent functions
function Snake:talk()
    print(self.name.." the "..self.breed.." goes \"hisssss\" at age "..self.age)
end

function Snake:move()
    print(self.name.." the snake is slithering")
end