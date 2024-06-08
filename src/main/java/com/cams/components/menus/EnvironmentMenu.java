package com.cams.components.menus;

import javax.swing.*;

public class EnvironmentMenu extends JMenu{
    public EnvironmentMenu(){
        this.setText("Environment");
        JMenuItem Fleet = new JMenuItem("Fleet settings");
        JMenuItem Load = new JMenuItem("Load Simulation");
        this.add(Fleet);
        this.add(Load);
    }
}