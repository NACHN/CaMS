package com.cams.logic;

import java.awt.*;
import com.cams.*;

/**
 * Aircraft class defines aircraft properties, including positions, velocity,
 * type, payload, etc, and aircraft maneuvers, including move, land, takeoff,
 * navigate, etc.
 */
public class Aircraft {
    private String name;
    private double Coord_N;
    private double Coord_E;
    private double Coord_D = 1700;
    private double Weight;
    private double Heading;
    private int onroute=0;
    private double Speed = 100;
    private double VerticalSpeed;
    private boolean isApproach;
    /**
     * 0 for cruise
     * 1 for landing
     * 2 for takeoff
     */
    private int status = 0;
    SimulationEnvironment Env;
    Route route;
    Waypoint from;
    Waypoint to;
    Runway rw;

    public Aircraft(SimulationEnvironment E, Route r, boolean A) {
        route = r;
        isApproach = A;
        Env = E;
        Env.Aircrafts.add(this);
        name = "Flight " + Env.mission.getCurrentFlight();
        Waypoint Ini = Env.Waypoints[route.getWaypoints()[0]];
        Coord_N = Ini.getY();
        Coord_E = Ini.getX();
        Speed = 100;
        if (isApproach) {
            navigate(Ini, Env.Waypoints[route.getWaypoints()[1]]);
            status = 0;
        }

        else {
            navigate(Ini, Env.Waypoints[route.getWaypoints()[1]]);
            status = 2;
        }

    }

    public void navigate(Waypoint From, Waypoint To) {
        from = From;
        to = To;
        double N1X = Coord_E;
        double N2X = To.getX();

        double N1Y = Coord_N;
        double N2Y = To.getY();

        if (To.getType() == 1) {
            Heading = Env.Runways[To.getbelong()].getAngle();
            status = 1;
        } else{
            Heading = Math.toDegrees(Math.atan2((N2X - N1X),(N1Y - N2Y)));
        }
            
        double dx = Coord_E - To.getX();
        double dy = Coord_N - To.getY();
        if (dx * dx + dy * dy < 5000) {
            onroute++;
            Waypoint Next = Env.Waypoints[route.getWaypoints()[onroute]];
            from = to;
            to = Next;
            move();
        }
        else move();
    }

    public void landing(Runway toLand) {
        rw = toLand;

    }

    public void takeoff(Runway forTakeoff) {
        rw = forTakeoff;

    }

    void move() {
        double dx = Math.sin(Math.toRadians(Heading)) * Speed;
        double dy = Math.cos(Math.toRadians(Heading)) * Speed;
        Coord_E += dx;
        Coord_N -= dy;
        Util.Map.repaint();
        //System.out.println(name + " Heading " + Heading + " at " + Coord_E + ", " + Coord_N);
    }

    public double getX() {
        return Coord_E;
    }

    public double getY() {
        return Coord_N;
    }

    public String getname() {
        return name;
    }

    public int getstatus() {
        return status;
    }

    public Waypoint getfrom() {
        return from;
    }

    public Waypoint getto() {
        return to;
    }

    double[] getCoord() {
        double[] Coord = { this.Coord_N, this.Coord_E, this.Coord_D };
        return Coord;
    }

    double getSpeed() {
        return Speed;
    }

    double getVerticalSpeed() {
        return VerticalSpeed;
    }

    public double getHeading() {
        return Heading;
    }
}