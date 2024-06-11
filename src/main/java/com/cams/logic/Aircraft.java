package com.cams.logic;

import java.awt.*;
import com.cams.*;
import com.cams.aircraftdata.*;

/**
 * Aircraft class defines aircraft properties, including positions, velocity,
 * type, payload, etc, and aircraft maneuvers, including move, land, takeoff,
 * navigate, etc.
 */
public class Aircraft {
    private String type = "NULL";
    private String name;
    private double Coord_N;
    private double Coord_E;
    private double Coord_D = 1700;
    private double targetHeight;
    private double Weight;
    private double Heading;
    private double AngularSpeed = 0;
    private double targetHeading = -1;
    private int onroute = 0;
    private double Speed = 100;
    private double targetSpeed;
    private double acceleration;
    private double VerticalSpeed;
    private boolean isApproach;
    AircraftData data;
    /**
     * 0 for cruise
     * 1 for landing
     * -11 for approaching
     * -12 for drifting
     * -13 for reaction 1(delay for slowing down)
     * -14 for slowing down
     * -15 for reaction 2(delay for exiting runway)
     * -16 for exiting runway
     * -17 for exited
     * 2 for takeoff
     * -21 for entering runway
     * -22 for accelerating
     * -23 for rotating
     * -24 for climbing
     * Changing in status refers to change flight stage
     * Inbound: 0-1-11-12-13-14-15-16
     * Outbound: 2-21-22-23-24-0
     */
    private int status = 0;
    SimulationEnvironment Env;
    Route route;
    Waypoint from;
    Waypoint to;
    Runway rw;
    private int pilotdelay;

    public Aircraft(SimulationEnvironment E, Route r, boolean A, String t) {
        type = t;
        data = AircraftPerformance.GetPerformance(this);
        route = r;
        isApproach = A;
        Env = E;
        Env.Aircrafts.add(this);
        name = "Flight " + Env.mission.getCurrentFlight();
        Waypoint Ini = Env.Waypoints[route.getWaypoints()[0]];
        Coord_N = Ini.getY();
        Coord_E = Ini.getX();
        Speed = Util.knotsToMetersPerSecond(data.descentIAS);
        if (isApproach) {
            status = 0;
            navigate(Ini, Env.Waypoints[route.getWaypoints()[1]]);
        }

        else {
            status = 2;
            navigate(Ini, Env.Waypoints[route.getWaypoints()[1]]);
        }

    }

    public void navigate(Waypoint From, Waypoint To) {
        from = From;
        to = To;
        double N1X = Coord_E;
        double N2X = To.getX();

        double N1Y = Coord_N;
        double N2Y = To.getY();

        if (From.getType() == 1 && status == 0) {
            Heading = From.Direction;
            targetHeight = To.height;
            int dir = 1;
            if (getHeading() == Env.Runways[to.getbelong()].getAngle())
                dir = 2;
            status = 1;
            landing(Env.Runways[To.getbelong()], dir);
        } else {
            Heading = Math.toDegrees(Math.atan2((N2X - N1X), (N1Y - N2Y)));
        }

        double dx = Coord_E - To.getX();
        double dy = Coord_N - To.getY();
        if (dx * dx + dy * dy < 5000) {
            onroute++;
            Waypoint Next = Env.Waypoints[route.getWaypoints()[onroute]];
            from = to;
            to = Next;
            move();
        } else
            move();
    }

