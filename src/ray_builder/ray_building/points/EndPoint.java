package ray_builder.ray_building.points;

import ray_builder.ray_building.Line;
import ray_builder.ray_building.ValueChangeHandler;

import java.util.ArrayList;
import java.util.List;

public class EndPoint implements RayPoint {
    private Line incomeLine;

    private Double x;
    private Double y;
    private List<ValueChangeHandler<RayPoint>> valueChangeHandlers = new ArrayList<>();

    @Override
    public Line income() {
        return incomeLine;
    }

    @Override
    public Line outcome() {
        return null;
    }

    @Override
    public Double X() {
        return x;
    }

    @Override
    public Double Y() {
        return y;
    }

    @Override
    public RayPoint setX(Double x) {
        this.x = x;
        return this;
    }

    @Override
    public RayPoint setY(Double y) {
        this.y = y;
        return this;
    }

    @Override
    public List<ValueChangeHandler<RayPoint>> valueChangeHandlers() {
        return valueChangeHandlers;
    }

    public EndPoint setIncomeRay(Line incomeLine) {
        this.incomeLine = incomeLine;
        return this;
    }
}
