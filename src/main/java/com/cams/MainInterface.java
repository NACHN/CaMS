package com.cams;
import javax.swing.*;

import com.cams.languages.*;

import java.awt.*;

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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        Font defaultFont = new Font("Arial", Font.PLAIN, 14); // �滻Ϊ����Ҫ������
        UIManager.put("Button.font", defaultFont); // ��ť����
        UIManager.put("Label.font", defaultFont); // ��ǩ����
        UIManager.put("TextField.font", defaultFont); // �ı�������
        UIManager.put("ComboBox.font", defaultFont); // ����������
        Font font = new Font("����", Font.PLAIN, 12);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        MainInterface Main = new MainInterface();
        Main.launch();
        new SimulationEnvironment();
    }
}