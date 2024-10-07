-- Base class definition
Animal = {name = "", age = 0}

-- Base class constructor
function Animal:new(o, name, age, breed, colour)
    o = o or {}
    setmetatable(o, self)
    self.__index = self
    o.name = name
    o.age = age
    o.breed = breed
    o.colour = colour
    return o
end

-- base class methods
function Animal:move()
    print(self.name .. " is moving")
end

function Animal:talk()
    print(self.name .. " is talking")
end
