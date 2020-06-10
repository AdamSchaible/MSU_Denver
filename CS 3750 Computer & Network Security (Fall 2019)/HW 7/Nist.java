//Name Adam Schaible
//CS3750
//Homework 7

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Nist implements Cloneable
{
    public static void main(String[] args) throws Exception
    {
        //2.1 Role Hierarchy
        String lineRead;
        ArrayList<Node> listOfAllNodes = new ArrayList<Node>();
        File roleFile = new File("roleHierarchy.txt");
        BufferedReader br = new BufferedReader(new FileReader(roleFile));
        Scanner input = new Scanner(System.in);
        int lineCount = 1;

        restartRoleHierarchy: //branching label
        while ((lineRead = br.readLine()) != null)
        {

            Node currentDescendant = null;
            Node currentAscendant = null;
            String[] edges = lineRead.split("\t");

            for (Node temp : listOfAllNodes)
            {
                if (temp.theRoles.get(0).equals(edges[0]))
                {
                    currentAscendant = temp;
                }

                if (temp.theRoles.get(0).equals(edges[1]))
                {
                    currentDescendant = temp;
                }
            }
            boolean ascendantCreated = false;
            boolean descendantCreated = false;
            if (currentAscendant == null)
            {
                currentAscendant = new Node(edges[0]);
                ascendantCreated = true;
                listOfAllNodes.add(currentAscendant);
            }

            if (currentDescendant == null)
            {
                currentDescendant = new Node(edges[1]);
                descendantCreated = true;
                listOfAllNodes.add(currentDescendant);
            }

            if (currentAscendant.descendant == null)
            {
                currentAscendant.descendant = currentDescendant;
            }
            else //2.2 The contents of this text file must be validated to enforce the "limited role hierarchies" - no more than 1 descendant
            {
                if (ascendantCreated)
                {
                    listOfAllNodes.remove(currentAscendant);
                }

                if (descendantCreated)
                {
                    listOfAllNodes.remove(currentDescendant);
                }



                System.out.println("Error, node is given more than 1 descendant at line " + lineCount + ":  " + lineRead);

                br.close();
                System.out.println("Remove 2nd descendant from line, and enter some character followed by enter to resume after fixing roleHierarchy.txt ");
                String temp = input.nextLine();
                lineCount = 1;
                roleFile = new File("roleHierarchy.txt");

                br = new BufferedReader(new FileReader(roleFile));
                break restartRoleHierarchy;
            }


            currentDescendant.ascendants.add(currentAscendant);

            lineCount++;
        }

        Node root = listOfAllNodes.get(0); //default to prevent null pointer exception
        for (Node listOfAllNode : listOfAllNodes) {
            if (listOfAllNode.descendant == null) {
                root = listOfAllNode;
            }
        }
        System.out.println("The Role Hierarchy top down : "); //problem 2.3
        root.printTree();

        //give each node their inheritance
        for (Node node: listOfAllNodes)
        {
            node.inherit();
        }

        ArrayList<String> resourceObjects = new ArrayList<String>();

        File roleFile2 = new File("resourceObjects.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(roleFile2));

        String[] tempResourceHolder = br2.readLine().split("\t");

        boolean gettingResources = true;
        //restartGettingResources: //branching label
        while (gettingResources)
        {
            boolean duplicate = false; //problem 3.1
            ArrayList<String> forDuplicateCheck = new ArrayList<String>();

            String duplicateResource = "";
            for (String resource : tempResourceHolder)
            {
                if (!forDuplicateCheck.contains(resource))
                {
                    forDuplicateCheck.add(resource);
                }
                else
                {
                    duplicate = true;
                    duplicateResource = resource;
                }
            }

            if (duplicate)
            {
                System.out.println("Duplicate object is found : " + duplicateResource + " in resourceObjects.txt,");
                System.out.println("correct the file and type in any character followed by enter to resume");
                br2.close();
                String temp = input.nextLine();
                roleFile2 = new File("resourceObjects.txt");
                br2 = new BufferedReader(new FileReader(roleFile2));

                tempResourceHolder = br2.readLine().split("\t");
                //break restartGettingResources;
            }
            if (!duplicate)
            {

                Collections.addAll(resourceObjects, tempResourceHolder);
                gettingResources = false;
            }


        }

        String[][] roleObjectMatrix= new String[root.theRoles.size()+1][resourceObjects.size() + root.theRoles.size()+ 1];
        int counter1 = 1;
        //add resource names to matrix
        for (String role : root.theRoles)
        {
            roleObjectMatrix[0][counter1] = role;
            counter1 = counter1 + 1;
        }

        for (String resource : tempResourceHolder)
        {
            roleObjectMatrix[0][counter1] = resource;
            counter1 = counter1 + 1;
        }
        //add role names to matrix

        int counter2 = 1;
        for (String role : root.theRoles)
        {
            roleObjectMatrix[counter2][0] = role;
            counter2 = counter2 + 1;
        }
        roleObjectMatrix[0][0] = "--";

        //Printing of the empty role-object matrix - problem 3.2
        System.out.println("\n\nThe Empty Role Object Matrix ");
        System.out.println("        leftmost column = roles, top row = objects");
        for(int i = 0; i < roleObjectMatrix.length; i++) //dealing with rows
        {
            String line = "";
            for (int j = 0; j < roleObjectMatrix[0].length; j++) //dealing with columns
            {
                String temp = "" + roleObjectMatrix[i][j];
                line = line + "\t" + String.format("%10s", temp);
            }
            System.out.println(line);
        }
        //adding control to the accessMatrix
        for (Node myNode : listOfAllNodes)
        {
            myNode.InheritAccessRights(myNode.theRoles.get(0),"control", myNode.theRoles.get(0),roleObjectMatrix,root);
        }

        //adding own to the accessMatrix
        for (Node myNode : listOfAllNodes)
        {
            myNode.InheritAccessRights(myNode.theRoles.get(0),"own", myNode.theRoles.get(0),roleObjectMatrix,root);
        }

        File permissionFile = new File("permissionsToRoles.txt");
        BufferedReader br3 = new BufferedReader(new FileReader(permissionFile));
        System.out.println("\n\n");
        while ((lineRead = br3.readLine()) != null) //question 4
        {
            String[] part = lineRead.split("\t");
            Node currentlyOn = null;
            for (Node myNode : listOfAllNodes)
            {
                if (myNode.theRoles.get(0).equals(part[0]))
                {
                    currentlyOn = myNode;
                }
            }
            currentlyOn.InheritAccessRights(part[0],part[1],part[2],roleObjectMatrix,root);
        }
        //access matrix after update
        System.out.println("Role matrix after adding control, own and other access features");
        System.out.println("Role Object Matrix         leftmost column = roles, top row = objects");
        for(int i = 0; i < roleObjectMatrix.length; i++) //dealing with rows
        {
            String line = "";
            for (int j = 0; j < roleObjectMatrix[0].length; j++) //dealing with columns
            {
                String temp = "" + roleObjectMatrix[i][j];
                line = line + "\t" + String.format("%16s", temp);
            }
            System.out.println(line);
        }
        //question 5
        File SSDFile = new File("roleSetsSSD.txt");
        BufferedReader br4 = new BufferedReader(new FileReader(SSDFile));

        int lineNumber = 1;
        String ssdDisplayString = "";
        ArrayList<String> roleSetSSDRules = new ArrayList<String>();
        while ((lineRead = br4.readLine()) != null)
        {
            roleSetSSDRules.add(lineRead);
            String[] temp = lineRead.split("\t");
            String roles = "";
            for(int i=1;i < temp.length;i++)
            {
                roles = roles + temp[i];
                if (i != temp.length -1)
                {
                    roles = roles + ", ";
                }
            }

            int constraint = Integer.parseInt(temp[0]);
            if (!(constraint >= 2))
            {
                System.out.println("Error on line # " + lineNumber + ", constraint is not >=2 for : " + lineRead);
                br4.close();
                System.out.println("Please fix the line in roleSetsSSD.txt and type anything followed by enter to resume reading the file\n\n");
                String temp2 = input.nextLine();
                SSDFile = new File("roleSetsSSD.txt");
                br4 = new BufferedReader(new FileReader(SSDFile));
                ssdDisplayString = "";
                lineNumber = 0;
                roleSetSSDRules = new ArrayList<String>();
            }
            else
            {
                if (lineNumber == 1)
                {
                    ssdDisplayString = ssdDisplayString + "\nConstraints present in roleSetsSSD.txt\n";
                }
                ssdDisplayString = ssdDisplayString + "Constraint " + lineNumber + ", " + "n = " + temp[0] + ", set of roles = {" + roles + "}\n";
            }
            lineNumber = lineNumber + 1;
        }
        System.out.println(ssdDisplayString); //problem 5.2
        //question 6
        //testing for valid file
        File userFile = new File("userRoles.txt");
        BufferedReader br5 = new BufferedReader(new FileReader(userFile));

        ArrayList<String> individualUsers = new ArrayList<String>();
        lineNumber = 1;

        while ((lineRead = br5.readLine()) != null)
        {
            String[] userRoleFragments = lineRead.split("\t");
            boolean validLine = true;

            if(!individualUsers.contains(userRoleFragments[0]))
            {
                individualUsers.add(userRoleFragments[0]);
            }
            else
            {
                validLine = false;
                System.out.println("invalid line is found in userRoles.txt: line # " + lineNumber + " due to duplicated user# " + userRoleFragments[0]);
            }

            for (String roleSet: roleSetSSDRules)
            {
                int rolesUsed = 0;
                for (String userFragment: userRoleFragments)
                {
                    if(roleSet.contains(userFragment))
                    {
                        rolesUsed = rolesUsed + 1;
                    }
                }
                String[] roleSetFragments = roleSet.split("\t");
                int constraint = Integer.parseInt(roleSetFragments[0]);

                if (constraint >= 3 && constraint < rolesUsed) //cardinal
                {
                    validLine = false;
                    System.out.println("invalid line is found in userRoles.txt: line # " + lineNumber + " due to constraint# " + lineNumber);
                }
                if (constraint == 2 && rolesUsed > 1) //mutually exclusive
                {
                    validLine = false;
                    System.out.println("invalid line is found in userRoles.txt: line # " + lineNumber + " due to constraint# " + lineNumber);
                }
            }

            if (!validLine) //reset
            {
                System.out.println("correct the file and type in any character followed by enter to resume");
                br5.close();
                String temp = input.nextLine();
                userFile = new File("userRoles.txt");
                br5 = new BufferedReader(new FileReader(userFile));
                individualUsers = new ArrayList<String>();
            }
            lineNumber = lineNumber + 1;
        }
        //now I know that userRoles.txt is okay and its time to fill in the table




        String[][] userRoleMatrix= new String[individualUsers.size()+1][root.theRoles.size()+1];

        counter1 = 1;
        //add roles names to matrix
        for (String role : root.theRoles)
        {
            userRoleMatrix[0][counter1] = role;
            counter1 = counter1 + 1;
        }
        //add the user names to matrix

        counter2 = 1;
        for (String individual : individualUsers)
        {
            userRoleMatrix[counter2][0] = individual;
            counter2 = counter2 + 1;
        }
        userRoleMatrix[0][0] = "--";
        br5.close();

        File userFile2 = new File("userRoles.txt");
        BufferedReader toPopulateUserRoles = new BufferedReader(new FileReader(userFile2));
        while ((lineRead = toPopulateUserRoles.readLine()) != null)
        {
            String[] fragments = lineRead.split("\t");
            int row = individualUsers.indexOf(fragments[0]) + 1;

            //Adding users to matrix
            for (int i = 1; i < fragments.length; i++) //navigating through the fragments
            {
                ArrayList<String> roleTemp = new ArrayList<String>();
                roleTemp.addAll(Arrays.asList(userRoleMatrix[0]));
                int column = roleTemp.indexOf(fragments[i]);
                userRoleMatrix[row][column] = "+";
            }
        }
        //Printing of the User Role Matrix
        System.out.println("\n\nThe User Role Matrix Matrix ");
        System.out.println("        leftmost column = users, top row = roles");
        for(int i = 0; i < userRoleMatrix.length; i++) //dealing with rows
        {
            String line = "";
            for (int j = 0; j < userRoleMatrix[0].length; j++) //dealing with columns
            {
                String temp = "" + userRoleMatrix[i][j];
                line = line + "\t" + String.format("%5s", temp);
            }
            System.out.println(line);
        }
        //problem 7 doing the query
        boolean doingQuery = true;
        while(doingQuery)
        {
            //problem 7.1
            String user = "";
            String object = "";
            String access = "";

            boolean userInUserRoleMatrix = false;
            while (!userInUserRoleMatrix)
            {
                System.out.println("Please enter the user in your query: ");
                user = input.nextLine();
                System.out.println("Please enter the object in your query: ");
                object = input.nextLine();
                System.out.println("Please enter the access right in your query: ");
                access = input.nextLine();
                userInUserRoleMatrix = individualUsers.contains(user); //problem 7.2
                if (!userInUserRoleMatrix)
                {
                    System.out.println("invalid user, try again.");
                }
            }
            //problem 7.3
            int userRoleMatrixRow = individualUsers.indexOf(user) + 1;

            boolean userWithObjectHasAccess = false;
            ArrayList<String> usersRoles = new ArrayList<String>();

            for (int j = 1; j < userRoleMatrix[0].length; j++) //dealing with columns
            {
                if(userRoleMatrix[userRoleMatrixRow][j] != null)
                {
                    usersRoles.add(userRoleMatrix[0][j]);
                }
            }


            //hunting for the object
            boolean objectInRoleMatrix = false;
            int objectsColumn = 0;

            for (int j = 1; j < roleObjectMatrix[0].length; j++) //dealing with columns
            {
                if (roleObjectMatrix[0][j].equals(object))
                {

                    objectsColumn = j;
                    objectInRoleMatrix = true;
                }
            }
            boolean objectHasAccessRights = false;

            if(objectInRoleMatrix)
            {

                for(int i = 0; i < roleObjectMatrix.length; i++) //dealing with rows
                {
                    if(roleObjectMatrix[i][objectsColumn] != null)
                    {
                        objectHasAccessRights = true;
                    }
                }
            }

            if(!objectHasAccessRights)
            {   System.out.println("\nAll of the objects that the user has access rights to\n");
                for (String individualRole: usersRoles)
                {

                    int row = 1;

                    for(int i = 1; i < roleObjectMatrix.length; i++) //dealing with rows
                    {
                        if(roleObjectMatrix[i][0].equals(individualRole))
                        {
                          row = i;
                        }
                    }

                    for (int j = 1; j < roleObjectMatrix[0].length; j++) //dealing with columns
                    {
                        if(roleObjectMatrix[row][j] != null)
                        {
                            System.out.println(roleObjectMatrix[0][j] + "\t" + roleObjectMatrix[row][j]);
                        }
                    }
                }
            }
            boolean isAuthorized = false;
            if(objectHasAccessRights)
            {
                for (String individualRole: usersRoles)
                {

                    for(int i = 1; i < roleObjectMatrix.length; i++) //dealing with rows
                    {
                        if(roleObjectMatrix[i][0].equals(individualRole))
                        {
                            if (roleObjectMatrix[i][objectsColumn] != null)
                            {
                                if (roleObjectMatrix[i][objectsColumn].contains(access)) {
                                    isAuthorized = true;
                                }
                            }
                        }
                    }

                }
            }
            if (isAuthorized)
            {
                System.out.println("authorized");
            }
            else
            {
                System.out.println("not authorized");
            }
            System.out.println("Would you like to continued for the next query?");
            String answer = input.nextLine();
            if (answer != "yes")
            {
                doingQuery = false;
            }
        }




        input.close();
        br.close();
        br2.close();
        br3.close();
        br4.close();
        toPopulateUserRoles.close();
    }
}

//derived from https://www.javatpoint.com/java-program-to-create-and-display-a-singly-linked-list
class Node
{
    ArrayList<Node> ascendants = new ArrayList<Node>();
    Node descendant = null; //the inheritor
    ArrayList<String> theRoles = new ArrayList<String>();


    public Node(String data)
    {
        this.theRoles.add(data);
    }

    public void printTree()
    {
        int ascendantsSize = this.ascendants.size();
        String temp = this.theRoles.get(0) + " -> ";

        if(ascendantsSize != 0)
        {
            for (int i = 0; ascendantsSize > i; i++)
            {
                if (i + 1 == ascendantsSize)
                {
                    temp = temp + this.ascendants.get(i).theRoles.get(0);
                }
                else
                {
                    temp = temp + this.ascendants.get(i).theRoles.get(0) + ", ";
                }
            }
            System.out.println(temp);
        }
        for (Node ascendant : this.ascendants)
        {
            ascendant.printTree();
        }
    }

    public void inherit()
    {
        if(descendant != null)
        {
            for (String role : this.theRoles)
            {
                if (!descendant.theRoles.contains(role))
                {
                    descendant.theRoles.add(role);
                }
            }
            descendant.inherit();
        }
    }


    public void InheritAccessRights(String role, String access, String object, String[][] matrix, Node rootNode)
    {
        int rowCount = rootNode.theRoles.indexOf(role) + 1;

        int columnCount = 1;

        for (int i = 1; i < matrix[0].length; i++)
        {
            if (matrix[0][i].equals(object))
            {
                columnCount = i;
            }
        }

        boolean notOwnedByMySelf = true;
        if (role == object && access == "own")
        {
            notOwnedByMySelf = false;
        }

        if (matrix[rowCount][columnCount] == null && notOwnedByMySelf)
        {

            matrix[rowCount][columnCount] = access;

        }
        if((!matrix[rowCount][columnCount].contains(access)) && (!(matrix[rowCount][columnCount] == null)) && notOwnedByMySelf)
        {
                matrix[rowCount][columnCount] = matrix[rowCount][columnCount] + " " + access;
        }

        if(descendant != null)
        {
            descendant.InheritAccessRights(descendant.theRoles.get(0), access, object, matrix, rootNode);
        }
    }


}