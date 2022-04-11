package com.prochalska;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Map {
    Cell[][] cells;
    int width, height;

    public Map(int w, int h) {
        this.width = w;
        this.height = h;
        Random random = new Random();
        this.cells = new Cell[width][height];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (i < w / 2 && random.nextInt(10) >= 2) {
                    double[] tab = {0,0,0,0};
                    tab[random.nextInt(4)] = 1;
                    cells[i][j] = new Cell(tab);
                } else {
                    cells[i][j] = new Cell();
                }
            }
        }
        for (int i = 0; i < w; i++) {
            cells[i][0].wall = true;
            cells[i][h - 1].wall = true;
        }
        for (int j = 0; j < h; j++) {
            cells[0][j].wall = true;
            cells[w - 1][j].wall = true;
        }
        for (int j = 0; j < h / 2 - 20; j++) {
            cells[w / 2][j].wall = true;
            cells[w / 2][h - j - 1].wall = true;
        }
    }

    void simulation() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].streaming(i, j, cells);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].collision();
            }
        }
    }

    void printToIMG(BufferedImage image) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cells[i][j].wall) {
                    image.setRGB(i, j, Color.BLUE.getRGB());
                }
                else {
                    double C = 0;
                    for(int c = 0; c < 4;c++){
                        C+= cells[i][j].in[c];
                    }
                    int x = (int) (255*C);
                    if(x > 255){
                        x = 255;
                    }
                    image.setRGB(i, j, new Color(x,x,x).getRGB());
                }
            }
        }
    }
/*
    void print() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (cells[i][j].wall) {
                    System.out.print("#");
                } else if (cells[i][j].in != 0) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();

    }*/


}
