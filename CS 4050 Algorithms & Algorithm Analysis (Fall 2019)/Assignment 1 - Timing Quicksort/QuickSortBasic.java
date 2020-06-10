//Name Adam Schaible
//CS4050
//Programming Assignment 1

//The code for this java is derived from http://www.java2s.com/Tutorial/Java/0140__Collections/Quicksortsimpleversionofquicksort.htm
//My comments (Adam Schaible) end with -AS at the end of the comment

public class QuickSortBasic {
//deleted main method -AS

    public static void quickSort(int[] intArray) {
        recQuickSort(intArray, 0, intArray.length - 1);
        //WROTE OUTPUT TEST TO MAKE SURE IT IS SORTING PROPERLY, THEN COMMENTED IT OUT SEEING IT WORKED -AS
//        System.out.println("Quicksorted BASIC sorted array: ");
//        int z;
//        String m = "";
//        m += "{ ";
//        for (z = 0; z < intArray.length; z++)
//            m += intArray[z] + ",";
//        m = m.substring(0,m.length()-1);
//        m += "}";
//        System.out.println(m);
//        END OF TEST
    }

    private static void recQuickSort(int[] intArray, int left, int right) {
        if (right - left <= 0)
            return;
        else {
            int pivot = intArray[right];

            int partition = partitionIt(intArray, left, right, pivot);
            recQuickSort(intArray, left, partition - 1);
            recQuickSort(intArray, partition + 1, right);
        }
    }

    private static int partitionIt(int[] intArray, int left, int right, int pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (intArray[++leftPtr] < pivot)
                ;

            while (rightPtr > 0 && intArray[--rightPtr] > pivot)
                ;

            if (leftPtr >= rightPtr)
                break;
            else
                swap(intArray, leftPtr, rightPtr);
        }
        swap(intArray, leftPtr, right);
        return leftPtr;
    }

    private static void swap(int[] intArray, int dex1, int dex2) {
        int temp = intArray[dex1];
        intArray[dex1] = intArray[dex2];
        intArray[dex2] = temp;
    }
}
