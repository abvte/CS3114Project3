/**
 * Quicksort
 */

/**
 * The class containing the main method.
 *
 * @author Adam Bishop
 * @author Kevin Zhang
 * @version 1
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class Quicksort {
    /**
     * @param args
     *      Command line parameters.
     */
    public static void main(String[] args) {
        // This is the main file for the program.
    }
    
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
            return (i+j)/2;
        }
        
        void quicksort(short[] A, int i, int j) {
            // Quicksort
            int pivotindex = findpivot(A, i, j);
            // Pick a pivot
            swap(A, pivotindex, j);
            // Stick pivot at end
            // k will be the first position in the right subarray
            int k = partition(A, i, j-1, A[j]);
            swap(A, k, j);
            // Put pivot in place
            if ((k-i) > 1) quicksort(A, i, k-1);
            // Sort left partition
            if ((j-k) > 1)
                quicksort(A, k+1, j);
            // Sort right partition }
        }
        
        private void swap(short[] A, int pos1, int pos2) {
            short temp = A[pos1];
            A[pos1] = A[pos2];
            A[pos2] = temp;
        }
}
