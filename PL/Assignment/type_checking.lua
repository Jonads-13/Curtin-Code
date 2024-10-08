local num1, num2 = 1, 2

print(num1 + num2)

num2 = "now a string" -- this is allowed
print(num1 + num2) --> crash for adding number with string

local tab1, tab2 = {}, {}
print(tab1 + tab2) --> crash for trying to add tables together