//
//  MapLocations.swift
//  CaneToadApp
//
//  Created by Jake Jonas on 23/4/2024.
//

import SwiftUI
import MapKit

struct MapLocations: View {
    @State var region = MKCoordinateRegion(center: .init(latitude: -32.33, longitude: 115.68), latitudinalMeters: 100000, longitudinalMeters: 100000)
    var body: some View {
        let bounds = MapCameraBounds(centerCoordinateBounds: region, minimumDistance: 25000, maximumDistance: 250000)
        VStack {
            Text("Cane Toad Locations")
                .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
            
            Map(bounds: bounds, interactionModes: .all)
            {
                Marker("", coordinate: .init(latitude: -31.96, longitude: 115.84))
                Marker("", coordinate: .init(latitude: -31.95, longitude: 115.82))
                Marker("", coordinate: .init(latitude: -31.9, longitude: 115.87))
                Marker("", coordinate: .init(latitude: -31.94, longitude: 115.86))
            }
        }
    }
}

#Preview {
    MapLocations()
}
