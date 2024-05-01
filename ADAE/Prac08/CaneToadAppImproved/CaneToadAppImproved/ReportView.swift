//
//  Report.swift
//  CaneToadApp
//
//  Created by Jake Jonas on 24/4/2024.
//

import SwiftUI

struct ReportView: View {
    @State var lattitude = 0.0
    @State var longitude = 0.0
    @State var dateFound = Date()
    @State var timeFound = Date()
    @State var quantity = 1
    
    @State var reports: [Report] = []
    
    var body: some View {
        VStack {
            Text("Create Report")
                .font(.title)
            Form {
                Section {
                    HStack {
                        Text("Lattitude:")
                        Spacer()
                        TextField("", value: $lattitude, format: .number)
                    }
                    HStack {
                        Text("Longitude:")
                        TextField("", value: $longitude, format: .number)
                    }
                }
                
                Section {
                    DatePicker("Date Found", selection: $dateFound, displayedComponents: .date)
                    DatePicker("Time Found", selection: $timeFound, displayedComponents: .hourAndMinute)
                }
                
                Section {
                    Stepper(value: $quantity, in: 1...100, label: { Text("Quantity\t\t\t\t\(quantity)") })
                }
            }
            Button(action: {
                let tempReport = Report.init(lattitude: lattitude, longitude: longitude, dateFound: dateFound, timeFound: timeFound, quantity: quantity)
                reports.append(tempReport)
                
                let propertyListEncoder = PropertyListEncoder()
                
                let documentDirectory = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first!
                let archiveURL = documentDirectory.appendingPathComponent("reports_library")
                    .appendingPathExtension("plist")
                
                if let encodedReports = try? propertyListEncoder.encode(reports) {
                    print("The encoded book bytes:\n\(encodedReports)")
                    try? encodedReports.write(to: archiveURL, options: .noFileProtection)
                }
                
                
                
                
                
                
                
            }) {
                VStack {
                        Image(systemName: "paperplane.fill")
                        Text("Submit Report")
                    }
                }
            .frame(maxWidth: .infinity, alignment: .center)
            Spacer()
        }
    }
}

class Report: CustomStringConvertible, Equatable, Codable, Identifiable {
    var lattitude: Double
    var longitude: Double
    var dateFound: Date
    var timeFound: Date
    var quantity: Int
    
    init(lattitude: Double, longitude: Double, dateFound: Date, timeFound: Date, quantity: Int) {
        self.lattitude = lattitude
        self.longitude = longitude
        self.dateFound = dateFound
        self.timeFound = timeFound
        self.quantity = quantity
    }
    
    var description: String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateStyle = .medium
        dateFormatter.timeStyle = .none
        dateFormatter.locale = Locale(identifier: "en_GB")
        dateFormatter.setLocalizedDateFormatFromTemplate("MMMMd")
        let timeFormatter = DateFormatter()
        timeFormatter.dateStyle = .none
        timeFormatter.timeStyle = .short
        return "\(quantity) Cane Toad(s) spotted at lattitude: \(lattitude), longitude: \(longitude) on \(dateFormatter.string(from: dateFound)) at \(timeFormatter.string(from: timeFound))"
    }
    
    static func == (reportOne: Report, reportTwo: Report) -> Bool {
        let reportsAreTheSame = reportOne.lattitude == reportTwo.lattitude &&
                    reportOne.longitude == reportTwo.longitude &&
                    reportOne.dateFound == reportTwo.dateFound &&
                    reportOne.timeFound == reportTwo.timeFound &&
                    reportOne.quantity == reportTwo.quantity
        return reportsAreTheSame
    }
}

#Preview {
    ReportView()
}
