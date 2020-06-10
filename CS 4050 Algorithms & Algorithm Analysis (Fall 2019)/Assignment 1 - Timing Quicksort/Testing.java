//Author Adam Schaible
//CS4050
//Programming Assignment 1

import java.util.Random;

public class Testing implements Cloneable
{
    public static void main(String[] args)
    {
        int[] arraySizeRecord = new int[40];

        long[] timeRecordMedianOf3 = new long[40];
        long[] timeRecordQuickSortBasic = new long[40];
        long[] timeRecordHoares = new long[40];
        long[] timeRecordInsertSorting11 = new long[40];
        long[] timeRecordInsertSorting12 = new long[40];
        long[] timeRecordInsertSorting13 = new long[40];
        long[] timeRecordInsertSorting14 = new long[40];
        long[] timeRecordInsertSorting15 = new long[40];
        long[] timeRecordInsertSorting16 = new long[40];
        long[] timeRecordInsertSorting17 = new long[40];
        long[] timeRecordInsertSorting18 = new long[40];
        long[] timeRecordInsertSorting19 = new long[40];
        long[] timeRecordInsertSorting20 = new long[40];

        long[] timeRecordLomato = new long[40];

        int[][] arraysTested = new int[40][];


        int slot = 0;
        long start, end;
        int [] tempArray;

        MedianOf3test median3 = new MedianOf3test();
        QuickSortBasic original = new QuickSortBasic();
        QuickSortWithHoares hoares = new QuickSortWithHoares();
        QuickSortWithInsertionSorting quickInsert = new QuickSortWithInsertionSorting();
        QuickSortWithLomutoPartition lomato = new QuickSortWithLomutoPartition();

        for (int sizeOfArray = 500;sizeOfArray <= 1000850;sizeOfArray = sizeOfArray + 25650)
        {
            tempArray = new int[sizeOfArray];
            Random num = new Random();

            for (int i = 0; i <= sizeOfArray - 1; i++) {
                tempArray[i] = num.nextInt(200);
            }
            arraySizeRecord[slot] = tempArray.length;
            arraysTested[slot] = tempArray;
            slot += 1;
        }
        //backups are a deep copy of the original
        int[][] backup = arraysTested.clone(); 
        int[][] backup2 = arraysTested.clone();
        int[][] backup3 = arraysTested.clone();
        int[][] backup4 = arraysTested.clone();
        int[][] backup5 = arraysTested.clone();
        int[][] backup6 = arraysTested.clone();
        int[][] backup7 = arraysTested.clone();
        int[][] backup8 = arraysTested.clone();
        int[][] backup9 = arraysTested.clone();
        int[][] backup10 = arraysTested.clone();
        int[][] backup11 = arraysTested.clone();
        int[][] backup12 = arraysTested.clone();
        int[][] backup13 = arraysTested.clone();
        int[][] backup14 = arraysTested.clone();

        //timing the different versions of quicksort
        for(int i = 0;i<40;i++) {
            //MEDIAN OF 3 TIMED
            start = System.nanoTime();
            median3.quickSort(backup[i]);
            end = System.nanoTime();
            timeRecordMedianOf3[i] = end - start;

            //QUICKSORT BASIC TIMED
            start = System.nanoTime();
            original.quickSort(backup2[i]);
            end = System.nanoTime();
            timeRecordQuickSortBasic[i] = end - start;

            //QUICKSORT WITH HOARES PARTITION TIMED
            start = System.nanoTime();
            hoares.quickSortStart(backup3[i]);
            end = System.nanoTime();
            timeRecordHoares[i] = end - start;

            //QUICKSORT WITH INSERTION SORT TIMED
            //sort size 11
            start = System.nanoTime();
            quickInsert.quickSort(backup4[i], 11);
            end = System.nanoTime();
            timeRecordInsertSorting11[i] = end - start;

            //sort size 12
            start = System.nanoTime();
            quickInsert.quickSort(backup5[i], 12);
            end = System.nanoTime();
            timeRecordInsertSorting12[i] = end - start;

            //sort size 13
            start = System.nanoTime();
            quickInsert.quickSort(backup6[i], 13);
            end = System.nanoTime();
            timeRecordInsertSorting13[i] = end - start;

            //sort size 14
            start = System.nanoTime();
            quickInsert.quickSort(backup7[i], 14);
            end = System.nanoTime();
            timeRecordInsertSorting14[i] = end - start;

            //sort size 15
            start = System.nanoTime();
            quickInsert.quickSort(backup8[i], 15);
            end = System.nanoTime();
            timeRecordInsertSorting15[i] = end - start;

            //sort size 16
            start = System.nanoTime();
            quickInsert.quickSort(backup9[i], 16);
            end = System.nanoTime();
            timeRecordInsertSorting16[i] = end - start;

            //sort size 17
            start = System.nanoTime();
            quickInsert.quickSort(backup10[i], 17);
            end = System.nanoTime();
            timeRecordInsertSorting17[i] = end - start;

            //sort size 18
            start = System.nanoTime();
            quickInsert.quickSort(backup11[i], 18);
            end = System.nanoTime();
            timeRecordInsertSorting18[i] = end - start;

            //sort size 19
            start = System.nanoTime();
            quickInsert.quickSort(backup12[i], 19);
            end = System.nanoTime();
            timeRecordInsertSorting19[i] = end - start;

            //sort size 20
            start = System.nanoTime();
            quickInsert.quickSort(backup13[i], 20);
            end = System.nanoTime();
            timeRecordInsertSorting20[i] = end - start;

            //QUICKSORT WITH LOMATO PARTITION TIMED
            start = System.nanoTime();
            lomato.quickSortStart(backup14[i]);
            end = System.nanoTime();
            timeRecordLomato[i] = end - start;
        }
        //----------------------------------------------------------
        //printing out the timing results of the sorted array
        //these results were copied from the terminal and pasted into the EXCEL spreadsheets

        System.out.println("Array Sizes Sampled");
        for (int i = 0; i < arraySizeRecord.length; i++)
        {
            System.out.println(arraySizeRecord[i]);
        }

        System.out.println("\nTime:Median of 3");
        for (int i = 0; i < timeRecordMedianOf3.length; i++)
        {
            System.out.println(timeRecordMedianOf3[i]);
        }

        System.out.println("\nTime: QuickSort Basic");
        for (int i = 0; i < timeRecordQuickSortBasic.length; i++)
        {
            System.out.println(timeRecordQuickSortBasic[i]);
        }

        System.out.println("\nTime: QuickSort with Hoares");
        for (int i = 0; i < timeRecordHoares.length; i++) {
            System.out.println(timeRecordHoares[i]);
        }

        System.out.println("\nTime: QuickSort with region size 11");
        for (int i = 0; i < timeRecordInsertSorting11.length; i++)
        {
            System.out.println(timeRecordInsertSorting11[i]);
        }

        System.out.println("\nTime: QuickSort with region size 12");
        for (int i = 0; i < timeRecordInsertSorting12.length; i++)
        {
            System.out.println(timeRecordInsertSorting12[i]);
        }

        System.out.println("\nTime: QuickSort with region size 13");
        for (int i = 0; i < timeRecordInsertSorting13.length; i++)
        {
            System.out.println(timeRecordInsertSorting13[i]);
        }

        System.out.println("\nTime: QuickSort with region size 14");
        for (int i = 0; i < timeRecordInsertSorting14.length; i++)
        {
            System.out.println(timeRecordInsertSorting14[i]);
        }

        System.out.println("\nTime: QuickSort with region size 15");
        for (int i = 0; i < timeRecordInsertSorting15.length; i++)
        {
            System.out.println(timeRecordInsertSorting15[i]);
        }

        System.out.println("\nTime: QuickSort with region size 16");
        for (int i = 0; i < timeRecordInsertSorting16.length; i++)
        {
            System.out.println(timeRecordInsertSorting16[i]);
        }

        System.out.println("\nTime: QuickSort with region size 17");
        for (int i = 0; i < timeRecordInsertSorting17.length; i++)
        {
            System.out.println(timeRecordInsertSorting17[i]);
        }

        System.out.println("\nTime: QuickSort with region size 18");
        for (int i = 0; i < timeRecordInsertSorting18.length; i++)
        {
            System.out.println(timeRecordInsertSorting18[i]);
        }

        System.out.println("\nTime: QuickSort with region size 19");
        for (int i = 0; i < timeRecordInsertSorting19.length; i++)
        {
            System.out.println(timeRecordInsertSorting19[i]);
        }

        System.out.println("\nTime: QuickSort with region size 20");
        for (int i = 0; i < timeRecordInsertSorting20.length; i++)
        {
            System.out.println(timeRecordInsertSorting20[i]);
        }

        System.out.println("\nTime: QuickSort with Lomato");
        for (int i = 0; i < timeRecordLomato.length; i++)
        {
            System.out.println(timeRecordLomato[i]);
        }
    }
}
