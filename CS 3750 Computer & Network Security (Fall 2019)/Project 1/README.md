**PROJECT TITLE:** Project 1

**PROJECT DESCRIPTION:**

Consisted of writing three independent programs in three separate directories: a key generation program, a sender’s program, and a receiver’s program, in order to implement a Public Key encrypted message and its digital digest.
...
All files related to this project are contained in this directory.

The full project description and requirements are in [Prj1_CS3750.pdf](https://github.com/AdamSchaible/MSU_Denver/blob/master/CS%203750%20Computer%20%26%20Network%20Security%20(Fall%202019)/Project%201/Prj1_CS3750.pdf).

For this project I choose Option #1 from the full project description to do, which is creating programs that demonstate the "Public Key encrypted message and its digital digest".

To get more of an overview about option #1 entails see [CS3750 Project 1 presentation.pptx](https://github.com/AdamSchaible/MSU_Denver/blob/master/CS%203750%20Computer%20%26%20Network%20Security%20(Fall%202019)/Project%201/CS3750%20Project%201%20presentation.pptx), which are slides from the presentation that I did for this project.

**VERSION or DATE:** 10/08/2019

**PROBLEMS THAT NEED ADDRESSING:** 
After decryption the text file is a few bytes smaller than the original, however line for line the decrypted file appears identical to the original when looking at both files side by side in a notepad. After some testing I believe the byte loss is occurring during decryption. 

**USER INSTRUCTIONS:** 

1) Install Java Development Kit (JDK) if you do not allready have Java Development Kit 8 or a latter version allready installed on your computer. For installation help see [Oracle's Java website](https://www.oracle.com/java/technologies/javase-downloads.html).

2) If you are unfamiliar with running Java programs first go to [How to Compile and Run your First Java Program](https://beginnersbook.com/2013/05/first-java-program/).

Go into the "Security Project 1 - KeyGen" subdirectory that is inside the same directory as this README file and run the following commands:

javac KeyGen.java
javac KeyGen

The KeyGen program will have the user enter a 16 character string to generate the encryption and decryption keys. The KeyGen program will exit after generating the keys into the same folder as the KeyGen program. Once the KeyGen program has exited copy the keys to both the "Security Project 1 - Sender" and the "Security Project 1 - receiver" subdirectories that are in the same directory as this README file.

Place the text file that you wish to encrypt into the "Security Project 1 - Sender" directory.

Go into the "Security Project 1 - Sender" directory and run the following commands in your terminal or command line:

javac Sender.java
java Sender

When the Sender program is done running copy your encrypted text file (message.rsacipher) from the "Security Project 1 - Sender" directory to the "Security Project 1 - receiver" directory.

Go into the "Security Project 1 - Receiver" directory and run the following commands in your terminal or command line:

javac Receiver.java
java Receiver

And after running the Receiver program the text file that the sender encrypted is now decrypted in the Receiver folder.

**AUTHOR:** Adam Schaible