/**
 * Quicksort algorithm
 * 
 * @author Kevin Zhang
 * @author Adam Bishop
 * @version 1.0
 *
 */
public class Sort {

    int partition(short[] A, int left, int right, short pivot) {
        while (left <= right) {
            // Move bounds inward until they meet
            while (A[left] < pivot)
                left++;
            while ((right >= left) && (A[right] >= pivot))
                right--;
            if (right > left)
                swap(A, left, right);
            // Swap out-of-place values
        }
        return left;
        // Return first position in right partition }
    }

    private int findpivot(short[] A, int i, int j) {
        return (i + j) / 2;
    }

    void quicksort(short[] A, int i, int j) {
        // Quicksort
        int pivotindex = findpivot(A, i, j);
        // Pick a pivot
        swap(A, pivotindex, j);
        // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(A, i, j - 1, A[j]);
        swap(A, k, j);
        // Put pivot in place
        if ((k - i) > 1)
            quicksort(A, i, k - 1);
        // Sort left partition
        if ((j - k) > 1)
            quicksort(A, k + 1, j);
        // Sort right partition }
    }

    private void swap(short[] A, int pos1, int pos2) {
        short temp = A[pos1];
        A[pos1] = A[pos2];
        A[pos2] = temp;
    }
}