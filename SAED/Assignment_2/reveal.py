from edu.curtin.saed_assignment2.api.handlers import InventoryHandler
from edu.curtin.saed_assignment2.api.model import Goal

print("Starting reveal script")
class Reveal(InventoryHandler):
    def takeAction(item):
        name = item.getName()
        map_upper = "Map"
        map_lower = "map"
        if map_upper in name or map_lower in name:
            goalLocation = api.getGoalLocation()
            api.setCellVisible(goalLocation[0], goalLocation[1])
reveal = Reveal()
api.registerInventoryHandler(reveal)