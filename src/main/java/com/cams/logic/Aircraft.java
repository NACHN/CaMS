package com.cams.logic;
/**
 * Aircraft class defines aircraft properties, including positions, velocity,
 * type, payload, etc, and aircraft maneuvers, including move, land, takeoff,
 * navigate, etc.
 */
public class Aircraft{
    private String Type;
    private double Coord_N;
    private double Coord_E;
    private double Coord_D;
    private double Weight;
    private int Heading;
    private double Distance;
    private double Speed;
    private double VerticalSpeed;
    private boolean toNext;

    public Aircraft(Waypoint Ini,double Height,Waypoint[] Route,boolean isApproach,Runway ActiveRunway){
        this.Coord_N=Ini.position()[0];
        this.Coord_E=Ini.position()[1];
        this.Coord_D=-Height;
        int RouteLength=Route.length;
        if(isApproach){
            for(int i=0;i<RouteLength-1;i++){
                this.navigate(Route[i]);
            }
            this.landing(ActiveRunway);
        }
        else{
            this.takeoff(ActiveRunway);
            for(int i=0;i<RouteLength-1;i++){
                this.navigate(Route[i]);
            }
        }
    }

    void navigate(Waypoint Dest){
        if(this.toNext){
            return;
        }
        else{
            this.move();
        }
    }
    
    void landing(Runway toLand){

    }

    void takeoff(Runway forTakeoff){

    }

    void move(){

    }

    double[] getCoord(){
        double[] Coord={this.Coord_N,this.Coord_E,this.Coord_D};
        return Coord;
    }
    
    double getSpeed() {return Speed;}

    double getVerticalSpeed() {return VerticalSpeed;}
}