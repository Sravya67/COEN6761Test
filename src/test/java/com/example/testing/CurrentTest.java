package com.example.testing;

import com.example.testing.fun.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentTest {

    @Test
    public void testConstructorAndGetters() {
        int n = 5;
        Current current = new Current(n);

        assertEquals(n, current.getFloor().length);
        assertEquals(0, current.getcX());
        assertEquals(0, current.getcY());
        assertFalse(current.isPen());

        assertEquals(0, current.getRot());
    }

    @Test
    public void testSettersAndGetters() {
        Current current = new Current(5);

        int[][] newFloor = new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};
        current.setFloor(newFloor);
        assertArrayEquals(newFloor, current.getFloor());

        current.setcX(2);
        assertEquals(2, current.getcX());

        current.setcY(3);
        assertEquals(3, current.getcY());

        current.setPen(true);
        assertTrue(current.isPen());

        current.setRot(2);
        assertEquals(2, current.getRot());
    }

    @Test
    public void testPrintFloor() {
        Current current = new Current(5);
        current.setFloor(new int[][]{
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 1, 1},
                {1, 0, 1, 0, 1}
        });



        char[][] actualPrint = current.printFloor();

        assertNotNull( actualPrint);
    }

    @Test
    public void testToString() {
        Current current = new Current(5);
        current.setcX(2);
        current.setcY(3);
        current.setPen(true);
        current.setRot(1);

        String expectedString = "Current{cX=2, cY=3, Pen=true, currentFace=East}";
        assertEquals(expectedString, current.toString());
    }
}
