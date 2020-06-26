**HOMEWORK TITLE:** Homework 4

**HOMEWORK DESCRIPTION:**

Consisted of writing a SMTP client program and a SMTP server program to implement the a simplified SMTP protocol based on TCP service. Server was written to support multiple clients. Both client and server were written in Java. 

All files related to this homework are contained in this directory.

The full homework description and requirements are in [HW4_CS3700.pdf](https://github.com/AdamSchaible/MSU_Denver/blob/master/CS%203700%20Computer%20Networks%20(Spring%202020)/HW4/HW4_CS3700.pdf).

**VERSION or DATE:** 02/25/2020

**PROBLEMS THAT NEED ADDRESSING:** ----

**USER INSTRUCTIONS:** 

1) Install Java Development Kit (JDK) if you do not allready have Java Development Kit 8 or a latter version allready installed on your computer. For installation help see [Oracle's Java website](https://www.oracle.com/java/technologies/javase-downloads.html).

2) If you are unfamiliar with running Java programs first go to [How to Compile and Run your First Java Program](https://beginnersbook.com/2013/05/first-java-program/).

Note: The server will need to be started before the client, otherwise the client will not be able to find the server.

On the server side run the following commands in your terminal or command line in the directory that contains TCPMultiServerThread.java and the TCPMultiServer.java:

javac TCPMultiServer.java
java TCPMultiServer

On the client side run the following commands in your terminal or command line in the directory containing the Java file TCPClient.java to run the program:

javac TCPClient.java
java TCPClient

**AUTHOR:** Adam Schaible