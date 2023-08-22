package ray_builder.ray_building;

import ray_builder.lens_builders.F40LensBuilder;

public class Main {
    public static void main(String[] args) {
        double n = 1.59;

        F40LensBuilder flb = new F40LensBuilder();
        flb.buildLens(12d, 10d);

/*
        List<RayPoint> rayWay = new ArrayList<>();
        StartPoint sp = new StartPoint();
        sp.setOutRay(new Ray(0d,0d, Math.toRadians(5),1d));

        RefractionPoint rp1 = new RefractionPoint();
        rp1.setInnerRay(sp.outcome());
        rp1.setRefractedRay(new Ray(null,5d,null,2d));

        RefractionPoint rp2 = new RefractionPoint();
        rp2.setInnerRay(rp1.outcome());
        rp2.setSurfaceNormal(0d);
        rp2.setRefractedRay(new Ray(10d,null,Math.toRadians(5), 1d));

        rayWay.add(sp);
        rayWay.add(rp1);
        rayWay.add(rp2);

        PointsHandler psh = new PointsHandler();
        psh.fillAngels(rayWay);

        double r = Math.toDegrees(Math.asin(0.25));*/
/*
        double income = Math.toRadians(190);
        double outcome = Math.toRadians(15);
        double surf = Math.toDegrees(Refraction.calculateSurfaceNormal(income, outcome,1,1.4904794774118173697977));
*/
        /*
        double income = Math.toRadians(239);
        double outcome = Math.toRadians(54);
        double surf = Math.toDegrees(Refraction.calculateSurfaceNormal(income, outcome,1.4904794774118173697977,1));
*/




        double r1 = 5;
        double r3 = r1/2.67;
/*
        ray_builder._backup._RefractionPoint rf1 = new ray_builder._backup._RefractionPoint();
        ray_builder._backup._RefractionPoint rf2 = new ray_builder._backup._RefractionPoint();

        rf1.setOdIn(1.0).setOdOut(1.59);
        rf2.setOdIn(1.59).setOdOut(1.0);

        rf1.setIn(Math.toRadians(5d));
        rf2.setOut(Math.toRadians(5d/2.67));
        rf2.setSurfaceNormal(0d);
        rf2.complete();
        rf1.setOut(rf2.in());
        rf1.complete();
        System.out.println("surface normal: "+Math.toDegrees(rf1.surfaceNormal()));
*/

/*
        raybuilding.points.RayPoint rp1 = new raybuilding.points.RayPoint();
        raybuilding.points.RayPoint rp2 = new raybuilding.points.RayPoint();



        double rp2GA = rp1.incomeRayAngle/2.67; //120/45
        rp2.outcomeN = 1;
        rp2.incomeN = 1.59;

        rp1.outcomeN = 1.59;
        rp1.incomeN = 1;
        rp2.incomeRayAngle = Math.asin(Math.sin(rp2.outcomeRayAngle)*rp2.outcomeN/rp2.incomeN);
        rp1.outcomeRayAngle = rp2.incomeRayAngle;
        System.out.println(rp1.calculateSurfaceAngle());
        //rp2.incomeRayAngle = rp2.outcomeRayAngle;
        //Math.sin(Math.toRadians())*/
    }
}
