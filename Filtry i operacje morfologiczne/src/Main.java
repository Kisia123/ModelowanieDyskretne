import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BufferedImage image;
        Scanner in = new Scanner(System.in);
        try {
            image = ImageIO.read(new File("mapa.bmp"));
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }
        System.out.println(new Color(image.getRGB(0, 0)));
        int w = image.getWidth(), h = image.getHeight();
        BufferedImage brightnessChangedImage = new BufferedImage(w, h, image.getType());
        BufferedImage binarizedImage = new BufferedImage(w, h, image.getType());
        BufferedImage convImage = new BufferedImage(w,h,image.getType());
        BufferedImage dilImage = new BufferedImage(w,h,image.getType());
        BufferedImage erImage = new BufferedImage(w,h,image.getType());
        //DILATATION

        for(int x = 0; x< w;x++){
            for(int y = 0; y<h; y++){
                if(x==0||y==0||x==w-1||y==h-1){
                    dilImage.setRGB(x,y,image.getRGB(x,y));
                    continue;
                }
                Color n = Morphology.dilatation(x,y,image);
                dilImage.setRGB(x,y,n.getRGB());
            }
        }
        try {
            File file = new File("dilImg.bmp");
            ImageIO.write(dilImage, "bmp", file);
        } catch (IOException e) {

        }

        //EROSION
        for(int x = 0; x< w;x++){
            for(int y = 0; y<h; y++){
                if(x==0||y==0||x==w-1||y==h-1){
                    erImage.setRGB(x,y,image.getRGB(x,y));
                    continue;
                }
                Color n = Morphology.erosion(x,y,image);
                erImage.setRGB(x,y,n.getRGB());
            }
        }
        try {
            File file = new File("erImg.bmp");
            ImageIO.write(erImage, "bmp", file);
        } catch (IOException e) {

        }



        //Convulsion
        for(int x = 1; x< w-1;x++){
            for(int y = 1; y<h-1; y++){
                Color g = Convulsion.mask(x,y,image);
                convImage.setRGB(x,y,g.getRGB());
            }
        }
        try {
            File file = new File("convImg.bmp");
            ImageIO.write(convImage, "bmp", file);
        } catch (IOException e) {

        }


        //BRIGHTNESS
        System.out.println("BRIGHTNESS\nChoose a number from -255 to 255");

        int b = in.nextInt();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                Color c = ColorChange.brightness(new Color(image.getRGB(x, y)), b);
                brightnessChangedImage.setRGB(x, y, c.getRGB());
            }
        }
        try {
            File file = new File("briImg.bmp");
            ImageIO.write(brightnessChangedImage, "bmp", file);
        } catch (IOException e) {

        }
        System.out.println(new Color(brightnessChangedImage.getRGB(0, 0)));

        //BINARIZATION
        System.out.println("BINARIZATION\nChoose a number from 0 to 255");
        int a = in.nextInt();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                Color c = ColorChange.binarize(new Color(image.getRGB(x, y)), a);
                binarizedImage.setRGB(x, y, c.getRGB());
            }
        }
        try {
            File file = new File("binImg.bmp");
            ImageIO.write(binarizedImage, "bmp", file);
        } catch (IOException e) {

        }
        System.out.println(new Color(binarizedImage.getRGB(0, 0)));



        //HISTOGRAM

        int max = 0;
        for (int i = 0; i <= 255; i++) {
            int hist = Histogram.count(i, image);
          //  System.out.println(String.valueOf(hist));
            if(hist>max) {
                max = Histogram.count(i, image);
            }
        }
        BufferedImage histImage = new BufferedImage(255,100,image.getType());
        Graphics2D hist = histImage.createGraphics();
        hist.fillRect(0,0,histImage.getWidth(),histImage.getHeight());
        hist.setStroke((Stroke) Color.BLUE);
        BasicStroke bs = new BasicStroke(1);
        hist.setStroke(bs);
        for(int i = 0; i<=255; i++){
            int g= Histogram.count(i, image);
            hist.drawLine(i,100,i,100-100*g/max);
        }
        try {
            File file = new File("histImg.bmp");
            ImageIO.write(histImage, "bmp", file);
        } catch (IOException e) {

        }

        in.close();
    }
}


