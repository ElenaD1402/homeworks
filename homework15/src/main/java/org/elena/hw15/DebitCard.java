package org.elena.hw15;

import org.elena.hw15.exceptions.BlockedException;
import org.elena.hw15.exceptions.NegativeBalanceException;

public class DebitCard extends Card {
    public DebitCard(String cardHolder, String pin, int balance) {
        super("debit", cardHolder, pin, balance, false);
    }

    @Override
    public double withdraw(double withdrawAmount) throws BlockedException, NegativeBalanceException {
        if (isBlocked) {
            throw new BlockedException();
        }
        if (this.balance < withdrawAmount) {
            throw new NegativeBalanceException();
        }
        return this.balance -= withdrawAmount;
    }
}