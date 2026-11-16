package com.bridgelabz.oceanfleet.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    @Test
    void testConsoleInteraction() {
        String input = String.join("\n",
                "3",
                "V001:Sea King:25.5:Cargo",
                "V002:Ocean Star:18.0:Tanker",
                "V003:Blue Wave:30.0:Passenger",
                "V001"
        ) + "\n";

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)
            ));
            System.setOut(new PrintStream(output));

            UserInterface userInterface = new UserInterface();
            userInterface.start();

            String result = output.toString();

            assertTrue(result.contains("V001 | Sea King | Cargo | 25.5 knots"));
            assertTrue(result.contains("V003 | Blue Wave | Passenger | 30.0 knots"));
            assertTrue(result.contains("High Performance Vessels"));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    void testVesselNotFound() {
        String input = String.join("\n",
                "1",
                "V001:Sea King:25.5:Cargo",
                "V999"
        ) + "\n";

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)
            ));
            System.setOut(new PrintStream(output));

            UserInterface userInterface = new UserInterface();
            userInterface.start();

            assertTrue(output.toString().contains("Vessel not found."));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}