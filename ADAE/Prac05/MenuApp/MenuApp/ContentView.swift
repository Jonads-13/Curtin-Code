//
//  ContentView.swift
//  MenuApp
//
//  Created by Jake Jonas on 31/3/2024.
//

import SwiftUI

let placeholder = "questionmark.circle"

struct ContentView: View {
    var body: some View {
        ScrollView {
            LazyVStack {
                HStack {
                    Image(systemName: placeholder)
                        .resizable()
                        .frame(width: 150, height: 150)
                        .padding()
                    VStack {
                        Text("<Prodcut Name>")
                        Text("$<Product Cost>")
                    }
                }
                
                HStack {
                    Image(systemName: placeholder)
                        .resizable()
                        .frame(width: 150, height: 150)
                        .padding()
                    VStack {
                        Text("<Prodcut Name>")
                        Text("$<Product Cost>")
                    }
                }
                
                HStack {
                    Image(systemName: placeholder)
                        .resizable()
                        .frame(width: 150, height: 150)
                        .padding()
                    VStack {
                        Text("<Prodcut Name>")
                        Text("$<Product Cost>")
                    }
                }
                
                HStack {
                    Image(systemName: placeholder)
                        .resizable()
                        .frame(width: 150, height: 150)
                        .padding()
                    VStack {
                        Text("<Prodcut Name>")
                        Text("$<Product Cost>")
                    }
                }
                
                HStack {
                    Image(systemName: placeholder)
                        .resizable()
                        .frame(width: 150, height: 150)
                        .padding()
                    VStack {
                        Text("<Prodcut Name>")
                        Text("$<Product Cost>")
                    }
                }
            }
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
