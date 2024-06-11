package com.cams.aircraftdata;

public class B38M extends AircraftData {
    public B38M() {
        name = "B38M";
        V2 = 145; // in knots
        takeOffDistance = 2500; // in meters
        WTC = "M";
        RECAT_EU = "Upper Medium";
        MTOW_kg = 82600; // in kg
        initialClimbIAS = 165; // in knots
        initialClimbROC = 2500; // in ft/min
        climbToFL150IAS = 290; // in knots
        climbToFL150ROC = 2300; // in ft/min
        climbToFL240IAS = 290; // in knots
        climbToFL240ROC = 2000; // in ft/min
        machClimbMach = 0.78;
        machClimbROC = 1500; // in ft/min
        TAS = 453; // in knots
        cruiseMach = 0.79;
        ceiling = 410; // FL
        range = 3550; // in NM
        initialDescentMach = 0.78;
        initialDescentROD = 1000; // in ft/min
        descentIAS = 290; // in knots
        descentROD = 3500; // in ft/min
        approachIAS = 250; // in knots
        approachROD = 1500; // in ft/min
        MCS = 215; // in knots
        landingVat = 145; // in knots
        landingDistance = 1500; // in meters
        APC = 'D';
    }
}
