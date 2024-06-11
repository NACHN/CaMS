package com.cams.aircraftdata;

public class PA32 extends AircraftData {
    public PA32() {
        name = "PA32";
        // Take-off
        V2 = 75; // in knots
        takeOffDistance = 500; // in meters
        WTC = "L";
        RECAT_EU = "Light";
        MTOW_kg = 1640; // in kg

        // Initial climb (to 5000ft)
        initialClimbIAS = 105; // in knots
        initialClimbROC = 1100; // in ft/min

        // Climb (to FL 150)
        climbToFL150IAS = 120; // in knots
        climbToFL150ROC = 600; // in ft/min

        // Climb (to FL 240)
        climbToFL240IAS = 120; // in knots
        climbToFL240ROC = 600; // in ft/min

        // MACH climb
        machClimbMach =0;
        machClimbROC = 0; // in ft/min

        // Cruise
        TAS = 150; // in knots
        cruiseMach = 0;
        ceiling = 160; // FL
        range = 700; // in NM

        // Initial Descent (to FL 240)
        initialDescentMach = 0;
        initialDescentROD = 0; // in ft/min

        // Descent (to FL 100)
        descentIAS = 130; // in knots
        descentROD = 800; // in ft/min

        // Approach
        approachIAS = 120; // in knots
        approachROD = 500; // in ft/min
        MCS = 90; // in knots

        // Landing
        landingVat = 80; // in knots
        landingDistance = 550; // in meters
        APC = 'A';
    }
}
