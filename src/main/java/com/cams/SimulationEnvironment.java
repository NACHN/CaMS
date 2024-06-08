package com.cams;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class SimulationEnvironment{
    public Waypoint[] Waypoints=new Waypoint[1000];
    public Runway[] Runways=new Runway[1000];
    public Route[] Routes=new Route[1000];
    public int WaypointCount=0;
    public int RunwayCount=0;
    public int RouteCount=0;
    public boolean[] Inbound=new boolean[1000];
    public boolean[] Outbound=new boolean[1000];
    public int ins=0;
    public int outs=0;

    public int Times=5000;
    public ArrayList<Integer> Arrival = new ArrayList<>();
    public ArrayList<Integer> Departure = new ArrayList<>(); 
    /**
     * Constructor for new simulation environment
     */
    public SimulationEnvironment(){
        Util.Current=this;
        Util.settings.newSimulation();
    }
    public int getWaypoint(Point p){
        if(Util.Current.WaypointCount!=0)
        for(int i=0;i<this.WaypointCount;i++){
            System.out.println(i+":"+Waypoints[i]);
            if(Waypoints[i]!=null){
                System.out.println(i+" at "+p+" and "+Waypoints[i].getPosition());
                if(p.distance(Waypoints[i].getPosition())<5*Util.scalar/100){
                    return i;
                }
            }
        }
        return -1;
    }
}