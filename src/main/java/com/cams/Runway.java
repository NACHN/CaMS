package com.cams;

import java.awt.*;

public class Runway{
    private double Coord_N;
    private double Coord_E;
    public int Width=64;
    public int Length=2800;
    private double Angle=133;
    private String name;
    public double ENTRY1=12980;
    public double ENTRY2=9000;
    public double IAF1=17300;
    public double IAF2=13400;

    public String nameENTRY1="31";
    public String nameENTRY2="13";
    public Waypoint EN1;
    public Waypoint EN2;
    private boolean inuse=true;
    
    public Runway(Point Coord){
        Point Ori=Coord.getLocation();
        Coord_N=Coord.getY();
        Coord_E=Coord.getX();
        name="Runway"+Integer.toString(Util.Current.RunwayCount);
        Util.Tree.newRunway(this);
        Coord.x+=Math.sin(Math.toRadians(Angle))*(ENTRY1+Length/2);
        Coord.y-=Math.cos(Math.toRadians(Angle))*(ENTRY1+Length/2);
        Util.Current.Waypoints[Util.Current.WaypointCount]=new Waypoint(Coord, 1);
        System.out.println(Util.Current.WaypointCount);
        System.out.println(Util.Current.Waypoints[Util.Current.WaypointCount]);
        EN1= Util.Current.Waypoints[Util.Current.WaypointCount-1];
        Coord=Ori.getLocation();
        Coord.x+=Math.sin(Math.toRadians(Angle))*(-ENTRY2-Length/2);
        Coord.y-=Math.cos(Math.toRadians(Angle))*(-ENTRY2-Length/2);
        Util.Current.Waypoints[Util.Current.WaypointCount]=new Waypoint(Coord, 1);
        EN2= Util.Current.Waypoints[Util.Current.WaypointCount-1];
        Util.Current.RunwayCount++;
    }

    public void setPosition(double x, double y){
        this.Coord_N=y;
        this.Coord_E=x;
    }
    
    public void ENTRYpoint(Point Coord){
        Point Ori=Coord.getLocation();
        Coord.x+=Math.sin(Math.toRadians(Angle))*(ENTRY1+Length/2);
        Coord.y-=Math.cos(Math.toRadians(Angle))*(ENTRY1+Length/2);
        EN1.setPosition(Coord.x, Coord.y);
        Coord=Ori.getLocation();
        Coord.x+=Math.sin(Math.toRadians(Angle))*(-ENTRY2-Length/2);
        Coord.y-=Math.cos(Math.toRadians(Angle))*(-ENTRY2-Length/2);
        EN2.setPosition(Coord.x, Coord.y);
    }

    void setSize(int x, int y){
        this.Width=y;
        this.Length=x;
    }
    public void setAngle(double a){
        Angle=a;
        if(a>180){
            nameENTRY1=Integer.toString((int)Angle/10);
            nameENTRY2=Integer.toString((int)Angle/10-18);
        }
        else if(a!=0){
            nameENTRY1=Integer.toString((int)Angle/10+18);
            nameENTRY2=Integer.toString((int)Angle/10);
        }
        else{
            nameENTRY1="18";
            nameENTRY2="00";
        }
        Point p=new Point((int)Coord_E, (int)Coord_N);
        ENTRYpoint(p);
    }
    public void setname(String n){
        name=n;
    }

    public boolean PermissiontoLand(){
        return inuse?false:true;
    }

    public void delete(){
        
    }

    public double getX(){
        return Coord_E;
    }
    
    public double getY(){
        return Coord_N;
    }

    public Point getSize(){
        return new Point(this.Width,this.Length);
    }

    public double getAngle(){
        return Angle;
    }

    public String getname(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}