# jerrys-qmart
Jerry's Q Mart excercise implementation

For my implementation, I used Java.

- Some compromises were made, given the time frame for the project
- I used JUnit to test the implementation
- I have provided a CSV file to pre-load the Inventory

## FILE STRUCTURE
###############################

The project directory contains:

- src/main/java - Source code for the app
- src/main/resources - Contains a CSV file example, used to populate the main Inventory
- src/test/java - Source code for the unit tests

- pom.xml - Maven POM descriptor used to build an executable jar, and execute unit tests


## PREREQUISITES
###############################

I used Java version 8 to build and compile. It should be downloaded from Oracle, and made accessible from the command line.

The following environment variables should be defined:

- JAVA_HOME whose value is Java installation directory
- PATH, adding the variable JAVA_HOME followed by subdirectory bin


## INSTRUCTIONS TO COMPILE
###############################

Apache Maven is used to automatize the procedure of building and compiling the source code, and to generate the executable jar.
It should be executed from a command line or an IDE.

Tested with Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)

To compile from command line:

Maven shall be available in the path environment variable, this is usually achieved defining the environment variables: 

- MAVEN_HOME whose value is Maven installation directory
- PATH, adding the variable MAVEN_HOME followed by subdirectory bin
- Please refer to Maven installation guide.

In command line:
1. Enter to project directory
2. Execute the command:
mvn clean install

this should create a subdirectory target in the current directory containing the file jqmart-mivs-0.0.1-SNAPSHOTSNAPSHOT.jar

This file is the executable compiled jar


## TO RUN UNIT TESTS
###############################

In command line:
1. Enter to project directory
2. Execute the command:
mvn test

This should create a subdirectory surefire-reports in the current directory containing the JUnit reports


## INSTRUCTIONS TO RUN THE APP
###############################

Place the executable jar file jqmart-mivs-0.0.1-SNAPSHOTSNAPSHOT.jar (found in directory target after compiling) and input text file containing the inventory, in the same directory

The required CSV file is provided in directory src/resources

In command line:
1. Enter to the directory containing the executable jar file
2. Execute the command:

java -jar jqmart-mivs-0.0.1-SNAPSHOTSNAPSHOT.jar .\Inventory.csv


If no Inventory file is found, the app will throw an Exception and will not work.

If the file is found in the directory, the program will execute.


###############################

Greetings

Marco Vaca
Quito, Ecuador

