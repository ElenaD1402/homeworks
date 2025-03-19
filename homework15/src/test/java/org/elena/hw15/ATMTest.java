package org.elena.hw15;

import org.elena.hw15.exceptions.BlockedException;
import org.elena.hw15.exceptions.CardIsNotInsertedException;
import org.elena.hw15.exceptions.NegativeBalanceException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(ListenerATM.class)
public class ATMTest{

    @BeforeAll
    public static void beforeTestsExecution() {
        System.out.println("Tests execution start...");
    }

    @AfterAll
    public static void afterTestsExecution() {
        System.out.println("Tests execution finish");
    }

    @Test
    @DisplayName("Default card creation test")
    @Tags({@Tag("card"), @Tag("regression")})
    public void cardCreationDefault() {
        Card card = new Card() {
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
        };
        Assertions.assertAll("Default card creation",
                () -> Assertions.assertEquals("debit", card.type, String.format("%s should be debit", card.type)),
                () -> Assertions.assertEquals("Card Holder", card.cardHolder, String.format("%s should be Card Holder", card.cardHolder)),
                () -> Assertions.assertEquals("1234", card.pin, String.format("%s should be 1234", card.pin)),
                () -> Assertions.assertEquals(0, card.balance, String.format("%s should 0", card.balance)),
                () -> Assertions.assertFalse(card.isBlocked, String.format("%s should be false", card.isBlocked))
        );
    }

    @Test
    @DisplayName("Credit card creation test")
    @Tags({@Tag("card"), @Tag("smoke")})
    public void creditCardCreation() {
        Card card = new CreditCard("Ivan Ivanov", "1234", 1000);
        Assertions.assertAll("Credit card creation",
                () -> Assertions.assertEquals("credit", card.type, String.format("%s should be credit", card.type)),
                () -> Assertions.assertEquals("Ivan Ivanov", card.cardHolder, String.format("%s should be Ivan Ivanov", card.cardHolder)),
                () -> Assertions.assertEquals("1234", card.pin, String.format("%s should be 1234", card.pin)),
                () -> Assertions.assertEquals(1000, card.balance, String.format("%s should be 1000", card.balance)),
                () -> Assertions.assertFalse(card.isBlocked, String.format("%s should be false", card.isBlocked))
        );
    }

    @Test
    @DisplayName("Debit card creation test")
    @Tags({@Tag("card"), @Tag("smoke")})
    public void debitCardCreation() {
        Card card = new DebitCard("Ivan Ivanov", "1234", 0);
        Assertions.assertAll("Debit card creation",
                () -> Assertions.assertEquals("debit", card.type, String.format("%s should be debit", card.type)),
                () -> Assertions.assertEquals("Ivan Ivanov", card.cardHolder, String.format("%s should be Ivan Ivanov", card.cardHolder)),
                () -> Assertions.assertEquals("1234", card.pin, String.format("%s should be 1234", card.pin)),
                () -> Assertions.assertEquals(0, card.balance, String.format("%s should be 0", card.balance)),
                () -> Assertions.assertFalse(card.isBlocked, String.format("%s should be false", card.isBlocked))
        );
    }

    public static List<Object[]> paramsDeposit() {
        List<Object[]> paramsDeposit = new ArrayList<>();
        paramsDeposit.add(new Object[]{new CreditCard("Ivan Ivanov", "1234", -1000), 2000});
        paramsDeposit.add(new Object[]{new CreditCard("Ivan Ivanov", "1234", 0), 3000});
        paramsDeposit.add(new Object[]{new CreditCard("Ivan Ivanov", "1234", 1500), 4500});
        paramsDeposit.add(new Object[]{new DebitCard("Ivan Ivanov", "1234", 0), 3000});
        paramsDeposit.add(new Object[]{new DebitCard("Ivan Ivanov", "1234", 2000), 5000});
        return paramsDeposit;
    }

    @ParameterizedTest
    @MethodSource("paramsDeposit")
    @DisplayName("Card method deposit test")
    @Tags({@Tag("card"), @Tag("smoke"), @Tag("methodDeposit")})
    public void testCardDeposit(Card card, double balance) throws BlockedException {
        Assertions.assertEquals(balance, card.deposit(3000), String.format("%s is not equal to %s", card.deposit(0), balance));
    }

