//Name Adam Schaible
//CS 4050
//Programming assignment # 3
//COMMENTS MADE BY ME (ADAM SCHAIBLE) HAVE -AS AT THE END OF THE LINE
//derived from https://www.techiedelight.com/print-possible-solutions-n-queens-problem/

import java.util.Arrays;
import java.util.Random;

class QueensTest
{
    // N x N chessboard
    public static final int N = 8;



    public static void main(String[] args)
    {
        //time, added by me -AS
        long start, end;
        //arrays used to hold the time it takes each queens problem, added by me -AS
        long [] timeGenerateSolutions = new long[9];


        //MY BRUTE FORCE STUFF -AS
        //added loop used to randomly glue up to 8 queens the board followed by backtracking and seeing how long this takes time wise -AS
        for (int numberOfQueensGluedToBoard = 0; numberOfQueensGluedToBoard<= 8;numberOfQueensGluedToBoard++)
        {
            QueensTest queen = new QueensTest();

            start = System.nanoTime();
            char[][] gluedBoardSetup = queen.gluedBoard(numberOfQueensGluedToBoard);
            nQueen(gluedBoardSetup, 0);
            end = System.nanoTime();
            timeGenerateSolutions[numberOfQueensGluedToBoard] = end - start;
        }
        System.out.println("AMOUNT OF TIME TAKEN TO GET 8 QUEENS SOLUTIONS: \n");
        for (int i = 0; i < 9; i++)
        {
            System.out.println(i + " queens glued randomly to board followed by backtracking " + (8-i) + " remaining queens took: " + timeGenerateSolutions[i] + " milliseconds");
        }
    }




    // Function to check if two queens threaten each other or not
    //DELETED IS SAFE METHOD -AS

    private static void nQueen(char mat[][], int r)
    {
        // if N queens are placed successfully, print the solution
        if (r == N)
        {
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                    System.out.print(mat[i][j] + " ");
                System.out.println();
            }
            System.out.println();

            return;
        }

        // place Queen at every square in current row r
        // and recur for each valid movement
        for (int i = 0; i < N; i++)
        {
            // if no two queens threaten each other
            if (isSafe2(mat, r, i))
            {
                // place queen on current square
                mat[r][i] = 'Q';

                // recur for next row
                nQueen(mat, r + 1);

                // backtrack and remove queen from current square
                mat[r][i] = '-';
            }
        }
    }



    //wrote glued board myself -AS
    char[][] gluedBoard(int numberOfQueens)
    {
        //borrowed initialization from main, 8x8 chessboard -AS
        char[][] mat = new char[8][8];

        for (int i = 0; i < N; i++)
        {
            Arrays.fill(mat[i], '-');// initialize mat[][] by '-'
        }

        Random num = new Random();


        boolean notDoneGluingQueensToBoard = true;
        int numberOfQueensNowGluedToBoard = 0;
        int numberOfAttemptsToPutQueenOnBoard = 0;
        while (notDoneGluingQueensToBoard && numberOfQueens != 0)
        {
            int row = num.nextInt(8);
            int column = num.nextInt(8);
            boolean noConflictingQueens = isSafe2(mat,row,column);

            if (noConflictingQueens)
            {
                mat[row][column] = 'Q';
                numberOfQueensNowGluedToBoard = numberOfQueensNowGluedToBoard + 1;
            }

            if (numberOfQueensNowGluedToBoard == numberOfQueens)
            {
                notDoneGluingQueensToBoard = false;
            }

            numberOfAttemptsToPutQueenOnBoard = numberOfAttemptsToPutQueenOnBoard + 1;
            //If number of attempts is insane, reset board and start over
            if (numberOfAttemptsToPutQueenOnBoard > 1000)
            {
                for (int i = 0; i<8; i++)
                {
                    for (int j = 0; j<8; j++)
                    {
                        mat[i][j] = '-';
                    }
                }

                numberOfAttemptsToPutQueenOnBoard = 0;
                numberOfQueensNowGluedToBoard = 0;
            }
        }
        return mat;
    }

    //printSolution derived from https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
    //printSolution was used for testing purposes -AS
    void printSolution(char board[][])
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
        System.out.println("\n\n"); //added newlines -AS
    }
    //isSafe derived from https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
    private static boolean isSafe2(char[][] board, int row, int col)
    {
        int i, j;

        //Modified to check the entire row -AS
        for (i = 0; i < 8; i++)
            if (board[row][i] == 'Q')
                return false;

        //Modified to check the entire column -AS
        for (i = 0; i < 8; i++)
            if (board[i][col] == 'Q')
                return false;

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q')
                return false;

        // Added check lower diagonal on right side -AS
        for (i = row, j = col; i < 8 && j < 8; i++, j++)
            if (board[i][j] == 'Q')
                return false;

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < 8; i++, j--)
            if (board[i][j] == 'Q')
                return false;

        // Added check upper diagonal on right hand side -AS
        for (i = row, j = col; j < 8 && i >= 0; i--, j++)
            if (board[i][j] == 'Q')
                return false;
        return true;
    }
}