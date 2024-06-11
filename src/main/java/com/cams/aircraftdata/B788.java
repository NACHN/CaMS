package com.cams.aircraftdata;

public class B788 extends AircraftData {
    public B788() {
        name = "B788";
        // Take-off
        V2 = 165; // in knots
        takeOffDistance = 2820; // in meters
        WTC = "H";
        RECAT_EU = "Upper Heavy";
        MTOW_kg = 228000; // in kg

        // Initial climb (to 5000ft)
        initialClimbIAS = 190; // in knots
        initialClimbROC = 2700; // in ft/min

        // Climb (to FL 150)
        climbToFL150IAS = 290; // in knots
        climbToFL150ROC = 2000; // in ft/min

        // Climb (to FL 240)
        climbToFL240IAS = 290; // in knots
        climbToFL240ROC = 1500; // in ft/min

        // MACH climb
        machClimbMach = 0.79;
        machClimbROC = 1500; // in ft/min

        // Cruise
        TAS = 470; // in knots
        cruiseMach = 0.85;
        ceiling = 430; // FL
        range = 8000; // in NM

        // Initial Descent (to FL 240)
        initialDescentMach = 0.84;
        initialDescentROD = 2600; // in ft/min

        // Descent (to FL 100)
        descentIAS = 300; // in knots
        descentROD = 2800; // in ft/min

        // Approach
        approachIAS = 240; // in knots
        approachROD = 1500; // in ft/min
        MCS = 220; // in knots

        // Landing
        landingVat = 140; // in knots
        landingDistance = 1520; // in meters
        APC = 'C';
    }
}
