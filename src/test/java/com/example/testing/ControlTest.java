package com.example.testing;
import com.example.testing.fun.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ControlTest {

    private Control control;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStream));
        control = new Control();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @ParameterizedTest
    @CsvSource({
            "L, 3, 2, 1, 2, 2", // Left from North
            "L, 3, 2, 0, 3, 2", // Left from West
            "R, 3, 2, 3, 2, 1", // Right from West
            "R, 3, 2, 1, 2, 0"  // Right from North
    })


    @Test
    public void testCommandCenter_Up() {
        control.setBoard(5);
        control.current.setPen(true);
        control.commandCenter("U");
        assertFalse(control.current.isPen());
    }

    @Test
    public void testCommandCenter_Down() {
        control.setBoard(5);
        control.current.setPen(false);
        int[][] originalFloor = control.current.getFloor();
        control.commandCenter("D");
        assertTrue(control.current.isPen());
        assertArrayEquals(originalFloor, control.current.getFloor());
    }

    @Test
    public void testCommandCenter_Move_Valid() {
        control.setBoard(5);
        control.current.setRot(0); // Facing North
        control.current.setcX(2);
        control.current.setcY(2);

        control.commandCenter("M 2");

        assertEquals(4, control.current.getcY());
    }

    @Test
    public void testCommandCenter_Move_Invalid() {
        control.setBoard(5);
        control.current.setRot(0); // Facing North
        control.current.setcX(2);
        control.current.setcY(4);

        control.commandCenter("M 2");

        assertEquals("java.lang.IndexOutOfBoundsException: Out of bounds error\n", outputStream.toString());
    }

    @Test
    public void testCommandCenter_PrintFloor() {
        control.setBoard(5);
        control.current.setFloor(new int[][] {
                {0, 0, 1},
                {0, 1, 0},
                {1, 0, 0}
        });

        control.commandCenter("P");
        assertNotNull( outputStream.toString());
    }

    @Test
    public void testCommandCenter_PrintCurrent() {
        control.setBoard(5);
        control.current.setRot(2); // South
        control.current.setcX(2);
        control.current.setcY(3);
        control.current.setPen(true);

        control.commandCenter("C");
        assertNotNull( outputStream.toString());
    }

    @Test
    public void testCommandCenter_Right() {
        control.setBoard(5);
        control.current.setRot(2); // South
        control.current.setcX(2);
        control.current.setcY(3);
        control.current.setPen(true);

        control.commandCenter("R");
        assertNotNull( outputStream.toString());
    }
    @Test
    public void testCommandCenter_Left() {
        control.setBoard(5);
        control.current.setRot(2); // South
        control.current.setcX(2);
        control.current.setcY(3);
        control.current.setPen(true);

        control.commandCenter("L");
        assertNotNull( outputStream.toString());
    }
    @Test
    public void testCommandCenter_InvalidCommandFormat() {
        control.setBoard(5);
        control.commandCenter("INVALID");
        assertEquals("Invalid command format!\n", outputStream.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "I 3, 3",
            "I 10, 10",
            "I 1, 1"
    })
    public void testCommandCenter_Init(String command, int size) {
        control.commandCenter(command);
        assertEquals(size, control.current.getFloor().length);
    }

    @Test
    public void testCommandCenter_InvalidInit() {
        control.commandCenter("I abc");
        assertEquals("Invalid command format!\n", outputStream.toString());
    }


    @Test
    public void testParseSize_ValidCommand() {
        Control control = new Control();
        int result = control.parseSize("I 10");
        assertEquals(10, result);
    }

    @Test
    public void testParseSize_InvalidCommand_MissingSpace() {
        Control control = new Control();
        int result = control.parseSize("I10");
        assertEquals(-1, result);
    }
    @Test
    public void testParseSize_InvalidCommand_NonIntegerSize() {
        Control control = new Control();
        int result = control.parseSize("I abc");
        assertEquals(-1, result);
    }

    @Test
    public void testParseSpaces_ValidCommand() {
        Control control = new Control();
        control.setBoard(5);
        control.current.setRot(2); // South
        control.current.setcX(2);
        control.current.setcY(3);
        control.current.setPen(true);
        control.commandCenter("M 5");



    }

    @Test
    public void testParseSpaces_InvalidCommand_MissingSpace() {
        Control control = new Control();
        int result = control.parseSpaces("M5");
        assertEquals(-1, result);
    }
    @Test
    public void testParseSpaces_InvalidCommand_NonIntegerSize() {
        Control control = new Control();
        int result = control.parseSpaces("M xyz");
        assertEquals(-1, result);
    }



}
