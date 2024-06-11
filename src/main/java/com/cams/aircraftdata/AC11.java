package com.cams.aircraftdata;

public class AC11 extends AircraftData {
    public AC11() {
        name = "AC11";
        V2 = 70; // in knots
        takeOffDistance = 350; // in meters
        WTC = "L";
        RECAT_EU = "Light";
        MTOW_kg = 1480; // in kg
        initialClimbIAS = 100; // in knots
        initialClimbROC = 800; // in ft/min
        climbToFL150IAS = 110; // in knots
        climbToFL150ROC = 200; // in ft/min
        climbToFL240IAS = 110; // in knots
        climbToFL240ROC = 200; // in ft/min
        machClimbMach = 0;
        machClimbROC = 0; // in ft/min
        TAS = 150; // in knots
        cruiseMach = 0;
        ceiling = 160; // FL
        range = 750; // in NM
        initialDescentMach = 0;
        initialDescentROD = 0; // in ft/min
        descentIAS = 140; // in knots
        descentROD = 500; // in ft/min
        approachIAS = 140; // in knots
        approachROD = 500; // in ft/min
        MCS = 67; // in knots
        landingVat = 70; // in knots
        landingDistance = 220; // in meters
        APC = 'A';
    }
}
