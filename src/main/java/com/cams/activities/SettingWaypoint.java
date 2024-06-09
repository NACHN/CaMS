package com.cams.activities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.cams.*;

public class SettingWaypoint extends JDialog{
    public SettingWaypoint(final Waypoint wp){
        setTitle("Waypoint settings");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Setting.png")));

        JButton Save = new JButton ("Save");
        JButton delete = new JButton ("Delete");
        final JTextArea setname = new JTextArea();  
        final JTextArea setX = new JTextArea();
        final JTextArea setY = new JTextArea();

        
        setname.setText(wp.getName());
        setname.setPreferredSize(new Dimension(80,16));
        setX.setText(Double.toString(wp.getX()));
        setX.setPreferredSize(new Dimension(100,16));
        setY.setText(Double.toString(wp.getY()));
        setY.setPreferredSize(new Dimension(100,16));
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wp.setPosition(Double.valueOf(setX.getText()), Double.valueOf(setY.getText()));
                wp.setname(setname.getText());
                setVisible(false);
                Util.Map.repaint();
                dispose();
            }
        });
        
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Util.Current.WaypointCount; i++) {
                    if (Util.Current.Waypoints[i] == wp) {
                        Util.Tree.deleteWaypoint(wp);
                        Util.Current.Waypoints[i] = null;
                        setVisible(false);
                        Util.Map.repaint();
                        dispose();
                    }
                }
            }
        });
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add( new JLabel ("Runway name:"));
        add(setname);
        add( new JLabel ("X position:"));
        add(setX);
        add( new JLabel ("Y position:"));
        add(setY);
        add(delete);
        add(Save);
        setSize(200,150);
        setLocation(200,200);
        setVisible(true);
        setAlwaysOnTop(true);

        if(wp.getType()!=0){
            Save.setEnabled(false);
            delete.setEnabled(false);
            setX.setEditable(false);
            setY.setEditable(false);
        }
    }
}