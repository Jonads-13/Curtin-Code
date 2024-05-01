//
//  ContentView.swift
//  StructClassTest
//
//  Created by Jake Jonas on 19/3/2024.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            Button("Create appointment with end time"){
                let appointment = Appointment(
                    description: "Complain about David's teaching to therapist",
                    startHours: 9, startMinutes: 0,
                    endHours: 14, endMinutes: 30,
                    numStaffRequired: 14
                )
                appointment.printAppointment()
            }
            
            Button("Create appointment with elapsed time"){
                let appointment = Appointment(
                    description: "Blame Tristan for Prac sheet errors",
                    startHours: 13, startMinutes: 0,
                    elapsedTime: 235,
                    numStaffRequired: 4
                )
                appointment.printAppointment()
            }
            
            Button("Create Residence") {
                let residence = Residence(
                    postcode: 6157,
                    suburb: "Palymra",
                    streetName: "Moody Glen",
                    streetNumber: 16,
                    numBedrooms: 4,
                    numBathrooms: 2.5,
                    numCarBays: 2
                )
                print(residence.toString())
                
            }
            
            Button("Create default person") {
                var defaultPerson = Person()
                defaultPerson.printName()
            }
            
            Button("Create parameter person") {
                var parameterPerson = Person(
                    firstName: "Jake",
                    lastName: "Jonas",
                    dayOfBirth: 23,
                    monthOfBirth: 12,
                    yearOfBirth: 1996,
                    COMP2010Student: true
                )
                parameterPerson.printName()
            }
        }
        .padding()
    }
}

struct Appointment {
    var description: String
    var startTime: HoursMinsTime
    var endTime: HoursMinsTime
    var numStaffRequired: Int
    var elapsedTime: Int
    
    // Computed property
    var length: Int {
        var result = 0
        if elapsedTime > 0 {
            result = elapsedTime
        } else {
            result = (self.endTime.hours - self.startTime.hours) * 60
            
            if endTime.minutes > startTime.minutes {
                result += (endTime.minutes - startTime.minutes)
            } else {
                result -= (startTime.minutes - endTime.minutes)
            }
        }
        
        return result
    }
    
    init(description: String, startHours: Int, startMinutes: Int, endHours: Int, endMinutes: Int, numStaffRequired: Int) {
        self.description = description
        
        self.startTime = HoursMinsTime(
            hours: startHours,
            minutes: startMinutes
        )
        
        self.endTime = HoursMinsTime(
            hours: endHours,
            minutes: endMinutes
        )
        
        self.numStaffRequired = numStaffRequired
        self.elapsedTime = 0
    }
    
    init(description: String, startHours: Int, startMinutes: Int, elapsedTime: Int, numStaffRequired: Int) {
        self.description = description
        
        self.startTime = HoursMinsTime(
            hours: startHours,
            minutes: startMinutes
        )
        
        self.elapsedTime = elapsedTime
        
        var hours: Int = startTime.hours
        var minutes: Int = startTime.minutes + elapsedTime
        
        while minutes > 59 {
            if startTime.hours == 23 {
                hours = 0
            }
            else {
                hours += 1
            }
            
            minutes -= 60
        }
        
        self.endTime = HoursMinsTime(hours: hours, minutes: minutes)
        
        self.numStaffRequired = numStaffRequired
    }
    
    func calculateStaffMinutes() -> Int {
        return numStaffRequired * length
    }
    
    func printAppointment() {
        print("Appointment Information:")
        print("Description: \(self.description)")
        print("StartTime: \(String(format: "%02d", self.startTime.hours)):\(String(format: "%02d", self.startTime.minutes))")
        print("EndTime: \(String(format: "%02d", self.endTime.hours)):\(String(format: "%02d", self.endTime.minutes))")
        print("Staff Minutes: \(calculateStaffMinutes())")
    }
    
    mutating func changeStaffNumbers() {
        numStaffRequired = 1
    }
    
}

struct HoursMinsTime {
    var hours: Int
    var minutes: Int
    init(hours: Int, minutes: Int) {
        self.hours = hours
        self.minutes = minutes
    }
}

class Address {
    var postcode: Int
    var suburb: String
    var streetName: String
    var streetNumber: Int
    
    init(postcode: Int, suburb: String, streetName: String, streetNumber: Int) {
        self.postcode = postcode
        self.suburb = suburb
        self.streetName = streetName
        self.streetNumber = streetNumber
    }
    
    func toString() -> String {
        return "\(streetNumber) \(streetName), \(suburb), \(postcode)"
    }
}

class Residence : Address {
    var numBedrooms: Int
    var numBathrooms: Double
    var numCarBays: Int
    
    init(postcode: Int, suburb: String, streetName: String, streetNumber: Int, numBedrooms: Int, numBathrooms: Double, numCarBays: Int) {
        self.numBedrooms = numBedrooms
        self.numBathrooms = numBathrooms
        self.numCarBays = numCarBays
        super.init(postcode: postcode, suburb: suburb, streetName: streetName, streetNumber: streetNumber)
    }
    
    override func toString() -> String {
        return "\(super.toString()) \nhas: \n\(numBedrooms) Bedrooms \n\(numBathrooms) Bathrooms \n\(numCarBays) Car Bays"
    }
}

struct Person {
    var firstName: String
    var lastName: String
    var dayOfBirth: Int
    var monthOfBirth: Int
    var yearOfBirth: Int
    var COMP2010Student: Bool
    
    init(firstName: String = "John", lastName: String = "Smith", dayOfBirth: Int = 1, monthOfBirth: Int = 1, yearOfBirth: Int = 1970, COMP2010Student: Bool = false) {
        self.firstName = firstName
        self.lastName = lastName
        self.dayOfBirth = dayOfBirth
        self.monthOfBirth = monthOfBirth
        self.yearOfBirth = yearOfBirth
        self.COMP2010Student = COMP2010Student
    }
    
    func printName() {
        print(firstName + " " + lastName)
    }
}

#Preview {
    ContentView()
}
