package com.prochalska;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    static Random random = new Random();
    public static void main(String[] args) throws IOException {
        BufferedImage image;
        image = ImageIO.read(new File("mapa.bmp"));
        int w = image.getWidth();
        int h = image.getHeight();
        Cell[][] tab = new Cell[w][h];
        for(int x = 0; x < w;x++) {
            for (int y = 0; y < h; y++) {
                int brightness = new Color(image.getRGB(x,y)).getRed();
                if(brightness == 128){
                    tab[x][y] = new Cell(State.WATER,0,0);
                }
                else{
                    State state = brightness > 200?State.ALIVE:State.EMPTY;
                    double hum = random.nextDouble();
                    double ter = (double)brightness/255;
                    tab[x][y] = new Cell(state,hum,ter);
                }
            }
        }
        int number = 300;
        double wind = random.nextDouble()+0.5;
        BufferedImage newImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        ImageOutputStream output = new FileImageOutputStream(new File("./gif.gif"));
        GifSequenceWriter gif = new GifSequenceWriter(output, newImage.getType(),
                1, true);
        for(int n = 0; n < number; n++ ) {
            Cell[][] tmp=new Cell[w][h];
           for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int burning = 0;
                    int alive = 0;
                    for(int x = -1;x<=1;x++){
                        for(int y = -1; y <=1;y++ ){
                            if(x == 0&&y == 0){
                                continue;
                            }
                            State s =tab[(i+x+w)%w][(j+y+h)%h].state;
                            if( s == State.ALIVE){
                                alive ++;
                            }
                            if(s == State.FIRE){
                                burning++;
                            }
                        }
                    }
                    tmp[i][j] = tab[i][j].step(wind,burning,alive);
                }
            }
            tab = tmp;
            for(int i = 0; i < w;i++){
                for (int j = 0; j< h;j++){
                    State s = tab[i][j].state;
                    Color c;
                    if(s == State.ALIVE){
                        c = Color.GREEN;
                    }
                    else if( s == State.EMPTY){
                        c = Color.BLACK;
                    }
                    else if(s == State.WATER){
                        c = Color.CYAN;
                    }
                    else{
                        c = Color.RED;
                    }
                    newImage.setRGB(i,j,c.getRGB());
                }
            }
            System.out.println(n);
            gif.writeToSequence(newImage);
        }
    gif.close();

    }
}
