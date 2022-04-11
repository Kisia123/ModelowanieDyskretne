public class Filters {
    static int lowerFilter(int k){
        int tab[] = {
                1,1,1,
                1,1,1,
                1,1,1};
        return tab[k];

    }
    static int upperFilter(int k){
        int tab[] = {
                -1,-1,-1,
                -1,9,-1,
                -1,-1,-1 };
        return tab[k];
    }
    static int gauss(int k){
        int tab[] = {
                1,4,1,
                4,32,4,
                1,4,1
        };
        return tab[k];
    }


}
