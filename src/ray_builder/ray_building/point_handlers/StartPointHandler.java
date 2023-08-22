package ray_builder.ray_building.point_handlers;

import ray_builder.ray_building.points.StartPoint;

public class StartPointHandler extends PointHandler<StartPoint> {

    @Override
    public Class<StartPoint> getPointClass() {
        return  StartPoint.class;
    }

    @Override
    public boolean validateAngles(StartPoint point) {
        return true;
    }

}
