//Name Adam Schaible
//CS4050
//Programming Assignment 1

//Code is a modification from https://www.geeksforgeeks.org/hoares-vs-lomuto-partition-scheme-quicksort/
//and www.java2s.com/Tutorial/Java/0140__Collections/Quicksortsimpleversionofquicksort.htm
//My comments (Adam Schaible) end with -AS at the end of the comment
public class QuickSortWithHoares {

    public void quickSortStart(int[] intArray) {
        quickSort(intArray, 0, intArray.length - 1);
        //OUTPUT TEST I WROTE TO MAKE SURE IT IS SORTING PROPERLY, COMMENTED OUT WHEN I SAW IT WAS WORKING PROPERLY -AS
//        System.out.println("Quick Sorted With Hoares Partition: ");
//        int z;
//        String m = "";
//        m += "{ ";
//        for (z = 0; z < intArray.length; z++)
//            m += intArray[z] + ",";
//        m = m.substring(0,m.length()-1);
//        m += "}";
//        System.out.println(m);
//        System.out.println("\n");
        //END OF TEST
    }

    /* The main function that
       implements QuickSort
    arr[] --> Array to be sorted,
    low --> Starting index,
    high --> Ending index */
    static void quickSort(int[] arr, int low,
                          int high)
    {
        if (low < high)
        {
        /* pi is partitioning index,
        arr[p] is now at right place */
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }



    static void Swap(int[] array,
                     int position1,
                     int position2)
    {
        // Swaps elements in an array

        // Copy the first position's element
        int temp = array[position1];

        // Assign to the second element
        array[position1] = array[position2];

        // Assign to the first element
        array[position2] = temp;
    }

    /* This function takes last element as
    pivot, places the pivot element at its
    correct position in sorted array, and
    places all smaller (smaller than pivot)
    to left of pivot and all greater elements
    to right of pivot */

    static int partition(int []arr, int low,
                         int high)
    {
        int pivot = arr[low];
        int i = low - 1, j = high + 1;

        while (true)
        {
            // Find leftmost element greater
            // than or equal to pivot
            do
            {
                i++;
            } while (arr[i] < pivot);

            // Find rightmost element smaller
            // than or equal to pivot
            do
            {
                j--;
            } while (arr[j] > pivot);

            // If two pointers met.
            if (i >= j)
                return j;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            //swap(arr[i], arr[j]);
        }
    }

// This code is contributed by vt_m.

}
