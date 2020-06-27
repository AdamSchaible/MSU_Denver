**ASSIGNMENT TITLE:** Database Assignment 2

**ASSIGNMENT DESCRIPTION:**

In this assignment the data from the [Inpatient Prospective Payment System(IPPS)](https://data.cms.gov/Medicare-Inpatient/Inpatient-Prospective-Payment-System-IPPS-Provider/97k6-zzx3) is loaded into a MySQL database named ipps.  The ipps database is designed so that all of its tables are normalized up to 3NF (third normal form).  All Data Definition Language (DDL) SQL statements (CREATE DATABASE and CREATE TABLE statements) and DCL (Data Control Language) statements (CREATE USER, GRANT statements) are present in a file named ipps.sql.  The ipps’s tables of the database are normalized up to 3NF, have primary keys, and appropriate foreign keys with referential integrity constraints in place. 

All files related to this assignment are contained in this directory.

The full assignment description and requirements are in [Dba02.docx](https://github.com/AdamSchaible/MSU_Denver/blob/master/CS%203810%20Principles%20of%20Database%20Systems%20(Fall%202019)/Database%20Assignment%202/Dba02.docx).

**VERSION or DATE:** 11/22/2019

**PROBLEMS THAT NEED ADDRESSING:** ---

**USER INSTRUCTIONS:**


If MySQL is not installed then install it at https://www.mysql.com/ and to run [ipps.sql](https://github.com/AdamSchaible/MSU_Denver/blob/master/CS%203810%20Principles%20of%20Database%20Systems%20(Fall%202019)/Database%20Assignment%202/ipps.sql) by [Running the SQL file from the terminal](https://www.tutorialspoint.com/run-sql-file-in-mysql-database-from-terminal).

Install Java Development Kit (JDK) for version 8. For installation help see [Oracle's Java website](https://www.oracle.com/java/technologies/javase-downloads.html).

Install IntelliJ IDEA though their [website](https://www.jetbrains.com/idea/).

Next, open up IntelliJ IDEA and create a Maven project that uses Java version 8 by using this [tutorial](https://www.jetbrains.com/help/idea/maven-support.html). After the project is created replace the pom.xml with the pom.xml in this directory and if you need help doing this see [Navigate to POM in Maven](https://www.jetbrains.com/help/idea/delegate-build-and-run-actions-to-maven.html). Copy the ipps.java and go to /src/main/java and right click on the java folder and click paste and say yes to refactoring, which this will add ipps.java to the Maven project. Then go to /src/main/java and right click on ipps.java and select run ipps.main() and then the ipps database will be populated.

**AUTHOR:** Adam Schaible
