**HOMEWORK TITLE:** HW 8 BANK

**HOMEWORK DESCRIPTION:**
Write a java program named [AccountApplication.java](https://github.com/AdamSchaible/MSU_Denver/blob/master/CS%201050%20Computer%20Science%201%20(Spring%202017)/HW%208%20BANK/AccountApplication.java). Which allows for the user to create a checking, savings, money market or certificate of deposit account and perform transactions on that account as well as get information on both account value and transaction history. The [AccountApplication.java](https://github.com/AdamSchaible/MSU_Denver/blob/master/CS%201050%20Computer%20Science%201%20(Spring%202017)/HW%208%20BANK/AccountApplication.java) should also use helper programs named [Account.java](https://github.com/AdamSchaible/MSU_Denver/blob/master/CS%201050%20Computer%20Science%201%20(Spring%202017)/HW%208%20BANK/Account.java) and [Transaction.java](https://github.com/AdamSchaible/MSU_Denver/blob/master/CS%201050%20Computer%20Science%201%20(Spring%202017)/HW%208%20BANK/Transaction.java). Account values and transactions are done using longs and transactions and will not include cents, but just whole numbers.

All files related to this homework are contained in this directory.

**VERSION or DATE:** 04/29/17

**PROBLEMS THAT NEED ADDRESSING:** It is possible for the value of an account to exceed the maximum or minimum of a long in this program, which would result in the account value no longer being accurate. The maximum is 9,223,372,036,854,775,807 and the minimum is -9,223,372,036,854,775,808.

**NOTES:**

Upgraded from version 04/29/17. The newer version prevents the user from entering either a bank name or account as an empty string, makes sure that the initial balance is positive and no bigger than the maximum value of a long, added date format verifications to the transactions, added a check to make sure that an account number is made up of digits only and added checks to ensure that all transaction amounts were a positive number which cannot exceed the maximum value of a double. 

**USER INSTRUCTIONS:** 

1) Install Java Development Kit (JDK) if you do not already have Java Development Kit 8 or a latter version allready installed on your computer. For installation help see [Oracle's Java website](https://www.oracle.com/java/technologies/javase-downloads.html).

2) Run the following commands in your terminal or command line in the directory containing this README file to run the program and if you need additional help doing this see [How to Compile and Run your First Java Program](https://beginnersbook.com/2013/05/first-java-program/).

javac AccountApplication.java
java AccountApplication

**AUTHOR:** Adam Schaible