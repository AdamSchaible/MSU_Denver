/**
 * CS3810 - Principles Database Systems - Fall 2019
 * IPPS database. Loads IPPS dataset to the 'ipps' database.
 * This program loads the dataset to the database in about 28 minutes.
 * @author Adam Schaible and Chris (Krzysztof) Rabka
 * @date   10-27-2019
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

public class ipps
{
    public static void main(String[] args) throws Exception {

        //server login will be updated with ipps credentials
        String server = "localhost";

        String database = "ipps";

        String user = "ipps";

        String password = "135791";

        String connectURL = "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&user=" + user + "&password=" + password;

        // connects to the database
        Connection conn = DriverManager.getConnection(connectURL);

        System.out.println("Connection to MySQL database " + database + " was successful!");

        //file reader derived from https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        //and combined with sample code for parsing from database assignment 2
        File csvFile = new File("ipps.csv");
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String token = "";

        String providerInsert = "INSERT INTO providers (providerId,name,address,city,state,zipCode) VALUES (?,?,?,?,?,?)"; //trying this one
        PreparedStatement prpStmt2 = conn.prepareStatement(providerInsert);

        String averagePaymentsInsert = "INSERT INTO AveragePayments (paymentID,coveredCharges,total,medicare) VALUES (?,?,?,?)"; //trying this one
        PreparedStatement prpStmt3 = conn.prepareStatement(averagePaymentsInsert);

        String hospitalReferralRegionInsert = "INSERT INTO HospitalReferralRegions (referralId,referralState,referalCity) VALUES (?,?,?)"; //trying this one
        PreparedStatement prpStmt4 = conn.prepareStatement(hospitalReferralRegionInsert);

        String DRGInsert = "INSERT INTO DRG (code,description) VALUES (?,?)"; //trying this one
        PreparedStatement prpStmt5 = conn.prepareStatement(DRGInsert);

        String DischargesInsert = "INSERT INTO Discharges (dischargeId,totalDischarges) VALUES (?,?)"; //trying this one
        PreparedStatement prpStmt6 = conn.prepareStatement(DischargesInsert);


        while ((token = br.readLine()) != null)
        {
            String[] tokenCollection = new String[12]; //assuming 12 columns in our database
            String temp = "";
            int tokenCollectionSlot = 0;
            boolean inString = false;
            for (int i = 0; i < token.length(); i++) //loop through each character in each line
            {
                if (token.charAt(i) == '"') //for detecting where " present
                {
                    inString = !inString;
                }

                if(!inString && token.charAt(i) == ',')
                {
                    tokenCollection[tokenCollectionSlot] = temp;
                    tokenCollectionSlot = tokenCollectionSlot + 1;
                    temp = "";
                }

                if(token.charAt(i) != '"' && inString) //rule for adding characters to temp while inside string
                {
                    temp = temp + token.charAt(i);
                }

                if(token.charAt(i) != ',' && !inString && token.charAt(i) != '"') //rule for adding characters to temp while outside of string
                {
                    temp = temp + token.charAt(i);
                }

                if(i == token.length() - 1) //at end of last token
                {
                    tokenCollection[tokenCollectionSlot] = temp;
                }

            }
            boolean inFirstLineOfCsv = (tokenCollection[0].substring(0, 3).equals("DRG"));

            if (!inFirstLineOfCsv)
            {
                //attempting to populate providers table

                int providerId = Integer.parseInt(tokenCollection[1]);

                //populating Providers
                try
                {
                    prpStmt2.setInt(1, providerId);
                    prpStmt2.setString(2, tokenCollection[2]);
                    prpStmt2.setString(3, tokenCollection[3]);
                    prpStmt2.setString(4, tokenCollection[4]);
                    prpStmt2.setString(5, tokenCollection[5]);
                    prpStmt2.setString(6, tokenCollection[6]);
                    prpStmt2.execute();
                }
                catch(Exception e) //catches duplicate primary key errors so that program does not crash
                {
                    System.out.println("Still running...");
                }

                //populating DRG table
                try{

                    prpStmt5.setString(1, tokenCollection[0].substring(0,3));
                    prpStmt5.setString(2, tokenCollection[0].substring(6));
                    prpStmt5.execute();
                }
                catch(Exception e) //catches duplicate primary key errors so that program does not crash
                {
                    System.out.println("Still running...");
                }

                //populating AveragePayments
                try
                {
                    prpStmt3.setInt(1, providerId);
                    prpStmt3.setString(2, tokenCollection[9]);
                    prpStmt3.setString(3, tokenCollection[10]);
                    prpStmt3.setString(4, tokenCollection[11]);
                    prpStmt3.execute();
                }
                catch (Exception e)
                {
                    System.out.println("Still running...");
                }

                //populating HospitalReferralRegion
                try
                {
                    prpStmt4.setInt(1, providerId);
                    prpStmt4.setString(2, tokenCollection[7].substring(0,2));
                    prpStmt4.setString(3, tokenCollection[7].substring(5));
                    prpStmt4.execute();
                }
                catch (Exception e)
                {
                    System.out.println("Still running...");
                }

                //populating Discharges table
                try
                {
                    prpStmt6.setInt(1, providerId);
                    prpStmt6.setString(2, tokenCollection[8]);
                    prpStmt6.execute();
                }
                catch (Exception e)
                {
                    System.out.println("Still running...");
                }
            }
        }

        // closes the connection
        System.out.println("\nALL DONE!");
        prpStmt2.close();
        prpStmt3.close();
        prpStmt4.close();
        prpStmt5.close();
        prpStmt6.close();
        conn.close();
    }
}
