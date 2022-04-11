import java.awt.*;

public class ColorChange {
    static Color brightness(Color color, int b) {
        int i = color.getGreen();
        int iMax = 255;
        if (i + b < 0) {
            return new Color(0, 0, 0);
        } else if (0 <= i + b && i + b < iMax) {
            return new Color(i + b, i + b, i + b);
        } else {
            return new Color(iMax, iMax, iMax);
        }
    }

    static Color binarize(Color color, int a) {
        int iMax = 255;
        if (color.getGreen() <= a) {
            return new Color(0, 0, 0);
        }
        return new Color(iMax,iMax,iMax);

    }
}
