package ray_builder.ray_building.points;

import ray_builder.ray_building.Line;
import ray_builder.ray_building.ValueChangeHandler;

import java.util.ArrayList;
import java.util.List;

public class RefractionPoint implements RayPoint {
    private Line incomeLine;
    private Line refractedLine;
    private Double surfaceNormal;
    private Double x;
    private Double y;
    private List<ValueChangeHandler<RayPoint>> valueChangeHandlers = new ArrayList<>();

    public RefractionPoint setInnerRay(Line innerLine) {
        this.incomeLine = innerLine;
        notifyChangeValue("INNER_RAY");
        return this;
    }

    public RefractionPoint setRefractedRay(Line refractedLine) {
        this.refractedLine = refractedLine;
        notifyChangeValue("REFRACTED_RAY");
        return this;
    }



    private void notifyChangeValue(String field) {
        for(ValueChangeHandler handler: valueChangeHandlers())
            handler.handle(this,field);
    }

    @Override
    public Line income() {
        return incomeLine;
    }

    @Override
    public Line outcome() {
        return refractedLine;
    }

    @Override
    public Double X() {
        return x;
    }

    @Override
    public Double Y() {
        return y;
    }

    public Double surfaceNormal() {
        return surfaceNormal;
    }

    public RefractionPoint setSurfaceNormal(Double surfaceNormal) {
        this.surfaceNormal = surfaceNormal;
        notifyChangeValue("SURFACE_NORMAL");
        return this;
    }

    public List<ValueChangeHandler<RayPoint>> valueChangeHandlers() {
        return valueChangeHandlers;
    }

    public RefractionPoint setX(Double x) {
        if(x!=null && x<0)
            System.out.println("x<0");
        this.x = x;
        notifyChangeValue("X");
        return this;
    }

    public RefractionPoint setY(Double y) {
        if(y!=null && y<0)
            System.out.println("y<0");
        this.y = y;
        notifyChangeValue("Y");
        return this;
    }
}
