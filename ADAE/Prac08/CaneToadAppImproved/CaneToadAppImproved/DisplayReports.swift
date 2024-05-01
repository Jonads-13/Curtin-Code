//
//  DisplayReports.swift
//  CaneToadAppImproved
//
//  Created by Jake Jonas on 24/4/2024.
//

import SwiftUI

struct DisplayReports: View {
    var body: some View {
        let propertyListDecoder = PropertyListDecoder()
        
        let documentDirectory = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first!
        let archiveURL = documentDirectory.appendingPathComponent("reports_library")
            .appendingPathExtension("plist")
        
        if let retrievedReports = try? Data(contentsOf: archiveURL), let decodedReports = try? propertyListDecoder.decode([Report].self, from: retrievedReports) {
            let sortedReports = decodedReports.sorted {(first,second) -> Bool in
                return first.quantity > second.quantity
                
            }
            VStack {
                ScrollView {
                    ForEach(sortedReports) {report in
                        VStack {
                            Text("\(report.description)")
                            Spacer()
                        }
                    }
                }
            }
        }
    }
}

#Preview {
    DisplayReports()
}
