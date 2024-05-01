//
//  ContentView.swift
//  AppVIews
//
//  Created by Jake Jonas on 27/3/2024.
//

import SwiftUI

struct ContentView: View {
    @State var test = [1,2,3,4,5]
    var body: some View {
        NavigationView() {
            Text("List of Products")
                .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                .foregroundStyle(.white)
                .frame(maxWidth: .infinity)
                .background(.black)
            VStack {
                ScrollView {
                    ForEach(test, id: \.self) { item in
                        NavigationLink(destination: NewAlbum()) {
                            HStack {
                                Image(systemName: "circle")
                                Text("Add New Album")
                            }
                        }
                    }
                    
                    NavigationLink(destination: CurrentAlbumsList()) {
                        Text("View Current Albums (List)")
                    }
                    NavigationLink(destination: CurrentAlbumsGrid()) {
                        Text("View Current Albums (Grid)")
                    }
                }
            }
        }
    }
}

#Preview {
    ContentView()
}
