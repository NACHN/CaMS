package com.cams;

public class Route {
    private int sequence=0;
    private int[] Waypoints=new int[1000];
    private int wpc=0;
    private String name;
    public double quotient=1;

    public Route(int wp){
        Waypoints[wpc]=wp;
        wpc++;
        name="Route"+Integer.toString(Util.Current.RouteCount);
        Util.Tree.newRoute(this);
    }
    public void addWaypoint(int wp){
        Waypoints[wpc]=wp;
        wpc++;
    }
    public void finish(){
        Util.Current.RouteCount++;
        StringBuilder wps = new StringBuilder();
        for (int i=0;i<wpc-1;i++) {
            wps.append(Util.Current.Waypoints[Waypoints[i]].toString()).append(", ");
        }
        name=Util.Current.Waypoints[Waypoints[0]].toString()+"-"+Util.Current.Waypoints[Waypoints[wpc-1]].toString();
    }
    public int[] getWaypoints(){
        return Waypoints;
    }
    public int routelength(){
        return wpc;
    }
    public String getname(){
        return name;
    }
    public void setname(String n){
        this.name=n;
    }
    public void setWaypoints(int[] in){
        Waypoints=in;
    }
    @Override
    public String toString() {
        return name;
    }
}
