import java.awt.*;
import java.awt.image.BufferedImage;

public  class Convulsion {
    static Color mask(int x, int y, BufferedImage img){
        int k = 0;
        int b = 0;
        for(int i = x-1; i<=x+1; i++){
            for(int j = y-1; j<= y+1;j++){
                if(i==x&&j==y){
                    continue;
                }
                Color color = new Color(img.getRGB(i,j));
                b += color.getGreen()*Filters.lowerFilter(k);
                k++;
            }
        }
        b/=52;
        if(b<0){
            b = 0;

        }
        if(b >255){
            b = 255;
        }

        Color g = new Color(b,b,b);
        return g;
    }

}
