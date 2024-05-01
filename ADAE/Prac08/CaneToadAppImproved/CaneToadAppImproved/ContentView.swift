//
//  ContentView.swift
//  CaneToadApp
//
//  Created by Jake Jonas on 24/4/2024.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        TabView {
            MapLocations()
                .tabItem {
                    Label("Map", systemImage: "map")
                }
            ReportView()
                .tabItem {
                    Label("Report", systemImage: "rectangle.and.pencil.and.ellipsis")
                }
            Information()
                .tabItem {
                    Label("Information", systemImage: "info.circle")
                }
            DisplayReports()
                .tabItem {
                    Label("View Reports", systemImage: "list.bullet.clipboard")
                }
        }
    }
}

#Preview {
    ContentView()
}
