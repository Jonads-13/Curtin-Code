//
//  EmojiFlag.swift
//  EmojiFlags
//
//  Created by Jake Jonas on 30/4/2024.
//

import Foundation

struct EmojiFlag : Codable, Hashable {
    let name: String
    let code: String
    let emoji: String
    let unicode: String
    let image: URL
    
//    enum CodingKeys: String, CodingKey {
//        case name = "name"
//        case code = "code"
//        case emoji = "emoji"
//        case uniciode = "unicode"
//        case image = "image"
//    }
    
    init(name: String, code: String, emoji: String, unicode: String, image: URL) {
        self.name = name
        self.code = code
        self.emoji = emoji
        self.unicode = unicode
        self.image = image
    }
    
//    init(from decoder: Decoder) throws {
//        
//    }
}
