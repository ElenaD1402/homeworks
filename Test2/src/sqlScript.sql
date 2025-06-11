/* Tables creation */

CREATE TABLE IF NOT EXISTS Users (
user_id INTEGER(10) PRIMARY KEY NOT NULL,
name TEXT(50) NOT NULL,
address TEXT(255) NULL
);

CREATE TABLE IF NOT EXISTS Accounts (
account_id INTEGER(10) NOT NULL,
user_id INTEGER(10) NOT NULL,
balance NUMERIC(15,3) NOT NULL DEFAULT 0.000,
currency TEXT(10) NOT NULL,
PRIMARY KEY (user_id, currency),
FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

CREATE TABLE IF NOT EXISTS Transactions (
transaction_id INTEGER(10) PRIMARY KEY NOT NULL,
account_id INTEGER(10) NOT NULL,
amount NUMERIC(15,3) NOT NULL,
timestamp TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (account_id) REFERENCES Accounts (account_id)
);

/* Test data for SqlAppCreateAccountTest */

INSERT INTO Users (user_id, name, address)
VALUES
   (1, "James Garcia", "755 Shawnee Ave, Kansas City, KS 66105, USA"),
   (2, "Rodney Scott", "837 S 8th St, Kansas City, KS 66105, USA"),
   (3, "William Jones", "715 S 8th St, Kansas City, KS 66105, USA"),
   (4, "Mark Rhodes", "2430 Rebecca Rd, Manhattan, KS 66502, USA"),
   (5, "David Smith", "2430 Buena Vista Dr, Manhattan, KS 66502, USA"),
   (6, "Kevin Brooks", "2730 Brittany Terrace, Manhattan, KS 66502, USA"),
   (7, "Luis Colon", "1502 Williamsburg Dr, Manhattan, KS 66502, USA"),
   (8, "Thomas Torres", "1503 Williamsburg Dr, Manhattan, KS 66502, USA"),
   (9, "Daniel Crawford", "508 N 7th St, Garden City, KS 67846, USA"),
   (10, "Robert Parker", "111 E Spruce St, Garden City, KS 67846, USA");

INSERT INTO Accounts (account_id, user_id, balance, currency)
VALUES
   (1, 10, 1000.000, "USD");

/* Test data for SqlAppDepositTest */

INSERT INTO Users (user_id, name, address)
VALUES
   (1, "Terry Baker", "755 Shawnee Ave, Kansas City, KS 66105, USA"),
   (2, "Francisco Miller", "715 S 8th St, Kansas City, KS 66105, USA"),
   (3, "Jonathan Harrison", "2430 Rebecca Rd, Manhattan, KS 66502, USA"),
   (4, "Angel Carr", "2430 Buena Vista Dr, Manhattan, KS 66502, USA");

INSERT INTO Accounts (account_id, user_id, balance, currency)
VALUES
   (1, 1, 1999900000.000, "EUR"),
   (2, 2, 1999984999.542, "USD"),
   (3, 3, 500000.258, "CNY"),
   (4, 4, 1900000000.000, "AUD");

/* Test data for SqlAppWithdrawTest */

INSERT INTO Users (user_id, name, address)
VALUES
   (1, "Richard Sullivan", "755 Shawnee Ave, Kansas City, KS 66105, USA"),
   (2, "Michael Campbell", "715 S 8th St, Kansas City, KS 66105, USA"),
   (3, "Albert Smith", "2430 Rebecca Rd, Manhattan, KS 66502, USA"),
   (4, "John Bradley", "2430 Buena Vista Dr, Manhattan, KS 66502, USA");

INSERT INTO Accounts (account_id, user_id, balance, currency)
VALUES
   (1, 1, 170000.000, "EUR"),
   (2, 2, 35000.250, "USD"),
   (3, 3, 200000000.250, "CNY"),
   (4, 4, 100000000.000, "AUD");