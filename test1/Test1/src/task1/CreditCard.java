package task1;

import task1.exceptions.BlockedException;

public class CreditCard extends Card {
    public CreditCard(String cardHolder, String pin, int balance) {
        super("credit", cardHolder, pin, balance, false);
    }

    @Override
    public double withdraw(double withdrawAmount) throws BlockedException {
        if (isBlocked) {
            throw new BlockedException();
        }
        return this.balance -= withdrawAmount;
    }
}