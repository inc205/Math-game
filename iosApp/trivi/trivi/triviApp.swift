//
//  triviApp.swift
//  trivi
//
//  Created by Diego Cando on 6/15/23.
//

import SwiftUI

@main
struct triviApp: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}
