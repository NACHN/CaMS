package com.cams.activities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.TreePath;

import com.cams.*;

public class SettingRunway extends JDialog{
    public SettingRunway(final Runway wp){
        setTitle("Runway settings");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Setting.png")));

        JButton Save = new JButton ("Save"); 
        JButton delete = new JButton ("Delete");
        final JTextArea setname = new JTextArea();  
        final JTextArea setX = new JTextArea();
        final JTextArea setY = new JTextArea();
        final JTextArea setAngle = new JTextArea();

        setname.setText(wp.getname());
        setname.setPreferredSize(new Dimension(80,16));
        setX.setText(Double.toString(wp.getX()));
        setX.setPreferredSize(new Dimension(100,16));
        setY.setText(Double.toString(wp.getY()));
        setY.setPreferredSize(new Dimension(100,16));
        setAngle.setText(Double.toString(wp.getAngle()));
        setAngle.setPreferredSize(new Dimension(100,16));
        Save.addMouseListener( new MouseAdapter(){  
            @Override
            public void mouseClicked(MouseEvent e){  
                wp.setPosition(Double.valueOf(setX.getText()),Double.valueOf(setY.getText()));
                wp.setAngle(Double.valueOf(setAngle.getText()));
                wp.setname(setname.getText());
                setVisible(false);
                Util.Map.repaint();
                dispose();
            }  
        });
        delete.addMouseListener( new MouseAdapter(){  
            @Override
            public void mouseClicked(MouseEvent e){  
                for(int i=0;i<Util.Current.RunwayCount;i++){
                    if(Util.Current.Runways[i]==wp){
                        Util.Tree.deleteRunway(wp);
                        Util.Current.Runways[i]=null;
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
        add( new JLabel ("Angle:"));
        add(setAngle);
        add(delete);
        add(Save);
        setSize(200,200);
        setLocation(200,200);
        setVisible(true);
        setAlwaysOnTop(true);
    }
}