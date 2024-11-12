package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void reverseIntArray() {

        int[] test = new int[]{1,2,6,2,4};
        assertArrayEquals(new int[]{4,2,6,2,1}, Utils.reverseIntArray(test));

    }
}