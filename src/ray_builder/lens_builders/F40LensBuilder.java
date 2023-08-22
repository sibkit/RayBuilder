package ray_builder.lens_builders;

import ray_builder.SvgMaker;
import ray_builder.common.RayPicture;
import ray_builder.ray_building.Line;
import ray_builder.ray_building.RayWayHandler;
import ray_builder.ray_building.ValueChangeHandler;
import ray_builder.ray_building.point_handlers.PointsHandler;
import ray_builder.ray_building.points.*;

import java.util.ArrayList;
import java.util.List;

public class F40LensBuilder {

    double lensRadius = 3.2;
    double lensHeight;
    double lensDensity = 1.59;
    double topLensInputHeight = 3.0; //height of point under lens
    double innerRadius = 3.0;
    double firstPartAngle = Math.toRadians(42);
    double ledAngle = Math.toRadians(120);
    double lensAngle = Math.toRadians(45);


    public void buildLens(double radius, double height) {
        this.lensRadius = radius;
        this.lensHeight = height;



        List<RayPicture> pics = new ArrayList<>();
        pics.add(buildFirstPart());
        pics.add(buildSecondPart());
        SvgMaker.makeSvg(pics, 50, 50);
    }

    List<RayPoint> buildFirstPartRayWay(double startAngle, List<RayPoint> prevRayWay) {
        double ak = ledAngle / lensAngle;
        List<RayPoint> rayWay = new ArrayList<>();
        StartPoint sp = new StartPoint(0d, 0d);
        sp.setOutLine(new Line(startAngle, 1d));
        RefractionPoint rp1 = new RefractionPoint();
        rp1.setInnerRay(sp.outcome());

        Double y = null;
        if(prevRayWay==null)
            y = topLensInputHeight;

        rp1.setX(null);
        rp1.setY(y);
        rp1.setRefractedRay(new Line( null, lensDensity));

        rp1.valueChangeHandlers().add(new ValueChangeHandler<RayPoint>() {
            @Override
            public void handle(RayPoint obj, String field) {
                if(field=="SURFACE_NORMAL") {
                    if(prevRayWay == null)
                        return;

                    RefractionPoint rp = (RefractionPoint)obj;
                    RefractionPoint rpPrev = (RefractionPoint)prevRayWay.get(1);

                    double sn_1 = rpPrev.surfaceNormal();
                    double sn_2 = rp.surfaceNormal();

                    double in_1 = rpPrev.income().angle();
                    double in_2 = startAngle;


                    double snt_1 = Math.abs(sn_2)-Math.abs(sn_1);
                    double snt_2 = (Math.PI - snt_1)/2d;

                    double int_2 = 2d*Math.PI - snt_2 - (Math.PI - Math.abs(in_1) - Math.abs(sn_1));
                    //double a = sn_1-in_1;
                    double int_1 = Math.abs(in_2 - in_1);
                    //double int_2 = Geometry.validateAngle(2d*Math.PI - (Math.PI-(sn_1 - sn_2))/2d - (sn_1 - in_1));
                    double int_3 = Math.abs(Math.PI - int_1 - int_2);

                    //double int_3_l =
                    double int_3_l = Math.sqrt(Math.pow(rpPrev.X(),2) + Math.pow(rpPrev.Y(),2));

                    // int_3_l/sin(int_3) = int_2_l/sin(int_2); int_2_l = int_3_l*sin(int_2)/sin(int_3)
                    double int_2_l = int_3_l*Math.abs(Math.sin(int_2)/Math.sin(int_3));
                    double x2 = Math.sin(startAngle)*int_2_l;
                    double y2 = Math.cos(startAngle)*int_2_l;
                    obj.setX(x2);
                    obj.setY(y2);

                }
            }
        });

        RefractionPoint rp2 = new RefractionPoint();
        rp2.setInnerRay(rp1.outcome());
        rp2.setSurfaceNormal(0d);
        rp2.setX(null);
        rp2.setY(lensHeight);
        rp2.setRefractedRay(new Line(startAngle / ak, 1d));

        EndPoint ep = new EndPoint();
        ep.setIncomeRay(rp2.outcome());
        ep.setY(20d);

        rayWay.add(sp);
        rayWay.add(rp1);
        rayWay.add(rp2);
        rayWay.add(ep);

        PointsHandler psh = new PointsHandler();
        psh.fill(rayWay);
        return rayWay;
    }

