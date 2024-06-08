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
                if (e.getClickCount() == 2) { // 检测是否是双击事件
                    TreePath path = getPathForLocation(e.getX(), e.getY()); // 获取双击位置对应的节点路径
                    if (path != null) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (node.getUserObject() instanceof Waypoint) { // 检查节点对应的用户对象是否是 Waypoint
                            Waypoint wp = (Waypoint) node.getUserObject();
                            new SettingWaypoint(wp); // 打开 Waypoint 设置窗口
                            
                        }
                        if (node.getUserObject() instanceof Runway) { // 检查节点对应的用户对象是否是 Runway
                            Runway wp = (Runway) node.getUserObject();
                            new SettingRunway(wp); // 打开 Runway 设置窗口
                            
                        }
                        if (node.getUserObject() instanceof Route) { // 检查节点对应的用户对象是否是 Route
                            Route wp = (Route) node.getUserObject();
                            new SettingRoute(wp); // 打开 Route 设置窗口
                            
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
            if (Runways[i].getUserObject() == rw) { // 找到要删除的节点
                RunwayRoot.remove(Runways[i]); // 从根节点中移除该节点
                treeModel.nodeStructureChanged(RunwayRoot); // 更新树形结构
                break;
            }
        }
    }
    public void deleteWaypoint(Waypoint rw) {
        for (int i = 0; i < Util.Current.WaypointCount; i++) {
            //System.out.println(i+":"+Util.Current.Waypoints[i]);
            if (Waypoints[i].getUserObject() == rw) { // 找到要删除的节点
                WaypointRoot.remove(Waypoints[i]); // 从根节点中移除该节点
                treeModel.nodeStructureChanged(WaypointRoot); // 更新树形结构
                break;
            }
        }
    }
    public void deleteRoute(Route rw) {
        for (int i = 0; i < Util.Current.RouteCount; i++) {
            if (Routes[i].getUserObject() == rw) { // 找到要删除的节点
                RouteRoot.remove(Routes[i]); // 从根节点中移除该节点
                treeModel.nodeStructureChanged(RouteRoot); // 更新树形结构
                break;
            }
        }
    }
}