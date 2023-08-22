package ray_builder.ray_building;

import ray_builder.ray_building.points.RayPoint;

import java.util.List;

public class RayWayHandler {

    public static void validateCoordinates(RayPoint p1, RayPoint p2) {
        Line line = p1.outcome();
        if(line ==null)
            throw new RuntimeException("RayWayHandler: Ray is null");
        if(p2.income()!= line)
            throw new RuntimeException("RayWayHandler: p1.outcome != p2.income");


        if(p1.X()!=null && p2.X()!=null && line.angle()!=null) {

            double ray_len = (p2.X() - p1.X()) / Math.sin(line.angle());
            if(!Double.isNaN(ray_len)) {
                if(p1.Y()!=null && p2.Y()==null) {
                    p2.setY(p1.Y()+ray_len*Math.cos(line.angle()));
                }
                if(p2.Y()!=null && p1.Y()==null) {
                    p1.setY(p2.Y() - ray_len*Math.cos(line.angle()));
                }
            }
            else {
                if(p1.Y()!=null && p2.X()==null) {
                    p2.setX(p1.X());
                }
                if(p2.Y()!=null && p1.X()==null) {
                    p1.setX(p2.X());
                }
            }
        }
        if(p1.Y() != null && p2.Y()!=null && line.angle()!=null) {
            double ray_len = (p2.Y() - p1.Y()) / Math.cos(line.angle());
            if(!Double.isNaN(ray_len)) {
                if(p1.X()!=null && p2.X()==null) {
                    p2.setX(p1.X()+ray_len*Math.sin(line.angle()));
                } else
                if(p2.X()!=null && p1.X()==null) {
                    p1.setX(p2.X() - ray_len*Math.sin(line.angle()));
                }
            }
            else {
                if(p1.X()!=null && p2.Y()==null) {
                    p2.setY(p1.Y());
                } else
                if(p2.X()!=null && p1.Y() == null) {
                    p1.setY(p2.Y());
                }
            }

        }
    }

    public static void validateCoordinates(List<RayPoint> way) {
        if (way.size()<2)
            return;
        for(int i =1;i<way.size();i++){
            RayPoint p1 = way.get(i-1);
            RayPoint p2 = way.get(i);

            validateCoordinates(p1,p2);
            /*
            Line line = p1.outcome();
            if(line ==null)
                throw new RuntimeException("RayWayHandler: Ray is null");
            if(p2.income()!= line)
                throw new RuntimeException("RayWayHandler: p1.outcome != p2.income");


            if(p1.X()!=null && p2.X()!=null && line.angle()!=null) {

                double ray_len = (p2.X() - p1.X()) / Math.sin(line.angle());
                if(!Double.isNaN(ray_len)) {
                    if(p1.Y()!=null && p2.Y()==null) {
                        p2.setY(p1.Y()+ray_len*Math.cos(line.angle()));
                    }
                    if(p2.Y()!=null && p1.Y()==null) {
                        p1.setY(p2.Y() - ray_len*Math.cos(line.angle()));
                    }
                }
                else {
                    if(p1.Y()!=null && p2.X()==null) {
                        p2.setX(p1.X());
                    }
                    if(p2.Y()!=null && p1.X()==null) {
                        p1.setX(p2.X());
                    }
                }
            }
            if(p1.Y() != null && p2.Y()!=null && line.angle()!=null) {
                double ray_len = (p2.Y() - p1.Y()) / Math.cos(line.angle());
                if(!Double.isNaN(ray_len)) {
                    if(p1.X()!=null && p2.X()==null) {
                        p2.setX(p1.X()+ray_len*Math.sin(line.angle()));
                    } else
                    if(p2.X()!=null && p1.X()==null) {
                        p1.setX(p2.X() - ray_len*Math.sin(line.angle()));
                    }
                }
                else {
                    if(p1.X()!=null && p2.Y()==null) {
                        p2.setY(p1.Y());
                    } else
                    if(p2.X()!=null && p1.Y() == null) {
                        p1.setY(p2.Y());
                    }
                }

            }
*/
        }
    }
}
