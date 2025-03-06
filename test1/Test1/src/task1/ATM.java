package task1;

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

    public void enterPin() {
        if (isCardInserted && !(card.isBlocked)) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your PIN code:");
            boolean isPinCorrect = false;
            int attempt = 3;
            do {
                String pin = input.next("[0-9]{4}");
                attempt--;
                if (pin.equals(card.getPin())) {
                    isPinCorrect = true;
                } else {
                    System.out.println("Entered PIN code is incorrect. You have " + attempt + " attempts left.");
                }
            } while (!isPinCorrect && attempt > 0);
            if (!isPinCorrect) {
                card.isBlocked = true;
                System.out.println("You have entered incorrect PIN code for 3 times. Your card is blocked");
            }
        } else if (!isCardInserted) {
            throw new NullPointerException("Please insert the card");
        } else {
            throw new RuntimeException("Your card is blocked");
        }
    }

    public void depositAmount(double depositAmount) {
        if (isCardInserted && !card.isBlocked) {
            card.balance += depositAmount;
            System.out.println("Your money has been successfully deposited");
        } else if (!isCardInserted) {
            System.out.println("Please insert the card");
        } else {
            System.out.println("Your card is blocked");
        }
    }

    public void withdrawAmount(double withdrawAmount) {
        if (isCardInserted && !card.isBlocked) {
            if (card.type.equals("debit")) {
                if (card.balance >= withdrawAmount) {
                    card.balance -= withdrawAmount;
                    System.out.println("Please collect your money");
                } else {
                    System.out.println("Sorry! Insufficient Funds");
                }
            } else if (card.type.equals("credit")) {
                card.balance -= withdrawAmount;
                System.out.println("Please collect your money");
            } else {
                System.out.println("Your card is blocked");
            }
        }
    }

    public void displayBalance() {
        if (isCardInserted && !card.isBlocked) {
            System.out.println("Your balance is " + card.balance + " BYN");
        } else if (!isCardInserted) {
            System.out.println("Please insert the card");
        } else {
            System.out.println("Your card is blocked");
        }
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
                    enterPin();
                    withdrawAmount(withdrawAmount);
                    System.out.println("Do you wish to proceed? If \"No\" choose 4 to exit");
                    break;
                case 2:
                    System.out.println("Enter money to be deposited:");
                    double depositAmount = scanner.nextDouble();
                    enterPin();
                    depositAmount(depositAmount);
                    System.out.println("Do you wish to proceed? If \"No\" choose 4 to exit");
                    break;
                case 3:
                    enterPin();
                    displayBalance();
                    System.out.println("Do you wish to proceed? If \"No\" choose 4 to exit");
                    break;
                case 4:
                    returnCard();
                    System.out.println("Please collect your card");
                    toContinue = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    System.out.println("Do you wish to proceed? If \"No\" choose 4 to exit");
            }
        }
        scanner.close();
    }
}





