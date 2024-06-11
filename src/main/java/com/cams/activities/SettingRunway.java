package com.cams.activities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.TreePath;

import com.cams.*;

public class SettingRunway extends JDialog{
    private final Runway wp;
    private final JTextArea setname;
    private final JTextArea setX;
    private final JTextArea setY;
    private final JTextArea setH;
    private final JTextArea setAngle;

    public SettingRunway(final Runway wp){
        this.wp = wp;
        setTitle("Runway settings");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Setting.png")));

        JButton Exit = new JButton ("Add Taxiway Port"); 
        JButton Save = new JButton ("Save"); 
        JButton delete = new JButton ("Delete");
        setname = new JTextArea();  
        setX = new JTextArea();
        setY = new JTextArea();
        setH = new JTextArea();
        setAngle = new JTextArea();

        setname.setText(wp.getname());
        setname.setPreferredSize(new Dimension(80,16));
        setX.setText(Double.toString(wp.getX()));
        setX.setPreferredSize(new Dimension(100,16));
        setY.setText(Double.toString(wp.getY()));
        setY.setPreferredSize(new Dimension(100,16));
        setH.setText(Double.toString(wp.getHeight()));
        setH.setPreferredSize(new Dimension(100,16));
        setAngle.setText(Double.toString(wp.getAngle()));
        setAngle.setPreferredSize(new Dimension(100,16));
        
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitAction();
            }
        });

        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAction();
            }
        });
        
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAction();
            }
        });

        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(new JLabel("Runway name:"));
        add(setname);
        add(new JLabel("X position:"));
        add(setX);
        add(new JLabel("Y position:"));
        add(setY);
        add(new JLabel("Elevation:"));
        add(setH);
        add(new JLabel("Angle:"));
        add(setAngle);
        add(Exit);
        add(delete);
        add(Save);
        setSize(200,240);
        setLocation(200,200);
        setVisible(true);
        setAlwaysOnTop(true);
    }

    private void saveAction(){
        try {
            wp.setPosition(Double.valueOf(setX.getText()), Double.valueOf(setY.getText()));
            wp.setHeight(Double.valueOf(setH.getText()));
            wp.setAngle(Double.valueOf(setAngle.getText()));
            wp.setname(setname.getText());
            setVisible(false);
            Util.Map.repaint();
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAction(){
        for(int i = 0; i < Util.Current.RunwayCount; i++) {
            if(Util.Current.Runways[i] == wp) {
                Util.Tree.deleteRunway(wp);
                Util.Current.Runways[i] = null;
                setVisible(false);
                Util.Map.repaint();
                dispose();
                break;
            }
        }
    }

    private void exitAction(){
        
    }
}