    public void landing(Runway toLand, int dir) {
        rw = toLand;
        double MDA;
        int t;
        switch (status) {
            case 1:
                if (dir == 1)
                    MDA = rw.MDA1;
                else
                    MDA = rw.MDA2;
                status = 11;
                Speed = Util.knotsToMetersPerSecond(data.landingVat);
                VerticalSpeed = Util.getRunwayROD(Speed);
                targetHeight = MDA;
                rw.useRunway();
                move();
                break;
            case 11:
                if (Coord_D <= targetHeight) {
                    status = 12;
                    targetSpeed = Util.knotsToMetersPerSecond(data.landingVat);
                    acceleration = -1;
                    targetSpeed=Util.knotsToMetersPerSecond(data.V2)/1.3;
                    VerticalSpeed *= Math.random()*0.2+0.3;
                    targetHeight = rw.getHeight();
                }
                move();
                break;
            case 12:
                if (Coord_D <= targetHeight) {
                    status = 13;
                    Coord_D=rw.getHeight();
                    pilotdelay = (int) (Math.random() * 3);
                }
                move();
                break;
            case 13:
                
                if (pilotdelay <= 0) {
                    status = 14;
                    acceleration = - Speed*Speed/data.landingDistance;
                } else
                    pilotdelay--;
                targetSpeed = 41.5;// 80 knots
                move();
                break;
            case 14:
                
                if (Speed <= targetSpeed) {
                    acceleration = -1;
                    int e = findExit();
                    System.out.println(name+"Exit: "+e+" Dist: "+Util.DisttoExit(rw, this, e)+" Speed");
                    if (rw.exitType[e] == 6 || rw.exitType[e] == 4) {
                        targetSpeed = 20;
                        if (Speed <= targetSpeed&&Util.DisttoExit(rw, this, e) < targetSpeed) {
                            status = 17;
                        }
                    } else if (rw.exitType[e] == 9) {
                        targetSpeed = 15;
                        if (Speed <= targetSpeed&&Util.DisttoExit(rw, this, e) < targetSpeed) {
                            status = 17;
                        }
                    } else {
                        targetSpeed = 15;
                        if (Speed <= targetSpeed) {
                            status = 15;
                            t = e;
                            pilotdelay = (int) (Math.random() * 3);
                        }
                    }

                }
                move();
                break;
            case 15:
                if (pilotdelay == 0) {
                    status = 16;
                } else
                    pilotdelay--;
                System.out.println(name+" delay: "+pilotdelay);
                move();
                break;
            case 16:
                int e = findExit();
                System.out.println(name+"Exit: "+e+" Dist: "+Util.DisttoExit(rw, this, e));
                if (Util.DisttoExit(rw, this, e) < targetSpeed) {
                    
                    if (targetHeading != Heading) {
                        if (Heading <= 180 && dir == 1) {
                            targetHeading = Heading + 180;
                            AngularSpeed = 18;
                            Speed=0;
                            targetSpeed=0;
                        } else if (Heading > 180 && dir == 2) {
                            targetHeading = Heading - 180;
                            AngularSpeed = -18;
                            Speed=0;
                            targetSpeed=0;
                        }
                    }
                    else{
                        Speed=15;
                        status=14;
                        targetSpeed=15;
                    }
                }
                move();
                break;
            case 17:
                rw.clearRunway();
                status = 3;
                break;

            default:
                break;
        }
    }

    private int findExit() {
        double dx = Coord_E - rw.getX();
        double dy = Coord_N - rw.getY();
        double dis = Math.sqrt(dx * dx + dy * dy);
        double near = Double.MAX_VALUE;
        int neari = -1;
        if (Heading == 0) {
            if (dy < 0) {
                dis *= -1;
            }
            for (int i = 0; i < rw.exitCount; i++) {
                if (dis - rw.exit[i] < near && dis - rw.exit[i] > 0) {
                    near = rw.exit[i];
                    neari = i;
                }
            }
            return neari;
        } else if (Heading > 180) {
            if (dx > 0) {
                dis *= -1;
            }
            for (int i = 0; i < rw.exitCount; i++) {
                if (dis - rw.exit[i] < near && dis - rw.exit[i] < 0) {
                    near = rw.exit[i];
                    neari = i;
                }
            }
            return neari;
        } else {
            if (dx > 0) {
                dis *= -1;
            }
            for (int i = 0; i < rw.exitCount; i++) {
                if (dis - rw.exit[i] < near && dis - rw.exit[i] > 0) {
                    near = rw.exit[i];
                    neari = i;
                }
            }
            return neari;
        }

    }

    public void takeoff(Runway forTakeoff) {
        rw = forTakeoff;

    }

    void move() {
        if (acceleration != 0) {
            if (acceleration > 0) {
                if (Speed< targetSpeed)
                    Speed += acceleration;
            } else {
                if (Speed> targetSpeed)
                    Speed += acceleration;
                    //if(Speed<15)Speed=15;
            }
        }
        if (AngularSpeed != 0) {
            if (Heading == targetHeading) {
                AngularSpeed = 0;
            }
            Heading += AngularSpeed;
        }
        double dx = Math.sin(Math.toRadians(Heading)) * Speed;
        double dy = Math.cos(Math.toRadians(Heading)) * Speed;
        Coord_E += dx;
        Coord_N -= dy;
        if (VerticalSpeed != 0 && Coord_D > targetHeight) {
            Coord_D += VerticalSpeed;
            dx = rw.getX() - Coord_E;
            dy = rw.getY() - Coord_N;
            // System.out.println(
            // name + ": " + Coord_D + ", target " + targetHeight + ", distance " +
            // Math.sqrt(dx * dx + dy * dy));
        }
        Util.Map.repaint();
        // System.out.println(name + " Heading " + Heading + " at " + Coord_E + ", " +
        // Coord_N);
    }

    public double getX() {
        return Coord_E;
    }

    public double getY() {
        return Coord_N;
    }

    public String getname() {
        return name;
    }

    public String gettype() {
        return type;
    }

    public int getstatus() {
        return status;
    }

    public Waypoint getfrom() {
        return from;
    }

    public Waypoint getto() {
        return to;
    }

    double[] getCoord() {
        double[] Coord = { this.Coord_N, this.Coord_E, this.Coord_D };
        return Coord;
    }

    public double getSpeed() {
        return Speed;
    }

    double getVerticalSpeed() {
        return VerticalSpeed;
    }

    public double getHeading() {
        return Heading;
    }

    public double getHeight() {
        return Coord_D;
    }
}