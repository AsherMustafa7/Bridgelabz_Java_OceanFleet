package com.bridgelabz.oceanfleet.ui;

import com.bridgelabz.oceanfleet.model.Vessel;
import com.bridgelabz.oceanfleet.util.VesselUtil;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final VesselUtil vesselUtil;
    private final Scanner scanner;

    public UserInterface() {
        this.vesselUtil = new VesselUtil();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== OceanFleet Vessel Performance System ===");

        System.out.print("Enter number of vessels: ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.println("\nEnter vessel details (ID:Name:Speed:Type):");
            String input = scanner.nextLine();

            String[] details = input.split(":", -1);

            if (details.length != 4) {
                System.out.println("Invalid input format. Please enter all four fields.");
                i--;
                continue;
            }

            String vesselId = details[0].trim();
            String vesselName = details[1].trim();
            double averageSpeed = Double.parseDouble(details[2].trim());
            String vesselType = details[3].trim();

            Vessel vessel = new Vessel(
                    vesselId, vesselName, averageSpeed, vesselType
            );

            vesselUtil.addVesselPerformance(vessel);
        }

        System.out.print("\nEnter Vessel ID to search: ");
        String searchId = scanner.nextLine();

        Vessel foundVessel = vesselUtil.getVesselById(searchId);

        if (foundVessel != null) {
            System.out.println("\nVessel Found:");
            displayVessel(foundVessel);
        } else {
            System.out.println("\nVessel not found.");
        }

        System.out.println("\n=== High Performance Vessels ===");
        List<Vessel> highPerformanceVessels =
                vesselUtil.getHighPerformanceVessels();

        for (Vessel vessel : highPerformanceVessels) {
            displayVessel(vessel);
        }
    }

    private void displayVessel(Vessel vessel) {
        System.out.println(
                vessel.getVesselId() + " | "
                        + vessel.getVesselName() + " | "
                        + vessel.getVesselType() + " | "
                        + vessel.getAverageSpeed() + " knots"
        );
    }
}