//Name Adam Schaible
//CS4050
//Assignment # 5
//Graph generation code derived from https://www.geeksforgeeks.org/find-paths-given-source-destination/
//Comments made by me have -AS at the end of the comment line

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph
{
    private int v; // No. of vertices in graph
    private ArrayList<Integer>[] adjList; // adjacency list

    public Graph(int vertices)
    {
        this.v = vertices;
        initAdjList();
    }
    @SuppressWarnings("unchecked")
    private void initAdjList()
    {
        adjList = new ArrayList[v];
        for(int i = 0; i < v; i++)
        {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) // add edge from u to v
    {
        adjList[u].add(v); // Add v to u's list.
    }
    //added currencyNames and potentialProfitableTrades to printAllPaths -AS
    // Prints all paths from 's' to 'd'
    public void printAllPaths(int s, int d, ArrayList<String> currencyNames,ArrayList<ArrayList<String>> potentialProfitableTrades)
    {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(s); //add source to path[]
        //added currencyNames to printAllPaths -AS
        printAllPathsUtil(s, d, isVisited, pathList, s, currencyNames, potentialProfitableTrades); //Call recursive utility

    }

    // Note: printAllPathsUtil prints all paths from u to d using recursion -AS
    // Added int s to printAllPathsUtil parameters to know originating node -AS
    // Added currencyNames and potentialProfitableTrades to printAllPathsUtil -AS
    private void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, ArrayList<Integer> localPathList, int s, ArrayList<String> currencyNames, ArrayList<ArrayList<String>> potentialProfitableTrades)
    {

        isVisited[u] = true;  // Mark the current node
        if (u.equals(d) && localPathList.size()>1) //print out path and stop program, line edited to allow for source = destination node -AS
        {
            int i = localPathList.indexOf(s);
            ArrayList<String> shortenedList = new ArrayList<>();
            boolean sFoundInLoop = false;
            for(int x : localPathList)
            {
                if (x == s)
                {
                    sFoundInLoop = true;
                }
                if (sFoundInLoop)
                {
                    shortenedList.add(currencyNames.get(x));
                }
            }
            if (shortenedList.size()>3 && !potentialProfitableTrades.contains(shortenedList))
            {
                potentialProfitableTrades.add(shortenedList);
            }

            isVisited[u] = false; // if match found then no need to traverse more till depth
            return;
        }

        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjList[u])
        {
            if (!isVisited[i] | i == s) //added i==s condition so source can be revisited as destination -AS
            {
                // store current node in path[]
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList, s, currencyNames, potentialProfitableTrades);
                // remove current node in path[]

                localPathList.remove(i);
            }
        }
        isVisited[u] = false; // Mark the current node
    }

    public static void main(String[] args) throws IOException //The code in main is code that I wrote -AS
    {
        String lineRead;
        Scanner input = new Scanner(System.in);
        File roleFile = new File("exchangeRatesF2016.txt");
        BufferedReader br = new BufferedReader(new FileReader(roleFile));
        boolean startReadingHere = false;
        int numberOfVertices = 0;
        Graph exchangeGraph = null;

        ArrayList<String> currencyNames = new ArrayList<String>();
        ArrayList<String> directionalEdges = new ArrayList<String>();

        while ((lineRead = br.readLine()) != null)
        {
            if(startReadingHere && !lineRead.isEmpty()) //then reading the actual exchange rates lines
            {
                if (Character.isLetter(lineRead.charAt(0)))
                {
                    String[] parsedLine = lineRead.split("\t");
                    int counter = 0;
                    String[] currencyLine = new String[3];
                    for(String part : parsedLine)
                    {
                        if (!part.isEmpty())
                        {
                            currencyLine[counter] = part;
                            counter = counter + 1;
                        }

                    }
                    directionalEdges.add(currencyLine[0]+ "," + currencyLine[1] + "," + currencyLine[2]); //forward direction
                    float ratio = Float.parseFloat(currencyLine[2]);
                    float inverseCurrencyRatio = 1/ratio;
                    directionalEdges.add(currencyLine[1]+ "," + currencyLine[0] + "," + inverseCurrencyRatio); //reverse direction

                    if (!currencyNames.contains(currencyLine[0]))
                    {
                        currencyNames.add(currencyLine[0]);
                    }
                    if (!currencyNames.contains(currencyLine[1]))
                    {
                        currencyNames.add(currencyLine[1]);
                    }

                    int originNode = currencyNames.indexOf(currencyLine[0]);
                    int destinationNode = currencyNames.indexOf(currencyLine[1]);
                    exchangeGraph.addEdge(originNode,destinationNode);
                    exchangeGraph.addEdge(destinationNode,originNode); //added edge to go backwards to make it bidirectional
                }
            }



            if (!startReadingHere &&  !lineRead.isEmpty()) //reading the first line
            {
                if (Character.isDigit(lineRead.charAt(0)))
                {
                    startReadingHere = true;
                    numberOfVertices = Character.getNumericValue(lineRead.charAt(0)); //added keep track
                    exchangeGraph = new Graph(numberOfVertices);
                }
            }
        }

        boolean stillWantingToDoTrades = true;

        while (stillWantingToDoTrades)
        {
            int slotNumberOfStartingCurrencyInCurrencyNames = -1;
            while (slotNumberOfStartingCurrencyInCurrencyNames == -1)
            {
                System.out.println("\n\nType in the currency that you want to trade in with only the first letter capitalized");
                System.out.println("Available currencies are: " + currencyNames);
                System.out.println("\n The currency that you want to trade in is : ");
                String startingCurrency = input.nextLine();
                if (currencyNames.contains(startingCurrency))
                {
                    slotNumberOfStartingCurrencyInCurrencyNames = currencyNames.indexOf(startingCurrency);
                }
                else
                {
                    System.out.println("You did not not type in a available currency");
                    System.out.println("Check your spelling and make sure that only the first letter is capitalized and there are no spaces\n");
                }
            }

            ArrayList<ArrayList<String>> potentialProfitableTrades = new ArrayList<ArrayList<String>>();


            assert exchangeGraph != null;
            exchangeGraph.printAllPaths(slotNumberOfStartingCurrencyInCurrencyNames, slotNumberOfStartingCurrencyInCurrencyNames, currencyNames, potentialProfitableTrades);
            System.out.println("If you invested a 1000 " + currencyNames.get(slotNumberOfStartingCurrencyInCurrencyNames));

            for (ArrayList<String> oneTradingStrategy : potentialProfitableTrades)
            {

                float moneyInvested = 1000;
                for (int countryCount = 0; countryCount < oneTradingStrategy.size()-1; countryCount++)
                {
                    for (String edgeWithWeight : directionalEdges)
                    {
                        String[] edgeParts = edgeWithWeight.split(",");
                        if (edgeParts[0].equals(oneTradingStrategy.get(countryCount)) && edgeParts[1].equals(oneTradingStrategy.get(countryCount + 1)))
                        {
                            moneyInvested = moneyInvested * Float.parseFloat(edgeParts[2]);

                        }
                    }
                }
                if (moneyInvested > 1000)
                {
                    String strategy = "(" + oneTradingStrategy.get(0);
                    for (int exchangeCountry = 1; exchangeCountry < oneTradingStrategy.size(); exchangeCountry++)
                    {
                        strategy = strategy + "-> " + oneTradingStrategy.get(exchangeCountry);
                    }
                    strategy = strategy + ")";

                    System.out.println("The Trading Strategy : " + strategy + " made a profit of " + (moneyInvested - 1000) + " " + oneTradingStrategy.get(0) + "\n");
                }
            }
            System.out.println("\n\nWould you like to perform another trade, then type 'yes' in all lowercase followed by enter ");
            System.out.println("Type anything else followed by enter to exit: ");
            String continueTrading = input.nextLine();
            if (!continueTrading.equals("yes"))
            {
                stillWantingToDoTrades = false;
                System.out.println("Done trading");
            }
        }
    }
}
