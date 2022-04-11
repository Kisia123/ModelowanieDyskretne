package com.company;

public class Cell {
    int in, out;
    boolean wall = false;

    Cell(int out) {
        in = 0;
        this.out = out;
    }

    void collision() {
        if (in == 10) {
            out = 5;
        } else if (in == 5) {
            out = 10;
        } else {
            out = in;
        }
    }

    void streaming(int x, int y, Cell[][] tab) {

        in = 0;
        if (y > 0 && (tab[x][y - 1].out & 0b0010) != 0) {
            in += 2;
        }
        if (y + 1 < tab[x].length && (tab[x][y + 1].out & 0b1000) != 0) {
            in += 8;
        }
        if (x > 0 && (tab[x - 1][y].out & 0b0100) != 0) {
            in += 4;
        }
        if (x + 1 < tab.length && (tab[x + 1][y].out & 0b0001) != 0) {
            in += 1;
        }

        if (wall) {
            int inT = 0;
            if ((in & 4) != 0) {
                inT += 1;
            }
            if ((in & 1) != 0) {
                inT += 4;
            }
            if ((in & 2) != 0) {
                inT += 8;
            }
            if ((in & 8) != 0) {
                inT += 2;
            }
            in = inT;
        }
    }

}
