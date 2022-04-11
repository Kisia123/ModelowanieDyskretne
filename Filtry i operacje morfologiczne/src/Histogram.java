import java.awt.*;
import java.awt.image.BufferedImage;

public class Histogram {
    static int count(int  c, BufferedImage image){
        int number = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
               Color color= new Color(image.getRGB(x,y));
                if(c == color.getGreen() ){
                    number++;
                }
            }
        }
        return number;
    }
}
