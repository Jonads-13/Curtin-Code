package edu.curtin.saed_assignment2.api.plugins;

import edu.curtin.saed_assignment2.api.API;

public interface PlayerPlugin {
    void start(API api);
    void itemAcquired();
    void obstacleTraversed();
}
