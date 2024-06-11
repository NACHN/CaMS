package com.cams;

import java.awt.*;

public class Waypoint{
    private double Coord_N;
    private double Coord_E;
    private String name;
    /**
     * 0 for Default
     * 1 for Runway FAF
     * 2 for Runway IF
     */
    private int type=0;
    private int belongsto;
    public double Direction=0;
    public double height=2400;
    
    public Waypoint(Point Coord){
        Coord_N=Coord.getY();
        Coord_E=Coord.getX();
        name="Waypoint"+Integer.toString(Util.Current.WaypointCount);
        Util.Tree.newWaypoint(this);
        Util.Current.WaypointCount++;
    }
    public Waypoint(Point Coord,int t){
        Coord_N=Coord.getY();
        Coord_E=Coord.getX();
        if(t==1)name="RunwayFAF"+Integer.toString(Util.Current.WaypointCount);
        else if(t==2)name="RunwayIF"+Integer.toString(Util.Current.WaypointCount);
        Util.Tree.newWaypoint(this);
        type=t;
        Util.Current.WaypointCount++;
    }
    public Waypoint(Point Coord,int t,int belong){
        Coord_N=Coord.getY();
        Coord_E=Coord.getX();
        if(t==1)name="RunwayFAF"+Integer.toString(Util.Current.WaypointCount);
        else if(t==2)name="RunwayIF"+Integer.toString(Util.Current.WaypointCount);
        Util.Tree.newWaypoint(this);
        type=t;
        belongsto=belong;
        Util.Current.WaypointCount++;
    }
    public Point getPosition(){
        Point p=new Point();
        p.x=(int)Coord_E;
        p.y=(int)Coord_N;
        return p;
    }
    public String getName(){
        return name;
    }
    public void setname(String n){
        name=n;
    }

    public int getbelong(){
        return belongsto;
    }
    public double getX(){
        return Coord_E;
    }
    
    public double getY(){
        return Coord_N;
    }
    public int getType(){
        return type;
    }
    public void setPosition(double x, double y){
        Coord_N=y;
        Coord_E=x;
    }

    @Override
    public String toString() {
        return name;
    }
}