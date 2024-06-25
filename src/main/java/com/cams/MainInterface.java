package com.cams;
import javax.swing.*;

import com.cams.languages.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import java.io.*;


public class MainInterface extends JFrame{
    int WindowWidth=1280;
    int WindowHeight=720;
    void launch(){
        this.setLocationRelativeTo(null);
        this.setTitle("CaMS(Capacity Monte-Carlo Simulator) v"+Util.Version);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/CAMS_S.png")));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WindowWidth, WindowHeight);
        this.setLocation(100,100);
        MainComponent Main=new MainComponent(this);
        this.setVisible(true);
        Util.Main=Main;
    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String NowDateTime = LocalDateTime.now().format(formatter);
            Util.outs = new FileOutputStream("Out_"+NowDateTime+".csv",true);
            PrintStream printStream = new PrintStream(Util.outs);
            System.setOut(printStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Font defaultFont = new Font("Arial", Font.PLAIN, 14); // 替换为你想要的字体
        UIManager.put("Button.font", defaultFont); // 按钮字体
        UIManager.put("Label.font", defaultFont); // 标签字体
        UIManager.put("TextField.font", defaultFont); // 文本框字体
        UIManager.put("ComboBox.font", defaultFont); // 下拉框字体
        Font font = new Font("黑体", Font.PLAIN, 12);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        MainInterface Main = new MainInterface();
        Main.launch();
        new SimulationEnvironment();
    }
}