package com.example.testing;
import com.example.testing.fun.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseSizeTest {
    private Control control;

    @BeforeEach
    public void setup() {

        control = new Control();
    }



    @Test
    public void testParseSize() {
        int result = control.parseSize("");
        assertEquals(-1, result);

        result = control.parseSize("A");
        assertEquals(-1, result);

        result = control.parseSize("AB");
        assertEquals(-1, result);

        result = control.parseSize("A ");
        assertEquals(-1, result);

        result = control.parseSize("ABCD");
        assertEquals(-1, result);

        result = control.parseSize(" A123");
        assertEquals(-1, result);

        result = control.parseSize(" A1A3");
        assertEquals(-1, result);

        result = control.parseSize(" 123");
        assertEquals(-1, result);
    }



}
