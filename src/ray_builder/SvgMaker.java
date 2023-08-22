package ray_builder;

import org.jfree.svg.SVGGraphics2D;
import org.jfree.svg.SVGUtils;
import ray_builder.common.RayPicture;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SvgMaker {
    public static void makeSvg(List<RayPicture> pics, double width, double height) {
        SVGGraphics2D g = new SVGGraphics2D(width, height);
        for(RayPicture pic: pics)
            RayPainter.drawPicture(g, pic);

        try {
            SVGUtils.writeToSVG(new File("lgp-test.svg"), g.getSVGElement());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
