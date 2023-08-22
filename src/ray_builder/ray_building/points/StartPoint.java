package ray_builder.ray_building.points;

import ray_builder.ray_building.Line;
import ray_builder.ray_building.ValueChangeHandler;

import java.util.ArrayList;
import java.util.List;

public class StartPoint implements RayPoint {
    private Line outLine;
    private List<ValueChangeHandler<RayPoint>> valueChangeHandlers = new ArrayList<>();
    private double x;
    private double y;


    public StartPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public Line income() {
        return null;
    }

    @Override
    public Line outcome() {
        return outLine;
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

    public StartPoint setOutLine(Line outLine) {
        this.outLine = outLine;
        return this;
    }
}
