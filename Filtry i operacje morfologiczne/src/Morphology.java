import java.awt.*;
import java.awt.image.BufferedImage;

public class Morphology {
    static Color dilatation(int x, int y, BufferedImage img) {
        Color n;
        Color w  = new Color(255,255,255);
        Color b = new Color(0,0,0);
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if(i==x&&j == y){
                    continue;
                }
                int a = 200;
                n = ColorChange.binarize(new Color(img.getRGB(i,j)),a);
                if(n.getGreen()>a){
                    return w;
                }
            }
        }
        return b;
    }
    static Color erosion(int x, int y, BufferedImage img) {
        Color n;
        Color w  = new Color(255,255,255);
        Color b = new Color(0,0,0);
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if(i==x&&j == y){
                    continue;
                }
                int a = 220;
                n = ColorChange.binarize(new Color(img.getRGB(i,j)),a);
                if(n.getGreen()<=a){
                    return b;
                }
            }
        }
        return w;
    }

}

