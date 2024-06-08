package com.cams.activities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.cams.*;

public class LicenseDialog extends JDialog{
    public LicenseDialog(){
        setTitle("Copyright information");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Setting.png")));
        Canvas LOGO = new Canvas(){
            public void paint(Graphics g) {   
                Image i=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/LOGO.png"));  
                g.drawImage(i, 10,10,this);  
                  
            }  
        };
        LOGO.setPreferredSize(new Dimension(150,200));
        JButton OK = new JButton ("Save");
        OK.addMouseListener( new MouseAdapter(){  
            @Override
            public void mouseClicked(MouseEvent e){  
                setVisible(false);
                dispose();
            }  
        });
        
        this.setLayout(new FlowLayout());
        add(LOGO);
        add( new JLabel ("Programming: Yuntao Dai."));
        add( new JLabel ("Art design: Yuntao Dai."));
        add( new JLabel ("Version: "+Util.Version));
        setSize(200,320);
        setResizable(false);
        setLocation(560,300);
        setVisible(true);
        setAlwaysOnTop(true);
    }
}