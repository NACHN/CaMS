package com.cams.components.panels;

import javax.swing.*;
import java.awt.*;

import com.cams.*;

public class StatusPanel extends JPanel{
    public JLabel mission = new JLabel("Mission status");
    JLabel H = new JLabel("重型机：");
    public JLabel Heavys = new JLabel("A345, A388, B744, B788");
    JLabel M = new JLabel("中型机：");
    public JLabel Meduims = new JLabel("A20N, A320, B38M, B738");
    JLabel L = new JLabel("轻型机：");
    public JLabel Lights = new JLabel("AC11, AC95, PA32");
    JLabel In = new JLabel("进港路由：");
    public JLabel Ins = new JLabel();
    JLabel Out = new JLabel("离港路由：");
    public JLabel Outs = new JLabel();
    
    public StatusPanel(){
        
        
        Util.status=this;
        add(mission);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel C = new JPanel();
        C.setLayout(new BoxLayout(C,BoxLayout.Y_AXIS));
        C.add(H);
        C.add(Heavys);
        C.add(M);
        C.add(Meduims);
        C.add(L);
        C.add(Lights);
        JPanel C1 = new JPanel();
        C1.setLayout(new BoxLayout(C1,BoxLayout.Y_AXIS));
        
        
        C1.add(In);
        C1.add(Ins);
        
        C1.add(Out);
        C1.add(Outs);
        add(C);
        add(C1);
    }
    public void update(){
        
    }
}
