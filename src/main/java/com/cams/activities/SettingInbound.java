package com.cams.activities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cams.Util;

public class SettingInbound extends JDialog{
    private JList<String> routeList;
    private DefaultListModel<String> listModel;
    public SettingInbound(){
        setTitle("Inbound settings");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Setting.png")));

        listModel = new DefaultListModel<>();
        for (int i = 0; i < Util.Current.RouteCount; i++) {
            if(Util.Current.Routes[i]!=null)
            listModel.addElement(Util.Current.Routes[i].toString());
        }
        

        routeList = new JList<>(listModel);
        routeList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        routeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                StringBuilder wps = new StringBuilder();
                Util.Current.InboundIdx=routeList.getSelectedIndices();
                Util.Current.ins=routeList.getSelectedIndices().length;
                for (int i:Util.Current.InboundIdx) {
                    Util.Current.Inbound[i]=Util.Current.Routes[i].quotient;
                    Util.Current.inq+=Util.Current.Routes[i].quotient;
                    wps.append(Util.Current.Routes[i].toString()).append(", ");
                }
                Util.status.Ins.setText(wps.toString());
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
