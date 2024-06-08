package com.cams.components.tools;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.cams.*;


public class ToolBarNew extends JToolBar{
    public ToolBarNew(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        Dimension ToolButton=new Dimension(32, 32);
        ImageIcon IconAddWaypoint = new ImageIcon(getClass().getResource("/image/AddWaypoint.png"));
        ImageIcon IconAddRunway = new ImageIcon(getClass().getResource("/image/AddRunway.png"));
        ImageIcon IconAddRoute = new ImageIcon(getClass().getResource("/image/AddRoute.png"));
        
        Util.tooltipLabel.setOpaque(true);
        Util.tooltipLabel.setBackground(Color.YELLOW);
        Util.tooltipLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Util.tooltipLabel.setVisible(false);

        JButton AddWaypoint = new JButton();
        AddWaypoint.setIcon(IconAddWaypoint);
        AddWaypoint.setPreferredSize(ToolButton);
        AddWaypoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Util.Main.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                Util.Toolstate=1;
            }
        });

        JButton AddRunway = new JButton();
        AddRunway.setIcon(IconAddRunway);
        AddRunway.setPreferredSize(ToolButton);
        AddRunway.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Util.Main.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                Util.Toolstate=2;
            }
        });

        JButton AddRoute = new JButton();
        AddRoute.setIcon(IconAddRoute);
        AddRoute.setPreferredSize(ToolButton);
        AddRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Util.Main.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                Util.Toolstate=3;
                Util.tooltipLabel.setVisible(true);
            }
        });

        this.add(AddWaypoint);
        this.add(AddRunway);
        this.add(AddRoute);
    }
    

}