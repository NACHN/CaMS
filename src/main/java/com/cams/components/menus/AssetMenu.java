package com.cams.components.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.cams.Util;


public class AssetMenu extends JMenu{
    public AssetMenu(){
        this.setText("Assets");
        JMenu Add = new JMenu("Add");
        JMenuItem addWaypoint = new JMenuItem("Waypoint");
        JMenuItem addRunway = new JMenuItem("Runway");
        Add.add(addWaypoint);
        Add.add(addRunway);
        this.add(Add);

        addWaypoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Util.Toolstate=1;
                
                
            }
        });
    }

}