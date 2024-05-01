//
//  ContentView.swift
//  FunctionTest
//
//  Created by Jake Jonas on 19/3/2024.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            Button("cutAndAdd") {
                print(cutAndAdd(firstNumber: 4.6, secondNumber: 2.1))
            }
                .foregroundStyle(.green)
                .padding(5)
            Button("helloNumbers") {
                print(helloNumbers(theText: "hello", theNumber: 6))
            }
            .foregroundStyle(.red)
            .padding(5)
            Button("multipleReturns") {
                print(multipleReturns(theNumber: 4))
            }
            .foregroundStyle(.blue)
            .padding(5)
            Button("toBePrinted") {
                printMeOut(toBePrinted: "Hello world")
            }
            .foregroundStyle(.purple)
            .padding(5)
        }
        .padding()
    }
}

func cutAndAdd(firstNumber: Double, secondNumber: Double) -> Int {
    return Int(firstNumber + secondNumber)
}

func helloNumbers(theText: String = "", theNumber: Int = 0) -> String {
    var result: String;
    if theNumber % 2 == 0 {
        result = theText
    }
    else {
        result = "Nope"
    }
    return result
}

func multipleReturns(theNumber: Int = 1) -> (Int, Int) {
    return (theNumber*theNumber, theNumber)
}

func printMeOut(toBePrinted: String = "") {
    print(toBePrinted)
}

#Preview {
    ContentView()
}
