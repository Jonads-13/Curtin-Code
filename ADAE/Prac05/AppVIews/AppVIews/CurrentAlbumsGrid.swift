//
//  CurrentAlbums.swift
//  AppVIews
//
//  Created by Jake Jonas on 31/3/2024.
//

import SwiftUI

let columns = [
    GridItem(.flexible()),
    GridItem(.flexible())
]

struct CurrentAlbumsGrid: View {
    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns) {
                Image("aggretsuko")
                    .resizable()
                    .frame(width: 150, height: 150)
                    .padding()
                Text("Aggretsuko")
                
                Image("air gear")
                    .resizable()
                    .frame(width: 150, height: 150)
                    .padding()
                Text("air gear")
                
                Image("Blazing_Beat_promo_single")
                    .resizable()
                    .frame(width: 150, height: 150)
                    .padding()
                Text("Blazing_Beat_promo_single")
                
                Image("bna ost")
                    .resizable()
                    .frame(width: 150, height: 150)
                    .padding()
                Text("bna ost")
                
                Image("brave shine")
                    .resizable()
                    .frame(width: 150, height: 150)
                    .padding()
                Text("brave shine")
                
                Image("Darling ost")
                    .resizable()
                    .frame(width: 150, height: 150)
                    .padding()
                Text("Darling ost")
                
                Image("dbztheme")
                    .resizable()
                    .frame(width: 150, height: 150)
                    .padding()
                Text("dbztheme")
                
                Image("Infinity")
                    .resizable()
                    .frame(width: 150, height: 150)
                    .padding()
                Text("Infinity")
                
                
            }
        }
    }
}

#Preview {
    CurrentAlbumsGrid()
}
