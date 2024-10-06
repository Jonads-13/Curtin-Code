-- Both of these are type: 'number'
local int = 1
local float = 1.5

local string = "hello"
local boolean = true
local table = {}
local nil_value = nil

-- Programmer defined data structure
local linked_list = {next = nil, data = 12}

-- Function as variable
local AddFunc = function(num1, num2) 
    return num1 + num2
end

function Dostuff(func)
    local num1, num2 = 2, 3
    local product = num1 * num2

    print(func(product, num2))
end

Dostuff(AddFunc)
-- Linked List functions
function InsertFirst(list, data)
    return {next = list, data = data}
end

function InsertLast(list, data)
    local cur = list

    while cur.next ~= nil do -- Traverse list to last node
        cur = cur.next
    end
    cur.next = {next = nil, data = data} -- Append a new "list node"

    return list
end

function PrintList(list)
    local cur = list

    while cur do -- Traverse list
        print(cur.data)
        cur = cur.next
    end
end

linked_list = InsertLast(linked_list, 14)
linked_list = InsertFirst(linked_list, 20)
linked_list = InsertFirst(linked_list, 16)
linked_list = InsertFirst(linked_list, 10)
linked_list = InsertLast(linked_list, 50)

PrintList(linked_list)
