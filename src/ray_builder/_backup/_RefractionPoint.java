package ray_builder._backup;

public class _RefractionPoint {
    private Double in;
    private Double out;
    private Double surfaceNormal;
    private Double odIn;
    private Double odOut;

    public _RefractionPoint(){}


    public void complete() {
        if (odIn() == null) throw new RuntimeException("NIn not set");
        if (odOut() == null) throw new RuntimeException("NOut not set");
        if (in() == null) {
            setIn(calculateIn(surfaceNormal(), out(), odIn(), odOut()));
        }
        if (out() == null) {
            setOut(calculateOut(in(), surfaceNormal(), odIn(), odOut()));
        }
        if (surfaceNormal() ==null)
            setSurfaceNormal(calculateSurfaceNormal(in(), out(), odIn(), odOut()));
    }

    static double calculateSurfaceNormal(double in, double out, double nIn, double nOut) {
        //return Math.atan((nIn*Math.sin(in)+nOut*Math.sin(out))/(nOut*Math.cos(out)+nIn*Math.cos(in)));
        double p1 = nIn*Math.sin(in+Math.PI) - nOut*Math.sin(out);
        double p2 = nIn*Math.cos(in+Math.PI) - nOut*Math.cos(out);

        double result = Math.atan(p1/p2);
        return result;
    }

    static double calculateOut(double in, double surfaceNormal, double nIn, double nOut) {
        double p1 = Math.sin(surfaceNormal+Math.PI-in)*nIn/nOut;
        double p2 = Math.asin(p1);
        return -1*(p2-surfaceNormal-Math.PI);
    }

    static double calculateIn(double surfaceNormal, double out, double nIn, double nOut) {
        double p1 = Math.sin(surfaceNormal - out)*nOut/nIn;
        double p2 = Math.asin(p1);
        return surfaceNormal+Math.PI-p2;
    }

    public Double in() {
        return in;
    }

    public _RefractionPoint setIn(Double in) {
        this.in = in;
        return this;
    }

    public Double out() {
        return out;
    }

    public _RefractionPoint setOut(Double out) {
        this.out = out;
        return this;
    }

    public Double surfaceNormal() {
        return surfaceNormal;
    }

    public _RefractionPoint setSurfaceNormal(Double surfaceNormal) {
        this.surfaceNormal = surfaceNormal;
        return this;
    }

    public Double odIn() {
        return odIn;
    }

    public _RefractionPoint setOdIn(Double odIn) {
        this.odIn = odIn;
        return this;
    }

    public Double odOut() {
        return odOut;
    }

    public _RefractionPoint setOdOut(Double odOut) {
        this.odOut = odOut;
        return this;
    }
}
