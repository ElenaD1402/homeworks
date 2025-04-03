package task1;

import task1.exceptions.BlockedException;
import task1.exceptions.NegativeBalanceException;

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