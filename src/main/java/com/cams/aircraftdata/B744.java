package com.cams.aircraftdata;

public class B744 extends AircraftData {
    public B744() {
        name = "B744";
        V2 = 185; // in knots
        takeOffDistance = 3300; // in meters
        WTC = "H";
        RECAT_EU = "Upper Heavy";
        MTOW_kg = 396890; // in kg
        initialClimbIAS = 215; // in knots
        initialClimbROC = 1500; // in ft/min
        climbToFL150IAS = 300; // in knots
        climbToFL150ROC = 1500; // in ft/min
        climbToFL240IAS = 300; // in knots
        climbToFL240ROC = 1500; // in ft/min
        machClimbMach = 0.75;
        machClimbROC = 1500; // in ft/min
        TAS = 510; // in knots
        cruiseMach = 0.85;
        ceiling = 450; // FL
        range = 7260; // in NM
        initialDescentMach = 0.85;
        initialDescentROD = 1000; // in ft/min
        descentIAS = 300; // in knots
        descentROD = 3000; // in ft/min
        approachIAS = 250; // in knots
        approachROD = 1500; // in ft/min
        MCS = 250; // in knots
        landingVat = 152; // in knots
        landingDistance = 2130; // in meters
        APC = 'D';
    }
}
