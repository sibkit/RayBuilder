package ray_builder.ray_building.point_handlers;

import ray_builder.ray_building.RayWayHandler;
import ray_builder.ray_building.points.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointsHandler {
    Map<Class, PointHandler> pointHandlers = new HashMap<>();

    public PointsHandler() {
        pointHandlers.put(StartPoint.class, new StartPointHandler());
        pointHandlers.put(RefractionPoint.class, new RefractionPointHandler());
        pointHandlers.put(ReflectionPoint.class, new ReflectionPointHandler());
        pointHandlers.put(EndPoint.class, new EndPointHandler());

    }

    public void validateAngels(RayPoint point) {
        PointHandler ph = pointHandlers.get(point.getClass());
        ph.validateAngles(point);
    }

    public void fill(List<RayPoint> rayWay) {
        for (int i = 0; i < 3; i++)
            for (RayPoint rp : rayWay) {
                PointHandler ph = pointHandlers.get(rp.getClass());
                ph.validateAngles(rp);
            }
        for (int i = 0; i < 3; i++)
            RayWayHandler.validateCoordinates(rayWay);
            /*
            for (RayPoint rp : rayWay) {
                PointHandler ph = pointHandlers.get(rp.getClass());
                ph.filCoordinates(rp);
            }*/
    }
}
