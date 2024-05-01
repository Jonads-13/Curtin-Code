//
//  ContentView.swift
//  IfStatements
//
//  Created by Jake Jonas on 12/3/2024.
//

import SwiftUI

struct ContentView: View {
    @State var counter: Int  = 0
    var body: some View {
        VStack {
            switch counter{
            case 0:
                Text("Colourful Text!")
                    .foregroundStyle(.green)
            case 1:
                Text("Colourful Text!")
                    .foregroundStyle(.blue)
            case 2:
                Text("Colourful Text!")
                    .foregroundStyle(.red)
            case 3:
                Text("Colourful Text!")
                    .foregroundStyle(.orange)
            case 4:
                Text("Colourful Text!")
                    .foregroundStyle(.yellow)
            case 5:
                Text("Colourful Text!")
                    .foregroundStyle(.purple)
            default :
                Text("Colourful Text!")
                    .foregroundStyle(.green)
            }
            
            Button("Change Colour") {
                counter += 1
                if(counter > 5) {
                    counter = 0
                }
            }
            
            
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
