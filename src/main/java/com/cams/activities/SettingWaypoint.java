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
        
        final JTextArea setX = new JTextArea();
        final JTextArea setY = new JTextArea();

        if(wp.getType()==1){
            Save.setEnabled(false);
            delete.setEnabled(false);
            setX.setEnabled(false);
            setY.setEnabled(false);
        }
        setX.setText(Double.toString(wp.getX()));
        setX.setPreferredSize(new Dimension(100,16));
        setY.setText(Double.toString(wp.getY()));
        setY.setPreferredSize(new Dimension(100,16));
        Save.addMouseListener( new MouseAdapter(){  
            @Override
            public void mouseClicked(MouseEvent e){  
                wp.setPosition(Double.valueOf(setX.getText()),Double.valueOf(setY.getText()));
                setVisible(false);
                Util.Map.repaint();
                dispose();
            }  
        });
        delete.addMouseListener( new MouseAdapter(){  
            @Override
            public void mouseClicked(MouseEvent e){  
                for(int i=0;i<Util.Current.WaypointCount;i++){
                    if(Util.Current.Waypoints[i]==wp){
                        Util.Tree.deleteWaypoint(wp);
                        Util.Current.Waypoints[i]=null;
                        setVisible(false);
                        Util.Map.repaint();
                        dispose();
                    }
                }
            }  
        });
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
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
    }
}