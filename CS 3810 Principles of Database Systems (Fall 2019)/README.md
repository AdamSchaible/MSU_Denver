AUTHOR: ADAM SCHAIBLE

This repository contains my programming projects for the CS 3810 Principles of Database Systems that I took at MSU Denver in Fall of 2019. Below is a general description about what each programming assignment is about. 

[Database Assignment 1](https://github.com/AdamSchaible/MSU_Denver/tree/master/CS%203810%20Principles%20of%20Database%20Systems%20(Fall%202019)/Database%20Assignment%201) :

In this assignment a database of flowers was created and queried for various flower questions using SQL. The product of this assignment is a file named flowers.sql containing all the necessary SQL commands to create a database (named flowers), a few tables according to a predefined schema, populated those tables with some initial data, and queried the database.

[Database Assignment 2](https://github.com/AdamSchaible/MSU_Denver/tree/master/CS%203810%20Principles%20of%20Database%20Systems%20(Fall%202019)/Database%20Assignment%202) :

In this assignment the data from the [Inpatient Prospective Payment System(IPPS)](https://data.cms.gov/Medicare-Inpatient/Inpatient-Prospective-Payment-System-IPPS-Provider/97k6-zzx3) is loaded into a MySQL database named ipps.  The ipps database is designed so that all of its tables are normalized up to 3NF (third normal form).  All Data Definition Language (DDL) SQL statements (CREATE DATABASE and CREATE TABLE statements) and DCL (Data Control Language) statements (CREATE USER, GRANT statements) are present in a file named ipps.sql.  The ipps’s tables of the database are normalized up to 3NF, have primary keys, and appropriate foreign keys with referential integrity constraints in place. 

[Database Assignment 3:](https://github.com/AdamSchaible/MSU_Denver/tree/master/CS%203810%20Principles%20of%20Database%20Systems%20(Fall%202019)/Database%20Assignment%203) 

In this assignment several CSV files that contain healthcare data were provided. The SQL file ipps.sql that is provided for this assignment allready had the needed code to populate the CSV healthcare data into the appropriate tables and the assignment was essentially writing serveral queries for these populated tables within the ipps.sql file. 

Queries included what the total hospital discharges were, the provider's average charge for services covered by Medicare, the average charge for services by the provider and the average of Medicare payments to the provider.

[Database Assignment 4](https://github.com/AdamSchaible/MSU_Denver/tree/master/CS%203810%20Principles%20of%20Database%20Systems%20(Fall%202019)/Database%20Assignment%204) :

In this assignment a collection of diseases from the [diseases_network.json](diseases_network.json) data set is modeled on a Neo4J graph database and then a cluster analysis was performed to see how groups of diseases are related to each other. 