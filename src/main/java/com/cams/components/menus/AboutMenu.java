package com.cams.components.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.cams.activities.*;


public class AboutMenu extends JMenu{
    public AboutMenu(){
        this.setText("About");
        JMenuItem License = new JMenuItem("License");
        this.add(License);

        License.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new LicenseDialog();
            }
        });
    }

}