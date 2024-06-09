package com.cams;

import javax.swing.*;
import java.awt.*;
import com.cams.components.panels.*;
import com.cams.components.trees.*;
import com.cams.activities.*;
import com.cams.languages.*;

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
     */
    public static String Version = "0.3";
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

    double[] NED2LLH(double[] Coord) {

        return Coord;
    }

    double[] LLH2NED(double[] Coord) {

        return Coord;
    }

}