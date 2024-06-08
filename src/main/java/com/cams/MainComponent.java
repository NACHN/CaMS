package com.cams;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import com.cams.components.menus.*;
import com.cams.components.panels.*;
import com.cams.components.tools.*;
import com.cams.components.trees.*;


public class MainComponent extends JComponent{
    
    public MainComponent(MainInterface Main){
        /**
         * Create menubar
         */
        JMenuBar menuBar = new JMenuBar();
        JMenu EnvironmentMenu = new EnvironmentMenu();
        JMenu SimulationMenu = new SimulationMenu();
        JMenu AssetMenu = new AssetMenu();
        JMenu AboutMenu = new AboutMenu();
        menuBar.add(EnvironmentMenu);
        menuBar.add(SimulationMenu);
        menuBar.add(AssetMenu);
        menuBar.add(AboutMenu);
        
        Main.setJMenuBar(menuBar);

        /**
         * Create toolbar
         */
        JToolBar toolBar = new ToolBarNew();
        Main.setLayout(new BorderLayout());
        Main.add(toolBar,BorderLayout.NORTH);
        /**
         * The tree for displaying runways, waypoints, etc.
         */
        ObjectTree airportTree = new ObjectTree();
        Util.Tree=airportTree;
        /**
         * Simulation environment settings
         */
        JPanel settingsPanel = new SimulationPanel();
        

        /**
         * Mission status panel
         */
        JPanel missionStatus = new StatusPanel();


        /**
         * Simulation map
         */
        MapPanel mapPanel = new MapPanel();
        mapPanel.setBackground(Color.BLACK);

        /**
         * Split panels
         */
        JSplitPane TreeMap = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, airportTree, mapPanel);
        TreeMap.setResizeWeight(0.1);
        JSplitPane MapSet = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, TreeMap, settingsPanel);
        MapSet.setResizeWeight(0.9);
        JPanel Map = new JPanel(new BorderLayout());
        Map.add(MapSet,BorderLayout.CENTER);
        
        JSplitPane thisSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, Map, missionStatus);
        thisSplitPane.setResizeWeight(0.8);

        /**
         * Layout of components
         */
        Main.add(thisSplitPane);
        Main.setVisible(true);

        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){
                switch (Util.Toolstate) {
                    case 0:
                        Util.Main.setCursor(Cursor.getDefaultCursor());
                        break;

                    default:
                        Util.Main.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        break;
                }
            }
        });
    }
}