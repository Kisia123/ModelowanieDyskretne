package com.prochalska;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int size = 50;
        int[][] tab = new int[size][size];
        for(int i = 0; i< size;i++){
            for(int j = 0; j<size;j++){
                tab[i][j] = 0;
            }
            /*tab[i][0]=1;
            tab[i][size-1]=1;*/
        }
        tab[0][size/2] =1;

        int reg = 137;
        String s = String.format("%8s",Integer.toBinaryString(reg));
        System.out.println(s);
        int sBin =8;
        boolean[] regTab = new boolean[sBin];
        System.out.println(s.length());

        for(int i = 0; i<sBin; i++){
            if( s.charAt(i)==' '||s.charAt(i)=='0'){
                regTab[i] = false;
            }
            else {
                regTab[i] = true;
            }
        }

        for(int i =0; i< sBin;i++ ){
            System.out.print(regTab[i]? "1":"0");
        }
        int max = 7;
        boolean tab2[] = Arrays.copyOf(regTab,sBin);
        for(int i =0;i<sBin;i++){
            regTab[i]=tab2[max];
            max--;
        }
        System.out.println();
        for(int i = 0; i<sBin;i++){
            System.out.print(regTab[i]? "1":"0");
        }

        System.out.println();
        //sasiedztwo
        for(int i = 1; i< size;i++){
//            tab[i-1][0]=tab[i-1][size-1];
            for(int j = 1; j<size-1;j++){
                boolean t = Neighbour.findNeighbour(tab[i-1][j-1],tab[i-1][j],tab[i-1][j+1],regTab);
                if(t){
                    tab[i][j] = 1;
                }
                else
                    tab[i][j] = 0;
            }
            boolean t = Neighbour.findNeighbour(tab[i-1][size-1],tab[i-1][0],tab[i-1][1],regTab);
            if(t){
                tab[i][0] = 1;
            }
            else
                tab[i][0] = 0;
            t = Neighbour.findNeighbour(tab[i-1][size-2],tab[i-1][size-1],tab[i-1][0],regTab);
            if(t){
                tab[i][size-1] = 1;
            }
            else
                tab[i][size-1]=0;
        }
 /*       for(int i =0;i<size;i++){
            for(int j =0; j< size;j++){

                System.out.print(tab[i][j] == 1 ? "$":" ");
            }
            System.out.println();
        }*/
        BufferedImage image = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i< size;i++){
            for(int j = 0; j<size;j++){
                if(tab[i][j]==1){
                    image.setRGB(j,i, Color.BLACK.getRGB());
                }
                else
                    image.setRGB(j,i, Color.WHITE.getRGB());
            }
        }
        ImageIO.write(image,"bmp",new File("./rys.bmp"));


    }
}
