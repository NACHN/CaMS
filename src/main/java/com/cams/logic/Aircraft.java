package com.cams.logic;

import com.cams.Route;
import com.cams.Runway;
import com.cams.SimulationEnvironment;
import com.cams.Util;
import com.cams.Waypoint;
import com.cams.aircraftdata.AircraftData;

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
        Waypoint Dest = Env.Waypoints[route.getWaypoints()[route.routelength() - 1]];

        Speed = Util.knotsToMetersPerSecond(data.descentIAS);
        if (isApproach) {
            Coord_N = Ini.getY();
            Coord_E = Ini.getX();
            status = 0;
            rw = Env.Runways[Dest.getbelong()];
            navigate(Ini, Env.Waypoints[route.getWaypoints()[1]]);
        }
        // Takeoff aircraft
        else {
            rw = Env.Runways[Ini.getbelong()];
            int e;
            while (true) {
                e = (int) (Math.random() * rw.exitCount);
                if (rw.isThreshold[e])
                    break;
            }
            Coord_E = rw.getX() + Math.sin(Math.toRadians(rw.getAngle())) * (-rw.exit[e]);
            Coord_N = rw.getY() - Math.cos(Math.toRadians(rw.getAngle())) * (-rw.exit[e]);
            status = 2;
            int dir = 1;
            if (Ini.Direction == Env.Runways[Ini.getbelong()].getAngle())
                dir = 2;
            Heading = rw.getAngle() + 90;
            /*
             * if (Ini.Direction >= 180)
             * Heading = Ini.Direction - 180;
             * else
             * Heading = Ini.Direction + 180;
             */
            from = null;
            to = Ini;
            Speed = 15;
            Coord_D = rw.getHeight();
            rw.aircraftUsing(this);
            takeoff(rw, dir);
        }

    }

    public void navigate(Waypoint From, Waypoint To) {
        from = From;
        to = To;
        double N1X = Coord_E;
        double N2X = To.getX();

        double N1Y = Coord_N;
        double N2Y = To.getY();
        if (Coord_D > To.height && status == 0) {
            targetHeight = to.height;
            VerticalSpeed = Util.getRunwayROD(Speed);
        }
        // Takeoff
        if (From == null) {
            targetHeight = to.height;
        } else {
            // To FAF, prepare to land
            if (From.getType() == 1 && status == 0 && isApproach) {

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
        }

        // Navigate to next
        double dx = Coord_E - To.getX();
        double dy = Coord_N - To.getY();
        if (Math.sqrt(dx * dx + dy * dy) <= Speed && status != 1) {
            onroute++;
            Waypoint Next = Env.Waypoints[route.getWaypoints()[onroute]];
            from = to;
            if (onroute >= route.routelength() && !isApproach)
                to = null;
            else
                to = Next;
            if (to != null) {
                move();
                navigate(from, to);
            } else {
                Speed = 0;
                VerticalSpeed = 0;
                status = 3;
            }
        } else
            move();
    }

    public void landing(Runway toLand, int dir) {
        rw = toLand;
        double MDA;
        int t;
        switch (status) {
            case 1: // Approaching
                if (dir == 1)
                    MDA = rw.MDA1;
                else
                    MDA = rw.MDA2;
                status = 11;
                Speed = Util.knotsToMetersPerSecond(data.landingVat);
                VerticalSpeed = Util.getRunwayROD(Speed);
                targetHeight = MDA;
                rw.aircraftUsing(this);
                move();
                break;
            case 11: // At MDA
                if (Coord_D <= targetHeight) {
                    status = 12;
                    targetSpeed = Util.knotsToMetersPerSecond(data.landingVat);
                    acceleration = -1;
                    targetSpeed = Util.knotsToMetersPerSecond(data.V2) / 1.3;
                    VerticalSpeed *= Math.random() * 0.2 + 0.6;
                    targetHeight = rw.getHeight();
                }
                move();
                break;
            case 12: // Touch down
                if (Coord_D <= targetHeight) {
                    status = 13;
                    System.out.println(Env.simulationTime+","+name+",Touchdown");
                    Coord_D = rw.getHeight();
                    pilotdelay = (int) (Math.random() * 3);
                }
                move();
                break;
            case 13: // Decelerate delay
                // System.out.println(Env.simulationTime+" "+name+"|Delay: "+pilotdelay);
                if (pilotdelay <= 0) {
                    status = 14;
                    acceleration = -Speed * Speed / data.landingDistance;
                } else
                    pilotdelay--;
                targetSpeed = 41.5;// 80 knots
                move();
                break;
            case 14: // Exit runway
                if (Speed <= targetSpeed) {
                    acceleration = -1;
                    int e = findExit();
                    if (rw.exitType[e] == 6 || rw.exitType[e] == 4) {
                        targetSpeed = 20;
                        if (Speed <= targetSpeed && Util.DisttoExit(rw, this, e) < targetSpeed) {
                            status = 17;
                        }
                    } else if (rw.exitType[e] == 9) {
                        targetSpeed = 15;
                        if (Speed <= targetSpeed && Util.DisttoExit(rw, this, e) < targetSpeed) {
                            status = 17;
                        }
                    } else {
                        targetSpeed = 15;
                        if (Speed <= 3 * targetSpeed) {
                            status = 15;
                            t = e;
                            pilotdelay = (int) (Math.random() * 3);
                        }
                    }
                }
                move();
                break;
            case 15: // Turning delay
                if (pilotdelay == 0) {
                    int e = findExit();
                    double dist = Util.DisttoExit(rw, this, e);
                    if (dist < targetSpeed) {
                        status = 16;
                    }
                    t = e;
                } else
                    pilotdelay--;
                move();
                break;
            case 16: // Turning
                if (targetHeading != Heading) {
                    if (Heading <= 180 && dir == 2 && targetHeading == -1) {
                        targetHeading = Heading + 180;
                        AngularSpeed = 18;
                        Speed = 0;
                        targetSpeed = 0;
                    } else if (Heading > 180 && dir == 1 && targetHeading == -1) {
                        targetHeading = Heading - 180;
                        AngularSpeed = -18;
                        Speed = 0;
                        targetSpeed = 0;
                    }
                } else {

                    Speed = 15;
                    status = 14;
                    targetSpeed = 15;
                }
                move();
                break;
            case 17: // Exited
                rw.clearUse(this);
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
                    near = dis - rw.exit[i];
                    neari = i;
                }
            }
            return neari;
        } else if (Heading > 180) {
            if (dx > 0) {
                dis *= -1;
            }
            for (int i = 0; i < rw.exitCount; i++) {
                if (rw.exit[i] - dis < near && dis - rw.exit[i] < 0 && rw.exit[i] > 0) {
                    near = rw.exit[i] - dis;
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
                    near = dis - rw.exit[i];
                    neari = i;
                }
            }
            return neari;
        }

    }

    public void takeoff(Runway forTakeoff, int dir) {
        rw = forTakeoff;
        switch (status) {
            case 2: // Turn to runway
                Speed = 0;
                targetSpeed = 0;
                if (targetHeading == Heading) {

                    AngularSpeed = 0;
                    targetHeading = -1;
                    status = 21;
                } else if (dir == 1) {
                    targetHeading = to.Direction + 180;
                    AngularSpeed = 18;
                } else {
                    targetHeading = rw.getAngle();
                    AngularSpeed = -18;
                }
                rw.aircraftUsing(this);
                move();
                break;
            case 21: // To turn
                targetSpeed = 15;
                Speed = 15;
                int e = findExit();
                if (rw.isTakeoff[e] == true) {
                    if (Util.DisttoExit(rw, this, e) < targetSpeed) {
                        status = 22;
                    }
                }
                move();
                break;
            case 22: // Turning
                acceleration = 0;
                if (targetHeading != Heading) {
                    if (Heading <= 180 && targetHeading == -1) {
                        targetHeading = Heading + 180;
                        AngularSpeed = 18;
                        Speed = 0;
                        targetSpeed = 0;
                    } else if (Heading > 180 && targetHeading == -1) {
                        targetHeading = Heading - 180;
                        AngularSpeed = -18;
                        Speed = 0;
                        targetSpeed = 0;
                    }
                } else {
                    status = 23;
                    targetSpeed = data.Vlof();
                    acceleration = data.FullAcc();
                }
                move();
                break;
            case 23: // Acceleration
                if (Speed >= targetSpeed) {
                    targetSpeed = Util.knotsToMetersPerSecond(data.initialClimbIAS);
                    acceleration = (0.7 + Math.random() * 0.1) * acceleration;
                    status = 24;
                    VerticalSpeed = Util.feetPerMinuteToMetersPerSecond(data.initialClimbROC);
                }
                move();
                break;
            case 24: // Rotate
                Waypoint from = Env.Waypoints[route.getWaypoints()[onroute]];
                Waypoint to = Env.Waypoints[route.getWaypoints()[1]];
                System.out.println(Env.simulationTime+","+name+",Liftoff");
                onroute = 1;
                rw.clearUse(this);
                status = 0;
                navigate(null, to);
                break;

            default:
                break;
        }
    }

    void move() {
        if (acceleration != 0) {
            if (acceleration > 0) {
                if (Speed < targetSpeed)
                    Speed += acceleration;
            } else {
                if (Speed > targetSpeed)
                    Speed += acceleration;
                // if(Speed<15)Speed=15;
            }
        }
        if (AngularSpeed != 0 && targetHeading != -1) {
            if (Heading == targetHeading) {
                AngularSpeed = 0;
            }
            Heading += AngularSpeed;
        }
        double dx = Math.sin(Math.toRadians(Heading)) * Speed;
        double dy = Math.cos(Math.toRadians(Heading)) * Speed;
        Coord_E += dx;
        Coord_N -= dy;
        if (VerticalSpeed < 0 && Coord_D > targetHeight) {
            Coord_D += VerticalSpeed;
            dx = rw.getX() - Coord_E;
            dy = rw.getY() - Coord_N;
        } else if (VerticalSpeed > 0 && Coord_D < targetHeight) {
            Coord_D += VerticalSpeed;
        }
        Util.Map.repaint();
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