package ray_builder.ray_building;

import ray_builder.common.Geometry;

import java.util.ArrayList;
import java.util.List;

public class Line {
    //private Double x;
    //private Double y;
    private Double angle;
    private Double density;

    private List<ValueChangeHandler<Line>> valueChangeHandlers = new ArrayList<>();

    private void notifyChangeValue(String field) {
        for(ValueChangeHandler handler: valueChangeHandlers)
            handler.handle(this,field);
    }

    public Line(/*Double x, Double y, */Double angle, Double density) {
        //this.x = x;
        //this.y = y;
        if(angle!=null)
        this.angle = Geometry.validateAngle(angle);
        this.density = density;
    }
/*
    public Double X() {
        return x;
    }

    public Ray setX(Double x) {
        this.x = x;
        notifyChangeValue("X");
        return this;
    }

    public Double Y() {
        return y;
    }

    public Ray setY(Double yIn) {
        this.y = yIn;
        notifyChangeValue("Y");
        return this;
    }
*/
    public Double angle() {
        return angle;
    }

    public Line setAngle(Double angle) {
        if(angle<0)
            System.out.println("angle<0");
        this.angle = Geometry.validateAngle(angle);
        notifyChangeValue("ANGLE");
        return this;
    }

    public Double density() {
        return density;
    }

    public Line setDensity(Double density) {
        this.density = density;
        notifyChangeValue("DENSITY");
        return this;
    }

    public List<ValueChangeHandler<Line>> valueChangeHandlers() {
        return valueChangeHandlers;
    }
}
