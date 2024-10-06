---@diagnostic disable: unused-local

-- typical array
local array = {1,2,3,4,5}

-- Dictionary of names and roles
local dictionary = {Arlen = "Lecturer", Jordan = "Tutor", Jacob = "Student"}

-- Simple object of a Student
local student = {
    -- object fields
    Name = "Jacob",
    Age = 27,
    Classes = {},

    -- Methods for the object
    AddClass = function(self, class)
        table.insert(self.Classes, class)
    end,

    toString = function(self)
        local classes = ""
        local index = 1
        local length = #self.Classes -- '#' gets the length of a string or table

        while index <= length do
            classes = classes .. self.Classes[index].Name .. ": " .. self.Classes[index].Code .. ", "
            index = index + 1
        end

        return self.Name .. ", " .. self.Age .. ", " .. classes
    end
}

-- Calling the methods on the object
student.AddClass(student, {Name = "Programming Languages", Code = "COMP2007"})
student.AddClass(student, {Name = "Digital Design 1", Code = "GRDE1016"})

print(student.toString(student)) --> Jacob, 27, Programming Languages: COMP2007, Digital Design 1: GRDE1019,


