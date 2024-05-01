//
//  AddNewContact.swift
//  AppControls
//
//  Created by Jake Jonas on 10/4/2024.
//

import SwiftUI
import MapKit

struct AddNewContact: View {
    
    struct PinItem :Identifiable {
        let id = UUID()
        let coordinate: CLLocationCoordinate2D
    }
    
    @State var firstName = ""
    @State var lastName = ""
    @State var dateOfBirth = Date()
    @State var phoneNumber = ""
    @State var email = ""
    @State var work: Bool = false
    @State var address = ""
    @State var notes = ""
    
    var dateClosedRange: ClosedRange<Date> {
        let min = Calendar.current.date(byAdding: .day, value: -1, to: Date())!
        let max = Calendar.current.date(byAdding: .day, value: 1, to: Date())!
        return min...max
    }
    
    var body: some View {
        VStack {
            Image(systemName: "camera.circle")
                .resizable()
                .frame(width:200, height:200)
            HStack {
                TextField("First Name", text: $firstName)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .frame(width: .infinity)
                    .padding([.top, .leading, .bottom], 10)
                TextField("Last Name", text: $lastName)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .frame(width: .infinity)
                    .padding([.top, .trailing, .bottom], 10)
            }
            DatePicker("Date of Birth", selection: $dateOfBirth, in: dateClosedRange, displayedComponents: .date)
                .frame(width: .infinity)
                .padding(10)
            TextField("Phone Number", text: $phoneNumber)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .frame(width: .infinity)
                .padding(10)
            TextField("Email", text: $email)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .frame(width: .infinity)
                .padding(10)
            Toggle("Work Contact", isOn: $work)
                .frame(width: .infinity)
                .padding(10)
            TextField("Address", text: $address)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .frame(width: .infinity)
                .padding(10)
            Text("Notes:")
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(10)
            TextEditor(text: $notes)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .padding(10)
                .border(Color.black)
            Spacer()
            Button("Save") {
                print("Contact Saved")
            }
            .frame(maxWidth: .infinity, alignment: .bottom)
        }
    }
}


                    
                    
#Preview {
    AddNewContact()
}
