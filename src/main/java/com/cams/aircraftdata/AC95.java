package com.cams.aircraftdata;

public class AC95 extends AircraftData {
    public AC95() {
        name = "AC95";
        V2 = 100; // in knots
        takeOffDistance = 500; // in meters
        WTC = "L";
        RECAT_EU = "Light";
        MTOW_kg = 5080; // in kg
        initialClimbIAS = 130; // in knots
        initialClimbROC = 2000; // in ft/min
        climbToFL150IAS = 210; // in knots
        climbToFL150ROC = 1000; // in ft/min
        climbToFL240IAS = 210; // in knots
        climbToFL240ROC = 1000; // in ft/min
        machClimbMach = 0;
        machClimbROC = 0; // in ft/min
        TAS = 270; // in knots
        cruiseMach = 0.0;
        ceiling = 350; // FL
        range = 800; // in NM
        initialDescentMach = 0.0;
        initialDescentROD = 0; // in ft/min
        descentIAS = 250; // in knots
        descentROD = 1500; // in ft/min
        approachIAS = 250; // in knots
        approachROD = 1500; // in ft/min
        MCS = 0; // in knots
        landingVat = 100; // in knots
        landingDistance = 500; // in meters
        APC = 'B';
    }
}