    public static List<Object[]> paramsWithdraw() {
        List<Object[]> paramsWithdraw = new ArrayList<>();
        paramsWithdraw.add(new Object[]{new CreditCard("Ivan Ivanov", "1234", 500), -500});
        paramsWithdraw.add(new Object[]{new CreditCard("Ivan Ivanov", "1234", 1000), 0});
        paramsWithdraw.add(new Object[]{new CreditCard("Ivan Ivanov", "1234", 2000), 1000});
        paramsWithdraw.add(new Object[]{new DebitCard("Ivan Ivanov", "1234", 1000), 0});
        paramsWithdraw.add(new Object[]{new DebitCard("Ivan Ivanov", "1234", 1500), 500});
        return paramsWithdraw;
    }

    @ParameterizedTest
    @MethodSource("paramsWithdraw")
    @DisplayName("Card method withdraw test")
    @Tags({@Tag("card"), @Tag("smoke"), @Tag("methodWithdraw")})
    public void testCardWithdraw(Card card, double balance) throws NegativeBalanceException, BlockedException {
        Assertions.assertEquals(balance, card.withdraw(1000), String.format("%s is not equal to %s", card.withdraw(0), balance));
    }

    @Test
    @DisplayName("Card method exchange test")
    @Tags({@Tag("card"), @Tag("smoke"), @Tag("methodExchange")})
    public void testCardExchange() {
        Card card1 = new CreditCard("Ivan Ivanov", "1234", 1050);
        Card card2 = new DebitCard("Ivan Ivanov", "1234", 1600);
        Assertions.assertAll("Exchange",
                () -> Assertions.assertEquals(300, card1.exchange(3.5, "EUR"), String.format("%s is not equal to 300", card1.exchange(3.5, "EUR"))),
                () -> Assertions.assertEquals(500, card2.exchange(3.2, "USD"), String.format("%s is not equal to 500", card2.exchange(3.2, "USD")))
        );
    }

    @Test
    @DisplayName("Debit card method withdraw with negative balance test")
    @Tags({@Tag("card"), @Tag("smoke"), @Tag("methodWithdraw"), @Tag("exceptions")})
    public void testDebitCardWithdrawNegativeBalance() {
        Card card = new DebitCard("Ivan Ivanov", "1234", 1000);
        Assertions.assertThrows(NegativeBalanceException.class, () -> card.withdraw(1001), "NegativeBalanceException should be thrown");
    }

    @Test
    @DisplayName("Debit card method withdraw with positive balance test")
    @Tags({@Tag("card"), @Tag("regression"), @Tag("methodWithdraw"), @Tag("exceptions")})
    public void testDebitCardWithdrawPositiveBalance() {
        Card card1 = new DebitCard("Ivan Ivanov", "1234", 1000);
        Card card2 = new DebitCard("Ivan Ivanov", "1234", 1000);
        Assertions.assertAll("NegativeBalanceException",
                () -> Assertions.assertDoesNotThrow(() -> card1.withdraw(1000), "NegativeBalanceException shouldn't be thrown"),
                () -> Assertions.assertDoesNotThrow(() -> card2.withdraw(500), "NegativeBalanceException shouldn't be thrown")
        );
    }

    @Test
    @DisplayName("Card BlockedException is thrown test")
    @Tags({@Tag("card"), @Tag("regression"), @Tag("exceptions")})
    public void testCardThrowsBlockedException() {
        Card card1 = new CreditCard("Ivan Ivanov", "1234", 1000);
        Card card2 = new DebitCard("Ivan Ivanov", "1234", 1000);
        Card card3 = new CreditCard("Ivan Ivanov", "1334", 1500);
        card1.isBlocked = true;
        card2.isBlocked = true;
        card3.isBlocked = true;
        Assertions.assertAll("BlockedException",
                () -> Assertions.assertThrows(BlockedException.class, () -> card1.withdraw(300), "BlockedException should be thrown"),
                () -> Assertions.assertThrows(BlockedException.class, () -> card2.deposit(500), "BlockedException should be thrown"),
                () -> Assertions.assertThrows(BlockedException.class, () -> card3.exchange(3.2, "USD"), "BlockedException should be thrown")
        );
    }

