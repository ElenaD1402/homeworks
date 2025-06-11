package org.elena.test2;

import org.elena.test2.dao.SqlApp;
import org.elena.test2.dto.AccountDto;
import org.elena.test2.dto.TransactionDto;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SqlAppDepositTest {

    @DataProvider(name = "paramsDeposit")
    public static Object[] paramsDeposit() {
        return new Object[][]{
                new Object[]{new TransactionDto("Terry Baker", 50_000.000d, "EUR"), new AccountDto("Terry Baker", 1_999_950_000.000d, "EUR")},
                new Object[]{new TransactionDto("Francisco Miller", 15_000.458d, "USD"), new AccountDto("Francisco Miller", 2_000_000_000.000d, "USD")},
                new Object[]{new TransactionDto("Jonathan Harrison", 100_000_000d, "CNY"), new AccountDto("Jonathan Harrison", 100_500_000.258d, "CNY")},
                new Object[]{new TransactionDto("Angel Carr", 100_000_000d, "AUD"), new AccountDto("Angel Carr", 2_000_000_000.000d, "AUD")}
        };
    }

    @Test(dataProvider = "paramsDeposit")
    public void testDeposit(TransactionDto transactionDto1, AccountDto accountDto1) {
        SqlApp sqlApp = new SqlApp();
        sqlApp.deposit(transactionDto1);
        List<TransactionDto> transactionsDtoList = Reader.getTransactions();
        List<AccountDto> accountsDtoListAfterDeposit = Reader.getAccounts();
        boolean isPositive = false;
        for (TransactionDto transactionDto : transactionsDtoList) {
            if (transactionDto.getAmount() > 0) {
                isPositive = true;
            } else {
                isPositive = false;
                break;
            }
        }
        boolean isEqual = false;
        for (AccountDto accountDto : accountsDtoListAfterDeposit) {
            if (accountDto.equals(accountDto1)) {
                isEqual = true;
                break;
            }
        }
        Assert.assertTrue(isPositive, "Transaction amount is not positive");
        Assert.assertTrue(isEqual, "Accounts list is not equal to expected");
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Balance cannot be greater than 2’000’000’000. Your current balance is [0-9]{1,10}(.[0-9]{0,3})?")
    public void testDepositHugeBalance() {
        SqlApp sqlApp = new SqlApp();
        TransactionDto transactionDto = new TransactionDto("Terry Baker", 100_000.230d, "EUR");
        sqlApp.deposit(transactionDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Transaction cannot be greater than 100’000’000")
    public void testDepositHugeAmount() {
        SqlApp sqlApp = new SqlApp();
        TransactionDto transactionDto = new TransactionDto("Jonathan Harrison", 100_000_001.000d, "CNY");
        sqlApp.deposit(transactionDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Transaction amount should be positive number")
    public void testDepositNegativeAmount() {
        SqlApp sqlApp = new SqlApp();
        TransactionDto transactionDto = new TransactionDto("Jonathan Harrison", -10_000.000d, "CNY");
        sqlApp.deposit(transactionDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Account for ([A-Za-z]\\s?){5,50} with currency [A-Z]{3} does not exist")
    public void testDepositNotExistingAccount() {
        SqlApp sqlApp = new SqlApp();
        TransactionDto transactionDto = new TransactionDto("Angel Carr", 50_000.000d, "EUR");
        sqlApp.deposit(transactionDto);
    }
}
