package com.prochalska;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {
    Cell[][] cells;
    int width, height;

    public Map(int w, int h) {
        this.width = w;
        this.height = h;
        this.cells = new Cell[width][height];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (i == 0 || i == w - 1 || j == 0 || j == h - 1) {
                    cells[i][j] = new Cell((1.0 * j / (h - 1)) * 0.05);
                } else {
                    cells[i][j] = new Cell(0);
                }
            }
        }
    }

    void simulation() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double tau = 1;
                cells[i][j].rho = cells[i][j].in[dir.C.val] + cells[i][j].in[dir.E.val] + cells[i][j].in[dir.W.val] + cells[i][j].in[dir.S.val] + cells[i][j].in[dir.N.val] +
                        cells[i][j].in[dir.NE.val] + cells[i][j].in[dir.NW.val] + cells[i][j].in[dir.SE.val] + cells[i][j].in[dir.SW.val];
                cells[i][j].vx = (cells[i][j].in[dir.E.val] + cells[i][j].in[dir.SE.val] + cells[i][j].in[dir.NE.val] - cells[i][j].in[dir.W.val] - cells[i][j].in[dir.SW.val] -
                        cells[i][j].in[dir.NW.val]) / cells[i][j].rho;
                cells[i][j].vy = (cells[i][j].in[dir.N.val] + cells[i][j].in[dir.NW.val] + cells[i][j].in[dir.NE.val] - cells[i][j].in[dir.S.val] - cells[i][j].in[dir.SW.val] -
                        cells[i][j].in[dir.SE.val]) / cells[i][j].rho;
                //rownowaga
                double vx2 = cells[i][j].vx * cells[i][j].vx;
                double vy2 = cells[i][j].vy * cells[i][j].vy;
                double eu2 = 1.f - 1.5f * (vx2 + vy2);
                double Rho36 = cells[i][j].rho / 36.0f;
                cells[i][j].eq[dir.C.val] = 16.0f * Rho36 * eu2;
                cells[i][j].eq[dir.N.val] = 4.0f * Rho36 * (eu2 + 3.0f * cells[i][j].vy + 4.5f * vy2);
                cells[i][j].eq[dir.E.val] = 4.0f * Rho36 * (eu2 + 3.0f * cells[i][j].vx + 4.5f * vx2);
                cells[i][j].eq[dir.S.val] = 4.0f * Rho36 * (eu2 - 3.0f * cells[i][j].vy + 4.5f * vy2);
                cells[i][j].eq[dir.W.val] = 4.0f * Rho36 * (eu2 - 3.0f * cells[i][j].vx + 4.5f * vx2);
                cells[i][j].eq[dir.NE.val] = Rho36 * (eu2 + 3.0f * (cells[i][j].vx + cells[i][j].vy) +
                        4.5f * (cells[i][j].vx + cells[i][j].vy) * (cells[i][j].vx + cells[i][j].vy));
                cells[i][j].eq[dir.SE.val] = Rho36 * (eu2 + 3.0f * (cells[i][j].vx - cells[i][j].vy) +
                        4.5f * (cells[i][j].vx - cells[i][j].vy) * (cells[i][j].vx - cells[i][j].vy));
                cells[i][j].eq[dir.SW.val] = Rho36 * (eu2 + 3.0f * (-cells[i][j].vx - cells[i][j].vy) +
                        4.5f * (cells[i][j].vx + cells[i][j].vy) * (cells[i][j].vx + cells[i][j].vy));
                cells[i][j].eq[dir.NW.val] = Rho36 * (eu2 + 3.0f * (-cells[i][j].vx + cells[i][j].vy) +
                        4.5f * (-cells[i][j].vx + cells[i][j].vy) * (-cells[i][j].vx + cells[i][j].vy));
                //relaksacja
                cells[i][j].out[dir.C.val] = cells[i][j].in[dir.C.val] + (cells[i][j].eq[dir.C.val] - cells[i][j].in[dir.C.val]) / tau;
                cells[i][j].out[dir.E.val] = cells[i][j].in[dir.E.val] + (cells[i][j].eq[dir.E.val] - cells[i][j].in[dir.E.val]) / tau;
                cells[i][j].out[dir.W.val] = cells[i][j].in[dir.W.val] + (cells[i][j].eq[dir.W.val] - cells[i][j].in[dir.W.val]) / tau;
                cells[i][j].out[dir.S.val] = cells[i][j].in[dir.S.val] + (cells[i][j].eq[dir.S.val] - cells[i][j].in[dir.S.val]) / tau;
                cells[i][j].out[dir.N.val] = cells[i][j].in[dir.N.val] + (cells[i][j].eq[dir.N.val] - cells[i][j].in[dir.N.val]) / tau;
                cells[i][j].out[dir.SE.val] = cells[i][j].in[dir.SE.val] + (cells[i][j].eq[dir.SE.val] - cells[i][j].in[dir.SE.val]) / tau;
                cells[i][j].out[dir.NE.val] = cells[i][j].in[dir.NE.val] + (cells[i][j].eq[dir.NE.val] - cells[i][j].in[dir.NE.val]) / tau;
                cells[i][j].out[dir.SW.val] = cells[i][j].in[dir.SW.val] + (cells[i][j].eq[dir.SW.val] - cells[i][j].in[dir.SW.val]) / tau;
                cells[i][j].out[dir.NW.val] = cells[i][j].in[dir.NW.val] + (cells[i][j].eq[dir.NW.val] - cells[i][j].in[dir.NW.val]) / tau;
            }

        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].in[dir.C.val] = cells[i][j].out[dir.C.val];
                if (i < width - 1) // EW
                {
                    cells[i][j].in[dir.W.val] = cells[i + 1][j].out[dir.W.val];
                    cells[i + 1][j].in[dir.E.val] = cells[i][j].out[dir.E.val];
                }
                if (j < height - 1) // NS
                {
                    cells[i][j].in[dir.S.val] = cells[i][j + 1].out[dir.S.val];
                    cells[i][j + 1].in[dir.N.val] = cells[i][j].out[dir.N.val];
                }
                if (i < width - 1 && j < height - 1) //D1
                {
                    cells[i][j].in[dir.SW.val] = cells[i + 1][j + 1].out[dir.SW.val];
                    cells[i + 1][j + 1].in[dir.NE.val] = cells[i][j].out[dir.NE.val];
                }
                if (i > 0 && j < height - 1) // D2
                {
                    cells[i][j].in[dir.SE.val] = cells[i - 1][j + 1].out[dir.SE.val];
                    cells[i - 1][j + 1].in[dir.NW.val] = cells[i][j].out[dir.NW.val];
                }

                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    cells[i][j].in[dir.E.val] = cells[i][j].out[dir.E.val];
                    cells[i][j].in[dir.NE.val] = cells[i][j].out[dir.NE.val];
                    cells[i][j].in[dir.SE.val] = cells[i][j].out[dir.SE.val];
                    cells[i][j].in[dir.W.val] = cells[i][j].out[dir.W.val];
                    cells[i][j].in[dir.SW.val] = cells[i][j].out[dir.SW.val];
                    cells[i][j].in[dir.NW.val] = cells[i][j].out[dir.NW.val];
                    cells[i][j].in[dir.N.val] = cells[i][j].out[dir.N.val];
                    cells[i][j].in[dir.S.val] = cells[i][j].out[dir.S.val];
                }
            }
        }

    }

    void printToIMG(BufferedImage image) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                float red, blue, green;
                red = blue = green = 1f;
                if (cells[i][j].vx < -0.0005) {
                    red = (float) (1 + 20f * cells[i][j].vx);
                    blue = 1f;
                    green = red;
                }
                if (cells[i][j].vx > 0.0005) {
                    blue = (float) (1 - 20f * cells[i][j].vx);
                    red = 1f;
                    green = blue;
                }
                if (red > 1) {
                    red = 1;
                }
                if (green > 1) {
                    green = 1;
                }
                if (blue > 1) {
                    blue = 1;
                }
                if (red < 0) {
                    red = 0;
                }
                if (green < 0) {
                    green = 0;
                }
                if (blue < 0) {
                    blue = 0;
                }
                image.setRGB(i, height-1-j, new Color(red, green, blue).getRGB());

            }
        }
        Graphics2D g2d = image.createGraphics();
        g2d.scale(1,-1);
        g2d.translate(0,-height);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i < width; i += 10) {
            for (int j = 0; j < height; j += 10) {
                g2d.drawLine(i, j, (int) (i + 300 * cells[i][j].vx),
                        (int) (j + 300 * cells[i][j].vy));
            }
        }
        drawLines(g2d, Color.GREEN, 0.9f, 0.005f);
        drawLines(g2d, Color.CYAN, 0f, 0f);
        drawLines(g2d, Color.BLUE, 0.6f, 0.02f);
    }

    void drawLines(Graphics2D g2d, Color color, float mu, float grav) {
        float xpart0, xpart1, ypart0, ypart1, vxpart0, vxpart1, vypart0, vypart1;
        g2d.setColor(color);
        for (int iterp = 1; iterp <= 5; iterp++) {
            xpart0 = xpart1 = 0f;
            ypart0 = ypart1 =  (float)(iterp * width / 5 - 1f);
            vxpart0 = (float) cells[(int) xpart0][(int) ypart0].vx;
            vypart0 = (float) cells[(int) xpart0][(int) ypart0].vy;

            while (xpart1 < width - 1 && ypart1 > 0.0 && ypart1 <= height - 1 && xpart1 >= 0.0) {
                vxpart1 = (float) (mu * vxpart0 + (1.0f - mu) * cells[(int) xpart0][(int) ypart0].vx);
                vypart1 = (float) (mu * vypart0 + (1.0f - mu) * cells[(int) xpart0][(int) ypart0].vy - grav);
                xpart1 = xpart0 + 100.f * (vxpart0 + vxpart1) / 2.0f;
                ypart1 = ypart0 + 100.f * (vypart0 + vypart1) / 2.0f;
                vxpart0 = vxpart1;
                vypart0 = vypart1;
                g2d.drawLine((int) xpart0, (int) ypart0, (int) xpart1, (int) ypart1);
                if (Math.abs(xpart1 - xpart0) <= 1e-3 && Math.abs(ypart1 - ypart0) < 1e-3) {
                    break;
                }
                xpart0 = xpart1;
                ypart0 = ypart1;
            }
        }
    }
}



