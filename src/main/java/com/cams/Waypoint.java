package com.cams;

import java.awt.*;

public class Waypoint{
    private double Coord_N;
    private double Coord_E;
    private String name;
    private int type=0;
    
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
        name="RunwayFAF"+Integer.toString(Util.Current.WaypointCount);
        Util.Tree.newWaypoint(this);
        type=t;
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
    public double getX(){
        return Coord_E;
    }
    
    public double getY(){
        return Coord_N;
    }
    public double getType(){
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