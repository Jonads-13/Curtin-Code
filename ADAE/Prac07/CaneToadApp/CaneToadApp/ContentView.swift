//
//  ContentView.swift
//  CaneToadApp
//
//  Created by Jake Jonas on 23/4/2024.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        TabView {
            MapLocations()
                .tabItem { 
                    Label("Map", systemImage: "map")
                }
            Report()
                .tabItem { 
                    Label("Report", systemImage: "rectangle.and.pencil.and.ellipsis")
                }
            Information()
                .tabItem { 
                    Label("Information", systemImage: "info.circle")
                }
        }
    }
}

#Preview {
    ContentView()
}
