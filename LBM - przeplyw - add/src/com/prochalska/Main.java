package com.prochalska;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        int height = 200, width = 200;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage image1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage images1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage images2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage images3 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage image3 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ImageOutputStream output = new FileImageOutputStream(new File("./gif.gif"));
        GifSequenceWriter gif = new GifSequenceWriter(output, image.getType(), 1, true);

        Map map = new Map(width, height);
        map.simulation();
        //map.print();
        for (int i = 0; i < 3000; i++) {
            if (i == 0) {
                map.printToIMG(image1);
                ImageIO.write(image1, "bmp", new File("p.bmp"));
            }
            if (i == 10) {
                map.printToIMG(images1);
                ImageIO.write(images1, "bmp", new File("s1.bmp"));
            }
            if (i == 300) {
                map.printToIMG(images2);
                ImageIO.write(images2, "bmp", new File("s2.bmp"));
            }
            if (i == 1000) {
                map.printToIMG(images3);
                ImageIO.write(images3, "bmp", new File("s3.bmp"));
            }
            if (i == 2999) {
                map.printToIMG(image3);
                ImageIO.write(image3, "bmp", new File("k.bmp"));
            }
            System.out.println(i);
            map.simulation();
            map.printToIMG(image);
            gif.writeToSequence(image);
        }
        //map.print();
        gif.close();
    }
}
