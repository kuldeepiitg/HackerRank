package com.inbhiwadi;

import static org.junit.Assert.assertEquals;

/**
 * Junit test for {@link Coins}.
 * <p>
 * Created by Kuldeep Yadav on 13/10/16.
 */
public class CoinsTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void change() throws Exception {
        Coins coins = new Coins(4, new int[]{1, 2, 3});
        assertEquals(4, coins.change());

        coins = new Coins(10, new int[]{2, 5, 3, 6});
        assertEquals(5, coins.change());

        coins = new Coins(250, new int[]{8, 47, 13, 24, 25, 31, 32, 35, 3,
                19, 40, 48, 1, 4, 17, 38, 22, 30, 33, 15, 44, 46, 36, 9, 20, 49});
        assertEquals(3542323427L, coins.change());
    }
}