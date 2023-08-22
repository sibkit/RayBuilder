package ray_builder.ray_building.point_handlers;

import ray_builder.ray_building.points.EndPoint;

public class EndPointHandler extends PointHandler<EndPoint> {
    @Override
    public Class<EndPoint> getPointClass() {
        return EndPoint.class;
    }

    @Override
    public boolean validateAngles(EndPoint point) {
        return true;
    }
}
