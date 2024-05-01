//
//  ItemInfo.swift
//  MenuApp
//
//  Created by Jake Jonas on 31/3/2024.
//

import SwiftUI

let loremipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

struct ItemInfo: View {
    var body: some View {
        VStack {
                Image(systemName: "questionmark.circle")
                    .resizable()
                    .frame(width: 350, height: 350)
                Text("<Product Name>: $<Product Cost>")
            HStack {
                Text("Product Description")
                Text("Nutritional Information")
            }
            HStack (alignment: .top) {
                Text(loremipsum)
                    .multilineTextAlignment(/*@START_MENU_TOKEN@*/.leading/*@END_MENU_TOKEN@*/)
                    .fixedSize(horizontal: false, vertical: /*@START_MENU_TOKEN@*/true/*@END_MENU_TOKEN@*/)
                    .frame(width: 200)
                VStack(alignment: .leading) {
                    Text("Calories: <>")
                    Text("Sugars: <>")
                    Text("Fats: <>")
                    Text("Carbohydrates: <>")
                }
            }
        }
        .frame(maxHeight: .infinity, alignment: .top)
    }
}

#Preview {
    ItemInfo()
}
