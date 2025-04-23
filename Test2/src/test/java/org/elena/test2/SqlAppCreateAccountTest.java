package org.elena.test2;

import org.elena.test2.dao.SqlApp;
import org.elena.test2.dto.AccountDto;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SqlAppCreateAccountTest {

    @DataProvider(name = "paramsCreateAccount")
    public static Object[] paramsCreateAccount() {
        return new Object[]{
                new AccountDto("James Garcia", 0.000d, "EUR"),
                new AccountDto("Rodney Scott", 15_000.458d, "USD"),
                new AccountDto("William Jones", 500_000d, "GBP"),
                new AccountDto("Mark Rhodes", 1_500_000.698d, "CNY"),
                new AccountDto("David Smith", 2_000_000_000d, "AUD")
        };
    }

    @Test(dataProvider = "paramsCreateAccount")
    public void testCreateAccount(AccountDto accountDto1) {
        SqlApp sqlApp = new SqlApp();
        sqlApp.createAccount(accountDto1);
        List<AccountDto> accountsDtoList = Reader.getAccounts();
        boolean isCreated = false;
        for (AccountDto accountDto : accountsDtoList) {
            if (accountDto.equals(accountDto1)) {
                isCreated = true;
                break;
            }
        }
        Assert.assertTrue(isCreated, "Account for user " + accountDto1.getName() + " with balance " +
                accountDto1.getBalance() + " " + accountDto1.getCurrency() + " is not created");
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Balance cannot be less than 0 and greater than 2’000’000’000")
    public void testCreateAccountNegativeBalance1() {
        SqlApp sqlApp = new SqlApp();
        AccountDto accountDto = new AccountDto("Kevin Brooks", -0.0001d, "BYN");
        sqlApp.createAccount(accountDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Balance cannot be less than 0 and greater than 2’000’000’000")
    public void testCreateAccountNegativeBalance2() {
        SqlApp sqlApp = new SqlApp();
        AccountDto accountDto = new AccountDto("Luis Colon", -100_000d, "BYN");
        sqlApp.createAccount(accountDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Balance cannot be less than 0 and greater than 2’000’000’000")
    public void testCreateAccountHugeBalance1() {
        SqlApp sqlApp = new SqlApp();
        AccountDto accountDto = new AccountDto("Thomas Torres", 2_000_000_000.0001d, "BYN");
        sqlApp.createAccount(accountDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Balance cannot be less than 0 and greater than 2’000’000’000")
    public void testCreateAccountHugeBalance2() {
        SqlApp sqlApp = new SqlApp();
        AccountDto accountDto = new AccountDto("Daniel Crawford", 2_000_000_000_000d, "BYN");
        sqlApp.createAccount(accountDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "Account for user ([A-Za-z]\\s?){5,50} with currency [A-Z]{3} already exists")
    public void testCreateAccountExistingCurrency() {
        SqlApp sqlApp = new SqlApp();
        AccountDto accountDto = new AccountDto("Robert Parker", 10_000d, "USD");
        sqlApp.createAccount(accountDto);
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "User ([A-Za-z]\\s?){5,50} does not exist")
    public void testCreateAccountNotExistingUser() {
        SqlApp sqlApp = new SqlApp();
        AccountDto accountDto = new AccountDto("Billy Gibson", 100_000d, "USD");
        sqlApp.createAccount(accountDto);
    }
}
