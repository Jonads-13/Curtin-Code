-- Source "classes" from other files
require("cat")
require("snake")

local cat = Cat:new(nil, "Bajo", 12, "Calico", "White", "Soft")
local snake = Snake:new(nil, "Nate", 4, "Anaconda", "Green", false)

cat:talk() --> Bajo the Calico goes "meow" at age 12
cat:move() --> Bajo the cat is walking
snake:talk() --> Nate the Anaconda goes "hisssss" at age 4
snake:move() --> Nate the snake is slithering