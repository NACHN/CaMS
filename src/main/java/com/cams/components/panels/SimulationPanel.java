package com.cams.components.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.cams.*;
import com.cams.activities.*;
import com.cams.logic.*;
import com.cams.languages.Language;

public class SimulationPanel extends JPanel{
    JLabel timesLabel = new JLabel("Times:");
    
    JButton Inbound = new JButton("进港设置");
    JButton ClearInbound = new JButton("清除进港");
    JButton Outbound = new JButton("离港设置");
    JButton ClearOutbound = new JButton("清除离港");
    public JLabel tips = new JLabel("请首先设置进离港路由");
    public JButton StartSim = new JButton("开始模拟");  
    public SimulationPanel(){
        JLabel settingsLabel = new JLabel("Simulation Settings");
        
        Util.settings=this;
        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        add(settingsLabel);
    }
    public void newSimulation(){
        JTextArea SimTimes = new JTextArea(Integer.toString(Util.Current.Times));
        SimTimes.setPreferredSize(new Dimension(100,16));

        JPanel C1 = new JPanel();
        C1.add(timesLabel);
        C1.add(SimTimes);
        C1.setMaximumSize(new Dimension(Integer.MAX_VALUE,24));
        add(C1);
        add(Inbound);
        add(ClearInbound);
        add(Outbound);
        add(ClearOutbound);
        add(tips);
        add(StartSim);
        StartSim.setEnabled(false);
        setVisible(false);
        setVisible(true);
        Inbound.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new SettingInbound();
            }
        });

        Outbound.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new SettingOutbound();
            }
        });

        ClearInbound.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Util.Current.Inbound=new boolean[1000];
                Util.Current.ins=0;
                Util.status.Ins.setText("");
            }
        });

        ClearOutbound.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Util.Current.Outbound=new boolean[1000];
                Util.Current.outs=0;
                Util.status.Outs.setText("");
                
            }
        });

        StartSim.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new Mission();
            }
        });
    }
}
