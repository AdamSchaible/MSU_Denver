-- IPPS database
-- This is the schemat that is used to
-- load ipps.csv dataset to the 'ipps' database.
-- Created at: 10/27/19
-- Author: Adam Schaible and Chris (Krzysztof) Rabka

CREATE database ipps;

USE ipps;

-- Table Providers
CREATE TABLE Providers (
    providerId INT   NOT NULL,
    name       VARCHAR(120),
    address    VARCHAR(120),
    city       VARCHAR(50),
    state      VARCHAR(3),
    zipCode    VARCHAR(5),
    PRIMARY KEY (providerId, name)
    );
-- Table DRG
CREATE TABLE DRG (	
	code        VARCHAR(3)   NOT NULL PRIMARY KEY,
	description VARCHAR(120)	
	);

-- Table HospitalReferralRegion
CREATE TABLE HospitalReferralRegions (
	referralId 	  INT         NOT NULL,
	referralState VARCHAR(3)  NOT NULL,
	referalCity  VARCHAR(30) NOT NULL,
	PRIMARY KEY (referralState, referalCity),	
	FOREIGN KEY (referralId)  REFERENCES Providers (providerId)
	);

-- Table Discharges
CREATE TABLE Discharges (
	dischargeId     INT    NOT NULL,
	totalDischarges INT(4) NOT NULL,
	FOREIGN KEY (dischargeId)  REFERENCES Providers (providerId)
	);

-- Table AveragePayments
CREATE TABLE AveragePayments (
	paymentId      INT            NOT NULL,
	coveredCharges DECIMAL(10, 2) NOT NULL,
	total          DECIMAL(10, 2) NOT NULL,
	medicare       DECIMAL(10, 2) NOT NULL,
	FOREIGN KEY (paymentId)  REFERENCES Providers (providerId)
	);

CREATE USER 'ipps' IDENTIFIED BY '135791';
GRANT ALL ON TABLE Providers               TO 'ipps';
GRANT ALL ON TABLE DRG                     TO 'ipps';
GRANT ALL ON TABLE HospitalReferralRegions TO 'ipps';
GRANT ALL ON TABLE Discharges              TO 'ipps';
GRANT ALL ON TABLE AveragePayments         TO 'ipps';

-- To count rows in each table:
-- SELECT COUNT(*) AS countDRG        FROM DRG;
-- SELECT COUNT(*) AS countReferral   FROM HospitalReferralRegions;
-- SELECT COUNT(*) AS countProviders  FROM Providers;
-- SELECT COUNT(*) AS countDischarges FROM Discharges;
-- SELECT COUNT(*) AS countPayments   FROM AveragePayments;

