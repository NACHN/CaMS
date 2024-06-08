package com.cams.components.menus;

import javax.swing.*;

import com.cams.SimulationEnvironment;

import java.awt.event.*;


public class SimulationMenu extends JMenu{
    public SimulationMenu(){
        this.setText("Simulation");
        JMenuItem New = new JMenuItem("New Simulation");
        JMenuItem Save = new JMenuItem("Save Simulation");
        JMenuItem Load = new JMenuItem("Load Simulation");
        this.add(New);
        //this.add(Save);
        //this.add(Load);

        New.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new SimulationEnvironment();
            }
        });

        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new SimulationEnvironment();
            }
        });

        Load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new SimulationEnvironment();
            }
        });
    }
}