function Test()
    print("Hello")
    coroutine.yield() --> halts execution
    print("everyone")
end

local co = coroutine.create(Test)
coroutine.resume(co)
print("there")
coroutine.resume(co) 
-- Prints Hello there everyone