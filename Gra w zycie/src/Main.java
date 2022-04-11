import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int size = 20;
        int half = size / 2;
        int l = 1000;
        int c = 1;

        int[][] tab = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tab[i][j] = 0;
            }
        }
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        ImageOutputStream output = new FileImageOutputStream(new File("./gif.gif"));
        GifSequenceWriter gif = new GifSequenceWriter(output, image.getType(), 100, true);

        //stan poczatkowy
        State.cellState(tab, half, c);
     /*   for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(tab[i][j]);
            }
            System.out.println();
        }*/
        //tworzenie nowej tablicy
        int[][] tab2 = new int[size][size];

        //przejscie
        for (int n = 0; n < l; n++) {
            System.out.println(n);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int sum = Neighbour.findNeighbours(tab, i, j);
                    if (tab[i][j] == 1) {
                        if (sum == 3 || sum == 2) {
                            tab2[i][j] = 1;
                        } else {
                            tab2[i][j] = 0;
                        }
                    } else {
                        if (sum == 3) {
                            tab2[i][j] = 1;
                        } else {
                            tab2[i][j] = 0;
                        }
                    }
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    tab[i][j] = tab2[i][j];
                    tab2[i][j] = 0;
                    if (tab[i][j] == 1) {
                        image.setRGB(i, j, Color.BLACK.getRGB());
                    } else {
                        image.setRGB(i, j, Color.WHITE.getRGB());
                    }
                }
            }
            gif.writeToSequence(image);

        }
        gif.close();


    }
}

