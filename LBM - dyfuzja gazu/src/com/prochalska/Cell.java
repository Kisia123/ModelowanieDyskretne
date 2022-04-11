package com.prochalska;

public class Cell {
    double in[];
    double out[];
    boolean wall = false;

    Cell() {
        this.in = new double[]{0, 0, 0, 0};//gora,dol,lewo,prawo
        this.out = new double[]{0, 0, 0, 0};
    }
    Cell(double[] out){
        this.out = out;
        this.in = new double[]{0, 0, 0, 0};
    }

    void collision() {

        if(wall) {
            out[0] = in[1];
            out[1] = in[0];

            out[2] = in[3];
            out[3] = in[2];
            return;
        }
        double C = 0;
        for(int i = 0; i < 4;i++){
            C+= in[i];
        }
        double w = 0.25;
        double tau = 5;
        for(int i = 0; i < 4;i++){
            out[i] = in[i] + 1/tau*(w*C-in[i]);
        }
    }

    void streaming(int x, int y, Cell[][] tab) {
        in = new double[]{0,0,0,0};

        if (y > 0) {
            in[0] = tab[x][y-1].out[0];
        }
        if (y + 1 < tab[x].length) {
            in[1] = tab[x][y+1].out[1];
        }
        if (x > 0) {
            in[2]=tab[x-1][y].out[2];
        }
        if (x + 1 < tab.length) {
            in[3]=tab[x+1][y].out[3];
        }

    }

}
