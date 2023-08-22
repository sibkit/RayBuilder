package ray_builder.common;

public class Geometry {
    public static double validateAngle(double angle) {
        if(angle<-1*Math.PI)
            throw new RuntimeException("Invalid angle");
        while(angle>2*Math.PI) {
            angle = angle - 2*Math.PI;
        }
        if(angle>Math.PI) {
            return -1*(2*Math.PI - angle);
        }
        return angle;
    }
}
