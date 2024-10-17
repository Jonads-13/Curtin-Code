package edu.curtin.saed_assignment2.game.model.exceptions;

// Custom exception for mopre useful error messages
public class FilledLocationException extends RuntimeException {
    
    public FilledLocationException(String msg) {
        super(msg);
    }
}
