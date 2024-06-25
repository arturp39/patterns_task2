package org.example.exception;

public class LeafOperationException extends RuntimeException {
    public LeafOperationException() {
    }

    public LeafOperationException(String message) {
        super("Leaf component can't have children: " + message);
    }

    public LeafOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeafOperationException(Throwable cause) {
        super(cause);
    }
}
