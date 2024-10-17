package edu.curtin.saed_assignment2.game.model.exceptions;

// Custom exception for mopre useful error messages
public class InvalidLocationException extends RuntimeException {
    
    public InvalidLocationException(String msg) {
        super(msg);
    }
}
