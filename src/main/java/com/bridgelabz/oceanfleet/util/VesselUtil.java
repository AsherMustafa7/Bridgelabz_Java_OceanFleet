package com.bridgelabz.oceanfleet.util;

import com.bridgelabz.oceanfleet.model.Vessel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VesselUtil {

    private final List<Vessel> vesselList;

    public VesselUtil() {
        this.vesselList = new ArrayList<>();
    }

    public void addVesselPerformance(Vessel vessel) {
        if (vessel != null) {
            vesselList.add(vessel);
        }
    }

    public Vessel getVesselById(String vesselId) {
        for (Vessel vessel : vesselList) {
            if (vesselId != null && vesselId.equals(vessel.getVesselId())) {
                return vessel;
            }
        }
        return null;
    }

    public List<Vessel> getHighPerformanceVessels() {
        List<Vessel> highPerformanceVessels = new ArrayList<>();

        if (vesselList.isEmpty()) {
            return highPerformanceVessels;
        }

        double highestSpeed = Double.NEGATIVE_INFINITY;

        for (Vessel vessel : vesselList) {
            if (vessel.getAverageSpeed() > highestSpeed) {
                highestSpeed = vessel.getAverageSpeed();
                highPerformanceVessels.clear();
                highPerformanceVessels.add(vessel);
            } else if (vessel.getAverageSpeed() == highestSpeed) {
                highPerformanceVessels.add(vessel);
            }
        }

        return highPerformanceVessels;
    }

    public List<Vessel> getVesselList() {
        return Collections.unmodifiableList(vesselList);
    }
}