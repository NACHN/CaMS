package com.cams.components.panels;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

import com.cams.*;
import com.cams.logic.Aircraft;
import com.cams.rendering.*;

public class MapPanel extends JPanel {
    private Point lastMousePosition = new Point(0, 0);
    private boolean isDragging = false;
    public double offX = 0;
    public double offY = 0;

    public MapPanel() {
        Util.Map = this;
        this.add(Util.tooltipLabel);
        this.addMouseListener(new MouseAdapter() {
            @Override

            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                double DrawingFactor = 100 / (double) Util.scalar;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (Util.Current != null) {
                        switch (Util.Toolstate) {
                            case 1:

                                int idw = Util.Current.WaypointCount;
                                p.setLocation(p.getX() / DrawingFactor + offX, p.getY() / DrawingFactor + offY);
                                Util.Current.Waypoints[idw] = new Waypoint(p);
                                // System.out.println("now:"+Util.Current.WaypointCount);
                                Util.Toolstate = 0;
                                break;

                            case 2:

                                int idr = Util.Current.RunwayCount;
                                p.setLocation(p.getX() / DrawingFactor + offX, p.getY() / DrawingFactor + offY);
                                Util.Current.Runways[idr] = new Runway(p);
                                // System.out.println("now:"+Util.Current.WaypointCount);
                                Util.Toolstate = 0;
                                break;

                            case 3:

                                int idt = Util.Current.RouteCount;
                                p.setLocation(p.getX() / DrawingFactor + offX, p.getY() / DrawingFactor + offY);
                                // System.out.println(p);
                                idw = Util.Current.getWaypoint(p);
                                // System.out.println(Util.NewRoute);
                                if (idw != -1) {
                                    if (Util.NewRoute) {
                                        Util.Current.Routes[idt] = new Route(idw);
                                        // System.out.println("new route:"+idw);
                                        Util.NewRoute = false;
                                    } else {
                                        Util.Current.Routes[idt].addWaypoint(idw);
                                        // System.out.println("add route:"+idw);
                                        repaint();
                                    }
                                }
                                break;

                            default:

                                break;
                        }
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(Util.Main, "Please set simulation environment first!.",
                                "Empty simulation environment", JOptionPane.WARNING_MESSAGE);
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    switch (Util.Toolstate) {
                        case 3:
                            Util.tooltipLabel.setVisible(false);
                            if (Util.Current.Routes[Util.Current.RouteCount] != null)
                                Util.Current.Routes[Util.Current.RouteCount].finish();
                            Util.NewRoute = true;
                            Util.Toolstate = 0;
                            repaint();
                            break;

                        default:
                            Util.Toolstate = 0;
                            break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && Util.Toolstate == 0) {
                    lastMousePosition = e.getPoint();
                    isDragging = true;
                }
            }

        });
        this.addMouseMotionListener(new MouseAdapter() {
            double DrawingFactor = 100 / (double) Util.scalar;

            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    Point currentMousePosition = e.getPoint();
                    offX += (currentMousePosition.x - lastMousePosition.x) / DrawingFactor;
                    offY += (currentMousePosition.y - lastMousePosition.y) / DrawingFactor;
                    lastMousePosition = currentMousePosition;
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Util.MousePosX = e.getPoint().x;
                Util.MousePosY = e.getPoint().y;
                if (Util.tooltipLabel.isVisible())
                    Util.tooltipLabel.setLocation(e.getX() + 10, e.getY() + 10);
                repaint();
            }

        });
        this.addMouseWheelListener(new MouseAdapter() {
            double DrawingFactor = 100 / (double) Util.scalar;
            Dimension size = Util.Map.getSize();

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();

                if (notches < 0 && Util.scalar > 1000) {
                    // 向上滚动，放大地图
                    Util.scalar /= 2;
                } else if (Util.scalar < 64000) {
                    // 向下滚动，缩小地图
                    Util.scalar *= 2;
                }
                // 更新地图显示的大小或者更新地图上各个元素的大小
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        double DrawingFactor = 100 / (double) Util.scalar;
        if (Util.Current != null) {
            switch (Util.Toolstate) {

                case 1:
                    g.setColor(new Color(255, 255, 255, 128));
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.translate(Util.MousePosX, Util.MousePosY);
                    g2d.fillOval(-5, -5, 10, 10);
                    g2d.dispose();
                    break;

                case 2:
                    g.setColor(new Color(0, 255, 0, 128));
                    g2d = (Graphics2D) g.create();
                    g2d.translate(Util.MousePosX, Util.MousePosY);
                    double angle = 133;

                    g2d.rotate(Math.toRadians(angle));

                    int runwayWidth = (int) (64 * DrawingFactor);
                    int runwayHeight = (int) (2800 * DrawingFactor);
                    if (runwayHeight == 0)
                        runwayHeight = 1;
                    if (runwayWidth == 0)
                        runwayWidth = 6;
                    g2d.fillRect(-runwayWidth / 2, -runwayHeight / 2, runwayWidth, runwayHeight);

                    int entrypoint1 = (int) (-2800 / 2 - 10000) * 100 / Util.scalar;
                    int[] xPoints = { entrypoint1, entrypoint1 - 5, entrypoint1 - 5 };
                    int[] yPoints = { 0, 5, -5 };
                    Polygon triangle = new Polygon(yPoints, xPoints, 3);
                    g2d.draw(triangle);

                    int entrypoint2 = (int) (2800 / 2 + 10000) * 100 / Util.scalar;
                    xPoints = new int[] { entrypoint2, entrypoint2 + 5, entrypoint2 + 5 };
                    yPoints = new int[] { 0, 5, -5 };
                    triangle = new Polygon(yPoints, xPoints, 3);
                    g2d.draw(triangle);
                    g2d.dispose();
                    break;

                case 3:
                    g.setColor(new Color(255, 255, 0, 128));
                    Route route;
                    route = Util.Current.Routes[Util.Current.RouteCount];
                    int idw1 = -1;
                    int idw2 = -1;
                    if (route != null) {
                        for (int i = 0; i < route.routelength() - 1; i++) {
                            idw1 = route.getWaypoints()[i];
                            idw2 = route.getWaypoints()[i + 1];
                            g2d = (Graphics2D) g.create();
                            Line2D.Double line = new Line2D.Double(
                                    (-offX + (int) Util.Current.Waypoints[idw1].getX()) * DrawingFactor,
                                    (-offY + (int) Util.Current.Waypoints[idw1].getY()) * DrawingFactor,
                                    (-offX + (int) Util.Current.Waypoints[idw2].getX()) * DrawingFactor,
                                    (-offY + (int) Util.Current.Waypoints[idw2].getY()) * DrawingFactor);
                            g2d.draw(line);
                        }
                        if (idw2 == -1)
                            idw2 = route.getWaypoints()[0];
                        if (Util.Current.Waypoints[idw2] != null) {
                            g2d = (Graphics2D) g.create();
                            Line2D.Double line = new Line2D.Double(
                                    (-offX + (int) Util.Current.Waypoints[idw2].getX()) * DrawingFactor,
                                    (-offY + (int) Util.Current.Waypoints[idw2].getY()) * DrawingFactor, Util.MousePosX,
                                    Util.MousePosY);
                            g2d.draw(line);
                        }
                    }
                    break;

                default:
                    break;
            }

            if (Util.Current.RunwayCount != 0)
                for (Runway runway : Util.Current.Runways) {
                    g.setColor(Color.GREEN);
                    if (runway != null) {

                        double angle = runway.getAngle(); // 获取跑道的角度

                        // 创建 Graphics2D 对象
                        Graphics2D g2d = (Graphics2D) g.create();
                        g2d.translate((-offX + (int) runway.getX()) * DrawingFactor,
                                (-offY + (int) runway.getY()) * DrawingFactor);
                        new DrawRunway(g2d, this, runway);
                        g2d.rotate(-Math.toRadians(angle));
                        g2d.drawString(runway.getSize().x + "x" + runway.getSize().y, 5, -12);
                        g2d.drawString(runway.getname(), 5, -24);
                        // 释放资源
                        g2d.dispose();
                    }
                }
            if (Util.Current.RouteCount != 0)
                for (Route route : Util.Current.Routes) {
                    g.setColor(new Color(0, 255, 255, 128));
                    if (route != null) {
                        for (int i = 0; i < route.routelength() - 1; i++) {
                            int idw1 = route.getWaypoints()[i];
                            int idw2 = route.getWaypoints()[i + 1];
                            Graphics2D g2d = (Graphics2D) g.create();
                            // Create a thicker stroke
                            Stroke oldStroke = g2d.getStroke();
                            Stroke newStroke = new BasicStroke(5); // Change the thickness as needed
                            g2d.setStroke(newStroke);

                            Line2D.Double line = new Line2D.Double(
                                    (-offX + (int) Util.Current.Waypoints[idw1].getX()) * DrawingFactor,
                                    (-offY + (int) Util.Current.Waypoints[idw1].getY()) * DrawingFactor,
                                    (-offX + (int) Util.Current.Waypoints[idw2].getX()) * DrawingFactor,
                                    (-offY + (int) Util.Current.Waypoints[idw2].getY()) * DrawingFactor);
                            g2d.draw(line);

                            // Restore the old stroke
                            g2d.setStroke(oldStroke);
                        }

                    }
                }
            if (Util.Current.WaypointCount != 0)
                for (Waypoint waypoint : Util.Current.Waypoints) {
                    g.setColor(Color.WHITE);

                    if (waypoint != null) {
                        if (waypoint.getType() == 0) {
                            Graphics2D g2d = (Graphics2D) g.create();
                            g2d.translate((-offX + (double) waypoint.getX()) * DrawingFactor,
                                    (-offY + (double) waypoint.getY()) * DrawingFactor);
                            g2d.fillOval(-5, -5, 10, 10);
                            g2d.drawString(waypoint.getName(), 5, -12);
                        }
                        if (waypoint.getType() == 1) {
                            g.setColor(new Color(0, 255, 0));
                            Graphics2D g2d = (Graphics2D) g.create();
                            g2d.translate((-offX + (double) waypoint.getX()) * DrawingFactor,
                                    (-offY + (double) waypoint.getY()) * DrawingFactor);
                            g2d.fillOval(-2, -2, 4, 4);
                            g2d.drawString("FAF", 5, -12);
                        }
                        if (waypoint.getType() == 2) {
                            g.setColor(new Color(0, 255, 0));
                            Graphics2D g2d = (Graphics2D) g.create();
                            g2d.translate((-offX + (double) waypoint.getX()) * DrawingFactor,
                                    (-offY + (double) waypoint.getY()) * DrawingFactor);
                            g2d.fillOval(-2, -2, 4, 4);
                            g2d.drawString("IF", 5, -12);
                        }
                        if (waypoint.getType() == 3) {
                            g.setColor(new Color(0, 255, 255));
                            Graphics2D g2d = (Graphics2D) g.create();
                            g2d.translate((-offX + (double) waypoint.getX()) * DrawingFactor,
                                    (-offY + (double) waypoint.getY()) * DrawingFactor);
                            g2d.fillOval(-2, -2, 4, 4);
                            g2d.drawString("IF", 5, -12);
                        }
                    }
                }
            if (Util.Current.Aircrafts != null && !Util.Current.Aircrafts.isEmpty()) {
                for (Aircraft aircraft : Util.Current.Aircrafts) {
                    if(aircraft.getstatus()==3)continue;
                    g.setColor(Color.RED);
                    //System.out.println(aircraft.getname());
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.translate((-offX + aircraft.getX()) * DrawingFactor,
                            (-offY + aircraft.getY()) * DrawingFactor);
                    
                    g2d.drawString(aircraft.getname(), 5, -60); // 显示飞机名称
                    g2d.drawString(aircraft.gettype(), 5, -48);
                    double H360=aircraft.getHeading();
                    if(H360<0)H360+=360;
                    g2d.drawString("HDG "+(int)H360, 5, -36);
                    if(aircraft.getstatus()/10==1||aircraft.getstatus()==1) g2d.drawString("IAS "+Integer.toString((int)aircraft.getSpeed()), 5, -24);
                    else g2d.drawString("TAS "+Integer.toString((int)aircraft.getSpeed()), 5, -24);
                    g2d.drawString("ALT "+Integer.toString((int)aircraft.getHeight()), 5, -12);
                    g2d.rotate(Math.toRadians(aircraft.getHeading()));
                    g2d.drawImage(Util.plane,-10,-10,20,20,null); // 绘制飞机的位置
                    g2d.dispose();
                }
            }
        }
        /**
         * Draw plotting scale
         */
        int x = 10;
        int y = getHeight() - 30;
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 100, 10);
        for (int i = 0; i <= 100; i += 10) {
            if (i % 50 == 0) {
                g.drawLine(x + i, y - 5, x + i, y + 15); // 长刻度线
                if (i / 50 * Util.scalar / 2 < 1000) {
                    g.drawString(Integer.toString(i / 50 * Util.scalar / 2) + "m", x + i - 5, y + 25); // 刻度标签
                } else {
                    g.drawString(Integer.toString(i / 50 * Util.scalar / 2000) + "km", x + i - 5, y + 25); // 刻度标签
                }
            } else {
                g.drawLine(x + i, y, x + i, y + 10); // 短刻度线
            }
        }

    }

}
