package org.elena.hw15;

import org.elena.hw15.exceptions.BlockedException;
import org.elena.hw15.exceptions.CardIsNotInsertedException;
import org.elena.hw15.exceptions.NegativeBalanceException;

public class Main {
    public static void main(String[] args) throws CardIsNotInsertedException, BlockedException, NegativeBalanceException {
        ATM atm = new ATM();
        Card card1 = new DebitCard("Alena Spiryna", "1236", 1500);
        Card card2 = new CreditCard("Alena Spiryna", "1236", 1500);
        atm.atmStart(card1);
    }
}