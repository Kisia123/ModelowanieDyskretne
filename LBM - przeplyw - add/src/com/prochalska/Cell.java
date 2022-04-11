package com.prochalska;

enum dir {
    C(0),
    N(1), S(2), W(3), E(4),
    NW(5), NE(6), SW(7), SE(8);

    int val;

    dir(int v) {
        val = v;
    }
}

public class Cell {
    double[] in;
    double[] out;
    double vx, vy = 0;
    double rho = 1;
    double[] eq;

    Cell(double vx) {
        this.vx = vx;
        this.in = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0};//gora,dol,lewo,prawo
        this.out = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.eq = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        double vx2 = this.vx * this.vx;
        double vy2 = this.vy * this.vy;
        double eu2 = 1.f - 1.5f * (vx2 + vy2);
        double Rho36 = this.rho / 36.0f;
        this.eq[dir.C.val] = 16.0f * Rho36 * eu2;
        this.eq[dir.N.val] = 4.0f * Rho36 * (eu2 + 3.0f * this.vy + 4.5f * vy2);
        this.eq[dir.E.val] = 4.0f * Rho36 * (eu2 + 3.0f * this.vx + 4.5f * vx2);
        this.eq[dir.S.val] = 4.0f * Rho36 * (eu2 - 3.0f * this.vy + 4.5f * vy2);
        this.eq[dir.W.val] = 4.0f * Rho36 * (eu2 - 3.0f * this.vx + 4.5f * vx2);
        this.eq[dir.NE.val] = Rho36 * (eu2 + 3.0f * (this.vx + this.vy) +
                4.5f * (this.vx + this.vy) * (this.vx + this.vy));
        this.eq[dir.SE.val] = Rho36 * (eu2 + 3.0f * (this.vx - this.vy) +
                4.5f * (this.vx - this.vy) * (this.vx - this.vy));
        this.eq[dir.SW.val] = Rho36 * (eu2 + 3.0f * (-this.vx - this.vy) +
                4.5f * (this.vx + this.vy) * (this.vx + this.vy));
        this.eq[dir.NW.val] = Rho36 * (eu2 + 3.0f * (-this.vx + this.vy) +
                4.5f * (-this.vx + this.vy) * (-this.vx + this.vy));
        this.in[dir.C.val] = this.eq[dir.C.val];
        this.in[dir.E.val] = this.eq[dir.E.val];
        this.in[dir.N.val] = this.eq[dir.N.val];
        this.in[dir.S.val] = this.eq[dir.S.val];
        this.in[dir.W.val] = this.eq[dir.W.val];
        this.in[dir.NE.val] = this.eq[dir.NE.val];
        this.in[dir.NW.val] = this.eq[dir.NW.val];
        this.in[dir.SE.val] = this.eq[dir.SE.val];
        this.in[dir.SW.val] = this.eq[dir.SW.val];
    }

}

