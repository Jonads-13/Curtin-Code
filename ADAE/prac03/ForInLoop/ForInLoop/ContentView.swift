//
//  ContentView.swift
//  ForInLoop
//
//  Created by Jake Jonas on 12/3/2024.
//

import SwiftUI

struct ContentView: View {
    @State var counter: Int = 1;
    @State var myViews = ["I Am Text!"]
    var body: some View {
        VStack {
            Button("Tap Me!") {
                counter += 1
                myViews.append("I Am Text!")
                if(counter > 5) {
                    counter = 1
                    myViews.removeAll()
                    myViews.append("I Am Text!")
                }
            }
            ForEach(myViews, id: \.self) {
                myView in Text(myView)
            }
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
