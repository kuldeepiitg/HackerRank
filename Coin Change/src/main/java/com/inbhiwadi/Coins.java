package com.inbhiwadi;

/**
 * Game with coins :)
 * <p>
 * Created by Kuldeep Yadav on 13/10/16.
 */
class Coins {

    /**
     * Dollars value to be made.
     */
    private int amount;

    /**
     * Denominations of all the coins supply.
     */
    private int[] denominations;

    /**
     * Store for saving values to use.
     * <p>
     * store[i][j] represents count of ways to make i amount from coins (Cj, Cj+1, ... Cm-1)
     */
    private long[][] store;

    public Coins(int amount, int[] denominations) {
        this.amount = amount;
        this.denominations = denominations;
        store = new long[amount + 1][denominations.length];
        for (int i = 0; i < amount + 1; i++) {
            for (int j = 0; j < denominations.length; j++) {
                store[i][j] = -1;
            }
        }
    }

    /**
     * Count number of distinct ways to get amount. Distinct means that set {1,2} is same as {2,1}.
     * Only consider combinations not permutations.
     *
     * @return count of distinct possible ways
     */
    public long change() {
        return change(amount, 0);
    }

    /**
     * Count number of ways to get amount using denomination Ci, Ci+1 ... Cm-1.
     *
     * @param dollars amount to get.
     * @param i       starting index in denomination array. All coins with denominations at index i and later
     *                will be used to get the amount.
     * @return count of possible ways.
     */
    private long change(int dollars, int i) {
        if (dollars == 0) {
            return 1;
        } else if (dollars < 0) {
            return 0;
        } else {
            if (store[dollars][i] >= 0) {
                return store[dollars][i];
            }

            long count = 0;
            for (int j = i; j < denominations.length; j++) {
                count += change(dollars - denominations[j], j);
            }
            store[dollars][i] = count;
            return count;
        }
    }
}