    List<RayPoint> buildSecondPartRayWay(double startAngle, List<RayPoint> prevRayWay) {
        PointsHandler psh = new PointsHandler();

        List<RayPoint> rayWay = new ArrayList<>();
        double ak = ledAngle / lensAngle;

        StartPoint sp = new StartPoint(0d, 0d);
        sp.setOutLine(new Line(startAngle, 1d));
        RefractionPoint rrp1 = new RefractionPoint();
        rrp1.setInnerRay(sp.outcome());
        rrp1.setSurfaceNormal(Math.PI/2d);
        rrp1.setX(innerRadius);
        rrp1.setRefractedRay(new Line(null, lensDensity));

        psh.validateAngels(rrp1);
        RayWayHandler.validateCoordinates(sp,rrp1);

        ReflectionPoint rlp = new ReflectionPoint();
        rlp.setIncome(rrp1.outcome());
        rlp.setOutcome(new Line(null,lensDensity));


        RefractionPoint rrp2 = new RefractionPoint();
        rrp2.setY(10d);
        rrp2.setSurfaceNormal(0d);
        rrp2.setInnerRay(rlp.outcome());
        rrp2.setRefractedRay(new Line(startAngle/ak,1d));

        psh.validateAngels(rrp2);
        psh.validateAngels(rlp);

        EndPoint ep = new EndPoint();
        ep.setY(20d);
        ep.setIncomeRay(rrp2.outcome());

        if(prevRayWay==null) {
            rlp.setX(lensRadius);
        } else {
            ReflectionPoint rlp_p = (ReflectionPoint) prevRayWay.get(2);

            double sn = rlp.surfaceNormal();
            double snp = rlp_p.surfaceNormal();

            double betta = (Math.PI - sn + snp)/2d;


            double si = Math.sin(rlp.income().angle());
            double ci = Math.cos(rlp.income().angle());
            double sb = Math.sin(snp - betta);
            double cb = Math.cos(snp - betta);

            double xp = rlp_p.X();
            double yp = rlp_p.Y();
            double x0 = rrp1.X();
            double y0 = rrp1.Y();


            double x = ((yp - y0)*(si/ci) - xp*(cb/sb)*(si/ci) + x0)/(1d - (cb/sb)*(si/ci));

            //double x = ((ca/sa)*yp - (cb/sb)*(ca/sa)*xp + x0 - y0)/(1-(cb/sb)*(ca/sa));
            double y = y0 + (x - x0) * ci/si;
            rlp.setX(x);
            rlp.setY(y);

        }

        //rlp.setSurfaceNormal(-1d*Math.toRadians(60));
        //rlp.setX(20d);


        rayWay.add(sp);
        rayWay.add(rrp1);
        rayWay.add(rlp);
        rayWay.add(rrp2);
        rayWay.add(ep);


        psh.fill(rayWay);
        return rayWay;
    }

    RayPicture buildFirstPart() {
        RayPicture picture1 = new RayPicture();
        List<RayPoint> rayWay1 = null;
        for(double dg = 0d;dg<firstPartAngle; dg+=Math.toRadians(0.5)) {
            rayWay1 = buildFirstPartRayWay(dg,rayWay1);
            picture1.rayWays().add(rayWay1);
        }
        return picture1;
    }

    RayPicture buildSecondPart() {
        RayPicture picture = new RayPicture();
        List<RayPoint> rayWay = null;
        for(double dg = firstPartAngle; dg<Math.PI/2d; dg += Math.toRadians(0.5)) {
            rayWay = buildSecondPartRayWay(dg,rayWay);
            picture.rayWays().add(rayWay);
        }
        return picture;
    }
}
