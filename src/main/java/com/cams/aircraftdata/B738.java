package com.cams.aircraftdata;

public class B738 extends AircraftData {
    public B738() {
        name = "B738";
        V2 = 145; // in knots
        takeOffDistance = 2300; // in meters
        WTC = "M";
        RECAT_EU = "Upper Medium";
        MTOW_kg = 70530; // in kg
        initialClimbIAS = 165; // in knots
        initialClimbROC = 3000; // in ft/min
        climbToFL150IAS = 290; // in knots
        climbToFL150ROC = 2000; // in ft/min
        climbToFL240IAS = 290; // in knots
        climbToFL240ROC = 2000; // in ft/min
        machClimbMach = 0.78;
        machClimbROC = 1500; // in ft/min
        TAS = 460; // in knots
        cruiseMach = 0.79;
        ceiling = 410; // FL
        range = 2000; // in NM
        initialDescentMach = 0.78;
        initialDescentROD = 800; // in ft/min
        descentIAS = 280; // in knots
        descentROD = 3500; // in ft/min
        approachIAS = 250; // in knots
        approachROD = 1500; // in ft/min
        MCS = 210; // in knots
        landingVat = 147; // in knots
        landingDistance = 1600; // in meters
        APC = 'D';
    }
}
