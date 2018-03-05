import java.util.Random;

public class Sorting {

    /*
     * For each pair, if they're out of order, swap them. Bubble up.
     *
     * Best: O(n^2)
     * Average: O(n^2)
     * Worst: O(n^2)
     * Space: O(1)
     */
    void BubbleSort() {

    }
    /* At each pass, check if the array is already sorted.
     *
     * Best: O(n)
     * Average: O(n^2)
     * Worst: O(n^2)
     * Space: O(1)
     */
    void ModifiedBubbleSort() {

    }
    /*
     * Small constant factor
     *
     * Best: O(n)
     * Average: O(n^2)
     * Worst: O(n^2) - sorted in the reverse order
     * Space: O(1)
     */
    void InsertionSort() {

    }
    /*
     * Use a pivot
     *
     * Best: O(n log(n)) - pivot happens to divide in n/2 halves
     * Average: O(n log(n)) - array is already sorted and the partition is n-1: 1
     * Worst: O(n^2) v
     * Space: O(1)
     */
    int[] QuickSort(int[] array) {
        int pivot = array[0];
        int[] left = new int[array.length];
        int liter = 0;
        int[] right = new int[array.length];
        int riter = 0;
        for( int i = 0; i <= array.length; i++) {
            if (array[i] <= pivot) {
                left[liter++] = array[i];
            } else {
                right[riter++] = array[i];
            }
        }
        left = QuickSort(left);
        right = QuickSort(right);
        for(int i=0; i< left.length; i++) {
            array[i] = left[i];
        }
        for (int i=left.length; i < right.length; i++) {
            array[i] = right[i-left.length];
        }
        return array;
    }
    /*
     * Choose a random pivot
     *
     * Best: O(n log(n))
     * Average: O(n log(n))
     * Worst: O(n log(n))
     * Space: O(1)
     */
    void RandomizedQuickSort() {

    }
    /*
     * Best to sort linked lists (constant extra space)
     * Best for a very large number of elements that can't fit in memory
     *
     * Best: O(n log(n))
     * Average: O(n log(n))
     * Worst: O(n log(n))
     * Space: O(n)
     */
    void MergeSort(int[] array) {

    }
    int[] mergeSortHelper(int[] array, int i, int j) {
        int[] L = mergeSortHelper(array, i, (i+j/2));
        int[] R = mergeSortHelper(array, (i+j)/2+1, j);
        return merge(L, R);
    }
    int[] merge(int[] left, int[] right) {
        int[] D = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < left.length && j < right.length) {
            if(left[i]<right[j]) { D[k]=left[i]; i++; }
            else { D[k]=R[j]; j++; }
            k++;
        }
        return D;
    }

    int[] mergeRec(int[] left, int liter, int[] right, int riter, int[] array, int aiter) {
        // check existence
        // if(left.length != 0) {
        //	if(right.length != 0) {
        if(right[riter]< left[liter]) {
            array[++aiter] = right[riter];
            mergeRec(right, ++riter, left, liter, array, aiter);
        } else {
            array[++aiter] = left[liter];
            mergeRec(right, riter, left, ++liter, array, aiter);
        }
        return array;
        //	}
        //}
    }
    /*
     * Best: O(n log(n))
     * Average: O(n log(n))
     * Worst: O(n log(n))
     * Space: O(1)
     */
    void HeapSort() {

    }

    // Non-comparison where k is the range of numbers in the list
    /*
     * When the range of the data isn't that much different from the range of indexes
     * Best: O(n+k)
     * Average: O(n+k)
     * Worst: O(n+k)
     * Space: O(n+2^k)
     */
    void CountingSort() {

    }
    /*
     * Best: O(n*k/s)
     * Average: O(n*k/s)
     * Worst: O(2^s*n*k/2)
     * Space: O(n)
     */
    void RadixSort() {

    }
    /*
     * Best: O(n*k)
     * Average: O(n^2*k)
     * Worst: O(n*k)
     * Space: O(n*k)
     */
    void BucketSort() {

    }

    // Selection
    /*
     * Find kth value in unsorted list
     */
    Random lolXD = new Random();
    int findValue(int k, int[] array) {
        int[] left = new int[array.length-1];
        int liter = 0;
        int[] right = new int[array.length-1];
        int riter = 0;
        int[] pivotArr = new int[array.length];
        int piter = 0;
        int pivot = lolXD.nextInt(array.length - 1);
        // may need try/catch block
        for (int i = 0; i < array.length; i++) {
            if(array[i] == array[pivot]) {
                pivotArr[piter++] = array[i];
            }
            else if(array[i]<array[pivot]) {
                left[liter++] = array[i];
            } else {
                right[riter++] = array[i];
            }
        }
        if (k == liter) {
            return array[pivot];
        } else if (k < liter) {
            return findValue(k, left);
        } else {
            return findValue(k-pivot, right);
        }
    }
    /*
     * Find median of medians
     */
    int findMedian(int[] array) {
        //for(int i = 0; i < Math.ceil(array.length/5); i++) {

        //}
        // sort in groups of 5, find median of medians in array of medians
        // select to find the n/10th element in the array
        // partition all elements around this median, with k being its rank
        // if i== k, return k
        // if i < k, recurse over Select(L, k-1, i)
        // else i > k, Select(R, n-k, 9-k) T(n-k)
    }
}
