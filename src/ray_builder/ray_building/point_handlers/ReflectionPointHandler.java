package ray_builder.ray_building.point_handlers;

import ray_builder.ray_building.points.ReflectionPoint;

public class ReflectionPointHandler extends PointHandler<ReflectionPoint> {
    @Override
    public Class<ReflectionPoint> getPointClass() {
        return ReflectionPoint.class;
    }

    //in+out-2*sn = PI
    @Override
    public boolean validateAngles(ReflectionPoint point) {
        Double in = point.income().angle();
        Double out = point.outcome().angle();
        Double sn = point.surfaceNormal();
        if (in != null && out != null && sn == null) {
            point.setSurfaceNormal((in + out - Math.PI) / 2d);
        }
        if (in != null && sn != null && out == null) {
            point.outcome().setAngle(Math.PI - in + 2 * sn);
        }
        if (out != null && sn != null && in == null) {
            point.income().setAngle(Math.PI - out + 2 * sn);
        }
        return false;
    }
}
