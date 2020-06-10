//Name Adam Schaible
//CS4050
//Programming Assignment 1

// code modified from https://megocode3.wordpress.com/2008/01/28/8/ and combined with
// http://www.java2s.com/Tutorial/Java/0140__Collections/Quicksortsimpleversionofquicksort.htm
//My comments (Adam Schaible) end with -AS at the end of the comment
class QuickSortWithInsertionSorting {

    private int regionSortSize;

    public void quickSort(int[] intArray,int sortSize) {
        regionSortSize = sortSize;
        Sort(intArray,0,intArray.length-1);
        //OUTPUT TEST I WROTE TO MAKE SURE IT IS SORTING PROPERLY, COMMENTED OUT ONCE I SAW IT WORKED PROPERLY -AS
//        System.out.println("Quicksorted with insertion sorted array: ");
//        int z;
//        String m = "";
//        m += "{ ";
//        for (z = 0; z < intArray.length; z++)
//            m += intArray[z] + ",";
//        m = m.substring(0,m.length()-1);
//        m += "}";
//        System.out.println(m);
        //END OF TEST
    }
    //wordpress code starts here
    private void Sort(int[] list, int start, int end)
    {
        if ((start < end))
        {
            //  This is where we switch to Insertion Sort!
            if (start < end)
            {
                if (end-start<regionSortSize)
                {
                    this.InsertionSort(list, start, (end + 1));
                }
                else
                {
                    int part = this.partition(list, start, end);
                    this.Sort(list, start, part- 1);
                    this.Sort(list, part + 1, end);
                }
            }
        }

    }

    public final void InsertionSort(int[] list, int start, int end) {
        for (int x = (start + 1); (x < end); x++) {
            int val = list[x];
            int j = x-1;

            while (j >= 0 && val < list[j])
            {
                list[j + 1] = list[j];
                j--;
            }

            list[(j + 1)] = val;
        }

    }

    private static int partition(int[] list, int leftIndex, int rightIndex) {
        int left = leftIndex;
        int right = rightIndex;
        int pivot = list[leftIndex];
        while ((left < right)) {
            if ((list[left] < pivot)) {
                left++;
                continue;
            }

            if ((list[right] > pivot)) {
                right--;
                continue;
            }

            int tmp = list[left];
            list[left] = list[right];
            list[right] = tmp;
            left++;
        }

        return left;
    }
}
