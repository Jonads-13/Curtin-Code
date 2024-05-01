//
//  Information.swift
//  CaneToadApp
//
//  Created by Jake Jonas on 24/4/2024.
//

import SwiftUI

struct Information: View {
    
    struct InfoSection: Identifiable {
        var id = UUID()
        var value: String
        var children: [InfoSection]?
    }
      
    let sections: [InfoSection] = [
        InfoSection(value: "How to Approach Cane Toads", children: [InfoSection(value:"It's best to approach toads from the front, as the torch light 'stuns' them, making them sit still. We recommend picking up Cane Toads by holding around the shoulders or body. Place Cane Toad in your bucket, making sure they can't jump out!")]),
        InfoSection(value: "How to Dispose of Cane Toads", children: [InfoSection(value:"Yeet the toad.\nAlternatively, to dispose of a toad humanely, put your bagged toad in the refrigerator at four degrees for an hour so that it becomes unconscious. Then transfer the toad to the freezer for at least 16 hours. You can bury the toad or put it in the compost as it is a safe natural fertiliser.")])]
    var body: some View {
        VStack {
            Text("Information")
                .font(.title)
            List(sections, children: \.children) { row in
                HStack {
                    Text(row.value)
                }
            }
        }
    }
}

#Preview {
    Information()
}
