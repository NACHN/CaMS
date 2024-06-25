package com.cams;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
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

    public boolean[] selectedHeavy={true,true,true,true};
    public boolean[] selectedMedium={true,true,true,true};
    public boolean[] selectedLight={true,true,true};

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
                NewAircraft();
                if (mission.isDone())
                    timer.stop();
            }
        });
        if (!mission.isDone())
            timer.start();
    }

    public void NewAircraft(){
        //New aircraft
        if (Math.random() > 0.5){
            mission.newRandomAircraft();
        }
            
        simulationTime++;
        //System.out.println("Time:" + simulationTime);
        Util.status.mission.setText("Running at: " + simulationTime + " s. Simulated flights: "+ mission.getCurrentFlight());
        Util.status.repaint();

        //Simulate aircraft
        for (Aircraft aircraft : Aircrafts) {
            if(aircraft.getstatus()==3)continue;
            Waypoint from=aircraft.getfrom();
            Waypoint to=aircraft.getto();
            int status=aircraft.getstatus();
            int dir=1;
            if(status>10)status/=10;
            switch (status) {
                case 0:
                    aircraft.navigate(from, to);
                    break;
                case 1:
                    if(aircraft.getHeading()==Runways[to.getbelong()].getAngle())dir=2;
                    aircraft.landing(Runways[to.getbelong()],dir);
                    break;
                case 2:
                    dir=2;
                    if(aircraft.getHeading()==Runways[to.getbelong()].getAngle())dir=1;
                    if(from==null)
                    aircraft.takeoff(Runways[to.getbelong()],dir);
                    else  aircraft.takeoff(Runways[from.getbelong()],dir);
                    break;

                default:
                    break;
            }
        }
    }
}