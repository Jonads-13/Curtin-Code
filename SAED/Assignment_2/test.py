class Reveal(PlayerHandler):
    def handlePlayerPickedUpItem(self, item):
        name = unicodedata.normalize("NFC", item.getName())
        map_upper = unicodedata.normalize("NFC", "Map")
        map_lower = unicodedata.normalize("NFC", "map")
        if map_upper in name or map_lower in name:
            print("Map acquired, revealing hidden mysteries...")
            map = api.getMap()
            for row in map:
                for cell in row:
                    # Only show the goal and items, not obstacles
                    if isinstance(cell, Goal) or isinstance(cell, Item):
                        api.setCellVisibility(cell.getRow(), cell.getCol(), True)
    
    
    def handlePlayerMoved(self, prevLocation, newLocation):
        str = "script does nothing for this callback"
        
    
    def handlePlayerTraversedObstacle(self, obstacle):
        str = "script does nothing for this callback"

reveal = Reveal()
api.registerPlayerHandler(reveal)
print("Reveal script loaded")