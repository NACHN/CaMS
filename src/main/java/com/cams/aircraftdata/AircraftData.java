package com.cams.aircraftdata;

import com.cams.Util;

public abstract class AircraftData {
    public String name;
    public int V2;
    public int takeOffDistance;
    public String WTC;
    public String RECAT_EU;
    public int MTOW_kg;
    public int initialClimbIAS;
    public int initialClimbROC;
    public int climbToFL150IAS;
    public int climbToFL150ROC;
    public int climbToFL240IAS;
    public int climbToFL240ROC;
    public double machClimbMach;
    public int machClimbROC;
    public int TAS;
    public double cruiseMach;
    public int ceiling;
    public int range;
    public double initialDescentMach;
    public int initialDescentROD;
    public int descentIAS;
    public int descentROD;
    public int approachIAS;
    public int approachROD;
    public int MCS;
    public int landingVat;
    public int landingDistance;
    public char APC;

    public double Vlof(){
        double V=Util.knotsToMetersPerSecond(V2);
        double FullENG=(takeOffDistance/1.15);
        double acc=(double)(V*V)/2/(double)takeOffDistance;
        return Math.sqrt(2*acc*FullENG);
    }

    public double FullAcc(){
        double V=Util.knotsToMetersPerSecond(V2);
        double acc=(double)(V*V)/2/(double)takeOffDistance;
        return acc;
    }
}
