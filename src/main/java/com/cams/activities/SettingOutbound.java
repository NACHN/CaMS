package com.cams.activities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cams.Util;

public class SettingOutbound extends JDialog{
    private JList<String> routeList;
    private DefaultListModel<String> listModel;
    public SettingOutbound(){
        setTitle("Outbound settings");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Setting.png")));

        listModel = new DefaultListModel<>();
        for (int i = 0; i < Util.Current.RouteCount; i++) {
            listModel.addElement(Util.Current.Routes[i].toString());
        }
        

        routeList = new JList<>(listModel);
        routeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        routeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                StringBuilder wps = new StringBuilder();
                for (int i:routeList.getSelectedIndices()) {
                    Util.Current.Outbound[i]=true;
                    Util.Current.outs++;
                    wps.append(Util.Current.Routes[i].toString()).append(", ");
                }
                Util.status.Outs.setText(wps.toString());
                if(Util.Current.outs!=0&&Util.Current.ins!=0){
                    Util.settings.StartSim.setEnabled(true);
                    Util.settings.remove(Util.settings.tips);
                    Util.settings.repaint();
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(routeList);
        add(scrollPane);

        setSize(200,200);
        setLocation(1100,200);
        setVisible(true);
        setAlwaysOnTop(true);
    }
}
