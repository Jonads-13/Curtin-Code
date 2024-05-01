import UIKit

let myName = "Jacob Jonas"
var wholeDivisionFactor = 2
var numberToDivide = 5.2
var anotherNumberToDivide = 4
let isThisBoring = false

//let result1: Int = anotherNumberToDivide/wholeDivisionFactor
//let result2: Double = numberToDivide/wholeDivisionFactor
//let result3: Double = wholeDivisionFactor * numberToDivide
//let result4: Int = anotherNumberToDivide + wholeDivisionFactor
//let result5: Double = anotherNumberToDivide - numberToDivide

let result1: Int = anotherNumberToDivide/wholeDivisionFactor
let result2: Double = numberToDivide/Double(wholeDivisionFactor)
let result3: Double = Double(wholeDivisionFactor) * numberToDivide
let result4: Int = anotherNumberToDivide + wholeDivisionFactor
let result5: Double = Double(anotherNumberToDivide) - numberToDivide

print("\(anotherNumberToDivide) / \(wholeDivisionFactor) = \(result1)")
print("\(numberToDivide) / \(wholeDivisionFactor) = \(result2)")
print("\(wholeDivisionFactor) x \(numberToDivide) = \(result3)")
print("\(anotherNumberToDivide) + \(wholeDivisionFactor) = \(result4)")
print("\(anotherNumberToDivide) - \(numberToDivide) = \(result5)")

var bigDivisionRemainder = 379 % 13

print("\nThe remainder or dividing 379 by 13 is \(bigDivisionRemainder)\n")

var arr: [Int] = [0,1,1,2,3,5,8]

arr.append(arr[(arr.count-1)] + arr[(arr.count-2)])
print(arr)

arr.removeLast()
print(arr)

print(arr[4])

var unitsAndNames = ["COMP2010": "App Development for the Apple Ecosystem",
                     "WORK3008": "Work Integrated Learning",
                     "ICTE3002": "Human Computer Interface",
                     "COMP3001": "Design and Analysis of Algorithms"]

print("\n\(unitsAndNames)")

if let appleDevKey = unitsAndNames["COMP2010"]
{
    unitsAndNames.updateValue("The Apple Ecosystem", forKey: "COMP2010")
}

if let pdiKey = unitsAndNames["COMP1007"]
{
    unitsAndNames.updateValue("Prog Des & Impl (Java)", forKey: "COMP1007")
}

print("\n")

print(unitsAndNames)

var array = [Int](repeating: 0, count: 10)

for i in 0..<array.count {
    array[i] = (i*i)
}

print(array)





