package com.bridgelabz.oceanfleet.util;

import com.bridgelabz.oceanfleet.model.Vessel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VesselUtilTest {

    @Test
    void testAddVesselPerformance() {
        VesselUtil vesselUtil = new VesselUtil();

        Vessel vessel1 = new Vessel("V001", "Sea King", 25.5, "Cargo");
        Vessel vessel2 = new Vessel("V002", "Ocean Star", 18.0, "Tanker");

        vesselUtil.addVesselPerformance(vessel1);
        vesselUtil.addVesselPerformance(vessel2);

        assertEquals(2, vesselUtil.getVesselList().size());
        assertEquals("V001", vesselUtil.getVesselList().get(0).getVesselId());
        assertEquals("V002", vesselUtil.getVesselList().get(1).getVesselId());
    }

    @Test
    void testEmptyVesselList() {
        VesselUtil vesselUtil = new VesselUtil();

        assertNotNull(vesselUtil.getVesselList());
        assertTrue(vesselUtil.getVesselList().isEmpty());
    }

    @Test
    void testGetVesselById() {
        VesselUtil vesselUtil = new VesselUtil();

        Vessel vessel = new Vessel("V001", "Sea King", 25.5, "Cargo");
        vesselUtil.addVesselPerformance(vessel);

        assertEquals(vessel, vesselUtil.getVesselById("V001"));
    }

    @Test
    void testGetVesselByIdNotFound() {
        VesselUtil vesselUtil = new VesselUtil();

        vesselUtil.addVesselPerformance(
                new Vessel("V001", "Sea King", 25.5, "Cargo")
        );

        assertNull(vesselUtil.getVesselById("V999"));
    }

    @Test
    void testGetVesselByIdIsCaseSensitive() {
        VesselUtil vesselUtil = new VesselUtil();

        vesselUtil.addVesselPerformance(
                new Vessel("V001", "Sea King", 25.5, "Cargo")
        );

        assertNull(vesselUtil.getVesselById("v001"));
    }

    @Test
    void testGetHighPerformanceVessel() {
        VesselUtil vesselUtil = new VesselUtil();

        vesselUtil.addVesselPerformance(
                new Vessel("V001", "Sea King", 25.5, "Cargo")
        );
        vesselUtil.addVesselPerformance(
                new Vessel("V002", "Ocean Star", 18.0, "Tanker")
        );
        vesselUtil.addVesselPerformance(
                new Vessel("V003", "Blue Wave", 30.0, "Passenger")
        );

        List<Vessel> result = vesselUtil.getHighPerformanceVessels();

        assertEquals(1, result.size());
        assertEquals("V003", result.get(0).getVesselId());
    }

    @Test
    void testGetHighPerformanceVesselsWithTie() {
        VesselUtil vesselUtil = new VesselUtil();

        vesselUtil.addVesselPerformance(
                new Vessel("V001", "Sea King", 30.0, "Cargo")
        );
        vesselUtil.addVesselPerformance(
                new Vessel("V002", "Ocean Star", 18.0, "Tanker")
        );
        vesselUtil.addVesselPerformance(
                new Vessel("V003", "Blue Wave", 30.0, "Passenger")
        );

        List<Vessel> result = vesselUtil.getHighPerformanceVessels();

        assertEquals(2, result.size());
        assertEquals("V001", result.get(0).getVesselId());
        assertEquals("V003", result.get(1).getVesselId());
    }

    @Test
    void testGetHighPerformanceVesselsWhenEmpty() {
        VesselUtil vesselUtil = new VesselUtil();

        assertTrue(vesselUtil.getHighPerformanceVessels().isEmpty());
    }
}