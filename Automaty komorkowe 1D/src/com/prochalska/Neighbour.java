package com.prochalska;

public class Neighbour {
    static boolean findNeighbour(int o,int s, int t,boolean [] tab) {
        int suma = 4*o+2*s+t;
        return tab[suma];

    }
}
