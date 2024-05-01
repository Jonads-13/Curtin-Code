//
//  Report.swift
//  CaneToadApp
//
//  Created by Jake Jonas on 23/4/2024.
//

import SwiftUI

struct Report: View {
    @State var lattitude = 0.0
    @State var longitude = 0.0
    @State var dateFound = Date()
    @State var timeFound = Date()
    @State var quantity = 1
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
                let dateFormatter = DateFormatter()
                dateFormatter.dateStyle = .medium
                dateFormatter.timeStyle = .none
                dateFormatter.locale = Locale(identifier: "en_GB")
                dateFormatter.setLocalizedDateFormatFromTemplate("MMMMd")
                let timeFormatter = DateFormatter()
                timeFormatter.dateStyle = .none
                timeFormatter.timeStyle = .short
                print("\(quantity) Cane Toad(s) spotted at lattitude: \(lattitude), longitude: \(longitude) on \(dateFormatter.string(from: dateFound)) at \(timeFormatter.string(from: timeFound))")
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

#Preview {
    Report()
}
