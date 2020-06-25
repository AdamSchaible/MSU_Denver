**HOMEWORK TITLE:** HW 5 HEATER PROJECT

**HOMEWORK DESCRIPTION:**

Creates a graphical user interface that displays the temperatures of two thermostats after one is turned up and another is turned down using a heating and a cooling method.

==Requirements for the creation of Heater.java:==

1) Should contain private integers named temperature, min, max, increment.

2) Should have a constructor that takes in the minimum and maximum temperature. The constructor assigns the minimum temperature to the min variable. The constructor assigns the maximum temperature to the max variable. The constructor assigns temperature = 15. The constuctor assigns the increment =5. If the constructor discovers that the min >= max then it should throw an IllegalArgumentException with a meaningful message to the user.

3) Should contain methods for getting the current temperature, setting the increment, cooling the temperature by the increment, warming the temperature by the increment and a method that checks if the fields of two heater objects are identical and this method should be named equals.

==Requirements for the main method for the Heater Application when creating HeaterApplication.java==

1) Create a Scanner and use it to prompt the user for the minimum and maximum temperature for the first heater and use those values to create a new Heater object named heater1.

2) After creating heater1 use heater1's getTemperature method to display to the user what its current temperature is, then call heater1's cooler method followed by heater1's getTemperature method to tell the use what the new temperature is.

3) Use JOptionPane to prompt the user for the minimum and maximum temperature for the first heater and use those values to create a new Heater object named heater2 and display heater2's temperature using heater2's getTemperature method, which after this heater2's warmer method should be called and after that JOptionPane should be used to display heater2's new temperature. After that heater1 and heater2 should be compared to each other using the equals method contained in one the heaters and a message should be displayed to the user as to whether or not the two heaters have identical values stored in them.

All files related to this project are contained in this directory.

**VERSION or DATE:** 03/13/17

**USER INSTRUCTIONS:** 

1) Install Java Development Kit (JDK) if you do not allready have Java Development Kit 8 or a latter version allready installed on your computer. For installation help see [Oracle's Java website](https://www.oracle.com/java/technologies/javase-downloads.html).

2) Run the following commands in your terminal or command line in the directory containing this README file to run the program and if you need additional help doing this see [How to Compile and Run your First Java Program](https://beginnersbook.com/2013/05/first-java-program/).

javac HeaterApplication.java
java HeaterApplication

**PROBLEMS THAT NEED ADDRESSING:** This program allows for users to enter in incorrect inputs, which would result in an unhandled exception. Having the program be resistant to these types of errors was not part of the requirements for this project.

**AUTHOR:** Adam Schaible