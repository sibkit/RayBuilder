package ray_builder.ray_building.point_handlers;

import ray_builder.common.Geometry;
import ray_builder.ray_building.points.RefractionPoint;

public class RefractionPointHandler extends PointHandler<RefractionPoint> {
    @Override
    public Class<RefractionPoint> getPointClass() {
        return RefractionPoint.class;
    }

    @Override
    public boolean validateAngles(RefractionPoint point) {

        double d_in = point.income().density();
        double d_out = point.outcome().density();
        if(point.surfaceNormal()==null) {
            if(point.income().angle()!=null && point.outcome().angle()!=null && point.surfaceNormal() == null) {
                double in = Geometry.validateAngle(point.income().angle());
                double out = Geometry.validateAngle(point.outcome().angle());

                double tg_sn = (d_out * Math.sin(out) - d_in * Math.sin(in)) / (d_out * Math.cos(out) - d_in * Math.cos(in));
                double sn = Math.atan(tg_sn);
                //double sin_sn = d_in * Math.sin(in - Math.PI / 2f) - d_out * Math.sin(out);
                //double cos_sn = Math.cos(in - Math.PI / 2d) * d_in - Math.cos(out) * d_out;
                point.setSurfaceNormal(sn);

            }
        } else {
            double sn = point.surfaceNormal();
            if(point.income().angle()!=null && point.outcome().angle()==null)
            {
                double in = point.income().angle();

                double alpha = in - sn;
                //double betta = Math.PI + sn - out;
                double out = sn + Math.asin(Math.sin(alpha)*d_in/d_out);

                /*
                double u_in = Math.PI/2d - sn - (Math.PI - in);
                double u_out = Math.asin(Math.sin(u_in)*d_in/d_out);
                */

                point.outcome().setAngle(out);
            }
            if(point.outcome().angle()!=null && point.income().angle()==null)
            {
                double out = point.outcome().angle();
                double betta = out-sn;

                double in = sn + Math.asin(Math.sin(betta)*d_out/d_in);
                point.income().setAngle(in);
                /*
                double out = point.outcome().angle();
                double sin_u_in = (d_out/d_in)*Math.sin(out - sn);
                double u_in = Math.asin(sin_u_in);

                 */
                //u_in = pi/2 - sn - (pi - in)
                //point.income().setAngle(u_in - Math.PI/2.0d + sn + Math.PI);
                //point.income().setAngle(u_in + sn);
            }
        }

        return false;
    }


}
