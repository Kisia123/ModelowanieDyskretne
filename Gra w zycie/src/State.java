import java.util.Random;

public class State {
    static Random random = new Random();
    static int [][] cellState(int [][] tab, int s, int w){
        //niezmienne
        if(w == 1) {
            tab[s][s] = 1;
            tab[s - 1][s + 1] = 1;
            tab[s - 1][s + 2] = 1;
            tab[s][s + 3] = 1;
            tab[s + 1][s + 1] = 1;
            tab[s + 1][s + 2] = 1;
        }
        //glider
        if(w ==2){
            tab[s][s] = 1;
            tab[s - 1][s + 1] = 1;
            tab[s - 1][s + 2] = 1;
            tab[s + 1][s + 2] = 1;
            tab[s][s+1]= 1;

        }
        //reczna definicja
        if(w == 3){
            tab[s][s] = 1;
            tab[s - 1][s + 1] = 1;
            tab[s - 1][s + 2] = 1;
            tab[s + 1][s + 1] = 1;
            tab[s][s+1]= 1;
        }
        //oscylator
        if(w==4){
            tab[s][s] = 1;
            tab[s+1][s] = 1;
            tab[s+2][s] = 1;
        }
        //losowo
        if(w==5){
            for(int i = 0;i<tab.length;i++){
                for(int j = 0 ; j < tab.length;j++){
                    tab[i][j] = random.nextBoolean()?1:0;
                }
            }
        }
        return tab;
    }

}
