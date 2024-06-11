package com.cams.components.panels;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.*;

import org.omg.CORBA.Environment;

import com.cams.*;
import com.cams.activities.*;
import com.cams.logic.*;
import com.cams.languages.Language;

public class SimulationPanel extends JPanel {
    JLabel timesLabel = new JLabel("Times:");
    JTextArea SimTimes = new JTextArea();
    JButton Inbound = new JButton("进港设置");
    JButton ClearInbound = new JButton("清除进港");
    JButton Outbound = new JButton("离港设置");
    JButton ClearOutbound = new JButton("清除离港");
    JButton AircraftTypes = new JButton("机型设置");
    public JLabel tips = new JLabel("请首先设置进离港路由");
    public JButton StartSim = new JButton("开始模拟");
    JLabel SpeedL = new JLabel("模拟速度：");
    JSlider Speed = new JSlider(JSlider.HORIZONTAL, 0, 1000, 100);

    public SimulationPanel() {
        JLabel settingsLabel = new JLabel("Simulation Settings");
        Util.settings = this;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(settingsLabel);
    }

    public void newSimulation() {
        SimTimes.setText(Integer.toString(Util.Current.Times));
        SimTimes.setPreferredSize(new Dimension(100, 16));

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("Pause"));
        labelTable.put(100, new JLabel("1"));
        labelTable.put(200, new JLabel("2"));
        labelTable.put(300, new JLabel("5"));
        labelTable.put(400, new JLabel("10"));
        labelTable.put(500, new JLabel("20"));
        labelTable.put(600, new JLabel("50"));
        labelTable.put(700, new JLabel("100"));
        labelTable.put(800, new JLabel("200"));
        labelTable.put(900, new JLabel("500"));
        labelTable.put(1000, new JLabel("1000"));
        Speed.setLabelTable(labelTable);

        Speed.setMajorTickSpacing(100);
        Speed.setPaintTicks(true);
        Speed.setPaintLabels(true);
        Speed.setSnapToTicks(true);

        JPanel C1 = new JPanel();
        C1.add(timesLabel);
        C1.add(SimTimes);
        C1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        add(C1);
        add(Inbound);
        add(ClearInbound);
        add(Outbound);
        add(ClearOutbound);
        add(AircraftTypes);
        add(tips);
        add(StartSim);
        add(SpeedL);
        add(Speed);
        StartSim.setEnabled(false);

        Inbound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingInbound();
            }
        });

        Outbound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingOutbound();
            }
        });

        ClearInbound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Util.Current.Inbound = new double[1000];
                Util.Current.inq = 0;
                Util.Current.ins = 0;
                Util.status.Ins.setText("");
            }
        });

        ClearOutbound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Util.Current.Outbound = new double[1000];
                Util.Current.outq = 0;
                Util.Current.outs = 0;
                Util.status.Outs.setText("");

            }
        });

        AircraftTypes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingAircraftTypes();
            }
        });

        StartSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Mission();
            }
        });

        Speed.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                SimulationEnvironment Env = Util.Current;
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    System.out.println("Selected value: " + value);
                    switch (value) {
                        case 0:
                            Env.timer.stop();
                            break;
                        case 100:
                            if (!Env.timer.isRunning())
                                Env.timer.start();
                            Env.timer.setDelay(1000);
                            break;
                        case 200:
                            if (!Env.timer.isRunning())
                                Env.timer.start();
                            Env.timer.setDelay(500);
                            break;
                        case 300:
                            if (!Env.timer.isRunning())
                                Env.timer.start();
                            Env.timer.setDelay(200);
                            break;
                        case 400:
                            if (!Env.timer.isRunning())
                                Env.timer.start();
                            Env.timer.setDelay(100);
                            break;
                        case 500:
                            if (!Env.timer.isRunning())
                                Env.timer.start();
                            Env.timer.setDelay(50);
                            break;
                        case 600:
                            if (!Env.timer.isRunning())
                                Env.timer.start();
                            Env.timer.setDelay(20);
                            break;
                        case 700:
                            Env.timer.setDelay(10);
                            break;
                        case 800:
                            if (!Env.timer.isRunning())
                                Env.timer.start();
                            Env.timer.setDelay(5);
                            break;
                        case 900:
                            if (!Env.timer.isRunning())
                                Env.timer.start();
                            Env.timer.setDelay(2);
                            break;
                        case 1000:
                            if (!Env.timer.isRunning())
                                Env.timer.start();
                            Env.timer.setDelay(1);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        setVisible(false);
        setVisible(true);
    }

    
}
