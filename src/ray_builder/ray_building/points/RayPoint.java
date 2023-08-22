package ray_builder.ray_building.points;

import ray_builder.ray_building.Line;
import ray_builder.ray_building.ValueChangeHandler;

import java.util.List;

public interface RayPoint {
    Line income();
    Line outcome();
    Double X();
    Double Y();
    RayPoint setX(Double x);
    RayPoint setY(Double y);
    List<ValueChangeHandler<RayPoint>> valueChangeHandlers();
}
