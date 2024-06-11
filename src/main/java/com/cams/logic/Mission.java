package com.cams.logic;

import com.cams.*;

public class Mission {
    private int flights = 0;
    private boolean done = false;
    SimulationEnvironment Env;

    public Mission() {
        Env = Util.Current;
        // start Monte-Carlo simulation
        Util.Current.mission = this;
        System.out.println("Started");
        Env.simulationRun();
    }

    public void newRandomAircraft() {
        boolean isInbound = false;
        if (Math.random() < 0.5)
            isInbound = true;
        String type;
        double dis=Double.MAX_VALUE;
        String gettype="NULL";
        if (isInbound) {
            // System.out.println(Env.ins);
            int inSeq = (int) (Math.random() * Env.ins);
            // System.out.println(inSeq);
            int inIdx = Env.InboundIdx[inSeq];
            Route i = Env.Routes[inIdx];
            int BasicType = (int) (Math.random() * 3);
            
            type=randomtype(BasicType);
            if(i.preceed!=null){
                gettype=i.preceed.gettype();
                double dx=i.preceed.getX()-Env.Waypoints[i.getWaypoints()[0]].getX();
                double dy=i.preceed.getY()-Env.Waypoints[i.getWaypoints()[0]].getY();
                dis=Math.sqrt(dx*dx+dy*dy);
            }
            if (Env.Runways[0].PermissiontoUse()&&Util.separationCheck(dis,gettype,type))
                i.preceed=new Aircraft(Env, i, true, type);
            flights++;
        } else {
            int outSeq = (int) (Math.random() * Env.outs);
            int outIdx = Env.OutboundIdx[outSeq];
            Route i = Env.Routes[outIdx];
            int BasicType = (int) (Math.random() * 3);

            type=randomtype(BasicType);
            if(i.preceed!=null){
                gettype=i.preceed.gettype();
                double dx=i.preceed.getX()-Env.Waypoints[i.getWaypoints()[0]].getX();
                double dy=i.preceed.getY()-Env.Waypoints[i.getWaypoints()[0]].getY();
                dis=Math.sqrt(dx*dx+dy*dy);
            }
            if (Env.Runways[0].PermissiontoUse()&&Util.separationCheck(dis,gettype,type))
                i.preceed=new Aircraft(Env, i, false, type);
            flights++;
        }
        if (flights == Env.Times - 1)
            done = true;
    }

    public int getCurrentFlight() {
        return flights;
    }

    public boolean isDone() {
        return done;
    }

    String randomtype(int BasicType) {
        String type = new String();
        switch (BasicType) {
            case 0:
                while (true) {
                    int m = (int) (Math.random() * 4);
                    if (Env.selectedHeavy[m]) {
                        type = Util.Heavy[m];
                        break;
                    }
                }
                break;
            case 1:
                while (true) {
                    int m = (int) (Math.random() * 4);
                    if (Env.selectedMedium[m]) {
                        type = Util.Medium[m];
                        break;
                    }
                }
                break;
            case 2:
                while (true) {
                    int m = (int) (Math.random() * 3);
                    if (Env.selectedLight[m]) {
                        type = Util.Light[m];
                        break;
                    }
                }
                break;

            default:
                break;
        }
        return type;
    }
}