//
//  ContactsList.swift
//  AppControls
//
//  Created by Jake Jonas on 10/4/2024.
//

import SwiftUI

let fname = "<first name>"
let lname = "<last name>"

struct ContactsList: View {
    var body: some View {
        HStack {
            Image(systemName: "magnifyingglass")
                .padding(10)
            Text("Contacts")
                .font(.title)
                .frame(maxWidth: .infinity)
                .padding(10)
            Image(systemName: "cross.fill")
                .padding(10)
        }
            
        ScrollView {
            ForEach(0..<10) { num in
                HStack {
                    Image(systemName: "camera.circle")
                        .resizable()
                        .frame(width:75, height:75)
                        .padding(10)
                    Text("\(lname), \(fname)")
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                .border(Color.black)
            }
        }
    }
}

#Preview {
    ContactsList()
}
