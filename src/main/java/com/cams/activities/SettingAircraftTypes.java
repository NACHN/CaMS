package com.cams.activities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cams.*;

public class SettingAircraftTypes extends JDialog{
    private JList<String> HList;
    private JList<String> MList;
    private JList<String> LList;
    private DefaultListModel<String> HlistModel;
    private DefaultListModel<String> MlistModel;
    private DefaultListModel<String> LlistModel;
    public SettingAircraftTypes(){
        setTitle("Aircraft Types Setting");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Setting.png")));

        HlistModel = new DefaultListModel<>();
        for (String i:Util.Heavy) {
            HlistModel.addElement(i);
        }

        MlistModel = new DefaultListModel<>();
        for (String i:Util.Medium) {
            MlistModel.addElement(i);
        }

        LlistModel = new DefaultListModel<>();
        for (String i:Util.Light) {
            LlistModel.addElement(i);
        }
        

        HList = new JList<>(HlistModel);
        HList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        MList = new JList<>(MlistModel);
        MList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        LList = new JList<>(LlistModel);
        LList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        HList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Util.Current.selectedHeavy=new boolean[]{false,false,false,false};
                StringBuilder wps = new StringBuilder();
                for (int i:HList.getSelectedIndices()) {
                    Util.Current.selectedHeavy[i]=true;
                    wps.append(Util.Heavy[i]).append(", ");
                }
                Util.status.Heavys.setText(wps.toString());
            }
        });
        JScrollPane scrollPaneH = new JScrollPane(HList);
        add(scrollPaneH);

        MList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Util.Current.selectedMedium=new boolean[]{false,false,false,false};
                StringBuilder wps = new StringBuilder();
                for (int i:MList.getSelectedIndices()) {
                    Util.Current.selectedMedium[i]=true;
                    wps.append(Util.Medium[i]).append(", ");
                }
                Util.status.Meduims.setText(wps.toString());
            }
        });
        JScrollPane scrollPaneM = new JScrollPane(MList);
        add(scrollPaneM);

        LList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Util.Current.selectedLight=new boolean[]{false,false,false};
                StringBuilder wps = new StringBuilder();
                for (int i:LList.getSelectedIndices()) {
                    Util.Current.selectedLight[i]=true;
                    wps.append(Util.Light[i]).append(", ");
                }
                Util.status.Lights.setText(wps.toString());
            }
        });
        JScrollPane scrollPaneL = new JScrollPane(LList);
        add(scrollPaneL);

        setSize(200,200);
        setLocation(1100,200);
        setVisible(true);
        setAlwaysOnTop(true);
}
}