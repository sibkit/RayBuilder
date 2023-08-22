package ray_builder.ray_building.point_handlers;

import ray_builder.ray_building.points.RayPoint;

public abstract class PointHandler<T extends RayPoint> {
    public abstract Class<T> getPointClass();
    public abstract boolean validateAngles(T point);
}
