//
//  ViewContact.swift
//  AppControls
//
//  Created by Jake Jonas on 16/4/2024.
//

import SwiftUI
import MapKit

struct ViewContact: View {
    struct PinItem :Identifiable {
        let id = UUID()
        let coordinate: CLLocationCoordinate2D
    }
    @State var isOn = true
    @State var region = MKCoordinateRegion(center: .init(latitude: -32.5, longitude: 115.75), latitudinalMeters: 10000, longitudinalMeters: 10000)
    
    var dateClosedRange: ClosedRange<Date> {
        let min = Calendar.current.date(byAdding: .day, value: -1, to: Date())!
        let max = Calendar.current.date(byAdding: .day, value: 1, to: Date())!
        return min...max
    }
    
    var body: some View {
        ScrollView {
            VStack {
                Image(systemName: "camera.circle")
                    .resizable()
                    .frame(width:200, height:200)
                HStack {
                    Text("Jake")
                        .frame(width: .infinity)
                        .padding([.top, .leading, .bottom], 10)
                    Text("Jonas")
                        .frame(width: .infinity)
                        .padding([.top, .trailing, .bottom], 10)
                }
                HStack {
                    Text("Date of Birth")
                        .padding(10)
                    Spacer()
                    Text("23 December 1996")
                        .padding(10)
                }
                HStack {
                    Text("Phone Number")
                        .padding(10)
                    Spacer()
                    Text("0893396113")
                        .padding(10)
                }
                
                HStack {
                    Text("Email")
                        .padding(10)
                    Spacer()
                    Text("jjonas@bigpond.com")
                        .padding(10)
                }
                
                Toggle("Work Contact", isOn: $isOn)
                    .frame(width: .infinity)
                    .padding(10)
                    .disabled(true)
                
                HStack {
                    Text("Address")
                        .padding(10)
                    Spacer()
                    Text("16 Moody Glen, Palmyra")
                        .padding(10)
                }
                Map(coordinateRegion: $region,
                    interactionModes: [],
                    showsUserLocation: true,
                    userTrackingMode: nil,
                    annotationItems: [PinItem(coordinate: .init(latitude: -32.5, longitude: 115.75))]) {
                    item in MapMarker(coordinate: item.coordinate)
                    
                }
                .frame(width: 300, height: 400)
                
                Text("Notes:")
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(10)
                Text("These are some notes about the contact")
                    .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
                    .padding(10)
                    .border(Color.black)
            }
        }
    }
}

#Preview {
    ViewContact()
}
