package org.splitwise.exception;

public class ExactSplitException extends Exception {
    public ExactSplitException() {
        super("The amount is not correct");
    }
}
