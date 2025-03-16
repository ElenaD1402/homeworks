package task1;

import task1.exceptions.BlockedException;
import task1.exceptions.CardIsNotInsertedException;
import task1.exceptions.NegativeBalanceException;

import java.util.Scanner;

public class ATM {
    private Card card;
    private boolean isCardInserted;

    public void insertCard(Card card) {
        this.isCardInserted = true;
        this.card = card;
    }

    public void returnCard() {
        this.isCardInserted = false;
        this.card = null;
    }

    public void enterPin() throws CardIsNotInsertedException, BlockedException {
        if (!isCardInserted) {
            throw new CardIsNotInsertedException();
        }
        if (card.isBlocked) {
            throw new BlockedException();
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your PIN code:");
        boolean isPinCorrect = false;
        int attempt = 3;
        do {
            String pin = input.nextLine();
            attempt--;
            if (pin.equals(card.getPin())) {
                isPinCorrect = true;
            } else {
                System.out.println("Entered PIN code is incorrect. You have " + attempt + " attempts left.");
            }
        } while (!isPinCorrect && attempt > 0);
        if (!isPinCorrect) {
            card.isBlocked = true;
            System.out.println("You have entered incorrect PIN code for 3 times.");
        }
    }

    public void depositAmount(double depositAmount) throws CardIsNotInsertedException, BlockedException {
        if (!isCardInserted) {
            throw new CardIsNotInsertedException();
        }
        if (card.isBlocked) {
            throw new BlockedException();
        }
        card.deposit(depositAmount);
    }

    public void withdrawAmount(double withdrawAmount) throws CardIsNotInsertedException, BlockedException, NegativeBalanceException {
        if (!isCardInserted) {
            throw new CardIsNotInsertedException();
        }
        if (card.isBlocked) {
            throw new BlockedException();
        }
        card.withdraw(withdrawAmount);
    }

    public void displayBalance() throws CardIsNotInsertedException, BlockedException {
        if (!isCardInserted) {
            throw new CardIsNotInsertedException();
        }
        if (card.isBlocked) {
            throw new BlockedException();
        }
        System.out.println("Your balance is " + card.balance + " BYN");
    }

    public void atmStart(Card card) {
        System.out.println("Please insert the card");
        insertCard(card);
        System.out.println("Choose an option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        Scanner scanner = new Scanner(System.in);
        boolean toContinue = true;
        while (toContinue) {
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter money to be withdrawn:");
                    double withdrawAmount = scanner.nextDouble();
                    try {
                        enterPin();
                        withdrawAmount(withdrawAmount);
                    } catch (CardIsNotInsertedException | BlockedException | NegativeBalanceException ex1) {
                        System.out.println(ex1.getMessage());
                    } finally {
                        System.out.println("Do you wish to proceed? If \"No\" choose 4 to exit");
                    }
                    break;
                case 2:
                    System.out.println("Enter money to be deposited:");
                    double depositAmount = scanner.nextDouble();
                    try {
                        enterPin();
                        depositAmount(depositAmount);
                    } catch (CardIsNotInsertedException | BlockedException ex1) {
                        System.out.println(ex1.getMessage());
                    } finally {
                        System.out.println("Do you wish to proceed? If \"No\" choose 4 to exit");
                    }
                    break;
                case 3:
                    try {
                        enterPin();
                        displayBalance();
                    } catch (CardIsNotInsertedException | BlockedException ex1) {
                        System.out.println(ex1.getMessage());
                    } finally {
                        System.out.println("Do you wish to proceed? If \"No\" choose 4 to exit");
                    }
                    break;
                case 4:
                    returnCard();
                    System.out.println("Please collect your card");
                    toContinue = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    System.out.println("Do you wish to proceed? If \"No\" choose 4 to exit");
                    break;
            }
        }
        scanner.close();
    }
}





