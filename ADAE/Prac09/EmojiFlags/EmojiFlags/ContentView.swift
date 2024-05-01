//
//  ContentView.swift
//  EmojiFlags
//
//  Created by Jake Jonas on 30/4/2024.
//

import SwiftUI

struct ContentView: View {
    
    @State var emojiData: [EmojiFlag] = []
    let url = URL(string: "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/index.json")!
    var body: some View {
        ScrollView {
            ForEach(emojiData, id: \.self) { country in
                HStack {
                    Text(country.emoji)
                        .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                    Text(country.name)
                        .font(.title)
                }
            }
        }
        .padding()
        .task {
            let task = URLSession.shared.dataTask(with: url) {
                (data, response, error) in if let data = data {
                    do {
                        emojiData = try JSONDecoder().decode([EmojiFlag].self, from: data)
                    }
                    catch {
                        print("oops")
                    }
                }
            }
            task.resume()
 
        }
    }
}

#Preview {
    ContentView()
}
