package com.bridgelabz.oceanfleet.util;

import com.bridgelabz.oceanfleet.model.Vessel;
import org.junit.jupiter.api.Test;

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
}