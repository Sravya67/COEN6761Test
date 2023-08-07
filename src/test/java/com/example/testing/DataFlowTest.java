package com.example.testing;

import com.example.testing.fun.Control;
import org.junit.jupiter.api.BeforeEach;

import com.example.testing.fun.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataFlowTest {
    private Control control;

    @BeforeEach
    public void setup() {

        control = new Control();
    }
        @Test
        public void testParseSizeValidSize() {
            int result = control.parseSize("I 10");
            assertEquals(10, result);
        }
    @Test
    public  void testParseSizeLength() {
            int result = control.parseSize("I2");
        assertEquals(-1, result);
        }
    @Test
    public void testParseSizeFalseLength() {
            int result = control.parseSize("I 1");
        assertEquals(1, result);
        }
    @Test
    public void testParseSizeNoException() {
            int result = control.parseSize("I 5");
        assertEquals(5, result);
        }
    @Test
    public  void testParseSizeWithException() {
            int result = control.parseSize("I a");
        assertEquals(-1, result);
        }

}
