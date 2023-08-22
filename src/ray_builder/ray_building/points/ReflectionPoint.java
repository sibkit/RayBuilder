package ray_builder.ray_building.points;

import ray_builder.ray_building.Line;
import ray_builder.ray_building.ValueChangeHandler;

import java.util.ArrayList;
import java.util.List;

public class ReflectionPoint implements RayPoint {

    private Line income;
    private Line outcome;
    Double x;
    Double y;
    private Double surfaceNormal;
    List<ValueChangeHandler<RayPoint>> valueChangeHandlers = new ArrayList<>();

    private void notifyChangeValue(String field) {
        for(ValueChangeHandler handler: valueChangeHandlers())
            handler.handle(this,field);
    }

    @Override
    public Line income() {
        return income;
    }

    @Override
    public Line outcome() {
        return outcome;
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
        notifyChangeValue("X");
        return this;
    }

    @Override
    public RayPoint setY(Double y) {
        this.y = y;
        notifyChangeValue("Y");
        return this;
    }

    @Override
    public List<ValueChangeHandler<RayPoint>> valueChangeHandlers() {
        return valueChangeHandlers;
    }

    public ReflectionPoint setIncome(Line income) {
        this.income = income;
        notifyChangeValue("INCOME");
        return this;
    }

    public ReflectionPoint setOutcome(Line outcome) {
        this.outcome = outcome;
        notifyChangeValue("OUTCOME");
        return this;
    }

    public Double surfaceNormal() {
        return surfaceNormal;
    }

    public ReflectionPoint setSurfaceNormal(Double surfaceNormal) {
        this.surfaceNormal = surfaceNormal;
        notifyChangeValue("SURFACE_NORMAL");
        return this;
    }
}
