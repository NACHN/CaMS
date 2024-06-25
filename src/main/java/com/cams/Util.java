package com.cams;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import com.cams.components.panels.*;
import com.cams.components.trees.*;
import com.cams.activities.*;
import com.cams.languages.*;
import com.cams.logic.Aircraft;

public class Util {
    public static SimulationEnvironment Current;
    public static MainComponent Main;
    public static MapPanel Map;
    public static ObjectTree Tree;
    public static SimulationPanel settings;
    public static StatusPanel status;

    public static boolean NewRoute = true;
    /**
     * Update log:
     * v0.1: GUI and assets displaying
     * v0.2: Change naming logic for routes
     * v0.3: Optimize user experience and display status
     * v0.4: Navigation visualization
     * v0.5: Aircraft perdormance
     * v0.6: Landing & Takeoff
     * v0.7: Output simulation results
     * -v0.8: Save and Load simulation environment
     */
    public static String Version = "0.7";
    /**
     * Default 100px for 1000m
     */
    public static int scalar = 1000;
    public static double DrawingFactor = 100 / (double) scalar;
    /**
     * Toolstate
     * 0 for view map
     * 1 for add waypoint
     * 2 for add runway
     * 3 for add route
     */
    public static int Toolstate = 0;
    public static int MousePosX;
    public static int MousePosY;
    public final static JLabel tooltipLabel = new JLabel("Select waypoints, end at Runway FAF; or rightclick to end.");
    public static Image plane = Toolkit.getDefaultToolkit().getImage(Util.class.getResource("/image/plane.png"));

    public static String[] Heavy = { "A345", "A388", "B744", "B788" };
    public static String[] Medium = { "A20N", "A320", "B38M", "B738" };
    public static String[] Light = { "AC11", "AC95", "PA32" };

    public static FileOutputStream outs;

    double[] NED2LLH(double[] Coord) {

        return Coord;
    }

    double[] LLH2NED(double[] Coord) {

        return Coord;
    }

    public static double knotsToMetersPerSecond(int knots) {
        return knots * 0.514444;
    }

    public static double nauticalMilesToMeters(int nm) {
        return nm * 1852;
    }

    public static double feetPerMinuteToMetersPerSecond(int ftmin) {
        return ftmin * 0.00508;
    }

    public static double flightLevelToMeters(int FL) {
        return FL * 30.48;
    }

    public static double getRunwayROD(double s) {
        return -s/19;
    }

    public static double DisttoExit(Runway rw,Aircraft air,int e){
        double dx = air.getX() - rw.getX();
        double dy = air.getY() - rw.getY();
        double dis=Math.sqrt(dx * dx + dy * dy);
        if (air.getHeading() == 0) {
            if (dy < 0) {
                dis *= -1;
            }
            return Math.abs(dis-rw.exit[e]);
        } else if (air.getHeading() > 180) {
            if (dx > 0) {
                dis *= -1;
            }
            return Math.abs(rw.exit[e]-dis);
        } else {
            if (dx > 0) {
                dis *= -1;
            }
            return Math.abs(dis-rw.exit[e]);
        }
    }
    public static boolean separationCheck(int s,String gettype, String type){
        /**
         * 0 for Heavy
         * 1 for A380-800
         * 2 for Medium
         * 3 for Light
         */
        int s1 = -1;
        int s2 = -1;
        for (String i : Util.Heavy) {
            if (gettype == i) {
                if (i == "A388")
                    s1 = 1;
                else
                    s1 = 0;
            }
            if (type == i) {
                if (i == "A388")
                    s2 = 1;
                else
                    s2 = 0;
            }
        }
        for (String i : Util.Medium) {
            if (gettype == i) {
                s1 = 2;
            }
            if (type == i) {
                s2 = 2;
            }
        }
        for (String i : Util.Light) {
            if (gettype == i) {
                s1 = 3;
            }
            if (type == i) {
                s2 = 3;
            }
        }
        int interval = 1;  // 默认1分钟
        if ((s1 == 0 && s2 == 2) || (s1 == 2 && s2 == 0) || (s1 == 0 && s2 == 3) || (s1 == 3 && s2 == 0) || (s1 == 2 && s2 == 3) || (s1 == 3 && s2 == 2)) {
            interval = 3;  // 飞机类型为重型、中型或轻型时的间隔时间为3分钟
        } else if ((s1 == 1 && (s2 == 2 || s2 == 3)) || (s2 == 1 && (s1 == 2 || s1 == 3))) {
            interval = 4;  // 飞机类型包括A380-800时的间隔时间为4分钟
        }
        return s>interval*60;
    }
    public static boolean separationCheck(double dis, String gettype, String type) {
        /**
         * 0 for Heavy
         * 1 for A380-800
         * 2 for Medium
         * 3 for Light
         */
        int s1 = -1;
        int s2 = -1;
        for (String i : Util.Heavy) {
            if (gettype == i) {
                if (i == "A388")
                    s1 = 1;
                else
                    s1 = 0;
            }
            if (type == i) {
                if (i == "A388")
                    s2 = 1;
                else
                    s2 = 0;
            }
        }
        for (String i : Util.Medium) {
            if (gettype == i) {
                s1 = 2;
            }
            if (type == i) {
                s2 = 2;
            }
        }
        for (String i : Util.Light) {
            if (gettype == i) {
                s1 = 3;
            }
            if (type == i) {
                s2 = 3;
            }
        }
        double standard = 0.0;

        if (s1 == 1) { // A380-800
            if (s2 == 0) { // Heavy
                standard = 11100;
            } else if (s2 == 2) { // Medium
                standard = 13000;
            } else if (s2 == 3) { // Light
                standard = 14800;
            }
        } else if (s1 == 0) { // Heavy
            if (s2 == 0) { // Heavy
                standard = 7400;
            } else if (s2 == 2) { // Medium
                standard = 9300;
            } else if (s2 == 3) { // Light
                standard = 11100;
            }
        } else if (s1 == 2) { // Medium
            if (s2 == 3) { // Light
                standard = 9300;
            }
        } else
            standard = 5600;

        return dis >= standard;
    }

}