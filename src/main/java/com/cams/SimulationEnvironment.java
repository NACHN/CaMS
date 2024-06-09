package com.cams;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.xml.bind.annotation.*;
import com.cams.logic.*;

public class SimulationEnvironment {
    public Timer timer;

    public Waypoint[] Waypoints = new Waypoint[1000];
    public Runway[] Runways = new Runway[1000];
    public Route[] Routes = new Route[1000];
    public int WaypointCount = 0;
    public int RunwayCount = 0;
    public int RouteCount = 0;
    public double[] Inbound = new double[1000];
    public double[] Outbound = new double[1000];
    public int ins = 0;
    public int outs = 0;
    public double inq = 0;
    public double outq = 0;
    public int[] InboundIdx;
    public int[] OutboundIdx;

    public Mission mission;
    public int simulationTime;

    public ArrayList<Aircraft> Aircrafts = new ArrayList<>();

    public int Times = 5000;
    public ArrayList<Integer> Arrival = new ArrayList<>();
    public ArrayList<Integer> Departure = new ArrayList<>();

    /**
     * Constructor for new simulation environment
     */
    public SimulationEnvironment() {
        Util.Current = this;
        Util.settings.newSimulation();
    }

    public int getWaypoint(Point p) {
        if (Util.Current.WaypointCount != 0)
            for (int i = 0; i < this.WaypointCount; i++) {
                // System.out.println(i + ":" + Waypoints[i]);
                if (Waypoints[i] != null) {
                    // System.out.println(i + " at " + p + " and " + Waypoints[i].getPosition());
                    if (p.distance(Waypoints[i].getPosition()) < 5 * Util.scalar / 100) {
                        return i;
                    }
                }
            }
        return -1;
    }

    public void simulationRun() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Math.random() > 0.5)
                    mission.newRandomAircraft();
                simulationTime++;
                System.out.println("Time:" + simulationTime);
                Util.status.mission.setText("Running at: " + simulationTime + " s.");
                Util.status.repaint();
                for (Aircraft aircraft : Aircrafts) {
                    Waypoint from=aircraft.getfrom();
                    Waypoint to=aircraft.getto();
                    switch (aircraft.getstatus()) {
                        case 0:
                            aircraft.navigate(from, to);
                            break;
                        case 1:
                            aircraft.landing(Runways[to.getbelong()]);
                            break;
                        case 2:
                            aircraft.takeoff(Runways[from.getbelong()]);
                            break;

                        default:
                            break;
                    }
                }
                if (mission.isDone())
                    timer.stop();
            }
        });
        if (!mission.isDone())
            timer.start();
    }
}