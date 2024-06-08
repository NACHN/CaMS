package com.cams.activities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.cams.*;

public class SettingRoute extends JDialog{
    public SettingRoute(final Route wp){
        setTitle("Route settings");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Setting.png")));
        final JTextArea[] setwp = new JTextArea[1000];
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton Save = new JButton ("Save");
        JButton delete = new JButton ("Delete");
        int[] idw=wp.getWaypoints();
        for(int i=0;i<=wp.routelength()-1;i++){
            //System.out.println("routei:"+i);
            this.add(new JLabel("#"+i+"waypoint:"));
            setwp[i]=new JTextArea(Integer.toString(idw[i]));
            setwp[i].setPreferredSize(new Dimension(80,16));
            this.add(setwp[i]);
        }

        Save.addMouseListener( new MouseAdapter(){  
            @Override
            public void mouseClicked(MouseEvent e){
                int[] wps=new int[1000];
                for(int i=0;i<wp.routelength();i++){
                    wps[i]=Integer.valueOf(setwp[i].getText());
                }
                wp.setWaypoints(wps);
                setVisible(false);
                Util.Map.repaint();
                dispose();
            }  
        });
        delete.addMouseListener( new MouseAdapter(){  
            @Override
            public void mouseClicked(MouseEvent e){  
                for(int i=0;i<Util.Current.WaypointCount-1;i++){
                    if(Util.Current.Routes[i]==wp){
                        Util.Tree.deleteRoute(wp);
                        Util.Current.Routes[i]=null;
                        setVisible(false);
                        Util.Map.repaint();
                        dispose();
                    }
                }
            }  
        });
        add(delete);
        add(Save);
        setSize(200,150);
        setLocation(200,200);
        setVisible(true);
        setAlwaysOnTop(true);
    }
}
