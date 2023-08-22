package ray_builder._backup;

/**
 * Все углы отсчитываются против часовой стрелки от вертикальной оси
 */

public class _RayPoint_v1 {

    double outcomeRayAngle;
    double incomeN;
    double outcomeN;
    double surfaceAngle;



    private _RayPoint_v1(){}

    static _RayPoint_v1 fromOutcome(double inN, double outN, double outcomeAngle) {
        _RayPoint_v1 rp = new _RayPoint_v1();
        rp.incomeN = inN;
        rp.outcomeN = outN;
        rp.outcomeRayAngle = outcomeAngle;
        rp.surfaceAngle = rp.calculateSurfaceAngle();
        return rp;
    }

    private double calculateSurfaceAngle() {

        double minOutcomeDelta = Double.MAX_VALUE;
        double surfAngle = -Math.PI/2;

        for(double curSurfNormalAngle = -Math.PI/2; curSurfNormalAngle < Math.PI/2; curSurfNormalAngle+=Math.PI/1800) {

            double incidence = curSurfNormalAngle;
            double refraction = Math.asin(Math.sin(incidence)*incomeN/outcomeN);

            double outcomeDelta = Math.abs(refraction - outcomeRayAngle);
            if(outcomeDelta<minOutcomeDelta) {
                minOutcomeDelta = outcomeDelta;
                surfAngle = curSurfNormalAngle;
            }
        }
        return Math.toDegrees(surfAngle);
    }

    private double calculateOutcome() {
        double incidence = this.surfaceAngle;
        double refraction = Math.asin(Math.sin(incidence)*incomeN/outcomeN);
        return incidence + refraction;
    }

    static _RayPoint_v1 fromSurface(double inN, double outN, double surfNormAngle) {
        _RayPoint_v1 rp = new _RayPoint_v1();
        rp.incomeN = inN;
        rp.outcomeN = outN;
        rp.surfaceAngle = surfNormAngle;
        rp.outcomeRayAngle = rp.calculateOutcome();
        return rp;
    }


}
