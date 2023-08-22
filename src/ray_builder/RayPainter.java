package ray_builder;

import ray_builder.common.RayPicture;
import ray_builder.ray_building.points.RayPoint;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class RayPainter {

    static Color getColor(int index) {
        if (index % 2 == 0) return Color.RED;
        return new Color(250, 150, 50);

    }

    public static void drawPicture(Graphics2D g, RayPicture pic) {
        if(pic.rayWays().isEmpty())
            return;
        g.setStroke(new BasicStroke(0.005f));

        for(java.util.List<RayPoint> way: pic.rayWays()) {

            RayPoint prevPoint = null;
            for(int i = 0; i<way.size();i++) {
                RayPoint rp = way.get(i);
                if(prevPoint!=null) {
                    g.setColor(getColor(i));
                    g.draw(new Line2D.Double(25d + prevPoint.X(), 25d - prevPoint.Y(), 25d + rp.X(), 25d - rp.Y()));
                }
                prevPoint = rp;
            }
        }

        g.setColor(Color.darkGray);
        for(int i = 1; i<pic.rayWays().get(0).size() - 1;i++) {
            RayPoint prevPoint = null;
            for(List<RayPoint> way: pic.rayWays()) {
                RayPoint p = way.get(i);
                if(prevPoint != null) {
                    g.draw(new Line2D.Double(25d+prevPoint.X(),25d-prevPoint.Y(), 25d+p.X(), 25d-p.Y()));
                }
                prevPoint = p;
            }
        }
    }
}
