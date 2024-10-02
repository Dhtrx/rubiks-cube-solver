package model;

public class Utils {

    /**
     * A utility method to reverse an array of primitive integers.
     * @param a The int array to be reversed
     * @return The reversed int array
     */
    public static int[] reverseIntArray(int a[])
    {
        int[] b = new int[a.length];
        int j = a.length;
        for (int k : a) {
            b[j - 1] = k;
            j = j - 1;
        }
        return b;
    }

}
