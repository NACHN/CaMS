package com.cams.components.panels;

import javax.swing.*;
import java.awt.*;

import com.cams.*;

public class StatusPanel extends JPanel{
    JLabel In = new JLabel("进港路由：");
    public JLabel Ins = new JLabel();
    JLabel Out = new JLabel("离港路由：");
    public JLabel Outs = new JLabel();
    public StatusPanel(){
        
        JLabel mission = new JLabel("Mission status");
        Util.status=this;
        add(mission);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel C1 = new JPanel();
        C1.setLayout(new BoxLayout(C1,BoxLayout.Y_AXIS));
        
        
        C1.add(In);
        C1.add(Ins);
        
        C1.add(Out);
        C1.add(Outs);
        add(C1);
    }
    public void update(){
        
    }
}
