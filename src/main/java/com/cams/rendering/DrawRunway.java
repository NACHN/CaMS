package com.cams.rendering;

import java.awt.*;
import com.cams.components.panels.*;
import com.cams.*;

public class DrawRunway{
    public DrawRunway(Graphics2D g2d,MapPanel Map,Runway runway){
        double DrawingFactor = 100/(double)Util.scalar;
        double angle = runway.getAngle();
        
        
        g2d.rotate(Math.toRadians(angle));
        g2d.setColor(new Color(0,255,0,64));
        g2d.drawOval(-(int)(40000*DrawingFactor)/2,-(int)(40000*DrawingFactor)/2,(int)(40000*DrawingFactor),(int)(40000*DrawingFactor));
        g2d.setColor(Color.GREEN);
        int runwayWidth = (int)(runway.getSize().x*DrawingFactor);
        int runwayHeight = (int)(runway.getSize().y*DrawingFactor);
        if(runwayHeight==0)runwayHeight=1;
        if(runwayWidth==0)runwayWidth=2;
        g2d.fillRect(-runwayWidth / 2, -runwayHeight / 2, runwayWidth, runwayHeight);
        
        int entrypoint1=(int)(-runway.Length/2-runway.ENTRY1)*100/Util.scalar;
        int[] xPoints = {entrypoint1, entrypoint1-5, entrypoint1-5};
        int[] yPoints = {0, 5, -5};
        Polygon triangle = new Polygon(yPoints, xPoints, 3); 
        g2d.draw(triangle);
        

        int entrypoint2=(int)(runway.Length/2+runway.ENTRY2)*100/Util.scalar;
        xPoints = new int[]{entrypoint2, entrypoint2+5, entrypoint2+5};
        yPoints = new int[]{0, 5, -5};
        triangle = new Polygon(yPoints, xPoints, 3); 
        g2d.draw(triangle);

        int if1=(int)(-runway.Length/2-runway.IF1)*100/Util.scalar;
        xPoints = new int[]{if1, if1-5, if1-5};
        yPoints = new int[]{0, 5, -5};
        triangle = new Polygon(yPoints, xPoints, 3); 
        g2d.draw(triangle);

        int if2=(int)(runway.Length/2+runway.IF2)*100/Util.scalar;
        xPoints = new int[]{if2, if2+5, if2+5};
        yPoints = new int[]{0, 5, -5};
        triangle = new Polygon(yPoints, xPoints, 3); 
        g2d.draw(triangle);

        float[] dashPattern = {5, 5};
        BasicStroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0);
        g2d.setStroke(dashedStroke);
        g2d.drawLine(0,0,0,entrypoint1);
        g2d.drawLine(0,0,0,entrypoint2);
        g2d.setStroke(new BasicStroke(1));
        
        g2d.drawString(runway.nameENTRY1, 0, entrypoint1);
        g2d.drawString(runway.nameENTRY2, 0, entrypoint2);
        if(Util.scalar<=2000)
        for(int i=0;i<runway.exitCount;i++){
            g2d.drawString("Exit "+Integer.toString(i), -runwayWidth/2-64, runway.exit[i]*100/Util.scalar);
            g2d.drawLine(-runwayWidth/2-8, runway.exit[i]*100/Util.scalar, 0, runway.exit[i]*100/Util.scalar);
        }
    }
}