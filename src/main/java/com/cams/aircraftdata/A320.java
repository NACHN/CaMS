package com.cams.aircraftdata;

public class A320 extends AircraftData {
    public A320() {
        // Initialize the properties
        name = "A320";
        // Take-off
        V2 = 145; // in knots
        takeOffDistance = 2190; // in meters
        WTC = "M";
        RECAT_EU = "Upper Medium";
        MTOW_kg = 73900; // in kg

        // Initial climb (to 5000ft)
        initialClimbIAS = 175; // in knots
        initialClimbROC = 2500; // in ft/min

        // Climb (to FL 150)
        climbToFL150IAS = 290; // in knots
        climbToFL150ROC = 2000; // in ft/min

        // Climb (to FL 240)
        climbToFL240IAS = 290; // in knots
        climbToFL240ROC = 1400; // in ft/min

        // MACH climb
        machClimbMach = 0.78;
        machClimbROC = 1000; // in ft/min

        // Cruise
        TAS = 450; // in knots
        cruiseMach = 0.79;
        ceiling = 390; // FL
        range = 2700; // in NM

        // Initial Descent (to FL 240)
        initialDescentMach = 0.78;
        initialDescentROD = 1000; // in ft/min

        // Descent (to FL 100)
        descentIAS = 290; // in knots
        descentROD = 3500; // in ft/min

        // Approach
        approachIAS = 250; // in knots
        approachROD = 1500; // in ft/min
        MCS = 210; // in knots

        // Landing
        landingVat = 137; // in knots
        landingDistance = 1440; // in meters
        APC = 'C';
    }
}
