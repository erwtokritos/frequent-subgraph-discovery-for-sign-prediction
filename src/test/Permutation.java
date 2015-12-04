/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 * This class returns the permutations of <tt>{0, 1, ..., n-1}</tt> in
 * lexicographical order.<BR><BR> Permutations of any distinct set of n elements
 * can be obtained by mapping the elements to the set <tt>{0, 1, ...,
 * n-1}</tt>.<BR><BR>
 *
 * Pseudo code taken from:<BR> Kenneth H. Rosen, <i>Discrete Mathematics and Its
 * Applications</i>, 3rd edition, McGraw-Hill (1995), pp. 293-295
 */
public class Permutation {

    /**
     * @param n The number of distinct elements to permute.
     */
    public Permutation(int n) {
        this.n = n;
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
    }

    /**
     * @param iArray integer array containing the initial order of the
     * permutation. Calls to <tt>getNext()</tt> will return permutations that
     * come after this set.
     */
    public Permutation(int[] iArray) {
        this.n = iArray.length;
        a = new int[n];
        boolean[] t = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (iArray[i] < 0 || iArray[i] >= n) {
                throw new IllegalArgumentException("Invalid permutation element");
            }
            if (t[iArray[i]]) {
                throw new IllegalArgumentException("Invalid permutation element: duplicate values");
            }
            t[iArray[i]] = true;
            a[i] = iArray[i];
        }
    }

    /**
     * Returns <tt>true</tt> if there are more permutations.
     *
     * @return <tt>true</tt> - if there are more permutations. values from the
     * bins.<BR><tt>false</tt> - if there are no more permutations.
     */
    public boolean hasNext() {
        int j = n - 1;
        for (int i = 0; i < n; i++) {
            if (a[i] != j--) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the next permutation as set of integers.
     *
     * @return integer array of the permutations.
     * @throws IllegalStateException - if there are no more permutations. Calls
     * to getNext() should be preceeded by hasNext().
     */
    public int[] getNext() {
        if (isFirstCall) {
            isFirstCall = false;
            return a;
        }

        if (!hasNext()) {
            throw new IllegalStateException("No more permutations.");
        }

        int j = n - 2;
        while (a[j] > a[j + 1]) {
            j--;
        }

        int k = n - 1;
        while (a[j] > a[k]) {
            k--;
        }

        int temp = a[k];
        a[k] = a[j];
        a[j] = temp;

        int r = n - 1;
        int s = j + 1;
        while (r > s) {
            temp = a[s];
            a[s] = a[r];
            a[r] = temp;
            r--;
            s++;
        }
        return a;
    }
    private int[] a;
    private int n;
    private boolean isFirstCall = true;
}