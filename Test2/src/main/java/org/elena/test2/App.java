package org.elena.test2;

import org.elena.test2.dao.SqlApp;
import org.elena.test2.dto.AccountDto;
import org.elena.test2.dto.TransactionDto;
import org.elena.test2.dto.UserDto;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("org.sqlite.JDBC is found. You can proceed");
        } catch (ClassNotFoundException e) {
            System.out.println("org.sqlite.JDBC is not found");
            return;
        }

        SqlApp sqlApp = new SqlApp();
        Scanner scanner = new Scanner(System.in);
        boolean toContinue = true;
        while (toContinue) {
            System.out.println("Choose an option: 1 - Create User, 2 - Create Account, 3 - Withdraw, 4 - Deposit, 5 - Quit");
            final int option = scanner.nextInt();
            switch (option) {
                case 1:
                    try {
                        createUser(scanner, sqlApp);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        createAccount(scanner, sqlApp);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        withdraw(scanner, sqlApp);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        deposit(scanner, sqlApp);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("You have quit the application");
                    toContinue = false;
                    break;
                default:
                    System.out.println("Invalid choice. Do you wish to proceed? If \"No\" choose 5 to quit the application");
                    break;
            }
        }
        scanner.close();
    }

    private static void createUser(Scanner scanner, SqlApp sqlApp) {
        System.out.println("Enter the name:");
        String name;
        try {
            name = scanner.useDelimiter("\n").next("([A-Za-z]\\s?){5,50}");
        } catch (Exception e) {
            System.out.println("Entered name is invalid");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter the address or press \"Enter\" to skip:");
        String address;
        try {
            address = scanner.useDelimiter("\n").next(".{0,255}");
        } catch (Exception e) {
            System.out.println("Entered address is invalid");
            scanner.nextLine();
            return;
        }
        UserDto userDto = new UserDto(name, address);
        sqlApp.createUser(userDto);
        System.out.println("Success! User has been created");
    }

    private static void createAccount(Scanner scanner, SqlApp sqlApp) {
        System.out.println("Enter your name:");
        String name;
        try {
            name = scanner.useDelimiter("\n").next("([A-Za-z]\\s?){5,50}");
        } catch (Exception e) {
            System.out.println("Entered name is invalid");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter the balance:");
        double balance;
        try {
            balance = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Entered balance is invalid");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter the currency:");
        String currency;
        try {
            currency = scanner.useDelimiter("\n").next("[A-Z]{3}");
        } catch (Exception e) {
            System.out.println("Entered currency is invalid");
            scanner.nextLine();
            return;
        }
        AccountDto accountDto = new AccountDto(name, balance, currency);
        sqlApp.createAccount(accountDto);
        System.out.println("Success! Account has been created");
    }

    private static void withdraw(Scanner scanner, SqlApp sqlApp) {
        System.out.println("Enter your name:");
        String name;
        try {
            name = scanner.useDelimiter("\n").next("([A-Za-z]\\s?){5,50}");
        } catch (Exception e) {
            System.out.println("Entered name is invalid");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter the amount to be withdrawn:");
        double amount;
        try {
            amount = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Entered amount is invalid");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter the currency:");
        String currency;
        try {
            currency = scanner.useDelimiter("\n").next("[A-Z]{3}");
        } catch (Exception e) {
            System.out.println("Entered currency is invalid");
            scanner.nextLine();
            return;
        }
        TransactionDto transactionDto = new TransactionDto(name, amount, currency);
        sqlApp.withdraw(transactionDto);
        System.out.println("Success! Your money has been withdrawn");
    }

    private static void deposit(Scanner scanner, SqlApp sqlApp) {
        System.out.println("Enter your name:");
        String name;
        try {
            name = scanner.useDelimiter("\n").next("([A-Za-z]\\s?){5,50}");
        } catch (Exception e) {
            System.out.println("Entered name is invalid");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter the amount to be deposited:");
        double amount;
        try {
            amount = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Entered amount is invalid");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter the currency:");
        String currency;
        try {
            currency = scanner.useDelimiter("\n").next("[A-Z]{3}");
        } catch (Exception e) {
            System.out.println("Entered currency is invalid");
            scanner.nextLine();
            return;
        }
        TransactionDto transactionDto = new TransactionDto(name, amount, currency);
        sqlApp.deposit(transactionDto);
        System.out.println("Success! Your money has been deposited");
    }
}
