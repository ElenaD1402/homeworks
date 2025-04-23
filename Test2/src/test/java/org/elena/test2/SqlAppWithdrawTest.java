package org.elena.test2;

import org.elena.test2.dao.SqlApp;
import org.elena.test2.dto.AccountDto;
import org.elena.test2.dto.TransactionDto;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SqlAppWithdrawTest {

    @DataProvider(name = "paramsWithdraw")
    public static Object[] paramsWithdraw() {
        return new Object[][]{
                new Object[]{new TransactionDto("Richard Sullivan", 50_000.000d, "EUR"), new AccountDto("Richard Sullivan", 120_000.000d, "EUR")},
                new Object[]{new TransactionDto("Michael Campbell", 35_000.250d, "USD"), new AccountDto("Michael Campbell", 0.000d, "USD")},
                new Object[]{new TransactionDto("Albert Smith", 100_000_000d, "CNY"), new AccountDto("Albert Smith", 100_000_000.250d, "CNY")},
                new Object[]{new TransactionDto("John Bradley", 100_000_000d, "AUD"), new AccountDto("John Bradley", 0.000d, "AUD")}
        };
    }

    @Test(dataProvider = "paramsWithdraw")
    public void testWithdraw(TransactionDto transactionDto1, AccountDto accountDto1) {
        List<AccountDto> accountsDtoListAfterWithdrawExpected = new ArrayList<>();
        accountsDtoListAfterWithdrawExpected.add(accountDto1);
        SqlApp sqlApp = new SqlApp();
        sqlApp.withdraw(transactionDto1);
        List<TransactionDto> transactionsDtoList = Reader.getTransactions();
        List<AccountDto> accountsDtoListAfterWithdrawActual = Reader.getAccounts();
        boolean isNegative = false;
        for (TransactionDto transactionDto : transactionsDtoList) {
            if (transactionDto.getAmount() < 0) {
                isNegative = true;
            } else {
                isNegative = false;
                break;
            }
        }
        boolean isEqual = false;
        for (int i = 0; i < accountsDtoListAfterWithdrawActual.size(); i++) {
            if (accountsDtoListAfterWithdrawActual.get(i).getName().equals(accountsDtoListAfterWithdrawExpected.get(0).getName())) {
                if (accountsDtoListAfterWithdrawActual.get(i).equals(accountsDtoListAfterWithdrawExpected.get(0))) {
                    isEqual = true;
                    break;
                }
            }
        }
        Assert.assertTrue(isNegative, "Transaction amount is not negative");
        Assert.assertTrue(isEqual, "Accounts list is not equal to expected");
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Insufficient funds! Your current balance is [0-9]{1,10}(.[0-9]{0,3})?")
    public void testWithdrawNegativeBalance() {
        SqlApp sqlApp = new SqlApp();
        TransactionDto transactionDto = new TransactionDto("Richard Sullivan", 50_000_000.100d, "EUR");
        sqlApp.withdraw(transactionDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Transaction cannot be greater than 100’000’000")
    public void testWithdrawHugeAmount() {
        SqlApp sqlApp = new SqlApp();
        TransactionDto transactionDto = new TransactionDto("Michael Campbell", 300_000_000.000d, "USD");
        sqlApp.withdraw(transactionDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Transaction amount should be positive number")
    public void testWithdrawNegativeAmount() {
        SqlApp sqlApp = new SqlApp();
        TransactionDto transactionDto = new TransactionDto("Albert Smith", -20_000.000d, "CNY");
        sqlApp.withdraw(transactionDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Account for ([A-Za-z]\\s?){5,50} with currency [A-Z]{3} does not exist")
    public void testWithdrawNotExistingAccount() {
        SqlApp sqlApp = new SqlApp();
        TransactionDto transactionDto = new TransactionDto("John Bradley", 3_000.000d, "USD");
        sqlApp.withdraw(transactionDto);
    }
}
