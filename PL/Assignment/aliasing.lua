local string = "hello"
local otherString = string
otherString = "there" --  doesn't change copied variable

print(string, otherString) --> hello    there


local tab = {message = "hi"}
local otherTab = tab
otherTab.message = "bye" -- does change copied variable

print(tab.message, otherTab.message) --> bye    bye

local otherOtherTab = otherTab
otherOtherTab.message = "I'm back" --> changes all

print(tab.message, otherTab.message, otherOtherTab.message) --> I'm back  I'm back  I'm back
