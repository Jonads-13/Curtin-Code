//
//  CurrentAlbums.swift
//  AppVIews
//
//  Created by Jake Jonas on 27/3/2024.
//

import SwiftUI

let albumNames = [
    "aggretsuko",
    "air gear",
    "Blazing_Beat_promo_single",
    "bna ost",
    "brave shine",
    "Darling ost",
    "dbztheme",
    "Infinity"
]

struct CurrentAlbumsList: View {
    var body: some View {
        ScrollView {
            ForEach(albumNames, id: \.self) {
                album in HStack {
                    Image(album)
                        .resizable()
                        .frame(width: 150, height: 150)
                        .padding()
                    Text(album)
                        .frame(maxWidth: .infinity)
                }
            }
        }
    }
}

#Preview {
    CurrentAlbumsList()
}