    @Test
    @DisplayName("Card BlockedException is not thrown test")
    @Tags({@Tag("card"), @Tag("regression"), @Tag("exceptions")})
    public void testCardDoesNotThrowBlockedException() {
        Card card1 = new CreditCard("Ivan Ivanov", "1234", 1000);
        Card card2 = new DebitCard("Ivan Ivanov", "1234", 1000);
        Card card3 = new CreditCard("Ivan Ivanov", "1334", 1500);
        Assertions.assertAll("BlockedException",
                () -> Assertions.assertDoesNotThrow(() -> card1.deposit(300), "BlockedException shouldn't be thrown"),
                () -> Assertions.assertDoesNotThrow(() -> card2.withdraw(500), "BlockedException shouldn't be thrown"),
                () -> Assertions.assertDoesNotThrow(() -> card3.exchange(3.2, "USD"), "BlockedException shouldn't be thrown")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsDeposit")
    @DisplayName("ATM method depositAmount test")
    @Tags({@Tag("atm"), @Tag("smoke"), @Tag("methodDepositAmount")})
    public void testAtmDepositAmount(Card card, double balance) throws BlockedException, CardIsNotInsertedException {
        ATM atm = new ATM();
        atm.insertCard(card);
        Assertions.assertEquals(balance, atm.depositAmount(3000), String.format("%s is not equal to %s", atm.depositAmount(0), balance));
    }

    @ParameterizedTest
    @MethodSource("paramsWithdraw")
    @DisplayName("ATM method withdrawAmount test")
    @Tags({@Tag("atm"), @Tag("smoke"), @Tag("methodWithdrawAmount")})
    public void testAtmWithdrawAmount(Card card, double balance) throws NegativeBalanceException, BlockedException, CardIsNotInsertedException {
        ATM atm = new ATM();
        atm.insertCard(card);
        Assertions.assertEquals(balance, atm.withdrawAmount(1000), String.format("%s is not equal to %s", atm.withdrawAmount(0), balance));
    }

    @Test
    @DisplayName("ATM method displayBalance test")
    @Tags({@Tag("atm"), @Tag("smoke"), @Tag("methodDisplayBalance")})
    public void testAtmDisplayBalance() throws CardIsNotInsertedException, BlockedException {
        ATM atm1 = new ATM();
        Card card1 = new DebitCard("Anton Ivanov", "1458", 1000);
        atm1.insertCard(card1);
        ATM atm2 = new ATM();
        Card card2 = new CreditCard("Ivan Antonov", "1118", -500);
        atm2.insertCard(card2);
        Assertions.assertAll("DisplayBalance",
                () -> Assertions.assertEquals("Your balance is " + card1.getBalance() + " BYN", atm1.displayBalance()),
                () -> Assertions.assertEquals("Your balance is " + card2.getBalance() + " BYN", atm2.displayBalance())
        );
    }

    @Test
    @DisplayName("ATM method withdrawAmount with negative balance test")
    @Tags({@Tag("atm"), @Tag("smoke"), @Tag("methodWithdrawAmount"), @Tag("exceptions")})
    public void testAtmWithdrawNegativeBalance() {
        ATM atm = new ATM();
        Card card = new DebitCard("Ivan Ivanov", "1234", 1000);
        atm.insertCard(card);
        Assertions.assertThrows(NegativeBalanceException.class, () -> atm.withdrawAmount(1000.9), "NegativeBalanceException should be thrown");
    }

    @Test
    @DisplayName("ATM method withdrawAmount with positive balance test")
    @Tags({@Tag("atm"), @Tag("regression"), @Tag("methodWithdrawAmount"), @Tag("exceptions")})
    public void testAtmWithdrawPositiveBalance() {
        ATM atm = new ATM();
        Card card = new DebitCard("Ivan Ivanov", "1234", 1000);
        atm.insertCard(card);
        Assertions.assertAll("NegativeBalanceException",
                () -> Assertions.assertDoesNotThrow(() -> atm.withdrawAmount(500), "NegativeBalanceException shouldn't be thrown"),
                () -> Assertions.assertDoesNotThrow(() -> atm.withdrawAmount(500), "NegativeBalanceException shouldn't be thrown")
        );
    }

    @Test
    @DisplayName("ATM BlockedException is thrown test")
    @Tags({@Tag("atm"), @Tag("regression"), @Tag("exceptions")})
    public void testAtmThrowsBlockedException() {
        ATM atm = new ATM();
        Card card = new DebitCard("Ivan Ivanov", "1234", 1000);
        card.isBlocked = true;
        atm.insertCard(card);
        Assertions.assertAll("BlockedException",
                () -> Assertions.assertThrows(BlockedException.class, atm::enterPin, "BlockedException should be thrown"),
                () -> Assertions.assertThrows(BlockedException.class, () -> atm.depositAmount(500), "BlockedException should be thrown"),
                () -> Assertions.assertThrows(BlockedException.class, () -> atm.withdrawAmount(200), "BlockedException should be thrown"),
                () -> Assertions.assertThrows(BlockedException.class, atm::displayBalance, "BlockedException should be thrown")
        );
    }

    @Test
    @DisplayName("ATM BlockedException is not thrown test")
    @Tags({@Tag("atm"), @Tag("regression"), @Tag("exceptions")})
    public void testAtmDoesNotThrowBlockedException() {
        ATM atm = new ATM();
        Card card = new CreditCard("Ivan Ivanov", "1212", 500);
        atm.insertCard(card);
        Assertions.assertAll("BlockedException",
                () -> Assertions.assertDoesNotThrow(() -> atm.depositAmount(1000), "BlockedException shouldn't be thrown"),
                () -> Assertions.assertDoesNotThrow(() -> atm.withdrawAmount(2000), "BlockedException shouldn't be thrown"),
                () -> Assertions.assertDoesNotThrow(atm::displayBalance, "BlockedException shouldn't be thrown")
        );
    }

    @Test
    @DisplayName("ATM CardIsNotInsertedException is thrown test1")
    @Tags({@Tag("atm"), @Tag("regression"), @Tag("exceptions")})
    public void testAtmThrowsCardIsNotInsertedException1() {
        ATM atm = new ATM();
        Assertions.assertAll("CardIsNotInsertedException",
                () -> Assertions.assertThrows(CardIsNotInsertedException.class, atm::enterPin, "CardIsNotInsertedException should be thrown"),
                () -> Assertions.assertThrows(CardIsNotInsertedException.class, () -> atm.depositAmount(500), "CardIsNotInsertedException should be thrown"),
                () -> Assertions.assertThrows(CardIsNotInsertedException.class, () -> atm.withdrawAmount(200), "CardIsNotInsertedException should be thrown"),
                () -> Assertions.assertThrows(CardIsNotInsertedException.class, atm::displayBalance, "CardIsNotInsertedException should be thrown")
        );
    }

    @Test
    @DisplayName("ATM CardIsNotInsertedException is thrown test2")
    @Tags({@Tag("atm"), @Tag("regression"), @Tag("exceptions")})
    public void testAtmThrowsCardIsNotInsertedException2() {
        ATM atm = new ATM();
        Card card = new CreditCard("Ivan Ivanov", "1212", 500);
        atm.insertCard(card);
        atm.returnCard();
        Assertions.assertAll("CardIsNotInsertedException",
                () -> Assertions.assertThrows(CardIsNotInsertedException.class, atm::enterPin, "CardIsNotInsertedException should be thrown"),
                () -> Assertions.assertThrows(CardIsNotInsertedException.class, () -> atm.depositAmount(500), "CardIsNotInsertedException should be thrown"),
                () -> Assertions.assertThrows(CardIsNotInsertedException.class, () -> atm.withdrawAmount(200), "CardIsNotInsertedException should be thrown"),
                () -> Assertions.assertThrows(CardIsNotInsertedException.class, atm::displayBalance, "CardIsNotInsertedException should be thrown")
        );
    }

    @Test
    @DisplayName("ATM CardIsNotInsertedException is not thrown test")
    @Tags({@Tag("atm"), @Tag("regression"), @Tag("exceptions")})
    public void testAtmDoesNotThrowCardIsNotInsertedException() {
        ATM atm = new ATM();
        Card card = new DebitCard("Ivan Ivanov", "1234", 1000);
        atm.insertCard(card);
        Assertions.assertAll("CardIsNotInsertedException",
                () -> Assertions.assertDoesNotThrow(() -> atm.depositAmount(1000), "CardIsNotInsertedException shouldn't be thrown"),
                () -> Assertions.assertDoesNotThrow(() -> atm.withdrawAmount(2000), "CardIsNotInsertedException shouldn't be thrown"),
                () -> Assertions.assertDoesNotThrow(atm::displayBalance, "CardIsNotInsertedException shouldn't be thrown")
        );
    }
}
