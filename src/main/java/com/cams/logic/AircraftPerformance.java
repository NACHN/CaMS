package com.cams.logic;

import com.cams.aircraftdata.*;
import com.cams.*;

public class AircraftPerformance {
    static AircraftData data;

    public static AircraftData GetPerformance(Aircraft aircraft) {
        switch (aircraft.gettype()) {
            case "A20N":
                data = new A20N();
                break;
            case "A320":
                data = new A320();
                break;
            case "A345":
                data = new A345();
                break;
            case "A388":
                data = new A388();
                break;
            case "AC11":
                data = new AC11();
                break;
            case "AC95":
                data = new AC95();
                break;
            case "B38M":
                data = new B38M();
                break;
            case "B738":
                data = new B738();
                break;
            case "B744":
                data = new B744();
                break;
            case "B788":
                data = new B788();
                break;
            case "PA32":
                data = new PA32();
                break;
            default:
                break;
        }

       return data;

    }
}