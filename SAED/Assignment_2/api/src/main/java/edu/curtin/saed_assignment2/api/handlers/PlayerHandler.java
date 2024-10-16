package edu.curtin.saed_assignment2.api.handlers;

public interface PlayerHandler {
    void takeAction(boolean didAction, int[] prevLocation, int[] newLocation);
}
