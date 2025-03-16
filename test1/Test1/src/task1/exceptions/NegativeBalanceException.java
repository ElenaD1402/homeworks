package task1.exceptions;

public class NegativeBalanceException extends Exception {
    public NegativeBalanceException() {
        super("Sorry! Insufficient Funds");
    }
}
