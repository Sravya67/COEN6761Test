//package com.example.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.example.testing.fun.*;

import java.io.PrintStream;

public class RightTest {
    private Control control;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStream));
        control = new Control();
    }

    @Test
    public void testCommandCenter_Right() {
        control.setBoard(5);
        control.current.setRot(0); // South
        control.commandCenter("R");
        assertEquals(1, control.current.getRot());
        control.current.setRot(1);
        control.commandCenter("R");
        assertEquals(2, control.current.getRot());
        control.current.setRot(2);
        control.commandCenter("R");
        assertEquals(3, control.current.getRot());
        control.current.setRot(3);
        control.commandCenter("R");
        assertEquals(0, control.current.getRot());
    }
}
