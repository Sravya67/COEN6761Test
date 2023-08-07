package com.example.testing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.testing.fun.Control;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

@SpringBootTest
class TestingApplicationTests {

    @Test
    void pentest() {
        Control c = new Control();
        c.setBoard(3); // Set board size to 3x3 for testing
        c.current.setPen(true);
        Assertions.assertTrue(c.current.isPen());
        c.current.setPen(false);
        Assertions.assertFalse(c.current.isPen());
    }

    @Test
    void floorTest() {
        Control c = new Control();
        int n = 5; // Random board size for testing
        c.setBoard(n);
        int[][] floor = c.current.getFloor();
        Assertions.assertEquals(n, floor.length);
    }

    @Test
    void controlTest() {
        Control c = new Control();
        c.setBoard(4); // Set board size to 4x4 for testing
        String[] directions = {"North", "East", "South", "West"};

        for (int r = 0; r < 4; r++) {
            String currentDirection = directions[c.current.getRot()];
            c.commandCenter("R");
            String newDirection = directions[c.current.getRot()];
            Assertions.assertEquals(newDirection, next_dir(currentDirection, "R"));
        }

        for (int r = 0; r < 4; r++) {
            String currentDirection = directions[c.current.getRot()];
            c.commandCenter("L");
            String newDirection = directions[c.current.getRot()];
            Assertions.assertEquals(newDirection, next_dir(currentDirection, "L"));
        }
    }

    @Test
    void testSystemOut() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        System.out.println("Hello, World!");

        String printedOutput = outputStream.toString().trim();
        System.setOut(System.out);

        Assertions.assertEquals("Hello, World!", printedOutput);
    }

    @Test
    void moveOutOfBoundsTest() {
        Control c = new Control();
        c.commandCenter("I 5"); // Set board size to 5x5 for testing

        // North
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> c.commandCenter("M 6"));
        
        // East
        c.commandCenter("R");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> c.commandCenter("M 6"));

        // South
        c.commandCenter("R");
        c.commandCenter("R");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> c.commandCenter("M 6"));

        // West
        c.commandCenter("R");
        c.commandCenter("R");
        c.commandCenter("R");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> c.commandCenter("M 6"));
    }

    @Test
    void moveFunctionTest() {
        Control c = new Control();
        c.commandCenter("I 6");

        // PenDownOneRotation
        c.commandCenter("D");
        c.commandCenter("M 5");
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(1, c.current.getFloor()[0][i]);
        }

        c.commandCenter("R");
        c.commandCenter("M 5");
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(1, c.current.getFloor()[i][5]);
        }

        c.commandCenter("R");
        c.commandCenter("M 5");
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(1, c.current.getFloor()[5][5 - i]);
        }

        c.commandCenter("R");
        c.commandCenter("M 5");
        for (int i = 0; i < 6; i++) {
            Assertions.assertEquals(1, c.current.getFloor()[5 - i][0]);
        }

        // PenUpOneRotation
        c.commandCenter("U");
        c.commandCenter("I 6");
        c.commandCenter("M 5");
        Assertions.assertEquals(0, c.current.getcX());
        Assertions.assertEquals(5, c.current.getcY());

        c.commandCenter("R");
        c.commandCenter("M 5");
        Assertions.assertEquals(5, c.current.getcX());
        Assertions.assertEquals(5, c.current.getcY());

        c.commandCenter("R");
        c.commandCenter("M 5");
        Assertions.assertEquals(5, c.current.getcX());
        Assertions.assertEquals(0, c.current.getcY());

        c.commandCenter("R");
        c.commandCenter("M 5");
        Assertions.assertEquals(0, c.current.getcX());
        Assertions.assertEquals(0, c.current.getcY());

        // PenUpandDownTestDuringMovement
        c.commandCenter("I 6");
        c.commandCenter("D");
        c.commandCenter("M 2");
        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(1, c.current.getFloor()[0][i]);
        }
        c.commandCenter("U");
        c.commandCenter("M 4");
        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(0, c.current.getFloor()[0][3 + i]);
        }
    }

    @Test
    void invalidInputTest() {
        Control c = new Control();
        c.commandCenter("I 5");
        c.commandCenter("M 3");
        c.commandCenter("L");
        c.commandCenter("R");
        c.commandCenter("U");
        c.commandCenter("P");
        c.commandCenter("C");

        // Test invalid command "X"
        Assertions.assertThrows(IllegalArgumentException.class, () -> c.commandCenter("X"));

        // Test invalid command format "I"
        Assertions.assertThrows(IllegalArgumentException.class, () -> c.commandCenter("I"));

        // Test invalid command format "I10"
        Assertions.assertThrows(IllegalArgumentException.class, () -> c.commandCenter("I10"));

        // Test invalid command format "D1"
        Assertions.assertThrows(IllegalArgumentException.class, () -> c.commandCenter("D1"));

        // Test invalid command format "D "
        Assertions.assertThrows(IllegalArgumentException.class, () -> c.commandCenter("D "));

        // Test invalid command format "M"
        Assertions.assertThrows(IllegalArgumentException.class, () -> c.commandCenter("M"));

        // Test invalid command format "M3"
        Assertions.assertThrows(IllegalArgumentException.class, () -> c.commandCenter("M3"));
    }

    @Test
    void printFunctionTest() {
        Control c = new Control();

        // Initialize the system, the array elements will be set to '0'
        c.commandCenter("I 5");
        char[][] expectedEmptyFloor = new char[][] {
            {'0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        Assertions.assertArrayEquals(expectedEmptyFloor, c.current.printFloor());

        // Move the pen with different movements
        c.commandCenter("M 3");
        c.commandCenter("D");
        c.commandCenter("R");
        c.commandCenter("M 2");
        char[][] expectedFloor = new char[][] {
            {'*', '*', '*', '0', '0'},
            {'0', '0', '*', '0', '0'},
            {'0', '0', '*', '0', '0'},
            {'0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        Assertions.assertArrayEquals(expectedFloor, c.current.printFloor());
    }

    String next_dir(String Current, String input){  //R2
		if (Current.equals("North") && (input.equals("R") || input.equals("r")))
			return "East";
		else if (Current.equals("North") && (input.equals("L") || input.equals("l")))
			return "West";
		else if (Current.equals("South") && (input.equals("R") || input.equals("r")))
			return "West";
        else if (Current.equals("South") && (input.equals("L") || input.equals("l")))
			return "East";
		else if (Current.equals("East") && (input.equals("R") || input.equals("r")))
			return "South";
        else if (Current.equals("East") && (input.equals("L") || input.equals("l")))
			return "North";
		else if (Current.equals("West") && (input.equals("R") || input.equals("r")))
			return "North";
        else if (Current.equals("West") && (input.equals("L") || input.equals("l")))
			return "South";
		else
			return null;
	}
}
