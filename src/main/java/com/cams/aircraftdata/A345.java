package com.cams.aircraftdata;

public class A345 extends AircraftData {
    public A345() {
        this.name = "A345";
        this.V2 = 145;
        this.takeOffDistance = 3200;
        this.WTC = "H";
        this.RECAT_EU = "Upper Heavy";
        this.MTOW_kg = 368000;
        this.initialClimbIAS = 175;
        this.initialClimbROC = 1500;
        this.climbToFL150IAS = 290;
        this.climbToFL150ROC = 1200;
        this.climbToFL240IAS = 290;
        this.climbToFL240ROC = 1000;
        this.machClimbMach = 0.81;
        this.machClimbROC = 600;
        this.TAS = 480;
        this.cruiseMach = 0.82;
        this.ceiling = 410;
        this.range = 8800;
        this.initialDescentMach = 0.81;
        this.initialDescentROD = 1000;
        this.descentIAS = 290;
        this.descentROD = 2000;
        this.approachIAS = 250;
        this.approachROD = 1500;
        this.MCS = 210;
        this.landingVat = 155;
        this.landingDistance = 1920;
        this.APC = 'D';
    }
}
