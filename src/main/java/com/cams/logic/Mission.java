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
        if (isInbound) {
            System.out.println(Env.ins);
            int inSeq = (int) (Math.random() * Env.ins);
            System.out.println(inSeq);
            int inIdx = Env.InboundIdx[inSeq];
            Route i = Env.Routes[inIdx];
            if (Env.Runways[0].PermissiontoUse())
                new Aircraft(Env, i, true);
                flights++;
        } else {
            int outSeq = (int) (Math.random() * Env.outs);
            int outIdx = Env.OutboundIdx[outSeq];
            Route i = Env.Routes[outIdx];
            if (Env.Runways[0].PermissiontoUse())
                new Aircraft(Env, i, false);
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
}