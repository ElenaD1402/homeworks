package org.elena.hw15.exceptions;

public class NegativeBalanceException extends Exception {
    public NegativeBalanceException() {
        super("Sorry! Insufficient Funds");
    }
}
