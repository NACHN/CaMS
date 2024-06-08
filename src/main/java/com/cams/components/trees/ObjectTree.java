package com.cams.components.trees;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.tree.*;

import com.cams.*;
import com.cams.activities.*;

public class ObjectTree extends JTree{
    DefaultMutableTreeNode simulation = new DefaultMutableTreeNode("Simulation");
    DefaultMutableTreeNode WaypointRoot = new DefaultMutableTreeNode("Waypoints");
    DefaultMutableTreeNode RunwayRoot = new DefaultMutableTreeNode("Runways");
    DefaultMutableTreeNode RouteRoot = new DefaultMutableTreeNode("Routes");
    DefaultMutableTreeNode[] Waypoints = new DefaultMutableTreeNode[1000];
    DefaultMutableTreeNode[] Runways = new DefaultMutableTreeNode[1000];
    DefaultMutableTreeNode[] Routes = new DefaultMutableTreeNode[1000];
    DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();

    public ObjectTree(){
        
        simulation.add(WaypointRoot);
        simulation.add(RunwayRoot);
        simulation.add(RouteRoot);
        treeModel.setRoot(simulation);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // ����Ƿ���˫���¼�
                    TreePath path = getPathForLocation(e.getX(), e.getY()); // ��ȡ˫��λ�ö�Ӧ�Ľڵ�·��
                    if (path != null) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (node.getUserObject() instanceof Waypoint) { // ���ڵ��Ӧ���û������Ƿ��� Waypoint
                            Waypoint wp = (Waypoint) node.getUserObject();
                            new SettingWaypoint(wp); // �� Waypoint ���ô���
                            
                        }
                        if (node.getUserObject() instanceof Runway) { // ���ڵ��Ӧ���û������Ƿ��� Runway
                            Runway wp = (Runway) node.getUserObject();
                            new SettingRunway(wp); // �� Runway ���ô���
                            
                        }
                        if (node.getUserObject() instanceof Route) { // ���ڵ��Ӧ���û������Ƿ��� Route
                            Route wp = (Route) node.getUserObject();
                            new SettingRoute(wp); // �� Route ���ô���
                            
                        }
                    }
                }
            }
        });
    }
    public void newWaypoint(Waypoint wp){
        Waypoints[Util.Current.WaypointCount] = new DefaultMutableTreeNode(wp.getName());
        Waypoints[Util.Current.WaypointCount].setUserObject(wp);
        WaypointRoot.add(Waypoints[Util.Current.WaypointCount]);
        treeModel.nodeStructureChanged(WaypointRoot);
        TreePath path = new TreePath(new Object[]{simulation, WaypointRoot});
        this.setExpandedState(path, true);
    }

    public void newRunway(Runway rw){
        Runways[Util.Current.RunwayCount] = new DefaultMutableTreeNode(rw.getname());
        Runways[Util.Current.RunwayCount].setUserObject(rw);
        RunwayRoot.add(Runways[Util.Current.RunwayCount]);
        treeModel.nodeStructureChanged(RunwayRoot);
        TreePath path = new TreePath(new Object[]{simulation, RunwayRoot});
        this.setExpandedState(path, true);
    }

    public void newRoute(Route rw){
        Routes[Util.Current.RouteCount] = new DefaultMutableTreeNode(rw.getname());
        Routes[Util.Current.RouteCount].setUserObject(rw);
        RouteRoot.add(Routes[Util.Current.RouteCount]);
        treeModel.nodeStructureChanged(RouteRoot);
        TreePath path = new TreePath(new Object[]{simulation, RouteRoot});
        this.setExpandedState(path, true);
    }

    public void deleteRunway(Runway rw) {
        for (int i = 0; i < Util.Current.RunwayCount; i++) {
            if (Runways[i].getUserObject() == rw) { // �ҵ�Ҫɾ���Ľڵ�
                RunwayRoot.remove(Runways[i]); // �Ӹ��ڵ����Ƴ��ýڵ�
                treeModel.nodeStructureChanged(RunwayRoot); // �������νṹ
                break;
            }
        }
    }
    public void deleteWaypoint(Waypoint rw) {
        for (int i = 0; i < Util.Current.WaypointCount; i++) {
            //System.out.println(i+":"+Util.Current.Waypoints[i]);
            if (Waypoints[i].getUserObject() == rw) { // �ҵ�Ҫɾ���Ľڵ�
                WaypointRoot.remove(Waypoints[i]); // �Ӹ��ڵ����Ƴ��ýڵ�
                treeModel.nodeStructureChanged(WaypointRoot); // �������νṹ
                break;
            }
        }
    }
    public void deleteRoute(Route rw) {
        for (int i = 0; i < Util.Current.RouteCount; i++) {
            if (Routes[i].getUserObject() == rw) { // �ҵ�Ҫɾ���Ľڵ�
                RouteRoot.remove(Routes[i]); // �Ӹ��ڵ����Ƴ��ýڵ�
                treeModel.nodeStructureChanged(RouteRoot); // �������νṹ
                break;
            }
        }
    }
}