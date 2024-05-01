//
//  ContentView.swift
//  ForAndIf
//
//  Created by Jake Jonas on 13/3/2024.
//

import SwiftUI

struct ContentView: View {
    @State private var counter: Int = 1;
    @State private var myViews = [Color.green: "I Am Text!"]
    let colours = [Color.green, Color.red, Color.blue, Color.yellow, Color.purple]
    var body: some View {
        VStack {
            Button("Tap Me!") {
                counter += 1
                myViews[colours[counter-1]] = "I Am Text!"
                if(counter == 5) {
                    counter = 1
                    myViews.removeAll()
                    myViews[colours[counter-1]] = "I Am Text!"
                }
            }
            
            ForEach(Array(myViews.keys), id: \.self) {
                key in Text(myViews[key] ?? "I Am Text!")
                    .foregroundStyle(key)
            }
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
