package com.cams.components.panels;

import javax.swing.*;
import java.awt.*;

import com.cams.*;

public class StatusPanel extends JPanel{
    public JLabel mission = new JLabel("Mission status");
    JLabel In = new JLabel("进港路由：");
    public JLabel Ins = new JLabel();
    JLabel Out = new JLabel("离港路由：");
    public JLabel Outs = new JLabel();
    public StatusPanel(){
        
        
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
